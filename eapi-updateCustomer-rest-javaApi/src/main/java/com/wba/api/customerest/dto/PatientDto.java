package com.wba.api.customerest.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PatientDto implements Serializable {

    private String patientId;

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

        if(profilePaymentDetails==null)
            profilePaymentDetails=new ArrayList<>();

        return profilePaymentDetails;
    }

    

	@Override
	public String toString() {
		return "PatientDto [patientId=" + patientId + ", email="
				+ email + ", dob=" + dob + ", phoneNumberAreaCode=" + phoneNumberAreaCode + ", phoneNumber="
				+ phoneNumber + ", preferredStoreNumber=" + preferredStoreNumber + ", lastFilledStoreNumber="
				+ lastFilledStoreNumber + ", preferredPaymentMethod=" + preferredPaymentMethod
				+ ", previousFilledLastMile=" + previousFilledLastMile + ", customerShippingAddress="
				+ customerShippingAddress + ", profilePaymentDetails=" + profilePaymentDetails + "]";
	}

}
