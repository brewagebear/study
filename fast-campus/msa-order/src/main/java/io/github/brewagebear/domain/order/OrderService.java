package io.github.brewagebear.domain.order;

public interface OrderService {
    String registerOrder(OrderCommand.RegisterOrder registerOrder);
    void paymentOrder(OrderCommand.PaymentRequest paymentRequest);
    OrderInfo.Main retrieveOrder(String orderToken);
}
