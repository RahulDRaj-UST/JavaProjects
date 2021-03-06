package com.wba.api.processcustomer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class PaymentDto implements Serializable {

    private String cardType;
    private String creditCard;
    private Integer lastFourDigits;
    private Long expiryMonth;
    private Long expiryYear;
    private String zipCode;
    @JsonProperty("isDefault")
    private Boolean isDefault;

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public Integer getLastFourDigits() {
        return lastFourDigits;
    }

    public void setLastFourDigits(Integer lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
    }

    public Long getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(Long expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public Long getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(Long expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }
}
