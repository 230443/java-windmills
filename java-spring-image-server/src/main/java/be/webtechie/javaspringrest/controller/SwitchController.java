package be.webtechie.javaspringrest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import be.webtechie.gpio.LedController;

@RestController
public class SwitchController {

    @GetMapping("/on")
    public String swithLed() {
        
        LedController.getInstance().swithLed();
        return "ok\n";
    }
}