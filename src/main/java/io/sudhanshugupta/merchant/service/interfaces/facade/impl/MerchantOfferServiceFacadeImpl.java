package io.sudhanshugupta.merchant.service.interfaces.facade.impl;

import io.sudhanshugupta.merchant.service.domain.jpa.entity.MerchantOffer;
import io.sudhanshugupta.merchant.service.domain.service.MerchantOfferService;
import io.sudhanshugupta.merchant.service.interfaces.facade.MerchantOfferServiceFacade;
import io.sudhanshugupta.merchant.service.interfaces.dto.OfferRequest;
import io.sudhanshugupta.merchant.service.interfaces.dto.OfferResponse;
import io.sudhanshugupta.merchant.service.interfaces.facade.mapper.MerchantOfferMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MerchantOfferServiceFacadeImpl implements MerchantOfferServiceFacade {

    private final MerchantOfferService merchantOfferService;

    public MerchantOfferServiceFacadeImpl(
            MerchantOfferService merchantOfferService) {
        this.merchantOfferService = merchantOfferService;
    }

    @Override
    public OfferResponse createOffer(OfferRequest offerRequest) {
        MerchantOffer merchantOffer = merchantOfferService
                .createOffer(MerchantOfferMapper.toMerchantOffer(offerRequest));
        return MerchantOfferMapper.toOfferResponse(merchantOffer);
    }

    @Override
    public OfferResponse cancelOffer(Long offerId) {
        MerchantOffer merchantOffer = merchantOfferService.cancelOffer(offerId);
        return MerchantOfferMapper.toOfferResponse(merchantOffer);
    }

    @Override
    public Page<OfferResponse> getOffers(int page, int size) {
        Page<MerchantOffer> merchantOffers = merchantOfferService.getOffers(page, size);
        List<OfferResponse> offerResponses = merchantOffers.getContent()
                .stream()
                .map(MerchantOfferMapper::toOfferResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(offerResponses, new PageRequest(merchantOffers.getNumber(),
                merchantOffers.getSize(), merchantOffers.getSort()), merchantOffers.getTotalElements());
    }
}
