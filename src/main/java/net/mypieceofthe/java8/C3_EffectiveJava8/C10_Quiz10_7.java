package net.mypieceofthe.java8.C3_EffectiveJava8;

import com.google.common.primitives.Ints;

import java.util.Optional;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 * Created by kgolebiowski on 04/05/2017.
 */
public class C10_Quiz10_7 {

    Properties props = new Properties();

    public C10_Quiz10_7() {
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");
    }

    public static void main(String[] args) {
        new C10_Quiz10_7().runQuizTests();
    }

    private void runQuizTests() {
        //DurationPropertyReader underTest = new IterativeDurationPropertyReader();
        DurationPropertyReader underTest = new OptionalBasedDurationPropertyReader();

        assertEquals(5, underTest.readDuration(props, "a"));
        assertEquals(0, underTest.readDuration(props, "b"));
        assertEquals(0, underTest.readDuration(props, "c"));
        assertEquals(0, underTest.readDuration(props, "d"));
    }
}

interface DurationPropertyReader {
    int readDuration(Properties props, String name);
}

class IterativeDurationPropertyReader implements DurationPropertyReader {
    @Override
    public int readDuration(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            Integer i = Ints.tryParse(value);
            if (i != null && i > 0) {
                return i;
            }
        }
        return 0;
    }
}

class OptionalBasedDurationPropertyReader implements DurationPropertyReader {
    @Override
    public int readDuration(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .map(Ints::tryParse).filter(value -> value > 0).orElse(0);
    }
}


/*
    // Helper method for parsing integers to Optionals if Guava is absent

    private Optional<Integer> parseInt(String input) {
        try {
            return Optional.of(input).map(Integer::parseInt);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
 */