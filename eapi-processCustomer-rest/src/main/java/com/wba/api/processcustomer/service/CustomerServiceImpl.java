package com.wba.api.processcustomer.service;

import com.mongodb.MongoException;
import com.wba.api.processcustomer.model.PatientModel;
import com.wba.api.processcustomer.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    PatientRepository patientRepository;

    /**
     * @param patientModel
     * @return Method for updating tbf0_patient data
     */
    public void updatePatientRecord(PatientModel patientModel) {

        try {

            PatientModel exsitingModel = patientRepository.findByPatientId(patientModel.getPatientId());

            if (exsitingModel == null) { // If no data exist for the requested patient

                LOGGER.warn("No Patient record existing with given ID...{}", patientModel.getPatientId());
                LOGGER.info("Execution terminated because of no data found :: patientID - {}", patientModel.getPatientId());

                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient record not found !!!");
            }

            patientModel.setId(exsitingModel.getId()); // setting ID for update purpose
            patientRepository.save(patientModel); //DB update

            LOGGER.info("Patient record successfully updated for the patient ID: {}", patientModel.getPatientId());

        } catch (MongoException | DataAccessResourceFailureException e) { // handling MongoDB access error

            LOGGER.error("Unable to establish DB Connection ....", e);

        }
    }

}
