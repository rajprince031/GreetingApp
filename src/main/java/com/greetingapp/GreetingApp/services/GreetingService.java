package com.greetingapp.GreetingApp.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getGreetingMessage(){
        return "I am greeting class";
    }
}
