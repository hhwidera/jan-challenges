package com.widera.janchallenges.blatt03;

public class House implements Comparable<House> {

    private final int houseNumber;

    public House(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int number() {
        return this.houseNumber;
    }

    @Override
    public int compareTo(House o) {
        return this.houseNumber - o.houseNumber;
    }

    @Override
    public String toString() {
        return "" + houseNumber;
    }
}
