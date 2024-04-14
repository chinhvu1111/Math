package contest;

import java.util.Arrays;

public class E54_MaximumStrengthOfKDisjointSubarrays {

    public static long[][] memo;

    public static long solution(int turn, int k, int curK,
                                int index, int n, long[] prefixSum,
                                int operator){
        if(index>=n){
            return 0;
        }
        if(turn==k){
//            System.out.printf("turn=%s, index=%s\n", turn, index);
            return 0;
        }
        if(memo[index][turn]!=-1){
            return memo[index][turn];
        }
        long curRs=Long.MIN_VALUE;
        //k=3
        //n=6: 0,1,2,3,4,5
        //i=0 --> 2 = 6-3-1
        //nums = [1,2,3,-1,2]
        //+
        for(int i=index;i<=n-curK;i++){
//            if(turn<k&&index==n-1){
//                continue;
//            }
            long addedVal=(prefixSum[i+1]-prefixSum[index])*curK*operator;
            long curSum=solution(turn+1, k, curK-1, i+1, n, prefixSum, operator*-1) + addedVal;
            //3,4 (count=2)
            //i+1=3, n=5
            if(n-i-1>=k-curK+1){
                curSum=Math.max(solution(turn, k, curK, i+1, n, prefixSum, operator), curSum);
            }
//            System.out.printf("start=%s, end=%s, curSum=%s\n", index, i, curSum);
            curRs=Math.max(curRs, curSum);
        }
        return memo[index][turn]=curRs;
    }

    public static long maximumStrengthTopDown(int[] nums, int k) {
        int n=nums.length;
        long[] prefixSum=new long[n+1];
        long sum=0;
        memo=new long[n][k+1];

        for(long[] arr: memo){
            Arrays.fill(arr, -1);
        }

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefixSum[i+1]=sum;
        }
        return solution(0, k, k, 0, n, prefixSum, 1);
    }

    public static long maximumStrengthBottomUp(int[] nums, int k) {
        int n=nums.length;
        //dp lưu thông tin nếu select đủ (k turns)
        long[] dp=new long[n];

        for(int i=0;i<k;i++){
            long curTurn=k-i;
            if(i%2==1){
                curTurn=curTurn*-1;
            }
            long[] tempDp=new long[n+1];
            Arrays.fill(tempDp, Long.MIN_VALUE/3);
            for(int j=1;j<=n;j++){
                tempDp[j]=Math.max(tempDp[j], Math.max(tempDp[j-1], dp[j-1])+nums[j-1]*curTurn);
            }
            dp=tempDp;
            for(int j=1;j<=n;j++){
                dp[j]=Math.max(dp[j], dp[j-1]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        // Requirement
        //- You are given a 0-indexed array of integers nums of length n, and a positive odd integer k.
        //The strength of x subarrays is defined as:
        // strength = sum[1] * x - sum[2] * (x - 1) + sum[3] * (x - 2) - sum[4] * (x - 3) + ... + sum[x] * 1
        // where sum[i] is the sum of the elements in (the ith subarray).
        // Formally, strength is sum of (-1)^i+1 * sum[i] * (x - i + 1) over all i's such that 1 <= i <= x.
        //You need to select k disjoint subarrays from nums, such that their strength is maximum.
        //* Return the maximum possible strength that can be obtained.
        //* Note that the selected subarrays don't need to cover the entire array.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n <= 10^4
        //  => Có thể làm O(n*k) được thôi
        //-109 <= nums[i] <= 10^9
        //1 <= k <= n
        //1 <= n * k <= 10^6
        //k is odd.
        //==> 10^6 số khá lớn ==> Long type
        //
        //- Brainstorm
        //--> Recursion
        //- Để tính sum nhanh --> prefix sum
//        int[] nums = {1,2,3,-1,2};
//        int k = 3;
//        int[] nums = {-99, 85};
//        int k = 1;
//        int[] nums = {-1,-2,-3};
//        int k = 1;
        //* Top down --> TLE
        //* Bottom up --> pass
        int[] nums = {75,-89,-79,87,64,-8,-9,89,29,85,77,-67,-60,-77,-68,31,-51,-53,-81,21,-44,-17,-81,-96,-7,66,54,-46,32,26,-15,-39,
                69,47,49,-50,82,-50,-18,-33,94,0,42,-44,49,-24,40,-94,40,28,22,-61,27,98,-40,88,-7,-90,-48,64,52,31,-41,61,-95,-2,-49,
                51,34,-21,30,-39,34,79,82,63,35,-23,-33,-9,-2,32,-38,-10,83,34,89,37,-7,37,-13,-23,14,83,56,70,56,-63,8,-48,34,64,29,
                37,93,97,57,18,-82,35,-23,72,48,-92,100,-17,-27,-92,25,75,98,91,-4,36,-8,-94,-42,-92,37,-73,20,75,-62,-31,75,-16,96,
                -94,-30,-70,2,-79,28,-81,20,4,-74,48,-60,-37,68,-46,-48,14,1,-76,-90,-66,-21,48,8,-92,30,-8,79,-71,29,-66,-65,6,4,-32,
                -69,-90,-45,-12,26,-65,45,-77,5,-100,-19,65,69,-89,86,24,4,-19,52,-54,49,6,-26,19,3,5,65,82,-12,26,9,-80,31,-52,-67,77,
                -80,62,-21,43,38,-1,14,-60,-84,53,37,-36,-9,17,-86,25,38,-13,83,75,16,47,-64,45,42,15,-53,91,-85,-19,-86,17,50,-12,
                -89,33,-26,-66,-75,68,-66,-89,-11,84,62,-1,-36,-92,43,92,-26,-5,15,0,-8,33,-6,-36,100,53,21,-16,89,-47,-13,57,-26,44,1,
                -55,12,-78,25,32,82,7,97,24,94,9,69,-78,35,44,-95,71,-87,14,63,-12,-16,-67,15,-13,-64,-29,-35,-93,-46,-57,36,11,50,1,8,
                -94,57,-88,5,-70,-46,-21,-48,-74,-100,97,-35,82,-92,67,-83,72,-99,72,-86,12,-15,-100,-71,100,-91,-43,-17,-51,79,19,
                -27,55,-25,-89,-82,22,-35,31,6,71,-23,-78,30,-47,-29,-67,-5,-74,7,21,-17,65,52,40,91,-49,4,100,-60,98,-13,29,-2,-26,
                88,91,72,-73,7,91,-54,-69,-54,34,-85,33,-34,5,-58,92,-50,-54,-8,-23,-55,13,37,-66,-31,7,21,-83,-42,-51,41,63,26,8,
                -54,82,-80,63,-35,-83,47,98,-53,14,-35,-33,-50,-80,-40,-19,-53,-26,-41,62,10,-2,-58,-47,40,-19,-54,62,-93,-64,78,25,
                87,71,-74,-98,-86,-64,-22,-74,-20,-19,-80,-69,73,-15,-67,47,76,89,58,90,2,85,-21,-38,74,94,-60,-72,59,-30,-81,8,39,
                -13,-68,-41,-33,-57,21,-93,80,-85,-20,-89,100,17,-28,7,-52,-7,-11,64,-37,-47,-30,-59,15,-19,-55,56,32,19,-61,-70,
                -80,13,-72,42,-29,-42,25,-28,47,82,50,6,-20,-13,42,-67,82,-26,-90,-45,-30,32,-58,54,5,-74,10,-71,6,-91,79,40,-100,
                -23,-90,23,-24,-57,34,-87,12,4,-56,-33,3,63,31,-8,-8,37,-77,52,79,33,-54,38,-45,-4,-44,-28,75,87,98,89,33,18,46,-65,
                -32,-79,-66,-54,-89,68,53,100,-78,-81,-60,-66,62,-66,-50,-26,-27,-50,80,63,64,80,93,94,54,-62,-30,98,59,64,92,18,67,
                -46,88,83,98,-51,-90,85,-33,58,-95,31,48,-47,59,34,-91,-77,18,79,13,-42,16,59,-13,77,-66,98,-52,-5,81,21,-3,-65,31,
                -41,17,92,-42,32,17,-7,-3,43,-93,-61,82,-53,69,95,-69,-22,42,29,-5,-64,-76,44,-30,4,-32,-64,76,-13,32,-58,40,-8,-5,
                85,-96,-65,-89,-3,-100,-78,-85,60,-93,-24,85,12,16,37,78,-43,72,100,44,84,-8,-36,65,35,23,-96,63,42,67,27,46,8,-27,
                60,-91,-60,-54,-62,83,-58,-62,-90,-78,-72,-4,43,-27,45,-37,40,5,72,29,54,-30,-20,22,-10,59,58,100,48,28,-71,-75,-49,
                88,97,34,-71,62,-67,-45,91,62,-21,-67,4,63,73,-16,-3,68,-33,-98,44,19,-37,14,47,-20,-61,62,43,-68,67,91,-75,-21,-79,
                21,16,44,-5,-2,-9,69,99,-21,8,32,-11,-92,86,-81,-49,-66,20,7,-38,-53,-74,-23,98,-23,-99,-59,-58,71,62,-85,-72,-70,76,
                -63,3,-1,70,-93,-40,33,94,81,96,14,70,33,-88,-94,-32,53,100,-17,-11,-6,-99,-8,72,34,36,48,100,4,61,61,-19,90,66,83,
                -21,-70,69,-10,-34,99,-55,97,-6,83,6,-44,-25,37,75,-77,73,-49,21,52,-77,93,-31,-5,3,15,-8,25,-56,-62,-19,-43,15,-9,
                -57,-18,17,77,-33,87,70,-44,-34,-78,77,-95,87,-63,-23,-51,81,-14,-89,43,-86,-70,-57,98,-44,-59,-22,10,-16,2,21,-68,
                62,27,-52,-85,28,99,0,-87,-63,-56,89,8,-88,-77,-34,-77,-49,13,-54,45,91,52,6,76,-67,-6,-86,-27,51,82,-34,21,83,79,66,
                -38,86,8,23,-1,-43,3,-41,26,4,74,76,64,-93,-39,54,4,78,94,39,-62,-82,52,3,-12,-51,-43,-73,-64,72,-94,84,-41,91,-55,25,
                100,67,32,-14,12,87,-53,3,27,-84,15,28,22,89,-87,-38};
        int k = 201;
        //
        System.out.println(maximumStrengthTopDown(nums, k));
        System.out.println(maximumStrengthBottomUp(nums, k));
    }
}
