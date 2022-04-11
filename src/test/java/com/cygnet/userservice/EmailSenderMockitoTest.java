package com.cygnet.userservice;

import com.cygnet.userservice.dto.Mail;
import com.cygnet.userservice.services.EmailSenderService;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {EmailSenderMockitoTest.class})
class EmailSenderMockitoTest
{

    Mail emailEntity;
    GreenMail serverA;


    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailSenderService emailSenderService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        emailEntity = mockItemMasterData();
        // Setup fake smtp server.
        serverA = new GreenMail(ServerSetupTest.SMTP);
        serverA.start();
    }


    private Mail mockItemMasterData() {
        Mail mail = new Mail();
        String toEmail = "apbanirudhgmail.com";
        String body = "Welcome";
        String subject = "Demo purpose";

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("samroman789@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        return mail;
    }


    @Test
    @Order(1)
    void emailserderserviceTest() throws MessagingException, IOException {

        emailSenderService.sendEmail("myonlinewallet01@gmail.com", "resetPasswordURL", "For testing purpose");

            Message[] messages = serverA.getReceivedMessages();

            Assert.assertNotNull(messages);
            Assert.assertEquals(0, messages.length);

    }
}
