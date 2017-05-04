package net.mypieceofthe.java8.domain.carsandinsurances;

import java.util.Optional;

/**
 * Created by kgolebiowski on 04/05/2017.
 */
public class Person {
    private Optional<Car> car;

    public Person() {
        this(null);
    }

    public Person(Car car) {
        this.car = Optional.ofNullable(car);
    }

    public Optional<Car> getCar() {
        return car;
    }

    public String getPersonName() {
        return "John Smith";
    }
}