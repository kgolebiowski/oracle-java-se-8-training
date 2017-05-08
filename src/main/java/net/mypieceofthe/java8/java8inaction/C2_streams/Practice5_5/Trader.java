package net.mypieceofthe.java8.java8inaction.C2_streams.Practice5_5;

/**
 * Created by kgolebiowski on 29/04/2017.
 */
public class Trader {
    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return "Trader: " + this.name + " in " + this.city;
    }
}