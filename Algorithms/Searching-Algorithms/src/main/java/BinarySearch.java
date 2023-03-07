import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

//        int[] numbers = {1};
//        int[] numbers = {-3, -2, -1, 0, 4, 5, 6, 7, 8, 9, 12, 13, 15, 17};
        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int number = Integer.parseInt(scanner.nextLine());

        int indexOfNumber = findIndexOfNumberInArray(number, numbers);
        System.out.println(indexOfNumber);
    }

//    Should return positive number - index of searched element in array.
//    If returned value == -1 => searched number is smaller than the smallest element in the array or array is empty.
//    If returned value == -2 => searched number is greater than the biggest element in the array.
    public static int findIndexOfNumberInArray(int number, int[] arr) {
        return findIndexOfNumberInArray(number, arr, 0, arr.length - 1);
    }

    private static int findIndexOfNumberInArray(int number, int[] arr, int startIndex, int endIndex) {
//        arr should not be an empty array.
        if (arr.length == 0) {
            return -1;
        }
        int middleIndex = (startIndex + endIndex) / 2;

        if (number == arr[middleIndex]) {
            return middleIndex;
        } else if (number < arr[middleIndex]) {
//            The number is smaller than the numbers in the array.
            if (number < arr[startIndex]) {
                return -1;
            }
//            Search in left half.
            return findIndexOfNumberInArray(number, arr, startIndex, middleIndex - 1);
        } else {
//            The number is greater than the numbers in the array.
            if (number > arr[endIndex]) {
                return -2;
            }
//            Search in right half.
            return findIndexOfNumberInArray(number, arr, middleIndex + 1, endIndex);
        }
    }
}
