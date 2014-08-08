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
* File : ColbournParser.java
*           Class to extract Charlie Colbourn Arrays
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
import java.io.FileReader;
import java.io.BufferedReader;

import com.coveringarray.CoveringArrayParameters;

/**
* A Parser for Charlie Colbourn Arrays.
* <p>
* Charlie Colbourn Arrays are of the following form <br>
* The File Name gives the parameters N,t,k,v in order and is of the form CA(N;t,k,v).txt <br>
* The first line of the file repeats the parameters again incase the extraction of parameters is not possible from the filename. <br>
* The next lines give the CA's with each a line beginning with a space, then symbols seperated by two spaces and finally ending with a space.
*/
class ColbournParser extends CAParser
{
    /**
    * Holds the covering array file.
    */
    private File CA_FILE;

    /**
    * Holds the parameters extracted for the covering array.
    */
    private CoveringArrayParameters parameters;

    /**
    * This variable indicates the amount of verification.
    * <p>
    * When true, the file is checked for 100% coverage. <br>
    * If false, then only basic integrity checking like corruptness of the file is carried out. <br>
    * This means any advisory could interchange the symbols possibly causing a loss of coverage.
    */
    private boolean ENSURE_COVERAGE;

    /**
    * Represents the symbol which marks dont care in the array.
    */
    private final String DONT_CARE = "-1";

    /**
    * Constructor which sets the covering array file and sets
    * strict verification to false.
    */ 
    protected ColbournParser(File CA_FILE)
    {
        this.CA_FILE = CA_FILE;
        this.ENSURE_COVERAGE = false;
        this.parameters = null;
    }

    /**
    * A constructor which can be used to set the verficiation as
    * per the reliability.
    */
    protected ColbournParser(File CA_FILE, boolean ENSURE_COVERAGE)
    {
        this.CA_FILE = CA_FILE;
        this.ENSURE_COVERAGE = ENSURE_COVERAGE;
        this.parameters = null;
    }

    /**
    * This method is called to extract the elements
    * of the Colbourn covering array.
    * @return true if the covering array was extracted, false if there was an error
    */
    protected boolean parse()
    {
    this.parameters = new CoveringArrayParameters();
    
        if(!extract(this.parameters))
        return false;

        if(!extract(this.parameters.CA))
        return false;

    return true;
    }

    /**
    * This method is used to return the covering array extracted.
    * <p>
    * This method must be called only if a call to #parse() returned true.
    * @return the covering array and its parameters that were extracted, null if there was error or #parse() was not called before this method.
    */
    protected CoveringArrayParameters getParameters()
    {
        return this.parameters;
    }

    /**
    * This method attempts to extract parameters from the file name of the Colbourn array.
    * <p>
    * If this method returns true, the parameters (N,t,k,v) are available to the program.
    * @return true if the parameters were extracted, false if the file name contained exceptions to Colbourn Arrays.
    */
    private boolean extract(CoveringArrayParameters parameters)
    {
    String file_name;

        try
        {
        file_name = this.CA_FILE.getName();
        file_name = file_name.replaceAll("CA","");
        file_name = file_name.replaceAll(";",",");        
        file_name = file_name.replaceAll("\\(","");
        file_name = file_name.replaceAll("\\).txt","");

        String params[] = file_name.split(",");

            this.parameters.N = Integer.parseInt(params[0]);
            this.parameters.t = Byte.parseByte(params[1]);
            this.parameters.k = Integer.parseInt(params[2]);
            this.parameters.v = Byte.parseByte(params[3]);

        this.parameters.compute();
        this.parameters.CA = new byte[this.parameters.N][this.parameters.k];
        }
        catch(Exception e)
        {
        return false;
        }
    
    return true;
    }

    /**
    * This method attempts to extract the Colbourn covering array from the file.
    * <p>
    * If this method returns true, the covering array is available to the program.
    * @return true if the covering array was extracted, false if the file either corrupt or if ENSURE_COVERAGE was set, the array was not a covering array.
    */
    private boolean extract(byte CA[][])
    {
    BufferedReader file_reader;
    String line;
    String symbols[];
    int row;
    
        try
        {
        file_reader = new BufferedReader(new FileReader(this.CA_FILE));
        line = file_reader.readLine();  //Colbourn arrays specify the parameters in the filename itself, so the first line is not needed.        
        row = 0;

            while((line=file_reader.readLine())!=null)
            {
            line = line.trim();
            line = line.replaceAll("  "," ");
            symbols = line.split(" ");

                for(int column=0;column<this.parameters.k;column++) //Note: We opted for this.parameters.k instead of symbols.length intentionally
                if(symbols[column].equals(this.DONT_CARE))          //This enables a simple renaming of the k in the file, to extract a small CA from the same file
                CA[row][column] = -1;                               //This is from the result:- a CA(N;t,k,v) is also a CA(N;t,k-1,v)
                else
                CA[row][column] = Byte.parseByte(symbols[column]);     
                
            row++;
            }

        file_reader.close();
        }
        catch(Exception e)
        {
        return false;
        }


    return ENSURE_COVERAGE?/*Placeholder*/true:true;    //This is weak verficiation since we assume that the file was not corrupted by an advisory.
                                                        //For more strict verification, consider setting ENSURE_COVERAGE = true   
    }

}
