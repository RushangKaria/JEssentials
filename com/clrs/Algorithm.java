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
* This package contains implementations of the 
* algorithms in the CLRS textbook.
* <p>
* The algorithms implemented depend on the book edition
* @see com.clrs.Config#BOOK_VERSION
*/
package com.clrs;

/**
* This interface is used to enforce some methods that 
* every algorithm of the CLRS textbook *must* implement.
* <p>
* Some common methods are to retrieve complexities and 
* instances which cause worst case behavior.
*/
public interface Algorithm
{
    /**
    * This method returns the name of the
    * algorithm.
    * @return The name of the algorithm.
    */
    public String getName();

    /**
    * This method returns wiki about 
    * the algorithm.
    * <p>
    * The wiki can include information extracted from Wikipedia
    * and other sources.
    * @return Information about the algorithm.
    */
    public String getInfo();

    /*
    * The method returns the time complexity
    * of the algorithm.
    * <p>
    * The format of the time complexity is already in a table form
    * and can be embedded in a print statement to display it.
    * <p>
    * For example @codeSystem.out.println(<Algorithm>.getInfo());
    * @return Various complexities of the algorithm.
    */
    public String getComplexity();

    /*
    * This method returns a best case example(s)
    * for the algorithm (if known).
    * <p>
    * As with @see#getInfo() the String is already properly formatted and can
    * be embedded in a print statement to display it.
    * <p>
    * For example @codeSystem.out.println(<Algorithm>.getExampleBest());
    * @return Example input(s) which causes the algorithm to run in the best case.
    */
    public String getExampleBest();

    /**
    * This method returns a worst case example(s)
    * for the algorithm (if known).
    * <p>
    * As with @see#getInfo() the String is already properly formatted and can
    * be embedded in a print statement to display it.
    * <p>
    * For example @codeSystem.out.println(<Algorithm>.getExampleWorst());
    * @return Example input(s) which causes the algorithm to run in the worst case.
    */
    public String getExampleWorst();

    /**
    * This method returns an average case example(s)
    * for the algorithm (if known).
    * <p>
    * As with @see#getInfo() the String is already properly formatted and can
    * be embedded in a print statement to display it.
    * <p>
    * For example @codeSystem.out.println(<Algorithm>.getExampleAverage());
    * @return Example input(s) which causes the algorithm to run in the worst case.
    */
    public String getExampleAverage();
}

