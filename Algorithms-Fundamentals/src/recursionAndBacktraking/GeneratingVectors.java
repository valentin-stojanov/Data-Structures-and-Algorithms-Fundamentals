package recursionAndBacktraking;

import java.util.Scanner;

public class GeneratingVectors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        Integer[] vector = new Integer[size];

        fillVector(vector, 0);
    }

    private static void fillVector(Integer[] vector, int index) {
        if (index == vector.length) {
            print(vector);
            return;
        }

        for (int i = 0; i <= 1; i++) {
            vector[index] = i;
            fillVector(vector, index + 1);
        }
    }

    private static void print(Integer[] vector) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i]);
        }
        System.out.println();
    }
}
