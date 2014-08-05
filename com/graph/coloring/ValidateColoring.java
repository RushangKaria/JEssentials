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
* File : ValidateColoring.java
*           Validates the coloring of a graph
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


import com.util.printers.ProgressBar;
import com.graph.Graph;

public class ValidateColoring
{
    private Graph G;
    private int coloring[];

    public ValidateColoring(Graph G,int coloring[])
    {
        this.G = G;
        this.coloring = coloring;
    }

    public boolean isValid()
    {
    ProgressBar progressbar = new ProgressBar(this.coloring.length-1);
    int vertices = this.G.getVertices();
    
        for(int i=0;i<vertices;i++)
        {
        progressbar.set(i);
        
            for(int j=i+1;j<vertices;j++)
                if(G.hasEdge(i,j) && coloring[i] == coloring[j])
                return false;
        }

    return true;
    }

    public void printConflicting()
    {
    int vertices = this.G.getVertices();
      
        for(int i=0;i<vertices;i++)
        {        
            for(int j=i+1;j<vertices;j++)
                if(G.hasEdge(i,j) && coloring[i] == coloring[j])
                System.out.println(i + " conflicts with " + j + " --> " + coloring[i]); 
        }
    }
    
}
