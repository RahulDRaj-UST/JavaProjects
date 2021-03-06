package com.wba.api.processcustomer.common;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.wba.api.processcustomer.service.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;



@SpringBootTest
@RunWith(SpringRunner.class)
@EmbeddedKafka(partitions = 1, controlledShutdown = false, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class KafkaConsumerTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Mock
    CustomerServiceImpl customerService;

    @InjectMocks
    KafkaConsumer KafkaConsumer;

    Logger kafkaConsumerLog;
    ListAppender<ILoggingEvent> listAppender;

    @Before
    public void init() {

        // get Logback Logger
        kafkaConsumerLog = (Logger) LoggerFactory.getLogger(KafkaConsumer.class);

        // create and start a ListAppender
        listAppender = new ListAppender<>();
        listAppender.start();

        // add the appender to the logger
        // addAppender is outdated now
        kafkaConsumerLog.addAppender(listAppender);

    }

    @Test
    public void kafkaconsumer_when_receving_validMessage() {

        String testData = "{\n" +
                "  \"patientId\": \"58519001003\",\n" +
                "  \"firstName\": \"nŮźDåŷ:ė-t77\",\n" +
                "  \"middleInit\": \"MI\",\n" +
                "  \"lastName\": \"nŮźDåŷ:ė\",\n" +
                "  \"surnameSuffix\": \"JR\",\n" +
                "  \"gender\": \"male\",\n" +
                "  \"email\": \"nŮźDåŷ:ė\",\n" +
                "  \"dob\": \"08/25/1909\",\n" +
                "  \"phoneNumberAreaCode\": \"nŮźDåŷ:ė\",\n" +
                "  \"phoneNumber\": \"nŮźDåŷ:ė\",\n" +
                "  \"preferredStoreNumber\": 0,\n" +
                "  \"lastFilledStoreNumber\": \"11231\",\n" +
                "  \"preferredPaymentMethod\": \"CR Card\",\n" +
                "  \"previousFilledLastMile\": \"ASCX\",\n" +
                "  \"customerShippingAddress\": {\n" +
                "   \n" +
                "    \"city\": \"nŮźDåŷ:ė\",\n" +
                "    \"zipCode\": \"XTTA::!Q!~#\",\n" +
                "    \"state\": \"string\"\n" +
                "    \n" +
                "  },\n" +
                "  \"profilePaymentDetails\": [\n" +
                "    {\n" +
                "      \"cardType\": \"Credit\",\n" +
                "      \"creditCard\": \"A4745588XXX0037549\",\n" +
                "      \"lastFourDigits\": 77848,\n" +
                "      \"expiryMonth\": 21,\n" +
                "      \"expiryYear\": 2025,\n" +
                "      \"zipCode\": \"AZS005\"\n" +
                "    }\n" +
                "  ]     \n" +
                "       \n" +
                "}";

        KafkaConsumer.consumer(testData);

        List<ILoggingEvent> logsList = listAppender.list;

        // JUnit assertions
        assertEquals("Patient record successfully updated", logsList.get(1).getMessage());
        assertEquals(Level.INFO, logsList.get(1).getLevel());
    }

    @Test
    public void kafkaconsumer_when_receving_inValidMessage() {

        String testData = "{\n" + "  \"patientNameA\": \"5851900100499\",\n" + "}";
        KafkaConsumer.consumer(testData);

        // JUnit assertions
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals("Exception occurs while casting received Object", logsList.get(1).getMessage());
        assertEquals(Level.ERROR, logsList.get(1).getLevel());

    }
}
