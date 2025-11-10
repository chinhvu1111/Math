package contest;

import java.util.HashSet;

public class E303_MaximumUniqueSubarraySumAfterDeletion {

    public static int maxSum(int[] nums) {
        int sum=0;
        int n=nums.length;
        HashSet<Integer> set=new HashSet<>();
        int maxVal=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            if(nums[i]>0){
                set.add(nums[i]);
            }
            maxVal=Math.max(maxVal, nums[i]);
        }
        if(set.isEmpty()){
            return maxVal;
        }
        for (int e: set){
            sum+=e;
        }
        return sum;
    }

    public static void main(String[] args) {
        //** Requirement
        //
        //Example 1:
        //
        //Input: nums = [1,2,3,4,5]
        //Output: 15
        //Explanation:
        //
        //Select the entire array without deleting any element to obtain the maximum sum.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //
        //
        //- Brainstorm
        //
        //
    }
}
