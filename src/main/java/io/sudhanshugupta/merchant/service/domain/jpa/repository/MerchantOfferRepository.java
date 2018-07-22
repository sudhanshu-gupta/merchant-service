package io.sudhanshugupta.merchant.service.domain.jpa.repository;

import io.sudhanshugupta.merchant.service.domain.enumeration.OfferState;
import io.sudhanshugupta.merchant.service.domain.jpa.entity.MerchantOffer;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantOfferRepository extends JpaRepository<MerchantOffer, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE MerchantOffer mo SET mo.state = :newState WHERE mo.id = :id AND mo.endTime > CURRENT_TIMESTAMP")
    int updateOfferState(@Param("id") Long offerId, @Param("newState") OfferState newState);

    @Transactional
    @Modifying
    @Query("UPDATE MerchantOffer mo SET mo.state = :state WHERE mo.state IN (:oldState) AND mo.endTime < CURRENT_TIMESTAMP")
    void updateAllExpiredOffer(@Param("state") OfferState newState, @Param("oldState") Iterable<OfferState> offerStates);

    @Transactional
    @Modifying
    @Query("UPDATE MerchantOffer mo SET mo.state = :state WHERE mo.state IN (:oldState) AND mo.startTime < CURRENT_TIMESTAMP AND mo.endTime > CURRENT_TIMESTAMP")
    void updateAllUpcomingOffer(@Param("state") OfferState newState, @Param("oldState") Iterable<OfferState> offerStates);
}
