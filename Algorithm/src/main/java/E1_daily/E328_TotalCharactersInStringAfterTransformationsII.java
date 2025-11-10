package E1_daily;

public class E328_TotalCharactersInStringAfterTransformationsII {
    public static void main(String[] args) {
        //** Requirement
        //- Mario drives on a two-lane freeway with coins every mile.
        // You are given two integer arrays, lane1 and lane2,
        // where the value at (the ith index) represents (the number of coins)
        // he gains or loses in the ith mile in that lane.
        //  + If Mario is in lane 1 at mile i and lane1[i] > 0, Mario gains lane1[i] coins.
        //  + If Mario is in lane 1 at mile i and lane1[i] < 0, Mario pays a toll and loses abs(lane1[i]) coins.
        //  + The same rules apply for lane2.
        //- Mario can enter the freeway anywhere and exit anytime after traveling at least one mile.
        //- Mario always enters the freeway on lane 1 but can switch lanes at most 2 times.
        //-  A lane switch is when Mario goes from lane 1 to lane 2 or vice versa.
        //* Return the maximum number of coins Mario can earn after performing at most 2 lane switches.
        //* Note:
        //- Mario can switch lanes immediately upon entering or just before exiting the freeway.
        //
        //Example 1:
        //
        //Input: lane1 = [1,-2,-10,3], lane2 = [-5,10,0,1]
        //
        //Output: 14
        //
        //Explanation:
        //
        //Mario drives the first mile on lane 1.
        //He then changes to lane 2 and drives for two miles.
        //He changes back to lane 1 for the last mile.
        //Mario collects 1 + 10 + 0 + 3 = 14 coins.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= lane1.length == lane2.length <= 10^5
        //-10^9 <= lane1[i], lane2[i] <= 10^9
        //  + Time: O(n*k)
        //
        //* Brainstorm:
        //- Using dynamic programing
        //- dp[i][2]:
        //  + dp[i][0]: Max coin Mario can get if he is in the lane 1
        //  + dp[i][1]: Max coin Mario can get if he is in the lane 2
        //- We can switch 2 times:
        //  + We add more dimension
        //- dp[i][2][2]:
        //  + dp[i][0] = Max(dp[i-1][0][0/1]+lane1[i], dp[i-1][1][0/1]+lane2[i])
        //  ...
        //
        //1.1, Case
        //int[] lane1 = {-9,10,4};
//        int[] lane2 = {10,-10,5};
        //Expected: 24
        //==> For the first time, Mario can switch immediately
        //  + Có thể switch ngay lập tức
//        int[] lane1 = {-10};
//        int[] lane2 = {-2};
        //Expected: -2 ==> Vì phải đi ít nhất 1 mile
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
    }
}