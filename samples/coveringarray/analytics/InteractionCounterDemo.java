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
* File : InteractionCounterDemo.java
*           Demo class to illustrate the use of InteractionCounterDemo
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

package samples.coveringarray.analytics;

import com.coveringarray.CoveringArrayParameters;
import com.coveringarray.parsers.CoveringArrayParser;

import com.coveringarray.analytics.InteractionCounter;

import com.util.file.FileUtils;
import com.util.generators.Array1DGenerator;

import java.io.File;
import java.util.LinkedList;
import java.util.Arrays;
/**
* Demo class to illustrate the use of 
* #com.coveringarray.analytics.InteractionCounter.
*/
public class InteractionCounterDemo
{ 
    /**
    * The path where the covering arrays are present.
    */
    private final String CA_SOURCE_PATH = "inputs/coveringarray/analytics";
    
    /**
    * The output file path where the results must be placed.
    */
    private final String CA_OUTPUT_PATH = "outputs/coveringarray/analytics";
    
    /**
    * Initializes an analysis object.
    */
    protected InteractionCounterDemo()
    {
    
    }
    
    /**
    * Demonstrates the various operations of
    * {@link #com.coveringarray.analytics.InteractionCounter}.
    */
    protected void driver()
    {
    File ca_files[];   
    CoveringArrayParser parser; 
    LinkedList<CoveringArrayParameters> cas;
    CoveringArrayParameters parameters[];
            
        ca_files = FileUtils.getFiles(this.CA_SOURCE_PATH);
        cas = new LinkedList<CoveringArrayParameters>();
        
        for(int i=0;i<ca_files.length;i++)
        {
        parser = new CoveringArrayParser(ca_files[i]);
        
            if(!parser.parse())
            System.out.println("[WARNING] Could not read the covering array");
            else
            cas.add(parser.getParameters());
        }
        
     parameters = new CoveringArrayParameters[cas.size()];
     cas.toArray(parameters);
        
        testAllCovered(parameters);
        testSomeCovered(parameters);
    }
    
    /**
    * Test #com.coveringarray.analytics.InteractionCounter#getCovered()
    * The test case is to get the counts of all the rows.
    * null is passed to the method to accomplish this.
    * @param parameters covering arrays whose counts are to be tested.
    */
    private void testAllCovered(CoveringArrayParameters parameters[])
    {
    InteractionCounter counter;
    long coverage[];    
     
        for(int i=0;i<parameters.length;i++)
        {
        counter = new InteractionCounter(parameters[i]);
        coverage = counter.getCovered(null);
        
            /*
            * We explicitly use parameters[i].N as the bound for
            * the loop to emphasize that when we use null as the
            * parameter for {@link com.coveringarray.analytics.InteractionCounter#getCovered}
            * the result are the counts of all rows.
            */
            for(int j=0;j<parameters[i].N;j++)
            System.out.println(String.format("| %3d --> ",j) + coverage[j]);
        }
    }

    /**
    * Test #com.coveringarray.analytics.InteractionCounter#getCovered()
    * The test case is to get the counts of a few specific rows.
    * @param parameters covering arrays whose counts are to be tested.
    */
    private void testSomeCovered(CoveringArrayParameters parameters[])
    {
    Array1DGenerator row_generator;
    InteractionCounter counter;
    long coverage[];    
    int rows[];
    int no_of_rows;
    int MAX_ROWS;
     
        for(int i=0;i<parameters.length;i++)
        {        
        counter = new InteractionCounter(parameters[i]);
        MAX_ROWS = (int)(parameters[i].N * 0.1);    //10% of rows
        no_of_rows = ((int)(Math.random()*100))%MAX_ROWS + 1; //+1 to make range between [1,x]
        row_generator = new Array1DGenerator(no_of_rows, parameters[i].N); 
        
            rows = row_generator.generatePositiveInt();
            coverage = counter.getCovered(rows);
        
            /*
            * We explicitly use a single value to emphasize
            * that the array returned just contains one element.
            * This means that when several rows are passed, this
            * method returns the count of the private interactions
            * of all these rows and not them individually.
            * The reason for doing so is that, it is much faster to 
            * do it like this. If for a certain column pair the selected
            * rows have even a single different interaction we can say
            * that those rows do not provide any private interaction for that
            * column pair. Else we would have to add the interactions of the
            * rows to a set and iterate through all N rows to be sure.
            * But now, only if all the specified rows have the same interaction
            * do we iterate through all N rows thus amortizing the cost.
            */
           
           System.out.println(Arrays.toString(rows) + " --> " + coverage[0]);
        }
    }
    
    public static void main(String args[])
    {
        InteractionCounterDemo demo = new InteractionCounterDemo();
        demo.driver();
    }
}
