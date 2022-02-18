package com.hernandes.rsocketplaying.listener;

import java.math.BigDecimal;

import com.hernandes.rsocketplaying.dto.request.GratuityRequest;
import com.hernandes.rsocketplaying.dto.response.GratuityResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class GratuityListener {

    @MessageMapping("gratuity")
    public Flux<GratuityResponse> calculate(Flux<GratuityRequest> gratuityInFlux) {
        return gratuityInFlux
            .doOnNext(in -> log.info("Calculating gratuity: {}", in))
            .map(in -> {
                double percentAsDecimal = in.getPercent() / 100.0;
                BigDecimal gratuity = in.getBillTotal()
                    .multiply(BigDecimal.valueOf(percentAsDecimal));
                return new GratuityResponse(
                    in.getBillTotal(), in.getPercent(), gratuity, Boolean.FALSE);
            });
    }

    @MessageMapping("teste")
    public Flux<GratuityResponse> calculateTeste(Flux<GratuityRequest> gratuityInFlux) {
        return gratuityInFlux
            .doOnNext(in -> log.info("Teste: {}", in))
            .map(in -> {
                double percentAsDecimal = in.getPercent() / 100.0;
                BigDecimal gratuity = in.getBillTotal()
                    .multiply(BigDecimal.valueOf(percentAsDecimal));
                return new GratuityResponse(
                    in.getBillTotal(), in.getPercent(), gratuity, Boolean.FALSE);
            });
    }

    @MessageMapping("canceled")
    public Flux<GratuityResponse> canceled(Flux<GratuityRequest> gratuityInFlux) {
        return gratuityInFlux
            .doOnNext(in -> log.info("Canceld: {}", in))
            .map(in ->
                new GratuityResponse(
                    in.getBillTotal(), in.getPercent(), null, Boolean.TRUE)
            );
    }

}
