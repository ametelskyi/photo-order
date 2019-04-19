package somes.selfImproving.grok.kata.binarySearch;

public class BinarySearch8 implements IBinarySearch {
    @Override
    public int binarySearch(int[] arr, int whatToFind) {
        int low = 0;
        int high = arr.length -1;
        while (low < high) {
            int middleIndex = (high - low) / 2 + low;
            int middleValue = arr[middleIndex];
            if(middleValue == whatToFind) {
                return middleIndex;
            } else if(whatToFind < middleValue) {
                high = middleIndex -1;
            } else {
                low = middleIndex + 1;
            }
        }
        return -1;
    }
}
