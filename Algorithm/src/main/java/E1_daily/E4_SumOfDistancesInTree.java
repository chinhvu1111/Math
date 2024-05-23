package E1_daily;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class E4_SumOfDistancesInTree {

    public static void bfs(int node, int n, int[][] dist, List<Integer>[] adj){
        boolean[] visited = new boolean[n];
        Queue<Integer> nodes=new LinkedList<>();
        nodes.add(node);
        int[] depth=new int[n-1];

        while(!nodes.isEmpty()){
            Integer curNode=nodes.poll();
            List<Integer> curAdj=adj[curNode];
            for(Integer e: curAdj){
                if(!visited[e]){
                    depth[e]=depth[curNode]+1;
                    nodes.add(e);
                    visited[e]=true;
                }
            }
        }
        for(int i=0;i<n;i++){
            dist[node][i]=depth[i];
        }
    }

    public static int[] sumOfDistancesInTreeWrong(int n, int[][] edges) {
        List<Integer>[] adj=new ArrayList[n];

        for(int i=0;i<n;i++){
            adj[i]=new ArrayList<>();
        }
        int[][] dist=new int[n][n];
        for(int[] e: edges){
            dist[e[0]][e[1]]=1;
            dist[e[1]][e[0]]=1;
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        return null;
    }

    public static void dfs1(
            int cur, int parent,
            List<Integer>[] adj, int[] count, int[] rs){
        count[cur]=1;
        List<Integer> curAdj=adj[cur];

        for(Integer node: curAdj){
            if(node!=parent){
                dfs1(node, cur, adj, count, rs);
                //số lượng node hiện tại += số lượng node của all child nodes của nó.
                count[cur]+=count[node];
//                System.out.printf("%s");
                rs[cur]+=rs[node]+count[node];
            }
        }
    }

    public static void dfs2(int cur, int parent,
                            List<Integer>[] adj,
                            int[] rs, int n, int[] count){
        List<Integer> curAdj=adj[cur];
        for(Integer node: curAdj){
            if(node!=parent){
                rs[node]=rs[cur] + n - 2*count[node];
                dfs2(node, cur, adj, rs, n, count);
            }
        }
    }

    public static int[] sumOfDistancesInTree(int n, int[][] edges) {
        List<Integer>[] adj=new ArrayList[n];
        int[] rs=new int[n];
        int[] count=new int[n];

        for(int i=0;i<n;i++){
            adj[i]=new ArrayList<>();
        }
        for(int[] e: edges){
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
            count[e[0]]++;
            count[e[1]]++;
        }
        //1 -> 2
        //1 -> 3
        //count[1]=2
        //count[2]=1
        //count[3]=1
        //
        //
        dfs1(0, -1, adj, count, rs);
        dfs2(0, -1, adj, rs, n, count);

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //There is (an undirected connected tree) with n nodes labeled from (0 to n - 1) and n - 1 edges.
        //- You are given the integer n and the array edges where edges[i] = [ai, bi]
        // indicates that there is an edge between nodes (ai and bi) in the tree.
        //* Return an array answer of length n where answer[i] is
        // the sum of the distances between (the ith node) in the tree and (all other nodes).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= n <= 3 * 10^4
        //edges.length == n - 1
        //edges[i].length == 2
        //0 <= ai, bi < n
        //ai != bi
        //The given input represents a valid tree.
        //
        //Ex:
        //      0
        //    /   \
        //  1      2
        //       /  \   \
        //     3     4   5
        //- count[i] là số lượng nodes của subtree có root là i (Tính cả root node)
        //- res[i] là sum khoảng cách từ node i --> all remainging nodes
        //- Với ví dụ trên:
        //  + count[2] = 4
        //  + count[0] = 6
        //  + res[2] = 1(3) + 1(4) + 1(5) + 1(0) + 2(1) = 6
        //  + res[0] = 1(1) + 1(2) + 2(3) + 2(4) + 2(5) = 8
        //
        //            0(1)
        //        /       \
        //      1(1)      2(1)
        //    /   \       /    \
        //  3(2)  4(2) 5(2)   6(2)
        //=> rs[0] = 8
        //           2
        //         /  \    \
        //       0(1) 5(1) 6(1)
        //       |     |
        //      1(2)   7(2)
        //    /  \
        //  3(3)  4(3)
        //=> rs[2] = 13
        //- Ta thấy rằng khi đẩy 2 thành root
        //  + Depth của left(2) tăng lên 1
        //      => sum x+y+z -> newSum= x+1 +y+1 +z+1
        //      => Nó sẽ tăng lên với giá trị chính bằng số lượng node cũ ==> count[0] <=> count[child]
        //  + Depth của right(2) giảm đi 1
        //      => sum x+y+z -> newSum= x-1+y-1+z-1
        //      => Nó sẽ giảm lên với giá trị chính bằng số lượng node cũ ==> count[5] <=> count[child]
        //* Áp dụng lý thuyết bên trên:
        //- Nếu 0 là root
        //=> sum[0] = sum[1] + count[1] + sum[2] + count[2]
        //  => Đi sâu thêm ta sẽ tính cho 2
        //   + sum[2] = sum[5] + count[6] + sum[5] + count[6]
        //- Ta thấy ở đây:
        //  + sum[2] chưa đủ
        //    + Do thiếu đường đi ngược về parent của nó nữa.
        //- Nếu coi 2 là root:
        //  + sum[2]+= sum[0] + count[0]
        //  + Problems:
        //      + count[0] sẽ thừa vì tính cả các node ở 2 nữa
        //      + sum[0] cũng thừa vì đã tính all distances nodes.
        //- Từ ở đây ta sẽ coi:
        //  + sum <=> rs
        //Ex:
        //            0(1)
        //        /       \
        //      1(1)      2(1)
        //    /   \       /    \
        //  3(2)  4(2) 5(2)   6(2)
        //- Tính thêm cho:
        //  + sum_left(2 -> 0) = rs[0] - rs[left[0]]
        //      = rs[0] - (rs[2] + count[2])
        //      = rs[0]-rs[2]-count[2]
        //  + Vì bù thêm 1 depth => +(count[0] - count[2])
        //  + rs[2]+= rs[0]-rs[2]-count[2] + count[0]-count[2]
        //  + Ta thấy rằng:
        //      + Vì là cộng thêm cho rs[2] mà lại có (-rs[2])
        ///     ==> Ta có thể update trực tiếp
        //      <=> rs[2] = rs[0] + count[0] - 2*count[2];
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n)
        //
        //#Reference:
        //979. Distribute Coins in Binary Tree
        //2049. Count Nodes With the Highest Score
        //2603. Collect Coins in a Tree
        int n = 6;
        int[][] edges = {{0,1},{0,2},{2,3},{2,4},{2,5}};
        sumOfDistancesInTree(n, edges);
    }
}
