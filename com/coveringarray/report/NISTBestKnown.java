/*
* Description : This software is an implementation of the algorithms 
*               in the Introduction to Algorithms book by
*               Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein.
* 
*               Information about the book can be found here :-
*               http://en.wikipedia.org/wiki/Introduction_to_Algorithms
*               http://mitpress.mit.edu/books/introduction-algorithms
*
*
* Author :      Rushang Vinod Vandana Karia
*                   - Rushang.Karia@asu.edu
*                   - 4806283130
*                   - github.com/RushangKaria
*                   - Arizona State University
*
* Author :      Shrijal Pravin Gandhi
*                   - Shrijal.Gandhi@asu.edu
*                   - 4806282324
*                   - github.com/ShrijalGandhi
*                   - Arizona State University
*
* File : DSaturColoring.java
*           Finds a Random Greedy Coloring of the Graph
*
*    Copyright (C) 2014  Rushang Karia, Shrijal Gandhi
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.

*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*/

package com.coveringarray.report;

import com.coveringarray.CoveringArrayParameters;

import java.net.URL;
import java.net.URLConnection;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
* Get the best known rows of NIST arrays using
* Charlie NIST covering array tables as the 
* authority.
* {@link http://math.nist.gov/coveringarrays/ipof/ipof-results.html}
*/
class NISTBestKnown
{
    /**
    * The strength of the covering array whose best known rows are to be found.
    */
    private int t;

    /**
    * The # of columns of the covering array whose best known rows are to be found.
    */
    private int k;

    /**
    * The # of values each symbol can take of the covering array whose best known rows are to be found.
    */
    private int v;

    /**
    * The URL for the NIST Covering Array Tables.
    */
    private final String BASE_ADDRESS = "http://math.nist.gov/coveringarrays/ipof/";

    /**
    * index.php for the #BASE_ADDRESS
    */
    private final String INDEX_HTML = "ipof-results.html";

    /**
    * The page where the best known for the parameters will be found.
    */
    private String URL;

    /**
    * Constructs a NISTBestKnown fetcher using
    * values t,k,v.
    */
    protected NISTBestKnown(int t, int k, int v)
    {
        this.t = t;
        this.k = k;
        this.v = v;
    }

    /**
    * Returns the best known rows of the given parameters
    * @return the best known rows for these parameters as mentioned on Charlie NIST's webpage.
    */
    protected String get()
    {
    URLConnection connection;
    BufferedReader data_reader;
    String data;
    int k;
    String rows = "-";
    
        if((connection = formURL()) == null)
        {
            System.out.println("There appears to be an error with the connection.. Returning -1");
            return rows;
        }

        if((data_reader = getStream(connection)) == null)
        {
            System.out.println("There appears to be an error with fetching content.. Returning -1");
            return rows;
        }

        try
        {
            while((data = data_reader.readLine()) != null)
            {
            String split_1[] = data.split("<tr><td headers=\"k\">");

                if(split_1.length == 2)
                {
                    try { k = Integer.parseInt(split_1[1].substring(0, split_1[1].indexOf('<'))); } catch(Exception e) { continue; }

                    if(k < this.k)
                    continue;
               else if( k > this.k)
                    break;
               else
                   {
                        try
                        {                
                        split_1[1] = split_1[1].split("Filesize: ")[1]; 
                        rows = split_1[1].substring(split_1[1].indexOf('>')+1, split_1[1].indexOf('<'));
                        }
                        catch(Exception e)
                        {}
                        
                    break;
                   }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("There appears to be a problem with the streams!!... ");
        }

    return rows;
    }

    /**
    * @return returns a buffered reader for fetching all the content from the URL.
    */
    private BufferedReader getStream(URLConnection connection)
    {
    InputStream URLStream;
    InputStreamReader reader;
    BufferedReader data_reader;
    
        try
        {
        URLStream = connection.getInputStream();
        reader = new InputStreamReader(URLStream);
        data_reader = new BufferedReader(reader);
        }
        catch(Exception e)
        {
            data_reader = null;
        }

    return data_reader;
    }

    /**
    * @return returns the URL where the record for the specified parameters will be found.
    */
    private URLConnection formURL()
    {
    URL source;
    URLConnection connection;

        try
        {
        source = new URL(BASE_ADDRESS + "tables/table." + this.t + "." + this.v + ".html");

            connection = source.openConnection();

        connection.connect();
        }
        catch(Exception e)
        {
            connection = null;
        }

    return connection;
    }
    
}
