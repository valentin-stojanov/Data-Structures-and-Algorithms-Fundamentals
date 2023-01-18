package recursionAndBacktraking;

import java.util.Scanner;

public class RecursiveDrawing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();

        drawFigure(size);
    }

    private static void drawFigure(int size) {
        if (size <= 0) {
            return;
        }
        printLineOfCharactersWithGivenSize('*', size);
        //The code above is executed before recursion
        drawFigure(size - 1);
        //The code below is executed after recursion

        printLineOfCharactersWithGivenSize('#', size);
    }

    private static void printLineOfCharactersWithGivenSize(char character, int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(character);
        }
        System.out.println();
    }

}
