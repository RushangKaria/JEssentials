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
* File : RandomGreedyColoring.java
*           Finds a Random Greedy Coloring of the Graph
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

package com.graph.coloring;

import com.graph.Graph;
import java.util.Random;

import com.util.statistics.MaxMin;
import java.util.LinkedList;
import java.util.HashMap;

public class RandomGreedyColoring
{
    private Graph G;   
    private int coloring[];
    private int V;
    private Random generator; 

    public RandomGreedyColoring()
    {
        this.G = null;
        this.coloring = null;
        this.V = -1;
        this.generator = new Random();
    }   

    public RandomGreedyColoring(Graph G)
    {
        this.G = G;
        this.V = G.getVertices();
        this.coloring = new int[this.V];
        this.generator = new Random();
    }

    public int[] color()
    {
    int order[] = getOrder();
    HashMap<Integer,Boolean> neighbour_colors;

        for(int i=0;i<order.length;i++)
        {
        this.coloring[order[i]] = 0;
        neighbour_colors = new HashMap<Integer,Boolean>();
            
            for(int j=0;j<this.V;j++)
                if(G.hasEdge(order[i],j))
                neighbour_colors.put(this.coloring[j],true);
                    
            while(neighbour_colors.containsKey(this.coloring[order[i]]))
            this.coloring[order[i]]++;
        }
    
    return coloring;
    }

    private int[] getOrder()
    {
    int random_order[] = new int[this.V];
    boolean processed[] = new boolean[this.V];
    int vertices_left = this.V;

        while(vertices_left > 0)
        {
        int vertex = generator.nextInt()%this.V;
        vertex = vertex < 0 ?-vertex:vertex;

            if(processed[vertex])
            continue;
            else
            {
            random_order[this.V-vertices_left] = vertex;
            processed[vertex] = true;
            vertices_left--;   
            }
        
        }     

    return random_order;
    }

    @SuppressWarnings("unchecked")
    public LinkedList<Integer>[] getColorClasses()
    {
    LinkedList<Integer> color_classes[];

    color_classes = new LinkedList[MaxMin.getMaxValue(this.coloring)+1];

        for(int i=0;i<color_classes.length;i++)
        color_classes[i] = new LinkedList<Integer>();
          
        for(int i=0;i<this.coloring.length;i++)
        color_classes[this.coloring[i]].add(i);
        
    return color_classes;        
    }    
    
}
