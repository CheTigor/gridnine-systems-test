package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightTest {

    @Test
    public void hasNotWrongDateSegmentsTest() {
        final Segment wrongSeg = new Segment(LocalDateTime.now(), LocalDateTime.now().minusDays(1));
        final Segment seg = new Segment(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        final Flight flight = new Flight(List.of(wrongSeg, seg));
        Assertions.assertFalse(flight.hasNotWrongDateSegments());

        final Flight flight1 = new Flight(List.of(seg));
        Assertions.assertTrue(flight1.hasNotWrongDateSegments());

        final Flight emptyFlight = new Flight(List.of());
        Assertions.assertFalse(emptyFlight.hasNotWrongDateSegments());
    }

    @Test
    public void getAllGroundTimeTest() {
        final Segment seg1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        final Segment seg2 = new Segment(seg1.getArrivalDate().plusSeconds(200), seg1.getArrivalDate().plusDays(1));
        final Flight flight = new Flight(List.of(seg1, seg2));
        Assertions.assertEquals(200, flight.getAllGroundTime());
        //Тип, который возвращает flight.getAllGroundTime() - int. Максимальное возможное количество секунд в годах:
        //68 лет, так что проверять на выход из-за границы не требуется

        final Flight emptyFlight = new Flight(List.of());
        Assertions.assertEquals(0, emptyFlight.getAllGroundTime());
    }

    @Test
    public void allDepartureAfterNowTest() {
        final Segment seg1 = new Segment(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2));
        final Segment seg2 = new Segment(LocalDateTime.now().plusMinutes(1), LocalDateTime.now().plusDays(1));
        final Segment seg3 = new Segment(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(2));
        final Flight flight = new Flight(new ArrayList<>(List.of(seg1, seg2, seg3)));
        Assertions.assertFalse(flight.allDepartureAfterNow());

        flight.getSegments().remove(seg3);
        Assertions.assertTrue(flight.allDepartureAfterNow());

        final Flight emptyFlight = new Flight(List.of());
        Assertions.assertFalse(emptyFlight.allDepartureAfterNow());
    }

}
