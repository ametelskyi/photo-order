package somes.selfImproving.grok.kata.dynamicProgramming.backpack;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BackpackItem {
    private String name;
    private int size;
    private int cost;
}
