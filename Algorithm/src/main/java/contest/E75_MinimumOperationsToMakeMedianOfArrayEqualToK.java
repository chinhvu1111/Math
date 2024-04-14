package contest;

import java.util.Arrays;

public class E75_MinimumOperationsToMakeMedianOfArrayEqualToK {

    public static long minOperationsToMakeMedianK(int[] a, int X) {
        // Sorting the array a[]
        Arrays.sort(a);
        long ans = 0;

        // Calculate the size of array
        int n = a.length;

        // Iterate over the array
        for(int i = 0; i < n; i++)
        {

            // For all elements
            // less than median
            if (i < n / 2)
                ans += Math.max(0, a[i] - X);

                // For element equal
                // to median
            else if (i == n / 2)
                ans += Math.abs(X - a[i]);

                // For all elements
                // greater than median
            else
                ans += Math.max(0, X - a[i]);
        }

        // Return the answer
        return ans;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer array nums and a non-negative integer k. In one operation, you can increase or decrease any element by 1.
        //* Return (the minimum number of operations) needed to make (the median of nums) equal to k.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 2 * 10^5
        //1 <= nums[i] <= 10^9
        //1 <= k <= 10^9
        //
        //- Brainstorm
        //Input: nums = [2,5,6,8,5], k = 4
        //Output: 2
        // 2,5,5,6,8
        // ==>
        //2, (4), 4, 6, 8
        //==> 2 vì để cho elements trước < elements sau
        //
        //
    }
}
