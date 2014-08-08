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
* File : CoveringArrayParameters.java
*           A class capturing parameters of a CA
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

package com.coveringarray;

import com.math.combinations.Combinations;
import com.math.combinations.SigmaAlgebra;

/**
* This class captures the various parameters
* of a Covering Array.
*/
public class CoveringArrayParameters
{
    /**
    * The number of the rows in the Covering Array.
    */ 
    public int N;

    /**
    * The strength of the Covering Array.
    */
    public byte t;

    /**
    * The number of columns of the Covering Array.
    */
    public int k;

    /**
    * The number of values each symbol of the Covering Array can take.
    */
    public byte v;

    /**
    * The Covering Array.
    */ 
    public byte CA[][];

    /**
    * The total number of t-tuples possible.
    */    
    public long k_choose_t;

    /**
    * The total permutations of interactions.
    */
    public long v_power_t;

    /**
    * The number of unique interactions that the Covering Array covers.
    */
    public long total_interactions;

    /**
    * Default constructor which initializes all the 
    * parameters.
    * <p>
    * The value of total_interactions are -1 and need to 
    * be updated after the values are set by a call to #compute()
    */
    public CoveringArrayParameters()
    {
        this.N = -1;
        this.t = -1;
        this.k = -1;
        this.v = -1;

        this.k_choose_t = -1;
        this.v_power_t  = -1;
        
        this.total_interactions = -1;
    }

    /**
    * Parameterized construct which takes all the parameters.
    * Calling this constructor automatically makes the total
    * interactions available.
    */
    public CoveringArrayParameters(int N,byte t,int k,byte v)
    {
        this.N = N;
        this.t = t;
        this.k = k;
        this.v = v;

        compute();
    }

    /**
    * Use this method to compute the total interactions
    * that are possible.
    * <p>
    * This method is not required to be called if the 
    * object was created by call new #CoveringArrayParameters(int,byte,int,byte);
    */
    public void compute()
    {   
        this.k_choose_t = Combinations.nCk(this.k,this.t);
        this.v_power_t  = (long)Math.pow(this.v,this.t);

        this.total_interactions = this.k_choose_t * this.v_power_t;
    }

    /**
    * Call this method to display all the information about the 
    * parameters of the covering array.
    */
    public void display()
    {
    System.out.println("------------------------------------------------");
    
        System.out.println("N        -> " + this.N);  
        System.out.println("t        -> " + this.t);
        System.out.println("k        -> " + this.k);
        System.out.println("v        -> " + this.v);
        System.out.println("v^t      -> " + this.v_power_t);
        System.out.println("(k C t)  -> " + this.k_choose_t);
        System.out.println("Total    -> " + this.total_interactions);   

    System.out.println("------------------------------------------------");
    }
    
}
