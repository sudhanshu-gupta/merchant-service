package io.sudhanshugupta.merchant.service.interfaces.controller.interceptor;

import io.sudhanshugupta.merchant.service.exception.BadRequestException;
import io.sudhanshugupta.merchant.service.exception.ExpiredOfferException;
import io.sudhanshugupta.merchant.service.interfaces.controller.interceptor.dto.ErrorResponse;
import io.sudhanshugupta.merchant.service.interfaces.controller.interceptor.dto.factory.ErrorResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MerchantOfferExceptionInterceptor {

    @ExceptionHandler(ExpiredOfferException.class)
    public ResponseEntity<ErrorResponse> handleExpiredOfferException(ExpiredOfferException e) {
        return new ResponseEntity<>(ErrorResponseFactory.getResponse(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(ErrorResponseFactory.getResponse(e), HttpStatus.BAD_REQUEST);
    }
}
