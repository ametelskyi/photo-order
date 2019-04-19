package somes.selfImproving.grok.kata.binarySearch;

/**
 * Created by anme on 25.02.19.
 */
public class BinarySearch7 implements IBinarySearch {
    @Override
    public int binarySearch(int[] arr, int whatToFind) {
        int low = 0;
        int high = arr.length-1;
        while(low<=high) {
            int middleIndex = (low + high) / 2;
            int middleVal = arr[middleIndex];
            if(middleVal == whatToFind) {
                return middleIndex;
            } else if(middleVal > whatToFind) {
                high = middleIndex-1;
            } else {
                low = middleIndex +1;
            }
        }

        return -1;
    }
}
