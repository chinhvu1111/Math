package E1_leetcode_medium_dynamic;

public class E157_MaximumAbsoluteSumOfAnySubarray {

    public static int maxAbsoluteSum(int[] nums) {
        int n=nums.length;
        int[] prefixSum=new int[n+1];
        int rs=0;
        int minVal=0;
        int maxVal=0;

        for(int i=1;i<=n;i++){
            prefixSum[i]=prefixSum[i-1]+nums[i-1];
//            System.out.printf("i=%s, prefixSum[i]=%s\n", i, prefixSum[i]);
            rs=Math.max(rs, Math.max(Math.abs(prefixSum[i]-minVal), Math.abs(prefixSum[i]-maxVal)));
            minVal=Math.min(minVal, prefixSum[i]);
            maxVal=Math.max(maxVal, prefixSum[i]);
        }
        return rs;
    }

    public static int maxAbsoluteSumOptimization(int[] nums) {
        int n=nums.length;
//        int[] prefixSum=new int[n+1];
        int rs=0;
        int minVal=0;
        int maxVal=0;
        int sum=0;

        for(int i=1;i<=n;i++){
            sum+=nums[i-1];
//            System.out.printf("i=%s, prefixSum[i]=%s\n", i, prefixSum[i]);
            rs=Math.max(rs, Math.max(Math.abs(sum-minVal), Math.abs(sum-maxVal)));
            minVal=Math.min(minVal, sum);
            maxVal=Math.max(maxVal, sum);
        }
        return rs;
    }

    public static int maxAbsoluteSumOptimization1(int[] A) {
        int s = 0, min = 0, max = 0;
        for (int a: A) {
            s += a;
            min = Math.min(min, s);
            max = Math.max(max, s);
        }
        return max - min;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer array nums. The absolute sum of a subarray [numsl, numsl+1, ..., numsr-1, numsr]
        // is abs(numsl + numsl+1 + ... + numsr-1 + numsr).
        //* Return (the maximum absolute sum of any (possibly empty) subarray of nums).
        //- Note that abs(x) is defined as follows:
        //  + If x is (a negative integer), then abs(x) = -x.
        //  + If x is (a non-negative integer), then abs(x) = x.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= nums.length <= 10^5
        //-10^4 <= nums[i] <= 10^4
        //
        //- Brainstorm
        //- Dùng Prefix Sum
        //- Vì cả negative và positive number ==> Cần phải xét min và max.
        //
        //1.1, Optimization
        //- Space O(n) --> O(1)
        //- Max - min chính là cái cần tìm
        //  ==> rs = max - min (prefix sum là được)
        //1.2, Complexity
        //- Space : O(1)
        //- Time : O(n)
        //
        //#Reference:
        //2061. Number of Spaces Cleaning Robot Cleaned
        //1054. Distant Barcodes
        //2086. Minimum Number of Food Buckets to Feed the Hamsters
//        int[] nums=new int[]{1,-3,2,3,-4};
        int[] nums=new int[]{2,-5,1,-4,3,-2};
        System.out.println(maxAbsoluteSum(nums));
        System.out.println(maxAbsoluteSumOptimization(nums));
        System.out.println(maxAbsoluteSumOptimization1(nums));
    }
}
