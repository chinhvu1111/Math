package interviews;

import java.util.Arrays;

public class E277_3SumClosest {

    public static int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) {
            return 0;
        }
        int n = nums.length - 1;
        Arrays.sort(nums);
        int rsSum = 0;
        int rsLength = Integer.MAX_VALUE;

        for (int i = 0; i < n - 1; i++) {
            int j = i + 1;
            int k = nums.length - 1;

            //a+b+c=target
            while (j < k) {
                int currentSum = nums[i] + nums[k];
                if(currentSum + nums[j] - target==0){
                    return target;
                }
                if (rsLength > Math.abs(currentSum + nums[j] - target)) {
                    rsSum = currentSum + nums[j];
                    rsLength = Math.abs(currentSum + nums[j] - target);
//                    System.out.printf("%s(%s) %s(%s) %s(%s) %s\n", i, nums[i], j, nums[j], k, nums[k], rsSum);
                }

                if (currentSum > target - nums[j]) {
                    do {
                        k--;
                    } while (j < k && nums[k] == nums[k + 1]);
                } else if (currentSum < target - nums[j]) {
                    do {
                        j++;
                    } while (j < k && nums[j] == nums[j - 1]);
                } else if (nums[j] > 0) {
                    do {
                        k--;
                    } while (j < k && nums[k] == nums[k + 1]);
                } else {
                    do {
                        j++;
                    } while (j < k && nums[j] == nums[j - 1]);
                }
            }

        }
        return rsSum;
    }

    public static int threeSumClosestRefactor(int[] nums, int target) {
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum  = nums[i] + nums[left] + nums[right];

                if (Math.abs(target - sum) < Math.abs(diff)) {
                    diff = target - sum;
                }

                if (sum > target) {
                    right--;
                }
                else {
                    left++;
                }
            }
        }
        return (target-diff);
    }

    public static void main(String[] args) {
//       int[] nums = {-1,2,1,-4};
//       int target = 1;
        int[] nums = {1, 1, 1, 1};
        int target = 0;
        System.out.println(threeSumClosest(nums, target));
        System.out.println(threeSumClosestRefactor(nums, target));
        //#Reference:
        //17. Letter Combinations of a Phone Number
    }
}
