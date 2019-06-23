package somes.oneTimeDeals.cypher;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by anme on 09.11.16.
 */
public class QueryFormatter {
    final static Path path = Paths.get  ("resources/qwe.cql");

    public static void main(String[] args) throws IOException {

        String query = null;
        try (Stream<String> stream = Files.lines(path)) {
            query = stream.map(String::trim).collect(Collectors.joining(" "));
        }

        List<String> commands = Arrays.asList(query.split(" ")).stream()
                .map(s -> s.replace("+", " "))
                .map(s -> s.replace("\\n", " "))
                .map(s -> s.replace("\\t", " "))
                .map(s -> s.replace("\"", ""))
                .filter(s -> s.trim().length() > 0).map(String::trim).collect(Collectors.toList());

        boolean returnBlock = false;
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (int i = 0; i < commands.size(); i++) {
                String s = commands.get(i);
                if(s.equalsIgnoreCase("return")) returnBlock = true;
                if (i > 1 && commands.get(i - 1).equalsIgnoreCase("match")) writer.write("\n\t");
                if (i > 1 && commands.get(i - 1).equalsIgnoreCase("return")) writer.write("\n\t");
                if (i > 1 && commands.get(i - 1).equalsIgnoreCase("where")) writer.write("\n\t\t");
                if ("return".equalsIgnoreCase(s)
                        || "with".equalsIgnoreCase(s)
                        || "optional".equalsIgnoreCase(s)
                        || "where".equalsIgnoreCase(s)
                        || "and".equalsIgnoreCase(s)
                        || "skip".equalsIgnoreCase(s)
                        || "limit".equalsIgnoreCase(s)
                        || ("match".equalsIgnoreCase(s) && i>0 &&!"optional".equalsIgnoreCase(commands.get(i - 1)))
                        )
                    writer.write("\n");
                if ("where".equalsIgnoreCase(s))  writer.write("\t");
                if ("and".equalsIgnoreCase(s))    writer.write("\t\t");

                writer.write(s + " ");

                if(returnBlock &&  s.contains(",")) writer.write("\n\t");
                if(i == commands.size()-1 && !s.contains(";")) writer.write(";");
            }
        }
    }


}
