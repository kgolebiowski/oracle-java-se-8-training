package net.mypieceofthe.java8.C3_EffectiveJava8;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.util.Optional;

/**
 * Created by kgolebiowski on 04/05/2017.
 */
public class C10_Optionals {
    private Optional<Person> personWithCar = Optional.of(new Person(new Car(new Insurance("ABCD/123"))));
    private Optional<Person> personWithoutCar = Optional.of(new Person());
    private Optional<Person> personWithCarWithoutInsurance = Optional.of(new Person(new Car(null)));
    private Optional<Person> personEmpty = Optional.empty();

    public static void main(String[] args) throws JsonProcessingException {
        new C10_Optionals()
                .testBasicOptionals()
                .testOptionalsForDifferentStructures()
                .testJacksonSerialization();
    }

    private C10_Optionals testBasicOptionals() {
        Optional<Insurance> optInsurance = Optional.of(new Insurance("ASD"));
        Optional<String> insuranceName = optInsurance.map(Insurance::getName);

        insuranceName.ifPresent(System.out::println);

        System.out.println();

        return this;
    }

    private C10_Optionals testOptionalsForDifferentStructures() {
        //Optional<Optional<Car>> car = somePerson.map(Person::getCar); // regular map returns Optional<Optional<Car>> !
        System.out.println(getInsuranceNumber(personWithCar));
        System.out.println(getInsuranceNumber(personWithoutCar));
        System.out.println(getInsuranceNumber(personWithCarWithoutInsurance));
        System.out.println(getInsuranceNumber(personEmpty));

        return this;
    }

    private String getInsuranceNumber(Optional<Person> person) {
        return person
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    private C10_Optionals testJacksonSerialization() throws JsonProcessingException {
        System.out.println(
                getJacksonObjectMapper().writeValueAsString(personWithCar.get()));
        System.out.println(
                getJacksonObjectMapper().writeValueAsString(personWithCarWithoutInsurance.get()));
        return this;
    }

    private ObjectMapper getJacksonObjectMapper() {
        return new ObjectMapper()
                .registerModule(new Jdk8Module())
                .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }
}

class Person {
    private Optional<Car> car;

    Person() {
        this(null);
    }

    Person(Car car) {
        this.car = Optional.ofNullable(car);
    }

    Optional<Car> getCar() {
        return car;
    }

    String getPersonName() {
        return "John Smith";
    }
}

class Car {
    private Optional<Insurance> insurance;

    Car(Insurance insurance) {
        this.insurance = Optional.ofNullable(insurance);
    }

    Optional<Insurance> getInsurance() {
        return insurance;
    }

    public String getRegNumber() {
        return "123 ABCD";
    }
}

class Insurance {
    private String name;

    String getName() {
        return name;
    }

    Insurance(String name) {
        this.name = name;
    }
}