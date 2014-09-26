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
* File : VerifierWorker.java
*           A worker thread for the CoveringArrayVerifier (protected access)
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

import java.util.HashMap;

import com.coveringarray.CoveringArrayParameters;

/**
* A worker thread for the {@link com.coveringarray.verifier.CoveringArrayVerifier}.
* <p>
* This thread verifies the coverage for a particular column set.
*/
class VerifierWorker extends Thread
{

    /**
    * the covering array parameters that this worker will work on.
    */
    private CoveringArrayParameters parameters;

    /**
    * the column set that this worker will work on.
    */
    private int columns[];

    /**
    * the worker sets this variable to true if the column set is covered else it is set to false.
    */
    private boolean valid;

    /**
    * the interactions are stored as numbers from 0 to v^t-1. 
    * <br>
    * Each column set must contain every number to be fully covered.
    */
    private HashMap<Long,Boolean> interactions;
    
    /**
    * Constructor which is used to initialize the 
    * object with the work. 
    * <p>
    * Since the access to this class is protected, the 
    * parameters used to initialize it <b>MUST</b> be 
    * valid, else undefined behavior may result.
    */
    VerifierWorker(CoveringArrayParameters parameters, int columns[])
    {
        this.parameters = parameters;
        this.columns = columns;
        this.interactions = new HashMap<Long,Boolean>();
        this.valid = false;        
    }

    /**
    * Starts the worker to verify the coverage for the 
    * given column set.
    * <p>
    * The parent thread must wait for all the threads to complete before making any decisions.
    */
    @Override
    public void run()
    {
    long interaction = -1;
    long interactions_seen = 0;

        for(int row=0;row<this.parameters.CA.length;row++)
        {
        interaction = getInteraction(row);

            if(interaction == -1)
            continue;

            if(interactions.containsKey(interaction))
            continue;
            else
            {                            
                interactions.put(interaction,true);
                interactions_seen++;                
            }

           if(interactions_seen == this.parameters.v_power_t)
           break;
        }

    this.valid = interactions_seen == this.parameters.v_power_t;
    }

    /**
    * Returns the interaction as a number between 0 and v^t-1
    * at the specific row for the column set.
    * @param row the row whose interaction is to be mapped.
    * @return a number in [0,v^t) representing the interaction.
    */
    private long getInteraction(int row)
    {
    int symbol;
    String interaction = new String("");

        for(int column=0;column<this.columns.length;column++)
        {
        symbol = this.parameters.CA[row][columns[column]];

            if(symbol == -1)
            return -1;
            else
            interaction += symbol >= 10 ? (char)('A' + (symbol - 10)) : symbol;
        }
        
    return Long.parseLong(interaction,this.parameters.v);
    }

    /**
    * @return true if the coverage was 100% for the column set.
    */
    public boolean isValid()
    {
        return this.valid;
    }


}
