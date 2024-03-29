package org.example.service.impl;

import org.example.dto.TicketsItem;
import org.example.service.ProcessingResultsPrinter;
import org.example.service.ProcessingService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class ProcessingServiceImpl implements ProcessingService {

    private ProcessingResultsPrinter processingResultsPrinter;

    public ProcessingServiceImpl() {
        this.processingResultsPrinter = new ProcessingResultsPrinterImpl();
    }

    @Override
    public void getMinFlightTime(List<TicketsItem> tickets) {

        Map<String, LocalTime> minFlightTimeMap = new HashMap<>();

        for (TicketsItem ticket : tickets) {
            if (ticket.getOrigin().equals("VVO") && ticket.getDestination().equals("TLV")) {
                LocalTime timeDifference = getTimeDifference(ticket.getDepartureDate().atTime(ticket.getDepartureTime()),
                        ticket.getArrivalDate().atTime(ticket.getArrivalTime()));

                if (minFlightTimeMap.containsKey(ticket.getCarrier())) {
                    if (minFlightTimeMap.get(ticket.getCarrier()).isAfter(timeDifference)) {
                        minFlightTimeMap.put(ticket.getCarrier(), timeDifference);
                    }
                } else {
                    minFlightTimeMap.put(ticket.getCarrier(), timeDifference);
                }
            }
        }

        for (Map.Entry<String, LocalTime> entry : minFlightTimeMap.entrySet()) {
            processingResultsPrinter.printResults("The minimum flight time for the carrier " + entry.getKey() + " is " + entry.getValue());
        }

    }

    @Override
    public void getAverageAndMedianPriceDifference(List<TicketsItem> tickets) {

        List<Integer> pricesList = new ArrayList<>();

        for (TicketsItem ticket : tickets) {
            if (ticket.getOrigin().equals("VVO") && ticket.getDestination().equals("TLV")) {
                pricesList.add(ticket.getPrice());
            }
        }

        int[] pricesInt = pricesList.stream().mapToInt(i -> i).toArray();

        double average = Arrays.stream(pricesInt).average().getAsDouble();

        Arrays.sort(pricesInt);

        double median;

        if (pricesInt.length % 2 == 0) {
            double largerNumberFromMiddle = pricesInt[pricesInt.length / 2];
            double lowerNumberFromMiddle = pricesInt[pricesInt.length / 2 - 1];

            median = (largerNumberFromMiddle + lowerNumberFromMiddle) / 2;
        } else {
            median = pricesInt[(pricesInt.length - 1) / 2];
        }

        double result = Math.abs(average - median);

        processingResultsPrinter.printResults("Difference between the average price " +
                "and the median price for a flight between the cities of Vladivostok and Tel Aviv is: " + result);
    }

    @Override
    public LocalTime getTimeDifference(LocalDateTime departureTime, LocalDateTime arrivalTime) {

        Duration duration = Duration.between(departureTime, arrivalTime);

        String hours = String.format("%02d", duration.toHoursPart());

        String minutes = String.format("%02d", duration.toMinutesPart());

        LocalTime localDurationTime = LocalTime.parse(hours + ":" + minutes);

        return localDurationTime;
    }


}
