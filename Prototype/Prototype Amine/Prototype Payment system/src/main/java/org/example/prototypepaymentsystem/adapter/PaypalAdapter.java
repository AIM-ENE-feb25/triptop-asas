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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
    public String createPayment(PaymentDTO paymentDTO) {

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

        CompletableFuture<String> ratFuture = new CompletableFuture<>();

        ordersController.createOrderAsync(createOrderInput).thenAccept(result -> {
            System.out.println("Order created: " + result.getResult().getId());
            ratFuture.complete(result.getResult().getId());
        }).exceptionally(exception -> {
            exception.printStackTrace();
            return null;
        });
            try {
                return ratFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        }

    public Boolean confirmPayment(String paymentId) {
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

        CompletableFuture<Boolean> resultFuture = new CompletableFuture<>();

        ordersController.confirmOrderAsync(confirmOrderInput).thenAccept(resultConfirm -> {
            ordersController.captureOrderAsync(captureOrderInput).thenAccept(resultCapture -> {
                System.out.println(resultCapture.getResult());
                resultFuture.complete(true);
            }).exceptionally(exception -> {
                exception.printStackTrace();
                resultFuture.complete(false);
                return null;
            });
            System.out.println(resultConfirm.getResult());
        }).exceptionally(exception -> {
            exception.printStackTrace();
            resultFuture.complete(false);
            return null;
        });

        try {
            return resultFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
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
