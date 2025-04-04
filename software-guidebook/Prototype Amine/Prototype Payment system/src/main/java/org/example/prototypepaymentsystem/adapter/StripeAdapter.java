package org.example.prototypepaymentsystem.adapter;

import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import org.example.prototypepaymentsystem.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripeAdapter implements PaymentPort {

    @Value("${stripe.client.key}")
    private String clientId;

    @Value("${stripe.secret.key}")
    private String clientSecret;

    @PostConstruct
    public void init() {
        configure();
    }

    private StripeClient client;
    @Override
    public String createPayment(PaymentDTO paymentDTO) {

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount((long) (paymentDTO.getAmount() * 100))
                        .setCurrency(paymentDTO.getCurrencyCode())
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();
        try{
            PaymentIntent paymentIntent = client.paymentIntents().create(params);
            System.out.println(paymentIntent);
            return paymentIntent.getId();
    }catch (StripeException e){
            System.out.println("Error: "+e.getMessage());
        e.printStackTrace();
    }
        return null;
    }

    @Override
    public Boolean confirmPayment(String paymentId) {
        try {
            PaymentIntent resource = client.paymentIntents().retrieve(paymentId);

            PaymentIntentConfirmParams paramsConfirm =
                    PaymentIntentConfirmParams.builder()
                            .setPaymentMethod("pm_card_visa")
                            .setReturnUrl("https://www.example.com")
                            .build();
            PaymentIntent paymentConfirm = resource.confirm(paramsConfirm);

            return paymentConfirm.getStatus().equals("succeeded");
        } catch (StripeException e) {
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void configure() {
        this.client = new StripeClient(clientSecret);

    }

}
