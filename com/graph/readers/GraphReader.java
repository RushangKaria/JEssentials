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
* File : GraphReader.java
*           Utility to read graphs from files
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

package com.graph.readers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

import com.graph.Graph;
import com.graph.UndirectedGraph;

/**
* Utility to read graphs from files.
* Supported formats are DIMACS
*/
public class GraphReader
{
    private enum Format {DIMACS,UNKNOWN};
    private String FILE_NAME;


    /**
    * Constructor takes in a file name from
    * which the graph is to be read
    */
    public GraphReader(String FILE_NAME)
    {
        this.FILE_NAME = FILE_NAME;
    }

    /**
    * Constructor takes in a file from
    * which the graph is to be read
    */
    public GraphReader(File graph_file)
    {
        this.FILE_NAME = graph_file.toString(); //We dont use getName() since we need the entire path
    }    

    /**
    * Default constructor that takes no arguments.
    * However the file name must be updated with a call
    * to read else the graph reading will fail
    */ 
    public GraphReader()
    {
        this.FILE_NAME = "";
    }

    /**
    * Reads the graph specified by the file nameand returns it if 
    * it is in one of the supported formats and not broken.
    * @return Returns the graph else returns null on error
    */
    public Graph read()
    {

        switch(getFormat(this.FILE_NAME))     
        {
        case DIMACS  : return readDimacs();

        case UNKNOWN :
        break;
        
        }
    
    return null;
    }

    /**
    * Reads the graph specified by the file nameand returns it if 
    * it is in one of the supported formats and not broken.
    * @return Returns the graph else returns null on error
    */
    public Graph read(String FILE_NAME)
    {
    this.FILE_NAME = FILE_NAME;

        return read();        
    }

    /**
    * This method is used to read Graphs that are in the DIMACS format.
    * More info about the DIMACS format can be found a http://prolland.free.fr/works/research/dsat/dimacs.html
    * @return a Graph if it conforms to the DIMACS format, null otherwise
    */
    private Graph readDimacs()
    {
    BufferedReader file_reader;
    String line;
    Graph G = null;
    boolean got_nodes = false;
    
        try
        {
        file_reader = new BufferedReader(new FileReader(this.FILE_NAME));
        
            while((line=file_reader.readLine())!=null)
            {             
                switch(line.charAt(0))
                {
                case 'c' :  break;
                
                case 'p' :  if(got_nodes) 
                            throw new Exception();
                            else
                            {
                            String vertex_data[] = line.split(" ");
                            int vertices = Integer.parseInt(vertex_data[2]);
                            G = new UndirectedGraph(vertices);
                            got_nodes = true;
                            }
                break;
                
                case 'e' :  String vertex_data[] = line.split(" ");
                            int v1 = Integer.parseInt(vertex_data[1]) - 1;
                            int v2 = Integer.parseInt(vertex_data[2]) - 1;

                                if(G == null) throw new Exception();
                                else G.addEdge(v1,v2);
                break;                          
                }
                
            }
        file_reader.close();
        }
        catch(Exception e)
        {
            System.out.println("Could not read the graph file/File format is incorrect");
        }

    return G;
    }

    /**
    * This method identifies the format of the file by
    * its extension. Each graph file has a different format.
    * @return The format type in which the graph is stored.
    */
    private Format getFormat(String file_name)
    {
        if(file_name.contains(".col.b") || file_name.contains(".col"))
        return Format.DIMACS;
        else
        return Format.UNKNOWN;                
                  
    } 
}
