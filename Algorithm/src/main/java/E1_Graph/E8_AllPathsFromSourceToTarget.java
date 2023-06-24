package E1_Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class E8_AllPathsFromSourceToTarget {

    public static int[][] g;
    public static int n;
    public static List<List<Integer>> rs;

    public static void solutionDFS(List<Integer> currentPath, int node){
        if(node==n-1){
            rs.add(new LinkedList<>(currentPath));
            return;
        }
        for(int i=0;i<g[node].length;i++){
            int nextNode=g[node][i];
            currentPath.add(nextNode);
            // System.out.println(currentPath);
            solutionDFS(currentPath, g[node][i]);
            currentPath.remove(currentPath.size()-1);
        }
    }

    public static HashMap<Integer, List<List<Integer>>> nodeMapToPath;

    public static List<List<Integer>> solutionDynamicProgramming(Integer node){
        if(node==n-1){
            List<List<Integer>> rs=new ArrayList<>();
            List<Integer> list=new ArrayList<>();
            list.add(node);
            rs.add(list);
            return rs;
        }
        if(nodeMapToPath.containsKey(node)){
            return nodeMapToPath.get(node);
        }
        int[] adjNode=g[node];
        List<List<Integer>> currentPathNode=new LinkedList<>();

        //N adj nodes
        for (int j : adjNode) {
            List<List<Integer>> adjPathNode = solutionDynamicProgramming(j);
            //N paths from (j to n-1)
            for (List<Integer> list : adjPathNode) {
                ArrayList<Integer> newPath = new ArrayList<>();
                newPath.add(node);
                newPath.addAll(list);
                currentPathNode.add(newPath);
            }
        }
//        System.out.printf("%s %s\n", node, currentPathNode);
        return currentPathNode;
    }
    public static List<List<Integer>> allPathsSourceTargetDFS(int[][] graph) {
        n=graph.length;
        g=graph;
        rs=new ArrayList<>();
        List<Integer> list=new LinkedList<>();
        list.add(0);
        solutionDFS(list, 0);
        return rs;
    }
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        n=graph.length;
        g=graph;
        nodeMapToPath=new HashMap<>();
        rs=solutionDynamicProgramming(0);
        return rs;
    }
    public static void main(String[] args) {
        int[][] graph = {{1,2},{3},{3},{}};
        //** Requirement
        //- Tìm all paths từ 0 --> n-1
        //
        //** Idea
        //1.
        //1.0,
        //- Đơn giản là dùng dfs thôi
        //
        //1.1, Complexity
        //- Time complexity :
        //+ 2 nodes --> 1 path từ (0 --> n-1)
        //1 --> 3
        //+ 3 nodes : 2 path từ ( 0 --> n-1)
        //VD:
        //1 --> 0 --> 3
        //1 --> 3
        //+ 4 nodes : Số path từ ( 0 --> n-1 ) sẽ là double so với 3 nodes
        //VD:
        //* Cũ ta có (Danh sách path (0-n-1)) = old paths
        // start --> old paths --> end
        //--> Nếu nối thêm new_node ==> Ta sẽ có thêm các paths dạng:
        //* start --> new_node --> old paths --> end + (old paths)
        //==> số path = (số old path * 2)
        //* Ta có thể dễ dàng chứng mình bằng cách nối chúng vào.
        // time = 2^0 + 2^1 + 2^2 + ... + 2^(N-2) (N =length) --> Nhị phân
        // CT: 0111=1000 - 1 ==> time = 2^(N-1) - 1
        //+ Each path ==> Có nhiều nhất N-2 intermediate nodes --> Cần build mất O(N)
        //--> Cận trên là O(2^(N-1) * N) = O(2^N*N)
        //
        //- Space complexity :
        //+ Each path = N nodes = O(N)
        //+ Stack mem = N layers = O(N)
        //==> Space = O(2*N) = O(N)
        //
        //2.
        //2.0, Dynamic programming
        //-
        //2.1, Idea
        //- Ta sẽ dùng lại kết quả danh sách path tại node i
        //+ Tư duy dạng này sẽ là (top-down approach)
        //VD:
        // 0 --> 1 --> 3 --> 5
        // 0 --> 1 --> 3 --> 4 --> 5
        //3 --> 5 : Ta có 2 paths:
        //+ 3 --> 5
        //+ 3 --> 4 --> 5
        //--> Xét đến điểm nào --> map + = (3, list_nodes)
        //- Tư duy dạng:
        //+ Tại node = 3 ta có list = {1,2,4,5}
        //--> recursion(adj(3)) --> Tổng của chúng + add(3) sẽ là của 3
        //+ Sau đó nếu lần gặp tiếp theo của 3 --> return lại 3 luôn thôi.
        //+ Lưu dạng hashmap< x, List<Integer>>
        //
        //2.2, Complexity
        //- Time complexity : (2^N * N)
        //+ Giả sử ta tính với node=0 (Last step)
        //  + Muốn copy mỗi path thì mất N time
        //  + Ta có (2^(N-1) -1) paths (Như đã suy luận trong method 1)
        //+ Trước last step suy luận tương tự ta có:
        //  + Length path = N-1
        //  + Có tổng cộng 2^(N-2) paths
        //Time= (i: 1 --> N) 2^(i-1) * i = (2^N * N)
        //
        //- Space complexity :
        //+ Ta có nhiều nhất 2^(N-1)-1 paths
        //+ Mỗi path gồm N nodes
        //+ Space cho việc lưu trữ node= O(2^(N-1)-1) * N = O(2^N*N)
        //+ Stack để call function --> Độ sâu N --> Tốn O(N) space
        //+ Kết quả trung gian --> hashMap() lưu các kết quả tại các node.
        //  + last node có 2^(N-1) path + Mỗi path có (N node) --> space = O(2^(N-1) * N)
        //  + node before last node : 2^(N-2) path --> space = O(2^(N-2) * (N-1))
        //--> space = O(2^N*N)
        //
        //#Reference:
        //1192. Critical Connections in a Network
        //1976. Number of Ways to Arrive at Destination
        //2328. Number of Increasing Paths in a Grid
        allPathsSourceTargetDFS(graph);
        allPathsSourceTarget(graph);
    }
}
