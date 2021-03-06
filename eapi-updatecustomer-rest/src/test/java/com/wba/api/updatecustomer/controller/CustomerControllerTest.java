package com.wba.api.updatecustomer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wba.api.updatecustomer.constant.EapiErrorEnum;
import com.wba.api.updatecustomer.constant.WalgErrorEnum;
import com.wba.api.updatecustomer.dto.PatientDto;
import com.wba.api.updatecustomer.dto.PaymentDto;
import com.wba.api.updatecustomer.dto.ShippingAddressDto;
import com.wba.api.updatecustomer.exception.ControllerAdvisor;
import com.wba.api.updatecustomer.service.CustomerTopicService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author 165571
 * @href <mohammedanas.abdulrasheed@ust.com>
 * @createdon 2/26/2021
 */

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    CustomerTopicService customerTopicService;
    @InjectMocks
    CustomerController customerController;
    String requestJson;
    PatientDto patientDto;
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new ControllerAdvisor()).build();

        patientDto = new PatientDto();

        /**--------Building PatientDto Test Obj-----------------------*/
        patientDto.setPatientId("PID000X");
        ShippingAddressDto customerShippingAddress = new ShippingAddressDto();
        customerShippingAddress.setAddressLine1("ADRES001");
        customerShippingAddress.setCity("CITY001");
        customerShippingAddress.setState("STATE001");
        customerShippingAddress.setZipCode("ZIP001");

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setZipCode("ZIP001");
        paymentDto.setCreditCard("CRD001");

        patientDto.setCustomerShippingAddress(customerShippingAddress);
        patientDto.getProfilePaymentDetails().add(paymentDto);
        /**--------------------------------------------------------------*/

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        requestJson = ow.writeValueAsString(patientDto);
    }

    @Test
    public void updateCustomer_succescenario() throws Exception {

        when(customerTopicService.pushCustomerDataToTopic(patientDto)).thenReturn("Update sent successfully...");

        mockMvc.perform(post("/v1/pharmacy/patient/update")
                .header("authorization", "TKN001")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().json("{'message':'Update sent successfully...'}"));

    }

    @Test
    public void updateCustomer_verify_invalidaAuthentication() throws Exception {

        mockMvc.perform(post("/v1/pharmacy/patient/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()))
                .andExpect(content().json("{'messages':[{'code':'" + WalgErrorEnum.WAG_E_UNAUTHORIZED_REQUEST_1001 + "','message':'" + EapiErrorEnum.ERRROR401.getMessage() + "','type':'ERROR'}]}"));

    }

    @Test
    public void updateCustomer_verify_methodnotallowed() throws Exception {

        mockMvc.perform(get("/v1/pharmacy/patient/update").header("authorization", "TKN001")
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is(HttpStatus.METHOD_NOT_ALLOWED.value()))
                .andExpect(content().json("{'messages':[{'code':'" + WalgErrorEnum.WAG_E_INVALID_METHOD_1001 + "','message':'" + EapiErrorEnum.ERRROR405.getMessage() + "','type':'ERROR'}]}"));
    }


    @Test
    public void updateCustomer_verify_internalserverErrorHandling() throws Exception {

        when(customerTopicService.pushCustomerDataToTopic(patientDto)).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, EapiErrorEnum.ERRROR500.getMessage()));

        mockMvc.perform(post("/v1/pharmacy/patient/update")
                .header("authorization", "TKN001")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(content().json("{'messages':[{'code':'" + WalgErrorEnum.WAG_E_SVC_ERROR_1000 + "','message':'" + EapiErrorEnum.ERRROR500.getMessage() + "','type':'ERROR'}]}"));

    }

    @Test
    public void updateCustomer_verify_backendErrorHandling() throws Exception {

        when(customerTopicService.pushCustomerDataToTopic(patientDto)).thenThrow(new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, EapiErrorEnum.ERRROR503.getMessage()));

        mockMvc.perform(post("/v1/pharmacy/patient/update")
                .header("authorization", "TKN001")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()))
                .andExpect(content().json("{'messages':[{'code':'" + WalgErrorEnum.WAG_E_SVC_ERROR_1000 + "','message':'" + EapiErrorEnum.ERRROR503.getMessage() + "','type':'ERROR'}]}"));
    }

    @Test
    public void updateCustomer_verify_badrequestHandling() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        patientDto = null;
        requestJson = ow.writeValueAsString(patientDto);

        mockMvc.perform(post("/v1/pharmacy/patient/update")
                .header("authorization", "TKN001")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().json("{'messages':[{'code':'" + WalgErrorEnum.WAG_E_INVALID_REQUEST_1001 + "','message':'" + EapiErrorEnum.ERRROR400.getMessage() + "','type':'ERROR'}]}"));

    }

    @Test
    public void updateCustomer_whenReqBodyMissing() throws Exception {

        mockMvc.perform(post("/v1/pharmacy/patient/update")
                .header("authorization", "TKN001")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().json("{'messages':[{'code':'" + WalgErrorEnum.WAG_E_INVALID_REQUEST_1001 + "','message':'" + EapiErrorEnum.ERRROR400.getMessage() + "','type':'ERROR'}]}"));

    }
}
