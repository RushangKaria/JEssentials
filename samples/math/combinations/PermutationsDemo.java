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
* File : PermutationsDemo.java
*           Demo program to demonstrate use of Permutations Class
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

package samples.math.combinations;

import com.math.combinations.Permutations;
import com.util.printers.ArrayPrinter;
import com.util.printers.ProgressBar;

public class PermutationsDemo
{

   private final int permutations[][] =  {
                                             {3,1},
                                             {3,2},
                                             {3,3},
                                             {6,2},
                                             {6,5},
                                             {8,6},
                                             {200,2}
                                         };
    protected PermutationsDemo()
    {

    }

    protected void driver()
    {

        displaynPk();
        doTimeStatistics();
        displayPermutations();
      
    }

    private void displayPermutations()
    {
    Permutations permutations;
    System.out.println("5 P 3");
    
        permutations = new Permutations(5,3);

            while(permutations.hasNext())
            ArrayPrinter.print(permutations.next());
    }

    private void doTimeStatistics()
    {
    Permutations permutation;
    ProgressBar progressbar;
    long iteration;
    
    System.out.println("----------------- Time Statistics -------------------");

        for(int i=0;i<permutations.length;i++)
        {
        System.out.println("(" + permutations[i][0] + " P " + permutations[i][1] + ")");
        permutation = new Permutations(permutations[i][0],permutations[i][1]);
        progressbar = new ProgressBar(permutation.getnPk());
        iteration = 0;

            System.out.println(progressbar.getLimit());
            while(permutation.hasNext())
            {
            progressbar.set(++iteration);
            permutation.next();
            }
            
        }
        
    System.out.println("-----------------------------------------------------");
            
    }

    private void displaynPk()
    {

        for(int i=0;i<permutations.length;i++)
        System.out.println("(" + permutations[i][0] + " P " + permutations[i][1] + ")\t\t--> " + Permutations.nPk(permutations[i][0],permutations[i][1])); 
            
    }

    public static void main(String args[])
    {
        PermutationsDemo demo = new PermutationsDemo();
        demo.driver();
    }
}


