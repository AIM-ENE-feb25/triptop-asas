package Triptop.Applicatie.dto.betaling;

import Triptop.Applicatie.model.betaling.BetalingsStatus;

public class BetalingsResultaat {
    private String betalingId;
    private BetalingsStatus status;
    private String redirectUrl;
}