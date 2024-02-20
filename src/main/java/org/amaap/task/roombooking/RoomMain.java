package org.amaap.task.roombooking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomMain {

    Scanner scanner = new Scanner(System.in);
    List<Room> freeRoomList = new ArrayList<Room>();

    public static void main(String[] args) {

        for (int i = 1; i <= 10; i++) {
            String roomName = "Room" + i;
            Room room = new Room(roomName, true);
            Room.rooms.add(room);
        }

        RoomHandler roomHandler = new RoomHandler();
        while (true) {

            System.out.println("1 for view available rooms\n2 for book room\n3 For Break");
            int choice = new Scanner(System.in).nextInt();
            if (choice == 1)
                roomHandler.showFreeRoom();
            else if (choice == 2)
                System.out.println(roomHandler.bookNewRoom());
            else if (choice == 3)
                break;
            else
                System.out.println("Your choice is wrong");

        }

    }
}
