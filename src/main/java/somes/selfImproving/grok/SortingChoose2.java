package somes.selfImproving.grok;

/**
 * Created by anme on 15.11.18.
 */
public class SortingChoose2 {
    public static void main(String[] args) {
        int arr[] = new int[]{34, 56, 3, 45, 15};

        int[] sorted = sortChoose(arr);
        if(sorted[0]!= 56) throw new RuntimeException("no");
        if(sorted[4]!= 3) throw new RuntimeException("no");
    }

    private static int[] sortChoose(int[] arr) {
        int length = arr.length;
        int[] clone = new int[length];
        System.arraycopy(arr, 0, clone, 0, length);
        int[] sorted = new int[length];

        for (int i = 0; i < length; i++) {
            int max = Integer.MIN_VALUE;
            int maxPosition = -1;
            for (int j = 0; j < length; j++) {
                if(clone[j] > max) {
                    max = clone[j];
                    maxPosition = j;
                }
            }
            sorted[i] = max;
            clone[maxPosition] = Integer.MIN_VALUE;
        }

        return sorted;
    }


}
