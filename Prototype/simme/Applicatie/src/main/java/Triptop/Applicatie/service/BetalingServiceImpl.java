package Triptop.Applicatie.service;

import org.springframework.stereotype.Service;

import Triptop.Applicatie.dto.betaling.BetalingsResultaat;
import Triptop.Applicatie.dto.betaling.BetalingsVerzoek;
import Triptop.Applicatie.factory.BetalingAdapter;
import Triptop.Applicatie.factory.BetalingFactory;
import Triptop.Applicatie.model.Betaling;
import lombok.AllArgsConstructor;
import Triptop.Applicatie.repository.BetalingRepository;
import Triptop.Applicatie.dto.betaling.DetailedBetalingStatus;

@Service
@AllArgsConstructor
public class BetalingServiceImpl implements BetalingService {
    private final BetalingFactory betalingFactory;
    private final BetalingRepository betalingRepository;

    @Override
    public BetalingsResultaat verwerkBetaling(BetalingsVerzoek betaling) {
        BetalingAdapter betalingAdapter = betalingFactory.createBetalingAdapter(betaling.getMethode());

        Betaling betalingResultaat = betalingAdapter.verwerkBetaling(betaling);
        betalingRepository.save(betalingResultaat);

        BetalingsResultaat resultaat = new BetalingsResultaat();
        resultaat.setBetalingId(betalingResultaat.getBetalingId());
        resultaat.setStatus(betalingResultaat.getStatus());
        resultaat.setRedirectUrl(betalingResultaat.getRedirectUrl());

        return resultaat;
    }

    @Override
    public DetailedBetalingStatus haalBetalingOp(String betalingId) {
        Betaling betaling = betalingRepository.findById(betalingId)
                .orElseThrow(() -> new RuntimeException("Betaling niet gevonden"));

        BetalingAdapter betalingAdapter = betalingFactory.createBetalingAdapter(betaling.getMethode());

        var status = betalingAdapter.controleerStatus(betalingId);

        betaling.setStatus(status.getStatus());
        betalingRepository.save(betaling);

        return status;
    }

}
