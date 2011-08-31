package com.rahulbotics.boxmaker;

import java.io.FileNotFoundException;

import com.lowagie.text.DocumentException;

/**
 * Simple wrapper to let me run the renderer from the command line
 * @author rahulb
 */
public class CommandLine {

    /**
     * Run the rendered with lots of args
     * 
     * @param args
     */
    public static void main(String[] args) {
        
        // parse it all out from the command line args 
        String filePath = args[0];
        double mmWidth = Double.parseDouble(args[1]);
        double mmHeight = Double.parseDouble(args[2]);
        double mmDepth = Double.parseDouble(args[3]);
        double mmThickness = Double.parseDouble(args[4]);
        double mmCutWidth = Double.parseDouble(args[5]);
        double mmNotchLength = Double.parseDouble(args[6]);
        boolean drawBoundingBox = Boolean.parseBoolean(args[7]);
        
        // try to render it, don't do any error handling (file won't get created)
        try {
            Renderer.render(filePath,filePath,mmWidth,mmHeight,mmDepth,
                            mmThickness,mmCutWidth,mmNotchLength,
                            drawBoundingBox,false);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR!"+e.toString());
            System.exit(0);
        } catch (DocumentException e) {
            System.out.println("ERROR!"+e.toString());
            System.exit(0);
        }
        System.exit(1);
    }

}
