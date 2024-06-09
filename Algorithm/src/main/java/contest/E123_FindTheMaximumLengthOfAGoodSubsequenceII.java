package contest;

import java.util.Arrays;

public class E123_FindTheMaximumLengthOfAGoodSubsequenceII {

    public static int valueAfterKSeconds(int n, int k) {
        int[] nums=new int[n];
        Arrays.fill(nums, 1);
        //- second=0
        //nums=    1,1,1,1
        //prefix = 1,2,3,4
        //- second=1
        //nums=prefix
        //prefix = 1,3,6,10
        int[] prefix=new int[n];
        int sum=0;
        int mod=1_000_000_007;

        for(int i=0;i<n;i++){
            sum=(sum+nums[i])%mod;
            prefix[i]=sum;
        }
        for(int i=1;i<=k;i++){
            for (int j = 0; j < n; j++) {
                nums[j]=prefix[j];
            }
            int curSum=0;
            for(int j=0;j<n;j++){
                curSum=(curSum+nums[j])%mod;
                prefix[j]=curSum;
            }
        }

        return nums[n-1];
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given two integers n and k.
        //Initially, you start with (an array a of n integers) where a[i] = 1 for all (0 <= i <= n - 1).
        // After each second, you simultaneously update each element to be (the sum of all its preceding elements plus the element itself).
        // For example, after one second, a[0] remains the same, (a[1] becomes a[0] + a[1]), a[2] becomes a[0] + a[1] + a[2], and so on.
        //* Return the value of a[n - 1] after k seconds.
        //Since the answer may be very large, return it modulo 109 + 7.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n, k <= 1000
        //==> Không quá lớn
        //
        //- Brainstorm
        //
        //
//        int n = 4, k = 5;
//        int n = 5, k = 3;
//        int n = 1, k = 3;
        int n = 5, k = 1000;
        System.out.println(valueAfterKSeconds(n, k));
    }
}
