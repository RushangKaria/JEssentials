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
* File : MergeSort.java
*           Implementation of the Merge Sort Algorithm
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
* Sorts arrays via Merge sort, 
* for more details about Merge sort see <a href="http://en.wikipedia.org/wiki/Merge_sort" >Merge Sort Wiki</a>
* @see com.clrs.sorting.InsertionSort
* @see com.clrs.sorting.QuickSort
* @see com.clrs.sorting.HeapSort
* @see com.clrs.sorting.BubbleSort
*/
public class MergeSort
{
    /**
    * Sorts an {@code int[]} in ascending order using merge sort.
    * <p>
    * <b> Note that this does not sort the array in place. </b>
    * @param array takes an integer array as input
    */
    public static void sort(int array[])
    {
        mergesort(array,0,array.length-1);   
    }

    /**
    * Divides the arrays into two halves and then combines them.
    * <p>
    * The recursion depth can become quite high for large arrays and can
    * cause {@link java.lang.StackOverflowError} errors.
    * @param array the array to be sorted.
    * @param low the first index of the subarray
    * @param high the last index of the subarray 
    * @throws java.lang.StackOverflowError    
    * @see java.lang.StackOverflowError
    */
    private static void mergesort(int array[],int low,int high)throws StackOverflowError
    {
       if(low < high)
       {
            mergesort(array,low,(low+high)/2);
            mergesort(array,(low+high/2)+1,high);
            merge(array,low,(low+high)/2,high);
       }
    }

    /**
    * Merges the subarrays into a sorted array.
    * <p>
    * <b> This method does not sort and merge in-place.</b><br>
    * When this method returns the lower levels are sorted with respect to each other but not with the final array.
    * @param array the array to be sorted.
    * @param low the first index of the subarray
    * @param high the last index of the subarray 
    */
    private static void merge(int array[],int low,int mid,int high)
    {
    int i = 0;
    int j = 0;
    int k = 0;    

        int L[] = new int[mid - low + 1];   //Extra storage required
        int R[] = new int[high - mid];      //for copying the elements.

        k = 0;
        for(i=low;i<mid+1;i++)              //Copy the elements of the original array
        L[k++] = array[i];                  //into the new arrays

        k = 0;
        for(i=mid+1;i<=high;i++)
        R[k++] = array[i];   

        i = 0;
        j = 0;
        k = low;
        
        while(i < (mid-low+1) && j < (high-mid))    //merge!!
            if(L[i] < R[j])
            array[k++] = L[i++];       
            else
            array[k++] = R[j++];

        if(i == (mid-low+1))
            while(j < (high-mid))
            array[k++] = R[j++];
        else
            while(i < (mid-low+1))
            array[k++] = L[i++];          
            
    }
}
