package org.example.integration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class F1Event {
    // Getters and setters
    private Integer sessionKey;
    private String sessionName;
    private String sessionType;
    private String dateStart;
    private String dateEnd;
    private String location;
    private String countryName;
    private Integer year;
    private String meetingKey;

}