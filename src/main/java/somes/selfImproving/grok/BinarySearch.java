package somes.selfImproving.grok;


/**
 * Created by anme on 02.08.18.
 */
public class BinarySearch {

    public static void main(String[] args) {


        int[] arr = new int[]{51,52,53,54,55,56,57,58};

        int whatWeWantToFind = 57;
        int index = binarySearch(arr, whatWeWantToFind);

        System.out.println(index);
        if(index != 6) {
            throw new RuntimeException("!!!");
        }
    }

    private static int binarySearch(int[] arr, int whatWeWantToFind) {
        int lowBoundary = 0;
        int highBoundary = arr.length -1;

        while (lowBoundary <= highBoundary) {
            int middle = (highBoundary-lowBoundary)/2 + lowBoundary;
            int midVal = arr[middle];

            if(whatWeWantToFind < midVal) {
                highBoundary = middle-1;
            } else if (whatWeWantToFind > midVal) {
                lowBoundary = middle +1;
            } else {
                return middle;
            }
        }

        return -1;
    }
}
