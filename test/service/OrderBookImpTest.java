package service;

import model.Order;
import model.OrderBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.OrderBookRepo;

import java.util.LinkedList;
import java.util.List;

public class OrderBookImpTest {

    private OrderBookImp orderBookImp;
    private Order order;
    private LinkedList<Order> orders;

    @Before
    public void setUp() throws Exception {
        System.out.println("Entering method: setup()");
        orderBookImp = new OrderBookImp();
        order = OrderBuilder.buildOrder(1);
        orders = OrderBuilder.buildOrders(5);
        OrderBookRepo.getInstance()
                .getAllOrders()
                .addAll(orders);
    }

    public void tearDown() throws Exception {
        orderBookImp = null;
        order = null;
        orders.clear();
        OrderBookRepo.getInstance().getAllOrders().clear();
    }

    @Test
    public void addOrder() {
        System.out.println("adding an order...)");
        boolean isOrderAdded = orderBookImp.addOrder(order);

        Assert.assertTrue(String.format("Order has been added? = [ %s ]", isOrderAdded), isOrderAdded);
        List<Order> allOrders = OrderBookRepo.getInstance().getAllOrders();
        Assert.assertNotNull(String.format("Number of orders available after adding an order = [ %s ]", allOrders.size() ), allOrders);
    }

    @Test
    public void deleteOrder() {
        System.out.println("deleting order by id=" + order.getId());
        boolean isOrderDeleted = orderBookImp.deleteOrder(order.getId());
        System.out.println("Order number # " + order.getId() + " has been deleted? " + isOrderDeleted);
        Assert.assertTrue(String.format("Order has been deleted? = [ %s ]", isOrderDeleted), isOrderDeleted);
    }

    @Test
    public void modifyOrder() {
        System.out.println("modifying order by quantity ");
        System.out.println("existing order to modify " + orders.get(2));
        Order existingOrder = orders.get(2);
        int existingOrderQuantity = existingOrder.getQuantity();
        int newQuantity = 10;

        Order modifiedOrder = orderBookImp.modifyOrder(existingOrder.getId(), newQuantity);
        System.out.println("modified order " + modifiedOrder.toString());
        Assert.assertNotNull(String.format("Number of orders available after adding an order = [ %s ]", modifiedOrder ), modifiedOrder);
        Assert.assertNotEquals(existingOrderQuantity, modifiedOrder.getQuantity());

        List<Order> allOrders = OrderBookRepo.getInstance().getAllOrders();
        System.out.println("Available orders: " + allOrders.size());
    }
}