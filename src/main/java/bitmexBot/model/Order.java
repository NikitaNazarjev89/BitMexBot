package bitmexBot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

  private String orderId;

  private Symbol symbol;
  private boolean isBuy;
  private Double orderQty;
  private Double price;
  private Double stopPx;
  private OrderStatus orderStatus;
  private OrderType orderType;
}
