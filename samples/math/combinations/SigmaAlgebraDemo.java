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
* File : SigmaAlgebraDemo.java
*           Demo program to demonstrate use of SigmaAlgebra Class
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

import com.math.combinations.SigmaAlgebra;
import com.util.printers.ArrayPrinter;
import com.util.printers.ProgressBar;

public class SigmaAlgebraDemo
{

   private final int elements[][] =  {
                                             {3,1},
                                             {3,2},
                                             {3,3},
                                             {6,2},
                                             {6,5},
                                             {8,6},
                                             {18,7}
                                         };
    protected SigmaAlgebraDemo()
    {

    }

    protected void driver()
    {

        doTimeStatistics();
        displaySigmaAlgebra();
      
    }

    private void displaySigmaAlgebra()
    {
    SigmaAlgebra possibilities;
    System.out.println("5 values 3 events");
    
        possibilities = new SigmaAlgebra(5,3);

            while(possibilities.hasNext())
            ArrayPrinter.print(possibilities.next());
    }

    private void doTimeStatistics()
    {
    SigmaAlgebra permutation;
    ProgressBar progressbar;
    long iteration;
    
    System.out.println("----------------- Time Statistics -------------------");

        for(int i=0;i<elements.length;i++)
        {
        System.out.println("(" + elements[i][0] + " values " + elements[i][1] + " events)");
        permutation = new SigmaAlgebra(elements[i][0],elements[i][1]);
        progressbar = new ProgressBar(permutation.getTotal());
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

    public static void main(String args[])
    {
        SigmaAlgebraDemo demo = new SigmaAlgebraDemo();
        demo.driver();
    }
}


