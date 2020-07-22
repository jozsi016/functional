package com.nagy.functional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NullChecks {
    public static void main(String[] args) {
        Map<String, Car> owners = new HashMap<>();
        owners.put("Shiela", Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Shila"));
        owners.put("Librarian", Car.withGasColorPassengersAndTrunk(3,"Octarine", "Rincewind", "Ridcules"));
        owners.put("Ogg", Car.withGasColorPassengersAndTrunk(9, "Black", "Weatherwax", "Magrat"));

        String owner = "Shiela";
        Optional<Map<String, Car>> ownersOpt = Optional.of(owners);
        ownersOpt
                .map(m -> m.get(owner))
                .map(x -> x.getTrunkContentsOpt().map(l -> l.toString()).orElse("nothing") )
                .map(x -> owner + " has " + x + " in the car")
                .ifPresent(m -> System.out.println(m));

        System.out.println("-------------------------");



    }
}
