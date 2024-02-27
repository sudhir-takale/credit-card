package org.amaap.task.bustask;

import java.util.ArrayList;
import java.util.List;

public class Bus {

    private int initialCapacity;
    private String source;
    private String destination;
    private List<Passenger> passengerList;
    private List<Integer> availableSeats;
    private int fareCollection;

    public Bus(int initialCapacity, String source, String destination) {
        this.initialCapacity = initialCapacity;
        this.source = source;
        this.destination = destination;
        this.availableSeats = new ArrayList<>();
        for (int i = 1; i <= initialCapacity; i++) {
            availableSeats.add(i);
        }
        passengerList = new ArrayList<>();
        fareCollection = 0;
    }


    public int getFareCollection() {
        return fareCollection;
    }

    public int getInitialCapacity() {
        return initialCapacity;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public List<Integer> getAvailableSeats() {
        return availableSeats;
    }

    public String bookSeat(Passenger passenger) {

        passengerList.add(passenger);
        availableSeats.remove(passenger.getSeatNumber());

//        Todo:


        return null;
    }
}
