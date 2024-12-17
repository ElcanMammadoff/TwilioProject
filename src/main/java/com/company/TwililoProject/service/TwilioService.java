package com.company.TwililoProject.service;

import com.company.TwililoProject.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    private final Example example;
    @Autowired
    public TwilioService(Example example) {
        this.example = example;
    }

    public void ExampleService(){
        example.main();
    }

}
