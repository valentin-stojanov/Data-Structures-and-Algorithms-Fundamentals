import example.Order;
import example.OrderPriorityComparator;
import example.Priority;
import implementations.PriorityQueue;
//import java.util.PriorityQueue;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Order order1 = new Order(1, Priority.HIGH);
        Order order2 = new Order(2, Priority.LOW);
        Order order3 = new Order(3, Priority.HIGH);
        Order order4 = new Order(4, Priority.MEDIUM);
        Order order5 = new Order(5, Priority.MEDIUM);
        Order order6 = new Order(6, Priority.LOW);
        Order order7 = new Order(7, Priority.MEDIUM);
        Order order8 = new Order(8, Priority.HIGH);
        Order order9 = new Order(9, Priority.LOW);

        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5, order6, order7, order8, order9);

        PriorityQueue<Order> queue = new PriorityQueue<>();
//        PriorityQueue<Order> queue = new PriorityQueue<>(new OrderPriorityComparator());

        for (Order order : orders) {
            queue.offer(order);
        }
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            System.out.println(queue.poll());
        }
    }
}
