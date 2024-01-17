package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.dto.FlightContainer;
import org.example.service.ProcessingService;
import org.example.service.impl.ProcessingServiceImpl;

import java.io.IOException;
import java.io.InputStream;

//@Slf4j(topic = "MAIN_APP")
public class App {

    public static void main(String[] args) {
        new App().runTheApp();
    }

    private void runTheApp() {
        FlightContainer flights = null;
        try(InputStream inputStream = getClass().getResourceAsStream("/tickets.json")) {
            ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
            flights = objectMapper.readValue(inputStream, FlightContainer.class);
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