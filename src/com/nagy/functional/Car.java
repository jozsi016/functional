package com.nagy.functional;

import java.util.*;

public class Car {
    private final int gasLevel;
    private final String color;
    private final List<String> passengers;
    private final List<String> trunkContents;

    private Car(int gasLevel, String color, List<String> passengers, List<String> trunkContens) {
        this.gasLevel = gasLevel;
        this.color = color;
        this.passengers = passengers;
        this.trunkContents = trunkContens;
    }

    public static Car withGasColorPassengers(int gas, String color, String... passengers) {
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        Car self = new Car(gas, color, p, null);
        return self;
    }

    public static Car withGasColorPassengersAndTrunk(int gas, String color, String... passengers) {
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        Car self = new Car(gas, color, p, Arrays.asList("jack", "wrench", "spare wheel"));
        return self;
    }

    public int getGasLevel() {
        return gasLevel;
    }

    public String getColor() {
        return color;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public List<String> getTrunkContents() {
        return trunkContents;
    }

    @Override
    public java.lang.String toString() {
        return "Car{" +
                "gasLevel=" + gasLevel +
                ", color='" + color + '\'' +
                ", passengers=" + passengers +
                (trunkContents != null ?
                        ", trunkContents=" + trunkContents : " no trunk"
                ) + '}';
    }

    public static Criterion<Car> getFourPassengersCriterion() {
        return c -> c.getPassengers().size() == 4;
    }

    public static Criterion<Car> getRedCarCriterion() {
        return RED_CAR_CRITERION;
    }

    private static final Criterion<Car> RED_CAR_CRITERION = c -> c.color.equals("Red");


    public static Criterion<Car> getGasLevelCarCriterion(final int threshold) {
        return c -> c.gasLevel >= threshold;
    }

    public static Criterion<Car> getColorCriterion(String... colors) {
        Set<String> colorSet = new HashSet<>(Arrays.asList(colors));
        return c -> colorSet.contains(c.color);
    }

    public static Comparator<Car> getGasComparator() {
        return GAS_COMPARATOR;
    }

    private static final Comparator<Car> GAS_COMPARATOR = (o1, o2) -> o1.getGasLevel() - o2.getGasLevel();
}
