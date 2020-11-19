package somes.selfImproving.grok.kata.dynamicProgramming.backpack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SolveOne implements IBackpackChoose {
    @Override
    public int maxCost(List<BackpackItem> items, int capacity) {
        /*
        task: get the most expensive combination of things
        plan:
        p-th: backpack as long-think tail
            road with squares and at the left my things placed vertically (a lot of them, placed as tower)
            start from the beginning, place where it is possible first item vertically and place for all next fields
            then come second field, here we can have 3 cases:
                1. second item can't be placed -- just put thing from previous row in same column
                  second item can be placed, calculate value plus get value of free cells from previous row and compare with prev col val:
                2. it is better to place second item -- put it with something from previous row
                3. it is better to get from previous row -- just get it
             repeat this for all other things
             at the last row and last column we will have best result, why? think for a minute with urchin
         */

        //create array for results (create two dimensions list of lists, inside cell -- group of objects!)
        //do in loop
        //fill current cell with algorithm:
        //if can't be placed, fill with prev row if it is not first row
        //else calculate this item with rest from prev and compare with just prev row, choose best

        List<List<List<BackpackItem>>> table = new ArrayList<>(capacity);
        for (int i = 0; i < items.size(); i++) {
            BackpackItem item = items.get(i);

            List<List<BackpackItem>> row = new ArrayList<>();
            for (int j = 0; j < capacity; j++) {
                int curCapacity = j+1;
                if(item.getSize() >  curCapacity) {
                    List<BackpackItem> cell = (i == 0) ? new ArrayList<>() : table.get(i-1).get(j);
                    row.add(cell);
                } else {
//                    int currentPrice = item.getCost();
                    int leftCapacity = curCapacity - item.getSize();

                    int prevFullPrice = table.get(i-1).get(curCapacity -1).stream().map(BackpackItem::getCost).mapToInt(Integer::intValue).sum();
                    int prevLeftoverPrice = (leftCapacity == 0) ? 0 : table.get(i - 1).get(leftCapacity - 1).stream().map(BackpackItem::getCost).mapToInt(Integer::intValue).sum();

                    int currentPrice = item.getCost() + prevLeftoverPrice;


                }
            }
            table.add(row);


        }








        return -1;
    }
}
