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
* File : UncoveredInteractionsWorker.java
*           Worker thread to count the # of interactions.
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

import java.util.HashMap;
import java.util.HashSet;

/**
* A worker thread which detects the uncovered
* interactions of the coveringarray.
* @see com.coveringarray.analytics.UncoveredInteractions
*/
class UncoveredInteractionsWorker extends Thread
{
    /**
    * The covering array parameters that this worker will act on.
    */   
    private final CoveringArrayParameters parameters;
    
    /**
    * The column pair that this work acts upon.
    */
    private final int column_pair[];
    
    /**
    * The set representing the uncovered interactions.
    */
    private HashSet<Integer> uncovered_interactions;
    
    /**
    * Initialize a worker with a specific workload.
    */
    protected UncoveredInteractionsWorker(  final CoveringArrayParameters parameters,
                                         final int column_pair[]
                                      )
    {
        this.parameters = parameters;
        this.column_pair = column_pair; 

    }
    
    /**
    * Start the computation.
    */
    public void run()
    {
        this.uncovered_interactions = computeUncovered();
    }
    
    /**
    * Initializes the set of uncovered interactions to all v^t interactions.
    * As interactions get covered they are removed from this set.
    * @return a set of all v^t interactions indexed from 0 to v^t-1.
    */
    private HashSet<Integer> initialize()
    {
    HashSet<Integer> uncovered_interactions;
    int v_power_t;
       
        uncovered_interactions = new HashSet<Integer>();
        v_power_t = (int)this.parameters.v_power_t;
        
        for(int i=0;i<v_power_t;i++)
        uncovered_interactions.add(i);
     
    return uncovered_interactions;
    }
    
    /**
    * Computes the uncovered interactions for this column pair.
    * @return returns a set of uncovered interactions, null if all interactions were covered.
    */
    private HashSet<Integer> computeUncovered()
    {
    HashSet<Integer> uncovered_interactions;
    
        uncovered_interactions = initialize();
        
        if(this.parameters.CA == null || this.parameters.N <= 0)
        return uncovered_interactions;
        
        for(int i=0;i<this.parameters.N;i++)
        uncovered_interactions.remove(Interaction.toInt(this.parameters, this.column_pair, i));
    
    return uncovered_interactions.size() == 0 ? null : uncovered_interactions;
    }
    
    /**
    * @return returns the set of uncovered interactions, null if all interactions were covered.
    */
    public HashSet<Integer> getUncovered()
    {
        return this.uncovered_interactions;
    }
    
    /**
    * @return returns the column pair that this worker worked on.
    */
    public int[] getColumnPair()
    {
        return this.column_pair;
    }
}
