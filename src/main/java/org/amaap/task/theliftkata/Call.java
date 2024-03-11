package org.amaap.task.theliftkata;

public class Call {
    private int destination;
    private String direction;

    public Call(int destination, String direction) {
        this.destination = destination;
        this.direction = direction;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
