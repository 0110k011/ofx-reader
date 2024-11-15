package com.ofxr;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;

public class OFXReader {

    private static final Logger logger = Logger.getLogger(OFXReader.class.getName());

    public static void main(String[] args) {
        OFXProcess processor = new OFXProcess();
        try {
            File ofxFile = new File("/home/k/Downloads/teste.ofx");
            processor.processOFX(ofxFile);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error: OFX file can`t be processed!", e);
        }
    }
}