package org.amaap.task.bustask;

import org.junit.jupiter.api.Test;

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
    void shouldAbleToBoardAPassenger() {

        Passenger passenger = new Passenger(1, "Sudhir", 12);
        Bus bus = new Bus(50, "Solapur", "Pune");

        String status = bus.bookSeat(passenger);
        assertEquals("Your ticket has been booked!", status);
        assertEquals(49, bus.getAvailableSeats().size());
        assertEquals(400, bus.getFareCollection());
        assertEquals(1, bus.getPassengerList().size());


    }


}
