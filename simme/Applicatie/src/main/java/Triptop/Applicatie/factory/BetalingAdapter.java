package Triptop.Applicatie.factory;

import Triptop.Applicatie.dto.betaling.BetalingsResultaat;
import Triptop.Applicatie.dto.betaling.BetalingsVerzoek;
import Triptop.Applicatie.dto.betaling.DetailedBetalingStatus;
import Triptop.Applicatie.model.Betaling;

public interface BetalingAdapter {
    public Betaling verwerkBetaling(BetalingsVerzoek betaling);

    public DetailedBetalingStatus controleerStatus(String betalingId);
}
