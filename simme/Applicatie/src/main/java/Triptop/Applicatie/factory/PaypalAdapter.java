package Triptop.Applicatie.factory;

import org.springframework.stereotype.Component;

import Triptop.Applicatie.dto.betaling.BetalingsResultaat;
import Triptop.Applicatie.dto.betaling.BetalingsVerzoek;
import Triptop.Applicatie.dto.betaling.DetailedBetalingStatus;
import Triptop.Applicatie.model.Betaling;

@Component
public class PaypalAdapter implements BetalingAdapter {

    @Override
    public Betaling verwerkBetaling(BetalingsVerzoek betaling) {
        return null;
    }

    @Override
    public DetailedBetalingStatus controleerStatus(String betalingId) {
        return null;
    }
}