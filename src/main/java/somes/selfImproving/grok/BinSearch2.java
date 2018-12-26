package somes.selfImproving.grok;

/**
 * Created by anme on 08.08.18.
 */
public class BinSearch2 {
    public static void main(String[] args) {
        int[] arr = new int[]{34, 56, 78, 94, 56};
        if(binSearch(arr, 78) != 2) {
            throw new RuntimeException("WRONG");
        }
        if(binSearch(arr, 79) != -1) {
            throw new RuntimeException("WRONG");
        }
    }

    private static int binSearch(int[] arr, int wantToFind) {
        int low = 0;
        int high = arr.length -1;

        while(high >= low) {
            int middle = (high-low)/2 + low;
            int midVal = arr[middle];

            if(midVal == wantToFind) {
                return middle;
            } else if(wantToFind < middle) {
                high = middle -1;
            } else {
                low = middle+1;
            }
        }

        return -1;
    }
}
