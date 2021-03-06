package com.wba.api.processcustomer.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wba.api.processcustomer.dto.PatientDto;
import com.wba.api.processcustomer.model.PatientModel;
import com.wba.api.processcustomer.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    CustomerService customerService;

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.consumer.group}")
    public void consumer(String msg) { // Kafka listener for "kafka.topic"

        LOGGER.info("Consumer received the Message:- {}", msg);

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            ModelMapper modelMapper = new ModelMapper(); // building patient Dto using model mapper helper class
            PatientDto patientDto =objectMapper.readValue(msg, PatientDto.class);
            PatientModel patientModel = new PatientModel();
            modelMapper.map(patientDto, patientModel);

            if (patientDto.getCustomerShippingAddress() != null)
                modelMapper.map(patientDto.getCustomerShippingAddress(), patientModel);
            if (!patientDto.getProfilePaymentDetails().isEmpty())
                modelMapper.map(patientDto.getProfilePaymentDetails().get(0), patientModel);

            customerService.updatePatientRecord(patientModel);

            LOGGER.info("Patient record successfully updated");

        } catch (Exception e) { // handling class cast com.wba.api.kafka.consumer.exception

            LOGGER.error("Exception occurs while casting received Object", e);
        }
    }
}
