package E1_daily;

import java.util.Arrays;

import static E1_daily.E155_CountTheNumberOfFairPairs_classic.lowerBound;

public class E156_CountOfRangeSum {

    public static int countRangeSum(int[] nums, int lower, int upper) {
        int n= nums.length;
        int[] prefixSum=new int[n];
        int sum=0;

        for (int i = 0; i < n; i++) {
            sum+=nums[i];
            prefixSum[i]=sum;
        }
        Arrays.sort(prefixSum);
        return lowerBound(prefixSum, upper+1)-lowerBound(prefixSum, lower);
    }
    public static int lowerBound(int [] nums, int value){
        int left=0, right=nums.length-1;
        int rs=0;

        while(left<=right){
            //nums =        [1,7,9,2,5]
            //sorted nums = [1,2,5,7,9]
            //sum = 12
            //(1+9)<12
            int sum = nums[right]-nums[left];
            if(sum<value){
                rs+=right-left;
                left++;
            }else{
                right--;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given (an integer array nums) and two integers (lower and upper),
        //* return (the number of range sums) that lie in [lower, upper] inclusive.
        //- Range sum S(i, j) is defined as (the sum of the elements) in nums between indices (i and j inclusive), where i <= j.
        //
        //Example 1:
        //
        //Input: nums = [-2,5,-1], lower = -2, upper = 2
        //Output: 3
        //Explanation: The three ranges are: [0,0], [2,2], and [0,2] and their respective sums are: -2, -1, 2.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 10^5
        //-2^31 <= nums[i] <= 2^31 - 1
        //-10^5 <= lower <= upper <= 10^5
        //The answer is guaranteed to fit in a 32-bit integer.
        //  + Length <= 10^5 => Time: O(n) or O(n*log(n))
        //
        //- Brainstorm
        //
        //
        int[] nums = {-2,5,-1};
        int lower = -2, upper = 2;
        //-2,-1,5
        //- upper=3
        //[-2,-1], [-2],[-1]
        //
        //- We need to keep (the order of elements)
        //
        //
        System.out.println(countRangeSum(nums, lower, upper));
        //
    }
}
