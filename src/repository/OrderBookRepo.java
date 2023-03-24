package repository;

import model.Order;

import java.util.LinkedList;

public class OrderBookRepo {
    private static OrderBookRepo instance;
    private LinkedList<Order> orders = new LinkedList<>();

    private OrderBookRepo(){}

    public static OrderBookRepo getInstance() {
        if (instance == null) {
            instance = new OrderBookRepo();
        }

        return instance;
    }

    public synchronized boolean addOrder(Order order) {
        Order existingOrder = checkIfOrderExist(order);
        boolean isOrderAddedOrModified = false;
        if (existingOrder != null) {
            // order has been modified
            orders.addLast(existingOrder);
            deleteOrder(order);
            isOrderAddedOrModified = true;
        } else {
            isOrderAddedOrModified = orders.add(order);
        }

        return isOrderAddedOrModified;
    }

    public boolean deleteOrder(Order order) {
        return orders.remove(order);
    }

    public LinkedList<Order> getAllOrders() {
        return this.orders;
    }

    private Order checkIfOrderExist(Order order) {
        return getAllOrders()
                .stream()
                .filter(olderOrder -> olderOrder.getId().equals(order.getId()))
                .findFirst()
                .orElse(null);
    }
}
