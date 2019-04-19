package somes.selfImproving.grok.kata.searchInBox;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Box {
    private String label;
    private Boolean hasGoldKey;
    private List<Box> innerBoxes;
}
