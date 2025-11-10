package E1_daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class E315_MinimumDominoRotationsForEqualRow_tricks {

    public static int minDominoRotationsHashMap(int[] tops, int[] bottoms) {
        int n=bottoms.length;
        HashMap<Integer, Set<Integer>> mapVal=new HashMap<>();
        HashMap<Integer, Set<Integer>> mapValTop=new HashMap<>();
        HashMap<Integer, Set<Integer>> mapValBot=new HashMap<>();

        for(int i=0;i<n;i++){
            Set<Integer> curSet=mapVal.getOrDefault(tops[i], new HashSet<>());
            curSet.add(i);
            mapVal.put(tops[i], curSet);
            Set<Integer> curSet1=mapVal.getOrDefault(bottoms[i], new HashSet<>());
            curSet1.add(i);
            mapVal.put(bottoms[i], curSet1);
        }
        for(int i=0;i<n;i++){
            Set<Integer> curSet=mapValTop.getOrDefault(tops[i], new HashSet<>());
            curSet.add(i);
            mapValTop.put(tops[i], curSet);
            Set<Integer> curSet1=mapValBot.getOrDefault(bottoms[i], new HashSet<>());
            curSet1.add(i);
            mapValBot.put(bottoms[i], curSet1);
        }
        int rs=Integer.MAX_VALUE;
        for (int key: mapVal.keySet()){
            Set<Integer> curCount=mapVal.get(key);
            if(curCount.size()==n){
                Set<Integer> topSet = mapValTop.getOrDefault(key, new HashSet<>());
                Set<Integer> botSet = mapValBot.getOrDefault(key, new HashSet<>());
                Set<Integer> allSet = mapVal.get(key);
                rs=Math.min(rs, Math.min(topSet.size(), allSet.size()-topSet.size()));
                rs=Math.min(rs, Math.min(botSet.size(), allSet.size()-botSet.size()));
            }
        }
        return rs==Integer.MAX_VALUE?-1: rs;
    }

    public static int check(int[] tops, int[] bottoms, int x, int n){
        int rotationTop=0, rotationBot=0;
        for (int i = 0; i < n; i++) {
            if(tops[i]!=x&&bottoms[i]!=x){
                return -1;
            }else if(tops[i]!=x){
                rotationTop++;
            }else if(bottoms[i]!=x){
                rotationBot++;
            }
        }
        return Math.min(rotationTop, rotationBot);
    }

    public static int minDominoRotations(int[] tops, int[] bottoms) {
        int n=bottoms.length;
        int rs = check(tops, bottoms, tops[0], n);
        if(rs!=-1||tops[0]==bottoms[0]){
            return rs;
        }
        rs = check(tops, bottoms, bottoms[0], n);
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith domino.
        // (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
        //- We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.
        //* Return the minimum number of rotations so that all the values in tops are the same,
        // or all the values in bottoms are the same.
        //- If it cannot be done, return -1.
        //
        //Input: tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
        //Output: 2
        //Explanation:
        //The first figure represents the dominoes as given by tops and bottoms: before we do any rotations.
        //If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2,
        // as indicated by the second figure.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= tops.length <= 2 * 10^4
        //bottoms.length == tops.length
        //1 <= tops[i], bottoms[i] <= 6
        //
        //* Brainstorm:
        //
        //Input:
        //tops      = [2,1,2,4,2,2],
        //bottoms   = [5,2,6,2,3,2]
        //- HashMap: value, set<Index>
        //==> Get value with length(set) is max value
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //- Greedy???
        //
        //* Main point:
        //- Because all top or bottom are (the same)
        //  + The number must be tops[0] or bottoms[0]
        //  ==> Otherwise return -1
        //
        //Ex:
        //Input:
        //tops      = [2,1,2,4,2,2],
        //bottoms   = [5,2,6,2,3,2]
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        int[] tops = {2,1,2,4,2,2}, bottoms = {5,2,6,2,3,2};
        System.out.println(minDominoRotationsHashMap(tops, bottoms));
        System.out.println(minDominoRotations(tops, bottoms));
        //#Reference:
        //158. Read N Characters Given read4 II - Call Multiple Times
        //3257. Maximum Value Sum by Placing Three Rooks II
        //2307. Check for Contradictions in Equations
        //
        //3449. Maximize the Minimum Game Score
        //690. Employee Importance
    }
}
