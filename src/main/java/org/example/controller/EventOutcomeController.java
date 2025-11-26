package org.example.controller;

import org.example.dto.EventOutcomeRequest;
import org.example.service.EventOutcomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/outcomes")
public class EventOutcomeController {
    private final EventOutcomeService outcomeService;

    public EventOutcomeController(EventOutcomeService outcomeService) {
        this.outcomeService = outcomeService;
    }

    @PostMapping
    public ResponseEntity<Void> processOutcome(@RequestBody EventOutcomeRequest request) {
        outcomeService.processEventOutcome(request.getEventId(), request.getWinningDriverId());
        return ResponseEntity.ok().build();
    }
}