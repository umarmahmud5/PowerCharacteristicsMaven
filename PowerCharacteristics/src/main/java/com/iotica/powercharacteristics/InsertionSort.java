/*
 * Licensed in Public Domain by IoTica Research Lab.
 * Foundation University Islamabad, Rawalpindi Campus (FURC)
 * Rawalpindi, Pakistan
 */
package com.iotica.powercharacteristics;

/**
 *
 * @author Umar
 */
public class InsertionSort {
    protected InsertionSort(int sortingArray[]){
        insertionSort(sortingArray);
    }
    private void insertionSort(int sortingArray[]) {
        int key, j;
        for (int i = 1; i < sortingArray.length; i++) {
            key = sortingArray[i];
            j = i - 1;  //Move elements of arr[0..i-1], that are 
            while (j >= 0 && sortingArray[j] > key) {   //greater than key, to one position ahead 
                sortingArray[j + 1] = sortingArray[j];     //  of their current position
                j = j - 1;
            }
            sortingArray[j + 1] = key;
        }
    }
}
