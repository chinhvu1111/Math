package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E170_MinimumDifficultyOfAJobSchedule {

    public static int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        int[][] dp=new int[n][d+1];
        int[][] maxElement=new int[n][n];

        for(int i=0;i<n;i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for(int i=0;i<n;i++){
            //Ex:
            //1,2,3,7,2,3
            //
            int curMax = jobDifficulty[i];
            for(int j=i;j<n;j++){
                curMax=Math.max(jobDifficulty[j], curMax);
                maxElement[i][j]=curMax;
            }
        }
        for(int i=0;i<n;i++){
            dp[i][1]= maxElement[0][i];
        }
        //Time: O(n*d*n)
        for(int i=0;i<n;i++){
            for(int h=2;h<=d;h++){
                if(i+1<h){
                    continue;
                }
                int curRs=Integer.MAX_VALUE;
                for(int j=i-1;j>=0;j--){
                    if(dp[j][h-1]!=Integer.MAX_VALUE){
                        curRs=Math.min(curRs, dp[j][h-1]+maxElement[j+1][i]);
                    }
                }
                dp[i][h]=curRs;
            }
        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 1; j <= d; j++) {
//                System.out.printf("i: %s, d: %s, val: %s\n", i, j, dp[i][j]);
//            }
//        }
        return dp[n-1][d]==Integer.MAX_VALUE?-1:dp[n-1][d];
    }

    public static void main(String[] args) {
        //** Requirement
        //- You want to schedule (a list of jobs) in (d days). Jobs are dependent
        // (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).
        //- You have to finish (at least) (one task) every day.
        //  + The difficulty of a job schedule is (the sum of difficulties) of (each day) of (the d days).
        //- The difficulty of a day is (the maximum difficulty) of (a job done) on that day.
        //- You are given an integer (array jobDifficulty) and an (integer d).
        //- The (difficulty of the ith job) is jobDifficulty[i].
        //* Return (the minimum difficulty) of a job schedule.
        // If you cannot find a schedule for the jobs return -1.
        //- 1 ngày có thể làm nhiều job
        //  + Độ khó của 1 ngày là max difficulty của all of jobs trong ngày đó
        //  + Độ khó của a job schedule là sum difficulty của all of days
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //1 <= jobDifficulty.length <= 300
        //0 <= jobDifficulty[i] <= 1000
        //1 <= d <= 10
        //  + jobDifficulty.length không quá lớn ==> Time: O(n^2)
        //  + d không quá lớn ==> Time: O(n^2)
        //
        //- Brainstorm
        //Ex:
        //Input: jobDifficulty = [6,5,4,3,2,1], d = 2
        //Output: 7
        //Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
        //Second day you can finish the last job, total difficulty = 1.
        //The difficulty of the schedule = 6 + 1 = 7
        //
        //- dp[i][x]:
        //  + Min nhất độ khó khi (xét đến i) với (số day = x)
        //- Xét đến (i) nếu đã có d=x:
        //  + Sẽ được tính theo d=(x-1) của tất cả các element phía trước
        //
        //
//        int[] jobDifficulty = {6,5,4,3,2,1};
//        int d = 2;
//        int[] jobDifficulty = {1,1,1};
//        int d = 3;
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n^2+n*d)
        //- Time: O(n^2*d)
        //
        int[] jobDifficulty = {11,111,22,222,33,333,44,444};
        int d = 6;
        System.out.println(minDifficulty(jobDifficulty, d));
        //#Reference:
        //2611. Mice and Cheese
        //927. Three Equal Parts
        //1620. Coordinate With Maximum Network Quality
    }
}
