package E1_daily;

public class E284_MaximumValueOfAnOrderedTripletI {

    public static long maximumTripletValue(int[] nums) {
        int n=nums.length;
        int[] maxSuffix=new int[n];
        int[] minSuffix=new int[n];
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;

        for (int i = n-1; i >=0 ; i--) {
            max=Math.max(nums[i], max);
            min=Math.min(nums[i], min);
            maxSuffix[i]=max;
            minSuffix[i]=min;
        }
        //min-max
        //
        long rs=Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j+1 < n; j++) {
                long curVal = nums[i]-nums[j];
                if(curVal<0){
//                    System.out.println(curVal*minSuffix[j+1]);
                    rs=Math.max(curVal*minSuffix[j+1], rs);
                }else{
//                    System.out.println(curVal*maxSuffix[j+1]);
                    rs=Math.max(curVal*maxSuffix[j+1], rs);
                }
            }
        }
//        System.out.println(rs);
        return rs < 0 ?0:rs;
    }

    public static long maximumTripletValueRefer(int[] nums) {
        int n=nums.length;
        int[] maxPrefix=new int[n];
        int[] maxSuffix=new int[n];
        int maxLeft=Integer.MIN_VALUE;
        int maxRight=Integer.MIN_VALUE;

        for (int i = 0; i <n ; i++) {
            maxLeft=Math.max(nums[i], maxLeft);
            maxRight=Math.max(nums[n-i-1], maxRight);
            maxPrefix[i]=maxLeft;
            maxSuffix[n-i-1]=maxRight;
        }
        //min-max
        //
        long rs=Long.MIN_VALUE;
        for (int i = 1; i+1 < n; i++) {
            long curMaxLeft = maxPrefix[i-1];
            long curMaxRight = maxSuffix[i+1];
            rs=Math.max(rs, (curMaxLeft-nums[i])*curMaxRight);
        }
//        System.out.println(rs);
        return rs < 0 ?0:rs;
    }

    public static void main(String[] args) {
        //- You are given (a 0-indexed integer array nums).
        //* Return (the maximum value) over all triplets of indices (i, j, k) such that i < j < k.
        //- If (all such triplets) have (a negative value),
        //  + return 0.
        //- The value of a triplet of indices (i, j, k) is equal to
        //  + (nums[i] - nums[j]) * nums[k].
        //
        //Example 1:
        //
        //Input: nums = [12,6,1,2,7]
        //Output: 77
        //Explanation: The value of the triplet (0, 2, 4) is (nums[0] - nums[2]) * nums[4] = 77.
        //It can be shown that there are no ordered triplets of indices with a value greater than 77.
        //
        //- (nums[i] - nums[j]) * nums[k]
        //  + x<0 ==> x*min
        //  + x>=0 ==> x*max
        //
        //1.2, Optimization
        //- We don't need to care about the order of (i,j)
        //  ==> Getting subtraction of that + check value:
        //  + <0: min
        //  + >=0: max
        //
        //- (nums[i] - nums[j]) * nums[k].
        //  + index=i, check:
        //      + PrefixLeftMax[i-1]
        //      + SuffixRight[i+1]
        //  ==> max
        //  <=>
        //  + (maxPrefix[i]-nums[i+1])*maxSuffix[i+2]
        //  ==> Optimize
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n^2) ==> O(n)
        //
        int[] nums = {1,10,3,4,19};
//        int[] nums = {1000000,1,1000000};
        System.out.println(maximumTripletValue(nums));
        System.out.println(maximumTripletValueRefer(nums));
        //
    }
}
