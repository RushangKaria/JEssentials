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
* File : Combinations.java
*           Generates combinations of elements given by (n C K)
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

package com.math.combinations;

import com.math.MathConstants;

/**
* Generates combinations of elements
* The order is always sorted in ascending order
*/
public class Combinations
{
    private int n;
    private int k;
    private long n_C_k;

    private boolean done;
    private int[]   combinations;

    /**
    * Constructor taking parameters for computing
    * (n C K). 
    * <p>
    * (n C K) is always ready once
    * the object is created using this constructor 
    * however the value if too large will always be erroneus
    * @param the total number of values
    * @param the number of combinations
    */
    public Combinations(int n,int k)
    {
        this.n = n;
        this.k = k;

        this.combinations = new int[this.k];
        reset();

        this.n_C_k = Combinations.nCk(this.n,this.k);
    }

    /**
    * Default constructor takes no parameters.
    * Note that set methods must be used before operating
    * on the methods of this class
    * @see com.math.combinations.Combinations#setN()
    * @see com.math.combinations.Combinations#setK()    
    */
    public Combinations()
    {
        this.n = MathConstants.INVALID;
        this.k = MathConstants.INVALID;
        this.done = true;
    }

    /**
    * This method computes ((n C K)) for the given
    * parameters.
    * <p>
    * If the value is too large for long a negative
    * number is returned.
    * @param The number of values that can be taken
    * @param The number of combinations required
    * @return the value of (n C K); -1 is returned
    * if the computation is too large to fit in long
    */
    public static long nCk(int n,int k)
    {
        if(k > n || k < 0 || n < 0 )
        return MathConstants.INVALID;
        else if( k == 0)
        return MathConstants.UNITY;
        else if (n == 0)
        return MathConstants.ZERO;
    
    long result = 1;

         for(int i=n;i>n-k;i--)
         result*=i;

         for(int i=0;i<k;i++)
         result/=(k-i);

    return result<=0?MathConstants.INVALID:result;
    }

    /**
    * This method indicates if the current
    * combination has finished generating.
    * <p>
    * A reset is required to start again else subsequent
    * calls will always return the last combination.
    * @return If the combination is still not complete
    */
    public boolean hasNext()
    {
    return !this.done;
    }

    /**
    * This method returns the current combination and
    * shifts to the next one,
    * @return The current combination
    */
    public int[] next()
    {
    int i;

    int temp[] = new int[this.k];
    System.arraycopy(this.combinations,0,temp,0,this.k);

        if(this.combinations[0] == this.n-1-(this.k-1))
        {
        this.done = true;
        }
        else
        {
            for(i=this.k-1;i>=0;i--)
            if(this.combinations[i] != this.n - 1 - (this.k-1 - i))
            break;
    
            this.combinations[i]+=1;
            i++;
        
            for(;i<this.k;i++)
            this.combinations[i] = this.combinations[i-1] + 1;
        }
            
    return temp;
    }

    /**
    * Computes the updated value of (n C K).
    * <p>
    * This method should be used if (n C K) is 
    * required after calls to @see com.math.combinations.Combinations#setN()
    * or @see com.math.combinations.Combinations#setK()
    */
    public void update()
    {
        this.n_C_k = Combinations.nCk(this.n,this.k);
    }

    
    /**
    * This method resets the combination to 
    * its initial state {0,1,2,.....k-1}.
    * After this method the combination can
    * be generated again by calls to 
    * @see com.math.combinations.Combination#next()
    */
    public void reset()
    {
        for(int i=0;i<this.k;i++)
        this.combinations[i] = i;

    this.done = false;
    }

    /**
    * This method sets the total number
    * of values.
    * <p>
    * Note: This does not update (n C K). That must be 
    * done if required by calling @see com.math.combinations.Combinations#update()
    * Also, to prevent inaccurate states it is advised to call @see com.math.combinations.Combinations#reset()
    * See also @see com.math.combinations.Combinations#getN()    
    * @param The total number of values
    */
    public void setN(int n)
    {
        this.n = n;
    }

    /**
    * This method sets the total number
    * of combinations.
    * <p>
    * Note: This does not update (n C K). That must be 
    * done if required by calling @see com.math.combinations.Combinations#update()
    * <p>
    * Caution: An update to k brings the combinations string back to start at 0,0,0....k with 
    * all previous progress lost.
    * <p>
    * See also @see com.math.combinations.Combinations#getN()    
    * @param The total number of combinations
    */
    public void setK(int k)
    {
        this.k = k;

        this.combinations = new int[this.k];
        reset();
    }

    /**
    * This method is used to return the value of
    * (n C K) for the current object.
    *
    * @return the value of (n C K) for the current object
    */
    public long getnCk()
    {
        return this.n_C_k;
    }

    /**
    * This method returns the total number of
    * values that this object acts over.
    * See also @see com.math.combinations.Combinations#setN()
    * @return The value of n
    */
    public int getN()
    {
        return this.n;
    }

    /**
    * This method returns the total number of
    * combinations that this object acts over.
    * See also @see com.math.combinations.Combinations#setK()    
    * @return The value of k
    */
    public int getK()
    {
        return this.k;
    }
    
}
