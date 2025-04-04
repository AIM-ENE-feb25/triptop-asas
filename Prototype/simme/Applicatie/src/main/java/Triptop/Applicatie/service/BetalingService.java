package Triptop.Applicatie.service;

import Triptop.Applicatie.dto.betaling.BetalingsResultaat;
import Triptop.Applicatie.dto.betaling.BetalingsVerzoek;
import Triptop.Applicatie.dto.betaling.DetailedBetalingStatus;

public interface BetalingService {
    public BetalingsResultaat verwerkBetaling(BetalingsVerzoek betaling);

    public DetailedBetalingStatus haalBetalingOp(String betalingId);
}
