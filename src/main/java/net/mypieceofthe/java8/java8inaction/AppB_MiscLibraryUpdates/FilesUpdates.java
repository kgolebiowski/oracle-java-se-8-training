package net.mypieceofthe.java8.java8inaction.AppB_MiscLibraryUpdates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by kgolebiowski on 13/05/2017.
 */
public class FilesUpdates {

    private static final String REPORT_FOLDER = "./src/main/resources/report";

    public static void main(String[] args) throws IOException {

        Files.walk(Paths.get(REPORT_FOLDER)).forEach(System.out::println);

        System.out.println();

        Files.walk(Paths.get(REPORT_FOLDER), 0).forEach(System.out::println);

        System.out.println();

        Files.walk(Paths.get(REPORT_FOLDER), 1).forEach(System.out::println);

        System.out.println();

        Files.find(Paths.get(REPORT_FOLDER), 1, (path, bfa) -> true).forEach(System.out::println);
    }
}
