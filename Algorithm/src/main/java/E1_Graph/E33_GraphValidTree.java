package E1_Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E33_GraphValidTree {
    public static boolean visited[][];
    public static boolean stack[];
    public static boolean traverse[];
    public static boolean isCycle(int u, List<Integer>[] adjNodes){
        traverse[u]=true;
        if(stack[u]){
            return true;
        }
//        System.out.println(u);
        if(adjNodes[u]!=null){
            stack[u]=true;
            for(int v:adjNodes[u]){
//                System.out.printf("%s %s\n", u, v);
                if(visited[u][v]){
                    continue;
                }
                visited[u][v]=true;
                visited[v][u]=true;
                if(isCycle(v, adjNodes)){
                    return true;
                }
//                System.out.println(v);
            }
        }
        stack[u]=false;
        return false;
    }

    public static boolean validTree(int n, int[][] edges) {
        List<Integer>[] adjNodes=new ArrayList[n];
        visited=new boolean[n][n];
        stack=new boolean[n];
        traverse=new boolean[n];
        int initNode=-1;

        //O(M)
        for(int[] edge:edges){
            int x=edge[0];
            int y=edge[1];
            if(adjNodes[x]==null){
                adjNodes[x]=new ArrayList<>();
            }
            if(adjNodes[y]==null){
                adjNodes[y]=new ArrayList<>();
            }
            adjNodes[x].add(y);
            adjNodes[y].add(x);
            if(initNode==-1){
                initNode=x;
            }
        }
//        System.out.println(Arrays.toString(adjNodes));
        if(initNode==-1){
            initNode=0;
        }
        //O(M)
//        System.out.println(initNode);
        if(isCycle(initNode, adjNodes)){
//            System.out.println("Having a cycle");
            return false;
        }
        //O(N)
        for(int i=0;i<n;i++){
            if(!traverse[i]){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        //** Requirement
        //- Cho 1 danh sách edges
        //* return true nếu Graph là valid tree
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //+ 1 <= n <= 2000
        //+ 0 <= edges.length <= 5000
        //+ edges[i].length == 2
        //
        //- Graph là 1 tree thì sẽ như thế nào?
        //+              0               6
        //            /     \           /  \
        //          1        2        7     8
        //        /   \       \
        //      3      4       5
        //        (8)
        //         |
        //(1) --  (2) -- (5)
        //|
        //(3) --  (4) -- (10)
        //         |
        //        (7)
        //+ Graph cần phải không có chu kỳ
        //+ Không có đồ thị độc lập
        //--> Vì là đô thị vô hướng --> Không cần xác định root của tree
        //
        //* Chú ý:
        //- Với đồ thị vô hướng --> Chỉ có thể check dựa trên visited[i][j] ==> Vì (i) có thể đến (j) cả 2 chiều
        //- Với đồ thị có hướng --> Thì ta có thể dùng visited[i] 1 chiều --> Vì (i) --> (j) không thể hiện 2 chiều nếu có (j) --> (i) tức là có chu kỳ
        //- Cả 2 thằng đều cần có stack[i] + (reset lại) để check danh sách các nodes trên đường đi.
        //- Với đồ thị có hướng ta đặt check (stack[u] trước visited[u]) ưu tiên check cycle.
        //
        //1.1, Complexity:
        //- Time complexity : O(M+N)
        //+ Trong mọi trường hợp nếu các biến khác nhau --> sẽ là phép cộng.
        //
        //- Space complexity : O(M+N^2)
        //+ adjNodes : O(M) spacew
        //+ visited[][] : O(N^2)
        //+ other : O(N)
        int n = 3;
//        int[][] edges = {{0,1},{0,2},{0,3},{1,4}};
//        int[][] edges = {{0,1},{1,2},{2,3},{1,3},{1,4}};
//        int[][] edges = {{0,1},{1,2},{2,3},{1,3},{1,4}};
        int[][] edges = {{1,0},{2,0}};
        System.out.println(validTree(n, edges));
        //#Reference:
        //721. Accounts Merge
        //323. Number of Connected Components in an Undirected Graph
    }
}
