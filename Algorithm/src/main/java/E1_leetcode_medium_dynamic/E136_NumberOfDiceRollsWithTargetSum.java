package E1_leetcode_medium_dynamic;

public class E136_NumberOfDiceRollsWithTargetSum {

    public static int numRollsToTarget(int n, int k, int target) {
        int[][] dp=new int[n+1][target+1];
        int mod = 1_000_000_007;
        int maxInit = Math.min(k, target);

        for(int i=1;i<=maxInit;i++){
            dp[1][i]=1;
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=target;j++){
                int maxUp=Math.min(j, k);

                for(int l=1;l<=maxUp;l++){
                    dp[i][j]=(dp[i][j]+dp[i-1][j-l])%mod;
                }
            }
        }
        return dp[n][target];
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You have n dice, and each dice has k faces numbered from (1 to k).
        //Given three integers n, k, and target,
        //* Return the number of possible ways (out of the k^n total ways) to roll the dice,
        // so the (sum of the face-up) numbers equals target.
        //  - Return số cách để có thể roll the dice ==> Sum(face-up) number is equal to target.
        //- Since the answer may be too large, return it modulo 109 + 7.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= n, k <= 30
        //1 <= target <= 1000
        //
        //- Brainstorm
        //Ex:
        //Input: n = 2, k = 6, target = 7
        //Output: 6
        //Explanation: You throw two dice, each with 6 faces.
        //There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
        //
        //- Đại loại là return số lượng ways của n số
        //  + Mỗi số trong khoảng 1 -> k
        //  + Sao cho sum(all) = target.
        //- Liệu có thể tính:
        //  + (n,k) theo (n-i,k-j) gì đó được không?
        //- Giả sử dp[i][j] là số lượng cách với:
        //  + Số dice = i + j target
        //+ (1,1) = 1
        //+ (1,2) = 1
        //+ (1,k) = 1
        //+ (2,2) = 1
        //+ (2,3) = 1
        //  + (2,3) = (1,1)*(1,2) + (1,2)*(1,1) = 2
        //  + Dice cuối có thể chọn k cách.
        //CT:
        // - dp[i][j]=(dp[i][j]+dp[i-1][j-l])%mod;
        //- Init:
        //+ dp[1][1->Min(k, target)]=1
        //1.1, Optimization
        //1.2, Complexity:
        //- Space : O(n*target)
        //- Time : O(n*target*k)
        //
        //#Reference:
        //1775. Equal Sum Arrays With Minimum Number of Operations
        //2028. Find Missing Observations
        //
//        int n = 1, k = 6, target = 3;
//        int n = 2, k = 6, target = 7;
//        int n = 30, k = 30, target = 500;
//        int n = 12, k = 12, target = 50;
        int n = 2, k = 3, target = 9;
        System.out.println(numRollsToTarget(n, k, target));
    }
}
