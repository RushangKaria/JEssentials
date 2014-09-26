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
* File : CAPrinter.java
*           Generic class to print covering arrays.
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

package com.coveringarray.printers;

import com.coveringarray.CoveringArrayParameters;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

/**
* A generic print protocol for
* covering arrays.
*/
abstract class CAPrinter
{
    /**
    * The file writer for this printer.
    */
    protected PrintWriter ca_writer;

    /**
    * The covering array to be printed.
    */   
    protected final CoveringArrayParameters parameters;
   
    /**
    * The dont care symbol for this printer.
    */    
    protected String DONT_CARE = "*";
    
    /**
    * Instantiates a generic printer.
    */
    protected CAPrinter(CoveringArrayParameters parameters)
    {
        this.parameters = parameters;
    }
    
    /**
    * Tries to open a stream for printing.
    * @return true if the file could be opened, false otherwise.
    */
    protected boolean open(File output_file)
    {
        try
        {
        this.ca_writer = new PrintWriter(new BufferedWriter(new FileWriter(output_file, false)));
        
        return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    /**
    * Tries to close the stream.
    * @return true if the file could be closed, false otherwise.
    */
    protected boolean close()
    {
        try
        {
        this.ca_writer.close();
        
        return true;
        }
        catch(Exception e)
        {
        return false;
        }
    }

    /**
    * Gets the output file format for the printer.
    * @return the file name of the method that the printer uses.
    */
    protected abstract String getFileName();
    
    /**
    * Gets the first row format for the printer.
    * @return the first row format of the method that the printer uses.
    */
    protected abstract String getFirstRow();
    
    /**
    * Gets any special message for the CA.
    * @return any special message of the method that the printer uses.
    */
    protected abstract String getSpecialMessage();
    
    /**
    * Prints the covering array into the file.
    * @return true on success, false if there was an error.
    */
    protected boolean print()
    {
        try
        {
            for(int i=0;i<this.parameters.N;i++)
            {
                for(int j=0;j<this.parameters.k;j++)
                if(this.parameters.CA[i][j] < 0 || this.parameters.CA[i][j] >= this.parameters.v)
                this.ca_writer.print(this.DONT_CARE + " ");
                else
                this.ca_writer.print(this.parameters.CA[i][j] + " ");
            
            this.ca_writer.println();
            }
        
        return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    /**
    * Prints the specified message into the file.
    * @return true on success, false if there was an error.
    */    
    protected boolean print(String message)
    {
        try
        {
            this.ca_writer.println(message);
        
        return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
