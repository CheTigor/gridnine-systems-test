package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightService;

import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Создание тестовых перелетов...");
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println(flights);
        System.out.println("Перелеты успешно созданы");
        System.out.println("Исключить из списка перелеты по фильтрам:");
        System.out.println("1) вылет до текущего момента времени");
        List<Predicate<Flight>> allPredicates = List.of(Flight:: allDepartureAfterNow);
        System.out.println(FlightService.filtering(flights, allPredicates));
        System.out.println("2) имеются сегменты с датой прилёта раньше даты вылета");
        allPredicates = List.of(Flight::hasNotWrongDateSegments);
        System.out.println(FlightService.filtering(flights, allPredicates));
        System.out.println("3) общее время, проведенное на земле, превышает 2 часа");
        allPredicates = List.of(f -> f.getAllGroundTime() < 2 * 60 * 60);
        System.out.println(FlightService.filtering(flights, allPredicates));
    }
}