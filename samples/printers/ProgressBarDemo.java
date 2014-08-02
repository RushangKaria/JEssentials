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
* File : ProgressBarDemo.java
*          Demo program to demonstrate use of ProgressBar.
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

package samples.printers;

import com.util.printers.ProgressBar;

/**
* Provides a demonstration of @link ProgressBar
*/ 
public class ProgressBarDemo
{
    private long limit;
    
    ProgressBarDemo(long limit)
    {
        this.limit = limit;
    }

    void driver()
    {
    ProgressBar progressbar;

        progressbar = new ProgressBar(this.limit);
        
            for(long i=0;i<=this.limit;i++)
            progressbar.set(i);

    }

    public static void main(String args[])
    {
        ProgressBarDemo demo = new ProgressBarDemo(1000000000);
        demo.driver();
    }
}
