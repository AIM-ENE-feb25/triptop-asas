package Triptop.Applicatie.factory;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;

import Triptop.Applicatie.dto.betaling.BetalingsVerzoek;
import Triptop.Applicatie.dto.betaling.DetailedBetalingStatus;
import Triptop.Applicatie.model.BetalingsStatus;
import Triptop.Applicatie.model.Betaling;
import Triptop.Applicatie.model.BetalingsMethode;

@Component
public class StripeAdapter implements BetalingAdapter {

    private final String apiKey = "sk_test_51N4OmtHDpfFA764U0V4Swu8jIkhL0eAKcqNSGMBVTkRKwjaUJwE02IlSacYUxAvfRPgXKinlKjkYgS0xp9UDlVlq00qU7SjCVO";

    @PostConstruct
    public void init() {
        Stripe.apiKey = apiKey;
    }

    @Override
    public Betaling verwerkBetaling(BetalingsVerzoek betaling) {
        Betaling betalingEntity = new Betaling();

        try {
            long amountInCents = (long) (betaling.getBedrag() * 100);

            PaymentIntentCreateParams.Builder paramsBuilder = PaymentIntentCreateParams.builder()
                    .setAmount(amountInCents)
                    .setCurrency("eur")
                    .setDescription("Reservering: " + betaling.getReserveringId());

            paramsBuilder.setAutomaticPaymentMethods(
                    PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                            .setEnabled(true)
                            .build());

            PaymentIntent paymentIntent = PaymentIntent.create(paramsBuilder.build());

            betalingEntity.setBetalingId(paymentIntent.getId());
            betalingEntity.setReserveringId(betaling.getReserveringId());
            betalingEntity.setBedrag(betaling.getBedrag());
            betalingEntity.setMethode(betaling.getMethode());
            betalingEntity.setStatus(BetalingsStatus.PENDING);
            betalingEntity.setTijdstempel(LocalDateTime.now());
            betalingEntity.setRedirectUrl("/stripe-betalen.html?client_secret=" + paymentIntent.getClientSecret());

        } catch (StripeException e) {
            betalingEntity.setStatus(BetalingsStatus.MISLUKT);
            System.err.println("Error processing payment: " + e.getMessage());
        }

        return betalingEntity;
    }

    @Override
    public DetailedBetalingStatus controleerStatus(String betalingId) {
        DetailedBetalingStatus status = new DetailedBetalingStatus();
        status.setBetalingId(betalingId);

        try {
            PaymentIntent paymentIntent = PaymentIntent.retrieve(betalingId);

            switch (paymentIntent.getStatus()) {
                case "succeeded":
                    status.setStatus(BetalingsStatus.VOLTOOID);
                    break;
                case "processing":
                    status.setStatus(BetalingsStatus.VERWERKING);
                    break;
                case "requires_payment_method":
                case "requires_confirmation":
                case "requires_action":
                    status.setStatus(BetalingsStatus.PENDING);
                    break;
                case "canceled":
                    status.setStatus(BetalingsStatus.GEANNULEERD);
                    break;
                default:
                    status.setStatus(BetalingsStatus.MISLUKT);
            }

            status.setBedrag(paymentIntent.getAmount() / 100.0);
            status.setTijdstempel(LocalDateTime.now());
            status.setMethode(BetalingsMethode.STRIPE);

        } catch (StripeException e) {
            status.setStatus(BetalingsStatus.MISLUKT);
            status.setTijdstempel(LocalDateTime.now());
            System.err.println("Error checking payment status: " + e.getMessage());
        }

        return status;
    }
}
