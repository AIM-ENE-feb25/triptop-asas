package Triptop.Applicatie.service;

import org.springframework.stereotype.Service;

import Triptop.Applicatie.dto.betaling.BetalingsResultaat;
import Triptop.Applicatie.dto.betaling.BetalingsVerzoek;
import Triptop.Applicatie.factory.betaling.BetalingFactory;
import Triptop.Applicatie.model.betaling.Betaling;

import lombok.AllArgsConstructor;

import Triptop.Applicatie.factory.betaling.BetalingAdapter;

@Service
@AllArgsConstructor
public class BetalingServiceImpl implements BetalingService {
    private final BetalingFactory betalingFactory;

    @Override
    public BetalingsResultaat verwerkBetaling(BetalingsVerzoek betaling) {
        BetalingAdapter betalingAdapter = betalingFactory.createBetalingAdapter(betaling.getMethode());
        return betalingAdapter.verwerkBetaling(betaling);
    }

    @Override
    public Betaling haalBetalingOp(String betalingId) {
        BetalingAdapter betalingAdapter = betalingFactory.createBetalingAdapter(betaling.getMethode());
        return betalingAdapter.controleerStatus(betalingId);
    }

}
