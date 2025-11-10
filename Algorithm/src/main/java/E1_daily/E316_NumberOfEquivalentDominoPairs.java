package E1_daily;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class E316_NumberOfEquivalentDominoPairs {

    public static int numEquivDominoPairs(int[][] dominoes) {
        HashMap<Pair<Integer, Integer>, Integer> mapCount=new HashMap();

        for(int[] e: dominoes){
            int x=e[0]>e[1]?e[0]:e[1];
            int y=e[0]<=e[1]?e[0]:e[1];
            Pair<Integer, Integer> curPair=new Pair<>(x, y);
            mapCount.put(curPair, mapCount.getOrDefault(curPair, 0)+1);
        }
        int rs=0;
        for (Map.Entry<Pair<Integer, Integer>, Integer> e: mapCount.entrySet()){
            rs+=e.getValue()*(e.getValue()-1)/2;
        }
        return rs;
    }

    public static int numEquivDominoPairsRefer(int[][] dominoes) {
        int[] num = new int[100];
        int ret = 0;
        for (int[] domino : dominoes) {
            int val = domino[0] < domino[1]
                    ? domino[0] * 10 + domino[1]
                    : domino[1] * 10 + domino[0];
            ret += num[val];
            num[val]++;
        }
        return ret;
    }

    public static void main(String[] args) {
        //** Requirement
        //-
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= dominoes.length <= 4 * 10^4
        //dominoes[i].length == 2
        //1 <= dominoes[i][j] <= 9
        //  + dominoes.length <= 4 * 10^4 ==> Time: O(n*k)
        //
        //* Brainstorm:
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //3385. Minimum Time to Break Locks II
        //2442. Count Number of Distinct Integers After Reverse Operations
        //1983. Widest Pair of Indices With Equal Range Sum
        //
        int[][] dominoes = {{1,2},{2,1},{3,4},{5,6}};
        System.out.println(numEquivDominoPairs(dominoes));
        System.out.println(numEquivDominoPairsRefer(dominoes));
    }
}
