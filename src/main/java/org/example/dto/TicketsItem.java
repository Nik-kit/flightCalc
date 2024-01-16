package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class TicketsItem{

	@JsonProperty("carrier")
	private String carrier;

	@JsonProperty("arrival_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="H:mm")
	private LocalTime arrivalTime;

	@JsonProperty("price")
	private Integer price;

	@JsonProperty("origin")
	private String origin;

	@JsonProperty("departure_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd.MM.yy")
	private LocalDate departureDate;

	@JsonProperty("destination")
	private String destination;

	@JsonProperty("destination_name")
	private String destinationName;

	@JsonProperty("stops")
	private Integer stops;

	@JsonProperty("departure_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="H:mm")
	private LocalTime departureTime;

	@JsonProperty("arrival_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd.MM.yy")
	private LocalDate arrivalDate;

	@JsonProperty("origin_name")
	private String originName;
}