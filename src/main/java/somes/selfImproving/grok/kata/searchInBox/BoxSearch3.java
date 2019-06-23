package somes.selfImproving.grok.kata.searchInBox;

import java.util.LinkedList;
import java.util.Queue;

public class BoxSearch3 implements IBoxSearch {
    @Override
    public String findInBox(Box box) {
        String bsf = bsf(box);
        String dfs = dfs(box);

        if(!bsf.equals(dfs)) {
            throw new RuntimeException();
        }

        return bsf;
    }

    private String bsf(Box box) {
        // search in breath
        // have storage queue for boxes, add first box to it,
        // than every child and operate with head of storage on every new step
        Queue<Box> storage = new LinkedList<>();
        storage.add(box);
        while(!storage.isEmpty()) {
            Box next = storage.poll();
            if(next.getHasGoldKey()) {
                return next.getLabel();
            }
            if(next.getInnerBoxes() != null) {
                next.getInnerBoxes().forEach(b -> storage.add(b));
            }
        }
        return null;
    }

    private String dfs(Box box) {
        //search deep first
        //check current box
        // if not gold, in loop do dfs for every child
        if(box.getHasGoldKey()) {
            return box.getLabel();
        } else if(box.getInnerBoxes() != null){
            for (Box innerBox : box.getInnerBoxes()) {
                String childResult = dfs(innerBox);
                if(childResult != null) {
                    return childResult;
                }
            }
        }

        return null;
    }
}

