package somes.selfImproving.grok.kata.sortingChoose;

/**
 * Created by anme on 14.02.19.
 */
public class Test {
    public static void main(String[] args) {
        ISortingChoose[] realizations = new ISortingChoose[] {
                new SortingChoose1()
                , new SortingChoose2()
                , new SortingChoose3()
        };

        for (ISortingChoose realization : realizations) {
            testIt(realization);
        }
        System.out.println("SUCCESS!!! awesome");
    }

    private static void testIt(ISortingChoose realization) {
        testFirst(realization);
        testSecond(realization);
    }

    private static void testSecond(ISortingChoose realization) {
        int[] arr = new int[]{234, 23, -555, 113, 56, -56, 2, 55};
        int[] sorted = realization.sortChoose(arr);
        if(sorted[0]!= -555) throw new RuntimeException("no");
        if(sorted[1]!= -56) throw new RuntimeException("no");
        if(sorted[7]!= 234) throw new RuntimeException("no");
    }

    private static void testFirst(ISortingChoose realization) {
        int arr[] = new int[]{34, 56, 3, 45, 15};
        int[] sorted = realization.sortChoose(arr);
        if(sorted[0]!= 3) throw new RuntimeException("no");
        if(sorted[4]!= 56) throw new RuntimeException("no");
    }
}
