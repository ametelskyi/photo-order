package somes.orderAtPhonePhotos;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.file.FileSystemDirectory;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.concat;

/**
 * Created by anme on 12.03.16.
 */

public class AppPhotos {
    private static final String COPY_FROM_PATH = "/home/anme/FOR_SORTING_ALL/video_android/";
    private static final String COPY_TO_PATH = "/home/anme/FOR_SORTING_ALL/vid/";

    // I format DCIM format
//        20151008_102109.jpg
//        20151009_083855.jpg

// II format StrangeViberImages format
//        image-2a8ebca1ea71bc6b563edb6ac88b24ab629585f7838fb4a84bb51dd5df4d42bb-V.jpg
//        image-2a4143c3cdb28fea0d67fc9559274dbd05de9de7058986bad103fd7fed545dad-V.jpg

// III format Watsapp format
//        IMG-20150817-WA0004.jpeg
//        IMG-20150819-WA0000.jpg

// IV format Viber format
//        IMG-1398358385438-V.jpg
//        IMG-1398396615599-V.jpg

//    V format screenshot format
//    Screenshot_20180709-140701.jpg
//    Screenshot_20181127-185258.jpg

// other

    private static final String DCIM_REGEXP = "\\d\\d\\d\\d\\d\\d\\d\\d_\\d\\d\\d\\d\\d\\d\\.\\w*";
    private static final String STRANGE_VIBER_REGEXP = "image-.*-V.\\w*";
    private static final String WATSAPP_REGEXP = "IMG-\\d\\d\\d\\d\\d\\d\\d\\d-WA.*";
    private static final String VIBER_REGEXP = "IMG-\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d-V\\.\\w*";
    private static final String SCREENSHOT_FORMAT = "Screenshot_\\d\\d\\d\\d\\d\\d\\d\\d-\\d\\d\\d\\d\\d\\d\\.\\w*";


    public static void main(String[] args) throws ParseException, ImageProcessingException, IOException {
        DaySpreadService.test();

        File folder = new File(COPY_FROM_PATH);
        File[] listOfFiles = folder.listFiles();

        sortAllNames(listOfFiles);
    }

    private static void sortAllNames(File[] listOfFiles) throws IOException {

        //todo for VIDEO w-p
//        for (File file : listOfFiles) {
//            if(file.getName().startsWith("VID")) {
//                System.out.println(file.getName() + " === " + file.getName().substring(4));
//                file.renameTo(new File(file.getName().substring(4)));
//            }
//        }
//        if(1==1) return;


        Set<File> namesDCIM = new TreeSet<>(File::compareTo);
        Set<File> namesStrangeViber = new TreeSet<>(File::compareTo);
        Set<File> namesWatsapp = new TreeSet<>(File::compareTo);
        Set<File> namesViber = new TreeSet<>(File::compareTo);
        Set<File> namesScreenshots = new TreeSet<>(File::compareTo);
        Set<File> unknown = new HashSet<>();

        for (File file : listOfFiles) {
            if (file.getName().matches(DCIM_REGEXP)) {
                namesDCIM.add(file);
            } else if (file.getName().matches(STRANGE_VIBER_REGEXP)) {
                namesStrangeViber.add(file);
            } else if (file.getName().matches(WATSAPP_REGEXP)) {
                namesWatsapp.add(file);
            } else if (file.getName().matches(VIBER_REGEXP)) {
                namesViber.add(file);
            } else if (file.getName().matches(SCREENSHOT_FORMAT)) {
                namesScreenshots.add(file);
            } else {
                unknown.add(file);
                System.out.println("CAN'T MATCH: " + file.getName());
            }
        }

        if(!unknown.isEmpty()) {
            throw new RuntimeException("UNKNOWN files, see console log");
        }

        List<Pair<File, String>> r1 = calcDCIMPatternNames(namesDCIM);
        List<Pair<File, String>> r2 = calcStrangeViberPatternNames(namesStrangeViber);
        List<Pair<File, String>> r3 = calcWatsappPatternNames(namesWatsapp);
        List<Pair<File, String>> r4 = calcViberPatternNames(namesViber);
        List<Pair<File, String>> r5 = calcScreenshotPatternNames(namesScreenshots);

        List<Pair<File, String>> allPairs = new ArrayList<>();
        allPairs.addAll(r1);
        allPairs.addAll(r2);
        allPairs.addAll(r3);
        allPairs.addAll(r4);
        allPairs.addAll(r5);

        allPairs.sort(Comparator.comparing(Pair::getValue));
        allPairs = DaySpreadService.getRidOfDoubles(allPairs);

        int uniqueSize = new HashSet<>(allPairs.stream().map(p -> p.getValue()).collect(toSet())).size();
        System.out.println("NAMES " + allPairs.size() + " unique: " + uniqueSize);
        if (allPairs.size() != uniqueSize) {
            throw new RuntimeException("NOT unique!!!");
        }

        int i=0;
        for (Pair<File, String> file : allPairs) {
            System.out.println(file.getKey().getName() + "\t\t\t" + file.getValue() + "\t " +  (i++ * 100 / allPairs.size())+"%");

            copyFile(file.getKey(), new File(COPY_TO_PATH + file.getValue()));
        }

        System.out.println(123);
    }



    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new RandomAccessFile(sourceFile, "rw").getChannel();
            destination = new RandomAccessFile(destFile, "rw").getChannel();

            long position = 0;
            long count = source.size();

            source.transferTo(position, count, destination);
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    private static List<Pair<File, String>> calcDCIMPatternNames(Set<File> files) {
        return files.stream().map(f -> new Pair<>(f, f.getName())).collect(toList());
    }

    private static List<Pair<File, String>> calcStrangeViberPatternNames(Set<File> files) {
        return files.stream().map(f -> new Pair<>(f, createNameByDate(f))).collect(toList());
    }

    private static List<Pair<File, String>> calcWatsappPatternNames(Set<File> files) {
//      like: IMG-20150817-WA0004.jpeg
        return files.stream().map(f -> new Pair<>(f, f.getName().substring(4, 12) + "_" + "00" + f.getName().substring(15))).collect(toList());
    }

    private static List<Pair<File, String>> calcScreenshotPatternNames(Set<File> files) {
        //Screenshot_20180709-140701.jpg
        return files.stream().map(f -> new Pair<>(f,
                f.getName().substring("Screenshot_".length()).replace("-", "_")))
                .collect(toList());
    }

    private static List<Pair<File, String>> calcViberPatternNames(Set<File> files) {
        List<Pair<File, String>> result = new ArrayList<>();
        for (File file : files) {
            String timestamp = file.getName().substring(4, 17);
            Date date = Date.from(Instant.ofEpochMilli(Long.valueOf(timestamp)));
            String finalName = formatDateToFinalFormat(date, file.getName());
            result.add(new Pair<>(file, finalName));
        }
        return result;
    }

    private static String createNameByDate(File file) {
        Metadata metadata2 = null;
        try {
            metadata2 = ImageMetadataReader.readMetadata(file);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("BAD");
        }

        ExifSubIFDDirectory directory = metadata2.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

        Date dateOriginal = null;
        Date dateModified = null;

        if (directory != null) {
            dateOriginal = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
        }

        FileSystemDirectory fileSystemDirectory = metadata2.getFirstDirectoryOfType(FileSystemDirectory.class);
        dateModified = fileSystemDirectory.getDate(FileSystemDirectory.TAG_FILE_MODIFIED_DATE);

        Date resultDate = dateOriginal != null ? dateOriginal : dateModified;


        //20151008_102109.jpg
        return formatDateToFinalFormat(resultDate, file.getName());
    }

    private static String formatDateToFinalFormat(Date date, String oldName) {
        String format = "yyyyMMdd_HHmmss";
        SimpleDateFormat dtf = new SimpleDateFormat(format);
        return dtf.format(date) + oldName.substring(oldName.indexOf("."));
    }


    private static boolean ifDateOriginalExist(String fileName) {
        String FILE = COPY_FROM_PATH + fileName;

        Metadata metadata2 = null;
        try {
            metadata2 = ImageMetadataReader.readMetadata(new File(FILE));
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExifSubIFDDirectory directory = metadata2.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

        Date dateOriginal = null;
        if (directory != null) {
            dateOriginal = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
        }

        return dateOriginal == null;
    }
}
