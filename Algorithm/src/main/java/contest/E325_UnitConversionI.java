package contest;

import javafx.util.Pair;

import java.util.*;

public class E325_UnitConversionI {

    public static int[] baseUnitConversions(int[][] conversions) {
        HashMap<Integer, Set<Pair<Integer, Integer>>> graph=new HashMap<>();
        int n=conversions.length;
        int max=0;

        for(int[] convert: conversions){
            Set<Pair<Integer, Integer>> curSet= graph.getOrDefault(convert[0], new HashSet<>());
            curSet.add(new Pair<>(convert[1], convert[2]));
            graph.put(convert[0], curSet);
            Set<Pair<Integer, Integer>> curSet1= graph.getOrDefault(convert[1], new HashSet<>());
            curSet1.add(new Pair<>(convert[0], convert[2]));
            graph.put(convert[1], curSet1);
            max=Math.max(max, Math.max(convert[0], convert[1]));
        }
        Queue<Pair<Integer, Integer>> queue=new LinkedList<>();
        queue.add(new Pair<>(0, 1));
        int mod=1_000_000_007;
        int[] rs=new int[max+1];
        boolean[] visited=new boolean[max+1];
        while (!queue.isEmpty()){
            Pair<Integer, Integer> curNode = queue.poll();
            rs[curNode.getKey()]=curNode.getValue();
            Set<Pair<Integer, Integer>> adj=graph.getOrDefault(curNode.getKey(), new HashSet<>());
            visited[curNode.getKey()]=true;

            for (Pair<Integer, Integer> adjNode: adj){
                if(visited[adjNode.getKey()]){
                    continue;
                }
                Pair<Integer, Integer> nextNode=new Pair<>(adjNode.getKey(), (int)(((long)(adjNode.getValue())*curNode.getValue())%mod));
                queue.add(nextNode);
                visited[adjNode.getKey()]=true;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are n types of units indexed from (0 to n - 1).
        //- You are given a 2D integer array conversions of length n - 1,
        // where conversions[i] = [sourceUniti, targetUniti, conversionFactori].
        //- This indicates that (a single unit of type) sourceUniti is equivalent to conversionFactori units of type targetUniti.
        //* Return an array baseUnitConversion of length n,
        // where baseUnitConversion[i] is the number of units of type i equivalent to a single unit of type 0.
        //- Since the answer may be large, return each baseUnitConversion[i] modulo 10^9 + 7.
        //
        //Input: conversions = [[0,1,2],[1,2,3]]
        //
        //Output: [1,2,6]
        //  + 0 = 1[0]
        //  + 1 = 2[0]
        //  + 2 = 6[0]
        //
        //Explanation:
        //
        //Convert a single unit of type 0 into 2 units of type 1 using conversions[0].
        //Convert a single unit of type 0 into 6 units of type 2 using conversions[0], then conversions[1].
        //
        //* Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= n <= 10^5
        //conversions.length == n - 1
        //0 <= sourceUniti, targetUniti < n
        //1 <= conversionFactori <= 10^9
        //It is guaranteed that unit 0 can be converted into any other unit
        // through a unique combination of conversions without using any conversions in the opposite direction.
        //  + Time: O(n)
        //  + Unique ==> BFS
        //
        //- Brainstorm
        //
        //- BFS
        //- long*int = long
        //
//        int[][] conversions = {{0,1,2},{1,2,3}};
        int[][] conversions = {{0,1,1000000000},{1,2,1000000000}};
        int[] rs = baseUnitConversions(conversions);

        for(int i=0;i< rs.length;i++){
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
        //
    }
}
