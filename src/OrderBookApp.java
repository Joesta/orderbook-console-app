import model.Order;
import repository.OrderBookRepo;
import service.OrderBookImp;
import java.util.Scanner;

public class OrderBookApp {
    private static final OrderBookRepo repo = OrderBookRepo.getInstance();
    private static final OrderBookImp orderBookImp = new OrderBookImp();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            displayOptions();
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    addOrder();
                    break;
                case 2:
                    deleteOrder();
                    break;
                case 3:
                    modifyOrder();
                    break;
                case 4:
                    viewOrders();
                    break;
                default:
                    System.out.println("Thank you for using BookOrdering Application. Hope to see you again :-)!");
                    break;
            }

        } while (option != 5);

    }

    private static void displayOptions() {
        System.out.println(
                "\nnWelcome to book order app\n" +
                "Please choose from the options below\n" +
                "1. Add order\n" +
                "2. Delete an order\n" +
                "3. Modify an order\n" +
                "4. View all available orders\n" +
                "5. To stop\n"
        );
    }

    private static void addOrder() {
        // side ,quantity,price
        System.out.println("\nPlease enter bid type [ Buy or Sell ] : ");
        Scanner input = new Scanner(System.in);
        String bidType = input.nextLine();
        System.out.println("Please enter order quantity: ");
        int quantity = input.nextInt();
        System.out.println("Pleas enter order price: ");
        double price = input.nextFloat();

        Order order = new Order(bidType, quantity, price);
        boolean isOrderAdded = orderBookImp.addOrder(order);
        if (isOrderAdded) {
            System.out.println("\norder has been added");
        } else {
            System.out.println("\nFailed to add the order");
        }
    }

    private static void deleteOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease enter the orderId: ");
        String orderId = scanner.nextLine();

        boolean isOrderDeleted = orderBookImp.deleteOrder(orderId);
        if (isOrderDeleted) {
            System.out.println("\norder has been deleted");
        } else {
            System.out.println("\nFailed to delete order " + orderId);
        }
    }

    private static void modifyOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease enter the orderId: ");
        String orderId = scanner.nextLine();
        System.out.println("Please enter quantity for the order: ");
        int quantity = scanner.nextInt();

        Order order = orderBookImp.modifyOrder(orderId, quantity);
        if (order != null) {
            System.out.println("Order No# " + order.getId() + " has been modified");
        } else {
            System.out.println("Order No# " + orderId + " was either not found or failed to failed to modify");
        }
    }

    private static void viewOrders() {
        repo.getAllOrders().forEach(
                order -> {
                    System.out.println(order.toString());
                }
        );
    }
}
