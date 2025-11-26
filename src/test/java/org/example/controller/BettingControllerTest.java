package org.example.controller;

import org.example.dto.PlaceBetRequest;
import org.example.model.Bet;
import org.example.service.BettingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BettingControllerTest {

    @Test
    void placeBet_endpointReturnsOk() throws Exception {
        BettingService service = Mockito.mock(BettingService.class);
        Bet mockBet = new Bet(UUID.randomUUID(), "EVT9", "99", new BigDecimal("20"), 2);
        Mockito.when(service.placeBet(any(PlaceBetRequest.class))).thenReturn(mockBet);

        BettingController controller = new BettingController(service);
        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

        String json = """
            {"userId":"%s","eventId":"EVT9","driverId":"99","amount":20}
            """.formatted(UUID.randomUUID());

        mvc.perform(post("/api/bets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}