import java.util.*;

public class TwoSumSystem {
    public static void find(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                System.out.println("Pair: " + nums[i] + " + " + (target - nums[i]));
                return;
            }
            map.put(nums[i], i);
        }
    }

    public static void main(String[] args) {
        int[] arr = {100, 200, 300, 400};
        find(arr, 500);
    }
}