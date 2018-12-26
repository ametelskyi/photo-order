package somes.selfImproving.grok;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;

/**
 * Created by anme on 31.08.18.
 */
public class SearchInBox {
    private static final String NOT_FOUND = "not found";

    public static void main(String[] args) throws InterruptedException {
        Box box = initBox();
        String loopFound = searchWithLoop(box);
        System.out.println("!!!!!!!!loopFound = " + loopFound);
        System.out.println();

        String recFound = recFind(box);
        System.out.println("!!!!!!!!!recFound = " + recFound);


    }

    private static String searchWithLoop(Box box) throws InterruptedException {
        List<Box> boxesToCheck = new ArrayList<>();
        boxesToCheck.add(box);

        while (!boxesToCheck.isEmpty()) {
            Box toAnalyze = boxesToCheck.get(0);
            System.out.println("analyzed box# " + toAnalyze.getNumber());

            Thread.sleep(100);
            if(toAnalyze.getHasKey()) return toAnalyze.getNumber();
            boxesToCheck.addAll(toAnalyze.getInnerBoxes());
            boxesToCheck.remove(0);
        }

        return NOT_FOUND;
    }

    private static String recFind(Box box) throws InterruptedException {
        System.out.println("analyzed box# " + box.getNumber());
        Thread.sleep(100);
        if(box.getHasKey()) {
            System.out.println("    FOUND REC BOX " + box.getNumber());
            return box.getNumber();
        } else {
            for (Box inner : box.getInnerBoxes()) {
                String found = recFind(inner);
                System.out.println("- " + found + " from box " + box.getNumber());
                if(!NOT_FOUND.equals(found)) {
                    System.out.println("+++Will return " + found);
                    return found;
                }

            }
        }
        System.out.println("--before finish box " + box.getNumber());
        return NOT_FOUND;
    }

    private static Box initBox() {
        Box box4 = new Box("box_4", false,
                asList(
                        new Box("box_7", false),
                        new Box("box_8", false)));

        Box box2 = new Box("box_2", false,
                asList(
                        box4,
                        new Box("box_5", false),
                        new Box("box_6", false)));

        Box boxMain =
                new Box("box_0", false,
                    asList(
                        new Box("box_1", false,
                            asList(
                                box2,
                                new Box("box_3", false)))));

        return boxMain;
    }


}

@Data
@Builder
class Box {
    private String number;
    private Boolean hasKey;
    private List<Box> innerBoxes;

    public Box(String number, Boolean hasKey, List<Box> innerBoxes) {
        this.number = number;
        this.hasKey = hasKey;
        this.innerBoxes = innerBoxes;
    }

    public Box(String number, Boolean hasKey) {
        this.number = number;
        this.hasKey = hasKey;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Box> getInnerBoxes() {
        return ofNullable(innerBoxes).orElse(new ArrayList<>());
    }

    public void setInnerBoxes(List<Box> innerBoxes) {
        this.innerBoxes = innerBoxes;
    }

    public Boolean getHasKey() {
        return hasKey;
    }

    public void setHasKey(Boolean hasKey) {
        this.hasKey = hasKey;
    }
}
