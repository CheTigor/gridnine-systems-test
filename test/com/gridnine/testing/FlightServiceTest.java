package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FlightServiceTest {

    @Test
    public void filterHasNotWrongDateSegmentsTest() {
        final List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        final List<Predicate<Flight>> allPredicates = List.of(Flight::hasNotWrongDateSegments);
        final List<Flight> sortedFlights = FlightService.filtering(flights, allPredicates);
        flights.remove(3);
        Assertions.assertEquals(flights, sortedFlights);
    }

    @Test
    public void filterComplexTest() {
        final List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        final List<Predicate<Flight>> allPredicates = List.of(Flight::hasNotWrongDateSegments,
                Flight::allDepartureAfterNow, f -> f.getAllGroundTime() < 2 * 60 * 60);
        final List<Flight> sortedFlights = FlightService.filtering(flights, allPredicates);
        flights.remove(5);
        flights.remove(4);
        flights.remove(3);
        flights.remove(2);
        Assertions.assertEquals(flights, sortedFlights);
    }
}
