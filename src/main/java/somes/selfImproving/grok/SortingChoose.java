package somes.selfImproving.grok;

import java.util.Arrays;

/**
 * Created by anme on 29.08.18.
 */
public class SortingChoose {
    public static void main(String[] args) {
        int[] arr = new int[]{234, 23, -555, 113, 56, -56, 2, 55};

        int[] sorted = sortIt(arr);

        System.out.println(Arrays.toString(sorted));
    }

    private static int[] sortIt(int[] arr) {
        int[] sorted = new int[arr.length];
        while(arr.length > 0) {
            int smallestIndex = findSmallestIndex(arr);
            sorted[sorted.length - arr.length] = arr[smallestIndex];
            int[] arrNew = new int[arr.length-1];
            System.arraycopy(arr, 0, arrNew, 0, smallestIndex);
            System.arraycopy(arr, smallestIndex+1, arrNew, smallestIndex, arr.length-smallestIndex-1);
            arr = arrNew;
        }

        return sorted;
    }

    private static int findSmallestIndex(int[] source) {
        if(source.length == 0) return -1;
        int smallValue = source[0];
        int smallIndex = 0;
        for (int i = 0; i < source.length; i++) {
            if(source[i] < smallValue) {
                smallIndex = i;
                smallValue = source[i];
            }
        }
        return smallIndex;
    }
}
