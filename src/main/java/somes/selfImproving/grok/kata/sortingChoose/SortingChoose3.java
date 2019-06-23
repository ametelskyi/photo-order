package somes.selfImproving.grok.kata.sortingChoose;

public class SortingChoose3 implements ISortingChoose {
    @Override
    public int[] sortChoose(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int current = arr[i];
            int lowestVal = current;
            int lowestIndex = -1;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[j] < lowestVal) {
                    lowestVal = arr[j];
                    lowestIndex = j;
                }
            }
            if(lowestIndex > 0) {
                arr[i] = lowestVal;
                arr[lowestIndex] = current;
            }
        }

        return arr;
    }
}
