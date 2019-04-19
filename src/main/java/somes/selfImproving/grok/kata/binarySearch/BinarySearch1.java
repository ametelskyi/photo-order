package somes.selfImproving.grok.kata.binarySearch;


/**
 * Created by anme on 02.08.18.
 */
public class BinarySearch1 implements IBinarySearch {

    public int binarySearch(int[] arr, int whatWeWantToFind) {
        int lowBoundary = 0;
        int highBoundary = arr.length -1;

        while (lowBoundary <= highBoundary) {
            int middle = (highBoundary-lowBoundary)/2 + lowBoundary;
            int midVal = arr[middle];

            if(whatWeWantToFind < midVal) {
                highBoundary = middle-1;
            } else if (whatWeWantToFind > midVal) {
                lowBoundary = middle +1;
            } else {
                return middle;
            }
        }

        return -1;
    }
}
