import implementations.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");
        strings.add("e");

//        String remove = strings.remove(0);

//        strings.add(3, "f");
        System.out.println(strings.indexOf("d"));

    }
}
