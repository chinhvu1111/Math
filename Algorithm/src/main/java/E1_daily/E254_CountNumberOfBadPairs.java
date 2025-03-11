package E1_daily;

import java.util.HashMap;
import java.util.Map;

public class E254_CountNumberOfBadPairs {

    public static long countBadPairs(int[] nums) {
        int n=nums.length;
        HashMap<Integer, Long> mapCount=new HashMap<>();

        for(int i=0;i<n;i++){
            int subtractionVal = nums[i]-i;
            mapCount.put(subtractionVal, mapCount.getOrDefault(subtractionVal, 0L)+1);
        }
        long rs = (long)n*(long)(n-1)/2;
        for(Map.Entry<Integer, Long> e: mapCount.entrySet()){
            if(e.getValue()>1){
                rs-= e.getValue() * (e.getValue()-1) /2;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed integer array nums.
        //- A pair of indices (i, j) is a bad pair if i < j and (j - i != nums[j] - nums[i]).
        //* Return the total (number of bad pairs) in nums.
        //
        //Example 1:
        //
        //Input: nums = [4,1,3,3]
        //Output: 5
        //Explanation: The pair (0, 1) is a bad pair since 1 - 0 != 1 - 4.
        //The pair (0, 2) is a bad pair since 2 - 0 != 3 - 4, 2 != -1.
        //The pair (0, 3) is a bad pair since 3 - 0 != 3 - 4, 3 != -1.
        //The pair (1, 2) is a bad pair since 2 - 1 != 3 - 1, 1 != 2.
        //The pair (2, 3) is a bad pair since 3 - 2 != 3 - 3, 1 != 0.
        //There are a total of 5 bad pairs, so we return 5.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^9
        //  + Length<=10^5 => Time: O(n*k)
        //
        //* Brainstorm:
        //- (j-i) != nums[j]-nums[i]
        //
        //Example 1:
        //Input: nums = [4,1,3,3]
        //- 1-4 != 1-0
        //- If we go to nums[j]=x
        //  + We can go to nums[i] such that:
        //      + nums[j]-j = nums[i]-i
        //- Get how many pair having the same substraction:
        //  + result = n*(n-1)/2-x
        //
        //1.1, Special cases
        //
        //1.2, Optimization
        //
        //1.3, Complexity
        //
        //
        int[] nums = {4,1,3,3};
        System.out.println(countBadPairs(nums));
        //#Reference:
        //532. K-diff Pairs in an Array
        //1814. Count Nice Pairs in an Array
        //2006. Count Number of Pairs With Absolute Difference K
    }
}
