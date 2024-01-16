package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.FlightContainer;
import org.example.service.ProcessingService;
import org.example.service.impl.ProcessingServiceImpl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

//@Slf4j(topic = "MAIN_APP")
public class App {



    public static void main(String[] args) {
//        if (args == null || Arrays.asList(args).isEmpty()) {
//            System.err.println("There is no path to source file! Exiting...");
//            return;
//        }
//        String path = args[0];

        //log.info("Path to source file: {}", path);
        Scanner scanner = new Scanner(System.in);

        String path = scanner.nextLine();

        System.out.printf("Path to source file: %s\n", path);
        new App().runTheApp(path);
    }

    private void runTheApp(String path) {
        FlightContainer flights = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            flights = objectMapper.readValue(new File(path), FlightContainer.class);
        } catch (IOException e) {
            //log.error("Error reading source file: {}", e.getMessage());
            System.err.printf("Error reading source file: %s\n", e.getMessage());
        }
        if (flights == null || flights.getTickets().isEmpty()) {
            //log.info("There were no flights parsed! Exiting...");
            System.err.println("There were no flights parsed! Exiting...");
            return;
        }

        ProcessingService processingService = new ProcessingServiceImpl();

        processingService.getMinFlightTime(flights.getTickets());

        processingService.getAverageAndMedianPriceDifference(flights.getTickets());

        System.out.println("Program finished! Exiting...");
    }
}