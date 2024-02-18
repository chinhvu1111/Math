package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E112_SolvingQuestionsWithBrainpower {

    public static long[]memo;
    public static long solutionTopDown(int index, int[][] ques){
        if(index>=ques.length){
            return 0;
        }
        if(memo[index]!=-1){
            return memo[index];
        }
        int[] quesInfor=ques[index];
        int curPoint=quesInfor[0];
        //Skip or solve + ignore duplication
        //Time : O(n)
        //Space : O(n)
        //  + Vì cùng lắm là nó đi sâu n
        long solvePoint=curPoint+solutionTopDown(index+quesInfor[1]+1, ques);
        long skipPoint=solutionTopDown(index+1, ques);
        long maxNextPoint=Math.max(solvePoint, skipPoint);
        return memo[index]=maxNextPoint;
    }

    public static long mostPoints(int[][] questions) {
        int n= questions.length;
        memo=new long[n];
        Arrays.fill(memo, -1);
        return solutionTopDown(0, questions);
    }

    public static long mostPointsBottomUp(int[][] questions) {
        int n = questions.length;
        if(n==0){
            return 0;
        }
        long[][] dp=new long[n][2];

        for(int i=n-1;i>=0;i--){
            int[] curInfor = questions[i];
            if(i+curInfor[1]+1<n){
                dp[i][1]=Math.max(dp[i+curInfor[1]+1][0], dp[i+curInfor[1]+1][1])+curInfor[0];
            }else{
                dp[i][1]=curInfor[0];
            }
            if(i+1<n){
                dp[i][0]=Math.max(dp[i+1][0], dp[i+1][1]);
            }
        }
        return Math.max(dp[0][0], dp[0][1]);
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given a 0-indexed 2D integer array questions where
        // questions[i] = [points-i, brainpower-i].
        //- Ta có thể solve/ skip question:
        //  + Solve :
        //      + Get point from the question
        //      + Cannot solve next (brainpower-i) question
        //  + Skip :
        //      + We can solve the next question
        //* Return the maximum points you can earn for the exam.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= questions.length <= 10^5
        //questions[i].length == 2
        //1 <= pointsi, brainpoweri <= 10^5
        //
        //- Brainstorm
        //- Top down trước:
        //- memo[i] là số max point có thể get khi start with (i)
        //
        //- Giả sử
        //+ dp[i][0] là max số points ta lấy được khi solve question(i)
        //+ dp[i][1] là max số points ta lấy được khi skip question(i)
        //
        //- Bottom up approach:
        //Ex:
        //[[3,2],[4,3],[4,4],[2,5]]
        //- Thử xét từ last -> first
        //+ dp[i][0]: Solve point at index=i
        //+ dp[i][1]: Skip point at index=i
        //+ dp[i][1]=max( dp[i+brain_power[i][1]+1][1], dp[i+brain_power[i][1]+1][0] )
        //+ dp[i][0]=max(all prev)
        //
        //1.2, Optimization
        //1.3, Complexity
        //Skip or solve + ignore duplication
        //+ Time : O(n)
        //+ Space : O(n)
        //  + Vì cùng lắm là nó đi sâu n
        int[][] questions = {{3,2},{4,3},{4,4},{2,5}};
        System.out.println(mostPoints(questions));
        System.out.println(mostPointsBottomUp(questions));
        //#Reference:
        //124. Binary Tree Maximum Path Sum
        //1928. Minimum Cost to Reach Destination in Time
        //2002. Maximum Product of the Length of Two Palindromic Subsequences
        //2770. Maximum Number of Jumps to Reach the Last Index
        //609. Find Duplicate File in System
        //1595. Minimum Cost to Connect Two Groups of Points
    }
}
