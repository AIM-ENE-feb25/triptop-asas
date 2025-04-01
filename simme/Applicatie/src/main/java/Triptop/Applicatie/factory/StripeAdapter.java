package Triptop.Applicatie.factory;

import org.springframework.stereotype.Component;

import Triptop.Applicatie.dto.betaling.BetalingsResultaat;
import Triptop.Applicatie.dto.betaling.BetalingsVerzoek;
import Triptop.Applicatie.dto.betaling.DetailedBetalingStatus;

@Component
public class StripeAdapter implements BetalingAdapter {

    @Override
    public BetalingsResultaat verwerkBetaling(BetalingsVerzoek betaling) {
        return null;
    }

    @Override
    public DetailedBetalingStatus controleerStatus(String betalingId) {
        return null;
    }
}