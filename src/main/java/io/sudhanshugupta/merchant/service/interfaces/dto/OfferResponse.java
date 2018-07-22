package io.sudhanshugupta.merchant.service.interfaces.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.sudhanshugupta.merchant.service.domain.enumeration.Currency;
import io.sudhanshugupta.merchant.service.domain.enumeration.OfferState;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OfferResponse {

    private Long offerId;
    private String description;
    private Float offerPrice;
    private Currency currency;
    private Timestamp startTime;
    private Timestamp endTime;
    private OfferState state;
    private Long merchantId;
    private Timestamp creationTime;
}
