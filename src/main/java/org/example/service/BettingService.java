package org.example.service;

import org.example.dto.PlaceBetRequest;
import org.example.model.Bet;
import org.example.model.User;
import org.example.repository.BetRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class BettingService {
    private final UserRepository userRepository;
    private final BetRepository betRepository;
    private final Random random;

    public BettingService(UserRepository userRepository, BetRepository betRepository) {
        this.userRepository = userRepository;
        this.betRepository = betRepository;
        this.random = new Random();
    }

    public Bet placeBet(PlaceBetRequest request) {
        UUID userId = UUID.fromString(request.getUserId());
        User user = userRepository.getOrCreate(userId);

        // Deduct balance
        user.deductBalance(request.getAmount());
        userRepository.save(user);

        // Create and save bet with random odds
        Integer odds = generateRandomOdds();
        Bet bet = new Bet(
                userId,
                request.getEventId(),
                request.getDriverId(),
                request.getAmount(),
                odds
        );

        return betRepository.save(bet);
    }

    private Integer generateRandomOdds() {
        int[] odds = {2, 3, 4};
        return odds[random.nextInt(odds.length)];
    }
}