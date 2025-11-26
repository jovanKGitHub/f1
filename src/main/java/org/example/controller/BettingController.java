package org.example.controller;

import org.example.dto.PlaceBetRequest;
import org.example.model.Bet;
import org.example.service.BettingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bets")
public class BettingController {
    private final BettingService bettingService;

    public BettingController(BettingService bettingService) {
        this.bettingService = bettingService;
    }

    @PostMapping
    public ResponseEntity<Bet> placeBet(@RequestBody PlaceBetRequest request) {
        Bet bet = bettingService.placeBet(request);
        return ResponseEntity.ok(bet);
    }
}