package net.mypieceofthe.java8.C3_EffectiveJava8;

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
        assertEquals(5, readDuration(props, "a"));
        assertEquals(0, readDuration(props, "b"));
        assertEquals(0, readDuration(props, "c"));
        assertEquals(0, readDuration(props, "d"));
    }

    private int readDuration(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException nfe) {
            }
        }
        return 0;
    }
}
