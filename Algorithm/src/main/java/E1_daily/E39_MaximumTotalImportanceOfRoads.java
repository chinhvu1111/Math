package E1_daily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class E39_MaximumTotalImportanceOfRoads {

    public static long maximumImportance(int n, int[][] roads) {
        int[] weight=new int[n+1];

        for(int[] r:roads){
            weight[r[0]]++;
            weight[r[1]]++;
        }
        List<Integer> listWeight=new ArrayList<>();

        for(int i=0;i<=n;i++){
            if(weight[i]!=0){
                listWeight.add(weight[i]);
            }
        }
//        System.out.println(listWeight);
        long x=n;
        long rs=0;
        listWeight.sort(Collections.reverseOrder());

        for(int e: listWeight){
            rs+=x*e;
            x--;
        }
        return rs;
    }

    public static long maximumImportanceOptimization(int n, int[][] roads) {
        int[] weight=new int[n+1];
        int[] countingSort=new int[n+1];

        for(int[] r:roads){
            weight[r[0]]++;
            weight[r[1]]++;
        }

        for(int i=0;i<=n;i++){
            if(weight[i]!=0){
                countingSort[weight[i]]++;
            }
        }
//        System.out.println(listWeight);
        long x=n;
        long rs=0;
        for(int i=n;i>=0;i--){
            if(countingSort[i]!=0){
                //x=5
                //countSort[i]=4
                //(5+4+3)*i
                //=12*i
                //5*(5+1)/2 - 2*(2+1)/2
                //= 15 - 3 = 12
                long end=x;
                long start=x-countingSort[i];
                rs+=(end*(end+1)/2 - start*(start+1)/2)*i;
                x-=countingSort[i];
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer n denoting (the number of cities in a country).
        //- The cities are numbered from (0 to n - 1).
        //- You are also given a 2D integer array roads where roads[i] = [ai, bi]
        // denotes that there exists a bidirectional road connecting (cities ai and bi).
        //- You need to assign (each city) with an integer value from (1 to n),
        // where each value can only be (used once).
        //- (The importance of a road) is then defined as (the sum of the values) of (the two cities) it connects.
        //* Return (the maximum total importance of all roads possible) after (assigning the values) optimally.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //2 <= n <= 5 * 10^4
        //1 <= roads.length <= 5 * 10^4
        //roads[i].length == 2
        //0 <= ai, bi <= n - 1
        //ai != bi
        //There are no duplicate roads.
        //
        //- Brainstorm
        //Input: n = 5, roads = [[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]
        //Output: 43
        //Explanation: The figure above shows the country and the assigned values of [2,4,5,3,1].
        //- The road (0,1) has an importance of 2 + 4 = 6.
        //- The road (1,2) has an importance of 4 + 5 = 9.
        //- The road (2,3) has an importance of 5 + 3 = 8.
        //- The road (0,2) has an importance of 2 + 5 = 7.
        //- The road (1,3) has an importance of 4 + 3 = 7.
        //- The road (2,4) has an importance of 5 + 1 = 6.
        //The total importance of all roads is 6 + 9 + 8 + 7 + 7 + 6 = 43.
        //It can be shown that we cannot obtain a greater total importance than 43.
        //
        //1.1, Optimization
        //- Ta có thể tối ưu time được không ==> Dùng counting sort
        //  + Để giảm thời gian từ O(n*log(n)) -> O(n)
        //
        //1.2, Complexity
        //
        //
        int[][] roads = {{0,1},{1,2},{2,3},{0,2},{1,3},{2,4}};
        int n=5;
        System.out.println(maximumImportance(n, roads));
        System.out.println(maximumImportanceOptimization(n, roads));
        //
        //#Reference:
        //1754. Largest Merge Of Two Strings
        //807. Max Increase to Keep City Skyline
        //2203. Minimum Weighted Subgraph With the Required Paths
        //274. H-Index
        //2948. Make Lexicographically Smallest Array by Swapping Elements
        //1502. Can Make Arithmetic Progression From Sequence
    }
}
