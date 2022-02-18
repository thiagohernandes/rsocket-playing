package com.hernandes.rsocketplaying;

import com.hernandes.rsocketplaying.dto.request.GratuityRequest;
import com.hernandes.rsocketplaying.dto.response.GratuityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;

@RestController
@RequestMapping("/gratuity")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class GratuityController {

    private final RSocketRequester rSocketRequester;

    @GetMapping("/{id}")
    public Flux<GratuityResponse> gratuities(@PathVariable("id") int id) {

        Flux<GratuityRequest> requestFlux = Flux.fromArray(new GratuityRequest[]{
            GratuityRequest.builder().billTotal(BigDecimal.valueOf(35.5)).percent(18).build(),
            GratuityRequest.builder().billTotal(BigDecimal.valueOf(10.00)).percent(15).build(),
            GratuityRequest.builder().billTotal(BigDecimal.valueOf(23.25)).percent(20).build(),
            GratuityRequest.builder().billTotal(BigDecimal.valueOf(52.72)).percent(12).build()
        }).delayElements(Duration.ofSeconds(3));

        Flux<GratuityRequest> requestFlux2 = Flux.fromArray(new GratuityRequest[]{
            GratuityRequest.builder().billTotal(BigDecimal.valueOf(200)).percent(18).build(),
            GratuityRequest.builder().billTotal(BigDecimal.valueOf(150)).percent(12).build()
        }).delayElements(Duration.ofSeconds(1));

        return rSocketRequester
            .route(id == 3 ? "canceled" : id == 1 ? "gratuity" : "teste")
            .data(id == 3 ? Flux.just(GratuityRequest.builder()
                .billTotal(BigDecimal.valueOf(0)).percent(0).build()) : id == 1 ? requestFlux : requestFlux2)
            .retrieveFlux(GratuityResponse.class);
    }
}
