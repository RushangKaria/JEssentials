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
* File : Email.java
*           Utility for writing sending and receiving mail
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

package com.util.net;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
* This class can be used to send and
* receive email.
* It relies on the JavaMail API
*/
public class Email
{
    private final String PROTOCOL      = "smtp";
    private final int    PROTOCOL_PORT = 587;

    private String host;
    private String username;
    private String password;    
    private Session session;
    private Properties connection_properties;
    private Transport transport;

    private enum Provider {gmail,hotmail};
    private Provider connection_provider;
    
    public Email(String username,String password)
    {
        this.username = username.toLowerCase();
        this.password = password;
        this.session = null;             

        String service = this.username.substring(this.username.indexOf('@')+1,this.username.lastIndexOf('.'));

        for(Provider provider : Provider.values())
            if((provider.toString()).equals(service)) 
            {
            this.connection_provider = provider;
            break;
            }
    }

    public boolean connect()
    {
    final String username = this.username;
    final String password = this.password;
    
        setProperties();
        this.session  = Session.getInstance(this.connection_properties,null);

         try
         {
         this.transport = session.getTransport(this.PROTOCOL);
         this.transport.connect(this.host,this.PROTOCOL_PORT,this.username,this.password);
         }
         catch(Exception e)
         {
            return false;
         }

    return this.transport.isConnected();
    }

    public void send(String subject,String body,String ...to)
    {
    MimeMessage email = new MimeMessage(this.session);

        try
        {
        email.setSubject(subject);

            for(int i=0;i<to.length;i++)
            email.addRecipients(Message.RecipientType.TO, to[i]);

            email.setText(body);

        this.transport.sendMessage(email,email.getAllRecipients());
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Error while processing email. Message could not be sent");
            return;
        }
    }

    private void setProperties()
    {
    this.connection_properties = new Properties();

        this.connection_properties.put("mail.smtp.starttls.enable","true");
        
        switch(this.connection_provider)
        {
        case gmail   : this.host = "smtp.gmail.com";
        break;

        case hotmail : this.host = "smtp-mail.outlook.com";
        break;
        }
    }
}

