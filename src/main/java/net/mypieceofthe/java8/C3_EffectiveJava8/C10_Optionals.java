package net.mypieceofthe.java8.C3_EffectiveJava8;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import net.mypieceofthe.java8.domain.carsandinsurances.Car;
import net.mypieceofthe.java8.domain.carsandinsurances.Insurance;
import net.mypieceofthe.java8.domain.carsandinsurances.Person;

import java.util.Optional;

import static net.mypieceofthe.java8.domain.carsandinsurances.PeopleFactory.*;

/**
 * Created by kgolebiowski on 04/05/2017.
 */
public class C10_Optionals {
    public static void main(String[] args) throws JsonProcessingException {
        new C10_Optionals()
                .testBasicOptionals()
                .testOptionalsForDifferentStructures()
                .testJacksonSerialization()
                .testFiltering();
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
        System.out.println(getInsuranceNumber(getPersonWithCar()));
        System.out.println(getInsuranceNumber(getPersonWithCar()));
        System.out.println(getInsuranceNumber(getPersonWithCarWithoutInsurance()));
        System.out.println(getInsuranceNumber(getPersonEmpty()));

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
                getJacksonObjectMapper().writeValueAsString(getPersonWithCar().get()));
        System.out.println(
                getJacksonObjectMapper().writeValueAsString(getPersonWithCarWithoutInsurance().get()));
        return this;
    }

    private ObjectMapper getJacksonObjectMapper() {
        return new ObjectMapper()
                .registerModule(new Jdk8Module())
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    private C10_Optionals testFiltering() {
        getPersonWithCar()
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .filter(insurance -> insurance.getName().equals("ABCD/123")) // true
                //.filter(insurance -> insurance.getName().equals("adsf"))
                .ifPresent(insurance -> System.out.println("OK !"));

        return this;
    }
}