package contest;

import java.util.HashMap;
import java.util.Map;

public class E214_SumOfGoodSubsequences {

    public static int sumOfGoodSubsequences(int[] nums) {
        HashMap<Integer, Long> mapSum = new HashMap<>();
        HashMap<Integer, Long> mapCount = new HashMap<>();
        int n = nums.length;
        int mod = 1_000_000_007;
        long rs = 0;

        //0 -> 10^5
        for (int i = 0; i < n; i++) {
            int curVal = nums[i];
            long curSum = curVal;
            long curCount = 1;

            if (mapSum.containsKey(curVal)) {
                curSum = (curSum + mapSum.get(curVal))%mod;
                curCount = (curCount+ mapCount.get(curVal))%mod;
            }
            if (mapSum.containsKey(curVal - 1)) {
                Long addNumber = mapCount.get(curVal - 1);
                curSum = (curSum + (curVal*addNumber)% mod + mapSum.get(curVal - 1)) % mod;
                curCount = (curCount+ mapCount.get(curVal - 1))%mod;
            }
            if (mapSum.containsKey(curVal + 1)) {
                Long addNumber = mapCount.get(curVal + 1);
                curSum = (curSum + (curVal*addNumber)% mod + mapSum.get(curVal + 1)) % mod;
                curCount = (curCount+ mapCount.get(curVal + 1))%mod;
            }
            mapSum.put(curVal, curSum);
            mapCount.put(curVal, curCount);
//            System.out.printf("Index: %s\n",i);
//            System.out.println(mapSum);
//            System.out.println(mapCount);
//            System.out.println();
        }
        for (Map.Entry<Integer, Long> e : mapSum.entrySet()) {
            rs = (rs + e.getValue()) % mod;
        }
        return (int) (rs);
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer array nums.
        //- A good subsequence is defined as a subsequence of nums
        // where (the absolute difference) between (any two consecutive elements) in the subsequence is exactly 1.
        //- A subsequence is an array that can be derived from another array by (deleting some or no elements)
        // without changing the order of the remaining elements.
        //* Return the sum of all possible good subsequences of nums.
        //* Since the answer may be very large, return it modulo 10^9 + 7.
        //- Note that a subsequence of size 1 is considered good by definition.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 10^5
        //0 <= nums[i] <= 10^5
        //  + len<=10^5 ==> Time = O(n)
        //
        //** Brainstorm
        //- Could we use the dynamic programming?
        //- dp[value] present the sum of good subsequence if:
        //  + We choose the value as the value of last element
        //- dp will be Map type
        //- dp will be calculated one by one element in array
        //
        //Example 1:
        //Input: nums = [1,2,1]
        //dp[1] = 1
        //dp[2] = nums[1] = 2
        //  + dp[2]+= nums[1] + dp[2-1] = 2 + 1
        //      dp[2] = 5
        //  sum([1,2],[2]) = 5
        //  + dp[2]+= nums[1] + dp[2+1] = 3 => Not change
        //dp[1]+= 1
        //  dp[1]=2
        //  sum([1],[1],[2,1],[1,2,1])
        //  + How to determine that we need to add 1 multiple times?
        //      + count the number of 2 values ==> Add count
        //  + dp[1]+= 1 + dp[1-1] => Not change
        //  + dp[1]+= 1 + dp[1+1] = 1 + 5
        //      + dp[1] = 2+6 = 8
        //===
        //dp[1]=8
        //dp[2]=6
        //
        //- How to combine?
        //[1,2,1,2] => [3]
        //=> prev sum = 6
        //- dp[3] = 3
        //=> dp[3] = nums[3][single] + (nums[3] + prev sum)[combination] = 9
        //
//        int[] nums = {1, 2, 1};
//        int[] nums = {3,4,5};
        int[] nums = {4,5,5,6};
        //[4],[5],[4,5],[5],[4,5],[6],[5,6],[5,6],[4,5,6],[4,5,6]
        //Rs: 84
        //Expected: 90
        //
        //#Reference:
        //Find the Number of Subsequences With Equal GCD
        //Total Characters in String After Transformations I
        //Find Maximum Removals From Source String
        //Find the Maximum Length of Valid Subsequence II
        //Count the Number of Inversions
        //Find the Maximum Length of a Good Subsequence II
        //Maximum Difference Score in a Grid
        //Taking Maximum Energy From the Mystic Dungeon
        //Maximum Points After Collecting Coins From All Nodes
        //Minimum Increment Operations to Make Array Beautiful
        //Apply Operations to Make Two Strings Equal
        //Maximize the Profit as the Salesman
        //Maximum Earnings From Taxi
        //Maximum Number of Events That Can Be Attended II
        //Maximum Profit in Job Scheduling
        System.out.println(sumOfGoodSubsequences(nums));
    }
}
