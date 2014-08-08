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
*          Stores basic configuration for the CLRS book.
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

/**
* This class holds common information about the program.
* <p>
* It provides uniform directories for input and output to the
* programs.
*/
public class Config
{
    /**
    * Records the name of the book.
    */
    private static final String PROGRAM_NAME = "CLRS";

    /**
    * The program version typically following Linux Kernel versioning.
    */
    private static final String PROGRAM_VERSION = "1.0.0";

    /**
    * The edition of the book which is supported.
    */
    private static final String BOOK_VERSION = "3rd Edition";

    /**
    * A directory from where inputs can be received.
    * Any change to this variable affects the whole program.
    */
    public static       String INPUT_FOLDER = "inputs/";

    /**
    * A directory where the outputs can be stored.
    * Any change to this variable affects the whole program.
    */
    public static       String OUTPUT_FOLDER = "outputs/";

    /**
    * This variable can be used to turn off append modes for files. 
    * <p>
    * When this mode is set any existing files will be deleted and recreated.
    */
    public static final boolean APPEND_OFF = false;

    /**

    * This variable can be used to turn on append modes for files. 
    * <p>
    * When this mode is set any existing files will be appended to.
    */
    public static final boolean APPEND_ON  = true;


    /**
    * Gets the program information of the form {@code <book>-<edition>-<version>}.
    * @return The program info which contains the book, edition and version.
    */
    public static String getProgramInfo()
    {
        return PROGRAM_NAME + "-" + BOOK_VERSION + " v" + PROGRAM_VERSION;  
    }

    /**
    * Gets the program name for the current program.
    * @return The program name. 
    */
    public static String getProgramName()
    {
        return PROGRAM_NAME;
    }

    /**
    * Gets the edition of the book which the current program supports.
    * @return The edition of the book which is supported.
    */
    public static String getBookVersion()
    {
        return BOOK_VERSION;
    }

    /**
    * Gets the program version where the versioning system matches that followed by the Linux Kernel.
    * @return The current program version.
    */
    public static String getProgramVersion()
    {
        return PROGRAM_VERSION;
    }
}
