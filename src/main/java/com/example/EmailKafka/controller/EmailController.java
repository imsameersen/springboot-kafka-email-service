package com.example.EmailKafka.controller;

import com.example.EmailKafka.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/send")
    public String sendEmail(@RequestParam("message") String message) {
        kafkaProducer.sendMessage(message);
        return "Email notification sent successfully!";
    }
}
