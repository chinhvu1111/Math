package contest;

import javafx.util.Pair;

import java.util.*;

public class E76_MinimumCostWalkInWeightedGraph {

    public static int[] weight;
    public static int[] rank;

    public static int find(int x, int[] parent){
        int temp=x;
        while(parent[temp]!=temp){
            weight[temp]=weight[temp] & weight[parent[temp]];
            weight[parent[temp]]=weight[temp];
            temp=parent[temp];
        }
        parent[x]=temp;
        return temp;
    }
    public static void union(int x, int y, int w, int[] parent){
        int parentX=find(x, parent);
        int parentY=find(y, parent);

        if(rank[parentX]<rank[parentY]){
            parent[parentX]=parentY;
        }else{
            parent[parentY]=parentX;
        }
        if(rank[parentX]==rank[parentY]){
            rank[parentX]++;
            rank[parentY]=rank[parentX];
        }
        if(weight[parentX]==-1){
            weight[parentX]=w;
        }
        if(weight[parentY]==-1){
            weight[parentY]=w;
        }
        weight[parentY]=weight[parentX]&weight[parentY]&w;
        weight[parentX]=weight[parentY];
    }

    public static int[] minimumCost(int n, int[][] edges, int[][] query) {
        //Space : O(n)
        //Time : O(n)
        int[] parent=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
        //Space : O(n)
        weight=new int[n];
        rank=new int[n];
        Arrays.fill(weight, -1);

        //Time : O(m)
        for(int[] e: edges){
            union(e[0], e[1], e[2], parent);
        }
        int[] rs=new int[query.length];
        int i=0;
        //Time : O(k)
        for(int[] q: query){
            if(q[0]==q[1]){
                rs[i++]=0;
                continue;
            }
            int parentX=find(q[0], parent);
            int parentY=find(q[1], parent);
            if(parentX!=parentY){
                rs[i]=-1;
            }else{
                rs[i]=Math.min(weight[parentX], weight[parentY]);
            }
            i++;
        }
//        for(i=0;i<rs.length;i++){
//            System.out.printf("%s, ", rs[i]);
//        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- There is an undirected weighted graph with n vertices labeled from 0 to n - 1.
        //You are given the integer n and an array edges, where edges[i] = [ui, vi, wi] indicates that there is an edge between vertices ui and vi with a weight of wi.
        //A walk on a graph is a sequence of vertices and edges.
        // The walk starts and ends with a vertex, and each edge connects the vertex that comes before it and the vertex that comes after it.
        //* It's important to note that a walk may visit the same edge or vertex more than once.
        //The cost of a walk starting at node u and ending at node v is defined as the bitwise AND of the weights of the edges traversed during the walk.
        //- In other words, if the sequence of edge weights encountered during the walk is w0, w1, w2, ..., wk,
        // then the cost is calculated as w0 & w1 & w2 & ... & wk, where & denotes the bitwise AND operator.
        //- You are also given a 2D array query, where query[i] = [si, ti].
        // For each query, you need to find the minimum cost of the walk starting at vertex si and ending at vertex ti.
        // If there exists no such walk, the answer is -1.
        //* Return the array answer, where answer[i] denotes the (minimum cost) of a walk for query i.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n <= 10^5
        //0 <= edges.length <= 10^5
        //edges[i].length == 3
        //0 <= ui, vi <= n - 1
        //ui != vi
        //0 <= wi <= 10^5
        //1 <= query.length <= 10^5
        //query[i].length == 2
        //0 <= si, ti <= n - 1
        //==> Query khá lớn
        //
        //- Brainstorm
        //- Idea ở đây là phép (& operation) thì cần làm càng nhiều càng tốt
        //==> A walk sẽ là phép & all nodes.
        //
//        int n = 5;
//        int[][] edges = {{0,1,7},{1,3,7},{1,2,1}}, query = {{0,3},{3,4}};
//        int n = 3;
//        int[][] edges = {{0,2,7},{0,1,15},{1,2,6},{1,2,1}}, query = {{1,2}};
        // 0 --> 2
        // 0 --> 1
        // 1 --> 2
        // 1 --> 2
        //- Bài này làm union find cần chú ý:
        //- Slow khi:
        //  + Khi không dùng rank ==> Để graph nối theo chiều dài thẳng tuột ==> Khiến cho thời gian traverse sẽ lâu hơn
        //      + Thay vì phân ra theo horizontally
        //  + Không gán weight bên trong find
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n+m+k)
        //
        //#Reference:
        //1478. Allocate Mailboxes
        //1713. Minimum Operations to Make a Subsequence
        //2581. Count Number of Possible Root Nodes
        //
        int n = 9;
        int[][] edges = {{0,4,7},{3,5,1},{1,3,5},{1,5,1}}, query = {{0,4},{1,5},{3,0},{3,3},{3,2},{2,0},{7,7},{7,0}};
        minimumCost(n, edges, query);
        System.out.println(1 & 7);
    }
}
