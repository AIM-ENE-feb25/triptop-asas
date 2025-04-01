package Triptop.Applicatie.factory.betaling;

import Triptop.Applicatie.dto.betaling.BetalingsResultaat;
import Triptop.Applicatie.dto.betaling.BetalingsVerzoek;
import Triptop.Applicatie.model.betaling.BetalingsStatus;

public interface BetalingAdapter {
    public BetalingsResultaat verwerkBetaling(BetalingsVerzoek betaling);

    BetalingsStatus controleerStatus(String betalingId);
}
