package contest;

public class E250_LongestSubsequenceWithDecreasingAdjacentDifference {

    public static int longestSubsequence(int[] nums) {
        return 1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an array of integers nums).
        //- Your task is to find the (length of the longest subsequence seq) of nums,
        // such that (the absolute differences) between (consecutive elements) form (a non-increasing sequence of integers).
        //- In other words, for a subsequence seq0, seq1, seq2, ..., seqm of nums, |seq1 - seq0| >= |seq2 - seq1| >= ... >= |seqm - seqm - 1|.
        //* Return (the length of such a subsequence).
        //
        //- A subsequence is an non-empty array that can be derived from another array by deleting some or no elements
        // without changing the order of the remaining elements.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //2 <= nums.length <= 10^4
        //1 <= nums[i] <= 300
        //  + length<=10^4 ==> Time: O(n*k)
        //
        //- Brainstorm
        //Ex: 10,20,10,19,10,20
        //0,10,10,9,8,10
        //- Dynamic programming:
        //  + dp[i] is max length at (index=i)
        //
        //- Find the subsequence first
        //
    }
}
