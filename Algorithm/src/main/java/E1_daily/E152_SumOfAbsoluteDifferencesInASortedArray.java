package E1_daily;

public class E152_SumOfAbsoluteDifferencesInASortedArray {

    public static int[] getSumAbsoluteDifferences(int[] nums) {
        int sum=0;
        int n=nums.length;

        for(int i=0;i<n;i++){
            sum+=nums[i];
        }
        int[] rs=new int[n];
        int curSum=0;
        for(int i=0;i<n;i++){
            rs[i]=nums[i]*(i+1)+sum-2*curSum-nums[i]*(n-i+1);
            curSum+=nums[i];
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given an integer array nums sorted in non-decreasing order.
        //- Build and return an integer array result with the same length as nums such that
        // result[i] is equal to the summation of absolute differences between nums[i] and all the other elements in the array.
        //In other words,
        //* result[i] is equal to sum(|nums[i]-nums[j]|) where 0 <= j < nums.length and j != i (0-indexed).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //2 <= nums.length <= 10^5
        //1 <= nums[i] <= nums[i + 1] <= 10^4
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: nums = [2,3,5]
        //Output: [4,3,5]
        //Explanation: Assuming the arrays are 0-indexed, then
        //result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
        //result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
        //result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.
        //
        //- For each ith:
        //  + result[i] = (nums[i]*(i+1) - prefixSum[i]) + sum - prefixSum[i] - nums[i]*(n-i+1)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        int[] nums = {2,3,5};
        int[] rs= getSumAbsoluteDifferences(nums);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        //
        //#Reference:
        //1222. Queens That Can Attack the King
        //2502. Design Memory Allocator
        //976. Largest Perimeter Triangle
    }
}
