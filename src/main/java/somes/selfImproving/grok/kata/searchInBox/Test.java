package somes.selfImproving.grok.kata.searchInBox;

import static java.util.Arrays.asList;

/**
 * Created by anme on 14.02.19.
 */
public class Test {
    public static void main(String[] args) {
        IBoxSearch[] searches = new IBoxSearch[]{
                new BoxSearch1()
                , new BoxSearch2()

        };

        for (IBoxSearch search : searches) {
            if(!"Cb2".equals(search.findInBox(packAllBoxes()))) {
                throw new RuntimeException("bad bad");
            }
        }

        System.out.println("SUCCESS !!! AWESOME !!! ");
    }

    private static Box packAllBoxes() {
        Box boxAa1 = new Box("Aa1", false, null);
        Box boxAa2 = new Box("Aa2", false, null);
        Box boxAb1 = new Box("Ab1", false, null);
        Box boxAb2 = new Box("Ab2", false, null);
        Box boxAc1 = new Box("Ac1", false, null);
        Box boxAc2 = new Box("Ac2", false, null);

        Box boxAa = new Box("Aa", false, asList(boxAa1, boxAa2));
        Box boxAb = new Box("Ab", false, asList(boxAb1, boxAb2));
        Box boxAc = new Box("Ac", false, asList(boxAc1, boxAc2));


        Box boxBa1 = new Box("Ba1", false, null);
        Box boxBa2 = new Box("Ba2", false, null);
        Box boxBb1 = new Box("Bb1", false, null);
        Box boxBb2 = new Box("Bb2", false, null);
        Box boxBc1 = new Box("Bc1", false, null);
        Box boxBc2 = new Box("Bc2", false, null);

        Box boxBa = new Box("Ba", false, asList(boxBa1, boxBa2));
        Box boxBb = new Box("Bb", false, asList(boxBb1, boxBb2));
        Box boxBc = new Box("Bc", false, asList(boxBc1, boxBc2));

        Box boxCa1 = new Box("Ca1", false, null);
        Box boxCa2 = new Box("Ca2", false, null);
        Box boxCb1 = new Box("Cb1", false, null);
        // HERE IS OUR GOLD KEY
        Box boxCb2 = new Box("Cb2", true, null);
        Box boxCc1 = new Box("Cc1", false, null);
        Box boxCc2 = new Box("Cc2", false, null);

        Box boxCa = new Box("Ca", false, asList(boxCa1, boxCa2));
        Box boxCb = new Box("Cb", false, asList(boxCb1, boxCb2));
        Box boxCc = new Box("Cc", false, asList(boxCc1, boxCc2));

        // first level
        Box boxA = new Box("A", false, asList(boxAa, boxAb, boxAc));
        Box boxB = new Box("B", false, asList(boxBa, boxBb, boxBc));
        Box boxC = new Box("C", false, asList(boxCa, boxCb, boxCc));

        // main
        Box box = new Box("MAIN", false, asList(boxA, boxB, boxC));

        return box;
    }
}
