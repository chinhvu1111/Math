package contest;

import java.util.*;

public class E141_FindMinimumDiameterAfterMergingTwoTrees {

    public static List<Integer> findMinHeightTreesRefactorRemoveNodes(int n, int[][] edges) {
        if(n<2){
            List<Integer> centroids=new ArrayList<>();

            for(int i=0;i<n;i++){
                centroids.add(i);
            }
            return centroids;
        }
        ArrayList<Integer>[] adjNodes = new ArrayList[n];
        int[] inDegree=new int[n];
        for(int i=0; i<n; i++){
            adjNodes[i] = new ArrayList<>();
        }

        for(int[] edge:edges){
            int x=edge[0];
            int y=edge[1];
            adjNodes[x].add(y);
            adjNodes[y].add(x);
            inDegree[x]++;
            inDegree[y]++;
        }
        Queue<Integer> nodes=new LinkedList<>();

        for(int i=0;i<n;i++){
            if(inDegree[i]==1){
                nodes.add(i);
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

    public static int getMaxHeight(int node, HashMap<Integer, HashSet<Integer>> adjNodes, boolean[] visited){
        int curHeight=0;
        HashSet<Integer> adj=adjNodes.get(node);
        if(adj==null){
            return curHeight;
        }

        for(int e: adj){
            if(!visited[e]){
                visited[e]=true;
                curHeight=Math.max(curHeight, getMaxHeight(e, adjNodes, visited)+1);
                visited[e]=false;
            }
        }
        return curHeight;
    }

    public static int getLongestPath(HashMap<Integer, HashSet<Integer>> adjNodes, int node, int prevNode, int[] rs){
        //       A
        //     /   \
        //   B      C
        // /  \
        //E    F
        //      \
        //       H
        //        \
        //         K
        //- Hoàn toàn có thể có case: E - B - F - H -K
        //C1:
        //  + Left: Độ dài height left tree
        //  + Right: Độ dài height right tree
        //  + Mỗi điểm ta xét:
        //      + max(left, right) : Đẩy lên trên
        //      + left+right: Tính vào rs luôn
        //- Trong tree 1 node có thể connect N nodes
        //  + Cần lấy 2 giá trị : first, second
        int firstVal=0;
        int secondVal=0;
        HashSet<Integer> adj=adjNodes.get(node);

        if(adj==null){
            return 1;
        }
        for(Integer nextNode: adj){
            if(nextNode==prevNode){
                continue;
            }
            int nextVal = getLongestPath(adjNodes, nextNode, node, rs);
            if(firstVal<nextVal){
                secondVal=firstVal;
                firstVal=nextVal;
            }else if(secondVal<nextVal){
                secondVal=nextVal;
            }
        }
//        System.out.printf("Node:%s, left:%s, right:%s\n", node, firstVal, secondVal);
        rs[0]=Math.max(rs[0], firstVal+secondVal+1);
        return firstVal+1;
    }

    public static int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        //Space: O(V1+E1+V2+E2)
        HashMap<Integer, HashSet<Integer>> adjNodes1=new HashMap<>();
        HashMap<Integer, HashSet<Integer>> adjNodes2=new HashMap<>();
        int maxNodeLeft=0;
        int maxNodeRight=0;

        //Time : O(E1)
        for(int[] edge:edges1){
            int x=edge[0];
            int y=edge[1];
            maxNodeLeft=Math.max(Math.max(x, y), maxNodeLeft);
            adjNodes1.computeIfAbsent(x, k -> new HashSet<>());
            adjNodes1.computeIfAbsent(y, k -> new HashSet<>());
            adjNodes1.get(x).add(y);
            adjNodes1.get(y).add(x);
        }

        //Time : O(E2)
        for(int[] edge:edges2){
            int x=edge[0];
            int y=edge[1];
            maxNodeRight=Math.max(Math.max(x, y), maxNodeRight);
            adjNodes2.computeIfAbsent(x, k -> new HashSet<>());
            adjNodes2.computeIfAbsent(y, k -> new HashSet<>());
            adjNodes2.get(x).add(y);
            adjNodes2.get(y).add(x);
        }
        //Time: O(V1+E1)
        Integer rootLeft = findMinHeightTreesRefactorRemoveNodes(maxNodeLeft+1, edges1).get(0);
        //Time: O(V2+E2)
        Integer rootRight = findMinHeightTreesRefactorRemoveNodes(maxNodeRight+1, edges2).get(0);
        //Space: O[V1]
        boolean[] visitedLeft=new boolean[maxNodeLeft+1];
        //Space: O[V2]
        boolean[] visitedRight=new boolean[maxNodeRight+1];
        visitedLeft[rootLeft]=true;
        visitedRight[rootRight]=true;
        //Space: O[V1]
        //Space: O[V2]
        //Time: O(V1+E1)
        int heighLeft=getMaxHeight(rootLeft, adjNodes1, visitedLeft);
        //Time: O(V1+E1)
        int heighRight=getMaxHeight(rootRight, adjNodes2, visitedRight);
//        if(heighLeft+heighRight==0){
//            return 0;
//        }
        int[] maxLenLeft=new int[1];
        int[] maxLenRight=new int[1];
        //Time: O(V1+E1)
        maxLenLeft[0]= Math.max(getLongestPath(adjNodes1, rootLeft, -1, maxLenLeft)-1, maxLenLeft[0]-1);
        //Time: O(V2+E2)
        maxLenRight[0] = Math.max(getLongestPath(adjNodes2, rootRight, -1, maxLenRight)-1, maxLenRight[0]-1);
        return Math.max(heighLeft+heighRight+1, Math.max(maxLenLeft[0], maxLenRight[0]));
    }

    public static int minimumDiameterAfterMergeOptimization(int[][] edges1, int[][] edges2) {
        //Space: O(V1+E1+V2+E2)
        HashMap<Integer, HashSet<Integer>> adjNodes1=new HashMap<>();
        HashMap<Integer, HashSet<Integer>> adjNodes2=new HashMap<>();
        int maxNodeLeft=0;
        int maxNodeRight=0;

        //Time : O(E1)
        for(int[] edge:edges1){
            int x=edge[0];
            int y=edge[1];
            maxNodeLeft=Math.max(Math.max(x, y), maxNodeLeft);
            adjNodes1.computeIfAbsent(x, k -> new HashSet<>());
            adjNodes1.computeIfAbsent(y, k -> new HashSet<>());
            adjNodes1.get(x).add(y);
            adjNodes1.get(y).add(x);
        }

        //Time : O(E2)
        for(int[] edge:edges2){
            int x=edge[0];
            int y=edge[1];
            maxNodeRight=Math.max(Math.max(x, y), maxNodeRight);
            adjNodes2.computeIfAbsent(x, k -> new HashSet<>());
            adjNodes2.computeIfAbsent(y, k -> new HashSet<>());
            adjNodes2.get(x).add(y);
            adjNodes2.get(y).add(x);
        }
        int[] maxLenLeft=new int[1];
        int[] maxLenRight=new int[1];
        //Time: O(V1+E1)
        maxLenLeft[0]= Math.max(getLongestPath(adjNodes1, 0, -1, maxLenLeft)-1, maxLenLeft[0]-1);
        if(adjNodes1.isEmpty()){
            maxLenLeft[0]=0;
        }
        //Time: O(V2+E2)
        maxLenRight[0] = Math.max(getLongestPath(adjNodes2, 0, -1, maxLenRight)-1, maxLenRight[0]-1);
        if(adjNodes2.isEmpty()){
            maxLenRight[0]=0;
        }
//        System.out.printf("%s %s\n", maxLenLeft[0], maxLenRight[0]);
        return Math.max((maxLenLeft[0]+1)/2+(maxLenRight[0]+1)/2+1, Math.max(maxLenLeft[0], maxLenRight[0]));
    }

    public static void main(String[] args) {
        //* Requirement
        //- There exist two (undirected trees) with n and m nodes, numbered from 0 to n - 1 and from 0 to m - 1, respectively.
        //- You are given two 2D integer arrays edges1 and edges2 of lengths (n - 1) and (m - 1), respectively,
        // where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree
        // and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.
        //- You must connect one node from the first tree with another node from the second tree with an edge.
        //* Return the minimum possible diameter of the resulting tree.
        //* The diameter of a tree is (the length of the longest path) between "any" (two nodes) in the tree.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n, m <= 10^5
        //edges1.length == n - 1
        //edges2.length == m - 1
        //edges1[i].length == edges2[i].length == 2
        //edges1[i] = [ai, bi]
        //0 <= ai, bi < n
        //edges2[i] = [ui, vi]
        //0 <= ui, vi < m
        //The input is generated such that edges1 and edges2 represent valid trees.
        //+ N và M khá lớn ==> Chỉ có thể O(N)/ O(M) thôi
        //
        //- Brainstorm
        //- Tìm khoảnh cách thì Ok
        //- Nhưng nối ntn?
        //- Tốt nhất là nối 2 root của 2 tree
        //
        //- Root của 2 tree được định nghĩa là node:
        //  + Có height ==> all nodes là min.
        //
        //* Lỗi sai:
        //- Không hiểu rõ length có nghĩa là ntn?
        //  + Số node
        //  + Số lượng các edges đi qua
        //==> Số lượng cách edges = length
        //* Cái này cần hỏi interviewer trước khi làm.
        //
//        int[][] edges1 = {{0,1},{0,2},{0,3},{2,4},{2,5},{3,6},{2,7}},
//                edges2 = {{0,1},{0,2},{0,3},{2,4},{2,5},{3,6},{2,7}};
//        int[][] edges1 = {},
//                edges2 = {};
        int[][] edges1 = {{0,1},{2,0},{3,2},{3,6},{8,7},{4,8},{5,4},{3,5},{3,9}},
                edges2 = {{0,1},{0,2},{0,3}};
//        int[][] edges1 = {},
//                edges2 = {{0,1},{1,2}};
        //         0 --- 2
        //           \    \
        //            1    3 -- 6
        //               /  \
        //              9    5 -- 4 -- 8 -- 7
        //      0
        //    /  \  \
        //   1    2  3
        //- Giả sử:
        //  + root = 3 : ==> Max height = 4
        //
        //* Kinh nghiệm:
        //- Tìm max nhất giữa 2 node trong 1 tree dùng DFS có 2 cách:
        //  + Return max(first_depth, second_depth)
        //      + rs = max(first_depth + second_depth)
        //  + Pass depth vào:
        //      + max(first_depth + second_depth - 2*cur_depth)
        //      + return first_depth
        //- Ngoài ra còn 1 cách là chọn bừa 1 root ==> tính... (Xemn lại phần topological sort để rõ)
        //- Nhớ truyền rs[0] vào method + lấy max bên ngoài thì:
        //  + rs[0] = max(function(rs), rs[0])
        //  Nếu để:
        //      + rs[0] =  max(rs[0], function(rs))
        //      ==> SAI vì rs[0] chưa được updated + function(rs) return lại không phải giá trị MAX
        //          + Giá trị max thực ra là 1 bước intermediat được gán bên trong.
        //
        //- Tìm root để min height ntn:
        //  + Cut từng layer từ leaf nodes --> centroid nodes.
        //  ==> Cho đến khi remaning node <=2
        //- Remaining node -=size (Size theo từng size của leaf nodes)
        //** CT:
        //- Remaining node = 1 : the number of edges = (2 * layers)
        //- Remaining node = 2 : the number of edges = (2 * layers + 1)
        //
        //* Tại sao không phải là 3 centroid nodes?
        //+ Nếu 3 nodes:
        //    3
        //  /   \
        // 1     2
        //==> Nó sẽ build được ntn ==> Xén được thêm 1 layer leaf nodes nữa
        //+ Nếu 2 centroid nodes:
        // 3 -- 2
        //==> 3 và 2 sẽ đối xứng vai trò như nhau ==> Có thể được.
        //  ==> Xén layer cũng khá đều r
        //
        //1.1, Optimization
        //- Bài này không cần xác định root chỉ cần:
        //  + Tìm longest path giữa 2 nodes của 2 trees là được
        //* CT:
        //- longest path giữa 2 nodes:
        //+ (Max height) với optimal root = (longest path giữa 2 nodes + 1)/2
        //Ex:
        //- Số edges lẻ
        //      1
        //    /  \
        //  2     5
        //   \
        //    3
        //+ max path = 5 - 1 - 2 - 3 = 3 (edges)
        //Ex:
        //- Số edges chẵn
        //      1
        //    /  \
        //  2     5
        //   \     \
        //    3     7
        //+ max path =7 - 5 - 1 - 2 - 3 = 4 (edges)
        //+ Root sẽ nằm trên path dài nhất đó ==> Để min height
        //  ==> heigh <= max path đó
        //+ Số remaining node chẵn: Có 2 root
        //  ==> remaining node không phải số nodes.
        //+ Số remaining node lẻ: Có 1 root
        //
        //- Số edges chẵn(even):
        //  + Số node lẻ: ==> Có thể 1 root
        //      + max height =
        //  + Số node chẵn: ==> Có thể 2 root
        //* CT:
        //+ max height = (longest_path+1)/2
        //** Cơ chế : (x+1)/2
        //  ==> Để bổ sung tính chất cho x lẻ ==> Vẫn làm tròn lên
        //  - Số chẵn sẽ không tác dụng gì
        //- Ở đây ta thấy nếu số edges lẻ ==> Ta vẫn sẽ cho kết quả == (e+1)/2
        //
        //- Sửa lại phần tìm longest path:
        //  + Trả lại số node
        //  + max(first+second+1) ==> Chính là lấy max số node
        //==> The longest path = max (the number of node) - 1
        //
        //* Int/dư ==> Luôn làm tròn xuống (Floor)
        System.out.println(13/5);
        System.out.println(5/2);
        //1.2, Complexity:
        //- Space: O(V1+E1+V2+E1)
        //- Time: O(V1+E1+V2+E1)
        //
        System.out.println(minimumDiameterAfterMerge(edges1, edges2));
        System.out.println(minimumDiameterAfterMergeOptimization(edges1, edges2));
        //
        //#Reference:
        //693. Binary Number with Alternating Bits
        //1760. Minimum Limit of Balls in a Bag
        //160. Intersection of Two Linked Lists
    }
}
