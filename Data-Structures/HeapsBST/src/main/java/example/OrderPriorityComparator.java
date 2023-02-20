package example;

import java.util.Comparator;

public class OrderPriorityComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        int firstPriority = o1.getPriority().getPriorityCode();
        int secondPriority = o2.getPriority().getPriorityCode();
        return firstPriority - secondPriority;
    }
}
