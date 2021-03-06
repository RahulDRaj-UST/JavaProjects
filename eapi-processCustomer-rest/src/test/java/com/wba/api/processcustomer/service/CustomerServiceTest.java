package com.wba.api.processcustomer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wba.api.processcustomer.model.PatientModel;
import com.wba.api.processcustomer.repository.PatientRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.Assert.assertEquals;




@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    @Mock
    @Autowired
    PatientRepository patientRepository;
    @InjectMocks
    @Autowired
    CustomerServiceImpl customerService;
    PatientModel testPatientRecord;

    /**
     * Using Embedded MongoDB for doing unit test
     */
    public void saveTestData() {

        String testData = "{\n" +
                "  \"patientId\": \"5851900100499\",\n" +
                "  \"firstName\": \"TESTBEFORE001\",\n" +
                "  \"middleInit\": \"patient middle initial\",\n" +
                "  \"lastName\": \"nŮźDåŷ:ė\",\n" +
                "  \"surnameSuffix\": \"patient name suffix (Jr, Sr, thrird,...)\",\n" +
                "  \"gender\": \"patient gender\",\n" +
                "  \"email\": \"nŮźDåŷ:ė\",\n" +
                "  \"dob\": \"mm/dd/yyyy, 08/25/1909\",\n" +
                "  \"phoneNumberAreaCode\": \"nŮźDåŷ:ė\",\n" +
                "  \"phoneNumber\": \"nŮźDåŷ:ė\",\n" +
                "  \"preferredStoreNumber\": 0,\n" +
                "  \"lastFilledStoreNumber\": \"string\",\n" +
                "  \"preferredPaymentMethod\": \"string\",\n" +
                "  \"previousFilledLastMile\": \"string\",\n" +
                "  \"addressLine1\": \"nŮźDåŷ:ė\",\n" +
                "  \"city\": \"nŮźDåŷ:ė\",\n" +
                "  \"zipCode\": \"Encrypted - string FPE\",\n" +
                "  \"state\": \"string\",\n" +
                "  \"cardType\": \"crdtype\",\n" +
                "  \"creditCard\": \"string\",\n" +
                "  \"lastFourDigits\": 0,\n" +
                "  \"expiryMonth\": 0,\n" +
                "  \"expiryYear\": 0,\n" +
                "  \"isDefault\": \"true\"\n" +
                "}";

        try {

            PatientModel patintModel = new ObjectMapper().readValue(testData, PatientModel.class);
            patientRepository.save(patintModel);

            testPatientRecord = patintModel;
            testPatientRecord.setFirstName("TESTAFTER001");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updatePatientRecord_successScenario() {

        saveTestData();
        customerService.updatePatientRecord(testPatientRecord);
        assertEquals("TESTAFTER001", patientRepository.findByPatientId("5851900100499").getFirstName());
    }

    @Test
    public void updatePatientRecord_whenNodataFound() {

        testPatientRecord = new PatientModel();
        testPatientRecord.setPatientId("7855595");
        expectedEx.expect(ResponseStatusException.class);
        customerService.updatePatientRecord(testPatientRecord);
    }
}
