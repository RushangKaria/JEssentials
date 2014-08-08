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
* File : BubbleSort.java
*           Implementation of the Bubble Sort Algorithm
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
* Bubble Sort Implementation,
* for more details about Bubblesort see <a href="http://en.wikipedia.org/wiki/Bubble_sort" >Bubble Sort Wiki</a>
* @see com.clrs.sorting.InsertionSort
* @see com.clrs.sorting.MergeSort
* @see com.clrs.sorting.QuickSort
* @see com.clrs.sorting.HeapSort
*/
public class BubbleSort
{
    /**
    * Sorts an {@code int[]} in ascending order using bubble sort.
    * <p>
    * This method sorts the array in place.
    * @param array takes a integer array as input.
    */
    public static void sort(int array[])
    {
    int temp;

        for(int i=0;i<array.length-1;i++)
            for(int j=i+1;j<array.length;j++)
                if(array[j] < array[i])
                {
                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
                }
    }

    /**
    * Sorts a {@code double[]} in ascending order using bubble sort.
    * <p>
    * This method sorts the array in place.
    * @param array takes a double array as input.
    */
    public static void sort(double array[])
    {
    double temp;

        for(int i=0;i<array.length-1;i++)
            for(int j=i+1;j<array.length;j++)
                if(array[j] < array[i])
                {
                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
                }
    }    

    /**
    * Sorts a {@code byte[]} in ascending order using bubble sort.
    * <p>
    * This method sorts the array in place.
    * @param array takes a byte array as input.
    */
    public static void sort(byte array[])
    {
    byte temp;

        for(int i=0;i<array.length-1;i++)
            for(int j=i+1;j<array.length;j++)
                if(array[j] < array[i])
                {
                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
                }
    }   

    /**
    * Sorts a {@code long[]} in ascending order using bubble sort.
    * <p>
    * This method sorts the array in place.
    * @param array takes a long array as input.
    */
    public static void sort(long array[])
    {
    long temp;

        for(int i=0;i<array.length-1;i++)
            for(int j=i+1;j<array.length;j++)
                if(array[j] < array[i])
                {
                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
                }
    }       
}


