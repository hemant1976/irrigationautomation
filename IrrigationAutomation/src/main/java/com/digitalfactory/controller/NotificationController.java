package com.digitalfactory.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.digitalfactory.model.Notification;
 
@RestController
public class NotificationController {
 
     private static final String TOPIC_ARN = "TOPIC_ARN";
 
    @Autowired
    private AmazonSNSClient amazonSNSClient;
 
    // URL - http://localhost:10093/addSubscription/+911234567890
    @PostMapping(value = "/addSubscription/{phoneNumber}")
    public ResponseEntity<String> addSubscription(@PathVariable final String phoneNumber) {
        System.out.println("Adding new phone number subscription = {} to the topic." +  phoneNumber);
        final SubscribeRequest subscribeRequest = new SubscribeRequest(TOPIC_ARN, "sms", phoneNumber);
        amazonSNSClient.subscribe(subscribeRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    // URL - http://localhost:10093/sendNotification
    // Sample request body -
    //  {
    //      "message": "Sensor Device APi is down."
    //  }
    @PostMapping(value = "/sendNotification")
    public ResponseEntity<String> publishMessageToTopic(@RequestBody final Notification notification) {
    	System.out.println("Publishing the notification = {} to the topic." + notification.toString());
        final PublishRequest publishRequest = new PublishRequest(TOPIC_ARN, notification.getMessage());
        amazonSNSClient.publish(publishRequest);
        return new ResponseEntity<>("Notification sent successfully!!", HttpStatus.OK);
    }
}