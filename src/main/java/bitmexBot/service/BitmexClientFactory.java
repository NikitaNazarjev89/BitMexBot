package bitmexBot.service;

import bitmexBot.util.Endpoint;

import java.net.http.HttpClient;

public class BitmexClientFactory {

    public static BitmexClient newTestNetBitmexClient(String apiKey, String apiSecret){

        return new BitmexClient(Endpoint.BASE_TEST_URL ,apiSecret, apiKey);
    }
//    public BitmexClient newRealNetBitmexClient(String apiKey, String apiSecretKey){
//
//        return new BitmexClient(apiKey, Endpoint.BASE_URL, apiSecretKey, true);
//    }
}
