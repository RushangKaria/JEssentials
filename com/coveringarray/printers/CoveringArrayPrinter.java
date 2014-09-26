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
* File : DontCareMarker.java
*           Marks symbols as dont cares in the covering array.
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

import java.io.File;

import com.coveringarray.CoveringArrayParameters;
import com.coveringarray.printers.CAPrinter;
import com.coveringarray.printers.NISTPrinter;

/**
* Prints covering arrays to files in 
* various formats.
*/
public class CoveringArrayPrinter
{
    /**
    * The types supported for printing.
    */
    public enum FORMAT { COLBOURN, NIST, JIMENEZ, CANONICAL };
    
    /**
    * The current print format.
    */
    private FORMAT current_format;

    /**
    * Instantiates a printer object and sets the printing
    * format to the #FORMAT.COLBOURN
    */
    public CoveringArrayPrinter()
    {
        this.current_format = FORMAT.COLBOURN;
    }

    /**
    * Instantiates a printer object and sets the printing
    * format to the one specified.
    * @param current_format the format to print the covering array in.
    */
    public CoveringArrayPrinter(FORMAT current_format)
    {
        this.current_format = current_format;
    }
    
    /**
    * Prints the covering array to the output path using the
    * current format.
    * @param parameters the covering array to be printed.
    * @param output_path the output path where the covering array is to be printed.
    * @return true if the covering array was printed, false if there was an error.
    */
    public boolean print(CoveringArrayParameters parameters, String output_path)
    {
    CAPrinter printer;
    String temp;
    File output_file;
    
        printer = getPrinter(parameters);
        
            if(printer == null)
            return false;

        if(output_path.charAt(output_path.length()-1) != '/')
        output_path += '/';

        temp = printer.getFileName();
        output_file = new File(output_path + temp);

            if(!printer.open(output_file))
            return false;

        temp = printer.getSpecialMessage();
        
            if(temp != null)
            printer.print(temp);
            
        temp = printer.getFirstRow();
        
            printer.print(temp);
            printer.print();
            
    printer.close();
    return true;
    }
    
    /**
    * Returns a printer object to print the covering array.
    * @param the covering array parameters to be printed.
    * @return a printer object based on the current printing format.
    */ 
    private CAPrinter getPrinter(CoveringArrayParameters parameters)
    {
        switch(current_format)
        {
        case NIST : return new NISTPrinter(parameters);
        
        default   : return null;
        }
    }
    
    /**
    * Sets the current printing format to the one specified.
    * @param current_format the format the printer must be set to.
    */
    public void setFormat(FORMAT current_format)
    {
        this.current_format = current_format;
    }
    
    
}
