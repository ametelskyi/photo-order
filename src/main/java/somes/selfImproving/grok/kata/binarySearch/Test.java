package somes.selfImproving.grok.kata.binarySearch;

/**
 * Created by anme on 18.01.19.
 */
public class Test {
    static IBinarySearch[] classesToTest = new IBinarySearch[] {
            new BinarySearch1(),
            new BinarySearch2(),
            new BinarySearch3(),
            new BinarySearch4(),
            new BinarySearch5(),
            new BinarySearch6(),
            new BinarySearch7()
            , new BinarySearch8()
            , new BinarySearch9()
            , new BinarySearch10()

    };
    static int[] ARR = new int[]{3, 5, 86, 259, 456, 999, 2856};

    public static void main(String[] args) {
        for (IBinarySearch iBinarySearch : classesToTest) {
            testOneClass(iBinarySearch);
            System.out.println(111);
        }

        System.out.println("SUCCESSFUL");

    }

    private static void testOneClass(IBinarySearch toTest) {
        System.out.println("TEST class <<" + toTest.getClass() + ">>");
        if(toTest.binarySearch(ARR, 999) != 5) throw new RuntimeException("WRONG");
        if(toTest.binarySearch(ARR, 998) > 0 ) throw new RuntimeException("WRONG");
    }

}
