package com.example.prototype_sybren.controller;

import com.example.prototype_sybren.service.BoekingOverzichtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoekingController {

    private BoekingOverzichtService boekingOverzichtService;

    @Autowired
    public BoekingController(BoekingOverzichtService boekingOverzichtService) {
        this.boekingOverzichtService = boekingOverzichtService;
    }

    @GetMapping("/overnachting")
    public String haalOvernachtingenOp() {
        return boekingOverzichtService.haalOvernachtingenOp();
    }


    @PostMapping("/boeking")
    public String slaBoekingenOp() {
        return "boekingen";
    }
}
