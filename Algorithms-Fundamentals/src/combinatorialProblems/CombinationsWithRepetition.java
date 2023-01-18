package combinatorialProblems;

import java.util.Scanner;

public class CombinationsWithRepetition {
    static String[] elements;
    static String[] combinations;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split("\\s+");
        int k = Integer.parseInt(scanner.nextLine());
        combinations = new String[k];

        findCombinations(0, 0);
    }

    private static void findCombinations(int combinationIndex, int elementsIndex) {
        if (combinationIndex == combinations.length) {
            print(combinations);
        } else{
            for (int i = elementsIndex; i < elements.length; i++) {
                combinations[combinationIndex] = elements[i];
                findCombinations(combinationIndex + 1, i);
            }
        }
    }

    private static void print(String[] combinations) {
        System.out.println(String.join(" ", combinations));
    }
}
