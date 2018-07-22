package io.sudhanshugupta.merchant.service.interfaces.facade.mapper;

import io.sudhanshugupta.merchant.service.domain.enumeration.OfferState;
import io.sudhanshugupta.merchant.service.domain.jpa.entity.*;
import io.sudhanshugupta.merchant.service.interfaces.dto.OfferRequest;
import io.sudhanshugupta.merchant.service.interfaces.dto.OfferResponse;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public final class MerchantOfferMapper {

    private MerchantOfferMapper() {
    }

    public static MerchantOffer toMerchantOffer(OfferRequest offerRequest) {
        Long startTime = (offerRequest.getStartTime() != null? offerRequest.getStartTime().getTime():
                Instant.now().toEpochMilli());
        Long endTime = Instant.ofEpochMilli(startTime)
                .plus(offerRequest.getDurationInMin(), ChronoUnit.MINUTES).toEpochMilli();
        OfferState state = Instant.now().isAfter(Instant.ofEpochMilli(startTime))?
                OfferState.ACTIVE: OfferState.UPCOMING;
        return MerchantOffer.builder()
                .description(offerRequest.getDescription())
                .currency(offerRequest.getCurrency())
                .offerPrice(offerRequest.getOfferPrice())
                .startTime(new Timestamp(startTime))
                .endTime(new Timestamp(endTime))
                .merchantId(offerRequest.getMerchantId())
                .state(state)
                .build();
    }

    public static OfferResponse toOfferResponse(MerchantOffer merchantOffer) {
        return OfferResponse.builder()
                .offerId(merchantOffer.getId())
                .description(merchantOffer.getDescription())
                .currency(merchantOffer.getCurrency())
                .startTime(merchantOffer.getStartTime())
                .endTime(merchantOffer.getEndTime())
                .offerPrice(merchantOffer.getOfferPrice())
                .state(merchantOffer.getState())
                .merchantId(merchantOffer.getMerchantId())
                .creationTime(merchantOffer.getCreationTime())
                .build();
    }
}
