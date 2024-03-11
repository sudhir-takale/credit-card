package org.amaap.task.theliftkata;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LiftTest {
    Lift lift = new Lift(10, 0, "Closed", "UP");

    @Test
    void shouldAbleToInitializeLift() {


        assertEquals(0, lift.getCurrentFloor());
        assertEquals(10, lift.getCapacity());
        assertEquals("Closed", lift.getStatus());
        assertEquals("UP", lift.getDirection());

    }


    @Test
    void shouldAbleToMakeRequestToLift() {
        List<Integer> result = lift.makeRequestToLift(2);
        assertEquals(1, result.size());
    }

    @Test
    void shouldAbleToEnterToLiftAndMakeACall() {
        List<Integer> result = lift.makeRequestToLift(2);
        assertEquals(1, result.size());
        List<Call> destinationList = lift.enterToLift();
        assertEquals(2, destinationList.size());
    }

    @Test
    void shouldAbleToDeliverPassengerToDestinationFloor() {
        Lift lift = new Lift(10, 0, "Closed", "UP");

        List<Integer> result1 = lift.makeRequestToLift(2);
        lift.moveLift();
        assertEquals(0, lift.getRequestList().size());
        assertEquals(0, lift.getWaitingList().size());


    }

}

