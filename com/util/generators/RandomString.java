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

import java.util.Random;

/**
* This class is used to generate a Random String
* in the alphabet [a-zA-Z].
*/
public class RandomString implements Generator_Interface
{
    private int MAX_SIZE;    //Max size that any string generated can have
    private Random generator;
    
    /**
    * Default constructor sets the max size to 
    * 20
    */
    public RandomString()
    {
    generator = new Random();
        this.MAX_SIZE = 20;
    }

    /**
    * Constructor that takes an argument to 
    * set the max size.
    * @param1 The max size of any randomly generated string
    */
    public RandomString(int MAX_SIZE) 
    {
    generator = new Random();
    
        this.MAX_SIZE = MAX_SIZE;
    }

    /**
    * Returns the current max size of the string
    * which is generated. The string sizes can vary
    * but cannot be greater than MAX_SIZE
    * @return int
    */
    public int getMaxSize()
    {
        return this.MAX_SIZE;
    }

    /**
    * Returns a randomly generated string of the alphabet
    * [a-zA-Z]
    * @return String
    */
    public String generate()
    {
    int size = -1;
    int next;
    String temp = new String("");

        size = generator.nextInt()%this.MAX_SIZE;         //randomly generate a size constrained by max_size
        size = size < 0 ? -size : size;

            for(int i=0;i<size;i++)
            {
                if(generator.nextInt()%2 == 0)               //select a capital or small character with 50% probability
                next = generator.nextInt()%(91 - 65) + 65;  //generate a character in [A-Z]
                else
                next = generator.nextInt()%(124 - 98) + 98; //generate a character in [a-z]

            temp += (char)next;
            }

    return temp;
    }
}
