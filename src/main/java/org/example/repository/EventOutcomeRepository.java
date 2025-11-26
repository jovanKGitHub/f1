package org.example.repository;

import org.example.model.EventOutcome;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EventOutcomeRepository {
    private final Map<String, EventOutcome> outcomes = new ConcurrentHashMap<>();

    public EventOutcome save(EventOutcome outcome) {
        outcomes.put(outcome.getEventId(), outcome);
        return outcome;
    }

    public Optional<EventOutcome> findByEventId(String eventId) {
        return Optional.ofNullable(outcomes.get(eventId));
    }
}