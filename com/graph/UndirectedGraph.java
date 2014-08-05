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
* File : Graph.java
*           Implementation of Graphs
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

package com.graph;

import com.util.statistics.MaxMin;

/**
* Light-weight class to form UndirectedGraphs and operate
* on them. For a much flexible but heavy-weight class
* please check out jgrapht.
*/
public class UndirectedGraph extends Graph
{
    private int V;
    private long E;
    private boolean[][] adjacency_list;
    private int degree[];

    /**
    * Construct a graph by specifying the number of
    * vertices.
    * @param The number of vertices in the graph
    */
    public UndirectedGraph(int V)
    {
        this.V = V;
        this.adjacency_list = new boolean[this.V][this.V];
        this.degree = new int[this.V];
        this.E = 0;
    }

    /**
    * Construct a graph by specifying the adjacency
    * list.
    * @param The adjacency list for the graph
    */
    public UndirectedGraph(boolean[][] adjacency_list)
    {
        this.adjacency_list = adjacency_list;
        this.V = this.adjacency_list.length;
        this.E = 0;
        
            for(int i=0;i<this.V;i++)
                for(int j=0;j<this.V;j++)
                if(this.adjacency_list[i][j] && i < j)
                {
                this.E++;
                this.degree[i]++;
                this.degree[j]++;
                }
    }

    /**
    * Method to check if an edge exists
    * @param Vertex 1
    * @param Vertex 2
    * @return Whether an edge exists between the two vertices
    */
    public boolean hasEdge(int v1,int v2)
    {
    return this.adjacency_list[v1][v2];
    }
    
    /**
    * Add an edge between two vertices
    * @param Vertex 1
    * @param Vertex 2
    * @return true if an edge was added, false if an edge was already present
    */
    public boolean addEdge(int v1,int v2)
    {
        if(this.adjacency_list[v1][v2])
        return false;
        else
        {
        this.adjacency_list[v1][v2] = true;
        this.adjacency_list[v2][v1] = true;
        this.E++;
        this.degree[v1]++;
        this.degree[v2]++;
        }

    return true;
    }

    /**
    * Delete an edge between two vertices
    * @param Vertex 1
    * @param Vertex 2
    * @return true if the edge was deleted, false if no edge existed
    */
    public boolean deleteEdge(int v1,int v2)
    {
        if(!this.adjacency_list[v1][v2])
        return false;
        else
        {
        this.adjacency_list[v1][v2] = false;
        this.adjacency_list[v2][v1] = false;
        this.E--;
        this.degree[v1]--;
        this.degree[v2]--;
        }

    return true;
    }

    /**
    * Returns the adjacency list of this graph
    * @return The adjaceny list of the graph
    */
    public boolean[][] getAdjacency()
    {
        return this.adjacency_list;
    }

    /**
    * @return Returns the number of vertices in this graph
    */
    public int getVertices()
    {
        return this.V;
    }

    /**
    * @return Returns the number of edges in this graph
    */
    public long getEdges()
    {
        return this.E;
    }

    /**
    * @return Returns the degree of all the vertices
    */
    public int[] getDegrees()
    {
        return this.degree;
    }

    /**
    * @param The vertex whose degree is required
    * @return Returns the degree of the vertex specified
    */
    public int getDegree(int vertex)
    {
        return this.degree[vertex];
    }

    /**
    * @return Returns the vertex with the maximum degree
    */
    public int getMaxVertex()
    {
        return MaxMin.getMax(this.degree)[1];    
    }

    /**
    * @return Returns the maximum degree of any vertex in this graph
    */
    public int getMaxDegree()
    {
        return MaxMin.getMax(this.degree)[0];
    }

    /**
    * @return Returns the vertex with the minimum degree
    */
    public int getMinVertex()
    {
        return MaxMin.getMin(this.degree)[1];
    }

    /**
    * @return Returns the minimum degree of any vertex in this graph
    */
    public int getMinDegree()
    {
        return MaxMin.getMin(this.degree)[0];
    }
    
}
