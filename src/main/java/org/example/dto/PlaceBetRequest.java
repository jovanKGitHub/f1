package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class PlaceBetRequest {
    @JsonProperty("userId")
    private String userId;

    @JsonProperty("eventId")
    private String eventId;

    @JsonProperty("driverId")
    private String driverId;

    @JsonProperty("amount")
    private BigDecimal amount;

}