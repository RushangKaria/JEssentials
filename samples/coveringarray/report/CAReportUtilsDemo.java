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
* File : CAReportUtilsDemo.java
*           Demo class to illustrate the use of the CAReportUtils class.
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

package samples.coveringarray.report;

import com.coveringarray.CoveringArrayParameters;
import com.coveringarray.report.CAReportUtils;

import java.io.File;

/**
* Demo class to illustrate the use of 
* #com.coveringarray.report.CAReportUtils
*/
public class CAReportUtilsDemo
{

    /**
    * Example covering arrays root directory.
    */
    private final String CA_SOURCE_PATH = "inputs/coveringarray/SortTestCA/";

    /**
    * Output file for the reports.
    */
    private final String OUTPUT_PATH = "outputs/coveringarray/reports/";
    
    /**
    * Instantiates a test object.
    */
    protected CAReportUtilsDemo()
    {
    
    }
    
    /**
    * Demonstrates use of most of the features of
    * #com.coveringarray.report.CAReportUtils.
    * @see com.coveringarray.report.CAReportUtils
    */
    protected void driver()
    {
    CoveringArrayParameters parameters[];
    
        parameters = CAReportUtils.parseAndSort(this.CA_SOURCE_PATH);
        
        testDefaultReport(parameters);
        testSpecializedReport(parameters);
    }

    /**
    * Creates a default report.
    * @param parameters the example parameters that are passed.
    */
    private void testDefaultReport(CoveringArrayParameters parameters[])
    {
    File report = new File(this.OUTPUT_PATH + "default_report.txt");

        CAReportUtils.prepareReport(report, parameters, null, null);
    }

    /**
    * Creates a specialized report.
    * @param parameters the example parameters that are passed.
    */
    private void testSpecializedReport(CoveringArrayParameters parameters[])
    {
    File report = new File(this.OUTPUT_PATH + "specialized_report.txt");
    
        String headers[] = { "TIME" };
        String values[][] = new String[parameters.length][headers.length];

        for(int i=0;i<values.length;i++)
        for(int j=0;j<values[0].length;j++)
        values[i][j] = Math.random() + " s";

        CAReportUtils.prepareReport(report, parameters, headers, values);
    }
    
    public static void main(String args[])
    {
        CAReportUtilsDemo demo = new CAReportUtilsDemo();
        demo.driver();
    }
}
