package com.nagy.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class SuperIterable<E> implements Iterable<E> {
    private Iterable<E> self;

    public SuperIterable(Iterable<E> s) {
        self = s;
    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }

    public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
        List<F> results = new ArrayList<>();

        self.forEach(e -> op.apply(e).forEach(f -> results.add(f)));
        return new SuperIterable<>(results);
    }

    public <F> SuperIterable<F> map(Function<E, F> op) {
        List<F> results = new ArrayList<>();
        self.forEach(e -> results.add(op.apply(e)));
        return new SuperIterable<>(results);
    }

    public SuperIterable<E> filter(Predicate<E> pred) {
        List<E> results = new ArrayList<>();
        self.forEach(e -> {
            if (pred.test(e)) {
                results.add(e);
            }
        });
        return new SuperIterable<>(results);
    }

    public static void main(String[] args) {
        SuperIterable<String> strings = new SuperIterable<>(Arrays.asList("LightCoral", "pink", "Orange", "Gold", "plum", "Blue", "limegree"));
        strings.forEach(s -> System.out.println("> " + s));

        SuperIterable<String> upperCase = strings.filter(s -> Character.isUpperCase(s.charAt(0)));
        System.out.println("---------------------------------");
        upperCase.forEach(s -> System.out.println("> " + s));

        System.out.println("---------------------------------");
        strings.filter(x -> Character.isUpperCase(x.charAt(0)))
                .map(x -> x.toUpperCase())
                .forEach(x -> System.out.println(x));

        SuperIterable<Car> carsItr = new SuperIterable<>(Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"  ),
                Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr Mahmoud"),
                Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo")
        ));

        System.out.println("---------------------------------");
        carsItr
                .filter(c -> c.getGasLevel() > 0)
                .map(c -> c.getColor())
                .map(c -> c.toUpperCase())
                .forEach(c -> System.out.println("> " + c));

        System.out.println("---------------------------------");
        carsItr
                .map(c -> c.addGas(4))
                .forEach(c -> System.out.println(">> " + c));

        System.out.println("---------------------------------");
        carsItr
                .filter(c -> c.getPassengers().size() > 3)
                .flatMap(c -> new SuperIterable<>(c.getPassengers()))
                .map(s -> s.toUpperCase())
                .forEach(s -> System.out.println(s));

        System.out.println("---------------------------------");
        carsItr
                .flatMap(c -> new SuperIterable<>(c.getPassengers())
                .map(p -> p + " is ridding in a " + c.getColor() + " car"))
        .forEach(s -> System.out.println(s));

//        carsItr
//                .map(c -> Car.withGasColorPassengers(
//                        c.getGasLevel() + 4,
//                        c.getColor(),
//                        c.getPassengers().toArray(new String[]{})))
//                .forEach(c -> System.out.println(">> " + c));
    }
}
