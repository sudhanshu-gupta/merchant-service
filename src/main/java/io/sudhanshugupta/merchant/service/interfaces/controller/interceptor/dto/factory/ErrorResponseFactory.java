package io.sudhanshugupta.merchant.service.interfaces.controller.interceptor.dto.factory;

import io.sudhanshugupta.merchant.service.exception.BadRequestException;
import io.sudhanshugupta.merchant.service.exception.ExpiredOfferException;
import io.sudhanshugupta.merchant.service.interfaces.controller.interceptor.dto.ErrorResponse;

public final class ErrorResponseFactory {

    private ErrorResponseFactory() {
    }

    public static ErrorResponse getResponse(Exception e) {
        if(e instanceof BadRequestException) {
            return buildBadRequestResponse((BadRequestException) e);
        } else if(e instanceof ExpiredOfferException) {
            return buildExpiredOfferResponse((ExpiredOfferException) e);
        } else {
            return buildExceptionResponse(e);
        }
    }

    private static ErrorResponse buildBadRequestResponse(BadRequestException e) {
        return ErrorResponse.builder()
                .fields(e.getFields())
                .message(e.getMessage())
                .build();
    }

    private static ErrorResponse buildExpiredOfferResponse(ExpiredOfferException e) {
        return ErrorResponse.builder()
                .fields(e.getFields())
                .message(e.getMessage())
                .build();
    }

    private static ErrorResponse buildExceptionResponse(Exception e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .exception(e)
                .build();
    }
}
