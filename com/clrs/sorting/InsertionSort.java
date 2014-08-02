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
* File : InsertionSort.java
*           Implementation of the Insertion Sort Algorithm
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

package com.clrs.sorting;


/**
* InsertionSort implementation
* See @link http://en.wikipedia.org/wiki/Insertion_sort
*/
public class InsertionSort
{

    /**
    * Sorts an int[] in ascending order using insertion sort.
    * @param1 takes a integer array as input
    * @return void
    */
    public static void sort(int array[])
    {
    int key;
    int j;
    
        for(int i=1;i<array.length;i++)
        {
        key = array[i];
            j = i-1;

                while(j >= 0 && array[j] > key)
                array[j+1] = array[j--];
                    
        array[j+1] = key;
        }
    }

    /**
    * Sorts a double[] in ascending order using insertion sort.
    * @param1 takes a double array as input
    * @return void
    */
    public static void sort(double array[])
    {
    double key;
    int j;
    
        for(int i=1;i<array.length;i++)
        {
        key = array[i];
            j = i-1;

                while(j >= 0 && array[j] > key)
                array[j+1] = array[j--];
                    

        array[j+1] = key;
        }
    }

}
