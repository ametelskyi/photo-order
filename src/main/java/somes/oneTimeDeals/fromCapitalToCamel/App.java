package somes.oneTimeDeals.fromCapitalToCamel;

/**
 * Created by anme on 16.03.17.
 */
public class App {
    public static void main(String[] args) {
        String input = "" +
                " LESS_HOURS_NUMBER_BETWEEN_PASSWORD_CHANGE " +
                " MIN_PASSWORD_LENGTH " +
                " NUMBER_OF_OLD_PASSWORDS_TO_COMPARE " +
                " NUMBER_OF_DIFFERENT_SYMBOLS_GROUP ";
        String[] sybls = input.split("");
        StringBuilder sb = new StringBuilder();
        boolean prevWas_ = false;
        for (int i = 0; i < sybls.length; i++) {
            String symb = sybls[i];
            if(symb.equals("_")) {
                prevWas_ = true;
            } else {
                sb.append(prevWas_ ? symb : symb.toLowerCase());
                prevWas_ = false;
            }
        }

        System.out.println(sb.toString());
    }
}
