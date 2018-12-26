package somes.orderAtPhonePhotos;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;

/**
 *  Class which will spread files from one time slot by nearest range
 */
public class DaySpreadService {
    public static <T> List<Pair<T, String>> getRidOfDoubles(List<Pair<T, String>> input) {
        input.sort(Comparator.comparing(Pair::getValue));

        List<Pair<T, String>> result = new ArrayList<>();
        LocalDateTime prevDate = LocalDateTime.of(2000, 1, 1, 0,0 );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        for (Pair<T, String> pair : input) {
            String initDateStr = pair.getValue();
            String extensionPart = initDateStr.substring(15);
            LocalDateTime date = LocalDateTime.parse(initDateStr.substring(0, initDateStr.indexOf(".")), formatter);

            if(prevDate.equals(date) || prevDate.isAfter(date)) {
                date = prevDate.plusSeconds(1);
            }
            prevDate = date;

            result.add(new Pair<>(pair.getKey(), date.format(formatter) + extensionPart));

        }

        return result;
    }

    public static void test() {
        List<Pair<Long, String>> list = asList(
                new Pair(null, "20181225_115959.jpg"),
                new Pair(null, "20181225_115959.jpg"),
                new Pair(null, "20181225_115959.jpg"),
                new Pair(null, "20181225_115959.jpg"),
                new Pair(null, "20181225_115959.jpg"),
                new Pair(null, "20181225_115959.jpg"),
                new Pair(null, "20181225_135959.jpg"),
                new Pair(null, "20181225_235959.jpg"),
                new Pair(null, "20181225_235959.jpg"),
                new Pair(null, "20181225_235959.jpg")
        );

        list = getRidOfDoubles(list);

        if(           !(list.get(0).getValue().equals("20181225_115959.jpg") &&
                        list.get(1).getValue().equals("20181225_120000.jpg") &&
                        list.get(2).getValue().equals("20181225_120001.jpg") &&
                        list.get(3).getValue().equals("20181225_120002.jpg") &&
                        list.get(4).getValue().equals("20181225_120003.jpg") &&
                        list.get(5).getValue().equals("20181225_120004.jpg") &&
                        list.get(6).getValue().equals("20181225_135959.jpg") &&
                        list.get(7).getValue().equals("20181225_235959.jpg") &&
                        list.get(8).getValue().equals("20181226_000000.jpg") &&
                        list.get(9).getValue().equals("20181226_000001.jpg"))) {
            throw new RuntimeException("bad");
        }
    }


    /*
        just for visual testing
     */
    public static void main(String[] args) {
        test();

        List<Pair<Long, String>> list = asList(
                new Pair(null, "20181225_115959.jpg"),
                new Pair(null, "20181225_115959.jpg"),
                new Pair(null, "20181225_115959.jpg"),
                new Pair(null, "20181225_115959.jpg"),
                new Pair(null, "20181225_115959.jpg"),
                new Pair(null, "20181225_115959.jpg"),
                new Pair(null, "20181225_135959.jpg"),
                new Pair(null, "20181225_235959.jpg"),
                new Pair(null, "20181225_235959.jpg"),
                new Pair(null, "20181225_235959.jpg")
        );

        list = getRidOfDoubles(list);

        list.forEach( p -> System.out.println(p.getValue()));


    }

}
