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
* File : Permutations.java
*           Generates permutations of elements given by (n P k)
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
import java.util.HashMap;

/**
* Generates permutations of elements
*/
public class Permutations
{
    private int n;
    private int k;
    private long n_P_k;

    private boolean done;
    private int[]   permutations;

    /**
    * Constructor taking parameters for computing
    * (n P k). 
    * <p>
    * n_P_k is always ready once
    * the object is created using this constructor 
    * however the value if too large will always be erroneus
    * @param the total number of values
    * @param the number of permutaions
    */
    public Permutations(int n,int k)
    {
        this.n = n;
        this.k = k;

        this.permutations = new int[this.k];
        reset();

        this.n_P_k = Permutations.nPk(this.n,this.k);
    }

    /**
    * Default constructor takes no parameters.
    * Note that set methods must be used before operating
    * on the methods of this class
    * @see com.math.combinations.Permutations#setN()
    * @see com.math.combinations.Permutations#setK()    
    */
    public Permutations()
    {
        this.n = MathConstants.INVALID;
        this.k = MathConstants.INVALID;
        this.done = true;
    }

    /**
    * This method computes (n P k) for the given
    * parameters.
    * <p>
    * If the value is too large for long a negative
    * number is returned.
    * @param The number of values that can be taken
    * @param The number of permutations required
    * @return the value of (n P k); -1 is returned
    * if the computation is too large to fit in long
    */
    public static long nPk(int n,int k)
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

    return result<=0?MathConstants.INVALID:result;
    }

    /**
    * This method indicates if the current
    * permutation has finished generating.
    * <p>
    * A reset is required to start again else subsequent
    * calls will always return the last permutation.
    * @return If the permutation is still not complete
    */
    public boolean hasNext()
    {
    return !this.done;
    }

    /**
    * This method returns the current permutation and
    * shifts to the next one.
    * <p>
    * To get the next permutation an inefficient approach
    * is followed. We generate all possibilities like
    * in @see com.math.combinations.SigmaAlgebra and then
    * resolve conflicts if there are two elements with the
    * same value.
    * <p>
    * The advantage of this inefficient approach is that it
    * is very simple to implement.
    * @return The current permutation
    */
    public int[] next()
    {
    int i;

    int temp[] = new int[this.k];
    System.arraycopy(this.permutations,0,temp,0,this.k);

        if(done())
        {
        this.done = true;
        }
        else
        {
        getNext();
        
            while(conflicting())
            getNext();
        }
            
    return temp;
    }

    /**
    * This method shifts to the next 
    * permutation which might contain 
    * duplicate values.
    * <p>
    * It mimicks @see com.math.combinations.SigmaAlgebra#next()
    * with the only difference being that the initial array
    * does not contain identical elements
    */
    private void getNext()
    {
    int i;
                 for(i=this.k-1;i>=0;i--)
                 if(this.permutations[i] != this.n - 1)
                 break;
    
                    this.permutations[i]+=1;
                    i++;
        
                    for(;i<this.k;i++)
                    this.permutations[i] = 0;
    }

    /**
    * This method checks if the given permutation is conflicting.
    * <p>
    * A permutation is called conflicting if there are two identical
    * values in the permutation but the sample set is not multi-set.
    * <p>
    * Collision resolution is done by changing the value for the conflicting
    * value to a valid value. Collision resolution is prioritized towards the end
    * of the permutation to preserve the order.
    * @return whether the given permutation is conflicting or no
    */
    private boolean conflicting()
    {
    HashMap<Integer,Boolean> conflict_map = new HashMap<Integer,Boolean>();
    
        for(int i=0;i<this.k;i++)
        if(conflict_map.containsKey(permutations[i]))
        return true;
        else
        conflict_map.put(permutations[i],true);

    return false;
    }

    /**
    * Method to test if we are at the last
    * permutation.
    * <p>
    * The last permutation of any (n P k) is given by
    * n-1,n-1,n-1, .... k times.
    * @return true if the last permuation is reached
    */
    private boolean done()
    {
        for(int i=0;i<this.k;i++)
        if(permutations[i] != this.n - 1 - i)
        return false;

    return true;
    }

    /**
    * Computes the updated value of (n P k).
    * <p>
    * This method should be used if (n P k) is 
    * required after calls to @see com.math.combinations.Permutations#setN()
    * or @see com.math.combinations.Permutations#setK()
    */
    public void update()
    {
        this.n_P_k = Permutations.nPk(this.n,this.k);
    }

    
    /**
    * This method resets the permutation to 
    * its initial state {0,1,2,.....k-1}.
    * After this method the permutation can
    * be generated again by calls to 
    * @see com.math.combinations.Permutations#next()
    */
    public void reset()
    {
        for(int i=0;i<this.k;i++)
        this.permutations[i] = i;

    this.done = false;
    }

    /**
    * This method sets the total number
    * of values.
    * <p>
    * Note: This does not update (n P k). That must be 
    * done if required by calling @see com.math.combinations.Permutations#update()
    * Also, to prevent inaccurate states it is advised to call @see com.math.combinations.Permutations#reset()
    * See also @see com.math.combinations.Permutations#getN()    
    * @param The total number of values
    */
    public void setN(int n)
    {
        this.n = n;
    }

    /**
    * This method sets the total number
    * of permutations.
    * <p>
    * Note: This does not update (n P k). That must be 
    * done if required by calling @see com.math.combinations.Permutations#update()
    * <p>
    * Caution: An update to k brings the permutations string back to start at 0,0,0....k with 
    * all previous progress lost.
    * <p>
    * See also @see com.math.combinations.Permutations#getN()    
    * @param The total number of permutations
    */
    public void setK(int k)
    {
        this.k = k;

        this.permutations = new int[this.k];
        reset();
    }

    /**
    * This method is used to return the value of
    * (n choose k) for the current object.
    *
    * @return the value of (n choose k) for the current object
    */
    public long getnPk()
    {
        return this.n_P_k;
    }

    /**
    * This method returns the total number of
    * values that this object acts over.
    * See also @see com.math.combinations.Permutations#setN()
    * @return The value of n
    */
    public int getN()
    {
        return this.n;
    }

    /**
    * This method returns the total number of
    * permutations that this object acts over.
    * See also @see com.math.combinations.Permutations#setK()    
    * @return The value of k
    */
    public int getK()
    {
        return this.k;
    }
    
}
