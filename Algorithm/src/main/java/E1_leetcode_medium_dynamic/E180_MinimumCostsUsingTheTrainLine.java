package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E180_MinimumCostsUsingTheTrainLine {

    public static long[] minimumCosts(int[] regular, int[] express, int expressCost) {
        int n=regular.length;
        long[][] dp=new long[n+1][2];
        long[] rs=new long[n];

        dp[0][0]=Integer.MAX_VALUE;

        for(int i=1;i<=n;i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for(int i=0;i<n;i++){
            //- dp[i][0] = Max(dp[i-1][0], dp[i-1][1]+expressCost)+express[i]
            //- dp[i][1] = Max(dp[i-1][1], dp[i-1][0])+regular[i]
            dp[i+1][1]=Math.min(dp[i][1], dp[i][0])+regular[i];
            dp[i+1][0]=Math.min(dp[i][0], dp[i][1]+expressCost)+express[i];
//            dp[i+1][0]=Math.min(dp[i+1][1]+expressCost, dp[i+1][0]);
            rs[i]=Math.min(dp[i+1][0], dp[i+1][1]);
        }
        return rs;
    }

    public static long[] minimumCostsSpaceOptimization(int[] regular, int[] express, int expressCost) {
        int n=regular.length;
        long[] rs=new long[n];
        long minPrevRegularPath=0, minCurRegularPath;
        long minPrevExpressPath=Long.MAX_VALUE, minCurExpressPath;

        for(int i=0;i<n;i++){
            //- dp[i][0] = Max(dp[i-1][0], dp[i-1][1]+expressCost)+express[i]
            //- dp[i][1] = Max(dp[i-1][1], dp[i-1][0])+regular[i]
            minCurRegularPath=Math.min(minPrevRegularPath, minPrevExpressPath)+regular[i];
            minCurExpressPath=Math.min(minPrevExpressPath, minPrevRegularPath+expressCost)+express[i];
//            dp[i+1][0]=Math.min(dp[i+1][1]+expressCost, dp[i+1][0]);
            rs[i]=Math.min(minCurRegularPath, minCurExpressPath);
            minPrevRegularPath=minCurRegularPath;
            minPrevExpressPath=minCurExpressPath;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- (A train line) going through a city has (two routes), the (regular route) and the (express route).
        //- (Both routes) go through the (same) (n + 1 stops) labeled from (0 to n).
        //- Initially,
        //  + you start on (the regular route) at (stop 0).
        //- You are given (two 1-indexed integer arrays) regular and express, both of length n.
        //- regular[i] describes:
        //  + the cost it takes to go from (stop i - 1) to (stop i) using (the regular route),
        //- express[i] describes:
        //  + the cost it takes to go from (stop i - 1) to (stop i) using (the express route).
        //- You are also given an integer expressCost which represents the cost to transfer from the regular route to the express route.
        //* Note that:
        //  + There is (no cost) to transfer from (the express route) back to (the regular route).
        //  + You pay (expressCost) every time you transfer from the (regular route) to the (express route).
        //  + There is (no extra cost) to stay on the (express route).
        //* Return (a 1-indexed array costs) of length n, where costs[i] is (the minimum cost) to reach (stop i) from (stop 0).
        //* Note that (a stop) can be counted as reached from (either route).
        //
        //Example 1:
        //
        //Input:
        //+ regular = [1,6,9,5],
        //+ express = [5,2,3,10], expressCost = 8
        //Output: [1,7,14,19]
        //Explanation: The diagram above shows how to reach stop 4 from stop 0 with minimum cost.
        //- Take the regular route from stop 0 to stop 1, costing 1.
        //- Take the express route from stop 1 to stop 2, costing 8 + 2 = 10.
        //- Take the express route from stop 2 to stop 3, costing 3.
        //- Take the regular route from stop 3 to stop 4, costing 5.
        //The total cost is 1 + 10 + 3 + 5 = 19.
        //Note that a different route could be taken to reach the other stops with minimum cost.
        //- We start out of range of the path:
        //  + 0 -> 1 = regular[1]
        //  + regular[i] -> express[i] = expressCost
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //n == regular.length == express.length
        //1 <= n <= 10^5
        //1 <= regular[i], express[i], expressCost <= 10^5
        //
        //- Brainstorm
        //- Chú ý điểm:
        //  + Start from regular ==> dp[0][0] == MAX
        //- dp[i][0]: Cost to reach to (ith position) with the (express) path
        //- dp[i][1]: Cost to reach to (ith position) with the (regular) path
        //
        //- dp[i][0] = Max(dp[i-1][0], dp[i-1][1]+expressCost)+express[i]
        //- dp[i][1] = Max(dp[i-1][1], dp[i-1][0])+regular[i]
        //
        int[] regular = {1,6,9,5}, express = {5,2,3,10};
        int expressCost = 8;
//        long[] rs = minimumCosts(regular, express, expressCost);
        long[] rs = minimumCostsSpaceOptimization(regular, express, expressCost);

        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s,", rs[i]);
        }
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space: O(n) --> O(1)
        //  + We only care the previous value
        //- Time: O(n)
        //
        //#Reference:
        //815. Bus Routes
        //1184. Distance Between Bus Stops
    }
}
