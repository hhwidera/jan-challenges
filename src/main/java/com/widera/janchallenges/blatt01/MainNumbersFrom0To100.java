package com.widera.janchallenges.blatt01;

public class MainNumbersFrom0To100 {

    public static void main(String[] args) {
        FiniteStateMachine fsm = new FiniteStateMachine();

        for (long endValue = 0; endValue <= 100; endValue++) {
            System.out.println(endValue + " - possible paths:");
            fsm.simulate(endValue).forEach(marker -> System.out.println(marker.steps()));
        }
    }

}
