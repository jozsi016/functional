package com.nagy.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

class PassengerCountOrder implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        return o1.getPassengers().size() - o2.getPassengers().size();
    }
}

public class CarScratch {

//    public static <E> ToIntFunction<E> compareWithThis(E target, Comparator<E> comp) {
//        return x -> comp.compare(target, x);
//    }
    public static <E> void showAll(List<E> lc) {
        for (E c : lc) {
            System.out.println(c);
        }
        System.out.println("---------------------------");
    }

    public static <E> List<E> getByCriterion(Iterable<E> in, Predicate<E> crit) {
        List<E> output = new ArrayList<>();
        for (E c : in) {
            if (crit.test(c)) {
                output.add(c);
            }
        }
        return output;
    }

    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                Car.withGasColorPassengers(3,"Octarine", "Rincewind", "Ridcully"),
                Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr Mahmoud"),
                Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo")
        );

        Predicate<Car> level7 = Car.getGasLevelCarCriterion(7);
        Predicate<Car> notLevel7 = level7.negate();
        Predicate<Car> or = level7.or(notLevel7);

        showAll(getByCriterion(cars, or));
        Car bert = Car.withGasColorPassengers(5, "Blue");
        Predicate<Car> compareWithInt = Car.getGasLevelCarCriterion(5);
        showAll(getByCriterion(cars, compareWithInt));
    }
}
