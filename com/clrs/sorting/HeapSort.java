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

/**
* Sorts arrays via HeapSort
*/
public class HeapSort
{

    /**
    * Sorts an int[] in ascending order using heap sort.
    * @param1 takes a integer array as input
    * @return void
    */    
    public static void sort(int array[])
    {
    int heapsize = array.length-1;
    
        build_min_heap(array,heapsize);

            for(int i=array.length-1;i>0;i--)
            {
                swap(array,0,i);
                min_heapify(array,0,--heapsize);
            }
    }    

    private static void build_min_heap(int array[],int heapsize)
    {
        for(int i=(int)Math.ceil(array.length/2);i>=0;i--)
        min_heapify(array,i,heapsize);
    }

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
                swap(array,index,smallest);
                min_heapify(array,smallest,heapsize);

            }
            
    }

    private static void swap(int array[],int i,int j)
    {
    int temp;

        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static int parent(int index)
    {
        return (index-1)/2;
    }

    private static int left_child(int index)
    {
        return 2*index+1;
    }

    private static int right_child(int index)
    {
        return 2*index+2;
    }
}
