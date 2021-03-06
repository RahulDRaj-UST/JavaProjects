package com.wba.api.processcustomer.repository;

import com.wba.api.processcustomer.model.PatientModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository  extends MongoRepository<PatientModel, String> {

    public PatientModel findByPatientId(String patientId);
}
