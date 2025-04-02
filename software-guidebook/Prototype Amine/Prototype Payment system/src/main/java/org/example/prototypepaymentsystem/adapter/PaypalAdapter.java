package org.example.prototypepaymentsystem.adapter;

import com.paypal.sdk.Environment;
import com.paypal.sdk.PaypalServerSdkClient;
import com.paypal.sdk.authentication.ClientCredentialsAuthModel;
import com.paypal.sdk.controllers.OrdersController;
import com.paypal.sdk.models.*;
import jakarta.annotation.PostConstruct;
import org.example.prototypepaymentsystem.dto.PaymentDTO;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PaypalAdapter implements PaymentPort {

    @Value("${paypal.client.key}")
    private String OAuthClientId;

    @Value("${paypal.secret.key}")
    private String OAuthClientSecret;

    private OrdersController ordersController;

    @PostConstruct
    public void init() {
        configure();
        this.ordersController = client.getOrdersController();
    }

    private PaypalServerSdkClient client;

    @Override
    public void createPayment(PaymentDTO paymentDTO) {
        CreateOrderInput createOrderInput = new CreateOrderInput.Builder(
                null,
                new OrderRequest.Builder(
                        CheckoutPaymentIntent.CAPTURE,
                        Arrays.asList(
                                new PurchaseUnitRequest.Builder(
                                        new AmountWithBreakdown.Builder(
                                                paymentDTO.getCurrencyCode(),
                                                String.valueOf(paymentDTO.getAmount())
                                        )
                                                .build()
                                )
                                        .build()
                        )
                )
                        .build()
        )
                .prefer("return=minimal")
                .build();

        ordersController.createOrderAsync(createOrderInput).thenAccept(result -> {
            System.out.println(result.getResult());
        }).exceptionally(exception -> {
            exception.printStackTrace();
            return null;
        });
    }

    public void confirmPayment(String paymentId) {
        CaptureOrderInput captureOrderInput = new CaptureOrderInput.Builder(
                paymentId,
                null
        )
                .prefer("return=minimal")
                .build();

        ConfirmOrderInput confirmOrderInput = new ConfirmOrderInput.Builder(
                paymentId,
                null
        )
                .prefer("return=minimal")
                .body(new ConfirmOrderRequest.Builder(
                        new PaymentSource.Builder()
                                .card(new CardRequest.Builder()
                                        .name("Jan")
                                        .number("4567832222222")
                                        .expiry("2099-11")
                                        .securityCode("123")
                                        .build())
                                .build()
                )
                        .build())
                .build();

        ordersController.confirmOrderAsync(confirmOrderInput).thenAccept(resultConfirm -> {
            ordersController.captureOrderAsync(captureOrderInput).thenAccept(resultCapture -> {
                System.out.println(resultCapture.getResult());
            }).exceptionally(exception -> {
                exception.printStackTrace();
                return null;
            });
            System.out.println(resultConfirm.getResult());
        }).exceptionally(exception -> {
            exception.printStackTrace();
            return null;
        });


    }

    public void configure() {
        PaypalServerSdkClient client = new PaypalServerSdkClient.Builder()
                .loggingConfig(builder -> builder
                        .level(Level.DEBUG)
                        .requestConfig(logConfigBuilder -> logConfigBuilder.body(true))
                        .responseConfig(logConfigBuilder -> logConfigBuilder.headers(true)))
                .httpClientConfig(configBuilder -> configBuilder
                        .timeout(0))
                .clientCredentialsAuth(new ClientCredentialsAuthModel.Builder(
                        this.OAuthClientId,
                        this.OAuthClientSecret
                )
                        .build())
                .environment(Environment.SANDBOX)
                .build();

        this.client = client;
    }
}
