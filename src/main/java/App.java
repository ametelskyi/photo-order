import javafx.util.Pair;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.ZoneOffset.UTC;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * Created by anme on 06.12.18.
 */
public class App {
    public static void main(String[] args) throws IOException {
        final Path pathProd =   Paths.get("/home/anme/QQQ_PROD.csv");
        final Path pathNew =    Paths.get("/home/anme/QQQTO_ADD.csv");
        final Path pathFixes =  Paths.get("/home/anme/QQQ_CORRECTED.csv");
        final Path result =     Paths.get("/home/anme/result.csv");



        Set<String> withHasSet = new HashSet<>(asList("812079",  "812182",  "812163",  "812205",  "812196",  "782327",  "830783",  "782328",  "799730",  "799731",  "799735",  "792752",  "799732",  "799733",  "799734",  "804775",  "804776",  "808422",  "804777",  "804779",  "804780",  "804781",  "804782",  "782329",  "782336",  "789799",  "789798",  "799726",  "782337",  "787098",  "782338",  "800600",  "800599",  "802316",  "799727",  "787101",  "787102",  "787103",  "787110",  "782339",  "805087",  "805097",  "805098",  "805099",  "805102",  "805100",  "805105",  "805106",  "805086",  "805089",  "805091",  "805093",  "805096",  "782340",  "803291",  "782334",  "782314",  "782315",  "782316",  "782321",  "782322",  "782324",  "782330",  "787107",  "787104",  "787105",  "804784",  "787106",  "782333",  "787099",  "799729",  "787109",  "810077",  "782343",  "782335",  "782323",  "827465",  "799736",  "799737",  "799738",  "638060",  "787115",  "804783",  "799728",  "787111",  "804654",  "804785",  "804649",  "804646",  "804652",  "804653",  "804650",  "804648",  "804647",  "804651",  "784111"));

        Stream<String> streamForReadResult = Files.lines(result);
        List<String> resRows = streamForReadResult.collect(toList());


        for (String row : resRows) {
            if(row.length() > 0) {
                String id = row.substring(0, row.indexOf(","));

                if (id != null && withHasSet.contains(id)) {
                    System.out.print("true");
                }
                System.out.print(",");

                System.out.print(row);




                System.out.println();
            }
        }



        //TODO
        if(1==1) return;

        BufferedWriter writer = Files.newBufferedWriter(result);


        Set<String> writtenFixed = new HashSet<>();

        Stream<String> stream = Files.lines(pathFixes);
        stream.forEach(s -> {
            String id = s.substring(0, s.indexOf(","));
            if(writtenFixed.contains(id)) {
                System.out.println("id = " + id);
                throw new RuntimeException("look here!");
            }
            writtenFixed.add(id);
            out(writer, s);
        });

        Set<String> duplicated = new HashSet<>();

        Set<String> writtenFromToAdd = new HashSet<>();

        Stream<String> stream2 = Files.lines(pathNew);
        List<String> rows = stream2.collect(toList());

//        for (int i = 0; i < rows.size(); i++) {
//            System.out.println(i);
//        }

        System.out.println("rows.size() = " + rows.size());



        for (String s : rows) {
            if(!"".equals(s.trim())) {
                String id = s.substring(0, s.indexOf(","));
                if(!writtenFixed.contains(id)) {
                    if (writtenFromToAdd.contains(id)) {
                        duplicated.add(id);
                    } else {
                        writtenFromToAdd.add(id);
                        out(writer, s);
                    }
                } else {
                    duplicated.add(id);
                }
            }
        }

        Stream<String> stream3 = Files.lines(pathProd);
        List<String> rowsProd = stream3.collect(toList());


        Set<String> writtenFromProd = new HashSet<>();
        for (String s : rowsProd) {
            if(!"".equals(s.trim())) {
                String id = s.substring(0, s.indexOf(","));
                if(!writtenFixed.contains(id) && !writtenFromToAdd.contains(id)) {
                    if (writtenFromProd.contains(id)) {
                        duplicated.add(id);
                    } else {
                        writtenFromProd.add(id);
                        out(writer, s);
                    }
                } else {
                    duplicated.add(id);
                }
            }
        }

//        duplicated.forEach(d -> System.out.println(d));

    }

    public static void out(BufferedWriter writer, String string) {
        try {
            writer.write(string);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("qwe");
        }

    }
}
