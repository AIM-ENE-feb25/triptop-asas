package Triptop.Applicatie.service;

import Triptop.Applicatie.dto.betaling.BetalingsResultaat;
import Triptop.Applicatie.dto.betaling.BetalingsVerzoek;
import Triptop.Applicatie.model.betaling.Betaling;

public interface BetalingService {
    public BetalingsResultaat verwerkBetaling(BetalingsVerzoek betaling);

    public Betaling haalBetalingOp(String betalingId);
}
