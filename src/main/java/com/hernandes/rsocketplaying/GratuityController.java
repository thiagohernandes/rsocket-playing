package com.hernandes.rsocketplaying;

import com.hernandes.rsocketplaying.dto.request.GratuityRequest;
import com.hernandes.rsocketplaying.dto.response.GratuityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;

@RestController
@RequestMapping("/gratuity")
@RequiredArgsConstructor
@Slf4j
public class GratuityController {

    private final RSocketRequester rSocketRequester;

    @GetMapping
    public Flux<GratuityResponse> gratuities() {

        Flux<GratuityRequest> requestFlux = Flux.fromArray(new GratuityRequest[]{
            GratuityRequest.builder().billTotal(BigDecimal.valueOf(35.5)).percent(18).build(),
            GratuityRequest.builder().billTotal(BigDecimal.valueOf(10.00)).percent(15).build(),
            GratuityRequest.builder().billTotal(BigDecimal.valueOf(23.25)).percent(20).build(),
            GratuityRequest.builder().billTotal(BigDecimal.valueOf(52.72)).percent(12).build()
        }).delayElements(Duration.ofSeconds(3));

        return rSocketRequester
            .route("gratuity")
            .data(requestFlux)
            .retrieveFlux(GratuityResponse.class);
    }
}
