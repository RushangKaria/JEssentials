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
* File : GraphReaderDemo.java
*           Basic Program to illustrate use of GraphReader
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

package samples.graph.readers;

import com.graph.Graph;
import com.graph.readers.GraphReader;

public class GraphReaderDemo
{
    private final String DIMACS_FILE = "inputs/dsjc250.5.col";
    
    protected GraphReaderDemo()
    {

    }

    protected void driver()
    {
        dimacs_example();
    }

    private void dimacs_example()
    {
    Graph G;
    GraphReader graph_reader;
    
        System.out.println("------------------- DIMACS -------------------");
        
            graph_reader = new GraphReader(this.DIMACS_FILE);
            G = graph_reader.read();

                if(G == null)
                System.out.println("BAD");
                else
                System.out.println("|V| = " + G.getVertices() + " |E| = " + G.getEdges());

        System.out.println("----------------------------------------------");
    }

    public static void main(String args[])
    {
        GraphReaderDemo demo = new GraphReaderDemo();
        demo.driver();
    }

}
