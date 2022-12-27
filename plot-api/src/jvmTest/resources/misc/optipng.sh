#!/bin/sh

DIR=`dirname "$0"`
#OPTS="-backup -f0-5 -zm8  -zs0 -i0 -nx -zc9"
#
OPTS="-f0-5 -zs0 -zc6 -zm8 -i0 -nx -force "
# -zs0 estrategy default
# -i0 don't interlace
# -zm8 memory level 8 (default)
# -zc6 zlib compression level 6
$DIR/optipng.exe $OPTS $1

# or better: 
#  find . -name '*.png' -exec echo "optipng.exe -f0-5 -zs0 -zc6 -zm8 -i0 -nx -force " '{}' \;
