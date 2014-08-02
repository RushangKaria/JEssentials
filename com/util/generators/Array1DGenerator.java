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
* File : Array1Dgenerator.java
*          Random 1D array generator
*
*    Copyright (C) 2014  Rushang Karia, Shrijal Gandhi
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*/

package com.util.generators;

import com.clrs.Config;
import java.util.Random;
import java.io.*;

/**
* Randomly generate 1-D arrays of different types.
*
*/
public class Array1DGenerator implements Generator_Interface
{
    private int size;
    private int MAX_RANGE;
    private Random generator;   

    /**
    * Default implementation creates an array
    * of a random size with a maximum range of
    * Integer.MAX_VALUE
    */
    public Array1DGenerator()
    {
    this.generator = new Random();

        this.size = generator.nextInt() % MAX_1D_ARRAY_SIZE;
        this.size = this.size < 0 ? -this.size : this.size;   
        this.MAX_RANGE = Integer.MAX_VALUE;
    }

    /**
    * Generator which creates an array of a specified
    * size with a maximum range of Integer.MAX_VALUE
    * @param1 An integer value for the size
    */             
    public Array1DGenerator(int size)
    {
    generator = new Random();
    this.size = size;
    this.MAX_RANGE = Integer.MAX_VALUE;
    }

    /**
    * Generator which creates an array of a specified
    * size with elements with the specified max value
    * @param1 An integer value for the size
    */             
    public Array1DGenerator(int size,int MAX_RANGE)
    {
    generator = new Random();
    this.size = size;
    this.MAX_RANGE = MAX_RANGE;
    }

    /**
    * Generate a double[] of the specified/random size
    * @return double[]
    */
    public double[] generateDouble()
    {
    double array[] = new double[this.size];

        for(int i=0;i<this.size;i++)
        array[i] = generator.nextDouble();
        
    return array;
    }

    /**
    * Generate an int[] of the specified/random size
    * @return int[]
    */
    public int[] generateInt()
    {
    int array[] = new int[this.size];

        for(int i=0;i<this.size;i++)
        array[i] = generator.nextInt() % this.MAX_RANGE;
        
    return array;   
    }

    /**
    * Generate a String[]
    * of the specified size. The String array
    * only constitutes the alphabet [a-z] and [A-Z]
    * @return String[]
    */
    public String[] generateString()
    {
    String array[] = new String[this.size];
    RandomString generator = new RandomString();

        for(int i=0;i<this.size;i++)
        array[i] = generator.generate();
        
    return array;   
    }        

    /**
    * Wrapper to return the size of the array that 
    * is generated.
    */ 
    public int getSize()
    {
    return this.size;
    }

    /**
    * Wrapper to return the max value that any element
    * can take.
    */
    public int getMaxRange()
    {
    return this.MAX_RANGE;
    }

    /**
    * Enables to set the size of the array generated.
    */
    public boolean setSize(int size)
    {
        try
        {
        this.size = size;
        return true;
        }
        catch(Exception e)
        {
        System.out.println("[WARNING] Setting the size failed");
        return false;
        }
    }

    /**
    * Enable to set the max value that an element can take.
    */
    public boolean setMaxRange(int MAX_RANGE)
    {
        try
        {
        this.MAX_RANGE = MAX_RANGE;
        return true;
        }
        catch(Exception e)
        {
        System.out.println("[WARNING] Setting the max value failed");
        return false;
        }
    }
}
