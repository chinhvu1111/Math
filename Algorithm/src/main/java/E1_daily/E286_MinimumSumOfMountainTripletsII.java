package E1_daily;

public class E286_MinimumSumOfMountainTripletsII {

    public static int minimumSum(int[] nums) {
        int n=nums.length;
        int[] prefixMin=new int[n];
        int[] suffixMin=new int[n];
        int min=Integer.MAX_VALUE;
        int min1=Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            prefixMin[i]=min;
            suffixMin[n-i-1]=min1;
            min=Math.min(min, nums[i]);
            min1=Math.min(min1, nums[n-i-1]);
        }
        int rs=Integer.MAX_VALUE;
        for (int i = 0; i+1 < n; i++) {
            if(prefixMin[i]==Integer.MAX_VALUE||suffixMin[i]==Integer.MAX_VALUE){
                continue;
            }
            if(nums[i]>prefixMin[i]&&nums[i]>suffixMin[i]){
                rs=Math.min(rs, nums[i]+prefixMin[i]+suffixMin[i]);
            }
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed array nums of integers).
        //- A triplet of indices (i, j, k) is a mountain if:
        //  + i < j < k
        //- nums[i] < nums[j] and nums[k] < nums[j]
        //* Return (the "minimum" possible sum of a mountain triplet of nums).
        //  + If (no such triplet) exists,
        //  return -1.
        //
        //Example 1:
        //
        //Input: nums = [8,6,1,5,3]
        //Output: 9
        //Explanation: Triplet (2, 3, 4) is a mountain triplet of sum 9 since:
        //- 2 < 3 < 4
        //- nums[2] < nums[3] and nums[4] < nums[3]
        //And the sum of this triplet is nums[2] + nums[3] + nums[4] = 9.
        // It can be shown that there are no mountain triplets with a sum of less than 9.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //3 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^8
        //  + Length<=10^5 ==> Time: O(n)
        //  + nums[i]<=10^8 ==> Long
        //
        //* Brainstorm:
        //Example 1:
        //
        //Input: nums = [8,6,1,5,3]
        //Output: 9
        //- Pivot(index=i):
        //=> (Prefix, suffix) min
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //
        //
//        int[] nums = {8,6,1,5,3};
//        int[] nums = {5,4,8,7,10,2};
        int[] nums = {6,5,4,3,4,5};
        System.out.println(minimumSum(nums));
        //
        //#Reference:
        //2786. Visit Array Positions to Maximize Score
        //1222. Queens That Can Attack the King
        //480. Sliding Window Median - Hard
    }
}
