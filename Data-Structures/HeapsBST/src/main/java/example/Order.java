package example;

public class Order implements Comparable<Order>{
    private int id;
    private Priority priority;

    public Order(int id, Priority priority) {
        this.id = id;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public Order setId(int id) {
        this.id = id;
        return this;
    }

    public Priority getPriority() {
        return priority;
    }

    public Order setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", priority=" + priority.name() +
                '}';
    }

    @Override
    public int compareTo(Order order) {
        return order.getPriority().getPriorityCode() - this.priority.getPriorityCode();
    }
}
