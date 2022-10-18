package com.straumann.blr.cloudapim.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@SpringBootApplication
@RestController
public class CloudApimServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudApimServiceApplication.class, args);
    }

    @Value("service.name")
    private String serviceName;

    @GetMapping("/getApi")
    public String getApiMethod(){
        return "getApiMethod";
    }

    @GetMapping("/getServiceName")
    public String getServiceName(){
        return serviceName+" : Service is up";
    }

}
