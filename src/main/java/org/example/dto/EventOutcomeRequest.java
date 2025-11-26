package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EventOutcomeRequest {
    @JsonProperty("eventId")
    private String eventId;
    @JsonProperty("winningDriverId")
    private String winningDriverId;

}