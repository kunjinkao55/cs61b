import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        int[] arr = {5, 3, 8, 4, 2, 7, 1, 6};
        int[] result = MergeSort.mergeSort(arr);
        System.out.println(Arrays.toString(result));
    }
}