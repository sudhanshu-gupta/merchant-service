package io.sudhanshugupta.merchant.service.domain.service.impl;

import com.google.common.collect.ImmutableList;
import io.sudhanshugupta.merchant.service.domain.enumeration.OfferState;
import io.sudhanshugupta.merchant.service.domain.jpa.entity.MerchantOffer;
import io.sudhanshugupta.merchant.service.domain.jpa.repository.MerchantOfferRepository;
import io.sudhanshugupta.merchant.service.domain.service.MerchantOfferService;
import io.sudhanshugupta.merchant.service.exception.BadRequestException;
import io.sudhanshugupta.merchant.service.exception.ExpiredOfferException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class MerchantOfferServiceImpl implements MerchantOfferService {

    private final MerchantOfferRepository merchantOfferRepository;
    private static final ImmutableList<OfferState> EXPIRE_ELIGIBLE_OFFER_STATES = ImmutableList.of(OfferState.ACTIVE);
    private static final ImmutableList<OfferState> ACTIVE_ELIGIBLE_OFFER_STATES = ImmutableList.of(OfferState.UPCOMING);

    public MerchantOfferServiceImpl(
            MerchantOfferRepository merchantOfferRepository) {
        this.merchantOfferRepository = merchantOfferRepository;
    }

    @Override
    public MerchantOffer createOffer(MerchantOffer merchantOffer) {
        return merchantOfferRepository.save(merchantOffer);
    }

    @Override
    public MerchantOffer cancelOffer(Long offerId) {
        if(!merchantOfferRepository.exists(offerId)) {
            throw BadRequestException.builder()
                    .field("offer_id", offerId)
                    .message("Offer does not exist")
                    .build();
        }
        int updated = merchantOfferRepository.updateOfferState(offerId, OfferState.CANCELLED);
        if(updated == 0) {
            throw ExpiredOfferException.builder()
                    .field("offer_id", offerId)
                    .message("Offer is expired")
                    .build();
        }
        return merchantOfferRepository.findOne(offerId);
    }

    @Override
    public Page<MerchantOffer> getOffers(int page, int size) {
        syncOffers();
        Pageable pageable = new PageRequest(page, size, Direction.DESC, "startTime", "endTime");
        return merchantOfferRepository.findAll(pageable);
    }

    private void syncOffers() {
        merchantOfferRepository.updateAllExpiredOffer(OfferState.EXPIRED, EXPIRE_ELIGIBLE_OFFER_STATES);
        merchantOfferRepository.updateAllUpcomingOffer(OfferState.ACTIVE, ACTIVE_ELIGIBLE_OFFER_STATES);
    }
}
