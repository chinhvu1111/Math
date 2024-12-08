package contest;

import java.util.HashMap;

public class E233_MaximumSubarraySumWithLengthDivisibleByK {

    public static long maxSubarraySum(int[] nums, int k) {
        int n=nums.length;
//        long[] prefixSum=new long[n];
        long sum=0;
        HashMap<Integer, Long> modToVal=new HashMap<>();
        long rs=Long.MIN_VALUE;
        modToVal.put(0, 0L);

        for(int i=0;i<n;i++){
            sum+=nums[i];
//            prefixSum[i]=sum;
            int mod=(i+1)%k;
            Long curVal= modToVal.getOrDefault(mod, Long.MAX_VALUE);
            if(curVal!=Long.MAX_VALUE){
                rs=Math.max(rs, sum-curVal);
            }
            if(curVal>sum){
                curVal=sum;
            }
            modToVal.put(mod, curVal);
        }

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an array of integers nums) and (an integer k).
        //* Return (the maximum sum) of (a non-empty subarray of nums), such that (the size of the subarray) is divisible (by k).
        //- A subarray is a contiguous non-empty sequence of elements within an array.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= k <= nums.length <= 2 * 10^5
        //-10^9 <= nums[i] <= 10^9
        //  + length<=2*10^5 ==> Time: O(n)
        //  + -10^9 ==> Long:
        //      + <0 ===> Ignore
        //
        //- Brainstorm
        //- Size = x*k
        //- For index (i):
        //  + x=1: j=i-k
        //  + x=2: j=i-2*k
        //
        //- Size%k==0
        //- (prefix sum + map) with modulo
        //- Map store min prefix sum for index=i
        //Ex:
        //nums = [-1,-2,-3,-4,-5], k = 2
        //[-3,-4]
        //- start=2
        //- end=3
        //  - 3%2 = 1
        //  - 1%2=1 ==> (3-1)%2==0
        //
        //
//        int[]  nums = {-1,-2,-3,-4,-5};
//        int k = 4;
        int[]  nums = {0,10,-3,-4,-5};
        int k = 2;
        System.out.println(maxSubarraySum(nums, k));
    }
}
