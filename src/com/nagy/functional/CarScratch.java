package com.nagy.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class PassengerCountOrder implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        return o1.getPassengers().size() - o2.getPassengers().size();
    }
}

interface CarCriterion {
    boolean test(Car c);
}

class RedCriterion implements CarCriterion {
    @Override
    public boolean test(Car c) {
        return c.getColor().equals("Red");
    }
}


public class CarScratch {
    public static void showAll(List<Car> lc) {
        for (Car c : lc) {
            System.out.println(c);
        }
        System.out.println("---------------------------");
    }

    public static List<Car> getColoredCars(Iterable<Car> in, String color) {
        List<Car> output = new ArrayList<>();
        for (Car c : in) {
            if (c.getColor().equals(color)) {
                output.add(c);
            }
        }
        return output;
    }

    public static List<Car> getCarsByGasLevel(Iterable<Car> in, int gasLevel) {
        List<Car> output = new ArrayList<>();
        for (Car c : in) {
            if (c.getGasLevel() >= gasLevel) {
                output.add(c);
            }
        }
        return output;
    }

    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr Mahmoud"),
                Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo")
        );
        showAll(cars);
        //  showAll(getColoredCars(cars, "Black"));

        cars.sort(new PassengerCountOrder());

        showAll(cars);
    }
}
