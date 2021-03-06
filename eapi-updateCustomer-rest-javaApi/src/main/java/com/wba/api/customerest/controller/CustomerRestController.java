package com.wba.api.customerest.controller;

import com.wba.api.customerest.constant.EapiErrorEnum;
import com.wba.api.customerest.dto.PatientDto;
import com.wba.api.customerest.service.CustomerService;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;



@RestController
public class CustomerRestController {

    @Autowired
    CustomerService customerService;
    


    private static final Logger LOGGER= LoggerFactory.getLogger(CustomerRestController.class);

    @PostMapping(value = "/v1/pharmacy/patient/update", produces = "application/json")
    public  @ResponseBody String getInput(@RequestBody PatientDto patientDto,@RequestHeader(value="authorization",defaultValue = "") String header) {
    	if(patientDto.getPatientId().equals("")||patientDto.getPatientId() == null||patientDto.getPatientId().trim().equals(""))
    	{
    		LOGGER.info("Hitting '/v1/pharmacy/patient' null error");
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EapiErrorEnum.ERRROR400.getMessage());
    		
    	}
    	
    	if(header.trim().equals("")||header==null)
    	{
    		LOGGER.info("Hitting '/v1/pharmacy/patient' Auth Error");
    		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, EapiErrorEnum.ERRROR401.getMessage());
    		
    	}
        LOGGER.info("Hitting '/v1/pharmacy/patient' api.....");
        String result=customerService.sendToKafka(patientDto);
		return result;
		
    }
}
