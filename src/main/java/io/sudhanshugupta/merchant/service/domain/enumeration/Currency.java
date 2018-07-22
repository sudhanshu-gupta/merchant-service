package io.sudhanshugupta.merchant.service.domain.enumeration;

public enum Currency {

    GBP("gbp"), USD("usd"), INR("inr");

    private String code;

    Currency(String code) {
        this.code = code;
    }

}
