package somes.selfImproving.grok.kata.binarySearch;

/**
 * Created by anme on 08.08.18.
 */
public class BinarySearch2  implements IBinarySearch {
    public int binarySearch(int[] arr, int wantToFind) {
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
