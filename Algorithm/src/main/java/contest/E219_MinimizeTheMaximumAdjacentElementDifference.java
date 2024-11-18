package contest;

public class E219_MinimizeTheMaximumAdjacentElementDifference {

    public static int minDifference(int[] nums) {
        return 1;
    }


    public static void main(String[] args) {
        //** Requirement:
        //- You are given (an array of integers nums).
        //  + Some values in nums are (missing) and are (denoted by -1).
        //- You can choose (a pair of positive integers) (x, y) (exactly once)
        //  and replace (each missing element) with (either x or y).
        //
        //- You need to minimize the maximum (absolute difference) between (adjacent elements of nums) (after replacements).
        //* Return (the minimum possible difference).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //2 <= nums.length <= 10^5
        //nums[i] is either -1 or in the range [1, 10^9].
        //  + n <=10^5 ==> Time: O(n) or O(n*log(n))
        //  + range =10^9 ==> Diff is ok
        //
        //- Brainstorm
        //
        //Example 1:
        //
        //Input: nums = [1,2,-1,10,8]
        //Output: 4
        //Explanation:
        //By choosing the pair as (6, 7), nums can be changed to [1, 2, 6, 10, 8].
        //The absolute differences between adjacent elements are:
        //|1 - 2| == 1
        //|2 - 6| == 4
        //|6 - 10| == 4
        //|10 - 8| == 2
        //- We are allow to choose (only one pair)
        //- If we choose pairs such that the absolute difference is not changed
        //  ==> Optimal
        //- If the absolute difference is changed ==> This number need to be minimized
        //
        //- Binary search???
        //- a,b,c
        //  + |a-b| and |b-c|
        //  maxDiff = x
        //  + |a-b|<= max diff
        //      + b<= a +/- max diff
        //  + |c-b|<= max diff
        //      + c<= a +/- max diff
        //
        //Ex:
        //  + b in (-2,10)
        //  + e in (-1,2)
        //==> How to choose the pair of value
        //- Do we need to (choose a pair) or we only need to (check the max diff) is valid?
        //
        //
    }
}
