package somes.oneTimeDeals.memory;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by anme on 26.04.18.
 */
public class Numbers {
    public static void main(String[] args) {
        IntStream.range(0, 30).forEach(i -> printRandomMultipl());
    }

    private static void printRandomMultipl(){
        Random r = new Random();
        Integer a =  getInt(100, 999);
        Integer b =  getInt(100, 999);
        System.out.println(a + "\t" + b + "\t\t\t" + (a*b));
        System.out.println("-");
        System.out.println("-");
    }

    private static Integer getInt(int low, int hi) {
        Random r = new Random();
        while (true) {
            Integer a =  r.nextInt(hi);
            if(a >= low && a%10 > 0) {
                return a;
            }
        }
    }
}
