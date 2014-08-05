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
*           Basic layout of a Graph
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

public abstract class Graph
{
    /**
    * Method to check if an edge exists
    * @param Vertex 1
    * @param Vertex 2
    * @return Whether an edge exists between the two vertices
    */
    public abstract boolean hasEdge(int v1,int v2);

    /**
    * Add an edge between two vertices
    * @param Vertex 1
    * @param Vertex 2
    * @return true if an edge was added, false if an edge was already present
    */
    public abstract boolean addEdge(int v1,int v2);

    /**
    * Delete an edge between two vertices
    * @param Vertex 1
    * @param Vertex 2
    * @return true if the edge was deleted, false if no edge existed
    */
    public abstract boolean deleteEdge(int v1,int v2);

    /**
    * Returns the adjacency list of this graph
    * @return The adjaceny list of the graph
    */
    public abstract boolean[][] getAdjacency();

    /**
    * @return Returns the number of vertices in this graph
    */
    public abstract int getVertices();

    /**
    * @return Returns the number of edges in this graph
    */
    public abstract long getEdges();

    /**
    * @return Returns the degree of all the vertices
    */
    public abstract int[] getDegrees();

    /**
    * @param The vertex whose degree is required
    * @return Returns the degree of the vertex specified
    */
    public abstract int getDegree(int vertex);

    /**
    * @return Returns the vertex with the maximum degree
    */
    public abstract int getMaxVertex();
    
    /**
    * @return Returns the maximum degree of any vertex in this graph
    */
    public abstract int getMaxDegree();

    /**
    * @return Returns the vertex with the minimum degree
    */
    public abstract int getMinVertex();

    /**
    * @return Returns the minimum degree of any vertex in this graph
    */
    public abstract int getMinDegree();           
}
