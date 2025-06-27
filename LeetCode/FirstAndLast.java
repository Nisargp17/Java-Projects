package Projects.LeetCode;

import java.util.Arrays;

public class FirstAndLast {
    public static void main(String[] args) {
        int[] arr = { 1, 1, 2, 3, 4, 4, 5, 6 };
        System.out.println(Arrays.toString(InnerFirstAndLast.findElement(arr, 5)));
    }
}

class InnerFirstAndLast {
    static int[] findElement(int[] arr, int val) {
        int first = findIndex(arr, val, true);
        int last = findIndex(arr, val, false);
        System.out.println("Value: " + val);
        return new int[] { first, last };
    }

    static int findIndex(int[] arr, int val, boolean isFirst) {
        int si = 0, ei = arr.length - 1;
        int index = -1;
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            if (arr[mid] == val) {
                index = mid;
                if (isFirst) {
                    ei = mid - 1;
                } else {
                    si = mid + 1;
                }
            } else if (arr[mid] < val) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }

        }
        return index;
    }
}
