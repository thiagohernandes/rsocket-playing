package com.hernandes.rsocketplaying.dto.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class GratuityResponse {

    private BigDecimal billTotal;
    private int percent;
    private BigDecimal gratuity;
    private Boolean canceled;

}
