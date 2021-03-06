package com.wba.api.customerest.service;

import com.wba.api.customerest.dto.PatientDto;

public interface CustomerService{

    String sendToKafka(PatientDto patientDto);

}
