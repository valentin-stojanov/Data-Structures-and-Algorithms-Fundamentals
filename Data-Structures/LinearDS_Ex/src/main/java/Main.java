import implementations.ArrayDeque;
import implementations.DoublyLinkedList;

import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(1);
        deque.add(2);
        deque.add(3);
        deque.add(4);
        deque.add(5);
        deque.add(6);

        deque.remove(4);
        deque.trimToSize();
        System.out.println();

    }
}
