package Triptop.Applicatie.dto.betaling;

import Triptop.Applicatie.model.BetalingsStatus;
import lombok.Data;

@Data
public class BetalingsResultaat {
    private String betalingId;
    private BetalingsStatus status;
    private String redirectUrl;
}