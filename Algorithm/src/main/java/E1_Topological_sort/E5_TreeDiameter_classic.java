package E1_Topological_sort;

import java.util.*;

public class E5_TreeDiameter_classic {

//    public static HashMap<Integer, List<Integer>> adjNodes;
//    public static int rs;
//
//    public static int dfs(int currentNode, boolean[] visited, int[] depth, int index){
//        if(depth[currentNode]!=-1){
//            return depth[currentNode];
//        }
//        visited[currentNode]=true;
//        int max=0;
//        if(adjNodes.get(currentNode).size()!=0){
//            for(Integer node: adjNodes.get(currentNode)){
//                if(visited[node]){
//                    continue;
//                }
//                max=Math.max(max, dfs(node, visited, depth, 1));
//            }
//        }
//        visited[currentNode]=false;
//        rs=Math.max(rs, index+max);
//        System.out.println(rs);
//        return depth[currentNode]=index+max;
//    }

    //* Method-1 without optimizing
    public static Queue<Integer> findCentroidNode(HashMap<Integer, HashSet<Integer>> adjNodes, HashMap<Integer, Integer> inDegree){
        Queue<Integer> nodes=new LinkedList<>();
//        System.out.println(inDegree);

        for(int key: inDegree.keySet()){
            if(inDegree.get(key)==1){
                nodes.add(key);
            }
        }
        int remainingNodes=inDegree.size();

        while (remainingNodes>2){
            Queue<Integer> nextNewNodes=new LinkedList<>();
//            System.out.println(nodes);
            remainingNodes-=nodes.size();

            while (!nodes.isEmpty()){
                int currentLeaf=nodes.poll();
//                System.out.printf("%s ,%s\n", currentLeaf, adjNodes.get(currentLeaf));

                for(int neighNode: adjNodes.get(currentLeaf)){
                    int newValue=inDegree.get(neighNode)-1;
                    inDegree.put(neighNode, newValue);
//                    System.out.printf("Neigh : %s %s\n",neighNode, newValue);

                    if(newValue==1){
                        nextNewNodes.add(neighNode);
                    }
                }
            }
//            System.out.printf("Next %s\n", nextNewNodes);
            nodes=nextNewNodes;
        }
//        System.out.println(inDegree);
        return nodes;
    }

    public static int rs;

    public static int getMinpath(int node,  HashMap<Integer, HashSet<Integer>> adjNodes, boolean[] visited){
        if(visited[node]){
            return 1;
        }
        HashSet<Integer> adj=adjNodes.get(node);
        visited[node]=true;
        int firstGreaterNode=0;
        int secondGreaterNode=0;

        for(Integer neighbor: adj){
            if(visited[neighbor]){
               continue;
            }
            int currentHeight=getMinpath(neighbor, adjNodes, visited);

            if(currentHeight>firstGreaterNode){
                secondGreaterNode=firstGreaterNode;
                firstGreaterNode=currentHeight;
            }else if(secondGreaterNode<currentHeight){
                secondGreaterNode=currentHeight;
            }
        }
//        System.out.printf("Node %s, first: %s, second: %s, max: %s\n", node,firstGreaterNode, secondGreaterNode, firstGreaterNode+secondGreaterNode);
        rs=Math.max(rs, firstGreaterNode+secondGreaterNode);
        visited[node]=false;
        return firstGreaterNode+1;
    }

    public static int findCentroidNodeOptimization(HashMap<Integer, HashSet<Integer>> adjNodes, HashMap<Integer, Integer> inDegree){
        Queue<Integer> nodes=new LinkedList<>();
//        System.out.println(inDegree);

        for(int key: inDegree.keySet()){
            if(inDegree.get(key)==1){
                nodes.add(key);
            }
        }
        int remainingNodes=inDegree.size();
        int layers=0;

        while (remainingNodes>2){
            Queue<Integer> nextNewNodes=new LinkedList<>();
            remainingNodes-=nodes.size();

            while (!nodes.isEmpty()){
                int currentLeaf=nodes.poll();

                for(int neighNode: adjNodes.get(currentLeaf)){
                    int newValue=inDegree.get(neighNode)-1;
                    inDegree.put(neighNode, newValue);

                    if(newValue==1){
                        nextNewNodes.add(neighNode);
                    }
                }
            }
            nodes=nextNewNodes;
            layers++;
        }
        if(nodes.size()==1){
            return layers*2;
        }
//        System.out.println(inDegree);
        return layers*2+1;
    }

    public static int treeDiameterOptimization(int[][] edges) {
        if(edges.length==0){
            return 0;
        }
        //Space : O(E*2)
        HashMap<Integer, HashSet<Integer>> adjNodes=new HashMap<>();
        //Space : O(N)
        HashMap<Integer, Integer> inDegree=new HashMap<>();
        int max=0;

        //Time : O(E)
        for(int[] edge: edges){
            int x=edge[0];
            int y=edge[1];
            inDegree.put(x, inDegree.getOrDefault(x, 0)+1);
            inDegree.put(y, inDegree.getOrDefault(y, 0)+1);
            adjNodes.computeIfAbsent(x, k-> new HashSet<>());
            adjNodes.computeIfAbsent(y, k-> new HashSet<>());
            adjNodes.get(x).add(y);
            adjNodes.get(y).add(x);
            max=Math.max(max, Math.max(x, y));
        }
        if(inDegree.size()==1){
            return 0;
        }

        //Time : O(N)
        return findCentroidNodeOptimization(adjNodes, inDegree);
    }

    public static int treeDiameter(int[][] edges) {
        if(edges.length==0){
            return 0;
        }
        rs=1;
        HashMap<Integer, HashSet<Integer>> adjNodes=new HashMap<>();
        HashMap<Integer, Integer> inDegree=new HashMap<>();
        int max=0;
        int root=0;

        for(int[] edge: edges){
            int x=edge[0];
            int y=edge[1];
            root=x;
            inDegree.put(x, inDegree.getOrDefault(x, 0)+1);
            inDegree.put(y, inDegree.getOrDefault(y, 0)+1);
            adjNodes.computeIfAbsent(x, k-> new HashSet<>());
            adjNodes.computeIfAbsent(y, k-> new HashSet<>());
            adjNodes.get(x).add(y);
            adjNodes.get(y).add(x);
            max=Math.max(max, Math.max(x, y));
        }
        if(inDegree.size()==1){
            return 0;
        }

//        Queue<Integer> centroidsNode=findCentroidNode(adjNodes, inDegree);
//        root=centroidsNode.poll();
        boolean[] visited=new boolean[max+1];
        visited[root]=true;

        getMinpath(root, adjNodes, visited);
        return rs;
    }

    public static int[] findFarthestNode(int node, int max, HashMap<Integer, HashSet<Integer>> adjNodes){
        Queue<Integer> nodes=new LinkedList<>();
        int[] depth=new int[max+1];
        boolean[] visited=new boolean[max+1];
        depth[node]=1;
        nodes.add(node);
        int maxDepth=0;
        int nodeWithMaxDepth=0;

        while (!nodes.isEmpty()){
            int currentNode=nodes.poll();
            visited[currentNode]=true;

            for(Integer neighborNode:adjNodes.get(currentNode)){
                if(visited[neighborNode]){
                    continue;
                }
                depth[neighborNode]=depth[currentNode]+1;
                if(maxDepth<depth[neighborNode]){
                    maxDepth=depth[neighborNode];
                    nodeWithMaxDepth=neighborNode;
                }
                nodes.add(neighborNode);
            }
        }
        return new int[]{nodeWithMaxDepth, maxDepth};
    }

    public int treeDiameterMethod3(int[][] edges) {
        if(edges.length==0){
            return 0;
        }
        HashMap<Integer, HashSet<Integer>> adjNodes=new HashMap<>();
        int max=0;
        int root=0;

        for(int[] edge: edges){
            int x=edge[0];
            int y=edge[1];
            root=x;
            adjNodes.computeIfAbsent(x, k-> new HashSet<>());
            adjNodes.computeIfAbsent(y, k-> new HashSet<>());
            adjNodes.get(x).add(y);
            adjNodes.get(y).add(x);
            max=Math.max(max, Math.max(x, y));
        }
        if(adjNodes.size()==1){
            return 0;
        }
        //- First run
        //- Second run
        int[] nodeInfor=findFarthestNode(root, max, adjNodes);
        nodeInfor=findFarthestNode(nodeInfor[0], max, adjNodes);
        return nodeInfor[1]-1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- The (diameter) of a tree is the number of edges in the longest path in that tree.
        //--> Diameter của 1 tree là số lượng edges trong 1 đường dài nhất của tree đó = (số node đi qua - 1)
        //-
        //- Cho 1 array là 2D với edges[i] = [a,b] chỉ undirected edge giữa a và b.
        //
        //** Idea
        //* Method-1 : Dựa trên kết quả số lượng centroid nodes --> Ta có công thức tính trên layer
        //1.
        //1.0,
        //- Constraint
        //n == edges.length + 1
        //1 <= n <= 10^4
        //0 <= ai, bi < n
        //ai != bi
        //
        //- Brainstorm
        //- Ta sẽ tìm root trước sau đó là traverse --> ra leaf nodes ==> return max length
        //+ Tìm (root/ leaf) trong (undirected graph) như thế nào
        //* Trong undirected graph:
        //- Mọi điểm có thể là root
        //- Điểm mà chỉ có inDegree[i] + outDegree[i]=1 ==> Nó chính là leaf
        //==> Traverse all leaf nodes thì sao?
        //- Worst case : N leaf nodes ==> 1 root nodes ==> height --> MIN : OK
        //==> Vẫn bị time limit.
        //
        //- Sau đó ta dùng dfs để tìm max
        //
        //* Vấn đề ở đây là lưu lại các giá trị tính trước như thế nào trong khi đây là undirected graph --> Đồ thị có thể quay trở lại node cũ.
        //
        //- Nó sẽ bị case đặc biệt sau:
        //            1
        //          /   \   \
        //        0      2   4
        //               |   |
        //               3   5
        //+ Nếu ta dfs từ 0 --> return 3 edges
        //+ Nếu ta dfs từ 1 --> return 2 edges
        //+ Nếu ta dfs từ 3 --> return 4 edges
        //
        //- Sau khi làm bài E4 và E5 --> thì bài này là tổng hợp của 2 bài đó với các steps:
        //+ Tìm centroids nodes
        //+ Tìm min height
        //
        //** Câu hỏi:
        //- Liệu chọn root node các điểm khác ngoài centroid node + thực hiện phương pháp tìm max height thì có thể tìm được max height hay không?
        //** Chọn root nào cũng được ==> Ta cũng có thể tìm được kết quả
        //- Tìm 1 ví dụ chứng mình là nếu chọn root đó sẽ cho kết quả sai ==> Không có trường hợp nào
        //VD:
        //                                1 (centroid node)
        //                           /   \     \
        //                         0      2     4
        //                              /        \
        //                             3         5
        //+ If we choose 1 as root node (centroid ode), the number of edges : 4
        //
        //VD:
        //                          3
        //                            \
        //                             2
        //                              \
        //                               1
        //                             /   \
        //                            0     4
        //                                   \
        //                                    5
        //
        //- Ở đây ta chỉ cần tìm centroid nodes + đếm số layer là ta có thể ra được kết quả.
        //
        //1.1, Optimization
        //- Áp dụng công thức tính từ bài E3
        //  + The number of edges in the longest path = 2*layers/ (2*layers+1)
        //- Cần
//        int[][] edges={{0,1},{1,2},{2,3},{1,4},{4,5}};
//        int[][] edges={{0,1},{1,2},{0,3},{3,4},{2,5},{3,6}};
        //
        //1.2, Complexity :
        //- Time complexity : O(N+E)
        //- Space complexity : O(E*2+N)
        //
        //* Method-2: Ta sẽ thực hiện chọn root tuỳ ý và tìm max giữa (left + right) của subnode ==> root.
        //2.
        //2.0, Idea
        //- Đọc lại E3
        //
        //* Method-3:
        //3.
        //3.0, Idea
        //- Ta sẽ cũng chọn 1 root node nào
        //+ Sau đó sẽ tìm leaf node xa nhất với root đó ==> mark visited
        //+ Sau đó call lần 2 đứng ở leaf node đó --> Tìm node xa nhất
        //==> Tổng khoảng cách ở step 2 chính là distance cần tìm.
        //* CM : Vì là ta tìm node farthest node ==> Khoảng cách về cơ bản sẽ là xa nhất có thể:
        //+ Đi qua root node
        //+ Không đi qua root node
        //
        //#Reference:
        //1617. Count Subtrees With Max Distance Between Cities
        int[][] edges={};
        System.out.println(treeDiameter(edges));
        System.out.println(treeDiameterOptimization(edges));
    }
}
