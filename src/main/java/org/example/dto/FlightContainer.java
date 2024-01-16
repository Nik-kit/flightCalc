package org.example.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlightContainer{

	@JsonProperty("tickets")
	private List<TicketsItem> tickets;
}