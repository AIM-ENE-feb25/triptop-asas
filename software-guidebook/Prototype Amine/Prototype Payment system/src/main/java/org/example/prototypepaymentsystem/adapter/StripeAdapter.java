package org.example.prototypepaymentsystem.adapter;

import com.paypal.sdk.Environment;
import com.paypal.sdk.PaypalServerSdkClient;
import com.paypal.sdk.authentication.ClientCredentialsAuthModel;
import com.paypal.sdk.controllers.OrdersController;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCaptureParams;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import org.example.prototypepaymentsystem.dto.PaymentDTO;
import org.slf4j.event.Level;
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
    public void createPayment(PaymentDTO paymentDTO) {

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
    }catch (StripeException e){
            System.out.println("Error: "+e.getMessage());
        e.printStackTrace();
    }
    }

    @Override
    public void confirmPayment(String paymentId) {
        try {
            PaymentIntent resource = client.paymentIntents().retrieve(paymentId);

            PaymentIntentConfirmParams paramsConfirm =
                    PaymentIntentConfirmParams.builder()
                            .setPaymentMethod("pm_card_visa")
                            .setReturnUrl("https://www.example.com")
                            .build();
            PaymentIntent paymentConfirm = resource.confirm(paramsConfirm);

            System.out.println(paymentConfirm);
        } catch (StripeException e) {
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
    }

    public void configure() {
        this.client = new StripeClient(clientSecret);

    }

}
