package contest;

import java.util.*;

public class E261_MaximumAndMinimumSumsOfAtMostSizeKSubsequences_classic {

    private static final int mod = 1_000_000_007;
    private static long modCal(long a) {
        return pow(a, mod - 2);
    }
    private static long pow(long b, long exponent) {
        long result = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = result * b % mod;
            }
            b = b * b % mod;
            exponent /= 2;
        }
        return result;
    }

    public static int minMaxSumsWrong(int[] nums, int k) {
        int n = nums.length;
        List<int[]> indexAndMax = new ArrayList<>();
        long rs = 0;
        for (int i = 0; i < n; i++) {
            indexAndMax.add(new int[]{i, nums[i], nums[i]});
            rs = (rs + nums[i] + nums[i]) % mod;
        }
        for (int i = 1; i < k; i++) {
            List<int[]> newIndexAndMax = new ArrayList<>();
            int m = indexAndMax.size();
            for (int j = 0; j < m; j++) {
                int curIndex = indexAndMax.get(j)[0];
                int curValMax = indexAndMax.get(j)[1];
                int curValMin = indexAndMax.get(j)[2];
                if (curIndex + 1 < n) {
                    int nextMax = Math.max(curValMax, nums[curIndex + 1]);
                    int nextMin = Math.min(curValMin, nums[curIndex + 1]);
                    newIndexAndMax.add(new int[]{curIndex + 1, nextMax, nextMin});
                    rs = (rs + nextMax + nextMin) % mod;
//                    System.out.printf("%s %s %s\n", curValMax, curValMin, i);
                }
            }
            indexAndMax = newIndexAndMax;
        }
        return (int) rs;
    }

    public static long ckn(int n, int k) {
        if (k > n) return 0;
        if (k == 0 || k == n) return 1;
        if (k > n - k) {
            k = n - k;
        }

        long rs = 1;
        for (int i = 0; i < k; i++) {
            rs = rs * (n - i) % mod;
            rs = rs * modCal(i + 1) % mod;
        }

        return rs;
    }

    static long[] fact;
    static long[] inv;

    static void precomputeFactorials(int n) {
        fact = new long[n + 1];
        inv = new long[n + 1];
        fact[0] = 1;
        //Calculate: (0->n)!
        for (int i = 1; i <= n; i++) {
            fact[i] = (fact[i - 1] * i) % mod;
        }
        inv[n] = power(fact[n], mod - 2, mod);
        for (int i = n - 1; i >= 0; i--) {
            inv[i] = (inv[i + 1] * (i + 1)) % mod;
        }
    }

    static long power(long a, long b, int m) {
        long val = 1;
        a = a % m;
        while (b > 0) {
            if ((b & 1) == 1) val = (val * a) % m;
            b = b >> 1;
            a = (a * a) % m;
        }
        return val;
    }

    static long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        return (fact[n] * inv[r] % mod * inv[n - r]) % mod;
    }

    public static int minMaxSumsTLEs(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        precomputeFactorials(n);
        long rs = 0;
        HashMap<String, Long> map=new HashMap<>();

        for (int i = 0; i < n; i++) {
//            int start = Math.max(0, i-k+1);
//            long prev = rs;
            //0,1,2
//            int size=i-start+1;
            for (int j = 1; j <= k; j++) {
                if (j != 1) {
                    long curVal = 0;
                    String key = i+" "+(j-1);
                    if(!map.containsKey(key)){
                        curVal = nCr(i, j - 1);
                        map.put(key, curVal);
                    }else{
                        curVal=map.get(key);
                    }
                    rs = (rs + nums[i] * curVal) % mod;
                } else {
                    rs = (rs + nums[i]) % mod;
                }
                if (j != 1) {
                    long curVal = 0;
                    String key = (n-i-1)+" "+(j-1);
                    if(!map.containsKey(key)){
                        curVal = nCr(n - i - 1, j - 1);
                        map.put(key, curVal);
                    }else{
                        curVal=map.get(key);
                    }
                    rs = (rs + nums[i] * curVal) % mod;
                } else {
                    rs = (rs + nums[i]) % mod;
                }
            }
//            System.out.printf("%s %s\n", rs-prev, nums[i]);
        }
        return (int) rs;
    }

    public static int minMaxSums(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        precomputeFactorials(n);
        long rs = 0;
//        HashMap<String, Long> map=new HashMap<>();

        for (int i = 0; i < n; i++) {
//            int start = Math.max(0, i-k+1);
//            long prev = rs;
            //0,1,2
//            int size=i-start+1;
            for (int j = 1; j <= k; j++) {
                if (j != 1) {
                    long curVal = nCr(i, j - 1);
                    rs = (rs + nums[i] * curVal) % mod;
                } else {
                    rs = (rs + nums[i]) % mod;
                }
                if (j != 1) {
                    long curVal = nCr(n - i - 1, j - 1);
                    rs = (rs + nums[i] * curVal) % mod;
                } else {
                    rs = (rs + nums[i]) % mod;
                }
            }
//            System.out.printf("%s %s\n", rs-prev, nums[i]);
        }
        return (int) rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an integer array nums) and (a positive integer k).
        //* Return (the sum of the maximum and minimum elements) of all subsequences of nums with (at most k elements).
        //- A non-empty subsequence is an array that can be derived from another
        // array by deleting some or no elements without changing the order of the remaining elements.
        //- Since the answer may be very large, return it modulo (10^9 + 7).
        //
        //Example 1:
        //
        //Input: nums = [1,2,3], k = 2
        //Output: 24
        //Explanation:
        //The subsequences of nums with at most 2 elements are:
        //Subsequence	Minimum	Maximum	Sum
        //[1]	1	1	2
        //[2]	2	2	4
        //[3]	3	3	6
        //[1, 2]	1	2	3
        //[1, 3]	1	3	4
        //[2, 3]	2	3	5
        //Final Total	 	 	24
        //The output would be 24.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 10^5
        //0 <= nums[i] <= 10^9
        //1 <= k <= min(100, nums.length)
        //  + Len<=10^5 ==> Time: O(n)
        //  + nums[i]<=10^9 ==> Time: O(log(k))
        //
        //- Brainstorm
        //- All of subsequences
        //Ex:
        //Input: nums = [1,2,3], k = 2
        //Output: 24
        //
        //nums = [1,2,3]
        //
        //(1,2)+3
        //dp[i] = max(dp[i-1], nums[i])
        //
        int[] nums = {1, 2, 3};
        int k = 2;
//        int[] nums = {1,1,1};
//        int k = 2;
        //1,1,1
        //[1,1],[1,1],[1,1]
        //
        //- Wrong idea
        //  ==> Subsequence not subarray
        //
        //- [1,2,3]
        //[1,2],[2,3] + [1,3]
        //  + ...x
        //- Heap:
        //[1,2]
        //= 1+2+(1+2)
        //(a,b,c)+d
        //- d is max value
        //- Combination array can have:
        //  + 1 -> (size+1)
        //  + size => 1
        //      Ex: 1,2,3 = 3*(3-1)/2
        //      + size*(size-1)/2*d
        //  + d*size(a,b,c)
        //[1,2,3]
        //[2]
        //  + rs+=2+2+2
        //  + rs+1+1+1
        //+ subsequence[index=1]
        //  + [1],[2],[1,2]
        //
        //- For each element in array such as 3
        //  + We find all of subsequence we can make if we use 3 as the (last/(max or min) element)
        //
        //[1,2,(3)]
        //+ k=1
        //  [3]
        //+ k=2
        //  [3] * size(2,3)
        //+ k=3
        //  [3]* (choose 2 in (i-1) previous elements)
        //
        //- We need to calculate C(k,n) in the optimal way
        //
        //Approach
        //1. Sorting the Array
        //First, sort the array in ascending order.
        // This makes it easy to calculate contributions when an element is the smallest (consider elements to the right)
        // or the largest (consider elements to the left).
        //2. Precomputing Factorials and Modular Inverses
        //- To efficiently compute C(n,r), precompute:
        //- Factorials fact[i] modulo 10^9 + 7 for i = 0, 1, ..., n
        //  + fact[i] = 1*1*...*i
        //- Modular inverses inv[i] of factorials using Fermat's Little Theorem:
        //  + inv[i] = fact[i]^(mod-2) mod (10^9 + 7)
        //
        //* Formula:
        //  + C(n,r) = fact[n] / (fact[r]*fact[n-r]) mod MOD
        //           = fact[n] * inv[r] * inv[n-r] mod MOD
        //
        //
        //These allow us to compute C(n,r) in O(1) time after O(n) preprocessing.
        //
        //1.1, Optimization
        //- Time Complexity:
        //- Precomputing Factorials:
        //  + O(n)
        //- Precomputing Inverses:
        //  + O(n)
        //- Computing a Binomial Coefficient:
        //  + O(1)
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n*k)
        //
//        System.out.println(minMaxSums(nums, k));
        //
        //- Fake max value:
//        List<Integer> list=new ArrayList<>();
//        for(int i=0;i<10000;i++){
//            list.add(i);
//        }
//        Integer[] numsInt=list.stream().toArray(Integer[]::new);
//        int k = 100;
//        int[] nums=new int[numsInt.length];
//        for(int i=0;i<numsInt.length;i++){
//            nums[i]=numsInt[i];
//        }
//        System.out.println(minMaxSumsTLEs(nums, k));
        System.out.println(minMaxSums(nums, k));
        //#Reference:
        //3251. Find the Count of Monotonic Pairs II
        //2136. Earliest Possible Day of Full Bloom
        //844. Backspace String Compare
        //1064. Fixed Point
        //1808. Maximize Number of Nice Divisors
        //2587. Rearrange Array to Maximize Prefix Score
    }
}
