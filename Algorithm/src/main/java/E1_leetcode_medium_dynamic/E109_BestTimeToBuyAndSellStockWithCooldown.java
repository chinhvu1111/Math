package E1_leetcode_medium_dynamic;

public class E109_BestTimeToBuyAndSellStockWithCooldown {

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        int rs = 0;

        for (int i = 0; i < n; i++) {
            //Buy
            int maxSell = 0;
            for (int j = 0; j < i - 1; j++) {
                maxSell = Math.max(maxSell, dp[j][1]);
            }
            dp[i][0] = maxSell - prices[i];

            int maxBuy = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                maxBuy = Math.max(maxBuy, dp[j][0]);
            }
            if (maxBuy != Integer.MIN_VALUE) {
                dp[i][1] = maxBuy + prices[i];
            }
            rs = Math.max(rs, Math.max(dp[i][0], dp[i][1]));
        }
//        for(int[] e: dp){
//            System.out.printf("%s %s, ", e[0], e[1]);
//        }
//        System.out.println();
        return rs;
    }

    public static int maxProfitTimeOptimization(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        int rs = 0;
        int maxSell = 0;
        int maxBuy = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            //Buy
            if (i >= 2) {
                maxSell = Math.max(maxSell, dp[i - 2][1]);
            }
            dp[i][0] = maxSell - prices[i];
            if (i >= 1) {
                maxBuy = Math.max(maxBuy, dp[i - 1][0]);
            }
            if (maxBuy != Integer.MIN_VALUE) {
                dp[i][1] = maxBuy + prices[i];
            }
            rs = Math.max(rs, Math.max(dp[i][0], dp[i][1]));
        }
//        for(int[] e: dp){
//            System.out.printf("%s %s, ", e[0], e[1]);
//        }
//        System.out.println();
        return rs;
    }

    public static void shiftArray(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            arr[i] = Math.max(arr[i + 1], arr[i]);
        }
    }

    public static int maxProfitSpaceOptimization(int[] prices) {
        int n = prices.length;
        if (n == 0 || n == 1) {
            return 0;
        }
        int[] dpBuy = new int[3];
        int[] dpSell = new int[3];
        int rs = 0;
        //0,1,2,3,4
        //+ indexes= 0,1,2
        //[0,1,2],3,4
        //+ Shift indexes= 0,1,2 ==> Keep index
        //0,[1,2,3],4
        //
        //- Clarify idea:
        //Ex:
        //buy:  0,1,(2) ==> sell(i=0)
        //sell: 0,1, 2  ==> buy(i=1)
        //buy:  0,1,2,3 ==> sell(i=1)
        //sell: 0,1,2,3  ==> buy(i=2)
        //
        dpBuy[0] = -1 * prices[0];
        dpBuy[1] = -1 * prices[1];
        dpSell[1] = dpBuy[0] + prices[1];
        rs = Math.max(rs, Math.max(dpBuy[0], Math.max(dpBuy[1], dpSell[1])));

//        for(int i=0;i<=2;i++){
//            System.out.printf("%s %s,", dpBuy[i], dpSell[i]);
//        }
//        System.out.println();
        for (int i = 2; i < n; i++) {
            dpSell[2] = Math.max(dpBuy[1], dpBuy[0]) + prices[i];
            rs = Math.max(rs, dpSell[2]);
            dpBuy[2] = dpSell[0] - prices[i];
            rs = Math.max(rs, dpBuy[2]);
            //Shift array
            shiftArray(dpBuy);
            shiftArray(dpSell);
//            for(int j=0;j<=2;j++){
//                System.out.printf("%s %s,", dpBuy[j], dpSell[j]);
//            }
//            System.out.println();
        }
//        for(int[] e: dp){
//            System.out.printf("%s %s, ", e[0], e[1]);
//        }
//        System.out.println();
        return rs;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- You are given an array prices where prices[i] is the price of (a given stock) on the (ith day).
        //- You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
        //  + After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
        //  -> 1 ngày cooldown.
        //* Find the maximum profit you can achieve
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= prices.length <= 5000
        //0 <= prices[i] <= 1000
        //
        //- Brainstorm
        //Ex:
        //Example 1:
        //Input: prices = [1,2,3,0,2]
        //Output: 3
        //Explanation: transactions = [buy, sell, cooldown, buy, sell]
        //rs = -1 + 2 -0 + 2 = 3
        //- 1 phần tử có thể sell/ buy
        //  + Nếu buy rồi --> Phải sell rồi mới buy tiếp được
        //  + Nếu sell --> Cần chờ 1 ngày thì mới buy lại được.
        //
        //- dp[i]: là gì
        //- Giả sử dp[i] là max cost có thể lấy được nếu sell at (index=i)
        //- dp[j] --> Tính dp[i] ntn?
        //
        //Ex:
        //  + dp[i] = max all( dp[j : (i-1 -> 0) ]
        //- Giả sử xét i:
        //  dp[j], j+1,...,dp[i] ==> Nếu mà xét tiếp j thì complexity > O(n^2)
        //
        //- Phần cool down có ý nghĩa gì?
        //  + Nó liên quan đến việc (sell + buy + cool down + buy)
        //- Ta quy định rằng việc (sell và buy) là độc lập với nhau
        //+ dp[i][0] : Là cost khi tính cả việc buy index=i
        //+ dp[i][1] : Là cost khi tính cả việc sell index=i
        //
        //- Formulas:
        //+ dp[i][0] = dp[j][1] - arr[i] : (j: 0 -> i-2)
        //+ dp[i][1] = dp[j][0] + arr[i] : (j: 0 -> i-1)
        //
        //1.2, Optimization
        //* Time có thể tối ưu xuống O(n) được.
        //- Ta chỉ cần gía trị :
        //  + Tại buy : cần max nhất sell --> i-2
        //  + Tại sell : cần max nhất buy --> i-1
        //-> Time : Decreased from O(n^2) -> O(n)
        //* Space ta cũng có thể tối ưu xuống O(1) được:
        //-
        //
//        int[] prices = {1,2,3,0,2};
        //Sell thì phải có gì đó đế sell chứ không phải muốn là sell được.
        //      1 | 2 | 3        | 0 | 2
        //Buy: -1 |-2 |-3 (0-3)  | 1 | 0 (2 - 2)
        //Sell: 0 | 1 | 2 (-1+3) |-1 | 3
//        int[] prices = {1};
//        int[] prices = {};
        //
        //        int[] prices = {2,1};
//        int[] prices = {1,2,4};
        //      1  |  2  | 4
        //Buy: -1  | -2  | -4
        //Sell: 0  |  1  | 3
        //
        //- Ta có thể shift reassign cho các element
        //Ex:
        //+ arr[i]=Max(arr[i+1], arr[i])
        //+ Và ta luôn xét arr[2] theo arr[0]/arr[1]
        //  + dpSell[2]=Math.max(dpBuy[1], dpBuy[0])+prices[i];
        //  + dpBuy[2]=dpSell[0]-prices[i];
        //
        //1.3, Complexity
        //- Space : O(constant)
        //- Time : O(n)
        //
        //2. State machine
        //2.1, Idea
        //-
        //
        //#Reference:
        //1434. Number of Ways to Wear Different Hats to Each Other
        //1067. Digit Count in Range
        //2227. Encrypt and Decrypt Strings
        //1605. Find Valid Matrix Given Row and Column Sums
        //2017. Grid Game
        //2617. Minimum Number of Visited Cells in a Grid
        int[] prices = {6, 1, 3, 2, 4, 7};
        //
        System.out.println(maxProfit(prices));
        System.out.println(maxProfitTimeOptimization(prices));
        System.out.println(maxProfitSpaceOptimization(prices));
    }
}
