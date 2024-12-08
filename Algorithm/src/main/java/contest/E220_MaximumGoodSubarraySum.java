package contest;

import java.util.HashMap;
import java.util.Map;

public class E220_MaximumGoodSubarraySum {

    public static long maximumSubarraySum(int[] nums, int k) {
        int n= nums.length;
        HashMap<Integer, Long> valToPrefixSum=new HashMap<>();
        long rs=Long.MIN_VALUE;
//        valToPrefixSum.put(0, 0L);
        long sum=0;

        //Ex:
        //nums =    [1,[2],-3,[2],(4)]
        //prefix =  [1, 3, 0, 2, 4]
        //
        for(int i=1;i<=n;i++){
            Long curPrefixSum = valToPrefixSum.getOrDefault(nums[i-1], Long.MAX_VALUE);
            if(curPrefixSum>sum){
                curPrefixSum=sum;
            }
            valToPrefixSum.put(nums[i-1], curPrefixSum);
//            System.out.printf("%s\n", i);
//            System.out.println(valToPrefixSum);
            //Adding after indexing
            sum+=nums[i-1];
            //Use the current sum
            Long oldPrevValSubtraction = valToPrefixSum.getOrDefault(nums[i-1]-k, Long.MAX_VALUE);
            Long oldPrevValAddition = valToPrefixSum.getOrDefault(nums[i-1]+k, Long.MAX_VALUE);
            long minPrevPrefixVal = Math.min(oldPrevValSubtraction, oldPrevValAddition);
            if(minPrevPrefixVal!=Long.MAX_VALUE){
                rs=Math.max(rs, sum-minPrevPrefixVal);
            }
        }
        return rs==Long.MIN_VALUE?0:rs;
    }

    public static long maximumSubarraySumKadaneAlgorithm(int[] nums, int k) {
        Map<Integer, Long> valToMinPrefixSum = new HashMap<>();
        long maxSum = Long.MIN_VALUE, prefixSum = 0;

        for (int i = 0; i < nums.length; ++i) {
            if (valToMinPrefixSum.getOrDefault(nums[i], Long.MAX_VALUE) > prefixSum) {
                valToMinPrefixSum.put(nums[i], prefixSum);
            }
            prefixSum += nums[i];
            if (valToMinPrefixSum.containsKey(nums[i] + k)) {
                maxSum = Math.max(maxSum, prefixSum - valToMinPrefixSum.get(nums[i] + k));
            }
            if (valToMinPrefixSum.containsKey(nums[i] - k)) {
                maxSum = Math.max(maxSum, prefixSum - valToMinPrefixSum.get(nums[i] - k));
            }
        }
        return maxSum == Long.MIN_VALUE ? 0 : maxSum;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given (an array nums of length n) and (a positive integer k).
        //- (A subarray of nums) is called good if (the absolute difference between its first and last element) is (exactly k),
        // in other words,
        //  + the subarray nums[i..j] is good if |nums[i] - nums[j]| == k.
        //* Return (the maximum sum of a good subarray) of nums.
        //  + If there are (no good subarrays), return 0.
        //
        //Example 2:
        //
        //Input: nums = [-1,3,2,4,5], k = 3
        //Output: 11
        //Explanation: The absolute difference between the first and last element must be 3 for a good subarray.
        // All the good subarrays are: [-1,3,2], and [2,4,5].
        // The maximum subarray sum is 11 for the subarray [2,4,5].
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //2 <= nums.length <= 10^5
        //-10^9 <= nums[i] <= 10^9
        //1 <= k <= 10^9
        //
        //- Brainstorm
        //- Return maximum subarray such that (|nums[i]-nums[j]|==k)
        //- Sum[i,j] = prefix[j] - prefix [i-1]
        //
        //- PrefixSum
        //- Check |nums[i]-nums[j]|==1:
        //Ex:
        //nums =    [1,[2],-3,[2],(4)]
        //prefix =  [1, 3, 0, 2, 4]
        //  + Map [val, prefixSum]:
        //      + We want to get (max of prefix sum) having (the same value)
        //- In this problem:
        //  + We calculate the prefixSum value at ith position:
        //      + We add the prefixSum first
        //          + map.put(nums[i-1], sum)
        //      + sum+=nums[i-1]
        //          + We calculate the rs by using sum
        //
//        int[] nums = {-1,3,2,4,5};
//        int k = 3;
//        int[] nums = {-1,-2,-3,-4};
//        int k = 2;
//        int[] nums = {1,5};
//        int k = 2;
        int[] nums = {3,3,2};
        //prefixSum = [3,6,8]
        int k = 1;
        System.out.println(maximumSubarraySum(nums, k));
        //Same idea
        System.out.println(maximumSubarraySumKadaneAlgorithm(nums, k));
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //2824. Count Pairs Whose Sum is Less than Target
        //381. Insert Delete GetRandom O(1) - Duplicates allowed
        //2361. Minimum Costs Using the Train Line
    }
}
