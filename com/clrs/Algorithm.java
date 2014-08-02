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
* File : Algorithm.java
*           Common Interface for all the algorithms in the book
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

/**
* This interface is used to reflect 
* information in the CLRS book.
*/

public interface Algorithm
{
    /**
    * This method returns the name of the
    * algorithm
    * @return The name of the algorithm
    */
    public String getName();

    /**
    * This method returns wiki about 
    * the algorithm
    * @return Information about the algorithm
    */
    public String getInfo();

    /*
    * The method returns the time complexity
    * of the algorithm
    * @return Complexity of the algorithm
    */
    public String getComplexity();

    /*
    * This method returns a best case example(s)
    * for the algorithm (if known)
    * @return Example input(s) which causes the algorithm to run in the best case    
    */
    public String getExampleBest();

    /**
    * This method returns a worst case example(s)
    * for the algorithm (if known)
    * @return Example input(s) which causes the algorithm to run in the worst case
    */
    public String getExampleWorst();

    /**
    * This method returns an average case example
    * for the algorithm (if known)
    * @return Example input(s) which causes the algorithm to run in the average case
    */
    public String getExampleAverage();
}

