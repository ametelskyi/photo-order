package somes.selfImproving.grok.kata.binarySearch;

public class BinarySearch9 implements IBinarySearch {
    @Override
    public int binarySearch(int[] arr, int whatToFind) {
        /*
        -- set initial boundaries
        -- run loop until low boundary less or equals than high
        --      check if found, if not shift corresponding boundary
         */

        int low = 0;
        int high = arr.length -1;
        while(low <= high) {
            int midIndex = (high - low)/2 + low;
            int midVal = arr[midIndex];
            if(midVal == whatToFind) {
                return midIndex;
            } else if(midVal > whatToFind) {
                high = midIndex -1;
            } else {
                low = midIndex +1;
            }
        }

        return -1;
    }
}
