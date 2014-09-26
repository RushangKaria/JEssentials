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
* File : CoveringArrayPrinterDemo.java
*           Demo class to illustrate the use of CoveringArrayPrinter
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

package samples.coveringarray.printers;

import java.io.File;

import com.coveringarray.CoveringArrayParameters;
import com.coveringarray.parsers.CoveringArrayParser;
import com.coveringarray.printers.CoveringArrayPrinter;

/**
* Demo class to illustrate the use of
* {@link com.coveringarray.printers.CoveringArrayPrinter}
*/
public class CoveringArrayPrinterDemo
{
    /**
    * Input file for a covering array.
    */
    private final String INPUT_CA = "inputs/coveringarray/nist/ca.3.5^7.txt";
    
    /**
    * Output folder where the printed covering arrays will be printed.
    */
    private final String OUTPUT_SRC_PATH = "outputs/coveringarray/printed";

    /**
    * Instantiates a test object.
    */
    protected CoveringArrayPrinterDemo()
    {
    
    }
    
    /**
    * Demonstrates the use of most of the functions of
    * {@link com.coveringarray.printers.CoveringArrayPrinter}
    */
    protected void driver()
    {
    CoveringArrayParser parser;
    CoveringArrayParameters parameters;
    File ca_file;
        
        ca_file = new File(this.INPUT_CA);
        parser = new CoveringArrayParser(ca_file);
        
            if(!parser.parse())
            {
            System.out.println("The covering array could not be read");
            System.exit(0);
            }    
        
        parameters = parser.getParameters();
        
        testNIST(parameters);
    }

    /**
    * Prints NIST arrays.
    */    
    private void testNIST(CoveringArrayParameters parameters)
    {
    CoveringArrayPrinter printer;
    
        printer = new CoveringArrayPrinter(CoveringArrayPrinter.FORMAT.NIST);
        
        if(!printer.print(parameters, this.OUTPUT_SRC_PATH))
        System.out.println("[WARNING] The covering array experienced an error printing");
    }
    
    public static void main(String args[])
    {
        CoveringArrayPrinterDemo demo = new CoveringArrayPrinterDemo();
        demo.driver();
    }
}
