package org.amaap.task.roombooking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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



    @Test
    void shouldFreeRooms() {

        String result = roomHandler.makeRoomsFree();
        assertEquals("Makes room freed", result);

    }


}