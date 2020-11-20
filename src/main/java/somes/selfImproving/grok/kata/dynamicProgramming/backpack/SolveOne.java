package somes.selfImproving.grok.kata.dynamicProgramming.backpack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                2. it is better to place second item -- put it with something(calculate it) from previous row
                3. it is better to get from previous row -- just get it
             repeat this for all other things
             at the last row and last column we will have best result, why? think for a minute with urchin
         */

        //create array for results (create two dimensions list of lists, inside cell -- group of objects!)
        //do in loop
        //fill current cell with algorithm:
        //if can't be placed, fill with prev row if it is not first row
        //else calculate this item with rest from prev and compare with just prev row, choose best

        List<List<Set<BackpackItem>>> table = new ArrayList<>(capacity);
        for (int i = 0; i < items.size(); i++) {
            BackpackItem item = items.get(i);

            List<Set<BackpackItem>> row = new ArrayList<>();
            for (int j = 0; j < capacity; j++) {
                int curCapacityHuman = j+1;
                if(item.getSize() >  curCapacityHuman) {
                    Set<BackpackItem> cell = (i == 0) ? new HashSet<>() : table.get(i-1).get(j);
                    row.add(cell);
                } else {
                    if(i ==0) {
                        row.add(Set.of(item));
                        continue;
                    }

                    int leftCapacity = curCapacityHuman - item.getSize();

                    int prevFullPrice = table.get(i-1).get(curCapacityHuman -1).stream().map(BackpackItem::getCost).mapToInt(Integer::intValue).sum();
                    int prevLeftoverPrice = (leftCapacity == 0) ? 0 : table.get(i - 1).get(leftCapacity - 1).stream().map(BackpackItem::getCost).mapToInt(Integer::intValue).sum();

                    int currentMaxPrice = item.getCost() + prevLeftoverPrice;

                    if(prevFullPrice > currentMaxPrice) {
                        //place everything from previous row
                        row.add(table.get(i-1).get(curCapacityHuman-1));
                    } else {
                        Set<BackpackItem> cell = new HashSet<>(Set.of(item));
                        if(leftCapacity > 0) cell.addAll(table.get(i - 1).get(leftCapacity-1));
                        row.add(cell);
                    }
                }
            }
            table.add(row);
        }

        //get last cell and see, what inside
        Set<BackpackItem> lastCell = table.get(items.size() -1).get(capacity -1);
        int sum  = lastCell.stream().mapToInt(BackpackItem::getCost).sum();
        int totalPlaces  = lastCell.stream().mapToInt(BackpackItem::getSize).sum();
        lastCell.forEach(b -> System.out.println(b.toString()));

        System.out.println("sum = " + sum);
        System.out.println("totalPlaces = " + totalPlaces);






        return sum;
    }
}
