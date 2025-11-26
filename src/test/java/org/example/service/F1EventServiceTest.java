package org.example.service;

import org.example.dto.EventResponse;
import org.example.integration.F1Driver;
import org.example.integration.F1Event;
import org.example.integration.F1EventProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class F1EventServiceTest {

    @Test
    void getEvents_mapsEventAndGeneratesDriverMarket() {
        F1EventProvider provider = Mockito.mock(F1EventProvider.class);
        F1Event event = new F1Event();
        event.setSessionKey(123);
        event.setSessionName("Race");
        event.setSessionType("RACE");
        event.setCountryName("Italy");
        event.setYear(2023);

        F1Driver driver1 = new F1Driver();
        driver1.setDriverNumber("44");
        driver1.setFullName("Lewis Hamilton");
        F1Driver driver2 = new F1Driver();
        driver2.setDriverNumber("33");
        driver2.setFullName("Max Verstappen");

        when(provider.getEvents(2023, "RACE", "Italy", "RACE")).thenReturn(List.of(event));
        when(provider.getDriversForSession(123)).thenReturn(List.of(driver1, driver2));

        F1EventService service = new F1EventService(provider);
        List<EventResponse> responses = service.getEvents(2023, "RACE", "Italy", "RACE");

        assertEquals(1, responses.size());
        EventResponse r = responses.get(0);
        assertEquals(123, r.getSessionKey());
        assertEquals(2, r.getDriverMarket().size());
        Set<Integer> allowed = Set.of(2, 3, 4);
        r.getDriverMarket().forEach(dm -> assertTrue(allowed.contains(dm.getOdds())));
    }
}