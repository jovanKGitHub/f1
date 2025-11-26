package org.example.service;

import org.example.dto.PlaceBetRequest;
import org.example.model.Bet;
import org.example.model.User;
import org.example.repository.BetRepository;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BettingServiceTest {

    @Test
    void placeBet_deductsBalanceAndStoresBet() {
        UserRepository userRepo = new UserRepository();
        BetRepository betRepo = new BetRepository();
        BettingService service = new BettingService(userRepo, betRepo);

        UUID userId = UUID.randomUUID();
        User user = userRepo.getOrCreate(userId);
        BigDecimal starting = user.getBalance();

        PlaceBetRequest req = new PlaceBetRequest();
        req.setUserId(userId.toString());
        req.setEventId("EVT1");
        req.setDriverId("44");
        req.setAmount(new BigDecimal("25"));

        Bet bet = service.placeBet(req);

        User updated = userRepo.findById(userId).orElseThrow();
        assertEquals(starting.subtract(new BigDecimal("25")), updated.getBalance());
        assertEquals("EVT1", bet.getEventId());
        assertTrue(Set.of(2, 3, 4).contains(bet.getOdds()));
    }

    @Test
    void placeBet_insufficientBalanceThrows() {
        UserRepository userRepo = new UserRepository();
        BetRepository betRepo = new BetRepository();
        BettingService service = new BettingService(userRepo, betRepo);

        UUID userId = UUID.randomUUID();
        PlaceBetRequest req = new PlaceBetRequest();
        req.setUserId(userId.toString());
        req.setEventId("EVT2");
        req.setDriverId("33");
        req.setAmount(new BigDecimal("1000"));

        assertThrows(IllegalArgumentException.class, () -> service.placeBet(req));
    }
}