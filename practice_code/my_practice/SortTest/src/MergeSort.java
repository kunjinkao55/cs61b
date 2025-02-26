
import java.util.*;
public class MergeSort {
    public static int[] mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        // 将数组分为两部分
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        // 递归排序左右两部分
        left = mergeSort(left);
        right = mergeSort(right);

        // 合并排序后的两部分
        return mergeSortedArrays(left, right);
    }

    public static int[] mergeSortedArrays(int a[], int b[]) {
        int ans[] = new int[a.length + b.length];

        int pa = 0;
        int pb = 0;
        int pans = 0;
        while (pa < a.length && pb < b.length) {
            if (a[pa] < b[pb]) {
                ans[pans] = a[pa];
                pa += 1;
            } else {
                ans[pans] = b[pb];
                pb += 1;
            }
            pans += 1;
        }
        while (pa < a.length) {
            ans[pans] = a[pa];
            pans += 1;
            pa += 1;
        }
        while (pb < b.length) {
            ans[pans] = b[pb];
            pans += 1;
            pb += 1;
        }
        return ans;
    }
}