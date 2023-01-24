import implementations.ArrayList;
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

        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");

        for (String s : stack) {
            System.out.println(s);
        }

        System.out.println();

    }
}
