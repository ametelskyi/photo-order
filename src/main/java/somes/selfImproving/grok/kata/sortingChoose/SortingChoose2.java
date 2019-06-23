package somes.selfImproving.grok.kata.sortingChoose;

/** аа3
 * Created by anme on 14.02.19.
 */
public class SortingChoose2 implements ISortingChoose {
    @Override
    public int[] sortChoose(int[] arr) {
        //loop for every index form first to last
        //   subloop for every index -- find smallest element in the rest part of array
        //      replace current element and smallest
        for (int i = 0; i < arr.length-1; i++) {
            int current = arr[i];
            int smallest = current;
            int smallestIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j] < smallest) {
                    smallest = arr[j];
                    smallestIndex = j;
                }
            }
            if(smallestIndex != i) {
                arr[i] = smallest;
                arr[smallestIndex] = current;
            }
        }

        return arr;
    }
}
