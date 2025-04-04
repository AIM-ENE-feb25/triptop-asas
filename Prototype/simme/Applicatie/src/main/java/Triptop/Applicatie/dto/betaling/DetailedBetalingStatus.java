package Triptop.Applicatie.dto.betaling;

import java.time.LocalDateTime;

import Triptop.Applicatie.model.BetalingsMethode;
import Triptop.Applicatie.model.BetalingsStatus;
import lombok.Data;

@Data
public class DetailedBetalingStatus {
    private String betalingId;
    private BetalingsStatus status;
    private double bedrag;
    private BetalingsMethode methode;
    private LocalDateTime tijdstempel;
}
