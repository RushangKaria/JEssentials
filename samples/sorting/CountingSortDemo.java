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
* File : CountingSortDemo.java
*           Demo program to demonstrate the implementation of CountingSort
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

package samples.sorting;

import com.clrs.Config;
import com.clrs.sorting.CountingSort;
import com.util.generators.Array1DGenerator;
import com.util.printers.ArrayPrinter;

public class CountingSortDemo
{
    CountingSortDemo()
    {

    }

    void driver()
    {
    int array[];

        Array1DGenerator generator = new Array1DGenerator(10,1000);
        array = generator.generateInt();

        ArrayPrinter.print(array, "Unsorted : ");
        CountingSort.sort(array);
        ArrayPrinter.print(array, "Sorted   : ");
    }
    
    public static void main(String args[])
    {
        System.out.println("                    " + Config.getProgramInfo() + "                    ");

        CountingSortDemo demo = new CountingSortDemo();
        demo.driver();
                
    }
}
