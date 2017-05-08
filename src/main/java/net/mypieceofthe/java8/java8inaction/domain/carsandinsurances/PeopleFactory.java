package net.mypieceofthe.java8.java8inaction.domain.carsandinsurances;

import java.util.Optional;

/**
 * Created by kgolebiowski on 04/05/2017.
 */
public class PeopleFactory {
    private static Optional<Person> personWithCar = Optional.of(new Person(new Car(new Insurance("ABCD/123"))));
    private static Optional<Person> personWithoutCar = Optional.of(new Person());
    private static Optional<Person> personWithCarWithoutInsurance = Optional.of(new Person(new Car(null)));
    private static Optional<Person> personEmpty = Optional.empty();

    public static Optional<Person> getPersonWithCar() {
        return personWithCar;
    }

    public static Optional<Person> getPersonWithoutCar() {
        return personWithoutCar;
    }

    public static Optional<Person> getPersonWithCarWithoutInsurance() {
        return personWithCarWithoutInsurance;
    }

    public static Optional<Person> getPersonEmpty() {
        return personEmpty;
    }
}
