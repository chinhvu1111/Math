package E1_daily;

import java.util.*;

public class E191_MaximumBeautyOfAnArrayAfterApplyingOperation {


    public static int maximumBeauty(int[] nums, int k) {
        int n=nums.length;
        List<int[]> ranges=new ArrayList<>();
        int rs=0;

        for(int i=0;i<n;i++){
            ranges.add(new int[]{nums[i]-k, nums[i]+k});
        }
        ranges.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int j=0;
        for(int i=0;i<n;i++){
            int y=ranges.get(i)[1];
            while(j<n&&y>=ranges.get(j)[0]){
                j++;
            }
            rs=Math.max(rs, j-i);
        }
        //Ex:
        //[1---3]
        //  [2-----------6]
        //       [4---5]
        //
        //Ex:
        //4,6,1,2
        //[-1----3]
        //  [0-----4]
        //      [2------6]
        //          [4-------8]
        return rs;
    }

    public static int maximumBeautyOptimization(int[] nums, int k) {
        int n=nums.length;
        Arrays.sort(nums);
        int j=0;
        int rs=0;

        for(int i=0;i<n;i++){
            int y=nums[i]+k;
            while(j<n&&y>=nums[j]-k){
                j++;
            }
            rs=Math.max(rs, j-i);
        }
        //Ex:
        //[1---3]
        //  [2-----------6]
        //       [4---5]
        //
        //Ex:
        //4,6,1,2
        //[-1----3]
        //  [0-----4]
        //      [2------6]
        //          [4-------8]
        return rs;
    }

    public static int maximumBeautyBinarySearch(int[] nums, int k) {
        Arrays.sort(nums);
        int maxBeauty = 0;

        for (int i = 0; i < nums.length; i++) {
            // Find the farthest index where the value is within the range [nums[i], nums[i] + 2*k]
            int upperBound = findUpperBound(nums, nums[i] + 2 * k);
            // Update the maximum beauty based on the current range
            maxBeauty = Math.max(maxBeauty, upperBound - i + 1);
        }
        return maxBeauty;
    }

    // Helper function to find the largest index where arr[index] <= val
    private static int findUpperBound(int[] arr, int val) {
        int low = 0, high = arr.length - 1, result = 0;

        // Perform binary search to find the upper bound
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= val) {
                result = mid; // Update the result and move to the right half
                low = mid + 1;
            } else {
                high = mid - 1; // Move to the left half
            }
        }
        return result;
    }

    public static int maximumBeautySweepline(int[] nums, int k) {
        // If there's only one element, the maximum beauty is 1
        if (nums.length == 1) return 1;

        int maxBeauty = 0;
        int maxValue = 0;

        // Find the maximum value in the array
        for (int num : nums) maxValue = Math.max(maxValue, num);

        // Create an array to keep track of the count changes
        int[] count = new int[maxValue + 1];

        // Update the count array for each value's range [val - k, val + k]
        for (int num : nums) {
            count[Math.max(num - k, 0)]++; // Increment at the start of the range
            count[Math.min(num + k + 1, maxValue)]--; // Decrement after the range
        }

        int currentSum = 0; // Tracks the running sum of counts
        // Calculate the prefix sum and find the maximum value
        for (int val : count) {
            currentSum += val;
            maxBeauty = Math.max(maxBeauty, currentSum);
        }

        return maxBeauty;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed array nums) and ("a non-negative" integer k).
        //- In one operation, you can do the following:
        //  + Choose an index i that (hasn't been chosen before) from the range [0, nums.length - 1].
        //  + Replace nums[i] with any integer from the range [nums[i] - k, nums[i] + k].
        //      + any interget between range [a,b]
        //- The beauty of the array is the length of (the longest subsequence) consisting of (equal elements).
        //* Return (the maximum possible beauty) of the array nums after applying the operation (any number of times).
        //- Note that you can apply the operation to (each index) (only once).
        //- (A subsequence of an array) is a new array generated from (the original array) by deleting some elements (possibly none)
        // without changing (the order of the remaining elements).
        //
        //Example 1:
        //
        //Input: nums = [4,6,1,2], k = 2
        //Output: 3
        //Explanation: In this example, we apply the following operations:
        //- Choose index 1, replace it with 4 (from range [4,8]), nums = [4,4,1,2].
        //- Choose index 3, replace it with 4 (from range [0,4]), nums = [4,4,1,4].
        //After the applied operations, the beauty of the array nums is 3 (subsequence consisting of indices 0, 1, and 3).
        //It can be proven that 3 is the maximum possible length we can achieve.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //0 <= nums[i], k <= 10^5
        //  + length <= 10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //- This problem is same as the (E189_FindLongestSpecialSubstringThatOccursThriceI_classic)
        //* Find the max substring following the rule:
        //- Classic problem can solved:
        //  + Linear
        //  + Binary search
        //
        //1.1, Optimization
        //- We don't need to create new array list
        //  ==> Sort nums + check ==> Good
        //- [nums[i]-k, nums[i]+k]
        //==> Move to right k units
        //<=> [nums[i], nums[i]+2*k]
        //
        //1.2, Complexity
        //- Space: O(log(n)+n)
        //- Time: O(n*log(n))
        //
        //2. Binary search
        //2.1,
        //- Just loop all of indices:
        //  + Find the (upper bound) of this index in the right
        //
        //3. Sweepline
        //
        int[] nums = {4,6,1,2};
        int k = 2;
        System.out.println(maximumBeauty(nums, k));
        System.out.println(maximumBeautyOptimization(nums, k));
        System.out.println(maximumBeautyBinarySearch(nums, k));
        System.out.println(maximumBeautySweepline(nums, k));
        //
        //#Reference:
        //822. Card Flipping Game
        //1982. Find Array Given Subset Sums
        //3004. Maximum Subtree of the Same Color
    }
}
