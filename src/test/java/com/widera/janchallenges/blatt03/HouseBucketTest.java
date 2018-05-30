package com.widera.janchallenges.blatt03;

import org.junit.Test;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class HouseBucketTest {

    @Test
    public void test() {
        HouseBucket a = new HouseBucket();
        a.houses().addAll(IntStream.of(9249).boxed().map(number -> new House(number)).collect(Collectors.toList()));
        HouseBucket b = new HouseBucket();
        b.houses().addAll(IntStream.of(9251, 9254, 9261, 9270, 9288).boxed().map(number -> new House(number)).collect(Collectors.toList()));

        System.out.println(a);
        System.out.println(b);
        assertEquals(Double.valueOf(58.8d), Double.valueOf(a.badLuck() + b.badLuck()));
    }

}