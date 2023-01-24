import implementations.ArrayList;
import implementations.Queue;
import implementations.SinglyLinkedList;
import implementations.Stack;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        SinglyLinkedList<String> str = new SinglyLinkedList<>();

        str.addLast("0");
        str.addLast("1");
        str.addLast("2");
        str.addLast("3");
        str.addLast("4");

        for (String s : str) {
            System.out.println(s);
        }
    }
}
