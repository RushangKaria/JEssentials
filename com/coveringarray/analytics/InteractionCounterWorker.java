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
* File : InteractionCounterWorker.java
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
* A worker thread who counts the # of 
* interactions for a specific column
* pair.
* @see com.coveringarray.analytics.InteractionCounter
*/
class InteractionCounterWorker extends Thread
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
    * The rows on which the search must be performed.
    */
    private final int rows[];
    
    /**
    * The mode of marking the common interaction.
    * If set to true this means that the first time the interaction
    * appears is preserved. So at the end all other rows with the same
    * interaction will be not private but the first occurence will 
    * surely be private. This is very useful for marking dont cares since
    * we can mark the others as non-private and keep the one as private.
    * <p>
    * If set to false then any common interaction will not be consider
    * private for all the rows who have it.
    */
    private boolean mode;
    
    /**
    * The vector which indicates whether the interaction at the row is private or no.
    */
    private boolean private_interactions[];
    
    /**
    * Initialize a worker with a specific workload.
    */
    protected InteractionCounterWorker(  final CoveringArrayParameters parameters,
                                         final int column_pair[], 
                                         final int rows[],
                                         boolean mode
                                      )
    {
        this.parameters = parameters;
        this.column_pair = column_pair; 
        this.rows = rows;
        this.mode = mode;
    }
    
    /**
    * Start the computation.
    */
    public void run()
    {
        if(this.rows == null)    this.private_interactions = get();
        else                     this.private_interactions = get(this.rows);;
        
    }
    
    /**
    * Computes whether each row provides a private interaction
    * to the given column_pair or no.
    * If yes, then it contributes/covers exactly one interaction
    * not covered by any other row for that column pair.
    * @return a boolean vector whether these rows contribute any interaction for this column pair.
    */
    private boolean[] get()
    {
    HashMap<Integer, Integer> interaction_map;
    boolean private_interaction[];
    int interaction;

        interaction_map = new HashMap<Integer, Integer>();
        private_interaction = new boolean[this.parameters.N];
        
        for(int i=0;i<this.parameters.N;i++)
        {
        interaction = Interaction.toInt(this.parameters, this.column_pair, i);
        
            if(interaction < 0)
            private_interaction[i] = false;
       else if(interaction_map.containsKey(interaction))
            {
            private_interaction[i] = false;
            private_interaction[interaction_map.get(interaction)] = mode;
            }
       else
            {
            private_interaction[i] = true;
            interaction_map.put(interaction, i);
            }
        }
    
    return private_interaction;
    }

    /**
    * Computes whether the given rows provide a private interaction
    * to the given column_pair or no.
    * If yes, then they contribute/cover exactly one interaction
    * not covered by any other row for that column pair.
    * @param rows the rows whose count must be found out.
    * @return a boolean vector whether these rows contribute any interaction for this column pair.
    */
    private boolean[] get(final int rows[])
    {
    boolean private_interaction[];
    int interaction;
    HashSet<Integer> interaction_set;
    HashSet<Integer> row_set;
    
        private_interaction = new boolean[1];
        private_interaction[0] = false;
        interaction_set = new HashSet<Integer>();
        row_set = new HashSet<Integer>();
        
        interaction = Interaction.toInt(this.parameters, this.column_pair, rows[0]);
        interaction_set.add(interaction);
        row_set.add(rows[0]);
        
        for(int i=1;i<rows.length;i++)
        {
        interaction = Interaction.toInt(this.parameters, this.column_pair, rows[i]);
        
            if(!interaction_set.contains(interaction))
            return private_interaction;
            
        row_set.add(rows[i]); 
        }
        
        for(int i=0;i<this.parameters.N;i++)
        {
            if(row_set.contains(i))
            continue;
            
        interaction = Interaction.toInt(this.parameters, this.column_pair, i);
        
            if(interaction_set.contains(interaction))
            return private_interaction;

        }
    
    private_interaction[0] = true;
    return private_interaction;
    }
    
    /**
    * @return returns the counts that were found.
    */
    public boolean[] getPrivate()
    {
        return this.private_interactions;
    }
    
    /**
    * @return returns the column pair that this worker worked on.
    */
    public int[] getColumnPair()
    {
        return this.column_pair;
    }
}
