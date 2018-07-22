package io.sudhanshugupta.merchant.service.interfaces.controller.validator;

import io.sudhanshugupta.merchant.service.exception.BadRequestException;
import io.sudhanshugupta.merchant.service.interfaces.dto.OfferRequest;
import java.time.Instant;
import java.util.function.Supplier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MerchantOfferRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return OfferRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        OfferRequest offerRequest = (OfferRequest) o;
        validateParameter(()-> offerRequest.getMerchantId() == null,
                "merchant_id", offerRequest.getMerchantId(), "MerchantId cannot be null");
        validateParameter(()-> offerRequest.getCurrency() == null,
                "currency", offerRequest.getCurrency(), "Currency cannot be null");
        validateParameter(()-> offerRequest.getDurationInMin() == null || offerRequest.getDurationInMin() <= 0,
                "duration_in_min", offerRequest.getDurationInMin(), "Duration cannot be null or less than zero");
        validateParameter(()-> offerRequest.getOfferPrice() == null || offerRequest.getOfferPrice() <= 0,
                "offer_price", offerRequest.getOfferPrice(), "Offer price cannot be null or 0");
        validateParameter(()-> offerRequest.getStartTime() != null && Instant.now().isAfter
                        (Instant.ofEpochMilli(offerRequest.getStartTime().getTime())),
                "start_time", offerRequest.getStartTime(),"offer Start time cannot be in past");
    }

    private <T> void validateParameter(Supplier<Boolean> supplier, String parameterName, T parameterValue, String msg) {
        if(supplier.get()) {
            throw BadRequestException.builder()
                    .field(parameterName, parameterValue)
                    .message(msg)
                    .build();
        }
    }
}
