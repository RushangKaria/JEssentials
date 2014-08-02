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
* File : QucikSort.java
*           Implementation of the Quick Sort Algorithm
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
* QuickSort implementation
* See @link http://en.wikipedia.org/wiki/Quick_sort
*/
public class QuickSort
{
    /**
    * Sorts an int[] in ascending order using merge sort.
    * @param1 takes a integer array as input
    * @return void
    */
    public static void sort(int array[])
    {
        quicksort(array,0,array.length-1);
    }

    private static void quicksort(int array[],int low,int high)
    {
        if(low < high)
        {
            int pivot = partition(array,low,high);
        //System.out.println("P = "+pivot+" L = "+low+" H = "+high);
            quicksort(array,low,pivot-1);
            quicksort(array,pivot+1,high);
        }
    }

    private static int partition(int array[],int low,int high)
    {
     int pivot = array[high];
     int i = low-1;

        for(int j=low;j<=high-1;j++)
            if(array[j] <= pivot)
            swap(array,++i,j);
           
    swap(array,i+1,high);
    return i+1;
    }

    private static void swap(int array[],int i,int j)
    {
    int temp;

        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

