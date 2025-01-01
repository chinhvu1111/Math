package E1_daily;

public class E215_MostExpensiveItemThatCanNotBeBought {

    public static int mostExpensiveItem(int primeOne, int primeTwo) {
        boolean[] dp=new boolean[primeOne*primeTwo+1];
        int max=primeOne*primeTwo;
        dp[primeOne]=true;
        dp[primeTwo]=true;
        int rs=Integer.MIN_VALUE;

        for(int i=1;i<=max;i++){
            if(i>=primeOne){
                dp[i]=dp[i]|dp[i-primeOne];
            }
            if(i>=primeTwo){
                dp[i]=dp[i]|dp[i-primeTwo];
            }
            if(!dp[i]){
                rs=i;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two (distinct prime numbers) primeOne and primeTwo.
        //- Alice and Bob are visiting a market.
        //- The market has (an infinite number of items), for (any positive integer x) there exists an item (whose price is x).
        //- Alice wants to buy some items from the market to (gift to Bob).
        //- She has (an infinite number of coins) in the denomination (primeOne and primeTwo).
        //- She wants to know (the most expensive item) she can not buy to gift to Bob.
        //* Return (the price of the most expensive item) which (Alice can not gift to Bob).
        //
        //Example 1:
        //
        //Input: primeOne = 2, primeTwo = 5
        //Output: 3
        //Explanation:
        // The prices of items which cannot be bought are [1,3].
        // It can be shown that all items with a price greater than 3 can be bought using a combination of coins of denominations 2 and 5.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 < primeOne, primeTwo < 10^4
        //primeOne, primeTwo are prime numbers.
        //primeOne * primeTwo < 10^5
        //  + primeNumber < 10^4 ==> Time: O(n*k)
        //  + primeOne * primeTwo < 10^5 ==> Time: O(primeOne * primeTwo)
        //
        //- Brainstorm
        //- Given a and b
        //- What is the price we can not reach?
        //- Check the x is the combination of (primeOne) or (primeTwo)
        //  ==> Using binary search but we don't have (any limitation)
        //- Upper bound for that:
        //Ex:
        //primeOne = 5, primeTwo = 7
        //- number=37
        //37 = 5*x+7*y
        //  + We can check that using dynamic programming
        //- For 1 -> 10^5:
        //   For coin in coins:
        //      + dp[i]=dp[i-coin]
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(primeOne*primeTwo)
        //- Time: O(primeOne*primeTwo)
        //
        int primeOne = 2, primeTwo = 5;
        System.out.println(mostExpensiveItem(primeOne, primeTwo));
        //#Reference:
        //2952. Minimum Number of Coins to be Added
    }
}
