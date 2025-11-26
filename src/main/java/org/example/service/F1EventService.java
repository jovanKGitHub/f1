package org.example.service;

import org.example.dto.DriverMarket;
import org.example.dto.EventResponse;
import org.example.integration.F1Driver;
import org.example.integration.F1Event;
import org.example.integration.F1EventProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class F1EventService {
    private final F1EventProvider eventProvider;
    private final Random random;

    public F1EventService(F1EventProvider eventProvider) {
        this.eventProvider = eventProvider;
        this.random = new Random();
    }

    public List<EventResponse> getEvents(Integer year, String sessionType, String country, String sessionName) {
        List<F1Event> events = eventProvider.getEvents(year, sessionType, country, sessionName);

        return events.stream()
                .map(event -> convertToEventResponse(event))
                .collect(Collectors.toList());
    }

    public EventResponse convertToEventResponse(F1Event event) {
        EventResponse response = new EventResponse();
        response.setSessionKey(event.getSessionKey());
        response.setSessionName(event.getSessionName());
        response.setSessionType(event.getSessionType());
        response.setDateStart(event.getDateStart());
        response.setDateEnd(event.getDateEnd());
        response.setLocation(event.getLocation());
        response.setCountryName(event.getCountryName());
        response.setYear(event.getYear());

        // Get drivers and create driver market
        List<F1Driver> drivers = eventProvider.getDriversForSession(event.getSessionKey());
        List<DriverMarket> driverMarket = drivers.stream()
                .map(driver -> new DriverMarket(
                        driver.getDriverNumber(),
                        driver.getFullName(),
                        generateRandomOdds()
                ))
                .collect(Collectors.toList());

        response.setDriverMarket(driverMarket);
        return response;
    }

    public Integer generateRandomOdds() {
        int[] odds = {2, 3, 4};
        return odds[random.nextInt(odds.length)];
    }
}