package somes.selfImproving.grok.kata.binarySearch;

/**
 * Created by anme on 14.02.19.
 */
public class BinarySearch6 implements IBinarySearch {
    @Override
    public int binarySearch(int[] arr, int whatToFind) {
        int low = 0;
        int high = arr.length-1;
        while(low<=high) {
            int middleIndex = (high-low)/2 + low;
            int middleVal = arr[middleIndex];
            if(middleVal == whatToFind) {
                return middleIndex;
            } else if(middleVal > whatToFind) {
                high = middleIndex -1;
            } else {
                low = middleIndex +1;
            }
        }
        return -1;
    }
}
