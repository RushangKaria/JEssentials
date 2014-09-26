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
* File : UncoveredInteractionsDemo.java
*           Demo class to illustrate the use of UncoveredInteractions
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

import com.coveringarray.analytics.UncoveredInteractions;

import com.util.file.FileUtils;
import com.util.generators.Array1DGenerator;

import java.io.File;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
* Demo class to illustrate the use of 
* #com.coveringarray.analytics.UncoveredInteractions.
*/
public class UncoveredInteractionsDemo
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
    protected UncoveredInteractionsDemo()
    {
    
    }
    
    /**
    * Demonstrates the various operations of
    * {@link #com.coveringarray.analytics.UncoveredInteractions}.
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
     
        testIncomplete(parameters);
        //testComplete(parameters);
        
    }
    
    /**
    * Tests coverage on incomplete CA's.
    */
    private void testIncomplete(CoveringArrayParameters parameters[])
    {
    UncoveredInteractions uncovered_interactions;
    HashMap<Long, HashSet<Integer>> interactions;
    
        for(int i=0;i<parameters.length;i++)
        {
        uncovered_interactions = new UncoveredInteractions(parameters[i]);
        interactions = uncovered_interactions.getUncovered();
            
        uncovered_interactions.displayUncovered(interactions);
        }
    }
   

    public static void main(String args[])
    {
        UncoveredInteractionsDemo demo = new UncoveredInteractionsDemo();
        demo.driver();
    }
}
