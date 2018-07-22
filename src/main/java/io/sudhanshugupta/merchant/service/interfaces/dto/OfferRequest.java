package io.sudhanshugupta.merchant.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.sudhanshugupta.merchant.service.domain.enumeration.Currency;
import java.sql.Timestamp;
import lombok.Data;

@Data
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OfferRequest {

    private String description;
    private Float offerPrice;
    private Currency currency;
    private Timestamp startTime;
    private Integer durationInMin;
    private Long merchantId;
}
