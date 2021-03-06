package com.wba.api.customerest.service;


import com.wba.api.customerest.constant.EapiErrorEnum;
import com.wba.api.customerest.dto.PatientDto;
import com.wba.api.customerest.dto.PaymentDto;
import com.wba.api.customerest.dto.ShippingAddressDto;

import kafka.common.KafkaException;

import java.util.Properties;
import java.util.concurrent.Future;

import com.google.gson.Gson; 
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.server.ResponseStatusException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.KafkaStorageException;


@Component("customerService")
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerService customerService;

    /**
     * Method for Sending to Kafka
     */
    public String sendToKafka(PatientDto patientDto) {

        try {
        	
            LOGGER.info("Inside SendToKafka Service");
            Properties properties =  new Properties();
            properties.put("bootstrap.servers", "localhost:9092");
            properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            ProducerRecord producerRecord = new ProducerRecord("dev-customer", new Gson().toJson(patientDto));
            
            KafkaProducer kafkaProducer =  new KafkaProducer(properties);
//            if(kafkaProducer==null)
//            {
//            	throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, EapiErrorEnum.ERRROR503.getMessage());
//            }
          Future status =   kafkaProducer.send(producerRecord);
          if(status !=null)
          {
        	  status.wait(100);
          }
          
            System.out.println(status);
            

            

            
            
return "SUCCESS";
        }  catch (ResponseStatusException responseStatusException) {
            throw responseStatusException;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, EapiErrorEnum.ERRROR500.getMessage());
        }
    }

 
    

}
