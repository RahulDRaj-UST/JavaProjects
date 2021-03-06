package com.wba.api.updatecustomer.service;

import com.wba.api.updatecustomer.constant.EapiErrorEnum;
import com.wba.api.updatecustomer.dto.PatientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author 165571
 * @href <mohammedanas.abdulrasheed@ust.com>
 * @createdon 2/24/2021
 */

@Service
public class CustomerTopicServiceImpl implements CustomerTopicService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerTopicServiceImpl.class);
    boolean isDataPushSuccess = true;// variable for determining kafka-push is success or not.

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, PatientDto> kafkaTemplate;

    /**
     * Service for pushing data to kafka-topic
     */
    public String pushCustomerDataToTopic(PatientDto patientObj) {

        if (patientObj == null || patientObj.getPatientId() == null || patientObj.getPatientId().trim().isEmpty()) { // as per SRS 'patient ID' is mandatory info

            LOGGER.error(" **Data validation Error** Received message not in expected format....unable to push this message to topic...");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EapiErrorEnum.ERRROR400.getMessage());
        }

        ListenableFuture<SendResult<String, PatientDto>> kafkafutureObj = kafkaTemplate.send(topic, patientObj.getPatientId(), patientObj); // push the 'patientObj' to the topic 'customer_topic' for further processing

        if (kafkafutureObj != null) {

            kafkafutureObj.addCallback(new ListenableFutureCallback<SendResult<String, PatientDto>>() {

                public void onSuccess(SendResult<String, PatientDto> result) {
                    isDataPushSuccess = true;
                }

                public void onFailure(Throwable ex) {
                    isDataPushSuccess = false;
                }
            });
        }

        if (isDataPushSuccess) {

//            LOGGER.info("-------- Message successfully pushed with below Info ---------------");
//            LOGGER.info("Topic Name : {}", topic);
//            LOGGER.info("MSG Id : {}", patientObj.getPatientId());
//            LOGGER.info("------------------------------------------------------------");
            return "Update sent successfully";

        } else {

//            LOGGER.info("---***** Unable to push below info to topic *****---");
//            LOGGER.info("Topic Name : {}", topic);
//            LOGGER.info("MSG Id : {}", patientObj.getPatientId());
//            LOGGER.info("------------------------------------------------------------");

            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, EapiErrorEnum.ERRROR503.getMessage());
        }

    }
}
