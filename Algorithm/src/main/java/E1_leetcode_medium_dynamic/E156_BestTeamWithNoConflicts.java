package E1_leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class E156_BestTeamWithNoConflicts {

    public static int bestTeamScore(int[] scores, int[] ages) {
        List<int[]> scoreAges=new ArrayList<>();
        int n=scores.length;

        for(int i=0;i<n;i++){
            scoreAges.add(new int[]{scores[i], ages[i]});
        }
        scoreAges.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]){
                    return o1[0]-o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        int[] dp=new int[n];
        int rs=scoreAges.get(0)[0];
        dp[0]=scoreAges.get(0)[0];

        for(int i=1;i<n;i++){
            int curSum=0;

            for(int j=i-1;j>=0;j--){
                if(scoreAges.get(i)[1] == scoreAges.get(j)[1] || (scoreAges.get(j)[1] < scoreAges.get(i)[1] && scoreAges.get(j)[0] <= scoreAges.get(i)[0])){
                    curSum=Math.max(curSum, dp[j]);
                }
            }
            dp[i]=curSum+scoreAges.get(i)[0];
            rs=Math.max(rs, dp[i]);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are the manager of a basketball team.
        //For the upcoming tournament, you want to choose the team with (the highest overall score).
        //The score of the team is (the sum of scores of all the players) in the team.
        //- However, the basketball team is not allowed to have conflicts.
        //  + A conflict exists if (a younger player) has (a strictly higher score) than an (older player).
        //  + A conflict does (not occur) between players of the (same age).
        //- Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player,
        // respectively,
        //* Return (the highest overall score) of (all) (possible basketball teams).
        //Example 1:
        //Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
        //Output: 34
        //Explanation: You can choose all the players.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= scores.length, ages.length <= 1000
        //scores.length == ages.length
        //1 <= scores[i] <= 10^6
        //1 <= ages[i] <= 1000
        //
        //- Brainstorm
        //Example 2:
        //Input: scores = [4,5,6,5], ages = [2,1,2,1]
        //Output: 16
        //Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the same age.
        //
        //- Sort theo ages trước
        //scores = [4,5,6,5], ages = [2,1,2,1]
        //=> sort
        //scores = [5,5,4,6], ages = [1,1,2,2]
        //  + Sort theo ages
        //- dp[i] : là max sum score kết thúc tại (i)
        //  + dp[i] = max(dp[i-k)
        //      + i-k<k ==> Tức là nhỏ tuổi hơn
        //      + scores[i-k]<=scores[i] thì mới xét.
        //- Special cases:
        //+ Sort theo ages chưa đủ:
        //  + Ta ưu tiên những thằng score lớn hơn ở đằng sau ==> score tăng dần với (age bằng nhau)/
        //* Note:
        //- Sai predicate này vài lần:
        //+ A conflict exists if (a younger player) has (a strictly higher score) than an (older player).
        //==> younger has score > older score ==> Conflicts
        //  ==> Có thể (<=)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is the number of people
        //- Space: O(N)
        //- Time: O(N^2+N*log(N))
        //
        //#Reference:
        //2172. Maximum AND Sum of Array
//        int[] scores = {1,3,5,10,15}, ages = {1,2,3,4,5};
//        int[] scores = {319776,611683,835240,602298,430007,574,142444,858606,734364,896074}, ages = {1,1,1,1,1,1,1,1,1,1};
        int[] scores = {1,1,1,1,1,1,1,1,1,1}, ages = {811,364,124,873,790,656,581,446,885,134};
//        int[] scores = {1,3,7,3,2,4,10,7,5}, ages = {4,5,2,1,1,2,4,1,4};
        System.out.println(bestTeamScore(scores, ages));
    }
}
