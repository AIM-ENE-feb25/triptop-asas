package Triptop.Applicatie.dto.betaling;

import Triptop.Applicatie.model.betaling.BetalingsMethode;
import lombok.Data;

@Data
public class BetalingsVerzoek {
    private String reserveringId;
    private double bedrag;
    private BetalingsMethode methode;
}
