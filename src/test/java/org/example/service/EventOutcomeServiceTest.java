package org.example.service;

import org.example.model.Bet;
import org.example.model.BetStatus;
import org.example.model.User;
import org.example.repository.BetRepository;
import org.example.repository.EventOutcomeRepository;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EventOutcomeServiceTest {

    @Test
    void processEventOutcome_updatesWinningAndLosingBetsAndBalances() {
        UserRepository userRepo = new UserRepository();
        BetRepository betRepo = new BetRepository();
        EventOutcomeRepository outcomeRepo = new EventOutcomeRepository();
        EventOutcomeService service = new EventOutcomeService(outcomeRepo, betRepo, userRepo);

        UUID userWin = UUID.randomUUID();
        UUID userLose = UUID.randomUUID();
        User uWin = userRepo.getOrCreate(userWin);
        User uLose = userRepo.getOrCreate(userLose);

        Bet winBet = new Bet(userWin, "EVT3", "DRIVER_WIN", new BigDecimal("10"), 3);
        Bet loseBet = new Bet(userLose, "EVT3", "DRIVER_LOSE", new BigDecimal("15"), 4);
        // Simulate placement (balance deduction)
        uWin.deductBalance(new BigDecimal("10"));
        uLose.deductBalance(new BigDecimal("15"));
        userRepo.save(uWin);
        userRepo.save(uLose);
        betRepo.save(winBet);
        betRepo.save(loseBet);

        BigDecimal winBalanceBefore = uWin.getBalance();

        service.processEventOutcome("EVT3", "DRIVER_WIN");

        Bet updatedWinBet = betRepo.findById(winBet.getId()).orElseThrow();
        Bet updatedLoseBet = betRepo.findById(loseBet.getId()).orElseThrow();
        User updatedWinUser = userRepo.findById(userWin).orElseThrow();
        User updatedLoseUser = userRepo.findById(userLose).orElseThrow();

        assertEquals(BetStatus.WON, updatedWinBet.getStatus());
        assertEquals(BetStatus.LOST, updatedLoseBet.getStatus());
        assertEquals(winBalanceBefore.add(winBet.calculatePrize()), updatedWinUser.getBalance());
        assertEquals(uLose.getBalance(), updatedLoseUser.getBalance());
    }
}