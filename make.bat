:: #########################################################################
:: #                       CLRS 3rd Edition v1.0.0                         #
:: #                        MAKEFILE FOR WINDOWS                           #
:: #                                                                       #
:: #                                                                       #
:: # Instructions to build:-                                               #
:: #    1. Make sure you have the PATH variable set to include javac       #
:: #    2. In a shell pointing to the root source type make.bat            #
:: #                                                                       #
:: #########################################################################
::
:: Description : This software is an implementation of the algorithms 
::               in the Introduction to Algorithms book by
::               Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein.
:: 
::               Information about the book can be found here :-
::               http://en.wikipedia.org/wiki/Introduction_to_Algorithms
::               http://mitpress.mit.edu/books/introduction-algorithms
::
::
:: Author :      Rushang Vinod Vandana Karia
::                   - Rushang.Karia@asu.edu
::                   - 4806283130
::                   - github.com/RushangKaria
::                   - Arizona State University
::
:: Author :      Shrijal Pravin Gandhi
::                   - Shrijal.Gandhi@asu.edu
::                   - 4806282324
::                   - github.com/ShrijalGandhi
::                   - Arizona State University
::
:: File : make.bat
::          Makefile for building the library and samples on Windows
::          NOTE: This makefile does not consider time stamps of files
::                so compiling the library takes a really long time. Will be fixed soon.
::
::  
::    Copyright (C) 2014  Rushang Karia, Shrijal Gandhi
::
::    This program is free software: you can redistribute it and/or modify
::    it under the terms of the GNU General Public License as published by
::    the Free Software Foundation, either version 3 of the License, or
::    (at your option) any later version.
::
::    This program is distributed in the hope that it will be useful,
::    but WITHOUT ANY WARRANTY; without even the implied warranty of
::    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
::    GNU General Public License for more details.
::
::    You should have received a copy of the GNU General Public License
::    along with this program.  If not, see <http://www.gnu.org/licenses/>.
::

@echo off

IF "%1%"=="clean" (
DEL /S *.class
goto :EOF
) ELSE IF "%1%"=="docs" (
    IF "%2%"=="library" (
    javadoc -private -d docs/ -link http://docs.oracle.com/javase/7/docs/api/ -subpackages com
    ) ELSE IF "%2%"=="samples" (
    javadoc -private -d docs/ -link http://docs.oracle.com/javase/7/docs/api/ -subpackages samples
    )
goto :EOF
)

:: COMPILE THE UTIL LIBRARY FIRST
javac com/util/exception/*.java
javac com/util/time/*.java
javac com/util/generators/*.java
javac com/util/printers/*.java
javac com/util/file/*.java
javac com/util/file/writers/*.java
javac com/util/statistics/*.java
javac com/util/arrays/*.java
javac com/util/hash/*.java
javac -cp jars/Javamail.jar com/util/net/*.java

:: COMPILE THE MATH LIBRARY
javac com/math/*.java
javac com/math/combinations/*.java

:: COMPILE THE GRAPH LIBRARY
javac com/graph/*.java
javac com/graph/readers/*.java
javac com/graph/coloring/*.java

:: COMPILE THE CLRS LIBRARY 
javac com/clrs/*.java
javac com/clrs/sorting/*.java

:: COMPILE THE COVERING ARRAY LIBRARY
javac com/coveringarray/*.java
javac com/coveringarray/generators/*.java
javac com/coveringarray/parsers/*.java

:: PACK THE LIBRARY INTO A JAR FILE
jar cf jars/clrs_lib.jar com

:: COMPILE ALL SAMPLES
javac samples/sorting/*.java
javac samples/exception/*.java
javac samples/printers/*.java
javac samples/math/combinations/*.java
javac samples/coveringarray/generators/*.java
javac samples/coveringarray/parsers/*.java
javac samples/util/net/*.java
javac samples/util/statistics/*.java
javac samples/util/file/*.java
javac samples/graph/readers/*.java
javac samples/graph/coloring/*.java

:: PACK SAMPLES INTO A JAR FILE
jar cf jars/samples.jar samples


