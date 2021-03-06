package com.wba.api.updatecustomer.service;

import com.wba.api.updatecustomer.constant.EapiErrorEnum;
import com.wba.api.updatecustomer.dto.PatientDto;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.Assert.assertEquals;

/**
 * @author 165571
 * @href <mohammedanas.abdulrasheed@ust.com>
 * @createdon 2/28/2021
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerTopicServiceImplTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    @Mock
    KafkaTemplate<String, PatientDto> kafkaTemplate;
    @InjectMocks
    CustomerTopicServiceImpl cutomerService;
    PatientDto patientDto;

    @Before
    public void init() {

        patientDto = new PatientDto();
        patientDto.setPatientId("TEST001");
    }

    @Test
    public void pushCustomerDataToTopic_successScenario() {

        assertEquals("Update sent successfully", cutomerService.pushCustomerDataToTopic(patientDto));
    }

    @Test
    public void pushCustomerDataToTopic_whenPatientIdNull() {

        patientDto = null;
        expectedEx.expect(ResponseStatusException.class);
        expectedEx.expectMessage(EapiErrorEnum.ERRROR400.getMessage());
        cutomerService.pushCustomerDataToTopic(patientDto);
    }

   /* @Test
    public void testpushCustomerDataToTopic_whenkafkaDown() {

    }*/

}