package org.amaap.task.car;


public class Car {

    private int fuelCapacity;
    private int avg;
    private int distanceCovered;
    private int distanceEmpty;
    private int fuelInLiter;

    Car(int fuelCapacity, int avg) {
        this.fuelCapacity = fuelCapacity;
        this.avg = avg;
        distanceCovered = 0;
        distanceEmpty = 0;
        fuelInLiter = 0;


    }

    public static void main(String[] args) throws OverFlowException, InsufficientFuelException {
        Car car = new Car(40, 12);
        System.out.println(car.getFuelInLiter());
        System.out.println(car.refill(30));
        System.out.println(car.getDistanceCovered());
        System.out.println("FuelInLiter after driving 240km : " + car.drive(240) + " l");
        System.out.println(car.distanceToEmpty(car.getFuelInLiter(), car.getAvg()) + " km");
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public int getAvg() {
        return avg;
    }

    public int getDistanceCovered() {
        return distanceCovered;
    }

    public int getDistanceEmpty() {
        return distanceEmpty;
    }

    public int getFuelInLiter() {
        return fuelInLiter;
    }

    public int refill(int fuel) throws OverFlowException {
        if (isFuelOverfilled(fuel, fuelInLiter)) {
            fuelInLiter += fuel;

        } else {
            throw new OverFlowException("Refill is more than Fuel Capacity");
        }
        return fuelInLiter;
    }

    private boolean isFuelOverfilled(int fuel, int fuelInLiter) {
        return (fuelInLiter + fuel) <= fuelCapacity;

    }

    public int distanceToEmpty(int fuelInLiter, int avg) {
        return (fuelInLiter * avg);

    }

    public int drive(int distance) throws InsufficientFuelException {
        int fuelRequired = distance / avg;
        if (fuelRequired <= fuelInLiter) {
            fuelInLiter -= fuelRequired;
            distanceCovered += distance;
        } else {
            throw new InsufficientFuelException("Fuel is Enough to Drive " + distanceToEmpty(fuelInLiter, avg) + " km");

        }
        return fuelInLiter;

    }

}