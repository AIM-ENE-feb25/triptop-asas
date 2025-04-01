package Triptop.Applicatie.service;

import Triptop.Applicatie.model.Betaling;
import Triptop.Applicatie.model.BetalingsResultaat;

public interface BetalingService {
    public BetalingsResultaat verwerkBetaling(BetalingsVerzoek betaling);

    public Betaling haalBetalingOp(String betalingId);
}
