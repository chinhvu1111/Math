package interviews;

import java.util.*;

public class E207_ToPo {
    public static int n;
    public static int[] degree;
    public static Map<Integer, Set<Integer>> map;

    public E207_ToPo(int n, int[][] edges){
        this.n=n;

        for(int i=0;i<n;i++) {
            map.put(i, new HashSet<>());
        }
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            map.get(u).add(v);
            degree[v]++;
        }
    }

    public static List<Integer> sortUsingDFS(){
        boolean[] visited=new boolean[n];
        List<Integer> result=new ArrayList<>();

        for(int i=0;i<n;i++){

        }
        return null;
    }

    public static void main(String[] args) {
        //VD
        //DFS(0) --> DFS(2) ==> [2]
        //DFS[0] --> DFS{4] ==> [2,4,0]
        //BFS ==> Tốt hơn vì sẽ không tràn bộ nhớ
    }
}
