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
* File : QucikSort.java
*           Implementation of the Quick Sort Algorithm
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

package com.coveringarray.verifier;

import com.coveringarray.CoveringArrayParameters;
import com.coveringarray.parsers.CoveringArrayParser;

/**
* A verifier for Covering Arrays.
* The program is multi-threaded for fast performance
*/
public class CoveringArrayVerifier
{
    /**
    * Holds the parameters of the Covering Array for verification.
    */
    private CoveringArrayParameters parameters;

    /**
    * Holds the maximum number of threads that can be used. 
    * Normally this is twice the number of processors.
    * Since the number of processors available to the Java Virtual Machine can
    * change on every invocation, this variable is polled for every object.
    * As such, time required might be varied, although this is rarely the case.
    */
    private int NO_OF_THREADS;

    /**
    * Holds the Covering Array File
    */
    private File CA_SOURCE_FILE;

    /**
    * Constructor which takes the covering array file and parses
    * it.
    * <p>
    * Any error during parsing will cause the verification method to fail.
    */
    public CoveringArrayVerifier(File CA_SOURCE_FILE)
    {
        this.CA_SOURCE_FILE = CA_SOURCE_FILE;
    }    

    /**
    * Verifies if the covering array has 100% coverage - all interactions covered atleast once.
    * @return true if the CA has 100% coverage, false if some interactions are missing. {@link #getMissing()} can be used to find out which.
    */
    public boolean verify()
    {

    return false;
    }

    /**
    * Gets the interactions which are missing of the covering.
    */
    public void getMissing()
    {

    }
    
}


