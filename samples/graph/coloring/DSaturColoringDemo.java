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
* File : DSaturColoringDemo.java
*           Basic Program to illustrate use of DSaturColoring
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

package samples.graph.coloring;

import com.graph.Graph;
import com.graph.readers.GraphReader;
import com.graph.coloring.DSaturColoring;
import com.graph.coloring.ValidateColoring;

import java.io.File;
import com.util.file.FileUtils;
import com.util.printers.ArrayPrinter;
import com.util.statistics.MaxMin;

import com.util.time.TimeTracker;

import java.util.LinkedList;
import java.util.Iterator;

public class DSaturColoringDemo
{
    private final String GRAPH_FOLDER = "inputs/";

    protected DSaturColoringDemo()
    {
    com.util.printers.ProgressBar.PRINT_OFF = true;
    }

    protected void driver()
    {
    File files[];

    System.out.println("---------------- DSATUR COLORING -----------------");
    
        files = FileUtils.getFiles(this.GRAPH_FOLDER);

        for(int i=0;i<files.length;i++)
        color(files[i]);
        
    System.out.println("--------------------------------------------------");        
    }

    private void color(File file)
    {
    Graph G;
    GraphReader graph_reader;
    DSaturColoring greedy_algorithm;
    int coloring[];
    ValidateColoring validator;
    LinkedList<Integer> color_classes[];
    TimeTracker timer;

    timer = new TimeTracker();
    timer.start();
    
        graph_reader = new GraphReader(file);
        G = graph_reader.read();

        greedy_algorithm = new DSaturColoring(G);
        coloring = greedy_algorithm.color();

    timer.stop();
    
        validator = new ValidateColoring(G,coloring);

            if(validator.isValid())
            {
            System.out.println(file.getName() + " with Chromatic # = " + (MaxMin.getMaxValue(coloring)+1) + " in " + timer.getProgramTime());
            color_classes = greedy_algorithm.getColorClasses();
            //printColorClasses(color_classes);
            }
            else
            System.out.println(file.getName() + " [INVALID]");
    }

    private void printColorClasses(LinkedList<Integer> color_classes[])
    {
    Iterator iterator;

        for(int i=0;i<color_classes.length;i++)
        {
        System.out.print(i+" | ");
        iterator = color_classes[i].iterator();

            while(iterator.hasNext())
            System.out.print(iterator.next() + " ");

        System.out.println();
        }
    
    }

    public static void main(String args[])
    {
        DSaturColoringDemo demo = new DSaturColoringDemo();
        demo.driver();
    }
}
