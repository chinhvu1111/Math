package E1_daily;

import java.util.Arrays;

public class E276_MinimumMovesToEqualArrayElementsII_classic {

    public static int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int len=nums.length;
//        System.out.println(list);
        int rs=0;
        int pilar = nums[len/2];

        for (int j : nums) {
            rs += Math.abs((pilar - j));
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an integer array nums of size n,
        //* Return the minimum number of moves required to make all array elements equal.
        //In one move, you can increment or decrement an element of the array by 1.
        //Test cases are designed so that the answer will fit in a 32-bit integer.
        //
        //** Idea
        //1. Median
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == nums.length
        //1 <= nums.length <= 10^5
        //-10^9 <= nums[i] <= 10^9
        //  + Nums[i]<=val ==> Long
        //
        //* Brainstorm:
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        int[] nums = {1,10,2,9};
        System.out.println(minMoves2(nums));
    }
}
