package com.wba.api.processcustomer.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class PatientDto implements Serializable {

    private String patientId;
    private String firstName;
    private String middleInit;
    private String lastName;
    private String surnameSuffix;
    private String gender;
    private String email;
    private String dob;
    private String phoneNumberAreaCode;
    private String phoneNumber;
    private String preferredStoreNumber;
    private String lastFilledStoreNumber;
    private String preferredPaymentMethod;
    private String previousFilledLastMile;
    private ShippingAddressDto customerShippingAddress;
    private List<PaymentDto> profilePaymentDetails;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInit() {
        return middleInit;
    }

    public void setMiddleInit(String middleInit) {
        this.middleInit = middleInit;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurnameSuffix() {
        return surnameSuffix;
    }

    public void setSurnameSuffix(String surnameSuffix) {
        this.surnameSuffix = surnameSuffix;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNumberAreaCode() {
        return phoneNumberAreaCode;
    }

    public void setPhoneNumberAreaCode(String phoneNumberAreaCode) {
        this.phoneNumberAreaCode = phoneNumberAreaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPreferredStoreNumber() {
        return preferredStoreNumber;
    }

    public void setPreferredStoreNumber(String preferredStoreNumber) {
        this.preferredStoreNumber = preferredStoreNumber;
    }

    public String getLastFilledStoreNumber() {
        return lastFilledStoreNumber;
    }

    public void setLastFilledStoreNumber(String lastFilledStoreNumber) {
        this.lastFilledStoreNumber = lastFilledStoreNumber;
    }

    public String getPreferredPaymentMethod() {
        return preferredPaymentMethod;
    }

    public void setPreferredPaymentMethod(String preferredPaymentMethod) {
        this.preferredPaymentMethod = preferredPaymentMethod;
    }

    public String getPreviousFilledLastMile() {
        return previousFilledLastMile;
    }

    public void setPreviousFilledLastMile(String previousFilledLastMile) {
        this.previousFilledLastMile = previousFilledLastMile;
    }

    public ShippingAddressDto getCustomerShippingAddress() {
        return customerShippingAddress;
    }

    public void setCustomerShippingAddress(ShippingAddressDto customerShippingAddress) {
        this.customerShippingAddress = customerShippingAddress;
    }

    public List<PaymentDto> getProfilePaymentDetails() {

        if (profilePaymentDetails == null)
            profilePaymentDetails = new ArrayList<>();

        return profilePaymentDetails;
    }

}
