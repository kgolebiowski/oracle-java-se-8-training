package net.mypieceofthe.java8.java8inaction.C2_streams;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by kgolebiowski on 03/05/2017.
 */
public class C7_3_WordCounter {

    private static final String SAMPLE_TEXT = " Nel   mezzo del cammin  di nostra  vita " +
            "mi  ritrovai in una  selva oscura" +
            " ché la  dritta via era   smarrita ";

    public static void main(String[] args) {

        C7_3_WordCounter wordCounter = new C7_3_WordCounter();

        System.out.println("Words counted iteratively: " +
                wordCounter.countWordsIteratively(SAMPLE_TEXT) + "\n");

        Stream<Character> sampleSequentialTextStream =
                IntStream.range(0, SAMPLE_TEXT.length()).mapToObj(SAMPLE_TEXT::charAt);

        System.out.println("Words counted with Sequential Stream: " +
                wordCounter.countWordsWithStream(sampleSequentialTextStream) + "\n");

        Stream<Character> sampleParallelTextStream =
                IntStream.range(0, SAMPLE_TEXT.length()).mapToObj(SAMPLE_TEXT::charAt).parallel();

        System.out.println("Words counted with Parallel Stream: "+
                wordCounter.countWordsWithStream(sampleParallelTextStream) + "\n");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SAMPLE_TEXT);
        Stream<Character> sampleParallelTextStreamWithSpliterator =
                StreamSupport.stream(spliterator, true);

        System.out.println("Words counted with Parallel Stream with custom Spliterator: "+
                wordCounter.countWordsWithStream(sampleParallelTextStreamWithSpliterator));
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

    WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    WordCounter accumulate(Character c) {
        System.out.printf("WordCounter.accumulate(%s), %s\n", c, this);
        if (Character.isWhitespace(c))
            return lastSpace ? this : new WordCounter(counter, true);
        else
            return lastSpace ? new WordCounter(counter + 1, false) : this;

    }

    WordCounter combine(WordCounter wordCounter) {
        System.out.printf("WordCounter.combine(%s) with this %s\n", wordCounter, this);
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    int getCounter() {
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

class WordCounterSpliterator implements Spliterator<Character> {
    private final String string;
    private int currentChar = 0;

    WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        System.out.printf("WordCounterSpliterator.tryAdvance(%s)\n", action);
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        System.out.printf("WordCounterSpliterator.trySplit()\n");
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null;
        }
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
            if (Character.isWhitespace(string.charAt(splitPos))) {
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        System.out.printf("WordCounterSpliterator.estimateSize()\n");
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        System.out.printf("WordCounterSpliterator.characteristics()\n");
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}