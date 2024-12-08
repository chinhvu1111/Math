package contest;

import java.util.HashMap;
import java.util.HashSet;

public class E225_IdentifyTheLargestOutlierInAnArray {

    public static int getLargestOutlier(int[] nums) {
        int n=nums.length;
        int sum=0;
        HashMap<Integer, Integer> mapCount=new HashMap<>();

        for(int i=0;i<n;i++){
            sum+=nums[i];
            mapCount.put(2*nums[i], mapCount.getOrDefault(2*nums[i], 0)+1);
        }
        int rs=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            int remainingSum = sum-nums[i];
            if(nums[i]*2==remainingSum){
                if(mapCount.get(nums[i]*2)>=2){
                    rs=Math.max(rs, nums[i]);
                }
            }else if(mapCount.containsKey(remainingSum)){
                rs=Math.max(rs, nums[i]);
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer array nums.
        // This array contains n elements, where exactly n - 2 elements are (special numbers).
        //- One of the remaining two elements is the sum of these special numbers, and the other is an outlier.
        //- (An outlier) is defined as a number that is (neither) one of (the original special numbers) nor the element
        // representing (the sum of those numbers).
        //* Note that (special numbers), (the sum element), and (the outlier) must have (distinct indices),
        // but may share (the same value).
        //* Return the ("largest" potential outlier) in nums.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //3 <= nums.length <= 10^5
        //-1000 <= nums[i] <= 1000
        //The input is generated such that (at least one potential outlier exists) in nums.
        //  + length<=10^5 => Time: O(n)
        //
        //- Brainstorm
        //
        //Example 2:
        //Input: nums = [-2,-1,-3,-6,4]
        //Output: 4
        //Explanation:
        //The special numbers could be -2, -1, and -3, thus making their sum -6 and the outlier 4.
        //
        //- Sum = sum of all of elements
        //- We only need to choose 2 values:
        //  + x,y: y=sum-x-y
        //  => sum-x=2*y
        //  => (sum-x)/2=y
        //
//        int[]  nums = {-2,-1,-3,-6,4};
//        int[]  nums = {-108,-108,-517};
        //output:0
        //rs: -517
        int[]  nums = {6,-31,50,-35,41,37,-42,13};
        //output: 13
        //rs: -35
        System.out.println(getLargestOutlier(nums));
    }
}
