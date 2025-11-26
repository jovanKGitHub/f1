package org.example.controller;

import org.example.dto.EventResponse;
import org.example.service.F1EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class F1EventController {
    private final F1EventService eventService;

    public F1EventController(F1EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getEvents(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String sessionType,
            @RequestParam(required = false) String sessionName,
            @RequestParam(required = false) String country) {

        List<EventResponse> events = eventService.getEvents(year, sessionType, country, sessionName);
        return ResponseEntity.ok(events);
    }
}