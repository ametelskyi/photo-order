package somes.selfImproving.hr.warmup;

import java.util.Scanner;

/**
 * Created by anme on 07.07.17.
 */
public class App {
    static int birthdayCakeCandles(int n, int[] ar) {
        Integer ii = 10;
//        Class<Integer> clazz = ii.getClass();


        int max = ar.length> 0?  ar[0] : 0;
        int maxCount = 1;
        for (int i = 1; i < ar.length; i++) {
            if(ar[i] == max) maxCount ++;
            if(ar[i]>max) {
                max = ar[i];
                maxCount = 1;
            }
        }

        return maxCount;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar = new int[n];
        for(int ar_i = 0; ar_i < n; ar_i++){
            ar[ar_i] = in.nextInt();
        }
        int result = birthdayCakeCandles(n, ar);
        System.out.println(result);
    }
}


/*

Colleen is turning  years old! Therefore,
she has  candles of various heights on her cake, and candle  has height . Because the taller candles tower over the
shorter ones, Colleen can only blow out the tallest candles.

Given the  for each individual candle, find and print the number of candles she can successfully blow out.

Input Format

The first line contains a single integer, , denoting the number of candles on the cake.
The second line contains  space-separated integers, where each integer  describes the height of candle .


 */
