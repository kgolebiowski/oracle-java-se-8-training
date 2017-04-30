package net.mypieceofthe.java8.C1_Fundamentals;

import java.io.*;

/**
 * Created by kgolebiowski on 26/04/2017.
 */
public class C1_3_2_LambdaExceptions {
    public static void main(String[] args) {
        File file = new File("/Users/kgolebiowski/Desktop/source_code.txt");

        processFile(file, (BufferedReader br) -> Integer.toString(br.read()));

        processFile(file, (BufferedReader br) -> br.readLine() + " << >> " + br.readLine());
    }

    private static void processFile(File file, ReaderProcessor readerProcessor) {
        try (LineNumberReader br = new LineNumberReader(new FileReader(file))) {
            System.out.println("Line " + br.getLineNumber() + ": ");
            System.out.println("-- " + readerProcessor.process(br));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

@FunctionalInterface
interface ReaderProcessor {
    String process(BufferedReader bufferedReader) throws IOException;
}

