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
* File : HeapSort.java
*           Implementation of the Heap Sort Algorithm
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

import com.util.arrays.ArrayUtils;

/**
* Sorts arrays via HeapSort
* for more details about Heapsort see <a href="http://en.wikipedia.org/wiki/Heap_sort" >Heap Sort Wiki</a>
* @see com.clrs.sorting.MergeSort
* @see com.clrs.sorting.QuickSort
* @see com.clrs.sorting.InsertionSort
* @see com.clrs.sorting.BubbleSort
*/
public class HeapSort
{

    /**
    * Sorts an {@code int[]} in ascending order using heap sort.
    * @param array takes a integer array as input
    */    
    public static void sort(int array[])
    {
    int heapsize = array.length-1;
    
        build_min_heap(array,heapsize);

            for(int i=array.length-1;i>0;i--)
            {
                ArrayUtils.swap(array,0,i);
                min_heapify(array,0,--heapsize);
            }
    }    

    /**
    * Builds a min-heap of the given array and heapsize.
    * @param array whose min heap is to be formed
    * @param heapsize the total size of the heap. <b>Note that heapsize &#8804; length of the array.</b>
    * @see #min_heapify(int[],int,int)
    */
    private static void build_min_heap(int array[],int heapsize)
    {
        for(int i=(int)Math.ceil(array.length/2);i>=0;i--)  //we only go till 1/2 of the length since we can be assured
        min_heapify(array,i,heapsize);                      //that we will compare all elements. See CLRS for a definite proof.
    }

    /**
    * Builds a heap of the array from the given index and heapsize.
    * <p>
    * This method tries to place the smallest element from the sub-tree rooted at index at index.
    * @param array the array whose heap must be computed. 
    * @param index the root of the subtree where the smallest index element be kept.
    * @param heapsize the heapsize of the subtree.
    */
    private static void min_heapify(int array[],int index,int heapsize)
    {
        int left = left_child(index);
        int right = right_child(index);
        int smallest = index;
        
            if(left <= heapsize && array[left] > array[index])  //'>' since index is always smaller than left/right so, 
            smallest = left;                                    //shift all small elements to the left/right

            if(right <= heapsize && array[right] > array[smallest])
            smallest = right;

            if(smallest != index)
            {
                ArrayUtils.swap(array,index,smallest);
                min_heapify(array,smallest,heapsize);

            }
            
    }
    
    /**
    * Gets the parent of the given child node.
    * @return the parent index of the given child.
    */
    private static int parent(int index)
    {
        return (index-1)/2;
    }

    /**
    * Gets the left child of the given parent node.
    * @return the left index of the given parent node.
    */
    private static int left_child(int index)
    {
        return 2*index+1;
    }

    /**
    * Gets the right child of the given parent node.
    * @return the right index of the given parent node.
    */   
    private static int right_child(int index)
    {
        return 2*index+2;
    }
}
