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
* File : InteractionCounter.java
*           Counts the # of private interactions covered by each row.
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

package com.coveringarray.analytics;

import com.coveringarray.CoveringArrayParameters;

import com.math.combinations.Combinations;
import com.util.printers.ProgressBar;
import com.util.generators.Array1DGenerator;

/**
* Analyzes the coverage per row.
* It is beneficial to see which rows add
* the greatest redundancy
*/
public class InteractionCounter
{
    /**
    * The covering array parameters to analyze.
    */
    private final CoveringArrayParameters parameters;
    
    /**
    * The number of processors.
    */
    private final int NO_OF_PROCESSORS = Runtime.getRuntime().availableProcessors();
    
    /**
    * The granularity of the method. 
    * <p>
    * NOTE: It is important to set the granularity to an optimized
    * value for your architecture.
    * #NO_OF_THREADS - #NO_OF_PROCESSORS * #GRANULARITY 
    * too many threads can cause a loss of performance. This is well documented
    * on the Internet.
    */
    private final int GRANULARITY;
    
    /**
    * The # of threads that are utilized.
    * <p>
    * # of threads = #NO_OF_PROCESSORS * #GRANULARITY 
    */
    private int NO_OF_THREADS;
    
    /**
    * Creates an analytic object that can
    * be used to determine coverage of rows.
    * <p>
    * A default granularity of 2 is used here.
    * @param parameters the covering array parameters to analyze.
    */
    public InteractionCounter(final CoveringArrayParameters parameters)
    {
        this.parameters = parameters;
        this.GRANULARITY = 2;
        this.NO_OF_THREADS = this.NO_OF_PROCESSORS * this.GRANULARITY;
    }
    
    /**
    * Creates an analytic object that can be used
    * to determine coverage of the rows. 
    * <p>
    * The granularity of the program can be specified.
    * If you have a larger # of cores then it is better to use
    * a large value for granularity, however a very large value
    * can actually degrade performance.
    * @param parameters the covering array parameters to analyze.
    * @param GRANULARITY the granularity of the program.
    */
    public InteractionCounter(final CoveringArrayParameters parameters, int GRANULARITY)
    {
        this.parameters = parameters;
        this.GRANULARITY = GRANULARITY;
        this.NO_OF_THREADS = this.NO_OF_PROCESSORS;
    }
    
    /**
    * Counts the # of interactions covered by every row of the array.
    * This is useful for even incomplete covering arrays.
    * @param rows the pair of rows whose counts must be found, null for all rows.
    * @return counts of the # of interactions covered by each row.
    */
    public long[] getCovered(final int rows[])
    {
    long coverage[];
    boolean private_interaction[];
    long count;
    int threads;
    Combinations column_pairs;
    ProgressBar progressbar;
    InteractionCounterWorker workers[];
    
        if(rows == null)    coverage = new long[this.parameters.N];
        else                coverage = new long[1];
        
        column_pairs = new Combinations(this.parameters.k, this.parameters.t);
        progressbar = new ProgressBar(this.parameters.k_choose_t);
        workers = new InteractionCounterWorker[this.NO_OF_THREADS];
        
        count = 0;
        threads = 0;
        
            while(column_pairs.hasNext())
            {
                while(threads < this.NO_OF_THREADS && column_pairs.hasNext())
                {
                workers[threads] = new InteractionCounterWorker(  
                                                                    this.parameters, 
                                                                    column_pairs.next(),
                                                                    rows,
                                                                    false
                                                                );
                workers[threads++].start();
                }
            
            count += threads;
            
                while(threads > 0)
                {   
                    try
                    {
                    workers[--threads].join();
                    }
                    catch(Exception e)
                    {
                    System.out.println("[FATAL] Interrupted -> " + e);
                    e.printStackTrace();
                    System.exit(0);
                    }
                    
                private_interaction = workers[threads].getPrivate();
                
                    for(int i=0;i<private_interaction.length;i++)
                        if(private_interaction[i])
                        coverage[i]++;
                }    
                
            progressbar.set(count);
            }
            
    return coverage;
    }
}
