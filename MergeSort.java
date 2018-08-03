/*
 * CSE 205: 17566 / M W 4:35PM - 5:50PM 
 * Assignment: Assignment 6
 * Authors: Randall Knutson 1212366088
 * Description: Class implementing a Mergesort given an array of Vehicles.
 */
public class MergeSort {
	private Vehicle[] array;
    private Vehicle[] temp;
    private int length;
 
    //sets up merge sort
    public void sort(Vehicle inputArr[]) {
        this.array = inputArr;
        this.length = inputArr.length;
        this.temp = new Vehicle[length];
        mSortHelper(0, length - 1);
    }
 
    //helps in mergesorting
    private void mSortHelper(int lowerIndex, int higherIndex) {
         
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            
            mSortHelper(lowerIndex, middle);
            
            mSortHelper(middle + 1, higherIndex);
            
            merge(lowerIndex, middle, higherIndex);
        }
    }
 
    //merges two sorted arrays into one sorted array
    private void merge(int lowerIndex, int middle, int higherIndex) {
 
        for (int i = lowerIndex; i <= higherIndex; i++) {
            temp[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if ((temp[i] != null && temp[j] != null) && temp[i].getTime() <= temp[j].getTime()) {
                array[k] = temp[i];
                i++;
            } else {
                array[k] = temp[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = temp[i];
            k++;
            i++;
        }
 
    }
}

