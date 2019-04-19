package somes.selfImproving.grok.kata.searchInBox;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by anme on 25.02.19.
 */
public class BoxSearch2 implements IBoxSearch {
    @Override
    public String findInBox(Box box) {
        String bfs = breadthFindFirst(box);
        String dfs = depthFindFirst(box);

        if(!bfs.equals(dfs)) {
            throw new RuntimeException("!");
        }

        return bfs;
    }

    private String breadthFindFirst(Box box) {
        /*
        have queue of boxes to check, initially with general box
        in loop:
            open box, check it(if find, return immediately), add all inner boxes to queue
            check next from queue
         */

        Queue<Box> toCheck = new LinkedList<>();
        toCheck.add(box);

        while(!toCheck.isEmpty()) {
            Box checking = toCheck.poll();
            if(checking.getHasGoldKey()) {
                return checking.getLabel();
            }
            if(checking.getInnerBoxes() != null) {
                checking.getInnerBoxes().forEach(b -> toCheck.add(b));
            }
        }

        return null;
    }


    private String depthFindFirst(Box box) {
        /*
        goal: have method with first go to deep as much as possible
        intention: write recursive method to open box, check it, run this method with all child

        check box, if gold key, return label
            run recursively for every inner box, if not EMPTY, return label
         */
        if(box.getHasGoldKey()) {
            return box.getLabel();
        }

        if(box.getInnerBoxes() != null) {
            for (Box innerBox : box.getInnerBoxes()) {
                String label = depthFindFirst(innerBox);
                if (label != null) return label;
            }
        }

        return null;
    }
}

