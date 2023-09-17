package E1_bitmanipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E3_CountSubtreesWithMaxDistanceBetweenCities {
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        int m=edges.length;
        List<Integer>[] adjNodes=new ArrayList[n+1];

        for(int[] edge: edges){
            int u=edge[0];
            int v=edge[1];
            if(adjNodes[u]==null){
                adjNodes[u]=new ArrayList<>();
            }
            if(adjNodes[v]==null){
                adjNodes[v]=new ArrayList<>();
            }
            adjNodes[u].add(v);
            adjNodes[v].add(u);
        }
        int[] rs=new int[n-1];

        for(int i=0;i<1<<n;i++){
            if(Integer.bitCount(i)<2){
                continue;
            }
            int d=getMaxDistance(i, n, adjNodes);
            // System.out.printf("Current distance: %s, %s\n", i, d);

            if(d>=1){
                rs[d-1]++;
            }
        }
        return rs;
    }

    public static void dfs(int node, int[] dist, List[] adjNodes, int mask){
        List<Integer> nodes=adjNodes[node];
        // System.out.printf("%s, adj: %s\n", node, nodes);

        for(Integer nextNode: nodes){
            if(((mask>>(nextNode-1)&1)==1)&&dist[nextNode]==Integer.MIN_VALUE){
                dist[nextNode]=dist[node]+1;
                dfs(nextNode, dist, adjNodes, mask);
            }
        }
    }

    public static int getMaxDistance(int mask, int n, List[] adjNodes){
        int[] dist=new int[n+1];
        Arrays.fill(dist, Integer.MIN_VALUE);

        for(int i=1;i<=n;i++){
            if(((mask>>(i-1)&1)==1)){
                dist[i]=0;
                dfs(i, dist, adjNodes, mask);
                break;
            }
        }
        //111 = 4+3
        // System.out.printf("Mask: %s\n", mask);
        // for(int i=1;i<=n;i++){
        //     System.out.printf("%s, ", dist[i]);
        // }
        // System.out.println();
        int j=-1;

        for(int i=1;i<=n;i++){
            if(((mask>>(i-1)&1)==1)){
                if(dist[i]==Integer.MIN_VALUE){
                    return -1;
                }
                if(j==-1||dist[i]>dist[j]){
                    j=i;
                }
            }
        }
        if(j==-1){
            return -1;
        }
        dist=new int[n+1];
        Arrays.fill(dist, Integer.MIN_VALUE);
        // System.out.printf("Node: %s\n", j);
        dist[j]=0;
        dfs(j, dist, adjNodes, mask);
        int maxDistance=-1;
        //    1 -- 4
        //    |
        //    3 -- 2
        //
        //11=1011

        for(int i=1;i<=n;i++){
            if(((mask>>(i-1)&1)==1)){
                // System.out.printf("%s, ", dist[i]);
                maxDistance=Math.max(maxDistance, dist[i]);
            }
        }
        // System.out.printf("%s\n", maxDistance);
        return maxDistance;
    }
    public static void main(String[] args) {
        //** Requirement
        //- n cities
        //- We have a tree of cities
        //
        //- There are (n cities) numbered from 1 to n. You are given an array edges of size n-1,
        // where edges[i] = [ui, vi] represents a bidirectional edge between cities ui and vi.
        //- A subtree is a subset of cities where every city is reachable from every other city in the subset,
        // where the path between each pair passes through only the cities from the subset.
        //- Two subtrees are (different) if there is a city in one subtree that is (not present)   in the other.
        //
        //      1
        //    /   \
        //   4    5
        //          3
        //        /
        //      1
        //    /   \
        //   4    5
        //- 2 cái này cùng 1 tree nhưng 3 không có trong tree 1 ==> 2 subtree
        //- Return each d from 1 to (n-1):
        //  - (Maximum distance) between any (two cities) in the subtree is equal to (d)
        //  - Distance : the number of edges between them.
        //* return array of size (n-1) where d-th is the number of trees having (the maximum depth == d)
        //
        //** Idea
        //*
        //1.
        //1.0,
        //- Constraint
        //2 <= n <= 15
        //edges.length == n-1
        //edges[i].length == 2
        //1 <= ui, vi <= n
        //All pairs (ui, vi) are distinct.
        //
        //- Brainstorm
        //
        //- Với mỗi subnodes ta có 2 nhiệm vụ:
        //+ Check collection đó có phải 1 subtree hay không
        //+ Độ dài đường đi dài nhất trong subtree đó là bao nhiêu
        //
        //#Reference:
        //2212. Maximum Points in an Archery Competition
        //1223. Dice Roll Simulation
        //361. Bomb Enemy
    }
}
