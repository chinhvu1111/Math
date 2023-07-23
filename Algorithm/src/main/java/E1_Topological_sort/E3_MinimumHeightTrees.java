package E1_Topological_sort;

import java.util.*;

public class E3_MinimumHeightTrees {

    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //Edge cases
        if(n<2){
            List<Integer> centroids=new ArrayList<>();

            for(int i=0;i<n;i++){
                centroids.add(i);
            }
            return centroids;
        }
        HashMap<Integer, HashSet<Integer>> adjNodes=new HashMap<>();

        for(int[] edge:edges){
            int x=edge[0];
            int y=edge[1];
            adjNodes.computeIfAbsent(x, k -> new HashSet<>());
            adjNodes.computeIfAbsent(y, k -> new HashSet<>());
            adjNodes.get(x).add(y);
            adjNodes.get(y).add(x);
        }
        Queue<Integer> nodes=new LinkedList<>();

        for(int key:adjNodes.keySet()){
            if(adjNodes.get(key).size()==1){
                nodes.add(key);
            }
        }
        int remainingNodes=n;
//        System.out.println(nodes);

        while (remainingNodes>2){
            remainingNodes-=nodes.size();
            List<Integer> currentLeavesNodes=new ArrayList<>(nodes);
//            System.out.printf("%s %s\n", remainingNodes, nodes);

            for(Integer node: currentLeavesNodes){
                nodes.poll();
                for(int neighBor:adjNodes.get(node)){
                    adjNodes.get(neighBor).remove(node);

                    if(adjNodes.get(neighBor).size()==1){
//                        System.out.println(neighBor);
                        nodes.add(neighBor);
                    }
                }
            }
        }

        return new ArrayList<>(nodes);
    }

    public static List<Integer> findMinHeightTreesRefactorRemoveNodes(int n, int[][] edges) {
        //Edge cases
        if(n<2){
            List<Integer> centroids=new ArrayList<>();

            for(int i=0;i<n;i++){
                centroids.add(i);
            }
            return centroids;
        }
        HashMap<Integer, HashSet<Integer>> adjNodes=new HashMap<>();

        for(int[] edge:edges){
            int x=edge[0];
            int y=edge[1];
            adjNodes.computeIfAbsent(x, k -> new HashSet<>());
            adjNodes.computeIfAbsent(y, k -> new HashSet<>());
            adjNodes.get(x).add(y);
            adjNodes.get(y).add(x);
        }
        Queue<Integer> nodes=new LinkedList<>();

        for(int key:adjNodes.keySet()){
            if(adjNodes.get(key).size()==1){
                nodes.add(key);
            }
        }
        int remainingNodes=n;
//        System.out.println(nodes);

        while (remainingNodes>2){
            remainingNodes-=nodes.size();
            Queue<Integer> newLeavesNodes=new LinkedList<>();
//            System.out.printf("%s %s\n", remainingNodes, nodes);

            while (!nodes.isEmpty()){
                Integer node=nodes.poll();
                for(int neighBor:adjNodes.get(node)){
                    adjNodes.get(neighBor).remove(node);

                    if(adjNodes.get(neighBor).size()==1){
//                        System.out.println(neighBor);
                        newLeavesNodes.add(neighBor);
                    }
                }
            }
            nodes=newLeavesNodes;
        }

        return new ArrayList<>(nodes);
    }

    public static List<Integer> findMinHeightTreesRefactorIndegree(int n, int[][] edges) {
        //Edge cases
        if(n<2){
            List<Integer> centroids=new ArrayList<>();

            for(int i=0;i<n;i++){
                centroids.add(i);
            }
            return centroids;
        }
        //+ Each node has less than N children
        //+ We have n nodes
        //+ This is an undirected graph so we will have a lot of duplicated edges
        //==> O(E*2) + O(N)
        List<Integer>[] adjNodes = new ArrayList[n];
        //Space : O(N)
        int[] inDegree=new int[n];
        //Time : O(N)
        for(int i=0; i<n; i++){
            adjNodes[i] = new ArrayList<>();
        }

        //Time : O(E)
        for(int[] edge:edges){
            int x=edge[0];
            int y=edge[1];
            adjNodes[x].add(y);
            adjNodes[y].add(x);
            inDegree[x]++;
            inDegree[y]++;
        }
        Queue<Integer> nodes=new LinkedList<>();

        //Time : O(N)
        for(int i=0;i<n;i++){
            if(inDegree[i]==1){
                nodes.add(i);
            }
        }
        int remainingNodes=n;
//        System.out.println(nodes);

        //Time : O(E)
        while (remainingNodes>2){
            remainingNodes-=nodes.size();
            Queue<Integer> newLeavesNodes=new LinkedList<>();
//            System.out.printf("%s %s\n", remainingNodes, nodes);

            while (!nodes.isEmpty()){
                Integer node=nodes.poll();
                for(int neighBor:adjNodes[node]){
                    inDegree[neighBor]--;

                    if(inDegree[neighBor]==1){
//                        System.out.println(neighBor);
                        newLeavesNodes.add(neighBor);
                    }
                }
            }
            nodes=newLeavesNodes;
        }

        return new ArrayList<>(nodes);
    }

    public static void main(String[] args) {
        //** Requirement
        //- Cho 1 tree chọn any nodes as root
        //- Với mỗi cách chọn root node ta sẽ có tree tương ứng
        //* Return lại cách chọn root để tree có min height.
        //+ Nếu các trees có cùng height --> return all of them.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= n <= 2 * 10^4
        //edges.length == n - 1
        //0 <= ai, bi < n
        //ai != bi
        //--> Số lượng nodes khá nhiều ==> Không thể dfs/bfs từng nodes được.
        //
        //- Brainstorm
        //- Cách traverse như thế nào?
        //              0
        //              |
        //              1
        //            /   \
        //          2      3
        //==> Traverse theo topological ==> Bỏ 0 với inDegree[0]=0
        //              1
        //           /    \
        //          2      3
        //* --> Việc dùng topological sort : Thường sẽ tác dụng trong các bài (directed graph)
        //
        //* Kỹ năng paraphase problem thành các easier problem:
        //- Mỗi pair of nodes trong graph : Sẽ chỉ có duy nhất 1 path
        //- Tree sẽ không có cycle --> Việc tìm (min height) của nó <=> Với việc tìm (min edge giữa root và any leaf nodes) bất kỳ trong tree.
        //==> Nếu xét all trees (any way to choose root) ==> Xét all pair of nodes.
        //
        //- Tìm số edges giữa 2 nodes như thế nào --> Có thể sử dụng lại:
        //==> Việc tìm như thế này khá khó nên ta sẽ cần 1 số rule để có thể làm được bài này.
        //
        //** Centroids nodes in tree
        //- Nodes that is close to all the peripheral nodes (leaf nodes) : Các nodes gần với các leaf node hơn.
        //* Tính chất 1:
        //- Các centroids phải luôn có vai trò như nhau + Khoảng cách đến các leaf nodes là như nhau
        //  + ==> Sẽ không có cases 3 centroid nodes ==> Nó sẽ cần connected lẫn nhau để đảm bảo khoảng cách đến các leaf nodes là tương đương nhau.
        //
        //* Tính chất 2:
        //- Nếu số nodes chẵn (even) : 2 centroid nodes ==> SAI
        //- Nếu số node lẻ (odd) : 1 centroid node ==> SAI
        //* Ta chỉ chứng minh được là số centroids nodes <=2 mà thôi
        //
        //* Tính chất 3:
        //* Tree có min height chính là tree chọn centroids node là root node.
        //- Ta sẽ tỉa dần dần các leaf nodes cho đến khi gặp các centroid nodes ==> Các layers đã tỉa chính là số min height
        //
        //* Tính chất 4:
        //- Nếu tree nhận centroid node là root node thì khoảng cách từ centroid node cho đến leaf node sẽ bằng nửa đường kính của đồ thị
        //==> Tức là có thể có 1 leaf node nào đó có khoảng cách đến centroid node tương tự.
        //- Ta có 2 trường hợp:
        //+ 1 centroid node
        //+ 2 centroid nodes
        //VD:
        //                                1 (centroid node)
        //                           /   \     \
        //                         0      2     4
        //                              /        \
        //                             3         5
        //+ The number of layers : 2
        //+ The number of edges in the longest path : 4
        //                                1 (centroid node)
        //                         /                \
        //                      2 (centroid node)      4
        //                     /
        //                    3
        //+ The number of layers : 1 (Vì dừng ở remaining nodes = 2)
        //+ The number of edges in the longest path : 3
        //
        //** CT:
        //- Remaining node = 1 : the number of edges = (2 * layers)
        //- Remaining node = 2 : the number of edges = (2 * layers + 1)
        //
        //* Dựa vào đây ta có công thức như sau:
        //- Tìm các leaf nodes như thế nào?
        //+ Các (leaf nodes) là những node mà số lượng các nodes kề của nó == 1.
        //
        //- Tỉa các nodes như thế nào --> Topological sort để tỉa dần các leaf nodes.
        //
        //- Traverse như sau:
        //VD:
        // 1 -- 2 -- (3) -- (4) -- 5 -- 6
        //Centroid nodes : 3, 4
        //queue : 1, 6 : n
        //queue : 6, 2 : n-1
        //queue : 2, 5 : n-2
        //queue : 5, 3 : n-3
        //queue : 3, 4 : n-4 ==> result
        //* ==> Traverse như thế này là sai ==> Vì nó không thể hiện sự tỉa leaves nodes
        //  - Result queue sẽ bị sót 1 leaf nodes trong đó ==> Không biết centroid nodes là gì
        //* --> Cần phải dùng dạng layer traverse để có thể có thể traverse
        //- Traverse như sau:
        //+ Remove all leaf nodes ở layer1 + remainingNodes-= size(leafNodes)
        //
        //1.1, Optimization
        //- Có thể optimization bằng cách:
        //+ Đổi đoạn traverse theo layer --> add những node mới vào List mới ==> Gán lại
        //  + Cách cũ là duplicate all queue hiện tại vào list để traverse các leaf nodes ==> Sau đó là poll trong for theo các leaf nodes
        //  --> bị slow do duplicate > hơn là chỉ tạo list cho các neighborhood nodes
        //
        //- Ngoài ra có thể optimized bằng cách dùng (inDegree) array để lưu số neighbor nodes
        // ==> Chỉ trừ số lượng nodes kề ==> Thay vì remove từ HashSet.
        //
        //- Dùng array <-> HashMap
        //--> Faster (beat 99%).
        //
        //1.2, Complexity:
        //+ N is number of nodes
        //+ E is number of edges
        //- Time complexity : O(N+E)
        //- Space complexity : O(E*2 + N + N) = O(E + N)
        //
        //- Let ∣V∣ be the number of nodes in the graph, then the number of edges would be ∣V∣−1 as specified in the problem.
        //+ Time Complexity: O(∣V∣)
        //+ Space Complexity: O(∣V∣)
        //
        //#Reference:
        //2603. Collect Coins in a Tree
        int n=4;
        int[][] edges={{1,0},{1,2},{1,3}};
        //queue : 0,2,3, remainingNodes=4
        //queue : 2,3, remainingNodes=3
        //queue : 3,1 remainingNodes=2
        //queue : , remainingNodes=1
        System.out.println(findMinHeightTrees(n, edges));
        System.out.println(findMinHeightTreesRefactorRemoveNodes(n, edges));
    }
}
