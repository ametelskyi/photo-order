package somes.files_work.file_rename;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by anme on 07.12.16.
 */
public class App {
    public static void main(String[] args) throws IOException {
//        File file = new File("/home/anme/tmp/qwe.qwe");
//        file.renameTo(new File("/home/anme/tmp/qwe2.qwe"));

        Files.list(Paths.get("/home/anme/tmp"))
                .forEach(p -> {
                    File f = p.toFile();
                    String path = f.getParent();
                    String name = f.getName();


                    Pattern pattern = Pattern.compile(". Урок \\d*");
                    Matcher matcher = pattern.matcher(name);
                    if (matcher.find()) {
//                        System.out.print("Start index: " + matcher.start());
//                        System.out.print(" End index: " + matcher.end() + " ");
//                        System.out.println(matcher.group());

                        System.out.println(name);
                        System.out.println(matcher.group().substring(". Урок ".length())+" "+name.substring(0, matcher.start())+".mp4");

                        System.out.println(f.renameTo(new File(path+"/"+matcher.group().substring(". Урок ".length())+" "+name.substring(0, matcher.start())+".mp4")));
                        System.out.println("--------------");
                    }

//                    System.out.println(f.getName());
                    String newName = f.getPath()+"_2_";
//                    System.out.println(newName);
//                 TODO   System.out.println(f.renameTo(new File(newName)));
                });
    }
}
