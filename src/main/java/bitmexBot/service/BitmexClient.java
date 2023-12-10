package bitmexBot.service;

import bitmexBot.model.AuthenticationHeaders;
import bitmexBot.model.Order;
import bitmexBot.model.OrderHttpRequest;
import bitmexBot.model.OrderRequest;
import bitmexBot.util.Endpoint;
import bitmexBot.util.Signature;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
public class BitmexClient {
    private final HttpClient httpClient = HttpClient.newBuilder().build();
    private final Gson gson = new Gson();

    private final String baseURL;
    private final String apiKey;
    private final String apiSecretKey;
    private boolean isReal;
    private final int EXPIRES_DELAY = 5;
    private final Signature signature = new Signature();





    public void sendOrder(Order order) throws NoSuchAlgorithmException, InvalidKeyException {
        String httpMethod = "POST";
        String data = "";
        String base = "/api/v1";

        if(order==null){
            data = "";
        }else{
            OrderRequest orderRequest = OrderRequest.toRequest(order);
            data = gson.toJson(orderRequest);
        }
        System.out.println(data);
        OrderHttpRequest orderHttpRequest = new OrderHttpRequest(order,baseURL, Endpoint.ORDER_ENDPOINT,httpMethod, getAuthenticationHeaders("POST",data,base+Endpoint.ORDER_ENDPOINT));
        try{
            HttpResponse<String> response = httpClient.send(orderHttpRequest.getHttpRequest(), HttpResponse.BodyHandlers.ofString());
            System.out.println(response);
        }catch (IOException | InterruptedException e){throw new RuntimeException(e);}

    }

    public void cancelOrder(){}

    private AuthenticationHeaders getAuthenticationHeaders(String httpMethod, String data, String path) throws NoSuchAlgorithmException, InvalidKeyException {
        long expires = System.currentTimeMillis() / 1000 + EXPIRES_DELAY;
        String signatureStr = signature.getSignature(apiSecretKey, httpMethod + path + expires + data);
        return  AuthenticationHeaders.builder()
                .apiKey(apiKey)
                .signature(signatureStr)
                .expires(Long.toString(expires))
                .build();
    }


}
