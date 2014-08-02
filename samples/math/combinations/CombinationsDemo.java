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
* File : CombinationsDemo.java
*           Demo program to demonstrate use of Combinations Class
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

import com.math.combinations.Combinations;
import com.util.printers.ArrayPrinter;
import com.util.printers.ProgressBar;

public class CombinationsDemo
{

   private final int combinations[][] =  {
                                             {3,1},
                                             {3,2},
                                             {3,3},
                                             {6,2},
                                             {6,5},
                                             {8,6},
                                             {100,3},
                                             {100,5},
                                             {1000,3},
                                             {200,2},
                                            // {12, 50},
                                             {10000,2}
                                         };
    protected CombinationsDemo()
    {

    }

    protected void driver()
    {

        displaynCk();
        doTimeStatistics();
        displayCombinations();
      
    }

    private void displayCombinations()
    {
    Combinations combinations;
    System.out.println("5 choose 3");
    
        combinations = new Combinations(5,3);

            while(combinations.hasNext())
            ArrayPrinter.print(combinations.next());
    }

    private void doTimeStatistics()
    {
    Combinations combination;
    ProgressBar progressbar;
    long iteration;
    
    System.out.println("----------------- Time Statistics -------------------");

        for(int i=0;i<combinations.length;i++)
        {
        System.out.println("(" + combinations[i][0] + " choose " + combinations[i][1] + ")");
        combination = new Combinations(combinations[i][0],combinations[i][1]);
        progressbar = new ProgressBar(combination.getnCk());
        iteration = 0;
    
            while(combination.hasNext())
            {
            progressbar.set(++iteration);
            combination.next();
            }
        }
        
    System.out.println("-----------------------------------------------------");
            
    }

    private void displaynCk()
    {

        for(int i=0;i<combinations.length;i++)
        System.out.println("(" + combinations[i][0] + " choose " + combinations[i][1] + ")\t\t--> " + Combinations.nCk(combinations[i][0],combinations[i][1])); 
            
    }

    public static void main(String args[])
    {
        CombinationsDemo demo = new CombinationsDemo();
        demo.driver();
    }
}


