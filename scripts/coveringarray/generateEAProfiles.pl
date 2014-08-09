
use strict;
use warnings;
use File::Copy;

my ROOT="../../com"

open FILES, "dir /b /aa /s |",$ROOT or die;
while ( <FILES> )
{ 
    my( $dir, $file ) = /(.+)\\(.+)/;
    defined $file or next; # no path separator
    print "$dir => $file\n";
}

close FILES;

