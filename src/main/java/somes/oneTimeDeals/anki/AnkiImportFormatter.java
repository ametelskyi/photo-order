package somes.oneTimeDeals.anki;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class AnkiImportFormatter {
    final static Path pathIn = Paths.get("resources/anki-france-in.txt");
    final static String pathOutStr = "resources/anki-france-out.csv";
    final static Path pathOut = Paths.get(pathOutStr);

    public static void main(String[] args) throws IOException {

        //goal: read from file, output to file ready-to-anki-import
        //read file
        //read by one words group, validation on all 5 not empty fields and starting symbols
        //write to words objects
        //output as text file with tabs

        List<String> rows;
        try (Stream<String> stream = Files.lines(pathIn)) {
            rows = stream.map(String::trim).filter(r -> r.length() > 0).collect(toList());
        }


        List<FranceWord> words = new ArrayList<>();
        for (int i = 0; i <= rows.size() - 5; i+=5) {

            validate(rows.get(i), rows.get(i+1), rows.get(i+2), rows.get(i+3),  rows.get(i+4));


            words.add(FranceWord.builder()
                    .france(rows.get(i))
                    .pronunciation(rows.get(i+1).replaceAll(" {2}]", "]"))
                    .description(rows.get(i+2))
                    .english(rows.get(i+3))
                    .examples(rows.get(i+4))
                    .build());
//            System.out.println(rows.get(i+4));
        }

        for (int i = 0; i < words.size(); i++) {
            FranceWord word = words.get(i);

            System.out.print(toCsv((i+1) + word.getFrance())+",");
            System.out.print(toCsv(word.getPronunciation())+",");
            System.out.print(toCsv(word.getDescription())+",");
            System.out.print(toCsv(word.getEnglish())+",");
            System.out.print(toCsv(word.getExamples()));
            System.out.println();
        }


        try (PrintWriter out = new PrintWriter(pathOutStr)) {
            for (FranceWord word : words) {
                out.print(toCsv(word.getFrance())+",");
                out.print(toCsv(word.getPronunciation())+",");
                out.print(toCsv(word.getDescription())+",");
                out.print(toCsv(word.getEnglish())+",");
                out.print(toCsv(word.getExamples()));
                out.println();
            }
        }

    }

    private static void validate(String france, String pronunciation, String descr, String english, String example) {
        if(france.trim().length() == 0
        || pronunciation.length() == 0
        || descr.length() == 0
        || english.length() == 0
        || example.length() == 0) throw new RuntimeException("EMPTY FIELD IN: " + france + "!");
        if(!pronunciation.substring(0, 1).equals("[")) {
            throw new RuntimeException("Looks '" + pronunciation + "' is not pronunciation, check it please! ("+france+")");
        }
    }

    private static String toCsv(String str) {
        String result = str.replaceAll("\"", "\"\"");
        return "\"" + result + "\"";
    }

}
