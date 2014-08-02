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
* File : ArrayWriter.java
*           Utility for writing arrays to files
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

package com.util.file.writers;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
/**
* Writes arrays to files
*/
public class ArrayWriter
{
    private String FILE_NAME;
    private boolean APPEND_MODE;
    private PrintWriter file_writer;

    public ArrayWriter(String FILE_NAME)
    {
        this.FILE_NAME = FILE_NAME;
        this.APPEND_MODE = false;

            Runtime.getRuntime().addShutdownHook(
        
                                                new Thread()
                                                {
                                                    public void run()
                                                    {
                                                        if(file_writer != null)
                                                        file_writer.close();
                                                    }
                                                }   
                                                );
    }

    public ArrayWriter(String FILE_NAME,boolean APPEND_MODE)
    {
        this.FILE_NAME = FILE_NAME;
        this.APPEND_MODE = APPEND_MODE;

            Runtime.getRuntime().addShutdownHook(
        
                                                new Thread()
                                                {
                                                    public void run()
                                                    {
                                                        if(file_writer != null)
                                                        file_writer.close();
                                                    }
                                                }   
                                                );        
    }

    public boolean open()
    {
        try
        {
            File file = new File(this.FILE_NAME);
            if(file.isDirectory())
            return false;
        
        this.file_writer = new PrintWriter(new BufferedWriter(new FileWriter(file,this.APPEND_MODE)));
        }
        catch(Exception e)
        {
        this.file_writer = null;
        return false;
        }

    return true;
    }

    public boolean close()
    {
        try
        {
            this.file_writer.close();
            this.file_writer = null;
        }
        catch(Exception e)
        {
            return false;
        }
        
    return true;
    }

    public boolean write(int array[],String SEPERATOR)
    {
        try
        {
            for(int i=0;i<array.length-1;i++)
            this.file_writer.print(array[i] + SEPERATOR);

            this.file_writer.println(array[array.length-1]);
        }
        catch(Exception e)
        {
        return false;
        }

    return true;
    }
}
