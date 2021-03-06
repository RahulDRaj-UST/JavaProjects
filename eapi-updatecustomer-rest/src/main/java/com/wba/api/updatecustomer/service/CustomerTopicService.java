package com.wba.api.updatecustomer.service;

import com.wba.api.updatecustomer.dto.PatientDto;

/**
 * @author 165571
 * @href <mohammedanas.abdulrasheed@ust.com>
 * @createdon 2/24/2021
 */
public interface CustomerTopicService {

    String pushCustomerDataToTopic(PatientDto patientObj);
}
