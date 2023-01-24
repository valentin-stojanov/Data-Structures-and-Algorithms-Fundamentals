import implementations.ArrayList;
import implementations.Queue;
import implementations.Stack;

public class Main {
    public static void main(String[] args) {

//        ArrayList<String> strings = new ArrayList<>();
//
////        for (int i = 0; i < 15; i++) {
////            strings.add(String.valueOf(i));
////        }
////
////        for (int i = 0; i < 10; i++) {
////            strings.remove(strings.size()-1);
////        }
//
//        strings.add( "f");
//        strings.remove(0);
//        System.out.println(strings.indexOf("d"));

        Queue<String> queue = new Queue<>();
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");
        queue.offer("4");
        queue.offer("5");
        queue.offer("6");
        queue.poll();
        queue.poll();
        queue.poll();
        System.out.println();



        System.out.println();

    }
}
