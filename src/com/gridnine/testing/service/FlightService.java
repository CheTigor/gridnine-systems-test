package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightService {

    public static List<Flight> filtering(List<Flight> flights, List<Predicate<Flight>> allPredicates) {
        Predicate<Flight> compositePredicate = allPredicates.stream().reduce(w -> true, Predicate::and);
        return flights.stream().filter(compositePredicate).collect(Collectors.toList());
    }

}
