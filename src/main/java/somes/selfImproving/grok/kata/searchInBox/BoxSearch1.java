package somes.selfImproving.grok.kata.searchInBox;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by anme on 14.02.19.
 */
public class BoxSearch1 implements IBoxSearch {
    @Override
    public String findInBox(Box box) {
        // find in depth [Depth-first search, DFS]
        // find in wide search [breadth-first search, BFS]
        // check that values same
        // return value

        String dfsLabel = depthFirstSearch(box);
        String bfsLabel = breadthFirstSearch(box);

        if(!dfsLabel.equals(bfsLabel)) {
            throw new RuntimeException("bad");
        }

        return dfsLabel;
    }

    private static String breadthFirstSearch(Box main) {
        Queue<Box> boxesQueue = new LinkedList<>();
        boxesQueue.add(main);

        while (!boxesQueue.isEmpty()) {
            Box box = boxesQueue.poll();
            if(box.getHasGoldKey()){
                return box.getLabel();
            }
            if(box.getInnerBoxes() != null) {
                boxesQueue.addAll(box.getInnerBoxes());
            }
        }
        return "NOT FOUND";
    }

    private static String depthFirstSearch(Box box) {
        if(box.getHasGoldKey()) return box.getLabel();

        if(box.getInnerBoxes() != null) {
            for (Box innerBox : box.getInnerBoxes()) {
                String lab = depthFirstSearch(innerBox);
                if(lab != null) return lab;
            }
        }

        return null;
    }
}
