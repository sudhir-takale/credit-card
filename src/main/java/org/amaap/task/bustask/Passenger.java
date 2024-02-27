package org.amaap.task.bustask;

public class Passenger {
    private int id;
    private String name;
    private int seatNumber;

    Passenger(int id, String name, int seatNumber) {
        this.id = id;
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
