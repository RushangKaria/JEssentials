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
* File : FileUtils.java
*           Utility for files
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

package com.util.file;

import java.util.LinkedList;
import java.util.Queue;
import java.io.File;

public class FileUtils
{
    public static File[] getFiles(String PATH)
    {
    LinkedList<File> file_list = new LinkedList<File>();
    LinkedList<File> directory_queue = new LinkedList<File>();
    File ROOT_FILE = new File(PATH);
    File files[];

        if(!ROOT_FILE.isDirectory())
        return null;
        else
        directory_queue.add(ROOT_FILE);

            while(directory_queue.size() > 0)
            {
            File folder = directory_queue.removeFirst();
            files = folder.listFiles();

                for(int i=0;i<files.length;i++)
                if(files[i].isDirectory())
                directory_queue.add(files[i]);
                else
                file_list.add(files[i]);              
            }           

    return file_list.toArray(new File[file_list.size()]);
    }
}
