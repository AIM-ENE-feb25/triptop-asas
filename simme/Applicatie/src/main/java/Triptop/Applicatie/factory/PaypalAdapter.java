package Triptop.Applicatie.factory;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Triptop.Applicatie.dto.betaling.BetalingsVerzoek;
import Triptop.Applicatie.dto.betaling.DetailedBetalingStatus;
import Triptop.Applicatie.model.BetalingsStatus;
import Triptop.Applicatie.model.Betaling;
import Triptop.Applicatie.model.BetalingsMethode;

@Component
public class PaypalAdapter implements BetalingAdapter {

    private final String clientId = "AUxgrM7CvQa1ZCq6R1s7LJZG5SS6sbpEBJA9-QUjhGyvzBSVJVIk5pkqekxjCjhjaSFkRoWkRDOSQ9YD";
    private final String clientSecret = "ECifq2F2ZkgmsinKX4Ihmb67fBYQJb_i8zILaXT3sTCWkx1ctCecUoeaJXc8r0dh0Gb7erFpfDwPZztX";
    private final String mode = "sandbox"; // Change to "live" for production

    private APIContext apiContext;

    public PaypalAdapter() {
        apiContext = new APIContext(clientId, clientSecret, mode);
    }

    @Override
    public Betaling verwerkBetaling(BetalingsVerzoek betaling) {
        Betaling betalingEntity = new Betaling();

        try {
            Amount amount = new Amount();
            amount.setCurrency("EUR");
            amount.setTotal(String.format("%.2f", betaling.getBedrag()));

            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setDescription("Reservering: " + betaling.getReserveringId());

            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);

            Payer payer = new Payer();
            payer.setPaymentMethod("paypal");

            Payment payment = new Payment();
            payment.setIntent("SALE");
            payment.setPayer(payer);
            payment.setTransactions(transactions);

            RedirectUrls redirectUrls = new RedirectUrls();
            redirectUrls.setCancelUrl("http://localhost:8080/betaling/cancel");
            redirectUrls.setReturnUrl("http://localhost:8080/betaling/success");
            payment.setRedirectUrls(redirectUrls);

            Payment createdPayment = payment.create(apiContext);

            String redirectUrl = null;
            for (Links link : createdPayment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    redirectUrl = link.getHref();
                    break;
                }
            }

            betalingEntity.setBetalingId(createdPayment.getId());
            betalingEntity.setReserveringId(betaling.getReserveringId());
            betalingEntity.setBedrag(betaling.getBedrag());
            betalingEntity.setMethode(betaling.getMethode());
            betalingEntity.setStatus(BetalingsStatus.PENDING);
            betalingEntity.setTijdstempel(LocalDateTime.now());
            betalingEntity.setRedirectUrl(redirectUrl);

        } catch (PayPalRESTException e) {
            betalingEntity.setStatus(BetalingsStatus.MISLUKT);
            System.err.println("Error processing PayPal payment: " + e.getMessage());
        }

        return betalingEntity;
    }

    @Override
    public DetailedBetalingStatus controleerStatus(String betalingId) {
        DetailedBetalingStatus status = new DetailedBetalingStatus();
        status.setBetalingId(betalingId);

        try {
            Payment payment = Payment.get(apiContext, betalingId);

            String paypalState = payment.getState();
            switch (paypalState) {
                case "approved":
                case "completed":
                    status.setStatus(BetalingsStatus.VOLTOOID);
                    break;
                case "created":
                    status.setStatus(BetalingsStatus.PENDING);
                    break;
                case "canceled":
                    status.setStatus(BetalingsStatus.GEANNULEERD);
                    break;
                case "failed":
                    status.setStatus(BetalingsStatus.MISLUKT);
                    break;
                default:
                    status.setStatus(BetalingsStatus.VERWERKING);
            }

            Transaction transaction = payment.getTransactions().get(0);
            status.setBedrag(Double.parseDouble(transaction.getAmount().getTotal()));
            status.setTijdstempel(LocalDateTime.now());
            status.setMethode(BetalingsMethode.PAYPAL);

        } catch (PayPalRESTException e) {
            status.setStatus(BetalingsStatus.MISLUKT);
            status.setTijdstempel(LocalDateTime.now());
            System.err.println("Error checking PayPal payment status: " + e.getMessage());
        }

        return status;
    }
}
