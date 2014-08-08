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
* File : CoveringArrayParser.java
*           A parser for covering arrays to get the parameters and the array
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

package com.coveringarray.parsers;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.regex.Pattern;

import com.coveringarray.CoveringArrayParameters;
import com.coveringarray.parsers.CAParser;
import com.coveringarray.parsers.ColbournParser;

/**
* A Parser for covering arrays that can be used to 
* extract the parameters and the array.
* <p>
* Supported types are:- <br>
* <table>
* <tr><td>Colbourn Arrays</td><td> CA(N;t,k,v).txt</td></tr>
* <tr><td>NIST Arrays</td><td>ca.t.v^t.txt</td></tr>
* <tr><td>Torres-Jimenez Arrays</td><td>N[no]k[no]v[no]^t[no].ca</td></tr>
* <tr><td>Non-Standard Arrays</td><td>t_k_v.txt</td></tr>
* </table>
* <p>
* The class makes a best effort detection for unsupported arrays also.
*/ 
public class CoveringArrayParser
{

    /**
    * holds the covering array file.
    */
    private File CA_FILE;

    /**
    * @see com.coveringarray.parsers.Parser;
    */
    private CAParser parser;
   
    /**
    * Supported Covering Array file formats
    */ 
    private enum ARRAY_TYPE {COLBOURN,NIST,JTJ,CANONICAL,UNSUPPORTED};

    /**
    * Regular Expression for C.J. Colbourn file format.
    */
    private final String COLBOURN_PATTERN  = "CA\\(\\d+;\\d+,\\d+,\\d+\\)\\.txt";

    /**
    * Regular Expression for NIST file format.
    */ 
    private final String NIST_PATTERN      = "ca\\.\\d+\\.\\d+\\^\\d+\\.txt";

    /**
    * Regular Expression for Jose Torres-Jimenez file format.
    */
    private final String JIMENEZ_PATTERN   = "N\\d+k\\d+v\\d+\\^\\d+t\\d+\\.ca";

    /**
    * Regular Expression for Canonical file format.
    */
    private final String CANONICAL_PATTERN = "\\d+_\\d+_\\d+\\.txt";

    /**
    * Constructor taking the file of the CA to be extracted.
    */
    public CoveringArrayParser(File CA_FILE)
    {
        this.CA_FILE = CA_FILE;
        this.parser  = null;
    }

    /**
    * Constructor taking the file path of the CA to be extracted.
    * @throws FileNotFoundException if the file is not found/is a directory
    */
    public CoveringArrayParser(String File_path)throws FileNotFoundException
    {
        this.CA_FILE = new File(File_path);

            if(!CA_FILE.exists() || CA_FILE.isDirectory())
            throw new FileNotFoundException();

        this.parser = null;
    }

    /**
    * This method parses the covering array and extracts the relevant parameters.
    * It returns true which indicates that #getParameters() can be used to get the parameters and the Covering Array
    * @return true if parsing was successful, false if the file was corrupted or not conforming to the type
    */
    public boolean parse()
    {
        switch(extract_type())
        {
        case COLBOURN          : this.parser = new ColbournParser(this.CA_FILE);
                                 return parser.parse();

        case NIST              : this.parser = new NistParser(this.CA_FILE);
                                 return parser.parse();        

        case JTJ               : this.parser = new JimenezParser(this.CA_FILE);
                                 return parser.parse();

        case CANONICAL         : this.parser = new CanonicalParser(this.CA_FILE);
                                 return parser.parse();

        case UNSUPPORTED       :
        default                : System.out.println("UNSUPPORTED");
                                 return false;
        }

    }

    public CoveringArrayParameters getParameters()
    {
        if(this.parser == null)
        return null;
        else
        return this.parser.getParameters();
    }

    /**
    * This method matches the file format against
    * the supported and returns the type of format for further processing.
    * @return The type of format of the Covering Array if supported else returns unsupported file format.
    */
    private ARRAY_TYPE extract_type()
    {
    String file_name = this.CA_FILE.getName();  

        if(Pattern.matches(this.COLBOURN_PATTERN  ,file_name))  return ARRAY_TYPE.COLBOURN;
        if(Pattern.matches(this.NIST_PATTERN      ,file_name))  return ARRAY_TYPE.NIST;
        if(Pattern.matches(this.JIMENEZ_PATTERN   ,file_name))  return ARRAY_TYPE.JTJ;
        if(Pattern.matches(this.CANONICAL_PATTERN ,file_name))  return ARRAY_TYPE.CANONICAL;

    return ARRAY_TYPE.UNSUPPORTED;
    }



    
}
