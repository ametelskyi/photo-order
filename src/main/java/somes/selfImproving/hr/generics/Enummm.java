package somes.selfImproving.hr.generics;

/**
 * Created by anme on 14.08.17.
 */
public enum  Enummm {
    A("normal item"),
    B("customer item (pre-owened)"),
    C("item vas damaged by carrier"),
    D("item was stolen \\lost by carrier");

    private String description;

    public String getDescription() {
        return description;
    }

    Enummm(String description) {
        this.description = description;
    }
}
