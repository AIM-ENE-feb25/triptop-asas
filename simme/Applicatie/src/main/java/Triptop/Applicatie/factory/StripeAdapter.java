package Triptop.Applicatie.factory;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;

import Triptop.Applicatie.dto.betaling.BetalingsResultaat;
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
        // Initialize Stripe API with your secret key
        Stripe.apiKey = apiKey;
    }

    @Override
    public Betaling verwerkBetaling(BetalingsVerzoek betaling) {
        Betaling resultaat = new Betaling();

        try {
            // Convert amount to cents (Stripe uses smallest currency unit)
            long amountInCents = (long) (betaling.getBedrag() * 100);

            // Create payment intent parameters
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(amountInCents)
                    .setCurrency("eur")
                    .setDescription("Reservering: " + betaling.getReserveringId())
                    .setAutomaticPaymentMethods(
                            PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                    .setEnabled(true)
                                    .build())
                    .build();

            // Create the payment intent in Stripe
            PaymentIntent paymentIntent = PaymentIntent.create(params);

            // Set the response values
            resultaat.setBetalingId(paymentIntent.getId());
            resultaat.setStatus(BetalingsStatus.PENDING);

            // Client needs this URL to complete payment with Stripe.js
            resultaat.setRedirectUrl("/payment/complete?client_secret=" + paymentIntent.getClientSecret());

        } catch (StripeException e) {
            resultaat.setStatus(BetalingsStatus.MISLUKT);
            System.err.println("Error processing payment: " + e.getMessage());
        }

        return resultaat;
    }

    @Override
    public DetailedBetalingStatus controleerStatus(String betalingId) {
        DetailedBetalingStatus status = new DetailedBetalingStatus();
        status.setBetalingId(betalingId);

        try {
            // Retrieve payment intent from Stripe
            PaymentIntent paymentIntent = PaymentIntent.retrieve(betalingId);

            // Map Stripe status to your application status
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

            // Set other details
            status.setBedrag(paymentIntent.getAmount() / 100.0);
            status.setMethode(BetalingsMethode.STRIPE);
            status.setTijdstempel(LocalDateTime.now());

        } catch (StripeException e) {
            status.setStatus(BetalingsStatus.MISLUKT);
            status.setTijdstempel(LocalDateTime.now());
            System.err.println("Error checking payment status: " + e.getMessage());
        }

        return status;
    }
}
