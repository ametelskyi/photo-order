package somes.selfImproving.grok.kata.dynamicProgramming.backpack;

import java.util.List;

public interface IBackpackChoose {
    int maxCost(List<BackpackItem> items, int capacity);
}
