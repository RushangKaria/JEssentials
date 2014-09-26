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
* File : CoveringArrayVerifierDemo.java
*           Demo program to illustrate use of the CoveringArrayVerifier class
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

package samples.coveringarray.verifier;

import java.io.File;
import com.util.file.FileUtils;

import com.coveringarray.CoveringArrayParameters;
import com.coveringarray.parsers.CoveringArrayParser;
import com.coveringarray.verifier.CoveringArrayVerifier;

class CoveringArrayVerifierDemo
{
    private final String GOOD_CA_SOURCE = "inputs/coveringarray/goodCA";
    private final String BAD_CA_SOURCE = "inputs/coveringarray/badCA";

    protected CoveringArrayVerifierDemo()
    {

    }

    protected void driver()
    {
        verifyGoodCA();
        verifyBadCA();
    }

    private void verifyGoodCA()
    {
    CoveringArrayParser parser;
    CoveringArrayParameters parameters;
    CoveringArrayVerifier verifier;
    File root_file;

        root_file = new File(this.GOOD_CA_SOURCE);

            if(!root_file.exists() || !root_file.isDirectory())
            {
            System.out.println("\nGood CA source file : " + this.GOOD_CA_SOURCE + " is corrupt/does not exist");
            return;
            }

    
    System.out.println("-------------------- VERIFYING GOOD CAS --------------------");

            for(File ca : FileUtils.getFiles(this.GOOD_CA_SOURCE))
            {
            parser = new CoveringArrayParser(ca);

                if(parser.parse())
                {
                parameters = parser.getParameters();
                verifier = new CoveringArrayVerifier(parameters);

                    try
                    {
                        if(verifier.verify())
                        System.out.println(ca.getName() + "  --> GOOD");
                        else
                        System.out.println("\n" + ca.getName() + "  --> BAD");
                     }
                     catch(InterruptedException e)
                     {
                        System.out.println("The verification thread was interrupted");
                     }
                }
            }        

    System.out.println("------------------------------------------------------------");
    }

    private void verifyBadCA()
    {
    CoveringArrayParser parser;
    CoveringArrayParameters parameters;
    CoveringArrayVerifier verifier;
    File root_file;

        root_file = new File(this.BAD_CA_SOURCE);

            if(!root_file.exists() || !root_file.isDirectory())
            {
            System.out.println("Bad CA source file : " + this.BAD_CA_SOURCE + " is corrupt/does not exist");
            return;
            }

    
    System.out.println("-------------------- VERIFYING BAD  CAS --------------------");

            for(File ca : FileUtils.getFiles(this.BAD_CA_SOURCE))
            {
            parser = new CoveringArrayParser(ca);

                if(parser.parse())
                {
                parameters = parser.getParameters();
                verifier = new CoveringArrayVerifier(parameters);

                    try
                    {
                        if(verifier.verify())
                        System.out.println(ca.getName() + "  --> GOOD");
                        else
                        System.out.println("\n" + ca.getName() + "  --> BAD");
                     }
                     catch(InterruptedException e)
                     {
                        System.out.println("The verification thread was interrupted");
                     }
                }
            }        

    System.out.println("------------------------------------------------------------");
    }

    public static void main(String args[])
    {
        CoveringArrayVerifierDemo demo = new CoveringArrayVerifierDemo();
        demo.driver();
    }








    
}
