package com.gridnine.testing.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bean that represents a flight.
 */
public class Flight {
    private final List<Segment> segments;

    public Flight(final List<Segment> segs) {
        segments = segs;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public int getAllGroundTime() {
        int duration = 0;
        for (int i = 0; i < segments.size() - 1; i++) {
            duration += Duration.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate())
                    .getSeconds();
        }
        return duration;
    }

    public boolean allDepartureAfterNow() {
        if (segments.isEmpty()) {
            return false;
        }
        for (Segment seg : segments) {
            if (seg.getDepartureDate().isBefore(LocalDateTime.now())) {
                return false;
            }
        }
        return true;
    }

    public boolean hasNotWrongDateSegments() {
        if (segments.isEmpty()) {
            return false;
        }
        for (Segment seg : segments) {
            if (seg.isDepartureIsAfterArrival()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}
