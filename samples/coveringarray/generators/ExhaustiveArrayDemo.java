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
* File : ExhaustiveArrayDemo.java
*           Demo program to illustrate use of ExhaustiveArray
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

package samples.coveringarray.generators;

import java.io.File;
import com.coveringarray.generators.ExhaustiveArray;

/**
* A program which generates Exhaustive 
* Arrays.
*/
public class ExhaustiveArrayDemo
{
    private final int[][] parameters =  {
                                            {2,3},
                                            {2,4},
                                            {2,5},
                                            {2,8},
                                            {3,4},
                                            {3,5}
                                        };

    private final String STORAGE_PATH = "./outputs/";                                        

    protected ExhaustiveArrayDemo()
    {
        File root_folder = new File(this.STORAGE_PATH);

            if(root_folder.exists() && !root_folder.isDirectory())
            {
                System.out.println("Root folder : " + this.STORAGE_PATH + " is not a directory...Exiting");
                System.exit(0);
            }
            else if(!root_folder.exists())
            root_folder.mkdir();
            
    }

    protected void driver()
    {
        ExhaustiveArray generator;

            for(int i=0;i<this.parameters.length;i++)
            {
                generator = new ExhaustiveArray(this.parameters[i][0],this.parameters[i][1],this.STORAGE_PATH);
                generator.generate();
            generator = null;
            System.gc();
            }
    }

    public static void main(String args[])
    {
        ExhaustiveArrayDemo demo = new ExhaustiveArrayDemo();
        demo.driver();
    }
}
