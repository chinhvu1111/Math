package E1_daily;

public class E233_MaximumAlternatingSubsequenceSum_classic {

    public static long maxAlternatingSum(int[] nums) {
        int n=nums.length;
        int maxOdd=nums[0];
        int maxEven=0;

        for(int i=1;i<n;i++){
            maxEven=Math.max(maxEven, maxOdd-nums[i]);
            maxOdd=Math.max(maxOdd, maxEven+nums[i]);
        }

        return Math.max(maxEven, maxOdd);
    }

    public static long maxAlternatingSumRefer(int[] A) {
        long odd = 0, even = 0;
        for (int a: A) {
            even = Math.max(even, odd + a);
            odd = even - a;
        }
        return even;
    }

    public static void main(String[] args) {
        //** Requirement
        //- The alternating sum of a 0-indexed array is defined as the sum of the elements at even
        // indices minus the sum of the elements at odd indices.
        //  + For example, the alternating sum of [4,2,5,3] is (4 + 5) - (2 + 3) = 4.
        //- Given an array nums, return the maximum alternating sum of any subsequence of nums (after reindexing the elements of the subsequence).
        //- A subsequence of an array is a new array generated from the original array by deleting some elements (possibly none)
        // without changing the remaining elements' relative order.
        //  + For example, [2,7,4] is a subsequence of [4,2,3,7,2,1,4] (the underlined elements),
        // while [2,4,2] is not.
        //
        //Example 1:
        //
        //Input: nums = [[4],[2],[5],3]
        //Output: 7
        //Explanation: It is optimal to choose the subsequence [4,2,5] with alternating sum (4 + 5) - 2 = 7.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^5
        //
        //- Brainstorm
        //Ex:
        //Input: nums = [6,2,1,2,4,5]
        //Output: 10
        //+ We can split the nums into smaller nums
        //nums = [6,2,[1],2,4,5]
        //nums = [.....[i].....]
        //- a,b,[c]
        //  + If we add(c) to the previous array
        //      + We will get (new subsequences) from that
        //- nums[i] could be:
        //  + odd index
        //  + even index
        //Ex:
        //nums =    [6,2,[1],2,4,5]
        //dp[i][0]= [6,2,5, 3,7,4 ]
        //dp[i][1]= [0,4,1, 3,-1,2] ==> It is not correct
        //- For the current index=i
        //  + We will choose:
        //      + max (even/odd) sum to calculate
        //Ex:
        //nums =    [6,2,[1],2,4,5]
        //dp[i][0]= [6,2,5, 3,7,4 ]
        //dp[i][1]= [0,4,1, 3,-1,2] ==> It is not correct
        //
        //* Main point:
        //- We need to get max of (odd and even) dp
        //  + For each index=i, we will calculate (the even/odd value) by using the previous max (odd and even) value
        //
        //max odd  =[6,2,5,7,9,10]
        //max even =[0,4,5,4,3,4]
        //==> Max
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        int[] nums ={4,2,5,3};
        System.out.println(maxAlternatingSum(nums));
        System.out.println(maxAlternatingSumRefer(nums));
        //
        //#Reference:
        //2036. Maximum Alternating Subarray Sum
        //2862. Maximum Element-Sum of a Complete Subset of Indices
    }
}
