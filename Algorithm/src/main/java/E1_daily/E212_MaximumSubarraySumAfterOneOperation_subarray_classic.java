package E1_daily;

import java.util.Arrays;

public class E212_MaximumSubarraySumAfterOneOperation_subarray_classic {

    public static int maxSumAfterOperation(int[] nums) {
        int n=nums.length;
        int[] prefixMax=new int[n];
        int[] suffixMax=new int[n];

        int sum=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefixMax[i]=sum;
            sum=Math.max(sum, 0);
        }
        sum=0;
        for(int i=n-1;i>=0;i--){
            sum+=nums[i];
            suffixMax[i]=sum;
            sum=Math.max(sum, 0);
        }
        int rs=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            int left= i>=1&&prefixMax[i-1]>0?prefixMax[i-1]:0;
            int right = i<n-1&&suffixMax[i+1]>0?suffixMax[i+1]:0;
            rs=Math.max(rs, left+right+nums[i]*nums[i]);
        }
        return rs;
    }

    public static int maxSumAfterOperationDynamicProgramming(int[] nums) {
        int n=nums.length;
        int[][] dp=new int[n][2];
        // int rs=Integer.MIN_VALUE;
        dp[0][0]=nums[0];
        dp[0][1]=nums[0]*nums[0];
        int rs= nums[0]*nums[0];

        for(int i=1;i<n;i++){
            int leftWithoutReplacing = Math.max(dp[i - 1][0], 0);
            dp[i][0]=leftWithoutReplacing+nums[i];
            int leftWithReplacing= Math.max(dp[i-1][1], 0);
            dp[i][1]=Math.max(leftWithReplacing+nums[i], leftWithoutReplacing+nums[i]*nums[i]);
            rs=Math.max(rs, Math.max(dp[i][0], dp[i][1]));
        }
        return rs;
    }

    public static int maxSumAfterOperationDynamicProgrammingSpaceOptimization(int[] nums) {
        int n=nums.length;

        // Initialize variables to store the maximum sums.
        int maxSumWithoutSquare = nums[0];
        int maxSumWithSquare = nums[0] * nums[0];
        int maxSum = maxSumWithSquare;

        for (int index = 1; index < n; index++) {
            // Option 1: Square the current element.
            // Option 2: Add the square of the current element to the previous sum without a square.
            // Option 3: Add the current element to the previous sum with a square.
            maxSumWithSquare = Math.max(
                    Math.max(
                            nums[index] * nums[index],
                            maxSumWithoutSquare + nums[index] * nums[index]
                    ),
                    maxSumWithSquare + nums[index]
            );

            // Option 1: Start a new subarray.
            // Option 2: Continue the previous subarray.
            maxSumWithoutSquare = Math.max(
                    nums[index],
                    maxSumWithoutSquare + nums[index]
            );

            // Update maxSum
            maxSum = Math.max(maxSum, maxSumWithSquare);
        }

        return maxSum;
    }

    public static int maxSumAfterOperationTopDown(int[] nums) {
        int n = nums.length;
        // Initialize a DP table to store results of subproblems.
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1); // Initialize all entries to -1.
        }

        // Create array to pass be reference
        int maxSum[] = new int[1];
        maxSum[0] = 0;

        // Call the recursive helper function to compute the result.
        getMaxSumHelper(0, nums, true, dp, maxSum);
        return maxSum[0];
    }

    private static int getMaxSumHelper(
            int index,
            int[] nums,
            boolean canSquare,
            int[][] dp,
            int[] maxSum
    ) {
        if (index == nums.length) {
            return 0; // Base case: if we reach the end of the array, return 0.
        }

        // If the result is already computed for this state, return it.
        if (dp[index][canSquare ? 1 : 0] != -1) {
            return dp[index][canSquare ? 1 : 0];
        }

        // Case 1: Skip squaring the current element.
        int nextSumWithoutSquare = getMaxSumHelper(
                index + 1,
                nums,
                canSquare,
                dp,
                maxSum
        );
        int maxSumWithoutSquare = nums[index]; // The value itself if we don't square it.
        if (nextSumWithoutSquare > 0) {
            maxSumWithoutSquare += nextSumWithoutSquare; // Accumulate if positive.
        }

        // Case 2: Square the current element if allowed.
        int maxSumWithSquare = 0;
        if (canSquare) {
            maxSumWithSquare = nums[index] * nums[index]; // Square the current element.
            int nextSumWithSquare = getMaxSumHelper(
                    index + 1,
                    nums,
                    false,
                    dp,
                    maxSum
            ); // Don't square further.
            if (nextSumWithSquare > 0) {
                maxSumWithSquare += nextSumWithSquare; // Accumulate if positive.
            }
        }

        // Update the global maxSum if we find a better one.
        maxSum[0] = Math.max(
                maxSum[0],
                Math.max(maxSumWithSquare, maxSumWithoutSquare)
        );

        // Store the result in dp table and return the maximum of the two options.
        dp[index][canSquare ? 1 : 0] = Math.max(
                maxSumWithSquare,
                maxSumWithoutSquare
        );
        return dp[index][canSquare ? 1 : 0];
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an integer array nums).
        //- You must perform (exactly one operation) where you can replace (one element)
        //  + nums[i] with nums[i] * nums[i].
        //* Return (the maximum possible subarray sum) after (exactly one operation).
        //  + The subarray must be non-empty.
        //
        //Example 1:
        //
        //Input: nums = [2,-1,-4,-3]
        //Output: 17
        //Explanation: You can perform the operation on index 2 (0-indexed) to make nums = [2,-1,16,-3].
        //Now, the maximum subarray sum is 2 + -1 + 16 = 17.
        //
        // Idea
        //1.
        //1.0, (Max left, max right)
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //-10^4 <= nums[i] <= 10^4
        //  + length<= 10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //- max sum subarray
        //
        //- Dynamic programming?
        //- i is the index position we are considering
        //
        //Input: nums = [2,-1,-4,-3]
        //
        //Ex:
        //nums = [2,-1,-4,-3,10]
        //- Right -> left:
        //[2,1,-3,-3,10]
        //- Left -> right:
        //[4,2,3,7,10]
        //==> [-4] <=> 16
        //==> 16+10+1 = 27 (true)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //2. Dynamic programming
        //2.0,
        //- For element[i] we have two choices:
        //  + Replace it
        //  + Keep it
        //- If we define:
        //  + dp[i][0] that means replace nums[i] or not that is WRONG, because:
        //      + dp[i][0] = max(dp[j][0], dp[j][1])
        //      ==> dp[i][0] will be used to calculate the next dp[k][1]
        //      ==> WRONG because we only one replacement operation
        //- dp[i][2]:
        //  + dp[i][0]: that's max subarray sum including nums[i] without any squaring any element
        //  + dp[i][1]: that's max subarray sum including nums[i] with squaring only one element
        //- Let's define the formula:
        //  + dp[i][0]= dp[j][0]+nums[i]
        //  + dp[i][1]= max(dp[j][0]+nums[i]*nums[i], dp[j][1]+nums[i])
        //      + Because dp[j][0] could be negative
        //      ==> If dp[j][0] <0 :
        //              + 0 + nums[i]*nums[i]
        //              <> dp[j][0] + nums[i]*nums[i]
        //
        //- Let's take an example:
        //nums = {2,-1,-4,-3, 10}
        //a,b,c
        //- We can replace(a or b or c)
        //  + If we replace(a) ==> b could not replaced
        //nums =   {2,-1,-4,-3, 10}
        //          2 | -1 | -4 | -3 | 10
        //dp[i][0]  2 | 1  | -3 | -3 | 7
        //dp[i][1]  4 | 3  | 17 | 17 | 27
        //
        //* Kinh nghiệm:
        //- Nếu việc chọn nums[i] là 0 or 1 mà:
        //  + nums[i][0] ==> tính theo [j][1]
        //  ==> Từ [k][1] lại tính từ [i][0]
        //  * Mà ta chỉ được sử dụng 1 lần REPLACEMENT
        //  ==> Invalid (1 lần ==> (THE WHOLE ARRAY) until (index=i))
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(n) -> O(1)
        //- Time: O(n) -> O(n)
        //
        int[] nums = {2,-1,-4,-3};
        System.out.println(maxSumAfterOperation(nums));
        System.out.println(maxSumAfterOperationDynamicProgramming(nums));
        System.out.println(maxSumAfterOperationDynamicProgrammingSpaceOptimization(nums));
        System.out.println(maxSumAfterOperationTopDown(nums));
        //
        //#Reference:
        //1743. Restore the Array From Adjacent Pairs
        //321. Create Maximum Number
        //679. 24 Game
        //
        //2599. Make the Prefix Sum Non-negative
        //3042. Count Prefix and Suffix Pairs I
        //1606. Find Servers That Handled Most Number of Requests
        //
        //747. Largest Number At Least Twice of Others
        //2080. Range Frequency Queries
        //1655. Distribute Repeating Integers
    }
}
