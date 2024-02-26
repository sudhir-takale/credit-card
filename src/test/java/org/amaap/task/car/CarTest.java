package org.amaap.task.car;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarTest {
    Car car = new Car(40, 12);

    @Test
    void shouldAbleToCreateCarWithEmptyFuel() {

        assertEquals(40, car.getFuelCapacity());
        assertEquals(12, car.getAvg());
        assertEquals(0, car.getDistanceCovered());
        assertEquals(0, car.getDistanceEmpty());
        assertEquals(0, car.getFuelInLiter());
    }

    @Test
    void shouldAbleToRefillFuelInCar() throws OverFlowException {
        int refilledFuel = car.refill(5);
        assertEquals(5, car.getFuelInLiter());

        assertEquals(5, refilledFuel);
        assertThrows(OverFlowException.class, () -> car.refill(70));


    }

    @Test
    void shouldAbleToReturnDistanceToEmpty() throws OverFlowException, InsufficientFuelException {
        car.refill(30);
        int fuel = car.drive(240);
        int distance = car.distanceToEmpty(fuel, 12);
        assertEquals(120, distance);
    }

    @Test
    void shouldAbleToReturnDistanceCoveredInKm() throws OverFlowException, InsufficientFuelException {
        car.refill(30);
        car.drive(200);
        assertEquals(200, car.getDistanceCovered());

    }

    @Test
    void shouldAbleToConsumeFuelWhileDriving() throws OverFlowException, InsufficientFuelException {
        car.refill(30);
        car.drive(36);
        assertEquals(36, car.getDistanceCovered());
        assertEquals(27, car.getFuelInLiter());


    }

    @Test
    void shouldAbleToThrowExceptionForInsufficientFuel() throws OverFlowException, InsufficientFuelException {
        car.refill(40);
        car.drive(360);
        assertThrows(InsufficientFuelException.class, () -> {
            car.drive(150);
        });


    }
}
