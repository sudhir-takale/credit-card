package org.amaap.task.roombooking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Scanner;

class RoomBookingTest {
    private RoomHandler roomHandler;

    @BeforeEach
    void setUp() {
        roomHandler = new RoomHandler();
    }

    @Test
    void shouldReturnAllRoomsAvailable() {

        roomHandler.showFreeRoom();
        Assertions.assertFalse(!roomHandler.freeRoomList.isEmpty());


    }

    @Test
    void shouldabletobooknewroomfromavailablerooms() {

        roomHandler.freeRoomList.add(new Room("Room2", true));
        roomHandler.scanner = new Scanner("Room2\n123\n01/02/2024\n01/03/2024");
        String result = roomHandler.bookNewRoom();
        String expected = "Room has been Booked Successfully. Your Room Name is Room2 You need to free room on/before 2024-03-01";
        Assertions.assertEquals(expected, result);


    }

//    @Test
//    void shouldFreeRoomsIfDepartureTimeIsPassed() {
//        Booking pastBooking = new Booking(1, "Room1", LocalDate.now().minusDays(1), LocalDate.now().minusDays(1));
//        Room.rooms.add(new Room("Room1", false));
//        // Add the past booking to the list of bookings
//        RoomHandler.booking.add(pastBooking);
//        roomHandler.makeRoomsFree();
//        Assertions.assertTrue(Room.rooms.get(0).isStatus());
//    }

}