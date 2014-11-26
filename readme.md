BoxMaker
========

**DEPRECATED!**
Maintaining this old java code, and running it on a server, was becoming a pain in the rear.  I rewrote it in Python. Find that code at http://github.com/rahulbot/boxmaker-website/ .  *Use this code at your own risk!*


What is BoxMaker?
-----------------

BoxMaker is a simple little application that can generate the outlines for a box to be out of some material with a cutting device (ideally using wood/acrylic on a lasercutter!).  You tell it the dimensions of the box (width, height, depth, material width), and it generates a PDF with the outlines for the six sides of the box.

The edges are notched, so it snaps together well.  You can import the PDF it creates into CorelDraw (or any other standard drawing program) and manipulate it further there (probably rearrange the pieces to fit on the shape of the material you have at hand).

How to Compile
--------------

BoxMaker is a Java program built with the Ant system.  You will need to install both before you can compile the code.  Once you have that installed, just run ant in this directory and it will create a BOX.jar with the compiled code for you.  BoxMaker makes use of the iText PDF library for PDF generation.

**Compile**

```
ant
```

or 

```
javac -cp "lib/iText-2.1.4.jar" src/com/rahulbotics/*.java src/com/rahulbotics/boxmaker/*.java
jar xvf lib/iText-2.1.4.jar 
mv com/lowagie/ src/com/
cd src
jar cvf ../BOX.jar .
```


**Generate API Documentation**

```
ant doc.javadoc
```

**Clean**

```
ant clean
```

How to Use
----------

**To quickly test it, just run the `render-box.sh` script.**

You can run BoxMaker from the command line like this (all the numbers are specified in millimeteres):

```
java -cp BOX.jar com.rahulbotics.boxmaker.CommandLine [file_to_output] [width] [height] [depth] [material_thickness] [cut_width] [notch_length] [draw_bounding_box]
```

For instance, the `render-box` script does this to draw a 4" x 5" x 6" box:

```
java -cp BOX.jar com.rahulbotics.boxmaker.CommandLine test-box.pdf 101.6 127 152.4 4.7625 0 11.90625 false
```

**width, height, depth**
The outer dimensions of the box. The inner dimensions of the box can be deterimined by subtracting (2*materialthickness).

**material depth**
The thickness of the material. This is needed in order to properly size the notches.
    
**cut width**
The width of material removed by the laser. We assume that the beam cuts symmetrically around the cutting line but that it has some finite width. For example, if a non-zero cut width is specified, the notches will be slightly exaggerated so that the notches fit more snugly together. A value of zero is usually safe but loose-fitting, and mimics the behavior of versions of BoxMaker prior to v1.3. The cut width must be experimentally determined, as it is a function of the material, laser speed/power parameters, etc. Reasonable values for 1/8" acrylic are .004 inches to .008 inches. If the cut width is set too large, the box will not fit together.

**notch length**
The length of each notch. If you don't care, just leave the auto checkbox as is.  A good rule of thumb is two or three times the material depth. Note that this parameter is used as a suggestion only-- the program will adjust the notch length so that the geometry of the box comes out correctly.

**bounding box**
Whether to draw a bounding box around the pieces or not.  This can be helpful when importing into some CAD programs that don't respect dimentions.  Set to `true` if you want to get a box around the entire outputted PDF.

License
-------

BoxMaker is licensed as Open Source Software under the MIT license.  The BoxMaker source code is copyright (c) 2002 Rahul Bhargava.
