package org.example.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Bet {
    private UUID id;
    private UUID userId;
    private String eventId;
    private String driverId;
    private BigDecimal amount;
    private Integer odds;
    private BetStatus status;
    private LocalDateTime placedAt;

    public Bet(UUID userId, String eventId, String driverId, BigDecimal amount, Integer odds) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.eventId = eventId;
        this.driverId = driverId;
        this.amount = amount;
        this.odds = odds;
        this.status = BetStatus.PENDING;
        this.placedAt = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public String getEventId() { return eventId; }
    public String getDriverId() { return driverId; }
    public BigDecimal getAmount() { return amount; }
    public Integer getOdds() { return odds; }
    public BetStatus getStatus() { return status; }
    public LocalDateTime getPlacedAt() { return placedAt; }

    public void setStatus(BetStatus status) { this.status = status; }

    public BigDecimal calculatePrize() {
        return amount.multiply(new BigDecimal(odds));
    }
}