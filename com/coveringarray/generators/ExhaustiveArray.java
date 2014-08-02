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
* File : ExhaustiveArray.java
*           Generates exhaustive arrays for given v,t
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

package com.coveringarray.generators;

import com.math.combinations.SigmaAlgebra;
import com.util.printers.ProgressBar;
import com.util.file.writers.ArrayWriter;

/**
* An Exhaustive Covering Array generator
* which generates all v^t interactions for
* given v,t.
*/
public class ExhaustiveArray
{
    private int v;
    private int t;
    private String STORAGE_PATH;
    private SigmaAlgebra generator;
    private final String DEFAULT_FILE_EXTENSION = ".txt";
    private final String SEPERATOR = " ";
    
    public ExhaustiveArray()
    {
        this.v = -1;
        this.t = -1;
        this.STORAGE_PATH="./";
    }

    public ExhaustiveArray(int v,int t,String STORAGE_PATH)
    {
        this.v = v;
        this.t = t;
        this.STORAGE_PATH = STORAGE_PATH;
    }

    public ExhaustiveArray(int v,int t)
    {
        this.v = v;
        this.t = t;
        this.STORAGE_PATH = "./";
    }

    public void generate()
    {  
        this.generator = new SigmaAlgebra(this.v,this.t);
        ProgressBar progressbar = new ProgressBar(generator.getTotal());             
        ArrayWriter writer = new ArrayWriter(getFileName());

            if(writer.open())
            {
            long iterator = 0;
            System.out.println("Generating " + this.v + "^" + this.t);
                while(this.generator.hasNext())
                {
                progressbar.set(++iterator);
                writer.write(this.generator.next(),this.SEPERATOR);
                }

            writer.close();
            }
            else
            {
            System.out.println("Error opening the file for writing");
            return;
            }
    }

    public String getFileName()
    {
        return this.STORAGE_PATH + "EA(" + this.generator.getTotal() + ";" + this.v + "^" + this.t +")" + this.DEFAULT_FILE_EXTENSION;
    }
}
