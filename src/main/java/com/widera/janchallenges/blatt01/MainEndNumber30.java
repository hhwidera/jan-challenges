package com.widera.janchallenges.blatt01;

public class MainEndNumber30 {

    public static void main(String[] args) {
        FiniteStateMachine fsm = new FiniteStateMachine();
        System.out.println("possible paths:");
        fsm.simulate(30).forEach(marker -> System.out.println(marker.steps()));
    }

}
