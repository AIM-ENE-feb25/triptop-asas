package Triptop.Applicatie.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "betalingen")
public class Betaling {
    @Id
    private String betalingId;
    private String reserveringId;
    private double bedrag;
    private BetalingsStatus status;
    private BetalingsMethode methode;
    private LocalDateTime tijdstempel;
}
