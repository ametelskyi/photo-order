package somes.selfImproving.hr.sherlock;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by anme on 07.06.17.
 */
public class App {
    public static void main(String[] args) throws IOException {
        readReportFromFileSystem("fsdfdsf");
    }



    protected static byte[] readReportFromFileSystem(String fileName) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Path pathToReport = FileSystems.getDefault().getPath( fileName);
        if(Files.exists(pathToReport)) {
            Files.copy(pathToReport, bos);
        }

        return bos.toByteArray();
    }
}
