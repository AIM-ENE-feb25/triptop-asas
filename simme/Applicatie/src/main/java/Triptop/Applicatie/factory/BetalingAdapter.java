package Triptop.Applicatie.factory;

import Triptop.Applicatie.dto.betaling.BetalingsResultaat;
import Triptop.Applicatie.dto.betaling.BetalingsVerzoek;
import Triptop.Applicatie.dto.betaling.DetailedBetalingStatus;

public interface BetalingAdapter {
    public BetalingsResultaat verwerkBetaling(BetalingsVerzoek betaling);

    public DetailedBetalingStatus controleerStatus(String betalingId);
}
