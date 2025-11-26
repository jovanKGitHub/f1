package org.example.integration;

import java.util.List;

public interface F1EventProvider {
    List<F1Event> getEvents(Integer year, String sessionType, String country, String sessionName);
    List<F1Driver> getDriversForSession(Integer sessionKey);
}