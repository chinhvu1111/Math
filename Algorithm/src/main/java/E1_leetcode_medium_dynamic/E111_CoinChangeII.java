package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E111_CoinChangeII {

    public static int changeWrongBottomUp(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        int n = coins.length;
        int[] dp = new int[amount + 1];

        for (int e : coins) {
            dp[e] = 1;
        }
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= coins[j] && coins[j] <= i / 2) {
                    dp[i] += dp[i - coins[j]];
                }
            }
        }
        for (int e : dp) {
            System.out.printf("%s, ", e);
        }
        System.out.println();
        return dp[amount];
    }

    public static int changeBottomUp(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        int n = coins.length;
        int[] dp = new int[amount + 1];

//        for(int e: coins){
//            dp[e]=1;
//        }
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
//        for(int e:dp){
//            System.out.printf("%s, ", e);
//        }
//        System.out.println();
        return dp[amount];
    }

    public static int[][] memo;

    public static int solutionTopDown(int[] coins, int index, int amount) {
        if (index >= coins.length||amount<0) {
            return 0;
        }
        if (amount == 0) {
            return 1;
        }
        if (memo[index][amount] != -1) {
            return memo[index][amount];
        }
        int numCasesUsedIndex = solutionTopDown(coins, index, amount - coins[index]);
        int numCasesUsedNotIndex = solutionTopDown(coins, index + 1, amount);
        return memo[index][amount] = numCasesUsedIndex + numCasesUsedNotIndex;
    }

    public static int changeTopDown(int amount, int[] coins) {
        int n = coins.length;
        memo = new int[n][amount + 1];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }
        return solutionTopDown(coins, 0, amount);
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Given coins array representing coins of different denominations (Đồng tiền mệnh giá khác nhau)
        //- Amount đại diện cho tổng số tiền
        //- Ta có vô hạn số lượng coin mỗi loại
        //* Return số lượng combinations mà có thể tạo ra "amount" <> nếu không có return 0.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= coins.length <= 300
        //1 <= coins[i] <= 5000
        //All the values of coins are unique.
        //0 <= amount <= 5000
        //
        //- Brainstorm
        //* Bottom Up approach:
        //Ex:
        //Input: amount = 5, coins = [1,2,5]
        //Output: 4
        //Explanation: there are four ways to make up the amount:
        //5=5
        //5=2+2+1
        //5=2+1+1+1
        //5=1+1+1+1+1
        //
        //- Xây dựng từ 1 --> amount
        //+ Reach 2:
        //2 = 0 + dp[2]
        //2 = 1 + dp[1]
        //+ Reach 3:
        //3 = 1 + dp[2] = 2 (cách) = (1,1,1 or 1, 2)
        //3 = 2 + dp[1] = 1 (cách) <=> 2, 1
        //  + Ta đang thấy nó lặp lại
        //  ==> Phần bù không được lớn hơn phần còn lại.
        //  --> Tức là :
        //     + Loại bỏ case:
        //     + 3 = 2 + dp[1]
        //+ Reach 4:
        //+ 4 = 1 + dp[3]
        //  + 1,(1,2)
        //  + 1,(1,1,1)
        //+ 4 = 2 + dp[2]
        //  + 2,2
        //  + 2,(1,1) ==> Cái này bị trùng
        //- Ta phải có thêm 1 direction nữa để loại bỏ duplicated cases
        //- Tư duy cũ là tính all (1 -> amount)
        //  + Mỗi i(1 -> amount) : tính cho all coins
        //  --> Xảy ra duplication do 1 coin được sử dụng nhiều lần --> chỉ khác position
        //
        //Ex:
        //i=0:
        //  + j=1: 1
        //  + j=2:
        //      + dp[1]+dp[2]= 2
        //      + 1,1
        //      + 2
        //  + j=3:
        //      + 1+dp[2] = 2
        //      + 1,1,1
        //      + 1,1,2
        //  + j=4:
        //      + 1+ (dp[3]) = 2 (*1)
        //      + 1,1,1,1
        //      + 1,1,2
        //i=1, val=2:
        //  + j=2:
        //      + dp[2]
        //  + j=3:
        //      + 2+dp[1]:
        //      + 2,1 ==> Đã trùng
        //  + j=4:
        //      + 2 + (dp[2]) (*2)
        //      + 2,1,1 ==> Cái này trùng
        //      + 2,2
        //- Từ (*1),(*2)
        // ==> Ta thấy vẫn duplicated bời vì [3] đang chứa 1 số case của [2]
        //* Duplication problem:
        //- Do init dp sai:
        //  + Mỗi (index=i) --> Coi như là việc sử dụng element được xét đến (i)
        //  Nhưng mình lại assign (i: 0-> n-1) dp[i]=1 ==> nó sẽ bị cộng vào coi như đã dùng rồi ==> SAI
        //  + Rule:
        //      + Ta sẽ build all amount trên tập hợp coins cho đến (index=i)
        //==> Mỗi lần calculate thì ta add thêm index, thì sẽ không trùng.
        //
        //* Top Down approach:
        //Ex:
        //coins=[1,2,5], amount=5
        //5=1,1,1,1,1 --> Giá trị is repeatable
        //- Chia làm 2 cases:
        //+ Khi sử dụng index=i rồi
        //--> Đằng sau chỉ có thể sử dụng index>=i (Lớn hơn index trước đó mà thôi)
        //+ Không sử dụng index=i:
        //
        //                  (i=0,amount=5)
        //      / (used i=0)      \ (not used i=0)
        // (i=0,amount=4)       (i=1,amount=5)
        //--> Các trường hợp trùng ta sẽ lưu memoization.
        //- Formula:
        //+ memo[index][amount] = numCasesUsedIndex + numCasesUsedNotIndex:
        //  + Số lượng cách ta có ở index=0 -> n-1 với amount
        //
        //1.2, Optimization
        //
        //1.3, Complexity
        //- Time : O(n*amount)
        //- Space : O(amount)
        //
        //#Reference:
        //2218. Maximum Value of K Coins From Piles
        //2585. Number of Ways to Earn Points
        //2902. Count of Sub-Multisets With Bounded Sum
        //
//        int[] coins={1,2,5};
//        int amount=5;
//        int[] coins={7};
//        int amount=0;
        int[] coins = {1, 2, 5};
        int amount = 500;
//        System.out.println(changeWrong(amount, coins));
//        System.out.println(changeWrongBottomUp(amount, coins));
        System.out.println(changeBottomUp(amount, coins));
        System.out.println(changeTopDown(amount, coins));
    }
}
