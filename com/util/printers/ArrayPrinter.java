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
* File : ArrayPrinter.java
*          Array printing utility
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

package com.util.printers;


import java.util.Arrays;

/**
* This class contains methods to print
* arrays.
*/
public class ArrayPrinter
{
    /*
    * print an integer array without any
    * special formatting. Optionally add
    * a message before the array
    */
    public static void print(int array[], String ...messages)
    {
        for(int i=0;i<messages.length;i++)
        System.out.print(messages[i]+" ");
        
        System.out.println(Arrays.toString(array));
    }

    /**
    * Print an integer array with a specified 
    * precision. Optionally add a message
    * before the array
    */
    public static void print(int array[],int precision, String ...messages)
    {
        String formatting = "%" + (precision > 0 ?precision:"") + "d";

        for(int i=0;i<messages.length;i++)
        System.out.print(messages[i]+" ");
        
            for(int i=0;i<array.length-1;i++)
            System.out.print(String.format(formatting,array[i]));

            System.out.println(String.format(formatting,array[array.length-1]));
    }    

    /*
    * print a double array without any
    * special formatting. Optionally add
    * a message before the array
    */
    public static void print(double array[], String ...messages)
    {
        for(int i=0;i<messages.length;i++)
        System.out.print(messages[i]+" ");
            
        System.out.println(Arrays.toString(array));
    }
    
    /**
    * Print a double array with a specified 
    * precision. Optionally add a message
    * before the array
    */
    public static void print(double array[],int precision, String ...messages)
    {
        String formatting = "%." + (precision > 0 ?precision:"") + "f";

        for(int i=0;i<messages.length;i++)
        System.out.print(messages[i]+" ");

            for(int i=0;i<array.length-1;i++)
            System.out.print(String.format(formatting,array[i]));

            System.out.println(String.format(formatting,array[array.length-1]));
    }

}
