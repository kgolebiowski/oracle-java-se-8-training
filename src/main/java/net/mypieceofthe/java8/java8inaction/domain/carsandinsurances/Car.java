package net.mypieceofthe.java8.java8inaction.domain.carsandinsurances;

import java.util.Optional;

/**
 * Created by kgolebiowski on 04/05/2017.
 */
public class Car {
    private Optional<Insurance> insurance;

    public Car(Insurance insurance) {
        this.insurance = Optional.ofNullable(insurance);
    }

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public String getRegNumber() {
        return "123 ABCD";
    }
}
