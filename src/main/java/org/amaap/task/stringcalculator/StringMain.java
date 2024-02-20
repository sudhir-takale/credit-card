package org.amaap.task.stringcalculator;

import static org.amaap.task.stringcalculator.StringCalculator.add;

public class StringMain {
    public static void main(String[] args) {
        System.out.println(add("175.2,\n35,-3,"));
        System.out.println(add("23\n,175.2,\n35"));
    }
}
