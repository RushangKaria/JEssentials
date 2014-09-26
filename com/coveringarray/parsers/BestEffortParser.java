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
* File : BestEffortParser.java
*           Class to extract Unknown Arrays
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

import java.util.LinkedList;
import java.util.Iterator;

import com.coveringarray.CoveringArrayParameters;
import com.coveringarray.verifier.CoveringArrayVerifier;

/**
* A Parser for Charlie Colbourn Arrays.
* <p>
* Charlie Colbourn Arrays are of the following form <br>
* The File Name gives the parameters N,t,k,v in order and is of the form CA(N;t,k,v).txt <br>
* The first line of the file repeats the parameters again incase the extraction of parameters is not possible from the filename. <br>
* The next lines give the CA's with each a line beginning with a space, then symbols seperated by two spaces and finally ending with a space.
*/
class BestEffortParser extends CAParser
{

    /**
    * A list of known and supported seperators.
    */
    private final char[] SEPERATORS = { ' ', '\t', ',', '\n' };

    /**
    * A list of known and supported dont care symbols.
    */
    private final char[] DONT_CARES = { '-', '*'};

    /**
    * The maximum strength of the CA that this parser can detect.
    */
    private final byte MAX_T = 6;
    
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
    protected BestEffortParser(File CA_FILE)
    {
        this.CA_FILE = CA_FILE;
        this.ENSURE_COVERAGE = false;
        this.parameters = null;
    }

    /**
    * A constructor which can be used to set the verficiation as
    * per the reliability.
    */
    protected BestEffortParser(File CA_FILE, boolean ENSURE_COVERAGE)
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
    LinkedList<LinkedList<Byte>> CA;

        CA = extract(this.parameters);

        if(CA == null)
        return false;

        if(!verify(CA))
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
    private LinkedList<LinkedList<Byte>> extract(CoveringArrayParameters parameters)
    {
    BufferedReader file_reader;    
    String line;
    LinkedList<Byte> row;
    LinkedList <LinkedList<Byte>> CA;
    int k = Byte.MIN_VALUE;
    int no_of_rows;
    boolean first;
    byte v = Byte.MIN_VALUE;
    byte second_v = Byte.MIN_VALUE;
    
        try
        {
        file_reader = new BufferedReader(new FileReader(this.CA_FILE));
        no_of_rows = 0;
        CA = new LinkedList<LinkedList<Byte>>();

            while((line = file_reader.readLine())!=null)
            {           
            if(isComment(line))            //if the file contains comments (any character not a number is a comment)
            continue;                       //we discard all its information.
                                            //The first row is usually redundant since the only cryptic info it would give
                                            //would be t, which the parser finds out by verification of coverage.
                row = getRow(line + '\n' );

                    if(row == null)
                    continue;

                byte temp = row.removeLast();

                    if(temp > v)                    
                    v = temp;
                    else if(temp > second_v)
                    second_v = temp;

                    k = row.size();
                    CA.add(row);                         
                    no_of_rows++;
            }
                                     
        file_reader.close();
        }
        catch(Exception e)
        {
        return null;
        }
        
        row = CA.getFirst();

            if(row.getFirst() == no_of_rows - 1)
            {
            CA.removeFirst();
            parameters.N = no_of_rows - 1;
            v = second_v;
            }
            else
            parameters.N = no_of_rows;

    parameters.v = ++v ;
    parameters.k = k;      
    parameters.CA = convertToArray(CA);          
    return parameters.CA == null ? null : CA;
    }

    private boolean isComment(String line)
    {
        for(int i=0;i<line.length();i++)
        if(!isNumber(line.charAt(i)) && !isSeperator(line.charAt(i)) && !isDontCare(line.charAt(i)))
        return true;

    return false;
    }   

    private byte[][] convertToArray(LinkedList<LinkedList<Byte>> CA)
    {
    byte covering_array[][] = null;
    Iterator<LinkedList<Byte>> row_iterator;
    Iterator<Byte> column_iterator;
    int row = 0;
    
        try
        {
        covering_array = new byte[this.parameters.N][this.parameters.k];
        row_iterator = CA.iterator();

            while(row_iterator.hasNext())
            {
            LinkedList<Byte> columns = row_iterator.next();
            column_iterator = columns.iterator();
            int column = 0;
            
                while(column_iterator.hasNext())
                covering_array[row][column++] = column_iterator.next();

            row++;
            }    
        }
        catch(Exception e)
        {
        return null;
        }
    
    return covering_array;
    }

    private LinkedList<Byte> getRow(String line)
    {
    boolean processing_symbol;
    String symbol;
    LinkedList<Byte> row;
    byte max_symbol = Byte.MIN_VALUE;

        row = new LinkedList<Byte>();
        symbol = "";
        processing_symbol = false;
    
        for(int i=0; i<line.length(); i++)
        {
            if(isSeperator(line.charAt(i)))
            {
                if(processing_symbol)
                {
                int v;
                   
                    try 
                    { 
                        v = Integer.parseInt(symbol); 

                        if(v > Byte.MAX_VALUE) return null;
                        
                    } 
                    catch(Exception e) 
                    { 
                    v = -1; 
                    }

                row.add((byte)v);
                max_symbol = (byte)v > max_symbol ? (byte)v : max_symbol;
                processing_symbol = false;
                symbol = "";
                }                
            }
            else
            {
            processing_symbol = true;
            symbol += line.charAt(i);
            }
        }
    
    row.add(max_symbol);
    
    return row;
    }

    private boolean isSeperator(char symbol)
    {
        for(int i=0;i<this.SEPERATORS.length;i++)
        if(symbol == this.SEPERATORS[i])
        return true;

    return false; 
    }

    private boolean isDontCare(char symbol)
    {
        for(int i=0;i<this.DONT_CARES.length;i++)
        if(symbol == this.DONT_CARES[i])
        return true;

    return false;
    }

    private boolean isNumber(char symbol)
    {
    return symbol >= '0' && symbol <= '9';
    }

    /**
    * This method attempts to extract the Colbourn covering array from the file.
    * <p>
    * If this method returns true, the covering array is available to the program.
    * @return true if the covering array was extracted, false if the file either corrupt or if ENSURE_COVERAGE was set, the array was not a covering array.
    */
    private boolean verify(LinkedList<LinkedList<Byte>> CA)
    {
    CoveringArrayVerifier verifier;
    byte t = this.parameters.k > this.MAX_T ? MAX_T : (byte)this.parameters.k;   
    
        while(t >= 2)
        {
        this.parameters.t = t;
        this.parameters.compute();

            verifier = new CoveringArrayVerifier(this.parameters);

            try
            {
            System.out.println("Trying for strength ::" + this.parameters.t);
                if(verifier.verify())
                return true;
            }
            catch(Exception e)
            {
            System.out.println(e);
            return false;
            }

        t--;        
        }
    
    return false;
    }

}
