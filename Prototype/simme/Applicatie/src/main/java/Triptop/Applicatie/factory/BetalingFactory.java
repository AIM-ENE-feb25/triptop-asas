package Triptop.Applicatie.factory;

import org.springframework.stereotype.Component;

import Triptop.Applicatie.model.BetalingsMethode;

@Component
public class BetalingFactory {
    public BetalingAdapter createBetalingAdapter(BetalingsMethode methode) {
        switch (methode) {
            case STRIPE:
                return new StripeAdapter();
            case PAYPAL:
                return new PaypalAdapter();
            default:
                throw new IllegalArgumentException("Ongeldige betalingsmethode: " + methode);
        }
    }
}
