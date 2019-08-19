/*
 * Licensed in Public Domain by IoTica Research Lab.
 * Foundation University Islamabad, Rawalpindi Campus (FURC)
 * Rawalpindi, Pakistan
 */
package com.iotica.powercharacteristics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author Umar
 */
public class WriteInFile {

    private PrintWriter printInFile;
    private File outputFile;

    protected WriteInFile(String fileName) {
        try {            
            outputFile = new File(fileName);
            printInFile = new PrintWriter(outputFile);
            printInFile.println("***********************************");
            printInFile.println("***********************************");
            printInFile.println();
            printInFile.println("STARTING TESTS");
        } catch (FileNotFoundException e) {
            System.err.println("File not created");
        }
    }
    protected void closeWriting(){
        printInFile.close();
    }
    protected void writeInFile(String dataToWrite) {
        printInFile.println(dataToWrite);
        printInFile.flush();
    }
}
