#!/usr/bin/perl

# if file is not PNG but ends in .png , renames it to .bad

my $filename = $ARGV[0];
open FILE, "<:raw",  $filename or die "Couldn't open $filename $!";
my $rr;
read(FILE, $rr, 4) ;
$png = $rr eq "\x89PNG";
#print "[$rr png=$png]\n";
close FILE;
if( lc($filename) =~ /\.png$/) {
  if(! $png) {
  	rename $filename, $filename . ".bad";
  	print STDERR "bad png $filename  renaming $filename.bad \n";
  }
}
exit ($png ? 0: 1);
