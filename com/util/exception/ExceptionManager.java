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
* File : ExceptionManager.java
*           Exception Manager Utility to capture all exceptions generated and take appropriate action
*
*    Copyright (C) 2014  Rushang Karia, Shrijal Gandhi
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*/

package com.util.exception;

/**
* This class is responsible for handling all exceptions
* and take appropriate behavior.
*
* <p> It defines log levels for each type of exception which
* can affect the log messages and handling. For example, a
* FATAL log will give a hint to exit the program.
*
* NOTE: Memory overhead used by this method can become quite
* ----- high as the exception list increases. Use only if you
*       want automated handling and have enough memory.
*/
public class ExceptionManager extends Exception
{
    public static final int ERR_NUM     = -1;
    public static final int ERR_FATAL   = -99;
    /**
    * Exception table with log levels
    */
    private static final Object EXCEPTION_LIST[][] =    { 
                                                            { new NumberFormatException(),  "WARNING",ERR_NUM},
                                                            { new ArrayIndexOutOfBoundsException(), "FATAL",ERR_FATAL},
                                                            { new ArithmeticException(), "FATAL", ERR_FATAL }
                                                        };
    /**
    * This method handles all the common exceptions 
    * arising due to the program and system limitations.
    * @param1 The type of exception in terms of its parent class @link Throwable
    * @return The return value of the error
    */
    public static int handle(Throwable exception)
    {
    
        for(int i = 0;i<EXCEPTION_LIST.length;i++)
        if((EXCEPTION_LIST[i][0].getClass()).equals(exception.getClass()))
        {
            if(EXCEPTION_LIST[i][1].equals("WARNING"))
            {
            printInfo(EXCEPTION_LIST[i][1],exception,"Results might not be correct");
            return (int)EXCEPTION_LIST[i][2];
            }

            if(EXCEPTION_LIST[i][1].equals("FATAL"))
            {
            printInfo(EXCEPTION_LIST[i][1],exception,"Cannot move forward with computation...");
            System.out.println("[PROGRAM TERMINATED]");
            System.exit(0);
            }
        }
        
    printInfo("[FATAL]",exception,"Unknown Exception");
    System.out.println("[PROGRAM TERMINATED]");
    System.exit(0);
    return ERR_FATAL;     //This statement will never be reached
    }

    /**
    * This method prints a detailed message
    * about the exception which is more useful
    * than the default JAVA printStackTrace()
    */     
    public static void printInfo(Object log_level, Throwable exception, String message)
    {
    StackTraceElement trace[] = exception.getStackTrace();
    String trace_data = trace[0].toString();
    String package_trace = trace_data.split("\\(")[0];
    
        System.out.println("[" + log_level + "] : " + message );
        System.out.println("- NAME      = " + exception.getClass().toString().replaceAll("class ",""));
        System.out.println("- CAUSE     = " + exception.getMessage());
        System.out.println("- METHOD    = " + package_trace.substring(package_trace.lastIndexOf('.')+1,package_trace.length()) + "()"); 
        System.out.println("- LINE #    = " + trace_data.substring(trace_data.indexOf(':')+1,trace_data.indexOf(')')));
        System.out.println("- FILE NAME = " + trace_data.substring(trace_data.indexOf('(')+1,trace_data.indexOf(':')));
        System.out.println("- FILE PATH = " + package_trace.substring(0,package_trace.lastIndexOf('.')).replaceAll("\\.","/") +".java");

    }
}
