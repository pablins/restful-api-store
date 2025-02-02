package com.pablins.restfull_api.controller;

import com.pablins.restfull_api.entity.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String MESSAGE_TEMPLATE = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();//su referencia no puede ser reasignada a otro objeto

    @GetMapping("/greeting")
    public Greeting helloWorld(@RequestParam(defaultValue = "World!") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(MESSAGE_TEMPLATE, name));
    }

}
