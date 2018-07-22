package io.sudhanshugupta.merchant.service.domain.service;

import io.sudhanshugupta.merchant.service.domain.jpa.entity.MerchantOffer;
import org.springframework.data.domain.Page;

public interface MerchantOfferService {

    MerchantOffer createOffer(MerchantOffer merchantOffer);

    MerchantOffer cancelOffer(Long offerId);

    Page<MerchantOffer> getOffers(int page, int size);
}
