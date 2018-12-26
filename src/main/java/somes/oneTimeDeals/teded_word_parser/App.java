package somes.oneTimeDeals.teded_word_parser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by anme on 20.03.17.
 */
public class App {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {




        List<String> words2 = new ArrayList<>(getFromFile());
        words2.sort(String::compareTo);

        words2.forEach(s -> System.out.println("" + s + ""));
    }

    public static Set<String> getFromFile() throws IOException {
        File file = new File("/home/anme/work/workspaces/idea/algs/resources/ted_text.txt");
        String all = Files.readAllLines(file.toPath()).stream().collect(Collectors.joining(" "));

//        String content = new String(Files.readAllBytes(Paths.get("ted_text.txt")));
        Set<String> words = Arrays.stream(all.split("[[ ]*|[,]*|[\n]*|[\\.]*|[:]*|[/]*|[!]*|[?]*|[+]*]+")).collect(Collectors.toSet());
        Set<String> set2 = new HashSet<>();
        for (String word : words) {
            set2.add(word.replaceAll("[^\\w]", ""));
        }

        return set2;

    }

    public static Set<String> parseFromXml() throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SAXPars saxp = new SAXPars();

        parser.parse(new File("/home/anme/Downloads/timedtext.xml"), saxp);

        return saxp.getWords();
    }
}


