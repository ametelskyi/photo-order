package somes.files_work.parse_log_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.time.temporal.ChronoField.MILLI_OF_DAY;

/**
 * Created by anme on 15.12.16.
 */
public class App {
    public static void main(String[] args) throws IOException {

        LocalDateTime now = LocalDateTime.now();
        //vehicle-information-neo4j-17 10 31-234001.tar.gz
        LocalDateTime weekStart = LocalDateTime.of(2017, Month.OCTOBER, 31, 23, 40);

        System.out.println(weekStart);
        System.out.println(weekStart.atZone(ZoneId.ofOffset("UTC", ZoneOffset.UTC)).toEpochSecond());

        if(1==1) return;




//        for (int i = 0; i < 24; i++) {
//            System.out.print("\"counters=\\["+i+"\\]\" | ");
//        }

        try(Stream<String> stream = Files.lines(Paths.get("/home/anme/work/customer_depend/hz/kibana_from/qwe4.txt"))){
            stream.forEach(s -> {

                String pattern = "counters=\\[(.+.*)\\]";
                Pattern r = Pattern.compile(pattern);

                // Now create matcher object.
                Matcher m = r.matcher(s);
                if (m.find( )) {

                    String patternVeh = "vehicle=([^}]*)";
                    Pattern rVeh = Pattern.compile(patternVeh);
                    Matcher mVeh = rVeh.matcher(s);
                    if(mVeh.find()) {
                        System.out.print( "match (v:Vehicle)-[:HAVING_DAMAGES]->(detected:Detected)-[rDet:DETECTED]->(d:Damage) where v.vin ='" + mVeh.group(0).substring(8) + "'  ");
                    }

                    System.out.println("and d.counter in "+ m.group(0).substring(9)  + " delete rDet merge (v)-[:REPAIRED_DAMAGES]->(rp:Repaired) merge (rp)-[:REPAIRED]->(d) delete rDet ");
//                    System.out.println(s);
//                    System.out.println(s.substring(s.indexOf("counters="), s.indexOf("counters=")+40));
//                    System.out.println("");
//                    System.out.println("============================");
                }else {
//                    System.out.println("NO MATCH");
                }
//                if(s.contains("counters=[")) System.out.println(s);
            });
        }
    }

    private static Integer getDifference(String from, String to) {
//        System.out.println(getMillis(from) + "   " + getMillis(to));
        return getMillis(from)-getMillis(to);
    }

    private static Integer getMillis(String time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
        LocalTime lt = LocalTime.parse(time, df);
        return lt.get(MILLI_OF_DAY);
    }
}


