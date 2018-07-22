package io.sudhanshugupta.merchant.service.domain.enumeration;

public enum  OfferState {

    UPCOMING(0), ACTIVE(1), EXPIRED(2), CANCELLED(3);

    private int code;

    OfferState(int code) {
        this.code = code;
    }
}
