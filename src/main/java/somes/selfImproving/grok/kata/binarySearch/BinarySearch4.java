package somes.selfImproving.grok.kata.binarySearch;

/**
 * Created by anme on 31.10.18.
 */
public class BinarySearch4 implements IBinarySearch {
    public int binarySearch(int[] arr, int whatWeSearch) {
        int low = 0;
        int high = arr.length -1;
        while(low <= high) {
            int middleIndex = (high - low)/2 + low;
            int middleElement = arr[middleIndex];
            if(middleElement == whatWeSearch) {
                return middleIndex;
            } else if(whatWeSearch < middleElement) {
                high = middleIndex -1;
            } else {
                low = middleIndex +1;
            }
        }
        return -1;
    }
}
