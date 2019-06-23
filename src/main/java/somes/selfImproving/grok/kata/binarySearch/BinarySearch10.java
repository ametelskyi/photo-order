package somes.selfImproving.grok.kata.binarySearch;

public class BinarySearch10 implements IBinarySearch {
    @Override
    public int binarySearch(int[] arr, int whatToFind) {
        //goal: find index of element, or say that it is not exist
        //intention: split by middle array, check middle and if not matched than search only in
        // one part in same way
        //plan: use boundaries (0 and total count of elements)
        // in loop increase low boundary and decrease high by switching them in new part array by cutting
        // not included middle index
        // stop will be when find element or (two possible cases can be -- at the end 1 or 2 elements left,
        // if 1 -- boundaries are equal it is okay for next step, after high will be less that low;
        // if we have 2 elements -- can be 1 element in one case or high less then low which indicate as end) --
        // so finally we have low more than high as exit case loop

        int low = 0;
        int high = arr.length;
        while(low <= high) {
            int middleIndex = (high + low) / 2;
            int middleVal = arr[middleIndex];
            if(middleVal == whatToFind) {
                return middleIndex;
            } else if(middleVal > whatToFind) {
                high = middleIndex -1;
            } else {
                low = middleIndex + 1;
            }
        }
        return -1;
    }
}
