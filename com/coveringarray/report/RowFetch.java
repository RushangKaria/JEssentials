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
* File : FetchNumberOfRows.java
*           Fetch the number of rows of a specific CA.
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

package com.coveringarray.report;

import com.coveringarray.CoveringArrayParameters;

import com.coveringarray.report.ColbournBestKnown;

/**
* Finds the best known rows of a 
* specific CA that are known by
* either Colbourn or NIST.
*/
public class RowFetch
{
    /**
    * The strength of the covering array whose best known rows are to be found.
    */
    private int t;

    /**
    * The # of columns of the covering array whose best known rows are to be found.
    */
    private int k;

    /**
    * The # of values each symbol can take of the covering array whose best known rows are to be found.
    */
    private int v;

    /**
    * Constructs a Fetcher from the specified parameters.
    * @param t the strength of the covering array.
    * @param k the # of columns of the covering array.
    * @param v the # of values each symbol can take of the covering array.
    */
    public RowFetch(int t, int k, int v)
    {
        this.t = t;
        this.k = k;
        this.v = v;
    }

    /**
    * Constructs a Fetcher from the specified parameters.
    * @param parameters the covering array parameters.
    */
    public RowFetch(CoveringArrayParameters parameters)
    {
        this.t = parameters.t;
        this.k = parameters.k;
        this.v = parameters.v;
    }

    /**
    * The different type of authorities whose rows are supported.
    */
    public static enum Authority { COLBOURN, NIST };

    /**
    * Finds the # of rows of the CA with that the specified best known authority 
    * knows.
    * @param authority the authority whose records must be searched.
    * @return the # of rows that the authority has knowledge of, - otherwise.
    */
    public String fetch(Authority authority)
    {
        switch(authority)
        {
        case COLBOURN : return new ColbournBestKnown(this.t, this.k, this.v).get();

        case NIST     : return new NISTBestKnown(this.t, this.k, this.v).get();

        default       : return "-";     
        }

    }
}
