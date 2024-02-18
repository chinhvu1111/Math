package E1_leetcode_medium_dynamic;

public class E101_ClimbingStairs {
    public static int climbStairs(int n) {
        int[] dp=new int[n+1];
        dp[1]=1;
        dp[0]=1;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    public static int climbStairsOptimization(int n) {
        int numPrevOneSteps=1;
        int numPrevTwoSteps=1;
        int numCurStep=numPrevOneSteps;
        //2 = [1] + [0]
        //3 = 2 + 1
        for(int i=2;i<=n;i++){
            numCurStep=numPrevOneSteps+numPrevTwoSteps;
            numPrevTwoSteps=numPrevOneSteps;
            numPrevOneSteps=numCurStep;
        }
        return numCurStep;
    }
    public static void main(String[] args) {
        // Đề bài:
        //- Climbing the staircase, It takes n steps to reach the top.
        //- Each time we will either climb 1 or 2 steps.
        //* Return how many distinct ways we can climb to the top
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= n <= 45
        //
        //- Brainstorm
        //- Bài này đơn giản là tổng thôi
        //1.2, Optimization
        //- Cái này thì chỉ có thể tối ưu space được từ O(n) -> O(1)
        //1.3, Complexity:
        //- N is the length of the staircase
        //- Space : O(1)
        //- Time : O(n)
        //
        //#Reference:
        //509. Fibonacci Number
        //2244. Minimum Rounds to Complete All Tasks
        //2320. Count Number of Ways to Place Houses
        //
        int n=2;
        System.out.println(climbStairs(n));
        System.out.println(climbStairsOptimization(n));
        //
    }
}
