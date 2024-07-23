package com.example.EmailKafka.kafka;

import com.example.EmailKafka.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class KafkaConsumer {

    @Autowired
    private EmailService emailService;

    @Value("${email.recipients}")
    private String[] recipients;



    @KafkaListener(topics = "email_topic", groupId = "group_id")
    public void consume(String message) {
        List<String> recipientList = Arrays.asList(recipients);
        for(String recipient : recipientList)
        emailService.sendEmail(recipient, "Notification", message);
    }
}
