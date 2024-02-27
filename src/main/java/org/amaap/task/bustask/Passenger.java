package org.amaap.task.bustask;

public class Passenger {
    private static int idCounter = 0;
    private int id;
    private String name;
    private int seatNumber;

    Passenger(String name, int seatNumber) {

        idCounter += 1;
        this.id = idCounter;
        this.name = name;
        this.seatNumber = seatNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSeatNumber() {
        return seatNumber;
    }


}
