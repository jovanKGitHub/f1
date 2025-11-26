package org.example.service;

import org.example.model.Bet;
import org.example.model.BetStatus;
import org.example.model.EventOutcome;
import org.example.model.User;
import org.example.repository.BetRepository;
import org.example.repository.EventOutcomeRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventOutcomeService {
    private final EventOutcomeRepository outcomeRepository;
    private final BetRepository betRepository;
    private final UserRepository userRepository;

    public EventOutcomeService(EventOutcomeRepository outcomeRepository,
                               BetRepository betRepository,
                               UserRepository userRepository) {
        this.outcomeRepository = outcomeRepository;
        this.betRepository = betRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void processEventOutcome(String eventId, String winningDriverId) {
        // Save outcome
        EventOutcome outcome = new EventOutcome(eventId, winningDriverId);
        outcomeRepository.save(outcome);

        // Get all bets for this event
        List<Bet> bets = betRepository.findByEventId(eventId);

        // Process each bet
        for (Bet bet : bets) {
            if (bet.getDriverId().equals(winningDriverId)) {
                // Bet won
                bet.setStatus(BetStatus.WON);

                // Calculate and add prize to user balance
                User user = userRepository.findById(bet.getUserId())
                        .orElseThrow(() -> new IllegalArgumentException("User not found"));
                user.addBalance(bet.calculatePrize());
                userRepository.save(user);
            } else {
                // Bet lost
                bet.setStatus(BetStatus.LOST);
            }
            betRepository.save(bet);
        }
    }
}