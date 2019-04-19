package somes.selfImproving.grok.kata.binarySearch;

/**
 * Created by anme on 17.10.18.
 */
public class BinarySearch3 implements IBinarySearch {
    public int binarySearch(int[] array, int toFind) {
        int low = 0;
        int high = array.length-1;
        int currentPoint;
        while(low<=high) {
            currentPoint = (high-low)/2 + low;
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
