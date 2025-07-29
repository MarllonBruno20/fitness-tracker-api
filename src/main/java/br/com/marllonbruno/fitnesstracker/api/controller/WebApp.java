package br.com.marllonbruno.fitnesstracker.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebApp {

    @GetMapping("/hello")
    @ResponseBody
    public String HelloWord(){
        return "<h1>Hello Word</h1>";
    }
}
