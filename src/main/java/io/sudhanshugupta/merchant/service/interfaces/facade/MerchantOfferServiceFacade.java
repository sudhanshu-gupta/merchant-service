package io.sudhanshugupta.merchant.service.interfaces.facade;

import io.sudhanshugupta.merchant.service.interfaces.dto.OfferRequest;
import io.sudhanshugupta.merchant.service.interfaces.dto.OfferResponse;
import org.springframework.data.domain.Page;

public interface MerchantOfferServiceFacade {

    OfferResponse createOffer(OfferRequest offerRequest);

    OfferResponse cancelOffer(Long offerId);

    Page<OfferResponse> getOffers(int page, int size);
}
