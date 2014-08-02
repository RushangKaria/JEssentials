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
* File : SigmaAlgebra.java
*           Generates all possibilities of elements dictated by sigma algebra.
*           If there is a set of elements which can take v values and are to appear as a group of t
*           then the total # of possibilties are v^t. A simple example is coin toss.
*
*           For another application, an example would be exhaustive Covering Arrays.
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
* Generates all possibilities of
* elements.
*/
public class SigmaAlgebra
{
    private int v;
    private int t;
    private long v_power_t;

    private boolean done;
    private int[]   possibilities;

    /**
    * Constructor taking parameters for computing
    * all the possibilities.
    * <p>
    * v_power_t is always ready once
    * the object is created using this constructor 
    * however the value if too large will always be erroneus
    * @param the total number of values an event can take
    * @param the number of events that will occur
    */
    public SigmaAlgebra(int v,int t)
    {
        this.v = v;
        this.t = t;

        this.possibilities = new int[this.t];
        reset();

        this.v_power_t = (long)Math.pow(this.v,this.t);
    }

    /**
    * Default constructor takes no parameters.
    * Note that set methods must be used before operating
    * on the methods of this class
    * @see com.math.combinations.SigmaAlgebra#setV()
    * @see com.math.combinations.SigmaAlgebra#setT()    
    */
    public SigmaAlgebra()
    {
        this.v = MathConstants.INVALID;
        this.t = MathConstants.INVALID;
        this.done = true;
    }

    /**
    * This method indicates if the all the
    * possibilites have finished generating.
    * <p>
    * A reset is required to start again else subsequent
    * calls will always return the last possibility.
    * @return If the permutation is still not complete
    */
    public boolean hasNext()
    {
    return !this.done;
    }

    /**
    * This method returns the current possibility and
    * shifts to the next one.
    * @return The current possibility
    */
    public int[] next()
    {
    int i;

    int temp[] = new int[this.t];
    System.arraycopy(this.possibilities,0,temp,0,this.t);

        if(done())
        {
        this.done = true;
        }
        else
        {
            for(i=this.t-1;i>=0;i--)
            if(this.possibilities[i] != this.v - 1)
            break;
    
            this.possibilities[i]+=1;
            i++;
        
            for(;i<this.t;i++)
            this.possibilities[i] = 0;
        }
            
    return temp;
    }

    /**
    * Method to test if we are at the last
    * possibility.
    * <p>
    * The last possibility of any v^t is given by
    * n-1,n-1,n-1, .... k times.
    * @return true if the last possibility is reached
    */
    private boolean done()
    {
        for(int i=0;i<this.t;i++)
        if(possibilities[i] != this.v - 1)
        return false;

    return true;
    }

    /**
    * Computes the updated value of v_power_t.
    * <p>
    * This method should be used if v_power_t is 
    * required after calls to @see com.math.combinations.SigmaAlgebra#setV()
    * or @see com.math.combinations.SigmaAlgebra#setT()
    */
    public void update()
    {
        this.v_power_t = (long)Math.pow(this.v,this.t);
    }

    
    /**
    * This method resets the program to 
    * its initial state {0,0,0 .... k-1 times}.
    * After this method the possibilities can
    * be generated again by calls to 
    * @see com.math.combinations.SigmaAlgebra#next()
    */
    public void reset()
    {
        for(int i=0;i<this.t;i++)
        this.possibilities[i] = 0;

    this.done = false;
    }

    /**
    * This method sets the total number
    * of values each element can take.
    * <p>
    * Note: This does not update v_power_t. That must be 
    * done if required by calling @see com.math.combinations.SigmaAlgebra#update()
    * Also, to prevent inaccurate states it is advised to call @see com.math.combinations.SigmaAlgebra#reset()
    * See also @see com.math.combinations.SigmaAlgebra#getN()    
    * @param The total number of values
    */
    public void setV(int v)
    {
        this.v = v;
    }

    /**
    * This method sets the total number
    * of events that the elements occur in.
    * <p>
    * Note: This does not update v_power_t. That must be 
    * done if required by calling @see com.math.combinations.SigmaAlgebra#update()
    * <p>
    * Caution: An update to k brings the program back to its initial state at 0,0,0....k with 
    * all previous progress lost.
    * <p>
    * See also @see com.math.combinations.SigmaAlgebra#getV()    
    * @param The total number of events that the elements may appear together in
    */
    public void setT(int t)
    {
        this.t = t;

        this.possibilities = new int[this.t];
        reset();
    }

    /**
    * This method is used to return the value of
    * v^t for the current object.
    *
    * @return the value of v_power_t for the current object
    */
    public long getTotal()
    {
        return this.v_power_t;
    }

    /**
    * This method returns the total number of
    * values that the elements of this object can take.
    * See also @see com.math.combinations.SigmaAlgebra#setV()
    * @return The number of values that the elements may take
    */
    public int getV()
    {
        return this.v;
    }

    /**
    * This method returns the total number of
    * events that the elements take part in
    * See also @see com.math.combinations.SigmaAlgebra#setT()    
    * @return The number of events that the elements participate in
    */
    public int getT()
    {
        return this.t;
    }
    
}
