package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class EventResponse {
    private Integer sessionKey;
    private String sessionName;
    private String sessionType;
    private String dateStart;
    private String dateEnd;
    private String location;
    private String countryName;
    private Integer year;
    private List<DriverMarket> driverMarket;

    // Constructors, getters, setters
    public EventResponse() {}

}