package org.example.repository;

import org.example.model.Bet;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class BetRepository {
    private final Map<UUID, Bet> bets = new ConcurrentHashMap<>();

    public Bet save(Bet bet) {
        bets.put(bet.getId(), bet);
        return bet;
    }

    public List<Bet> findByEventId(String eventId) {
        return bets.values().stream()
                .filter(bet -> bet.getEventId().equals(eventId))
                .collect(Collectors.toList());
    }

    public Optional<Bet> findById(UUID id) {
        return Optional.ofNullable(bets.get(id));
    }
}