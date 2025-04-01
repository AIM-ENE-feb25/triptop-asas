package Triptop.Applicatie.service;

import org.springframework.stereotype.Service;

import Triptop.Applicatie.dto.betaling.BetalingsResultaat;
import Triptop.Applicatie.dto.betaling.BetalingsVerzoek;
import Triptop.Applicatie.model.betaling.Betaling;

@Service
public class BetalingServiceImpl implements BetalingService {

    @Override
    public BetalingsResultaat verwerkBetaling(BetalingsVerzoek betaling) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verwerkBetaling'");
    }

    @Override
    public Betaling haalBetalingOp(String betalingId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'haalBetalingOp'");
    }

}
