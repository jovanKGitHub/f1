package org.example.dto;

import lombok.Getter;

@Getter
public class DriverMarket {
    private String driverId;
    private String fullName;
    private Integer odds;

    public DriverMarket(String driverId, String fullName, Integer odds) {
        this.driverId = driverId;
        this.fullName = fullName;
        this.odds = odds;
    }

}