package interviews;

import java.util.HashSet;
import java.util.Set;

public class E87_MaximumLengthOfRepeatedSubarray {

    public static int findLengthDynamic(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int m=nums2.length;
        int dp[][]=new int[n+1][m+1];
        int rs=0;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(nums1[i-1]==nums2[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                    rs=Math.max(rs, dp[i][j]);
                }
            }
        }
        return rs;
    }

    private static final int PRIME = 101;
    private static final int MOD = 1_000_000_007;

    public int findLength(int[] A, int[] B) {
        if (A.length < B.length) return findLength(B, A);

        // 1.) Check result for length 1  <==>  single character
        Set<Integer> set = new HashSet<>();
        boolean lenZero = false;

        for (int a : A) set.add(a);
        for (int b : B) {
            if (!set.contains(b)) continue;
            lenZero = true;
            break;
        }

        if (!lenZero) return 0;


        // 2). Now the result lies in [1, min(A.length, B.length)], perform binary serach
        int left = 1, right = Math.min(A.length, B.length);
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (check(mid, A, B)) left = mid;
            else right = mid - 1;
        }
        return check(right, A, B) ? right : left;
    }

    // check every subarray with length mid
    private boolean check(int mid, int[] A, int[] B) {
        Set<Integer> set = new HashSet<>();

        // 1). Adding hash values of subarrays of B into set
        long hash = 0;
        for (int j = 0; j < mid; j++) {
            hash = hash * PRIME + (B[j] + 1);
            hash %= MOD;
        }
        set.add((int) hash);

        long power = 1;
        for (int i = 0; i < mid-1; i++) {
            power = (power * PRIME) % MOD;
        }

        for (int i = 0, j = mid; j < B.length; i++, j++) {
            hash = hash - ((B[i] + 1) * power % MOD) + MOD;
            hash = hash * PRIME + (B[j] + 1);
            hash %= MOD;
            set.add((int) hash);
        }

        // 2.) Check the hash values of subarrays of A
        hash = 0;
        for (int j = 0; j < mid; j++) {
            hash = hash * PRIME + (A[j] + 1);
            hash %= MOD;
        }
        if (set.contains((int)hash)) return true;

        for (int i = 0, j = mid; j < A.length; i++, j++) {
            hash = hash - ((A[i] + 1) * power % MOD) + MOD;
            hash = hash * PRIME + (A[j] + 1);
            hash %= MOD;
            if (set.contains((int)hash)) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int nums1[]=new int[]{1,2,3,2,1};
        int nums2[]=new int[]{3,2,1,4,7};

        //1, Basic dynamic
        System.out.println(findLengthDynamic(nums1, nums2));
        //2, Rolling hash
        //
    }
}
