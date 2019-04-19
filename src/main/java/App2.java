import javafx.util.Pair;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by anme on 22.01.19.
 */
public class App2 {
    public static void main(String[] args) {

        List<Pair<String, String>> sentences = new ArrayList<>();

        sentences.addAll(makeSentences("cherche", "(искать)"));


        sentences.addAll(makeSentences("parle", "(говорить)"));
        sentences.addAll(makeSentences("mange", "(кушать)"));

        sentences.addAll(makeSentences("chante", "(петь)"));
        sentences.addAll(makeSentences("pense", "(думать)"));
        sentences.addAll(makeSentences("danse", "(танцевать)"));
        sentences.addAll(makeSentences("cherche", "(искать)"));
        sentences.addAll(makeSentences("trouve", "(находить)"));
        sentences.addAll(makeSentences("quitte", "(покидать)"));
        sentences.addAll(makeSentences("écoute", "(слушать)"));

        sentences.addAll(makeSentences("aime", "(любить)"));


        //todo
        for (Pair<String, String> sentence : sentences) {
//            System.out.println(sentence.getKey() + "\t\t\t" + sentence.getValue());
        }

        List<Pair<String, String>> randomSentences = new ArrayList<>();
        while(sentences.size() >0) {
            int index = ThreadLocalRandom.current().nextInt(0, sentences.size());
            randomSentences.add(sentences.get(index));
            sentences.remove(index);
        }



        System.out.println("----------------");
        for (Pair<String, String> sentence : randomSentences) {
            System.out.println(sentence.getKey());
        }

        System.out.println("----------------");
        for (Pair<String, String> sentence : randomSentences) {
            System.out.println(sentence.getValue());
        }


    }

    private static List<Pair<String, String>> makeSentences(String fr, String ru) {
        List<Pair<String, String>> sentences = new ArrayList<>();

        // normal
        sentences.add(new Pair<>( (vowel(fr) ? "j'" : "je ") + fr + "", "я " + ru));
        sentences.add(new Pair<>( "tu " + fr + "s", "ты " + ru));
        sentences.add(new Pair<>( "il/elle " + fr + "", "он/она " + ru));
        sentences.add(new Pair<>( "nous " + cutLast(fr) + "ons", "мы " + ru));
        sentences.add(new Pair<>( "vous " + fr + "z", "вы " + ru));
        sentences.add(new Pair<>( "ils/elles " + fr + "nt", "они " + ru));

        //negative
        sentences.add(new Pair<>( (vowel(fr) ? "j n'" : "je ne " )+ fr + " pas", "я не " + ru));
        sentences.add(new Pair<>( (vowel(fr) ? "tu n'" : "tu ne " ) + fr + "s pas", "ты не " + ru));
        sentences.add(new Pair<>( (vowel(fr) ? "il/elle n'" : "il/elle ne " ) + fr + " pas", "он/она не " + ru));
        sentences.add(new Pair<>( (vowel(fr) ? "nous n'" : "nous ne " ) + fr + "ons pas", "мы не " + ru));
        sentences.add(new Pair<>( (vowel(fr) ? "vous n'" : "vous ne " ) + fr + "z pas", "вы не " + ru));
        sentences.add(new Pair<>( (vowel(fr) ? "ils/elles n'" : "ils/elles ne " ) + fr + "nt pas", "они не " + ru));

        // past
        sentences.add(new Pair<>( "j'ai " + fr + "", "[про вчера] я " + ru));
        sentences.add(new Pair<>( "tu as " + fr, "[про вчера] ты " + ru));
        sentences.add(new Pair<>( "il/elle a " + fr, "[про вчера] он/она " + ru));
        sentences.add(new Pair<>( "nous avons " + fr, "[про вчера] мы " + ru));
        sentences.add(new Pair<>( "vous avez " + fr, "[про вчера] вы " + ru));
        sentences.add(new Pair<>( "ils/elles ont " + fr, "[про вчера] они " + ru));

        // past negative
        sentences.add(new Pair<>( "je n'ai pas " + fr + "", "[про вчера] я не " + ru));
        sentences.add(new Pair<>( "tu n'as pas " + fr, "[про вчера] ты не " + ru));
        sentences.add(new Pair<>( "il/elle n'a pas " + fr, "[про вчера] он/она не " + ru));
        sentences.add(new Pair<>( "nous n'avons pas " + fr, "[про вчера] мы не " + ru));
        sentences.add(new Pair<>( "vous n'avez pas " + fr, "[про вчера] вы не " + ru));
        sentences.add(new Pair<>( "ils/elles n'ont pas " + fr, "[про вчера] они не " + ru));

                // future
        sentences.add(new Pair<>( "je vais " + fr + "", "[про завтра] я " + ru));
        sentences.add(new Pair<>( "tu vas " + fr, "[про завтра] ты " + ru));
        sentences.add(new Pair<>( "il/elle va " + fr, "[про завтра] он/она " + ru));
        sentences.add(new Pair<>( "nous allons " + fr, "[про завтра] мы " + ru));
        sentences.add(new Pair<>( "vous allez " + fr, "[про завтра] вы " + ru));
        sentences.add(new Pair<>( "ils/elles vont " + fr, "[про завтра] они " + ru));

                // future negative
        sentences.add(new Pair<>( "je ne vais " + fr + "", "[про завтра] я не" + ru));
        sentences.add(new Pair<>( "tu ne vas " + fr, "[про завтра] ты не " + ru));
        sentences.add(new Pair<>( "il/elle ne va " + fr, "[про завтра] он/она не " + ru));
        sentences.add(new Pair<>( "nous n'allons " + fr, "[про завтра] мы не " + ru));
        sentences.add(new Pair<>( "vous n'allez " + fr, "[про завтра] вы не " + ru));
        sentences.add(new Pair<>( "ils/elles ne vont " + fr, "[про завтра] они не " + ru));

        return sentences;
    }

    private static String cutLast(String fr) {
        return fr == null || fr.length() < 2 ? fr : fr.substring(0, fr.length()-1);
    }

    private static boolean vowel(String word) {
        return word.startsWith("a") ||word.startsWith("e") ||word.startsWith("é") ||word.startsWith("o");
    }

}