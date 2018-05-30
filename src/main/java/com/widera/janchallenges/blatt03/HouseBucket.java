package com.widera.janchallenges.blatt03;

import java.util.*;

public class HouseBucket {

    private final TreeSet<House> houses = new TreeSet<>();
    private boolean valid = true;

    private HouseBucketLink linkToLeft;
    private HouseBucketLink linkToRight;

    public HouseBucket(House... houses) {
        this.houses.addAll(Arrays.asList(houses));
    }

    public TreeSet<House> houses() {
        return houses;
    }

    public void invalid() {
        this.valid = false;
    }

    public boolean isValid() {
        return this.valid;
    }

    public HouseBucketLink getLinkToLeft() {
        return linkToLeft;
    }

    public void setLinkToLeft(HouseBucketLink linkToLeft) {
        this.linkToLeft = linkToLeft;
    }

    public HouseBucketLink getLinkToRight() {
        return linkToRight;
    }

    public void setLinkToRight(HouseBucketLink linkToRight) {
        this.linkToRight = linkToRight;
    }

    @Override
    public String toString() {
        return badLuck() + ", " + this.valid + ", " + Arrays.toString(houses.toArray());
    }

    public double badLuck() {
        double sum = 0;
        for (House house : houses) {
            sum += Math.pow((double)house.number() - busStop(), 2d);
        }
        return sum;
    }

    public double busStop() {
        double middleOfHouseNumbers = ((double)houses.stream().mapToInt(house -> house.number()).sum()) / (double)houses.size();
        return middleOfHouseNumbers;
    }

    public List<HouseBucket> split() {
        List<HouseBucket> result = new ArrayList<>();
        House leftHouseOfMaxDistance = null;
        House lastHouse = null;
        int maxDistance = 0;
        for (House house : this.houses()) {
            if (lastHouse != null) {
                int distance = house.number() - lastHouse.number();
                if (distance > maxDistance) {
                    leftHouseOfMaxDistance = lastHouse;
                    maxDistance = distance;
                }
            }
            lastHouse = house;
        }

        System.out.println("maxDistance: " + maxDistance);
        HouseBucket leftSplit = new HouseBucket();
        HouseBucket rightSplit = new HouseBucket();
        boolean insertLeft = true;
        for (House house : houses()) {
            if (insertLeft) {
                leftSplit.houses().add(house);
            } else {
                rightSplit.houses().add(house);
            }
            if (house.equals(leftHouseOfMaxDistance)) {
                insertLeft = false;
            }
        }
        System.out.println("left: " + leftSplit);
        System.out.println("right: " + rightSplit);
        result.add(leftSplit);
        result.add(rightSplit);
        return result;
    }
}
