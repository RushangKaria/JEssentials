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
* File : UncoveredInteractions.java
*           Analyzes the # of uncovered interactions of the covering array.
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
import com.coveringarray.Interaction;

import com.math.combinations.Combinations;
import com.util.printers.ProgressBar;
import com.util.generators.Array1DGenerator;

import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Arrays;

/**
* Analyzes the # of uncovered interactions.
*/
public class UncoveredInteractions
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
    public UncoveredInteractions(final CoveringArrayParameters parameters)
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
    public UncoveredInteractions(final CoveringArrayParameters parameters, int GRANULARITY)
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
    public HashMap<Long, HashSet<Integer>> getUncovered()
    {
    long count;
    int threads;
    int column_pair[];
    long column_pair_value;
    Combinations column_pairs;
    ProgressBar progressbar;
    HashMap<Long, HashSet<Integer>> uncovered_interactions;
    HashSet<Integer> interactions;
    UncoveredInteractionsWorker workers[];
        
        column_pairs = new Combinations(this.parameters.k, this.parameters.t);
        progressbar = new ProgressBar(this.parameters.k_choose_t);
        uncovered_interactions = new HashMap<Long, HashSet<Integer>>();
        workers = new UncoveredInteractionsWorker[this.NO_OF_THREADS];
        
        count = 0;
        threads = 0;
        
            while(column_pairs.hasNext())
            {
                while(threads < this.NO_OF_THREADS && column_pairs.hasNext())
                {
                workers[threads] = new UncoveredInteractionsWorker( this.parameters,
                                                                    column_pairs.next()
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
                    
                interactions = workers[threads].getUncovered();
                    
                    if(interactions != null)
                    {
                    column_pair = workers[threads].getColumnPair();                
                    column_pair_value = Interaction.getColumnValue(this.parameters.k,  
                                                                    column_pair);
                                 
                    uncovered_interactions.put(column_pair_value, interactions);
                    }
                }    
                
            progressbar.set(count);
            }
            
    return uncovered_interactions.size() == 0 ? null : uncovered_interactions;
    }
    
    /**
    * Displays the uncovered interactions in a neat format.
    */
    public void displayUncovered(HashMap<Long, HashSet<Integer>> uncovered_interactions)
    {
    //Tweak these values according to your console settings.
    final int MAX_COLUMN_WIDTH = 30;
    final int MAX_VALUES_PER_LINE = 9;
    final String column_formatting = "| %" + MAX_COLUMN_WIDTH + "s | ";        
    
    Iterator<Long> column_iterator;
    HashSet<Integer> interactions;    
    Iterator<Integer> set_iterator;
    long column_pair_value;
    int column_pair[];
    long total_uncovered;
    int print_count;
    
        if(uncovered_interactions == null)
        {
        System.out.println("100% Coverage");
        return;
        }
        
    column_iterator = uncovered_interactions.keySet().iterator();
    total_uncovered  = 0;
    
        while(column_iterator.hasNext())
        {
        column_pair_value = column_iterator.next();
        column_pair = Interaction.decodeColumnValue( column_pair_value, 
                                                     this.parameters.k,
                                                     this.parameters.t
                                                   );
                                              
        System.out.print(String.format(column_formatting, Arrays.toString(column_pair)));
        
        interactions = uncovered_interactions.get(column_pair_value);
        set_iterator = interactions.iterator();
        total_uncovered += interactions.size();
        print_count = 0;
        
            while(set_iterator.hasNext())
            {            
            System.out.print(set_iterator.next() + " ");
              
                if(print_count == MAX_VALUES_PER_LINE)
                {
                print_count = 0;
                System.out.println();
                System.out.print(String.format(column_formatting,""));
                }
                else
                print_count++;
            }
        
        System.out.println();    
        System.out.println("-----------------------------------------------------------"); 
      }
    
    System.out.print("Total # of uncovered interactions = " + total_uncovered);  
    System.out.print(" [ ");
    System.out.print(String.format("%.2f%%",(total_uncovered*100.0)/this.parameters.total_interactions));
    System.out.println(" ] ");
    }
}
