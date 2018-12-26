package somes.selfImproving.grok;

/**
 * TODO Created by anme on 08.11.18. (SPENT 10 minutes)
 */
public class BinarySearch5 {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 6, 7, 32, 45, 674, 7453};
        if(binarySearch(arr, 45) != 4) throw new RuntimeException("ERROR");
        if(binarySearch(arr, 55) >0 ) throw new RuntimeException("ERROR");
        System.out.println("SUCCESS");
    }

    private static int binarySearch(int[] arr, int whatWantToFind) {
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
