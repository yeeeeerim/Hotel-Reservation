package com.back.bookingmodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController
{

    @GetMapping("test12")
    public String get(){
        return "test";
    }
}
