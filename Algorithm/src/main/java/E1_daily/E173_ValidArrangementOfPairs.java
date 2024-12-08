package E1_daily;

import javafx.util.Pair;

import java.util.*;

public class E173_ValidArrangementOfPairs {

    public static List<Integer> solution(int index, int[] nodes,
                                HashMap<Integer, List<int[]>> valToPairs, List<Integer> indices,
                                HashSet<Pair<Integer, Integer>> visited, int n){
        if(index==n-1){
            return indices;
        }
        List<int[]> adj=valToPairs.get(nodes[1]);

        if(adj==null){
            return new ArrayList<>();
        }
        List<Integer> curRs=new ArrayList<>();
        for(int[] e: adj){
            Pair<Integer, Integer> nextNode = new Pair<>(e[0], e[1]);
            if(visited.contains(nextNode)){
                continue;
            }
            visited.add(nextNode);
            indices.add(e[2]);
            curRs=solution(index+1, e, valToPairs, indices, visited, n);
            if(curRs.size()!=0){
                break;
            }
            indices.remove(indices.size()-1);
            visited.remove(nextNode);
        }
        return curRs;
    }

    public static int[][] validArrangement(int[][] pairs) {
        int n = pairs.length;
        HashMap<Integer, List<int[]>> valToPairs=new HashMap<>();

        for(int i=0;i<n;i++){
            List<int[]> curList = valToPairs.getOrDefault(pairs[i][0], new ArrayList<>());
            curList.add(new int[]{pairs[i][0], pairs[i][1], i});
            valToPairs.put(pairs[i][0], curList);
        }
        List<Integer> s=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            HashSet<Pair<Integer, Integer>> visited=new HashSet<>();
            Pair<Integer, Integer> nextNode = new Pair<>(pairs[i][0], pairs[i][1]);
            visited.add(nextNode);
            List<Integer> curIndices=new ArrayList<>();
            curIndices.add(i);
            s= solution(0, pairs[i], valToPairs, curIndices, visited, n);
            if(!s.isEmpty()){
                break;
            }
        }
        int[][] rs=new int[n][2];

        for(int i=0;i<s.size();i++){
            rs[i]=pairs[s.get(i)];
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed 2D integer array pairs) where pairs[i] = [start-i, end-i].
        //- An arrangement of pairs is valid
        //  + if for (every index i) where (1 <= i < pairs.length), we have end(i-1) == start(i).
        //* Return (any valid arrangement of pairs).
        //- Note: The inputs will be generated such that there exists (a valid arrangement of pairs.)
        //
        //Example 1:
        //Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
        //Output: [[11,9],[9,4],[4,5],[5,1]]
        //Explanation:
        //This is a valid arrangement since end(i-1) always equals starti.
        //end0 = 9 == 9 = start1
        //end1 = 4 == 4 = start2
        //end2 = 5 == 5 = start3
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //Constraints:
        //1 <= pairs.length <= 10^5
        //pairs[i].length == 2
        //0 <= start-i, end-i <= 10^9
        //(start-i != end-i)
        //No two pairs are exactly the same.
        //There exists a valid arrangement of pairs.
        //  + Length <= 10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //- [a,b], [b,c]
        //  + end with (x)
        //
        //Ex:
        //Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
        //Output: [[11,9],[9,4],[4,5],[5,1]]
        //[4,5] -> [5,1] -> None
        //[9,4] -> [4,5] -> [5,1]
        //[11,9] -> [9,4] -> [4,5] -> [5,1]
        //
        //Ex:
        //Input: pairs = [[5,1],[4,5],[11,9],[9,4]] + [1,4]
        //Output: [[11,9],[9,4],[4,5],[5,1]]
        //[11,9] -> [9,4] -> [4,5] -> [5,1] -> [1,3]
        //[11,9] -> [9,4] -> [4,1] -> [5,1]
        //- Choose between [a,b], [a,c]
        //[a,b] -> [b,e]
        //[a,c] -> [c,b] -> [b,e]
        //
        //- BFS
        //- HashMap
//        int[][] pairs = {{5,1},{4,5},{11,9},{9,4}};
        int[][] pairs = {{0,8},{11,20},{4,15},{18,17},{17,4},{6,14},{15,0},{13,3},{14,9},{19,13},{3,11},{2,19},{20,2},{9,19},{8,6}};
        int[][] rs= validArrangement(pairs);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s %s\n", rs[i][0], rs[i][1]);
        }
    }
}
