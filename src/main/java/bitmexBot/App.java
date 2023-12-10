package bitmexBot;

import bitmexBot.model.Order;
import bitmexBot.model.OrderRequest;
import bitmexBot.model.OrderType;
import bitmexBot.model.Symbol;
import bitmexBot.service.BitmexClient;
import bitmexBot.service.BitmexClientFactory;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class App {


    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        Order order = Order.builder()
                .orderQty(100.)
                .orderType(OrderType.LMT)
                .isBuy(true)
                .symbol(Symbol.XBTUSD)
                .price(30000.)
               .build();
        String apiKey = "U81zqKXjVZriH4aCqlN4Ypxa";
        String apiSecret = "X2zz-IN6pngk9DTrxGCoVnMzZpzfx9ZBoJVtf5Yr9CPxf7yu";
        BitmexClient bitmexClient = BitmexClientFactory.newTestNetBitmexClient(apiKey, apiSecret);
        bitmexClient.sendOrder(order);
    }
}
