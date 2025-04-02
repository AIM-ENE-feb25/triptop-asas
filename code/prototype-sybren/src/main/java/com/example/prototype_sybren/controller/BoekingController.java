package com.example.prototype_sybren.controller;

import com.example.prototype_sybren.domain.Boeking;
import com.example.prototype_sybren.domain.Overnachting;
import com.example.prototype_sybren.dto.OvernachtingResponse;
import com.example.prototype_sybren.repository.BoekingRepository;
import com.example.prototype_sybren.service.BoekingOverzichtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoekingController {

    private BoekingOverzichtService boekingOverzichtService;
    private BoekingRepository boekingRepository;

    @Autowired
    public BoekingController(BoekingOverzichtService boekingOverzichtService, BoekingRepository bookingRepository) {
        this.boekingOverzichtService = boekingOverzichtService;
        this.boekingRepository = bookingRepository;
    }

    @PostMapping("/overnachting")
    public ResponseEntity<List<OvernachtingResponse>> haalOvernachtingenOp(@RequestBody Overnachting overnachting) {
        List<OvernachtingResponse> overnachtingenMetKeyword = this.boekingOverzichtService.haalOvernachtingenOp(overnachting);
        return ResponseEntity.ok(overnachtingenMetKeyword);
    }


    @PostMapping("/boeking")
    public String slaBoekingOp(@RequestBody Boeking boeking) {
        return this.boekingRepository.slaBoekingOp(boeking);
    }
}
