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
* File : RowFetchDemo.java
*           Demo program to illustrate use of the RowFetch class.
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

package samples.coveringarray.report;

import com.coveringarray.report.RowFetch;

/**
* Demo program illustrating the use of
* #com.coveringarray.report.RowFetch
*/ 
public class RowFetchDemo
{
    /**
    * Various covering array parameters to be tried out.
    */
    private final int parameters[][] =  {
                                            {2, 17      ,3},
                                            {2, 20000   ,3},
                                            {2, 25000   ,3},
                                            {2, 99      ,3},
                                            {2, 12      ,25}
                                        };

    /**
    * Default Constructor to construct an object.
    */
    protected RowFetchDemo()
    {

    }

    /**
    * Gets the best known rows of various Covering
    * Array parameters.
    */
    protected void driver()
    {
        testColbourn();
        testNIST();
        
    }

    /**
    * Gets the best known rows of CA's using
    * Charlie Colbourn as the authority.
    */
    private void testColbourn()
    {
    RowFetch fetcher;
      
        System.out.println("======------ Colbourn records ------======");

            for(int i=0;i<this.parameters.length;i++)
            {
            fetcher = new RowFetch(this.parameters[i][0], this.parameters[i][1], this.parameters[i][2]);            
            System.out.println("| Best Known " + getName(i) +" --> " + fetcher.fetch(RowFetch.Authority.COLBOURN));
            }

        System.out.println("__________________________________________");        
    }

    /**
    * Gets the best known rows of CA's using
    * NIST as the authority.
    */
    private void testNIST()
    {
    RowFetch fetcher;
      
        System.out.println("======-------- NIST records --------======");

            for(int i=0;i<this.parameters.length;i++)
            {
            fetcher = new RowFetch(this.parameters[i][0], this.parameters[i][1], this.parameters[i][2]);            
            System.out.println("| Best Known " + getName(i) +" --> " + fetcher.fetch(RowFetch.Authority.NIST));
            }

        System.out.println("__________________________________________");    
    }


    /**
    * @param index the index to the CA instance in #parameters.
    * @return a string format of the example instance.
    */
    private String getName(int index)
    {
        return "CA(" + this.parameters[index][0] + "," + this.parameters[index][1] + "," + this.parameters[index][2] + ")";
    }

    public static void main(String args[])
    {
        RowFetchDemo demo = new RowFetchDemo();
        demo.driver();
    }
}














