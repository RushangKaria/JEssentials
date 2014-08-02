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
* File : Config.java
*          Stores basic configuration for the project
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

package com.clrs;

public class Config
{
    private static final String PROGRAM_NAME = "CLRS";
    private static final String PROGRAM_VERSION = "1.0.0";
    private static final String BOOK_VERSION = "3rd Edition";

    public static       String INPUT_FOLDER = "inputs/";
    public static       String OUTPUT_FOLDER = "outputs/";
    public static       String SAMPLES_FOLDER = "samples/";

    public static final boolean APPEND_OFF = false;
    public static final boolean APPEND_ON  = true;


    /**
    * Get the program information of the form {BOOK-BOOK EDITION - VERSION}
    * @return String
    */
    public static String getProgramInfo()
    {
        return PROGRAM_NAME + "-" + BOOK_VERSION + " v" + PROGRAM_VERSION;  
    }

    /**
    * Get the program name
    * @return String
    */
    public static String getProgramName()
    {
        return PROGRAM_NAME;
    }

    /**
    * Get the book edition
    * @return String
    */
    public static String getBookVersion()
    {
        return BOOK_VERSION;
    }

    /**
    * Get the program version
    * @return String
    */
    public static String getProgramVersion()
    {
        return PROGRAM_VERSION;
    }
}
