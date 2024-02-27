package org.amaap.task.bustask;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BusTest {

    @Test
    void shouldAbleToInitializeBusWithInitialCapacityAndAvailableSeatsWithEmptyPassengerListAndFareCollection() {
//        Bus bus = new Bus();
        Bus bus = new Bus(50, "Solapur", "Pune");
        assertEquals(50, bus.getInitialCapacity());
        assertEquals(50, bus.getAvailableSeats().size());
        assertTrue(bus.getPassengerList().isEmpty());
        assertEquals(0, bus.getFareCollection());
        assertEquals("Solapur", bus.getSource());
        assertEquals("Pune", bus.getDestination());

    }

    @Test
    void shouldReturnListOfAvailableSeats() {

        Bus bus = new Bus(10, "Solapur", "Pune");
        List<Integer> availableSeats = bus.showAvailableSeats();
        int[] seats = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> expectedAvailableSeats = Arrays.stream(seats).boxed().toList();
        assertEquals(expectedAvailableSeats, availableSeats);

    }


    @Test
    void shouldAbleToBoardAPassenger() {

        Bus bus = new Bus(50, "Solapur", "Pune");
        List<Integer> availableSeats = bus.showAvailableSeats();
        Passenger passenger = new Passenger("Sudhir", 12);
        String status = bus.bookSeat(passenger);
        assertEquals("Your ticket has been booked!", status);
        assertEquals(49, bus.getAvailableSeats().size());
        assertEquals(400, bus.getFareCollection());
        assertEquals(1, bus.getPassengerList().size());
    }


    @Test
    void shouldReturnTotalFareCollection() {

        Bus bus = new Bus(50, "Solapur", "Pune");
        List<Integer> availableSeats = bus.showAvailableSeats();
        Passenger passenger = new Passenger("Sudhir", 12);
        Passenger passenger1 = new Passenger("Sudhir", 22);
        Passenger passenger2 = new Passenger("Sudhir", 32);

        String status = bus.bookSeat(passenger);
        String status1 = bus.bookSeat(passenger1);
        String status2 = bus.bookSeat(passenger2);

        int collection = bus.getFareCollection();
        assertEquals(1200, collection);
        assertEquals("Your ticket has been booked!", status);
        assertEquals("Your ticket has been booked!", status1);
        assertEquals("Your ticket has been booked!", status2);
        assertEquals(47, bus.getAvailableSeats().size());
        assertEquals(3, bus.getPassengerList().size());

    }

    @Test
    void shouldReturnListOfNameOfAllPassengers() {

        Bus bus = new Bus(2, "Solapur", "Pune");
        List<Integer> availableSeats = bus.showAvailableSeats();
        Passenger passenger = new Passenger("Sudhir", 1);
        Passenger passenger1 = new Passenger("Amey", 2);
        Passenger passenger2 = new Passenger("Ashok", 3);

        String status = bus.bookSeat(passenger);
        String status1 = bus.bookSeat(passenger1);
        String status2 = bus.bookSeat(passenger2);
        LinkedHashMap<Integer, String> passengerWithIdAndName = new LinkedHashMap<>();

        passengerWithIdAndName.put(passenger.getId(), "Sudhir");
        passengerWithIdAndName.put(passenger1.getId(), "Amey");
//        passengerWithIdAndName.put(passenger2.getId(), "Ashok");
        LinkedHashMap<Integer, String> actualPassengerWithIdAndName = bus.showAllPassenger();
        assertEquals(passengerWithIdAndName, actualPassengerWithIdAndName);
        assertEquals("Your ticket has been added in waiting list", status2);
    }


    @Test
    void shouldMaintainTheWaitingListIfBusIsFull() {
        Bus bus = new Bus(2, "Solapur", "Pune");
        List<Integer> availableSeats = bus.showAvailableSeats();
        Passenger passenger = new Passenger("Sudhir", 1);
        Passenger passenger1 = new Passenger("Amey", 2);
        Passenger passenger2 = new Passenger("Ashok", 3);

        String status = bus.bookSeat(passenger);
        String status1 = bus.bookSeat(passenger1);
        String status2 = bus.bookSeat(passenger2);

        assertEquals(1, bus.getWaitingPassengerList().size());


    }


//@Test
//    void shouldAbleToCancelTheTicketAndSeatToWaitingListPassenger() {
//
//    Bus bus = new Bus(2, "Solapur", "Pune");
//    List<Integer> availableSeats = bus.showAvailableSeats();
//    Passenger passenger = new Passenger( "Sudhir", 1);
//    Passenger passenger1 = new Passenger("Amey", 2);
//    Passenger passenger2 = new Passenger("Ashok", 3);
//    Passenger passenger3 = new Passenger("Rahul", 4);
//
//    String status = bus.bookSeat(passenger);
//    String status1 = bus.bookSeat(passenger1);
//    String status2 = bus.bookSeat(passenger2);
//    int seatNumber = bus.cancelTicket(2);
//
//
//    assertEquals(passenger1.getSeatNumber(),passenger2.getSeatNumber());
//    assertEquals(1, bus.getWaitingPassengerList().size());
//
//
//}


}
