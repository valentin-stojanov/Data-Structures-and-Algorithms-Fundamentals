import implementations.DoublyLinkedList;
import implementations.Queue;

public class Main {
    public static void main(String[] args) {

        DoublyLinkedList<Integer> numbers = new DoublyLinkedList<>();

        for (int i = 0; i < 5; i++) {
            if (i % 2 != 0){
                numbers.addFirst(i);
            }else {
                numbers.addLast(i);
            }
        }

        System.out.println(numbers.removeLast());
    }
}
