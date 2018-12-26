package somes.files_work.file_read;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by anme on 03.10.16.
 */
public class ParseLog {

    public static void main(String[] args) throws FileNotFoundException {
        String path = "/home/anme/report25.csv";
        

        BufferedReader file
                = new BufferedReader(new FileReader(path));


//        List<String> sorted = file.lines().sorted().collect(Collectors.toList());
        List<String> lines = file.lines().map(l->l.substring(11, 19)).collect(Collectors.toList());


        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

        lines.forEach(l -> {
            Integer hour = Integer.valueOf(l.substring(0,2));
            Integer min = Integer.valueOf(l.substring(3,5));
            Integer sec = Integer.valueOf(l.substring(6,8));

            if(!map.containsKey(hour)) map.put(hour, 0);

            map.put(hour, map.get(hour)+1);
            Integer result = sec + min*100 + hour*100*100;

        });

        map.keySet().forEach(k -> System.out.println("time:"+k+"-"+(k+1)+" --> count:"+map.get(k)));
        System.out.println(123);
    }
}
