package Triptop.Applicatie.model.betaling;

import java.time.LocalDateTime;

public class Betaling {
    private String betalingId;
    private String reserveringId;
    private double bedrag;
    private BetalingsStatus status;
    private BetalingsMethode methode;
    private LocalDateTime tijdstempel;
}

