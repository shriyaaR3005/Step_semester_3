import java.util.*;

public class PairWithTargetSumUnsortedArray {
    static boolean hasPairWithSum(int[] nums, int target) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(target - num))
                return true;
            set.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        System.out.println(hasPairWithSum(nums, 9));
    }
}