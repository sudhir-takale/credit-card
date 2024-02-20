package org.amaap.task.roombooking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomHandler {
    static List<Booking> booking = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    List<Room> freeRoomList = new ArrayList<Room>();

    public void showFreeRoom() {

        System.out.println("Showing available rooms");
        for (Room room : Room.rooms) {

            if (room.isStatus()) {
                freeRoomList.add(room);
                System.out.print(room.getRoomName() + " | ");
            }
        }
        System.out.println();

    }

    public String bookNewRoom() {

        System.out.println("Choose your Room");

        System.out.println("Available Room Names : ");
        for (Room room : freeRoomList) {
            System.out.print(room.getRoomName() + " | ");
        }
        System.out.println();
        System.out.println("Enter room Name");
        String roomName = scanner.nextLine();

        if (Room.rooms.contains(roomName)) return "Invalid room Name";

        System.out.println("Enter client id");
        int clientId = scanner.nextInt();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Enter your arrival Date dd/MM/yyyy:");
        String arrival = scanner.next();
        LocalDate arrivalDate = LocalDate.parse(arrival, dateFormatter);

        System.out.println("Enter your departure Date dd/MM/yyyy:");
        String departure = scanner.next();
        LocalDate departureDate = LocalDate.parse(departure, dateFormatter);
        Booking b = new Booking(clientId, roomName, arrivalDate, departureDate);
        for (Room room : freeRoomList) {

            if (room.getRoomName().equalsIgnoreCase(roomName))
                room.setStatus(false);
        }
        booking.add(b);


        return ("Room has been Booked Successfully." + " Your Room Name is " + roomName +
                " You need to free room on/before " + departureDate);
    }

    public String makeRoomsFree() {
        Booking book = new Booking();
        for (Room room : Room.rooms) {

            if (book.getDepartureDate().equals(LocalDate.now())) {
                room.setStatus(true);
            }
        }
        return "Makes room freed";
    }

}
