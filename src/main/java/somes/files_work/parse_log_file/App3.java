package somes.files_work.parse_log_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by anme on 07.09.17.
 */
public class App3 {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        try (Stream<String> stream = Files.lines(Paths.get("/home/anme/server.log"))) {
            stream.forEach(l -> {
                if(l.contains("getStatusList() , DOCUMENT_TY")){
                    String sub = l.substring(l.indexOf("DOCUMENT_TY")+15);
//                    System.out.println(sub);
                    set.add(sub);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        set.forEach(s-> System.out.println(s));
    }
}
