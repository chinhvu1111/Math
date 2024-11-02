package contest;

import E1_Topological_sort.E10_LargestColorValueInADirectedGraph;

import java.util.*;

public class E197_RemoveMethodsFromProject {

    public static void bfs(HashSet<Integer> visited, HashSet<Integer>[] adjNodes, int k){
        Queue<Integer> queue=new LinkedList<>();
        queue.add(k);
        visited.add(k);
//        System.out.printf("%s %s %s\n", x, y, w[x][y]);
        while (!queue.isEmpty()){
            int curNode=queue.poll();
            HashSet<Integer> adj=adjNodes[curNode];
            for(int e: adj){
                if(!visited.contains(e)){
                    queue.add(e);
                }
                visited.add(e);
            }
        }
    }

    public static boolean bfsLoop(HashSet<Integer> suspicious, HashSet<Integer>[] adjNodes, int node, boolean[] visited){
        Queue<Integer> queue=new LinkedList<>();
        queue.add(node);
//        System.out.printf("%s %s %s\n", x, y, w[x][y]);
        while (!queue.isEmpty()){
            int curNode=queue.poll();
            HashSet<Integer> adj=adjNodes[curNode];
            for(int e: adj){
                if(suspicious.contains(e)){
                    return false;
                }
                if(!visited[e]){
                    queue.add(e);
                }
                visited[e]=true;
            }
        }
        return true;
    }

    public static boolean couldBeRemoveAll(int k, HashSet<Integer> suspicious, int n, int[] inDegree, HashSet<Integer>[] adjNodes){
//        if(inDegree[k]==0){
//            return true;
//        }
        Queue<Integer> nodesQueue=new LinkedList<>();
        boolean[] visited=new boolean[n+1];
        boolean remove=true;

        for(int i=0;i<=n-1;i++) {
            if(visited[i]){
                continue;
            }
            if(!suspicious.contains(i)){
                remove= bfsLoop(suspicious, adjNodes, i, visited);
            }
            if(!remove){
               return false;
            }
        }
        return true;
    }

    public static List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        int[] inDegree=new int[n];
        HashSet<Integer>[] adjNodes=new HashSet[n];
        
        for(int i=0;i<n;i++){
            adjNodes[i]=new HashSet<Integer>();
        }
        for(int[] e: invocations){
            int x=e[0];
            Integer y=e[1];
            inDegree[y]++;
            adjNodes[x].add(y);
        }
        HashSet<Integer> visited=new HashSet<>();
        bfs(visited, adjNodes, k);
        boolean removeAll = couldBeRemoveAll(k, visited, n, inDegree, adjNodes);
        List<Integer> rs=new ArrayList<>();
        if(!removeAll){
            visited.clear();
        }
        for(int i=0;i<n;i++){
            if(!visited.contains(i)){
                rs.add(i);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are maintaining a project that has n methods numbered from (0 to n - 1).
        //- You are given two integers n and k, and a 2D integer array invocations, where invocations[i] = [ai, bi] indicates that (method ai invokes method bi).
        //- There is (a known bug) in method k.
        //- Method k, along with any method invoked by it, either directly or indirectly, are considered suspicious and we aim to remove them.
        //- A group of methods can (only be removed) if
        //  + no method outside the group invokes (any methods within it).
        //* Return an array containing (all the remaining methods) after (removing all the suspicious methods).
        //  + Tức không có thằng nào call tất cả (any method in the current group)
        //- You may return the answer in any order. If it is not possible to remove all the suspicious methods, none should be removed.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n <= 10^5
        //0 <= k <= n - 1
        //0 <= invocations.length <= 2 * 10^5
        //invocations[i] == [ai, bi]
        //0 <= ai, bi <= n - 1
        //ai != bi
        //invocations[i] != invocations[j]
        //  ==> N khá lớn: Time: O(n)
        //
        //** Brainstorm
        //- List ra những thằng có thể là "suspicious"
        //
        //
//        int n = 4, k = 1;
//        int[][] invocations = {{1,2},{0,1},{3,2}};
//        int n = 3, k = 2;
        // 1 --> 0
        //     ^
        // 2 -/
//        int[][] invocations = {{1,0},{2,0}};
        //Wrong: [1]
        //Expected: [0,1,2]
//        int n = 5, k = 0;
//        int[][] invocations = {{1,2},{0,2},{0,1},{3,4}};
//        int n = 3, k = 0;
//        int[][] invocations = {};
        int n = 3, k = 2;
        // 1 <-- 0 -> 2
        //     ^
        //   -/
        int[][] invocations = {{1,0},{0,1},{0,2}};
        System.out.println(remainingMethods(n, k, invocations));
    }
}
