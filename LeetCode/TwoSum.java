package Projects.LeetCode;

import java.util.*;

public class TwoSum {

    public static void main(String[] args) {
        Scanner x = new Scanner(System.in);
        System.out.println("Enter size of array");
        int n = x.nextInt();

        int[] nums = new int[n];
        System.out.println("Enter Element of array");
        for (int i = 0; i < nums.length; i++) {
            nums[i] = x.nextInt();
        }
        System.out.println("Enter Target Value ");
        int target = x.nextInt();

        int[] result = twoSum(nums, target);

        if (result != null) {
            System.out.println("Indices: " + result[0] + ", " + result[1]);
            System.out.println("Elements: " + nums[result[0]] + ", " + nums[result[1]]);
        } else {
            System.out.println("No solution found.");
        }
        x.close();
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                int[] result = { map.get(complement), i };
                return result;
            }

            map.put(nums[i], i);
        }

        return null;
    }
}
