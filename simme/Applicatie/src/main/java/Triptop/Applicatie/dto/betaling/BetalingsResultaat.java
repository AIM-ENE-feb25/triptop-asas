package Triptop.Applicatie.dto.betaling;

import Triptop.Applicatie.model.betaling.BetalingsStatus;

import lombok.Data;

@Data
public class BetalingsResultaat {
    private String betalingId;
    private BetalingsStatus status;
    private String redirectUrl;
}