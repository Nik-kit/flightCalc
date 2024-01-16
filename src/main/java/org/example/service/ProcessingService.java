package org.example.service;

import org.example.dto.TicketsItem;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ProcessingService {
    void getMinFlightTime(List<TicketsItem> tickets);

    void getAverageAndMedianPriceDifference(List<TicketsItem> tickets);

    LocalTime getTimeDifference(LocalDateTime departureTime, LocalDateTime arrivalTime);
}
