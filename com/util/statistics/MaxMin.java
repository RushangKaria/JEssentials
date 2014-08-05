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
* File : MaxMin.java
*           Utility to get maximum/minimum values of arrays
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

package com.util.statistics;

public class MaxMin
{
    /**
    * This method is used to compute the maximum value of an array
    * @param Array whose maximum value is to be found
    * @return The maximum value of this array
    */
    public static int getMaxValue(int array[])
    {
    int max = Integer.MIN_VALUE;

        for(int i=0;i<array.length;i++)
            if(array[i] > max)
            max = array[i];

    return max;
    }

    /**
    * This method is used to get the index of the maximum value of the array
    * @param Array whose maximum value is to be found
    * @return The index where the maximum value occurs
    */
    public static int getMaxIndex(int array[])
    {
    int max = Integer.MIN_VALUE;
    int max_index = -1;

        for(int i=0;i<array.length;i++)
            if(array[i] > max)
            {
            max = array[i];
            max_index = i;
            }
            
    return max_index;            
    }

    /**
    * This method returns the max value and the index where the max value occurs of an array
    * @param Array whose maximum is to be found
    * @return The maximum value and index where it occurs as an int[] where [0] = max value [1] = index
    */
    public static int[] getMax(int array[])
    {
    int max = Integer.MIN_VALUE;
    int max_index = -1;

        for(int i=0;i<array.length;i++)
            if(array[i] > max)
            {
            max = array[i];
            max_index = i;
            }
            
    return new int[]{max,max_index};   
    }

    /**
    * This method is used to compute the minimum value of an array
    * @param Array whose minimum value is to be found
    * @return The minimum value of this array
    */
    public static int getMinValue(int array[])
    {
    int min = Integer.MAX_VALUE;

        for(int i=0;i<array.length;i++)
            if(array[i] < min)
            min = array[i];

    return min;
    }

    /**
    * This method is used to get the index of the minimum value of the array
    * @param Array whose minimum value is to be found
    * @return The index where the minimum value occurs
    */
    public static int getMinIndex(int array[])
    {
    int min = Integer.MAX_VALUE;
    int min_index = -1;

        for(int i=0;i<array.length;i++)
            if(array[i] < min)
            {
            min = array[i];
            min_index = i;
            }
            
    return min_index;            
    }

    /**
    * This method returns the min value and the index where the min value occurs of an array
    * @param Array whose minimum is to be found
    * @return The minimum value and index where it occurs as an int[] where [0] = min value [1] = index
    */
    public static int[] getMin(int array[])
    {
    int min = Integer.MAX_VALUE;
    int min_index = -1;

        for(int i=0;i<array.length;i++)
            if(array[i] < min)
            {
            min = array[i];
            min_index = i;
            }
            
    return new int[]{min,min_index};   
    }    
}
