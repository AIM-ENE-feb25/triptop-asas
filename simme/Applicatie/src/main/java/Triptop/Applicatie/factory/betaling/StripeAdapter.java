package Triptop.Applicatie.factory.betaling;

import org.springframework.stereotype.Component;

import Triptop.Applicatie.dto.betaling.BetalingsResultaat;
import Triptop.Applicatie.dto.betaling.BetalingsVerzoek;
import Triptop.Applicatie.model.betaling.BetalingsStatus;

@Component
public class StripeAdapter implements BetalingAdapter {

    @Override
    public BetalingsResultaat verwerkBetaling(BetalingsVerzoek betaling) {
        return null;
    }

    @Override
    public BetalingsStatus controleerStatus(String betalingId) {
        return null;
    }
}