package somes.selfImproving.grok.kata.dynamicProgramming.backpack;

import java.util.List;

import static java.util.Arrays.asList;

public class Test {
    public static void main(String[] args) {
        var water = BackpackItem.builder().name("water").size(3).cost(10).build();
        var book  = BackpackItem.builder().name("book").size(1).cost(3).build();
        var food  = BackpackItem.builder().name("food").size(2).cost(9).build();
        var dress = BackpackItem.builder().name("dress").size(2).cost(5).build();
        var camera = BackpackItem.builder().name("camera").size(1).cost(6).build();

        final int RESULT = -1;

        List<BackpackItem> items = asList(water, book, food, dress, camera);

        SolveOne solveOne = new SolveOne();
        if(solveOne.maxCost(items, 7) != RESULT) {
            throw new RuntimeException("Something is going wrong, let's check what!");
        }

        System.out.println("SUCCESS!!!");
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
