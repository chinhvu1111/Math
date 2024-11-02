package E1_weekly;

public class E18_NumberOfGreatPartitions_hard {

    public static int countPartitions(int[] nums, int k) {
        return 1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an array nums) consisting of (positive integers) and (an integer k).
        //- Partition the array into two (ordered groups) such that (each element) is in ("exactly one" group).
        //- A partition is called ("great")
        //  + if (the sum of elements of (each group)) is (greater than or equal) to k.
        //* Return (the number of distinct great partitions).
        //- Since the answer may be too large, return it modulo 10^9 + 7.
        //- Two partitions are considered (distinct)
        //  + if some element nums[i] is in different groups in (the two partitions).
        //
        //** Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length, k <= 1000
        //1 <= nums[i] <= 10^9
        //  + N không quá lớn ==> O(n^2) được
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: nums = [1,2,3,4], k = 4
        //Output: 6
        //Explanation: The great partitions are: ([1,2,3], [4]), ([1,3], [2,4]), ([1,4], [2,3]), ([2,3], [1,4]), ([2,4], [1,3]) and ([4], [1,2,3]).
        //  + Mỗi partition is ordered group ==> Nếu cùng 1 collection sort --> nó sẽ trùng nhau
        //  + Nhưng (p1,p2) và (p2,p1) là 2 cases
        //- Sub problem:
        //- Find the number of collections có sum of number >=k trước
        //- Nó liên quan đến việc element exists at only one partition
        //
        //  + Xét đến vị trí (index=i):
        //      + val = nums[i]
        //      + Nó có thể kết hợp với:
        //          + dp[k-val]
        //          + dp[k-val]
        //
    }
}
