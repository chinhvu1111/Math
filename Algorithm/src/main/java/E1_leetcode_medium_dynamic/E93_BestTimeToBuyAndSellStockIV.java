package E1_leetcode_medium_dynamic;

public class E93_BestTimeToBuyAndSellStockIV {

    public static int maxProfit(int k, int[] prices) {
        return 1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given prices, prices[i] is the price of given stock on the ith day.
        //- Một lúc chỉ có thể giữ nhiều nhất 1 stock ==> Nhưng có thể buy and sell on the same day.
        //- Given k=2 ta chỉ được thực hiện tối đa k=2 transaction.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= k <= 100
        //1 <= prices.length <= 1000
        //0 <= prices[i] <= 1000
        //- K,n không quá lớn
        //
        //- Brainstorm
        //- Nếu limit k times lần thực hiện transaction thì nó sẽ liên quan đến phần ignore hay không ignore để có thể thực hiện được k transactions
        //
        //
    }
}
