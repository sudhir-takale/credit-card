package org.amaap.task.bustask;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Bus {

    private static List<Integer> availableSeats;
    private int initialCapacity;
    private String source;
    private String destination;
    private List<Passenger> passengerList;
    private int fareCollection;
    private int fare;
    private List<Passenger> waitingPassengerList;

    public List<Passenger> getWaitingPassengerList() {
        return waitingPassengerList;
    }

    public Bus(int initialCapacity, String source, String destination) {
        this.initialCapacity = initialCapacity;
        this.source = source;
        this.destination = destination;
        availableSeats = new ArrayList<>();
        for (int i = 1; i <= initialCapacity; i++) {
            availableSeats.add(i);
        }
        passengerList = new ArrayList<>();
        fareCollection = 0;
        this.fare = 400;
    }

    public int getFare() {
        return fare;
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
        String message = "";

        if (availableSeats.isEmpty()) {

            waitingPassengerList = new ArrayList<>();
            waitingPassengerList.add(passenger);
            return "Your ticket has been added in waiting list";

        }
        if (availableSeats.size() <= initialCapacity) {
            System.out.println("Available Seat Numbers - ");
            for (int i : availableSeats) System.out.print(i + " ");
            System.out.println();

            passengerList.add(passenger);
            availableSeats.remove(Integer.valueOf(passenger.getSeatNumber()));

            fareCollection += fare;
            message = "Your ticket has been booked!";

        }
        return message;

    }


    public List<Integer> showAvailableSeats() {

        return availableSeats;
    }

    public LinkedHashMap<Integer, String> showAllPassenger() {

        LinkedHashMap<Integer, String> allPassenger = new LinkedHashMap<Integer, String>();

        if (passengerList.isEmpty()) return allPassenger;
        for (Passenger passenger : passengerList) {

            allPassenger.put(passenger.getId(), passenger.getName());
            System.out.print(passenger.getId() + " " + passenger.getName() + " ");
            System.out.println();

        }
        return allPassenger;
    }


//    public int cancelTicket(int seatNumber) {
//        Passenger passenger = waitingPassengerList.get(0);
//        for(Passenger p : passengerList) {
//            if(p.getSeatNumber() == seatNumber) {
//
//                passe
//
//            }
//
//
//
//        }
//
//
//    }
}
