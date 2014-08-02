/*
* Title : FormatTime.java
*
* Description : Time formatter 
*
* Package : com.rushang.util
*
* Author : Rushang Vinod Vandana Karia
*	     - Rushang.Karia@asu.edu	
*	     - 4806283130	
*/

package com.util.time;

public class FormatTime
{
	public static String format(double time)
	{
		long mins,hours;
		double secs;

		secs = time % 60;
		time/=60;

		mins = (long)time % 60;
		time/=60;

		hours = (long)time % 60;
		time/=60;

	return (hours + "h:" + mins + "m:" + String.format("%.2f",secs) + "s"); 
	}

	public static String format(long time)
	{
		long secs,mins,hours;
		

		secs = (long)time % 60;
		time/=60;

		mins = (long)time % 60;
		time/=60;

		hours = (long)time % 60;
		time/=60;

	return (hours + "h:" + mins + "m:" + secs + "s"); 
	}
}
