package somes.selfImproving.grok.kata.searchInBox;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BoxSearch4 implements IBoxSearch {
    @Override
    public String findInBox(Box box) {
        String dsf = dsf(box);
        dsf = NOT_FOUND.equals(dsf) ? null : dsf;
        String bsf = bsf(box);
        if(dsf == null && bsf == null) {
            return null;
        }
        if(dsf != null && bsf == null || dsf == null && bsf != null || !dsf.equals(bsf)) {
            throw new RuntimeException("WRONG");
        }
        return dsf;
    }

    //bread first
    //goal -- go box by box and find key
    //intention -- open box, check it and than open every box inside
    // plan -- have storage with queue what to open next
    // put to it first box, than in loop get first and put every inner box to queue
    private String bsf(Box box) {
        Queue<Box> queueToCheck = new LinkedList<>();
        queueToCheck.add(box);
        while (!queueToCheck.isEmpty()) {
            Box currentLookOn = queueToCheck.poll();
            if(currentLookOn.getHasGoldKey()) {
                return currentLookOn.getLabel();
            }
            List<Box> inner = currentLookOn.getInnerBoxes();
            if(inner != null) {
                inner.forEach(b -> queueToCheck.add(b));
            }
        }
        return null;
    }

    // deep first
    // goal -- search by drilling inside
    // intention -- open box, open first in it, open inner and go inside while we have boxes
    // when last box reached, go on one level up and check if there is something else to check, if
    // not -- go to up level and continue before reach main box
    // plan --
    //          |
    //         / \
    //        /|\ \
    //      /|\  \/
    // write method which check box on key and then recursively check every inside box
    // if key not exist in box -- return "NOT_FOUND" else return label and this result is indicator
    // if need to continue search
    private static final String NOT_FOUND = "NOT_FOUND";

    private String dsf(Box box) {
        System.out.println("start with " + box.getLabel());
        if(box.getHasGoldKey()) {
            return box.getLabel();
        }
        if(box.getInnerBoxes() == null) {
            return NOT_FOUND;
        }

        for (Box innerBox : box.getInnerBoxes()) {
            String innerSearchResult = dsf(innerBox);
            if(!NOT_FOUND.equals(innerSearchResult)) {
                System.out.println("FOUND SOMETHING, parent is " + box.getLabel() + "; inner is " + innerBox.getLabel() );
                return innerSearchResult;
            }
        }

        return NOT_FOUND;
    }
}
