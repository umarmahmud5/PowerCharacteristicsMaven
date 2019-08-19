/*
 * Licensed in Public Domain by IoTica Research Lab.
 * Foundation University Islamabad, Rawalpindi Campus (FURC)
 * Rawalpindi, Pakistan
 */
package com.iotica.powercharacteristics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 *
 * @author Umar Mahmud
 */
public class PowerCharacteristics{

    private final Runtime runTime;
    private final WriteInFile fileWriter;
    private InsertionSort insertionSorter;
    private int[] sortingArray;
    private Random randomNumber;
    private Process processInfo;
    private BufferedReader stdInput;
    private long beforeUsedMem;
    private long afterUsedMem;

    public PowerCharacteristics() {
        fileWriter = new WriteInFile("Output File.txt");
        runTime = Runtime.getRuntime();
        fileWriter.writeInFile("Available processors cores are: " + Runtime.getRuntime().availableProcessors());
        fileWriter.writeInFile("Available memory bytes avilable to JVM are: " + Runtime.getRuntime().maxMemory());
        executeSort(100000,40);
        fileWriter.closeWriting();
    }

    private void executeSort(int startSize, int repNumber) {
        randomNumber = new Random();
        int arraySize;
        for (int i = 0; i < repNumber; i++) {
            fileWriter.writeInFile("\n");
            fileWriter.writeInFile("CONDUCTING TEST NUMBER " + (i + 1));
            fileWriter.writeInFile("\n");
            arraySize = startSize * (i + 1);
            System.out.println(arraySize);
            fileWriter.writeInFile("Size of array is: " + arraySize);
            sortingArray = new int[arraySize];
            randomlyFillData();
            fileWriter.writeInFile("START time of algorithm in nanoseconds is: " + System.nanoTime());
            beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
            fileWriter.writeInFile("START memory of algorithm in bytes is: " + beforeUsedMem);
            fileWriter.writeInFile("START state of battery");
            try {
                processInfo = runTime.exec("upower -i /org/freedesktop/UPower/devices/battery_BAT0");
                stdInput = new BufferedReader(new InputStreamReader(processInfo.getInputStream()));
                String line;
                while ((line = stdInput.readLine()) != null) {
                    fileWriter.writeInFile(line);
                }
            } catch (IOException e) {
                System.err.println("Error from reading console");
            }
            insertionSorter = new InsertionSort(sortingArray);
            fileWriter.writeInFile("END time of algorithm in nanoseconds is: " + System.nanoTime());
            afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
            fileWriter.writeInFile("END memory of algorithm in bytes is: " + afterUsedMem);
            fileWriter.writeInFile("END state of battery");
            try {
                processInfo = runTime.exec("upower -i /org/freedesktop/UPower/devices/battery_BAT0");
                stdInput = new BufferedReader(new InputStreamReader(processInfo.getInputStream()));
                String line;
                while ((line = stdInput.readLine()) != null) {
                    fileWriter.writeInFile(line);
                }
            } catch (IOException e) {
                System.err.println("Error from reading console");
            }
        }
    }

    private void randomlyFillData() {
        for (int i = 0; i < sortingArray.length; i++) {
            sortingArray[i] = randomNumber.nextInt();
        }
    }

    public static void main(String[] args) {
        PowerCharacteristics powerAnalysisObject = new PowerCharacteristics();
    }

}

