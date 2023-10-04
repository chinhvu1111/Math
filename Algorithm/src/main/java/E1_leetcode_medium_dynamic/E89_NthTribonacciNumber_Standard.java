package E1_leetcode_medium_dynamic;

import java.util.HashMap;
import java.util.Map;

public class E89_NthTribonacciNumber_Standard {

    private Map<Integer, Integer> dp = new HashMap(){{
        put(0, 0);
        put(1, 1);
        put(2, 1);
    }};

    private int dfs(int i) {
        if (dp.containsKey(i)) {
            return dp.get(i);
        }

        int answer = dfs(i - 1) + dfs(i - 2) + dfs(i - 3);
        dp.put(i, answer);
        return answer;
    }

    public static int tribonacciTopDown(int n) {
        return tribonacciTopDown(n);
    }

    public static int tribonacciBottomUp(int n) {
        int[] dp=new int[n+1];

        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(n==2){
            return 1;
        }
        dp[0]=0;
        dp[1]=1;
        dp[2]=1;
        for(int i=3;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2]+dp[i-3];
        }
        return dp[n];
    }

    public static int tribonacciOptimization(int n) {
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(n==2){
            return 1;
        }
        int firstPrevElement=0;
        int secondPrevElement=1;
        int thirdPrevElement=1;

        for(int i=3;i<=n;i++){
            int tmp=thirdPrevElement;
            thirdPrevElement=firstPrevElement+secondPrevElement+thirdPrevElement;
            firstPrevElement=secondPrevElement;
            secondPrevElement=tmp;
        }
        return thirdPrevElement;
    }

    public static void main(String[] args) {
        //- Top-down dynamic programming:
        //+ Recursion (From n-1 to 0)
        //
        //- Bottom-up dynamic programming:
        //+ Recursive (From i=0 to n-1)
        int n=4;
        //Normal method
        System.out.println(tribonacciBottomUp(n));
        //Using three variables rather than the array
        System.out.println(tribonacciOptimization(n));
        //Using top down method
        System.out.println(tribonacciTopDown(n));
        //Using bottom up method
        System.out.println(tribonacciBottomUp(n));
    }
}
