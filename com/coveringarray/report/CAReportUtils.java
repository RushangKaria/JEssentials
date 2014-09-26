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
* File : CAReportUtils.java
*           Utilities for creating covering array reports.
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

import com.coveringarray.parsers.CoveringArrayParser;
import com.coveringarray.CoveringArrayParameters;
import com.coveringarray.report.RowFetch;

import com.util.file.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

import java.util.LinkedList;

/**
* A helper class to assist in the creation of
* reports for covering arrays.
*/
public class CAReportUtils
{
    /**
    * The seperator for the report. 
    * Example usage : Use tabs to enable automatic table creation. 
    */
    private static String SEPERATOR = "\t";

    /**
    * Set the seperator for generation of reports.
    * @param SEPERATOR the seperator between values in the report.
    */
    public static void setSeperator(String SEPERATOR)
    {
        SEPERATOR = SEPERATOR;
    }

    /**
    * Generate a report for the covering arrays provided.
    * <p>
    * The report is of the following form :- <br>
    * CA(t,k,v) -- Best Known -- NIST -- [Headers] where [Headers] are user-defined headers.
    * <p>
    * <b> IMPORTANT : This method does not sort the covering arrays by itself. Please make
    * sure that you provide sorted arrays else the report will be very ugly. </b>
    * @param output_file the file representing the report.
    * @param parameters the covering arrays whose report must be produced.
    * @param headers additional headers which you wish to attach.
    * @param values the values for the additional headers.
    * @see #sort(CoveringArrayParameters)
    * @see #parseAndSort(String)
    */
    public static void prepareReport(File output_file, CoveringArrayParameters parameters[], String headers[], String values[][])
    {
    PrintWriter report_writer;
    RowFetch fetcher;
    int old_t;
    int old_v;

        try
        {
        report_writer = new PrintWriter(new BufferedWriter(new FileWriter(output_file, false)));
        old_t = -1;
        old_v = -1;
        
            for(int i=0;i<parameters.length;i++)
            {
                /*
                * Put a new header whenever either t,v change so that
                * we can have canonical formats of representation.
                */
                if(old_t != parameters[i].t || old_v != parameters[i].v)
                {
                    report_writer.println();
                    report_writer.print("CA(" + parameters[i].t + ",k," + parameters[i].v + ")" + SEPERATOR);
                    report_writer.print("BEST_KNOWN" + SEPERATOR +"NIST");

                    if(headers != null)
                    for(int j=0;j<headers.length;j++)
                    report_writer.print(SEPERATOR + headers[j]);

                report_writer.println();
                old_t = parameters[i].t;
                old_v = parameters[i].v;
                }

            fetcher = new RowFetch(parameters[i]);
            
                report_writer.print(parameters[i].k + SEPERATOR);
                report_writer.print(fetcher.fetch(RowFetch.Authority.COLBOURN) + SEPERATOR);
                report_writer.print(fetcher.fetch(RowFetch.Authority.NIST));           

                if(headers != null)
                for(int j=0;j<headers.length;j++)
                {
                report_writer.print(SEPERATOR);
                
                    try
                    {
                        report_writer.print(values[i][j]);
                    }
                    catch(Exception e)
                    {
                        report_writer.print("-");
                    }
                }     

            report_writer.println();
            }
            
        report_writer.close();
        }
        catch(Exception e)
        {
            System.out.println("[WARNING]  There was an error generating the report.. Exiting report generation");
        }  
    }
    
    /**
    * Gets the covering arrays in a sorted order from a directory.
    * All covering arrays are processed and returned.
    * @param path the path of the covering array directory.
    * @return an array of covering array parameters in sorted order.
    */
    public static CoveringArrayParameters[] parseAndSort(String path)
    {
    File covering_arrays[];
    CoveringArrayParser parser;
    LinkedList<CoveringArrayParameters> ca;
    CoveringArrayParameters parameters[];
    
        covering_arrays = FileUtils.getFiles(path);
        ca = new LinkedList<CoveringArrayParameters>();
        
        for(int i=0;i<covering_arrays.length;i++)
        {
        parser = new CoveringArrayParser(covering_arrays[i]);
            
          if(parser.parse())
          ca.add(parser.getParameters());
          else
          System.out.println("[WARNING] " + covering_arrays[i].getName() + " Unrecognized format");
          
        }
    
        parameters = new CoveringArrayParameters[ca.size()];
        ca.toArray(parameters);
        sort(parameters);

    return parameters;
    }
    
    /**
    * Sorts the parameters in the increasing order of either t,k,v as
    * specified by index.
    * @param parameters The covering array parameters to be sorted.
    */
    private static void sort(CoveringArrayParameters parameters[])
    {
    CoveringArrayParameters temp;
    int value_1;
    int value_2;
    
        for(int i=0;i<parameters.length-1;i++)
        {        
            for(int j=i+1;j<parameters.length;j++)
            {
                 if(parameters[j].t < parameters[i].t)  //this ladder could be put into one if-loop with OR seperating the conditions of each loop.
                 swap(i,j,parameters);
            else if(parameters[i].t == parameters[j].t && parameters[j].v < parameters[i].v)
                 swap(i,j,parameters);
            else if(parameters[i].t == parameters[j].t && parameters[j].v == parameters[i].v && parameters[j].k < parameters[i].k)
                 swap(i,j,parameters);
            }
        }
    }

    /**
    * Swaps the values in the array.
    * @param i the first index to be swapped.
    * @param j the second index to be swapped.
    * @param parameters the array whose values must be swapped.
    */
    private static void swap(int i, int j, CoveringArrayParameters parameters[])
    {
    CoveringArrayParameters temp;

        temp = parameters[i];
        parameters[i] = parameters[j];
        parameters[j] = temp;
    }

}
