package sortingAlgorithms;

import java.util.Random;

public class SortingAlgorithm {
    public static void main(String[] args) {
//                Scanner scanner = new Scanner(System.in);
//
//        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
//                .mapToInt(Integer::parseInt)
//                .toArray();


        Random rand = new Random();
//        `-Xms256m -Xmx25000m` this should be added to the run configuration for  2 000 000 000 elements if using mergeSort
        int[] numbers = new int[10];
//        int[] numbers = new int[10];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(100);
        }



        System.out.println("Before");
        System.out.println(printArray(numbers));

        mergeSort2(numbers, 0, numbers.length - 1);
//        mergeSort(numbers);

        System.out.println("After sorting:");
        System.out.println(printArray(numbers));

    }

    // Using less memory!!!
    private static void mergeSort2(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }

        int mid = (begin + end) / 2;

        mergeSort2(arr, begin, mid);
        mergeSort2(arr, mid + 1, end);

        merge2(arr, begin, mid, end);
    }
    private static void merge2(int[] arr, int begin, int mid, int end) {
        if (mid < 0 || mid >= arr.length || arr[mid] < arr[mid + 1]) {
            return;
        }

        int left = begin;
        int right = mid + 1;
        int[] helper = new int[arr.length];

        for (int i = begin; i <= end; i++) {
            helper[i] = arr[i];
        }

        for (int i = begin; i <= end; i++) {
            if (left > mid) {
                arr[i] = helper[right++];
            } else if (right > end) {
                arr[i] = helper[left++];
            } else if (helper[left] < helper[right]) {
                arr[i] = helper[left++];
            } else {
                arr[i] = helper[right++];
            }
        }
    }

    // n * log2(n) space complexity!!!
    private static void mergeSort(int[] inputArray) {

        int inputLength = inputArray.length;

        if (inputLength < 2) {
            return;
        }

        int midIndex = inputLength / 2;
        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[inputLength - midIndex];

        for (int i = 0; i < leftHalf.length; i++) {
            leftHalf[i] = inputArray[i];
        }

        for (int i = 0; i < rightHalf.length; i++) {
            rightHalf[i] = inputArray[i + leftHalf.length];
        }

        mergeSort(leftHalf);
        mergeSort(rightHalf);
        merge(inputArray, leftHalf, rightHalf);
    }
    private static void merge(int[] inputArray, int[] leftHalf, int[] rightHalf) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftSize && j < rightSize) {
            if (leftHalf[i] <= rightHalf[j]) {
                inputArray[k] = leftHalf[i];
                i++;
            } else {
                inputArray[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        //fill-up remaining elements from left array (if there any) into initial array;
        while (i < leftSize) {
            inputArray[k] = leftHalf[i];
            k++;
            i++;
        }

        //fill-up remaining elements from right array (if there any) into initial array;
        while (j < rightSize) {
            inputArray[k] = rightHalf[j];
            j++;
            k++;
        }

    }

    private static void bubbleSort(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                int bubble = numbers[j];
                if (bubble > numbers[j + 1]) {
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = bubble;
                }
            }
        }
    }

    private static void selectionSort(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            int minElement = numbers[i];
            for (int j = i + 1; j < numbers.length; j++) {
                if (minElement > numbers[j]) {
                    numbers[i] = numbers[j];
                    numbers[j] = minElement;
                    minElement = numbers[i];
                }
            }
        }
    }

    private static String printArray(int[] numbers) {
        StringBuilder builder = new StringBuilder();
        for (int number : numbers) {
            builder
                    .append(number)
                    .append(" ");
        }
        return builder.toString();
    }
}
