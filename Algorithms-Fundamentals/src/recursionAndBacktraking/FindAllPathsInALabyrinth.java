package recursionAndBacktraking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindAllPathsInALabyrinth {
    static List<Character> paths = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
         int rows = Integer.parseInt(scanner.nextLine());
         int cols = Integer.parseInt(scanner.nextLine());

         char [][] labyrinth = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            labyrinth[row] = scanner.nextLine().toCharArray();
        }

         findPath(labyrinth, 0, 0, ' ');

    }

    private static void findPath(char[][] labyrinth, int row, int cow, char direction) {
        if (!isInBounds(labyrinth, row, cow) ||
                isVisited(labyrinth, row, cow) ||
                isWall(labyrinth, row, cow)){
            return;
        }

        paths.add(direction);

        if (isFoundExit(labyrinth,  row, cow)){
            printPath(paths);
//            printLabyrinth(labyrinth);
            paths.remove(paths.size()-1);
            return;
        }


        labyrinth[row][cow] = 'V';

        findPath(labyrinth, row, cow + 1, 'R');
        findPath(labyrinth, row, cow - 1, 'L');
        findPath(labyrinth, row - 1, cow, 'U');
        findPath(labyrinth, row + 1, cow, 'D');

        labyrinth[row][cow] = '-';
        paths.remove(paths.size()-1);

    }

    private static void printPath(List<Character> paths) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < paths.size(); i++) {
            builder.append(paths.get(i));
        }
        System.out.println(builder);
    }

    private static boolean isFoundExit(char[][] labyrinth, int row, int cow) {
        return isEqualToChar(labyrinth, row, cow, 'e');
    }

    private static boolean isVisited(char[][] labyrinth, int row, int cow) {
        return isEqualToChar(labyrinth, row, cow, 'V');
    }

    private static boolean isWall(char[][] labyrinth, int row, int cow) {
        return isEqualToChar(labyrinth, row, cow, '*');
    }

    private static boolean isEqualToChar(char[][] labyrinth, int row, int cow, char character) {
        return labyrinth[row][cow] == character;
    }

    private static boolean isInBounds(char[][] labyrinth, int row, int cow) {
        return (0<= row && row < labyrinth.length) && (0<= cow && cow < labyrinth[row].length);
    }


    static void printLabyrinth(char[][] labyrinth){
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < labyrinth.length; row++) {
            for (int col = 0; col < labyrinth[row].length; col++) {
                builder.append(labyrinth[row][col]);
            }
            builder.append(System.getProperty("line.separator"));
        }
        System.out.println(builder);
    }
}
