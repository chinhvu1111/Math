package E1_daily;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class E129_MaximalScoreAfterApplyingKOperations {

    public static long maxKelements(int[] nums, int k) {
//        int n=nums.length;
        PriorityQueue<Integer> sortCeil=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        for(int e: nums){
            sortCeil.add(e);
        }
        long rs=0;
        for(int i=0;i<k;i++){
            double curVal = sortCeil.poll();
            rs+=curVal;
            sortCeil.add((int) Math.ceil(curVal / 3));
//            System.out.println(sortCeil);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed integer array nums and an integer k.
        //  + You have (a starting score of 0).
        //- In one operation:
        //  + choose an index (i) such that (0 <= i < nums.length),
        //  + increase (your score) by (nums[i]), and
        //  + replace (nums[i]) with ceil(nums[i] / 3).
        //* Return (the maximum possible score) you can attain after applying (exactly k operations).
        //- The ceiling function ceil(val) is the least integer greater than or equal to val.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length, k <= 10^5
        //1 <= nums[i] <= 10^9
        //
        //- Brainstorm
        //
        //Example 2:
        //
        //Input: nums = [1,10,3,3,3], k = 3
        //Output: 17
        //Explanation: You can do the following operations:
        //Operation 1: Select i = 1, so nums becomes [1,4,3,3,3]. Your score increases by 10.
        //Operation 2: Select i = 1, so nums becomes [1,2,3,3,3]. Your score increases by 4.
        //Operation 3: Select i = 2, so nums becomes [1,1,1,3,3]. Your score increases by 3.
        //The final score is 10 + 4 + 3 = 17.
        //
        //-
        //
        int[] nums = {1,10,3,3,3};
        int k = 3;
        System.out.println(maxKelements(nums, k));
        //#Reference:
        //3004. Maximum Subtree of the Same Color
        //2992. Number of Self-Divisible Permutations
        //1388. Pizza With 3n Slices
        //2672. Number of Adjacent Elements With the Same Color
        //2182. Construct String With Repeat Limit
        //2303. Calculate Amount Paid in Taxes
    }
}
