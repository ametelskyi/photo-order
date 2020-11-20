package somes.selfImproving.grok.kata.dynamicProgramming.backpack;

import java.util.List;

import static java.util.Arrays.asList;

public class Test {
    public static void main(String[] args) {
        IBackpackChoose solveOne = new SolveOne();

        List<BackpackItem> items = asList(
                BackpackItem.builder().name("water").size(3).cost(10).build(),
                BackpackItem.builder().name("book").size(1).cost(3).build(),
                BackpackItem.builder().name("food").size(2).cost(9).build(),
                BackpackItem.builder().name("dress").size(2).cost(5).build(),
                BackpackItem.builder().name("camera").size(1).cost(6).build());

        int capacity = 6;
        int result = 25;

        test(solveOne, items, capacity, result);

        items = asList(
                BackpackItem.builder().name("guitar").size(1).cost(1500).build(),
                BackpackItem.builder().name("boombox").size(4).cost(3000).build(),
                BackpackItem.builder().name("laptop").size(3).cost(2000).build(),
                BackpackItem.builder().name("iphone").size(1).cost(2000).build(),
                BackpackItem.builder().name("mp3").size(1).cost(1000).build()
        );
        capacity = 4;
        result = 4500;

        test(solveOne, items, capacity, result);

        System.out.println("SUCCESS!!!");
    }

    private static void test(IBackpackChoose solve, List<BackpackItem> items, int capacity, int result) {
        if (solve.maxCost(items, capacity) != result) {
            throw new RuntimeException("Something is going wrong, let's check what!");
        }
    }

/* todo
    -- NEXT STEP IS RUN JAVA 11, RUN THIS TEST
    -- REFLECTION WITH TESTING
    -- HABR WHAT NEW IN JAVA -- USE AND ANKI CONSPECT!
    -- MAKE ORDER HERE AND PUSH TO GIT!
    */


/*
 TODO just for training write algorithm which will check all variants with 'brute force method'
 */
}
