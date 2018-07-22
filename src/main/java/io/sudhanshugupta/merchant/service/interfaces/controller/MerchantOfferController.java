package io.sudhanshugupta.merchant.service.interfaces.controller;

import io.sudhanshugupta.merchant.service.interfaces.controller.validator.MerchantOfferRequestValidator;
import io.sudhanshugupta.merchant.service.interfaces.dto.OfferRequest;
import io.sudhanshugupta.merchant.service.interfaces.dto.OfferResponse;
import io.sudhanshugupta.merchant.service.interfaces.facade.MerchantOfferServiceFacade;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/merchant/offers")
public class MerchantOfferController {

    private final MerchantOfferServiceFacade merchantOfferServiceFacade;
    private final MerchantOfferRequestValidator merchantOfferRequestValidator;

    public MerchantOfferController(
            MerchantOfferServiceFacade merchantOfferServiceFacade,
            MerchantOfferRequestValidator merchantOfferRequestValidator) {
        this.merchantOfferServiceFacade = merchantOfferServiceFacade;
        this.merchantOfferRequestValidator = merchantOfferRequestValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(merchantOfferRequestValidator);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferResponse> createOffer(@Valid @RequestBody OfferRequest request) {

        return new ResponseEntity<>(merchantOfferServiceFacade.createOffer(request), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{offerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferResponse> cancelOffer(@PathVariable("offerId") Long offerId) {
        Assert.notNull(offerId, "Offer Id cannot be null");
        return new ResponseEntity<>(merchantOfferServiceFacade.cancelOffer(offerId), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<OfferResponse>> getOffers(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        return new ResponseEntity<>(merchantOfferServiceFacade.getOffers(page, size), HttpStatus.OK);
    }
}
