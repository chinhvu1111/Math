package contest;

public class E119_FindSubarrayWithBitwiseANDClosestToK {

    public static int minimumDifference(int[] nums, int k) {
        return 1;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an array nums and an integer k.
        // You need to find a (subarray of nums) such that the (absolute difference) between k and
        // (the bitwise AND) of (the subarray elements) is as small as possible.
        // In other words,
        // - select a subarray nums[l..r] such that |k - (nums[l] AND nums[l + 1] ... AND nums[r])| is minimum.
        //* Return (the minimum possible value) of (the absolute difference).
        //- A subarray is a contiguous non-empty sequence of elements within an array.
        //
        //Example 1:
        //Input: nums = [1,2,4,5], k = 3
        //Output: 1
        //Explanation:
        //The subarray nums[2..3] has AND value 4, which gives the minimum absolute difference |3 - 4| = 1.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^9
        //1 <= k <= 10^9
        //==> Size khá lớn
        //
        //- Brainstorm
        //- Tìm min( absolute (k-AND(nums[i...j] )
        //+ A AND B = C
        //E.g:
        //1011
        //0010
        //0010 ==> Mất bit không reverse được
        //-
        //
    }
}
