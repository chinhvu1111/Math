package E1_daily;

public class E322_WaysToMakeAFairArray {

    public static int waysToMakeFair(int[] nums) {
        return 1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer array nums.
        //- You can choose exactly one index (0-indexed) and remove the element.
        //- Notice that the index of the elements may change after the removal.
        //
        //- For example, if nums = [6,1,7,4,1]:
        //  + Choosing to remove index 1 results in nums = [6,7,4,1].
        //  + Choosing to remove index 2 results in nums = [6,1,4,1].
        //  + Choosing to remove index 4 results in nums = [6,1,7,4].
        //- (An array is fair) if the sum of the odd-indexed values equals the sum of the even-indexed values.
        //* Return the number of indices that you could choose such that after the removal, nums is fair.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^4
        //  + nums.length <= 10^5 ==> Time: O(n*k)
        //
        //* Brainstorm:
        //
        //Example 1:
        //
        //Input: nums = [2,1,6,4]
        //Output: 1
        //nums = [2,(1),6,4]
        //=> [2,6,4]
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
    }
}
