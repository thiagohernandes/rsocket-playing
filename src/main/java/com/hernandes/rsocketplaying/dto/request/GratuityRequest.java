package com.hernandes.rsocketplaying.dto.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GratuityRequest {

    private BigDecimal billTotal;
    private int percent;

}
