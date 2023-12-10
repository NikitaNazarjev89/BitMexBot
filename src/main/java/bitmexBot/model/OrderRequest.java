package bitmexBot.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRequest {

   private final String SYMBOL;

   private final String SIDE;

    private final Double ORDER_QTY;

    private final Double  PRICE;

    private final String ORDER_TYPE;

    private final Double STOP_PX;

    public static OrderRequest toRequest(Order order){
         String symbol = order.getSymbol().toString();

         String side = order.isBuy() ? "Buy" : "Sell";

         Double orderQty = order.getOrderQty();

         Double  price = order.getPrice();

         String orderType = getType(order.getOrderType());

         Double stopPx = order.getStopPx();

         return new OrderRequest(symbol,side,orderQty,price, orderType, stopPx);
    }

    private static String getType(OrderType orderType){

        switch (orderType){
            case LMT:
                return "Limit";
            case MKT:
                return  "Market";
            case STP_LMT:
                return "StopLimit";
            case STP_MKT:
                return "StopMarket";
            default: throw new IllegalArgumentException("Unsupported order Type");

        }
    }

}
