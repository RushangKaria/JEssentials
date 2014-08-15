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
* File : CoveringArrayParserDemo.java
*           Demo program to demonstrate use of the CoveringArrayParse class.
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

package samples.coveringarray.parsers;

import java.io.File;

import com.coveringarray.CoveringArrayParameters;
import com.coveringarray.parsers.CoveringArrayParser;
import com.util.file.FileUtils;

public class CoveringArrayParserDemo
{
    private final String CA_SOURCE_PATH = "inputs/coveringarray/";

    CoveringArrayParserDemo()
    {

    }

    void driver()
    {
        //parseColbourn();
        //parseNIST();
        //parseJtj();
        //parseCanonical();
        parseUnsupported();
    }

    void parseColbourn()
    {
    System.out.println("-------------------- COLBOURN ARRAY TEST --------------------");

    CoveringArrayParameters ca;
    CoveringArrayParser parser;
    FileUtils file_utils;
    File files[];

        files = FileUtils.getFiles(this.CA_SOURCE_PATH + "colbourn/");

        for(File covering_array : files)
        {
        parser = new CoveringArrayParser(covering_array); 

            if(parser.parse())
            {
            ca = parser.getParameters();
            ca.display();
            }
        }
        
    System.out.println("-------------------------------------------------------------");            
    }

    void parseNIST()
    {
    System.out.println("---------------------- NIST ARRAY TEST ----------------------");        

    CoveringArrayParameters ca;
    CoveringArrayParser parser;
    FileUtils file_utils;
    File files[];

        files = FileUtils.getFiles(this.CA_SOURCE_PATH + "nist/");

        for(File covering_array : files)
        {
        parser = new CoveringArrayParser(covering_array); 

            if(parser.parse())
            {
            ca = parser.getParameters();
            ca.display();
            }
        }
        
    System.out.println("-------------------------------------------------------------");    
    }

    void parseJtj()
    {
    System.out.println("---------------------- JTJ ARRAY TEST -----------------------");        

    CoveringArrayParameters ca;
    CoveringArrayParser parser;
    FileUtils file_utils;
    File files[];

        files = FileUtils.getFiles(this.CA_SOURCE_PATH + "jtj/");

        for(File covering_array : files)
        {
        parser = new CoveringArrayParser(covering_array); 

            if(parser.parse())
            {
            ca = parser.getParameters();
            ca.display();
            }
        }
        
    System.out.println("-------------------------------------------------------------");  
    }

    void parseCanonical()
    {
    System.out.println("-------------------- CANONICAL ARRAY TEST --------------------");        

    CoveringArrayParameters ca;
    CoveringArrayParser parser;
    FileUtils file_utils;
    File files[];

        files = FileUtils.getFiles(this.CA_SOURCE_PATH + "canonical/");

        for(File covering_array : files)
        {
        parser = new CoveringArrayParser(covering_array); 

            if(parser.parse())
            {
            ca = parser.getParameters();
            ca.display();
            }
        }
        
    System.out.println("-------------------------------------------------------------");  
    }

    void parseUnsupported()
    {
    System.out.println("-------------------- UNKNOWN ARRAY TEST --------------------");        

    CoveringArrayParameters ca;
    CoveringArrayParser parser;
    FileUtils file_utils;
    File files[];

        files = FileUtils.getFiles(this.CA_SOURCE_PATH + "unknown/");

        for(File covering_array : files)
        {
        parser = new CoveringArrayParser(covering_array); 

            if(parser.parse())
            {
            ca = parser.getParameters();
            ca.display();
            }
        }
        
    System.out.println("-------------------------------------------------------------");      
    }

    public static void main(String args[])
    {
        CoveringArrayParserDemo demo = new CoveringArrayParserDemo();
        demo.driver();
    }      
}
