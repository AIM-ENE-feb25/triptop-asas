package Triptop.Applicatie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import Triptop.Applicatie.dto.betaling.BetalingsResultaat;
import Triptop.Applicatie.dto.betaling.BetalingsVerzoek;
import Triptop.Applicatie.dto.betaling.DetailedBetalingStatus;
import Triptop.Applicatie.model.Betaling;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Triptop.Applicatie.service.BetalingService;

@RestController
@AllArgsConstructor
@RequestMapping("/betaling")
public class BetalingController {
    private final BetalingService betalingService;

    @PostMapping("/add")
    public BetalingsResultaat verwerkBetaling(@RequestBody BetalingsVerzoek betaling) {
        return betalingService.verwerkBetaling(betaling);
    }

    @GetMapping("/status")
    public DetailedBetalingStatus controleerStatus(@RequestParam String betalingId) {
        return betalingService.haalBetalingOp(betalingId);
    }
}
