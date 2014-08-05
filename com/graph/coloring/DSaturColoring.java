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
* File : DSaturColoring.java
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
import com.clrs.sorting.MergeSort;
import com.util.arrays.ArrayUtils;
import com.util.statistics.MaxMin;
import java.util.LinkedList;
import java.util.HashMap;

public class DSaturColoring
{
    private Graph G;   
    private int coloring[];
    private int V;

    public DSaturColoring()
    {
        this.G = null;
        this.coloring = null;
        this.V = -1;
    }   

    public DSaturColoring(Graph G)
    {
        this.G = G;
        this.V = G.getVertices();
        this.coloring = new int[this.V];

        for(int i=0;i<this.V;i++)
        this.coloring[i] = -1;
    }

    public int[] color()
    {
    boolean colored[] = new boolean[this.V];
    int highest_degree_vertex;
    int colored_vertices;
    
        highest_degree_vertex = MaxMin.getMaxIndex(G.getDegrees());
        this.coloring[highest_degree_vertex] = 0;
        colored[highest_degree_vertex] = true;

        colored_vertices = this.V - 1;     
        while(colored_vertices-- > 0)
        {
            int next_vertex = DSatur(colored);
            colorSmallest(next_vertex);
            colored[next_vertex] = true;
            
        }
    
    return coloring;
    }

    private void colorSmallest(int vertex)
    {
    HashMap<Integer,Boolean> neighbour_vertices = new HashMap<Integer,Boolean>();

        for(int i=0;i<this.V;i++)
        if(this.G.hasEdge(i,vertex))
        neighbour_vertices.put(this.coloring[i],true);

        this.coloring[vertex] = 0;

        while(neighbour_vertices.containsKey(this.coloring[vertex]))
        this.coloring[vertex]++;
    }

    private int DSatur(boolean colored[])
    {   
    HashMap<Integer,Boolean> colors;
    int max_saturation = Integer.MIN_VALUE;
    int best_vertex = -1;
    int saturation;
        
        for(int i=0;i<this.V;i++)
        {
            if(colored[i])
            continue;
        
        saturation = 0;
        colors = new HashMap<Integer,Boolean>();
        
            for(int j=0;j<this.V;j++)
                if(this.G.hasEdge(i,j) && this.coloring[j] != -1 && !colors.containsKey(this.coloring[j]))
                {
                saturation ++;
                colors.put(this.coloring[j],true);
                }

            if(saturation > max_saturation)
            {
            max_saturation = saturation;
            best_vertex = i;
            }
            else if(saturation == max_saturation && this.G.getDegree(i) > this.G.getDegree(best_vertex))
                 best_vertex = i;                                              
        }

    return best_vertex;
    } 

    private int[] getOrder()
    {
    int degrees[] = this.G.getDegrees();
    int order[] = new int[this.V];
    
        for(int i=0;i<this.V;i++)
        {
        int index = MaxMin.getMax(degrees)[1];
        degrees[index] = Integer.MIN_VALUE;
        order[i] = index;
        }

    return order;
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
