package org.example.model;

import java.time.LocalDateTime;

public class EventOutcome {
    private String eventId;
    private String winningDriverId;
    private LocalDateTime recordedAt;

    public EventOutcome(String eventId, String winningDriverId) {
        this.eventId = eventId;
        this.winningDriverId = winningDriverId;
        this.recordedAt = LocalDateTime.now();
    }

    public String getEventId() { return eventId; }
    public String getWinningDriverId() { return winningDriverId; }
    public LocalDateTime getRecordedAt() { return recordedAt; }
}