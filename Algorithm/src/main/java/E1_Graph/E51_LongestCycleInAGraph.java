package E1_Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class E51_LongestCycleInAGraph {

    public static void dfs(
            int node, List<Integer>[] adjNodes,
            boolean[] visited, int depth, int[] rs, int root){
        if(node==root&&visited[node]){
            rs[0]=Math.max(rs[0], depth);
            return;
        }
        visited[node]=true;
        List<Integer> adj=adjNodes[node];

        for(int nextNode: adj){
            dfs(nextNode, adjNodes, visited, depth+1, rs, root);
        }
    }

    public static int longestCycle(int[] edges) {
        int n= edges.length;
        //Space: O(n)
        //Time: O(n)
        List<Integer>[] adjNodes=new List[n];

        //Time: O(n)
        for(int i=0;i<n;i++){
            adjNodes[i]=new ArrayList<>();
        }
        //Space: O(n)
        //Time: O(n)
        int[] inDegree=new int[n];

        //Time: O(n)
        for(int e: edges){
            if(e!=-1){
                inDegree[e]++;
            }
        }
        //Space: O(n)
        Queue<Integer> nodes=new LinkedList<>();

        for(int i=0;i<n;i++){
            if(inDegree[i]==0){
                nodes.add(i);
            }
            if(edges[i]!=-1){
                adjNodes[i].add(edges[i]);
            }
        }
        //Space: O(n)
        //Time: O(n)
        boolean[] visited =new boolean[n];

        while(!nodes.isEmpty()){
            int curNode = nodes.poll();
            List<Integer> adj=adjNodes[curNode];
            visited[curNode]=true;

            for(Integer nextNode: adj){
                inDegree[nextNode]--;
                if(inDegree[nextNode]==0&&!visited[nextNode]){
                    nodes.add(nextNode);
                }
            }
        }
//        Queue<int[]> node1s=new LinkedList<>();
        int[] rs=new int[1];

        //Time: O(n)
        for(int i=0;i<n;i++){
            if(inDegree[i]==1){
//                node1s.add(new int[]{i, 1});
                if(!visited[i]){
                    //Space: O(n)
                    dfs(i, adjNodes, visited, 0, rs, i);
                }
            }
        }
        return rs[0]==0?-1:rs[0];
    }

    public static int longestCycleOptimization(int[] edges) {
        int n= edges.length;
        //Space: O(n)
        //Time: O(n)
        List<Integer>[] adjNodes=new List[n];

        //Time: O(n)
        for(int i=0;i<n;i++){
            adjNodes[i]=new ArrayList<>();
        }
        //Space: O(n)
        //Time: O(n)
        int[] inDegree=new int[n];

        //Time: O(n)
        for(int e: edges){
            if(e!=-1){
                inDegree[e]++;
            }
        }
        //Space: O(n)
        Queue<Integer> nodes=new LinkedList<>();

        for(int i=0;i<n;i++){
            if(inDegree[i]==0){
                nodes.add(i);
            }
            if(edges[i]!=-1){
                adjNodes[i].add(edges[i]);
            }
        }
        //Space: O(n)
        //Time: O(n)
        boolean[] visited =new boolean[n];

        while(!nodes.isEmpty()){
            int curNode = nodes.poll();
            List<Integer> adj=adjNodes[curNode];
            visited[curNode]=true;

            for(Integer nextNode: adj){
                inDegree[nextNode]--;
                if(inDegree[nextNode]==0){
                    nodes.add(nextNode);
                }
            }
        }
//        Queue<int[]> node1s=new LinkedList<>();
        int rs=0;

        for(int i=0;i<n;i++){
            if(!visited[i]){
                int curNeigh=edges[i];
                int count=1;
                visited[i]=true;
                while(curNeigh!=i){
                    visited[curNeigh]=true;
                    curNeigh=edges[curNeigh];
                    count++;
                }
                rs=Math.max(rs, count);
            }
        }
        return rs==0?-1:rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a directed graph) of n nodes numbered from (0 to n - 1), where (each node) has (at most) (one outgoing edge).
        //- The graph is represented with (a given 0-indexed array) edges of size n, indicating
        //  that
        //      + there is a directed edge from (node i) to node (edges[i]).
        //- If there is (no outgoing edge) from node i, then edges[i] == -1.
        //* Return the length of (the longest cycle) in the graph.
        // If no cycle exists, return -1.
        //- A cycle is a path that (starts and ends at) the same node.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //n == edges.length
        //2 <= n <= 10^5
        //-1 <= edges[i] < n
        //edges[i] != i
        //  + Số lượng node khá lớn ==> Không thể loop bình thường được.
        //  + Mỗi node có nhiều nhất (1 outgoing node)
        //
        //- Brainstorm
        //- Nếu DFS như bình thường thì sẽ bị TLE vì phải traverse lại các path cũ
        //- Dùng topological sort:
        //  + Traverse sẽ đỡ hơn.
        //
        //Ex:      / ---- 4
        //        |       ^
        //        V       |
        //       3  ----> 2
        //     ^  ^
        //    /    \
        //  1       0
        //+ Dù traverse qua 0 và 1 nhưng sẽ không bao giờ đến được 3
        //  ==> Vì ta sẽ không bao giờ đển đc 4 ==> 4 in cycle
        //
        //- Mỗi node có nhiều nhất (1 outgoing node)
        // 0 -> 1
        // 1 -> 2
        // 2 -> 3
        // 3 -> 0
        //  + Hoàn toàn có thể không có node mà (inDegree[i]==0)
        //
        //- Khi nào thì in cycle?
        //0 <-- 1
        //|    ^ ^
        //V    | |
        //2 - /  |
        //3 ---> 4
        //[2,0,1,4,1]
        //==> Rs = 3
        //  + Không tính 3, 4 vì không in cycle
        //- Có thể có nhiều cycle không?
        //  + Có thể nếu nó không liên thông
        // 0 -> 1 -> 2  (3) <-4 <- 5
        //  ^      /     \       ^
        //   \----        \---- /
        //[1,2,0,5,3,4]
        //  + Còn nếu cùng 1 graph ==> Không thể
        // 1 -> 2 -> 3 <- (4) <-5 <-6
        //  ^      /
        //   \-----
        //  ==> 4 không thể kết nối ra.
        //  + Không vì nếu có nhiều cycle ==> 1 điểm cần trỏ đến 2 điểm để tạo ra 2 cycle
        //
        //- Điểm có inDegree[i]==0:
        //  + Sẽ không bao giờ được include trong cycle
        //  ==> Triệt tiêu node có inDegree[node]==0
        //
        //
        //Ex:      / ---- 4
        //        |       ^
        //        V       |
        //       3  ----> 2
        //     ^  ^
        //    /    \
        //  1       0
        //    \   /
        //      5
        //
//        int[] edges = {3,3,4,2,3};
//        int[] edges = {2,0,1,4,1};
//        int[] edges = {1,2,0,5,3,4};
        //- Thử thay đổi len của các cycles khác nhau xem sao:
        // 0 -> 1 -> 2  (3) <-4 <- 5 <- 6
        //  ^      /     \             ^
        //   \----        \---------- /
        //[1,2,0,5,3,4]
        //rs=4
        //
        //1.1, Optimization
        //- Đoạn sau khi áp dụng tolological sort ta có thể do it iteratively
        //- Do ta đã suy luận là sẽ không có 2 cycles kề nhau:
        //  ==> Ta có thể loop để traverse luôn thay vì dùng DFS.
        //  + Dùng while
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        int[] edges = {1,2,0,6,3,4,5};
        System.out.println(longestCycle(edges));
        System.out.println(longestCycleOptimization(edges));
        //#Reference:
        //1591. Strange Printer II
        //2608. Shortest Cycle in a Graph
    }
}
