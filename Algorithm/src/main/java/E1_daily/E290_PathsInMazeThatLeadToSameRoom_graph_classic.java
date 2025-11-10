package E1_daily;

import java.util.*;

public class E290_PathsInMazeThatLeadToSameRoom_graph_classic {

    public static void solution(
            int firstNode, int prevNode, int node, int depth, HashSet<Integer> nodes,
            HashSet<String> set, HashMap<Integer, List<Integer>> graph){
        //1 -> 2 -> 3
        if(depth==3){
            if(node==firstNode){
                List<Integer> listNode=new ArrayList<>(nodes);
                Collections.sort(listNode);
                StringBuilder curStr=new StringBuilder();
                for(int e: listNode){
                    curStr.append(e);
                    curStr.append("_");
                }
//                System.out.println(firstNode);
                set.add(curStr.toString());
//                System.out.println(curStr);
//                System.out.println(set);
            }
            return;
        }
        List<Integer> adj=graph.get(node);

        if(adj==null){
            return;
        }
        for(int e: adj){
            if(prevNode==e){
                continue;
            }
            nodes.add(e);
            solution(firstNode, node, e, depth+1, nodes, set, graph);
            nodes.remove(e);
        }
    }

    public static int numberOfPathsTLE(int n, int[][] corridors) {
        HashMap<Integer, List<Integer>> graph= new HashMap<>();
        for(int[] e: corridors){
            List<Integer> adj=graph.getOrDefault(e[0], new ArrayList<>());
            adj.add(e[1]);
            graph.put(e[0], adj);
            List<Integer> adj1=graph.getOrDefault(e[1], new ArrayList<>());
            adj1.add(e[0]);
            graph.put(e[1], adj1);
        }
        HashSet<String> cycleSet=new HashSet<>();
        //Time: O(n)
        for (int i = 1; i <= n; i++) {
            HashSet<Integer> curSet = new HashSet<>();
            curSet.add(i);
            solution(i, -1, i, 0, curSet, cycleSet, graph);
        }
//        System.out.println(cycleSet);
        return cycleSet.size();
    }

    public static void solutionFull(
            int prevNode, int node, List<Integer> nodes,
            HashSet<Integer> visited, HashMap<Integer, List<Integer>> graph, HashSet<String> set, HashSet<Integer> vs){
        //1 -> 2 -> 3
        List<Integer> adj=graph.get(node);

        if(adj==null){
            return;
        }
        for(int e: adj){
            if(prevNode==e){
                continue;
            }
            if(visited.contains(e)){
                if(nodes.size()>=3&&nodes.get(nodes.size()-3)==e){
                    List<Integer> subList= new ArrayList<>(nodes.subList(nodes.size()-3, nodes.size()));
//                    System.out.println(e);
//                    System.out.println(subList);
//                    System.out.println(nodes);
                    Collections.sort(subList);
                    StringBuilder curStr=new StringBuilder();

                    for(int curNode: subList){
                        curStr.append(curNode);
                        curStr.append("_");
                    }
                    set.add(curStr.toString());
//                    System.out.println(curStr);
                }
                continue;
            }
            nodes.add(e);
            visited.add(e);
            vs.add(e);
            solutionFull(node, e, nodes, visited, graph, set, vs);
            nodes.remove(nodes.size()-1);
            visited.remove(e);
        }
    }

    public static int numberOfPathsTLE1(int n, int[][] corridors) {
        HashMap<Integer, List<Integer>> graph= new HashMap<>();
        for(int[] e: corridors){
            List<Integer> adj=graph.getOrDefault(e[0], new ArrayList<>());
            adj.add(e[1]);
            graph.put(e[0], adj);
            List<Integer> adj1=graph.getOrDefault(e[1], new ArrayList<>());
            adj1.add(e[0]);
            graph.put(e[1], adj1);
        }
        HashSet<String> cycleSet=new HashSet<>();
        HashSet<Integer> visited=new HashSet<>();
        HashSet<Integer> vs=new HashSet<>();
        //Time: O(n)
        for (int i = 1; i <= n; i++) {
            if(vs.contains(i)){
                continue;
            }
            visited.add(i);
            vs.add(i);
            List<Integer> listNodes=new ArrayList<>();
            listNodes.add(i);
            solutionFull(-1, i, listNodes, visited, graph, cycleSet, vs);
        }
//        System.out.println(cycleSet);
        return cycleSet.size();
    }

    public static int numberOfPaths(int n, int[][] corridors) {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int i = 1; i <= n; i++) g.put(i, new HashSet<>());

        //build the graph, we count only neighbours with higher index
        for (int[] e: corridors) {
            if (e[0] < e[1]) g.get(e[0]).add(e[1]);
            else g.get(e[1]).add(e[0]);
        }

        int res = 0;
        //for every edge we count common neighbours
        //Time: O(E)
        for (int[] e: corridors) {
            Set<Integer> s0 = g.get(e[0]);
            Set<Integer> s1 = g.get(e[1]);
            //TIME: O(V) ==> SUM=N
            for (int k: s0) {
                if (s1.contains(k)) res++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        //** Requirement
        //- (A maze consists of n rooms) numbered from (1 to n), and some rooms are connected by corridors.
        //- You are given a 2D integer array corridors where corridors[i] = [room1i, room2i]
        // indicates that there is a corridor connecting (room1-i) and (room2-i),
        // allowing a person in the maze to go from (room1-i) to (room2-i) and vice versa.
        //- (The designer of the maze) wants to know (how confusing) the maze is.
        //- (The confusion score of the maze)
        //  + is the number of (different cycles of length 3).
        //
        //For example,
        // 1 → 2 → 3 → 1 is a cycle of length 3,
        // but 1 → 2 → 3 → 4 and 1 → 2 → 3 → 2 → 1 are not.
        //- Two cycles are considered to be different
        // if (one or more of the rooms visited) in (the first cycle) is not in (the second cycle).
        //* Return (the confusion score) of the maze.
        //
        //Input: n = 5,
        //corridors = [
        // [1,2],[5,2],[4,1],[2,4],[3,1],[3,4]
        //]
        //Output: 2
        //Explanation:
        //One cycle of length 3 is 4 → 1 → 3 → 4, denoted in red.
        //Note that this is the same cycle as 3 → 4 → 1 → 3 or 1 → 3 → 4 → 1 because the rooms are the same.
        //Another cycle of length 3 is 1 → 2 → 4 → 1, denoted in blue.
        //Thus, there are two different cycles of length 3.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= n <= 1000
        //1 <= corridors.length <= 5 * 10^4
        //corridors[i].length == 2
        //1 <= room1i, room2i <= n
        //room1-i != room2-i
        //There are no duplicate corridors.
        //  + n<=1000 ==> Can not use (binary number)
        //  + corridors.length <= 5 * 10^4 ==> Time: O(n*k)
        //
        //* Brainstorm:
        //
        //Input: n = 5,
        //corridors = [
        // [1,2],[5,2],[4,1],[2,4],[3,1],[3,4]
        //]
        //Output: 2
        //- Find the number of cycle with (length == 3)
        //- If we focus on the traverse
        //  ==> It is difficult to traverse (the all of nodes) to find the cycle with (length == 3)
        //  + 1 -> 2 -> 3 -> 1
        //- For each node ==> We just need to traverse with (depth == 3)
        //  + If we don't see (the ("second") node) == (the ("first") node)
        //  ==> Ignore
        //==> We just need to store map to check result
        //Ex:
        //1 -> 2 -> 3 -> 1
        //map: [1,2,3,1] ==> [2,3,1,2]: Same
        //1 - 3 - 2
        //      \----/
        //1 - 2 - 3
        //  \---/
        //==> Same
        //===> Sort list of nodes
        //
        //- For (all of nodes) + traverse util getting (depth==3)
        //==> TLE
        //
        // 1 -- 3 -- 2 -- 4 -- 5
        //  \-------/  \-----/
        //
        //- Traverse(1)
        //- Traverse(2)
        //  ==> Duplicated path
        //- How to skip them?
        //  ==> DFS full tree
        //  ==> It is still TLE
        //
        //* Solution:
        //- For each edge(a,b)
        //  + a,b,c --> Cycle
        //      ==> C is neighbor of (a,b)
        //  ==> Get adj nodes of a,b
        //  * Main point:
        //  ==> Find the common neighbors of two of nodes
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(N+E)
        //- Time: O(N+E)
        //  + E egdes
        //  + Bz O(V) ==> SUM=N
        //
        int n = 5;
        int[][] corridors = {{1,2},{5,2},{4,1},{2,4},{3,1},{3,4}};
        System.out.println(numberOfPathsTLE(n, corridors));
        System.out.println(numberOfPathsTLE1(n, corridors));
        System.out.println(numberOfPaths(n, corridors));
    }
}
