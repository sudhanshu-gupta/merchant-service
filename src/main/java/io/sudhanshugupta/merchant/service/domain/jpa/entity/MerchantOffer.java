package io.sudhanshugupta.merchant.service.domain.jpa.entity;

import io.sudhanshugupta.merchant.service.domain.enumeration.Currency;
import io.sudhanshugupta.merchant.service.domain.enumeration.OfferState;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantOffer implements Serializable {

    private static final long serialVersionUID = 1251240494841174192L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "offer_price")
    private Float offerPrice;

    @Column(name = "currency", columnDefinition = "tinyint")
    private Currency currency;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "state", columnDefinition = "tinyint")
    private OfferState state;

    @Column(name = "merchantId")
    private Long merchantId;

    @Column(name = "creation_time")
    private Timestamp creationTime;

    @PrePersist
    public void prePersist() {
        if(creationTime == null) {
            creationTime = new Timestamp(Instant.now().toEpochMilli());
        }
    }
}
