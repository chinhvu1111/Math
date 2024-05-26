package contest;

import java.util.HashMap;
import java.util.HashSet;

public class E110_FindTheNumberOfDistinctColorsAmongTheBalls {

    public static int[] queryResults(int limit, int[][] queries) {
        HashMap<Integer, Integer> ballColors=new HashMap<>();
        HashMap<Integer, HashSet<Integer>> colorToBalls=new HashMap<>();
        int rs=0;
        int i=0;
        int[] ans=new int[queries.length];

        for(int[] q: queries){
            if(ballColors.containsKey(q[0])){
                int curColor = ballColors.get(q[0]);
                //If no change the color
                if(curColor==q[1]){
                    ans[i]=rs;
                    i++;
                    continue;
                }
                //Delete the ball having the prev color
                HashSet<Integer> balls= colorToBalls.getOrDefault(curColor, new HashSet<>());
                if(balls.size()==1){
                    //Reset for current color
                    colorToBalls.remove(curColor);
                    rs--;
                }else{
                    balls.remove(q[0]);
                    colorToBalls.put(curColor, balls);
                }
//                ballColors.put(q[0], q[1]);
            }
            if(colorToBalls.containsKey(q[1])){
                HashSet<Integer> prevColors=colorToBalls.getOrDefault(q[1], new HashSet<>());

                if(prevColors.isEmpty()){
                    rs++;
                }
                prevColors.add(q[0]);
                //Reset for new color
                colorToBalls.put(q[1], prevColors);
            }else{
                HashSet<Integer> balls=new HashSet<>();
                balls.add(q[0]);
                colorToBalls.put(q[1], balls);
                rs++;
            }
            ballColors.put(q[0], q[1]);
            ans[i]=rs;
            i++;
        }
//        for (int j = 0; j < queries.length; j++) {
//            System.out.println(ans[j]);
//        }
        return ans;
    }
    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer limit and a 2D array queries of size n x 2.
        //- There are (limit + 1) balls with (distinct labels) in the range [0, limit].
        //- Initially, (all balls) are uncolored.
        //  + For (every query) in queries that is of the form [x, y], you mark (ball x) with the (color y).
        //  + After (each query), you need to find (the number of distinct colors) among (the balls).
        //* Return an array result of length n, where result[i] denotes (the number of distinct colors) after (ith query).
        //* Note that when answering a query, (lack of a color) will not be considered as (a color).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        // 1 <= limit <= 10^9
        //1 <= n == queries.length <= 10^5
        //queries[i].length == 2
        //0 <= queries[i][0] <= limit
        //1 <= queries[i][1] <= 10^9
        //==> Limit khá to (nên không dùng array)
        //
        //- Brainstorm
        //- Key point ở đây là có update color của ball
        //Ex:
        //Example 1:
        //Input: limit = 4, queries = [[1,4],[2,5],[1,3],[3,4]]
        //Output: [1,2,2,3]
        //+ 1 -> 4 : rs=1
        //+ 2 -> 5 : rs=2
        //+ 1 -> 3 ==> rs=2
        //  + 1 đã có 4 rồi:
        //      + color = 4 chỉ có 1 có
        //          + rs--
        //      + color = 4 có nhiều hơn 1 có.
        //      ==> Cả 2 đều remove 1 có color 4
        //
//        int limit = 4;
//        int[][] queries = {{0,1},{1,2},{2,2},{3,4},{4,5}};
//        int limit = 1;
//        int[][] queries = {{0,1},{0,4},{0,4},{0,1},{1,2}};
        // 0 -> 1
        // 0 -> 4
        // 0 -> 4
        // 0 -> 1
        // 1 -> 2
        //1,1,1,2,0
        //1,1,1,1,2
        int limit = 1;
        int[][] queries = {{0,1},{0,4},{0,4},{0,1},{1,2}};
        queryResults(limit, queries);
    }
}
