package E1_UnionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E3_RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable {

    public static int[] findParent(int[] parent, int node){
//        int prevNode=node;
        int depth=1;
        int initNode=node;

        while(parent[node]!=node){
//            prevNode=node;
            node=parent[node];
            depth++;
        }
        parent[initNode]=node;
        return new int[]{node, depth};
    }

    public static int addEdge(int u, int v, int[] parent){
        int[] parentU=findParent(parent, u);
        int[] parentV=findParent(parent, v);
//        System.out.printf("u: %s, parentu: %s\n", u, parentU[0]);
//        System.out.printf("v: %s, parentV: %s\n", v, parentV[0]);

        //1 -> 2
        //2 -> 3
        //==> parent(1) != parent(3) ==> Nhưng findParent(1)==findParent(3)
        //==>
        if(parentU[0]!=parentV[0]){
            if(parentU[1]>parentV[1]){
                parent[parentV[0]]=parentU[0];
            }else{
                parent[parentU[0]]=parentV[0];
            }
            return 0;
        }
        return 1;
    }

    public static boolean isValidTraverse(int[] parents, int n){
        for(int i=0;i<n;i++){
            if(parents[i]!=-1&&parents[i+1]!=-1&&findParent(parents, i)[0]!=findParent(parents, i+1)[0]){
//                System.out.printf("%s %s,", parents[i], parents[i+1]);
//                System.out.println();
                return false;
            }
        }
//        System.out.println();
        return true;
    }

    public static int maxNumEdgesToRemove(int n, int[][] edges) {
        //Space: O(V)
        int[] parents=new int[n+1];
        Arrays.fill(parents, -1);
        //Space: O(E)
        List<int[]> commonEdges=new ArrayList<>();
        List<int[]> aliceEdges=new ArrayList<>();
        List<int[]> bobEdges=new ArrayList<>();
        int m=edges.length;

        //Time: O(E)
        for(int i=0;i<m;i++){
            int[] edge=edges[i];
            parents[edge[1]]=edge[1];
            parents[edge[2]]=edge[2];
            if(edge[0]==1){
                aliceEdges.add(new int[]{edge[1], edge[2]});
            }else if(edge[0]==2){
                bobEdges.add(new int[]{edge[1], edge[2]});
            }else{
                commonEdges.add(new int[]{edge[1], edge[2]});
            }
        }
//        System.out.printf("%s %s %s\n", commonEdges.size(), aliceEdges.size(), bobEdges.size());
        int numRemovedEdge=0;

        for(int i=0;i<commonEdges.size();i++){
            int[] curEdge=commonEdges.get(i);
            //Time: O(E)
            numRemovedEdge+=addEdge(curEdge[0],curEdge[1], parents);
        }
//        System.out.println("Alice");
        int[] copyOfCommon=Arrays.copyOf(parents, n+1);
        for(int i=0;i<aliceEdges.size();i++){
            int[] curEdge=aliceEdges.get(i);
            //Time: O(E)
            numRemovedEdge+=addEdge(curEdge[0],curEdge[1], parents);
        }
        if(!isValidTraverse(parents, n)){
            return -1;
        }
//        System.out.println("Bob");
        for(int i=0;i<bobEdges.size();i++){
            int[] curEdge=bobEdges.get(i);
            //Time: O(E)
            numRemovedEdge+=addEdge(curEdge[0],curEdge[1], copyOfCommon);
        }
        if(!isValidTraverse(copyOfCommon, n)){
            return -1;
        }
        return numRemovedEdge;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given the undirected graph that has 3 types of edges:
        //+ Type 1: Can be traversed by Alice only.
        //+ Type 2: Can be traversed by Bob only.
        //+ Type 3: Can be traversed by both Alice and Bob.
        //- edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between nodes ui and vi
        //* Find the maximum number of edges you can remove so that after removing the edges,
        // the graph can still be fully traversed by (both) Alice and Bob.
        //- Return -1 nếu graph cannot fully traverse by (both) Alice and Bob.
        //
        //** Idea
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= n <= 10^5
        //1 <= edges.length <= min(10^5, 3 * n * (n - 1) / 2)
        //edges[i].length == 3
        //1 <= typei <= 3
        //1 <= ui < vi <= n
        //All tuples (typei, ui, vi) are distinct.
        //
        //- UNION FIND theory:
        //+ Union find is preferred:
        //  + When the relationships between nodes are changing.
        //+ DFS is preferred:
        //  + Solving fixed undirected graph where relationships never change
        //  + Solving (directed graph) problems
        //
        //- Brainstorm
        //Ex:
        //Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
        //Output: 2
        //Explanation: If we remove the 2 edges [1,1,2] and [1,1,3].
        // The graph will still be fully traversable by Alice and Bob.
        // Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.
        //
        //- Tìm số lượng edge lớn nhất mà ta có thể delete đi sao cho (each node) can traverse to (other nodes)
        //+ Bài này có thể quy về bài xoá 1 vài edges thì các collections đủ các node được không
        //  + Vấn đề nằm ở chỗ là nó các path mà chỉ 1 số thằng qua được
        //  + Liệu ta có thể chia theo 2 kiểu alice đi và bob đi được không?
        //- Nếu ta xoá đi và xét chỉ với từng người bob và alice thì kết quả chung sẽ là tổ hợp của cả 2
        //+ --> Ta sẽ xét với từng người
        //- Ở đây ta sẽ dùng phương pháp UNION FIND để làm
        //- Nếu xét riêng rẽ thì xoá 1 cạnh sẽ có 2 cases:
        //+ Delete the edges owned by Alice or Bob
        //+ Delete the edges could be traversed by (both) (Alice and Bob)
        //--> Thứ tự ưu tiên xoá như thế nào?
        //- Sẽ có cases ta cần xoá edges có thể traversed by both (Alice and Bob)
        //
        //- So sánh sự khác nhau giữa việc xoá between (type 1/2 edge and type 3 edge)
        //- Để 1 cạnh có thể xoá được thì có thể xảy ra cases:
        //+ Sẽ luôn 1 cạnh type 1/2 giữa 2 nodes đã cho ==> Ta xoá loại 1/2/3 kết quả như nhau
        //  + Nếu xoá loại 1/2 --> Ta vẫn còn loại 3 + Với việc xoá (1/2) thì sẽ it rủi ro hơn
        //+ Sẽ không có cạnh giữa 2 nodes nhưng sẽ có đường chạy edges dài giữa các nodes có trong
        //  Ex:
        //  (1) <--type-1--> (2) <--type-1--> (3) <--type-1--> (4) và (1 <-> 4)
        //  + Ở đây ta vẫn ưu tiên xoá type 1/2
        //==> Chốt xoá type 1/2 ==> Union thì ta sẽ ưu tiên add nhưng edge loại 1/2 trước là được.
        //
        //- Ta sẽ call union find cho type 3 edge trước :
        // + Cái này dùng chung cho cả Alice and Bob
        //- Sau đó làm cho từng Alice và Bob
        //
        //- Đại loại là UNION FIND:
        //  + Dùng common edges ==> reduce chính nó xuống
        //      + Ra được số common edges có thể removed
        //  + Dùng alice ==> Reduce
        //      + Sau khi reduce, cần check all nodes:
        //          + Đôi 1 có parent[e[i]]==parent[e[1]] hay không?
        //          ** = nhau thì có nghĩa là connected lẫn nhau
        //  + Tương tự với Bob
        //
        //- Return -1 when graph could not traversed by Alice and Bob
        //
        //1.1, Implementation
        //- Parent check node
        //- Check the collection is connected
        //  + Add an edge --> Check two nodes whether they are connected or not
        //  + Connected : Remove
        //  + Not connected : Add
        //
        //1.2, Optimization
        //1.3, Complexity
        //- Space: O(V+E)
        //- Time: O(E)
        //Time complexity: O(E∗α(N)).
        //
        //We iterate over edges, and for every edge we call the function performUnion(),
        // whose time complexity is equal to O(α(N) as we have included union by size as well as path compression.
        // Therefore, the total time complexity is equal to O(E∗α(N)).
        //* Note that α is the Inverse Ackermann function which grows so slowly that it can be considered as O(1).
        //
        int n = 4;
        int[][] edges = {{3,1,2},{3,2,3},{1,1,3},{1,2,4},{1,1,2},{2,3,4}};
//        int n = 4;
//        int[][] edges = {{3,1,2},{3,2,3},{1,1,4},{2,1,4}};
//        int n = 4;
//        int[][] edges = {{3,2,3},{1,1,2},{2,3,4}};
        System.out.println(maxNumEdgesToRemove(n, edges));
        //
        //#Reference:
        //2360. Longest Cycle in a Graph
        //2382. Maximum Segment Sum After Removals
        //2782. Number of Unique Categories
    }
}
