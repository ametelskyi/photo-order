package somes.selfImproving.hr.generics;

public class Qwe {
    private Erty erty;

    public void setStr(String s) {
        if(erty == null) erty = new Erty();
        erty.str = s;
    }

    public String getStr() {
        return erty == null ? "NULL" : erty.str;
    }

    private static class Erty{
        public String str;
    }
}
