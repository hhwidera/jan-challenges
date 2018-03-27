package com.widera.janchallenges.blatt01;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FiniteStateMachineTest {

    @Test
    public void test() {
        List<Marker> result = new FiniteStateMachine().simulate(30L);
        assertEquals(1, result.size());
        assertEquals("NOONWSOOWNNO", result.get(0).steps());
    }

}