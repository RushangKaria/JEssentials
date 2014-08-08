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
* File : GeneralFileReader.java
*           General File Reader to avoid the extra typing
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

package com.util.file.readers;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

/**
* A Lazy utility to read files in 
* general. 
* <p>
* This class provides a wrapper over the
* @see java.io.FileReader
* <p>
* This class is inefficient since it involves
* copying arbitrarily long Strings.
*/
public class GeneralFileReader
{
    private File SOURCE_FILE;
    private BufferedReader file_reader;
    private String line;
    
    public GeneralFileReader(File SOURCE_FILE)
    {
        this.SOURCE_FILE = SOURCE_FILE;
    }

    public GeneralFileReader(String FILE_PATH)
    {
        this.SOURCE_FILE = new File(FILE_PATH);
    }

    public boolean open()
    {
        if(!this.SOURCE_FILE.exists() || this.SOURCE_FILE.isDirectory())
        return false;

        try
        {
        this.file_reader = new BufferedReader(new FileReader(this.SOURCE_FILE));
        }
        catch(Exception e)
        {
        return false;
        }

    return true;
    }

    public void close()
    {
        this.file_reader.close();
    }

    public boolean hasNext()
    {
        this.line = this.file_reader.readLine();

    return this.line == null;
    }

    public String readLine()
    {
    return this.line;   
    }
}
