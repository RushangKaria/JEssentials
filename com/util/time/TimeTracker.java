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
* File : TimeTracker.java
*           Utility to track execution times
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

package com.util.time;

import com.util.time.FormatTime;

public class TimeTracker
{
    private long start_time;
    private long end_time;
    private long time_limit;
    
    public TimeTracker()
    {
        this.start_time = 0;
        this.end_time = 0;
        this.time_limit = 0;
    }

    public void start()
    {
        this.start_time = System.currentTimeMillis();
    }

    public void stop()
    {
        this.end_time = System.currentTimeMillis();
    }

    public String getProgramTime()
    {
        return FormatTime.format((this.end_time - this.start_time)/1000.0);
    }

    public long getStartTime()
    {
        return this.start_time;
    }

    public long getEndTime()
    {
        return this.end_time;
    }

    public void setLimit(long seconds)
    {
        this.time_limit = System.currentTimeMillis() + seconds*1000;
    }

    public boolean expired()
    {
        if(System.currentTimeMillis() > this.time_limit)
        return true;
        else 
        return false;
    }
    
}
