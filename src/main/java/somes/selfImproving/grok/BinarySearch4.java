package somes.selfImproving.grok;

/**
 * Created by anme on 31.10.18.
 */
public class BinarySearch4 {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 86, 259, 456, 999, 2856};
        if(binarySearch(arr, 999) != 5) throw new RuntimeException("WRONG");
        if(binarySearch(arr, 998) > 0 ) throw new RuntimeException("WRONG");
    }

    private static int binarySearch(int[] arr, int whatWeSearch) {
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
