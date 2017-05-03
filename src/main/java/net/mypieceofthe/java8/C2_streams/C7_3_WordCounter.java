package net.mypieceofthe.java8.C2_streams;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by kgolebiowski on 03/05/2017.
 */
public class C7_3_WordCounter {

    public static void main(String[] args) {
        String sampleText =
                " Nel   mezzo del cammin  di nostra  vita " +
                        "mi  ritrovai in una  selva oscura" +
                        " ché la  dritta via era   smarrita ";

        C7_3_WordCounter wordCounter = new C7_3_WordCounter();

        System.out.println("Words counted iteratively: " +
                wordCounter.countWordsIteratively(sampleText) + "\n");

        Stream<Character> sampleSequentialTextStream =
                IntStream.range(0, sampleText.length()).mapToObj(sampleText::charAt);

        System.out.println("Words counted with Sequential Stream: " +
                wordCounter.countWordsWithStream(sampleSequentialTextStream) + "\n");

        Stream<Character> sampleParallelTextStream =
                IntStream.range(0, sampleText.length()).mapToObj(sampleText::charAt).parallel();

        System.out.println("Words counted with Parallel Stream: "+
                wordCounter.countWordsWithStream(sampleParallelTextStream));
    }

    private int countWordsIteratively(String input) {
        int counter = 0;
        boolean lastSpace = false;

        for (char c : input.toCharArray()) {
            if (Character.isWhitespace(c))
                lastSpace = true;
            else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    private int countWordsWithStream(Stream<Character> input) {
        return input.reduce(
                new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine)
                .getCounter();
    }
}

class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {
        System.out.printf("Called accumulate(%s), %s\n", c, this);
        if (Character.isWhitespace(c))
            return lastSpace ? this : new WordCounter(counter, true);
        else
            return lastSpace ? new WordCounter(counter + 1, false) : this;

    }

    public WordCounter combine(WordCounter wordCounter) {
        System.out.printf("Called combine(%s) with this %s\n", wordCounter, this);
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return "WordCounter{" +
                "counter=" + counter +
                ", lastSpace=" + lastSpace +
                '}';
    }
}