public class SearchingAlgorithms {
    public static void main(String[] args) {

        int[] arr = {13, 2, 34, 73, 24, 86};
//        int[] arr1 = {};
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};

//        System.out.println(indexOf(arr, 73));
        int index = binarySearch(arr1, 10);
        System.out.println(index);
    }

    private static int binarySearch(int[] arr, int number) {
        int startIndex = 0;
        int endIndex = arr.length - 1;
        int mid = (endIndex + startIndex) / 2;

        while (startIndex <= endIndex){

            if (arr[mid] == number){
                return mid;
            } else if (number > arr[mid]) {
                startIndex = mid + 1;
                mid = (endIndex + startIndex) / 2;
            } else {
                endIndex = mid - 1;
                mid = (endIndex + startIndex) / 2;
            }
        }
        return mid;
    }

    //    Linear search
    private static int indexOf(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }
}
