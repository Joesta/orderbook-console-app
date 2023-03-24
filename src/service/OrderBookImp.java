package service;

import interfaces.IOrderBook;
import model.Order;
import repository.OrderBookRepo;

public class OrderBookImp implements IOrderBook {
    @Override
    public boolean addOrder(Order order) {
        return OrderBookRepo.getInstance().addOrder(order);
    }

    @Override
    public boolean deleteOrder(String orderId) {
        Order order = getOrderById(orderId);
        return OrderBookRepo.getInstance()
                .deleteOrder(order);
    }

    @Override
    public Order modifyOrder(String orderId, int quantity) {
        Order order = getOrderById(orderId);
        order.setQuantity(quantity);
        addOrder(order);
        return order;
    }

    private Order getOrderById(String orderId) {
        return OrderBookRepo.getInstance()
                .getAllOrders()
                .stream()
                .filter(order -> order.getId().equals(orderId))
                .findAny()
                .orElse(null);
    }
}
