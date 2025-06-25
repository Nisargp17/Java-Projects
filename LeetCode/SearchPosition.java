package Projects.LeetCode;

public class SearchPosition {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        System.out.println(InnerSearchPosition.findIndex(arr, 2));

    }
}

class InnerSearchPosition {
    static int findIndex(int[] arr, int val) {
        int si = 0, ei = arr.length - 1;
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            if (arr[mid] == val) {
                return mid;
            } else if (arr[mid] < val) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }

        }
        return si;
    }

}