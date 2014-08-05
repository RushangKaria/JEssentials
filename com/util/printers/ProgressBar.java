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
* File : ProgressBar.java
*          A ProgressBar utility for shells. Will not work with Eclipse due to known bugs.
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

package com.util.printers;

import com.util.time.FormatTime;


/**
* Creates a progress bar with rich information.
*/
public class ProgressBar
{
    public static boolean PRINT_OFF = false;

	private long limit;
	private int cache;
	private double total_time;
	private long start;

    /**
    * The limit must be specified for correct
    * operation else the program defaults to 100.
    * @param1 The limit at which the bar hits 100%
    */
	public ProgressBar(long limit)
	{
	this.limit = limit;
	this.cache = -1;
	this.start = System.currentTimeMillis();
	}

	/**
	* Default constructor specifies a max value of 
	* 100 implying that the application must handle
	* the percent calculations
	*/
	public ProgressBar()
	{
        this.limit = 100;
        this.cache = -1;
        this.start = System.currentTimeMillis();
	}

    /**
    * Set the progress and display the time and other info.
    * It calculates the percent and no processing for that must
    * be done.
    * @param1 the current # that is being iterated
    */
	public void set(long progress)
	{
		if((int)((progress*100.0)/this.limit) == cache || ProgressBar.PRINT_OFF) //Maintain a cache to prevent flickering for fast calls
		return;

	this.cache      = (int)((progress*100.0)/this.limit);	
	this.total_time = (System.currentTimeMillis() - start)/(1000.0*(cache+1));
	

	String hashes = "";
	String spaces = "";

		for(int i=0;i<cache/2;i++)      //Concatenate the string since stdout 'n' times is more costly
		hashes += "#";

		for(int i=cache/2;i<50;i++)
		spaces += " ";

        if(cache < 100)
    	System.out.print("[" + hashes + spaces + "] " + cache + "% eta " + FormatTime.format((100 - cache)*this.total_time) + "\r");
	    else
	    {
	    this.total_time = (System.currentTimeMillis() - start )/1000.0;
    	System.out.print("[" + hashes + spaces + "] " + cache + "% in " + FormatTime.format(this.total_time) + "\r");
    	System.out.println();
	    }	
	}

	/**
	* This method returns the limit that this
	* @link ProgressBar object was set with
	* @return the limit of the progress when it goes till 100%
	*/
	public long getLimit()
	{
		return this.limit;
	}

    /**
    * This method returns the time taken in seconds, and in case of 
    * 100%, the total time taken for the computation.
    * @return The time taken uptil now in seconds
    */
	public long getTimeLong()
	{
        return (long)this.total_time;
	}

	/**
	* This method returns the time taken as a String, and in case
	* of 100%, the total time taken for the computation.
	* @return The time taken in h:m:s format
	*/
	public String getTimeTaken()
	{
        return FormatTime.format(this.total_time);
	}
	
}
