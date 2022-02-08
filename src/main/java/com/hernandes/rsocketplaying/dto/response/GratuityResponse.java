package com.hernandes.rsocketplaying.dto.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GratuityResponse {

    private BigDecimal billTotal;
    private int percent;
    private BigDecimal gratuity;

}
