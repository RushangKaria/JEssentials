
all:
	# COMPILE THE UTIL LIBRARY FIRST
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

	# COMPILE THE MATH LIBRARY
	javac com/math/*.java
	javac com/math/combinations/*.java

	# COMPILE THE GRAPH LIBRARY
	javac com/graph/*.java
	javac com/graph/readers/*.java
	javac com/graph/coloring/*.java

	# COMPILE THE CLRS LIBRARY 
	javac com/clrs/*.java
	javac com/clrs/sorting/*.java

	# COMPILE THE COVERING ARRAY LIBRARY
	javac com/coveringarray/*.java
	javac com/coveringarray/generators/*.java
	javac com/coveringarray/parsers/*.java
	javac com/coveringarray/verifier/*.java
	javac com/coveringarray/report/*.java

	# PACK THE LIBRARY INTO A jar FILE
	jar cf jars/clrs_lib.jar com

	# COMPILE ALL SAMPLES
	javac samples/sorting/*.java
	javac samples/exception/*.java
	javac samples/printers/*.java
	javac samples/math/combinations/*.java
	javac samples/coveringarray/generators/*.java
	javac samples/coveringarray/parsers/*.java
	javac samples/coveringarray/verifier/*.java
	javac samples/coveringarray/report/*.java
	javac samples/util/net/*.java
	javac samples/util/statistics/*.java
	javac samples/util/file/*.java
	javac samples/graph/readers/*.java
	javac samples/graph/coloring/*.java

	# PACK SAMPLES INTO A jar FILE
	jar cf jars/samples.jar samples    
