package org.amaap.task.theliftkata;

import java.util.ArrayList;
import java.util.List;


public class Lift {

    private final int capacity;
    private int currentFloor;
    private String status;
    private String direction;
    private List<Integer> waitingList = new ArrayList<>();
    private List<Call> requestList = new ArrayList<>();

    public Lift(int capacity, int currentFloor, String status, String direction) {
        this.capacity = capacity;
        this.currentFloor = currentFloor;
        this.status = status;
        this.direction = direction;

    }

    public List<Integer> getWaitingList() {
        return waitingList;
    }

    public List<Call> getRequestList() {
        return requestList;
    }

    public int getCapacity() {
        return capacity;
    }


    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }


    public List<Integer> makeRequestToLift(int waitingFloor) {
        waitingList.add(waitingFloor);

        return waitingList;


    }


    public List<Call> enterToLift() {
        Call call = new Call(4, "UP");
        Call call1 = new Call(5, "UP");

        System.out.println("Lift is opened");
        requestList.add(call);
        requestList.add(call1);
        waitingList.remove(Integer.valueOf(currentFloor));
        System.out.println("Closed");
        return requestList;
    }


    void moveLift() {

        System.out.println("Lift is moving");
        do {


            if (direction.equalsIgnoreCase("Up")) {

                while (currentFloor < 10) {
                    System.out.println("Lift is at  " + currentFloor);
                    currentFloor += 1;
                    if (waitingList.contains(currentFloor)) {

                        for (Call call : requestList) {
                            System.out.println(call.getDestination() + " " + call.getDirection());
                            if (call.getDestination() == currentFloor) requestList.remove(call);
                        }

                        enterToLift();

                    }
                    if (requestList.stream().anyMatch(call -> call.getDestination() == currentFloor)) {
                        System.out.println("Lift is open");
                        requestList.removeIf(call -> call.getDestination() == currentFloor);
                        System.out.println("lift is closed");
                    }


                }
                direction = "Down";

            }

            if (direction.equalsIgnoreCase("Down")) {
                while (currentFloor > 0) {
                    System.out.println("Lift is coming back at " + currentFloor);
                    currentFloor -= 1;
                    if (waitingList.contains(currentFloor)) {
                        for (Call call : requestList) {
                            System.out.println(call.getDestination() + " " + call.getDirection());

                            if (call.getDestination() == currentFloor) requestList.remove(call);
                        }
                    }

                    if (requestList.stream().anyMatch(call -> call.getDestination() == currentFloor)) {
                        System.out.println("Lift is open");
                        requestList.removeIf(call -> call.getDestination() == currentFloor);
                        System.out.println("lift is closed");
                    }
                }
                direction = "UP";

            }

        }
        while (!waitingList.isEmpty() && !requestList.isEmpty());

        System.out.println(waitingList.size());
        System.out.println(requestList.size());

    }


}
