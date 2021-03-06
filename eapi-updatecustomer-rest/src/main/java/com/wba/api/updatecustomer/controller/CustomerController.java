package com.wba.api.updatecustomer.controller;

import com.wba.api.updatecustomer.constant.EapiErrorEnum;
import com.wba.api.updatecustomer.dto.PatientDto;
import com.wba.api.updatecustomer.service.CustomerTopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    CustomerTopicService customerTopicService;

    @PostMapping("/v1/pharmacy/patient/update")
    public @ResponseBody
    Map<String, String> updateConsumer(@RequestBody PatientDto patientObj, @RequestHeader Map<String, String> headers) {

        LOGGER.info("Hitting '/patient/update' request....");

        if (headers.get("authorization") == null) { // trigger com.wba.api.updatecustomer.exception if auth token is missing in header

            LOGGER.error("Authorization error .... terminating execution with com.wba.api.updatecustomer.exception ....");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, EapiErrorEnum.ERRROR401.getMessage());
        }

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("message", customerTopicService.pushCustomerDataToTopic(patientObj));
        return resultMap;
    }

}
