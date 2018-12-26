package somes.selfImproving.grok;

/**
 * Created by anme on 17.10.18.
 */
public class BinarySearch3 {
    public static void main(String[] args) {
        int k = binarySearch(new int[]{1, 5, 7, 8, 77}, 8);
        if(k != 3) throw new RuntimeException();
    }

    private static int binarySearch(int[] array, int toFind) {
        int low = 0;
        int high = array.length-1;
        int currentPoint;
        while(low<=high) {
            currentPoint = (high-low)/2;
            if(array[currentPoint] == toFind) {
                return currentPoint;
            }
            if(array[currentPoint] < toFind) {
                low = currentPoint +1;
            } else {
                high = currentPoint -1;
            }
        }
        return -1;
    }
}
