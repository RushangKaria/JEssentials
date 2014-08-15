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

import java.io.File;
import java.util.HashMap;

import com.util.printers.ProgressBar;

import com.coveringarray.CoveringArrayParameters;

import com.math.combinations.Combinations;

import com.util.printers.ArrayPrinter;

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
    * Constructor which takes the covering array file and parses
    * it.
    * <p>
    * Any error during parsing will cause the verification method to fail.
    * @param CA_SOURCE_FILE the covering array file
    */
    public CoveringArrayVerifier(CoveringArrayParameters parameters)
    {
        this.parameters = parameters;
        this.NO_OF_THREADS = Runtime.getRuntime().availableProcessors();          
    }    

    /**
    * Verifies if the covering array has 100% coverage - all interactions covered atleast once.
    * <p>
    * This verifier works on a very fundamental and reasonable assumption. <br>
    * A position is called a dont-care position only if all interactions representing that symbol already occur in the array.
    * @return true if the CA has 100% coverage, false if some interactions are missing. {@link #getMissing()} can be used to find out which.
    */
    public boolean verify()throws InterruptedException
    {
    ProgressBar progressbar;
    Combinations column_pairs;
    VerifierWorker workers[];
    int threads = 0;
    int join_count = 0;
    long interactions_done = 0;

    progressbar = new ProgressBar(this.parameters.k_choose_t);
    workers = new VerifierWorker[this.NO_OF_THREADS];
    column_pairs = new Combinations(this.parameters.k,this.parameters.t);
              
        while(column_pairs.hasNext())
        {
        threads = 0;
        join_count = 0;   
        System.gc();
        
            while(threads < this.NO_OF_THREADS && column_pairs.hasNext())
            {
            workers[threads] = new VerifierWorker(this.parameters,column_pairs.next());
            workers[threads++].start();
            }
            
            while(join_count < threads)
            workers[join_count++].join();

        threads = 0;

            while(threads < join_count)
            if(!workers[threads++].isValid())
            return false;

        interactions_done += join_count;
        progressbar.set(interactions_done);
        }//end of while
    
    return true;
    }//end of method

    /**
    * Gets the interactions which are missing of the covering.
    * <p>
    * To control the memory usage of the application, only the first 
    * missed pair is kept. The others are discarded.
    * <p>
    * To retrieve all missing interactions see {@link #getAllMissing()}.
    * @see #getAllMissing()
    */
    public void getMissing()
    {

    }
    
}


