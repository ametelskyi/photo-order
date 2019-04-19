package somes.selfImproving.grok.kata.binarySearch;

/**
 * TODO Created by anme on 08.11.18. (SPENT 10 minutes)
 */
public class BinarySearch5 implements IBinarySearch {
    public int binarySearch(int[] arr, int whatWantToFind) {
        int low = 0;
        int high = arr.length-1;
        while(low <= high) {
            int currIndex= (high - low) / 2 + low;
            int currVal = arr[currIndex];
            if(currVal == whatWantToFind) {
                return currIndex;
            } else if(whatWantToFind > currVal) {
                low = currIndex +1;
            } else {
                high = currIndex -1;
            }
        }
        return -1;
    }
}
