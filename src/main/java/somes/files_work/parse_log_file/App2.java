package somes.files_work.parse_log_file;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by anme on 09.01.17.
 */
public class App2 {
    public static void main(String[] args) throws IOException {
        Double d = null;
        BigDecimal bd = new BigDecimal(d);
        System.out.println(bd);

        if(true) return;
        Scanner scanner = new Scanner(System.in);

        Integer words = 1;//scanner.nextInt();
        for (Integer i = 0; i < words; i++) {
            System.out.println(calcAnagramCount("pvmupwjjjf"));//scanner.next()));
        }
    }

    private static int calcAnagramCount(String word) {
        int count = 0;
        for (int lettersCount = 1; lettersCount < word.length(); lettersCount++) {
            int finalLettersCount = lettersCount;
            count += Stream.of(word.split("")).collect(
                    ()-> new SubstringCollector(finalLettersCount),
                    (substringCollector, letter) -> substringCollector.accept(letter),
                    (a, b) -> {}).countAllReversions();
        }
        return count;
    }
}

class SubstringCollector {
    private Integer subLength;
    private Map<String, Integer> subsCount = new HashMap();
    private List<String> currentLetters = new ArrayList<>();

    public SubstringCollector(Integer subLength) {
        this.subLength = subLength;
    }

    public void accept(String letter) {
        currentLetters.add(letter);
        turnSubWord();
    }

    private void turnSubWord(){
        if(currentLetters.size() < subLength) return;
        List<String> sorted = new ArrayList<>(currentLetters);
        String sub = sorted.stream().sorted(String::compareTo).collect(Collectors.joining(""));
        System.out.println(sub);
        subsCount.put(sub, subsCount.get(sub) == null ? 1 : subsCount.get(sub)+1);
        currentLetters.remove(0);
    }

    public Integer countAllReversions() {
        return subsCount.values().stream().map((i)-> i == 1 ? 0 : (i*(i-1))/2).mapToInt((i) -> i).sum();
    }
}
