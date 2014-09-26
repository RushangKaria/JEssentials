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
* File : NISTPrinter.java
*           Prints covering arrays in NIST format.
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

/**
* Prints covering arrays in NIST format.
* The NIST format is as follows :- <br>
* File Name       := ca.t.v^k.txt  <br>
* Special Message := none          <br>
* First row       := N             <br>
* CA Print format := single space after every symbol
*/
class NISTPrinter extends CAPrinter
{
    /**
    * Initializes a NIST Printer.
    */
    protected NISTPrinter(CoveringArrayParameters parameters)
    {
        super(parameters);
    
    this.DONT_CARE = "-";
    }
    
    /**
    * @return the file name in NIST Format.
    */
    protected String getFileName()
    {
        /*<--- ca.t.v^k.txt --->*/
        return "ca."                + 
                this.parameters.t   + 
                "."                 + 
                this.parameters.v   + 
                "^"                 + 
                this.parameters.k   + 
                ".txt";
    }
    
    /**
    * @return the first row in NIST Format.
    */
    protected String getFirstRow()
    {
        return "" + this.parameters.N;
    }
    
    /**
    * @return null since the NIST format does not specify any special message.
    */
    protected String getSpecialMessage()
    {
        return null;
    }
}
