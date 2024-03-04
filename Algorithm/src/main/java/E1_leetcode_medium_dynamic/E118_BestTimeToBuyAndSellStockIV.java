package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E118_BestTimeToBuyAndSellStockIV {

    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][k + 1][2];
        int[] maxPriceBuy = new int[k + 1];
        int[] maxPriceSell = new int[k + 1];
        Arrays.fill(maxPriceBuy, Integer.MIN_VALUE);
        Arrays.fill(maxPriceSell, Integer.MIN_VALUE);
//        int[] indexMaxPriceBuy=new int[k+1];
//        int[] indexMaxPriceSell=new int[k+1];
        //- The Max price after buying at kth transaction
        //+ maxPriceBuy[k]
        //- The Index of Max price after buying at kth transaction
        //+ indexMaxPriceBuy[k]

        for (int i = 0; i < n; i++) {
            dp[i][1][0] = -prices[i];
        }
        maxPriceBuy[1]=Integer.MIN_VALUE;

        //#Sell
        //dp[i][j][1]
        //#Buy
        //dp[i][j][0]
        //      3  | 2   |6|5|0|3
        //Sell: 0  | -1  |
        //Buy: -3  | -2  |
        int rs=0;
        for (int i = 0; i < n; i++) {
            //Init for k=1
            maxPriceBuy[1]=Math.max(dp[i][1][0], maxPriceBuy[1]);
            //0,1,2,3 ==> Max= 2 transaction
            //0,1,2 ==> Max= 2 transaction
            //0,1 ==> Max= 2 transaction
            //Nếu i=1, j=2:
            //  + Buy :
            // 0,1,2,3 ==> Chỉ có thể tính buy(j<=2) = (i+1)/2
            // 0,1,2,3 ==> Tính cho buy(j=1)/ sell(j=1)
//            int limit=Math.min((i+1)/2+1, k);
            //* Khi đã if else rồi:
            //==> Để k cũng đúng thôi.
            for (int j = 1; j <= k; j++) {
                //+ Buy ==> Buy sau khi sell
                //  + buy(j) -> sell(j-1)
                dp[i][j][0] = (maxPriceSell[j-1]==Integer.MIN_VALUE?0:maxPriceSell[j-1]) - prices[i];
                //+ Sell
                if(maxPriceBuy[j]!=Integer.MIN_VALUE){
                    dp[i][j][1] = maxPriceBuy[j] + prices[i];
                }
                if (maxPriceBuy[j] < dp[i][j][0]) {
                    maxPriceBuy[j] = dp[i][j][0];
                }
                if (maxPriceBuy[j]!=Integer.MIN_VALUE&&maxPriceSell[j] < dp[i][j][1]) {
                    maxPriceSell[j] = dp[i][j][1];
                }
                System.out.printf("i=%s, maxPriceBuy(j=%s): %s\n", i, j, maxPriceBuy[j]);
                System.out.printf("i=%s, maxPriceSell(j=%s): %s\n", i, j, maxPriceSell[j]);
            }
        }
        for(int i=1;i<=k;i++){
            rs=Math.max(rs, maxPriceBuy[i]);
            rs=Math.max(rs, maxPriceSell[i]);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given an integer array prices where prices[i] is the price of a given stock on the (ith day),
        // and an integer k.
        //* Find (the maximum profit) you can achieve.
        //+ buy + sell = transaction
        // You may complete (at most k transactions):
        // i.e. you may buy (at most k times) and (sell at most k times).
        //Note: You may not engage in (multiple transactions) simultaneously
        // (i.e., you must sell the stock before you buy again).
        //  + Vẫn cần sell trước khi buy again.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= k <= 100
        //1 <= prices.length <= 1000
        //0 <= prices[i] <= 1000
        //==> Số cũng không lớn ==> Time có thể <= O(n^2)
        //
        //- Brainstorm
        //- Mình sẽ chỉ được (buy/ sell) <= k times.
        //Ex:
        //Input: k = 2, prices = [3,2,6,5,0,3]
        //Output: 7
        //
        //- tại index=i có thể:
        //  + sell
        //      + Sell lần thứ (0 --> j) ==> Ghép với min(buy(-1 --> j-1))
        //  + buy
        //      + Buy lần thứ (0 --> j) ==> Ghép với min(sell(-1 --> j-1))
        //
        //
        int k = 2;
//        int[] prices = {3, 2, 6, 5, 0, 3};
//        int[] prices = {2,4,1};
//        int[] prices = {1,2,4,7};
        int[] prices = {1,4,2,7};
        System.out.println(maxProfit(k, prices));
        System.out.println((4+1)/2);
        //#Reference:
        //2291. Maximum Profit From Trading Stocks
    }
}
