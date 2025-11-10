package E1_daily;

import java.util.Arrays;

public class E295_MinimumMovesToEqualArrayElements {

    public static int minMoves(int[] nums) {
        int n=nums.length;
//        Arrays.sort(nums);
        int min=Integer.MAX_VALUE;
        int rs=0;
        for (int i = 0; i < n; i++) {
//            rs+=Math.abs(nums[i]-min);
            rs+=nums[i];
            min=Math.min(min, nums[i]);
        }
//        int min=Arrays.stream(nums).min().getAsInt();
//        int rs=0;
//        for (int i = 0; i < n; i++) {
////            rs+=Math.abs(nums[i]-min);
//            rs+=nums[i]-min;
//        }
        return rs-min*n;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an integer array nums of size n,
        //* return (the minimum number of moves) required to make (all array elements equal).
        //In one move, you can (increment) (n - 1 elements) of the array by 1.
        //
        //Example 1:
        //
        //Input: nums = [1,2,3]
        //Output: 3
        //Explanation: Only three moves are needed (remember each move increments two elements):
        //[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == nums.length
        //1 <= nums.length <= 105
        //-109 <= nums[i] <= 109
        //The answer is guaranteed to fit in a 32-bit integer.
        //
        //* Brainstorm:
        //- Each time, we can increment (n-1) elements
        //  ==> We should increment (the smallest to biggest value)
        //- Increment (n-1) elements
        //  ==> Decrease 1 elements
        //Ex:
        //nums = {1,2,3};
        //<=>
        //nums = {1,2,2};
        //<=>
        //nums = {1,2,2};
        //<=>
        //nums = {1,2,1};
        //<=>
        //nums = {1,1,1};
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        int[] nums = {1,2,3};
        System.out.println(minMoves(nums));
    }
}
