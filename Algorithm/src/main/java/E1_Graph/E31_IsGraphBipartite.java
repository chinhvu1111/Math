package E1_Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class E31_IsGraphBipartite {

//    public static int[] collection1;
//    public static int[] collection2;
    public static boolean[][] visited;
    public static int[] color;
    public static boolean solution(int n, int x, int[][] graph, int site){
        if(color[x]!=-1){
            return true;
        }
        Queue<Integer> nodes=new LinkedList<>();
        nodes.add(x);
        if(color[x]==-1){
            color[x]=0;
        }
        while (!nodes.isEmpty()){
            Integer currentNode=nodes.poll();
            int m=graph[currentNode].length;

            for(int i=0;i<m;i++){
                if(visited[currentNode][graph[currentNode][i]]){
                    continue;
                }
                if(color[graph[currentNode][i]]==color[currentNode]){
                    return false;
                }else if(color[graph[currentNode][i]]==-1){
                    color[graph[currentNode][i]]=(color[currentNode]+1)%2;
                    nodes.add(graph[currentNode][i]);
                    visited[currentNode][graph[currentNode][i]]=true;
                    visited[graph[currentNode][i]][currentNode]=true;
                }
            }
        }
        return true;
    }

    public static boolean isBipartite(int[][] graph) {
        int n=graph.length;
        visited=new boolean[n][n];
        color=new int[n];
        boolean isValid;
        Arrays.fill(color, -1);

        for(int i=0;i<n;i++){
            isValid=solution(n, i, graph, 0);
//                System.out.printf("%s %s %s\n", i, j, isValid);
            if(!isValid){
                return false;
            }
        }
//        for(int i=0;i<n;i++){
//            System.out.printf("%s %s\n", i, color[i]);
//        }

        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- A graph is (bipartite) if the nodes can be partitioned into (two independent sets) A and B
        // such that (every edge) in the graph connects a node in set A and a node in set B.
        //+ Tức là những nodes có thể partitioned thành 2 set độc lập A và B sao cho mọi cạnh trong đồ thì connect node trong set A và node trong set B
        //* Return true nếu có thể chia ra thành 2 collection nodes mà các nodes của 2 collection connect lẫn nhau ( các edges đó thuộc graph).
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //graph.length == n
        //1 <= n <= 100
        //0 <= graph[u].length < n
        //0 <= graph[u][i] <= n - 1
        //--> Không có gì đặc biệt
        //
        //- Brainstorm
        //- 2 node of collections phải kết nối được lẫn nhau
        //--> Ta sẽ dùng BFS ==> Tìm ra một tập hợp các nodes không thể connect lẫn nhau.
        //
        //VD:
        //0, 1, 2, 3
        //+ 0 không connect được 2, connect được 1 ==> (0,2), (1)
        //+ 1 không connect được 2 ==> (1,2) ==> (0,2) (1) : Tức là A không connect to B được thì ta có thể chọn A đặt vào collection 1 hoặc 2
        //+ Sẽ có cases như sau:
        //(0,1) (2)
        //(0,1,3) (2,4)
        //(0,1,3) (2,4) + [5,6]
        // Vì (3 connected 5) và (1 connected 6) ==> Không thể put (5,6) được
        //<>
        //(0,1) (2)
        //(0,1,4) (2,3)
        //(0,1,4) (2,3) + [5,6] = (0,1,4,5) (2,3,6)
        //
        //- Ta sẽ chia ra 2 trường hợp chinh:
        //- Các trường hợp các vertices có thể có chung 1 phần destination node
        // 0 --> (1,2) [ Nó sẽ có 2 chiều từ 1 và 2 nữa ]
        // 3 --> 1
        // 4 --> 2,5
        // 6 --> 7,8
        // ==> (0,3,5,6), (1,2,5,7,8)
        //
        //- Các cạnh không chung gì cả:
        // 0 --> (1,2) [ Nó sẽ có 2 chiều từ 1 và 2 nữa ]
        // 3 --> 1
        // 4 --> 2,5
        // 6 --> 7,8
        // 9 --> 10 [ Không chung cái gì cả ] ==> Để đâu cũng được
        // ==> (0,3,5,6), (1,2,5,7,8)
        //+ Liệu thứ tự add vertice vào các collection có ảnh hướng đến kết quả hay không?
        // 0 --> (1,2)
        // 4 --> 3 ==> (0,4), (1,2,3)
        // 3 --> 2 [không được vì 2,3 đã cùng collection rồi)
        //=> Làm lại
        // 0 --> (1,2)
        // 4 --> 3 ==> (0,3), (1,2,4)
        // 3 --> 2 ==> (0,3), (1,2,4) như cũ vì đã có rồi.
        //
        //- Để xử lý được việc add thêm edge phụ thuộc --> ta cần phải traverse hết cả cái graph liên quan đến các node của a edge vừa add vào:
        //VD:
        //+ 0 --> (1,2)
        //+ 2 connected to 3:
        //+ Check 3 có thuộc tập hợp 1 chưa (rồi : false <> continue)
        //(0,3) --> (1,2)
        //- Thêm edge mới thì chọn sao cho nó không bị case:
        //+ Đã A thuộc collection-1 và B thuộc collection-2 : OK
        //+ Đã A thuộc collection-1 và B chưa thuộc collection-2 : OK
        //+ Đã A chưa thuộc collection-1 và B thuộc collection-2 : OK
        //+ Đã A chưa thuộc collection-1 và B chưa thuộc collection-2 : OK
        //+ Đã A thuộc collection-1 và B thuộc collection-1 : return false.
        //+ Đã A thuộc collection-2 và B thuộc collection-2 : return false.
        //
        //1.1, Implementation
        //- Traverse:
        // A --> (B,C)
        // (E,D) <-- (B,C)
        // (E,D) --> (G,H)
        //
        //- Recursion:
        int[][] graph = {
                {1,3},
                {0,2},
                {1,3},
                {0,2}};
        //0 --> 1
        //2 <-- 1
        //
        //0 (0) --> 1(1)
        //0 (0) --> 3(1)
        //1 (1) --> 0 (0)
        //1 (1) --> 2 (0)
        //2(0) --> 1 (1)
        //2(0) --> 3(1)
        //
        //- Chuyển về bài toán tô màu:
        //+ A --> B: Nếu B tô màu rồi + A chưa tô màu --> Tô A ngược B
        //+ A --> B: Nếu B chưa tô màu + A chưa tô màu --> Còn tuỳ vào order như bên trên để tô màu A và B
        //+ Bài này theo tô theo dạng layer --> nên cần dùng BFS
        //
        //1.2, Complexity:
        //- Time complexity: O(N^2)
        //- Space complexity: O(N^2)
        //+ N is the number of vertices
        System.out.println(isBipartite(graph));
        //#Reference:
        //261. Graph Valid Tree
        //2493. Divide Nodes Into the Maximum Number of Groups
    }
}
