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
* File : EmailDemo.java
*           Demo program illustrating use of the Email class
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

package samples.util.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Console;

import com.util.net.Email;


public class EmailDemo
{
    private BufferedReader input;
    private Email mailer;
    private String username;
    private String password;
    
    protected EmailDemo()
    {
    this.input = new BufferedReader(new InputStreamReader(System.in));
    }

    protected void driver()
    {
        try
        {
            System.out.print("Please enter your username : ");
            this.username = this.input.readLine();
            System.out.print("Please enter your password : ");
            this.password = readPassword();
        }
        catch(Exception e)
        {
        }
        
        this.mailer = new Email(this.username,this.password);

            if(!this.mailer.connect())
            {
                System.out.println("Incorrect username/password or no Internet connection..Exiting");
                System.exit(0);
            }
            else
            mailer.send("hotmail test","smtp-mail.outlook.com","shrijalgandhi@hotmail.com");
    }

    private String readPassword()
    {   
        Console cmd = System.console();

            if(cmd == null)
            return null;
            else
            return new String(cmd.readPassword());
    }

    public static void main(String args[])
    {
        EmailDemo demo = new EmailDemo();
        demo.driver();
    }
}
