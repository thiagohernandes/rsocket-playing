//package com.hernandes.rsocketplaying.client;
//
//import com.hernandes.rsocketplaying.dto.request.GratuityRequest;
//import com.hernandes.rsocketplaying.dto.response.GratuityResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.rsocket.RSocketRequester;
//import reactor.core.publisher.Flux;
//
//import java.math.BigDecimal;
//import java.time.Duration;
//
//@Configuration
//@Slf4j
//public class GratuityClient {
//
//    @Bean
//    public ApplicationRunner sender(RSocketRequester.Builder requesterBuilder)
//    {
//        return args -> {
//            RSocketRequester tcp = requesterBuilder.tcp("localhost", 7000);
//
//            Flux<GratuityRequest> requestFlux = Flux.fromArray(new GratuityRequest[]{
//                GratuityRequest.builder().billTotal(BigDecimal.valueOf(35.5)).percent(18).build(),
//                GratuityRequest.builder().billTotal(BigDecimal.valueOf(10.00)).percent(15).build(),
//                GratuityRequest.builder().billTotal(BigDecimal.valueOf(23.25)).percent(20).build(),
//                GratuityRequest.builder().billTotal(BigDecimal.valueOf(52.72)).percent(12).build()
//            }).delayElements(Duration.ofSeconds(3));
//
//            tcp
//                .route("gratuity")
//                .data(requestFlux)
//                .retrieveFlux(GratuityResponse.class)
//                .subscribe(out ->
//                    log.info(out.getPercent() + "% gratuity on "
//                        + out.getBillTotal() + " is "
//                        + out.getGratuity()));
//        };
//    }
//
//}
