package somes.oneTimeDeals.quest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by anme on 05.07.17.
 */
public class App {
    static String[] words = ("Ивано-Франковск Харьков Бангкок Кокуд Мосвка Киев Гудаури Батуми " +
            "Тбилиси Кутаиси Днепропетровск Новый-Свет Ялта Ай-Петри Гамбург Бремен Казань" +
            " Майрхофен Мюнхен Петрозаводск")
            .toLowerCase().split(" ");
    static String[] key = "вкладовкезазеркалом".split("");
    static int[][] variants = new int[19][20];

    public static void main(String[] args) {

        for (int i = 0; i < 19; i++) {
            String keySymb = key[i];
            for (int j = 0; j < 20; j++) {
                String word = words[j];
                if(word.contains(keySymb)){
                    variants[i][j] = 1;
                    System.out.println("Word " + word + " contains: " + keySymb);
                }
            }
        }

        deep(0, new HashMap<>(), new HashSet<>());
        System.out.println("f");
    }

    public static void deep(int keyCurrent, Map<Integer, Integer> usedWordsArr, Set<Integer> usedWords) {
        if(keyCurrent >= 19) return;

        //find all possible variants with current key symbol
        for (int i = 0; i < variants[keyCurrent].length; i++) {
            if(variants[keyCurrent][i] == 1 && !usedWords.contains(i)) {
                System.out.println(keyCurrent + " Can be used!!! " + key[keyCurrent] + " " + words[i] + "  " + usedWords.size());
                Map<Integer, Integer> newUsedArr = new HashMap<>(usedWordsArr);
                newUsedArr.put(keyCurrent, i);

                Set<Integer> newUsedWords = new HashSet<>(usedWords);
                newUsedWords.add(i);

                if(usedWords.size() == 18 ){
                    printMe(usedWordsArr);
                    System.out.println("18");
                }

                deep(keyCurrent+1, newUsedArr, newUsedWords);
            }
        }
    }

    private static void printMe(Map<Integer, Integer> usedWordsArr) {
        for (Map.Entry<Integer, Integer> entry : usedWordsArr.entrySet())
        {
            String k = key[entry.getKey()];
            String w = words[entry.getValue()];
            System.out.println(k + " " + w);
        }
    }
}
