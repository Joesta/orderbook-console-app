package interfaces;

import model.Order;

public interface IOrderBook {
    boolean addOrder(Order order);
    boolean deleteOrder(String orderId);
    Order modifyOrder(String orderId, int quantity);
}
