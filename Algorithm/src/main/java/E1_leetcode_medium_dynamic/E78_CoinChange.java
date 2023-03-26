package E1_leetcode_medium_dynamic;

public class E78_CoinChange {

    public static int coinChange(int[] coins, int amount) {
        int n=coins.length;
        int dp[]=new int[amount+1];

        for(int i=1;i<=amount;i++){
            int minValue=Integer.MAX_VALUE;

            for(int j=0;j<n;j++){
                if(i>=coins[j]&&dp[i-coins[j]]!=Integer.MAX_VALUE){
                    minValue=Math.min(minValue, dp[i-coins[j]]+1);
                }
            }
            dp[i]=minValue;
        }
        if(dp[amount]<0||dp[amount]==Integer.MAX_VALUE){
            return -1;
        }
        return dp[amount];
    }

    public static void main(String[] args) {
//        int amount=11;
//        int[] coins=new int[]{1,2,5};

//        int amount=3;
//        int[] coins=new int[]{2};

//        int amount=1;
//        int[] coins=new int[]{0};

//        int amount=1;
//        int[] coins=new int[]{2};

        int amount=6249;
        int[] coins=new int[]{186,419,83,408};
        //[186,419,83,408]
        //6249
        System.out.println(coinChange(coins, amount));
    }

}
