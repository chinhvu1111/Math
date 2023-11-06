package E1_minimum_spaning_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class E4_FindCriticalAndPseudoCriticalEdgesInMinimumSpanningTree {

    public static int[] findParent(int[] parent, int node){
        int prevNode=node;
        int depth=1;

        while(parent[node]!=node){
            node=parent[node];
            parent[prevNode]=node;
            depth++;
        }
        return new int[]{node, depth};
    }

    public static boolean union(int[] parent, int u, int v){
        int[] parentU=findParent(parent, u);
        int[] parentV=findParent(parent, v);

        if(parentU[0]!=parentV[0]){
            if(parentV[1]>parentU[1]){
                parent[parentU[0]]=parentV[0];
            }else{
                parent[parentV[0]]=parentU[0];
            }
            return true;
        }
        return false;
    }

    public static boolean isConnected(int[] parent, int m, int[][] edges){
        for(int i=0;i<m;i++){
            int x=edges[i][0];
            int y=edges[i][1];

            int[] parentU=findParent(parent, x);
            int[] parentV=findParent(parent, y);
            if(parentU[0]!=parentV[0]){
                return false;
            }
        }
        return true;
    }

    public static List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m= edges.length;
        //Space : O(m)
        //Time : O(m)
        int[] isCritical=new int[m];
        Arrays.fill(isCritical, -1);
        //Space : O(m)
        //Time : O(m)
        List<int[]> sortedEdges=new ArrayList<>();
        //Space : O(n)
        //Time : O(n)
        int[] parent=new int[n];

        //Time : O(n)
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
        //Time : O(m)
        for(int i=0;i<m;i++){
            int[] edge=edges[i];
            sortedEdges.add(new int[]{edge[0], edge[1], edge[2], i});
        }
        //Time : m*O(m)
        sortedEdges.sort((a, b) -> (a[2] - b[2]));
        int minWeight=0;

        //Time : O(m)
        for(int i=0;i<m;i++){
            int[] edge=sortedEdges.get(i);
            int x=edge[0];
            int y=edge[1];
            int w=edge[2];
//            System.out.printf("%s %s %s\n", x, y, w);
            if(union(parent, x, y)){
                minWeight+=w;
            }
        }
//        int[] nodeTraverse=new int[n];
//        System.out.println(minWeight);

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                parent[j]=j;
//                nodeTraverse[j]=0;
            }
            int curMinWeight=0;

            for(int j=0;j<m;j++){
                int[] edge=sortedEdges.get(j);
                int index=edge[3];

                if(i==index){
//                    System.out.printf("Ignore index: %s, weight: %s \n", index, edge[2]);
                    continue;
                }
                int x=edge[0];
                int y=edge[1];
                int w=edge[2];

                if(union(parent, x, y)){
//                    nodeTraverse[x]=1;
//                    nodeTraverse[y]=1;
                    curMinWeight+=w;
                    //Nếu nó chưa được đánh dấu là (critical/ pseudo-critical)
                    //+ Để loại trường hợp nó được đánh critical edge rồi ==> revert về (pseudo-critical)
//                    if(isCritical[index]==-1){
//                        isCritical[index]=2;
//                    }
                }
                if(curMinWeight>minWeight){
                    break;
                }
            }
//            System.out.printf("Cur min: %s, index ignore: %s\n", curMinWeight, i);
            //Ta sẽ check curWeight với min weight nếu:
            //+ Thay đổi ==> critical
            //+ Không thay đổi ==> Check xem index cạnh vừa có đã đi qua chưa ==> Nếu chưa đi qua ==> Critical
            if(curMinWeight>minWeight){
                isCritical[i]=1;
//                System.out.printf("1-Critical edge: index: %s\n", i);
            }else if(!isConnected(parent, m, edges)){
                isCritical[i]=1;
//                int[] edge=edges[i];
//                int x=edge[0];
//                int y=edge[1];
//
//                //Vẫn bị case là x và y là root trong unionfind ==> Thế nên không dùng parent được
//                //==> Ta dùng critical cũng không được do critical là để phân biệt edge
//                //
//                if(nodeTraverse[x]==0||nodeTraverse[y]==0){
//                    isCritical[i]=1;
////                    System.out.printf("2-Critical edge: index: %s\n", i);
//                }
            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                parent[j]=j;
//                nodeTraverse[j]=0;
            }
            int[] e=edges[i];
            int curMinWeight=e[2];
//            System.out.printf("Force: %s: %s\n", e[0], e[1]);
            union(parent, e[0], e[1]);

            for(int j=0;j<m;j++){
                int[] edge=sortedEdges.get(j);
                int x=edge[0];
                int y=edge[1];
                int w=edge[2];

                if(union(parent, x, y)){
//                    nodeTraverse[x]=1;
//                    nodeTraverse[y]=1;
                    curMinWeight+=w;
                    //Nếu nó chưa được đánh dấu là (critical/ pseudo-critical)
                    //+ Để loại trường hợp nó được đánh critical edge rồi ==> revert về (pseudo-critical)
//                    if(isCritical[index]==-1){
//                        isCritical[index]=2;
//                    }
                }
            }
            if(curMinWeight>minWeight){
                isCritical[i]=-1;
            }else if(isCritical[i]!=1){
                isCritical[i]=2;
            }
//            System.out.println(curMinWeight);
        }
        //Space : O(m)
        List<Integer> criticalEdges=new ArrayList<>();
        List<Integer> pseudoCriticalEdges=new ArrayList<>();
        List<List<Integer>> rs=new ArrayList<>();

        for(int i=0;i<m;i++){
            if(isCritical[i]==1){
                criticalEdges.add(i);
            }else if(isCritical[i]==2){
                pseudoCriticalEdges.add(i);
            }
        }
        rs.add(criticalEdges);
        rs.add(pseudoCriticalEdges);
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given a weighted undirected connected graph with n vertices numbered from 0 to n - 1,
        // and an array edges where edges[i] = [ai, bi, weighti] represents a bidirectional and
        // weighted edge between nodes ai and bi
        //- A critical edge:
        //+ An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge.
        //- a pseudo-critical edge:
        //+ is that which can appear in some MSTs but (not all).
        //* Return all the (critical) and (pseudo-critical) edges in the given graph's minimum spanning tree (MST).
        //
        //- Tức là ta sẽ có 1 list of MSTs
        //+ Và ta sẽ phải lọc ra các edge mà có trong all MSTs
        //+ Và lọc ra các edges mà có trong 1 số MST (Loại đi trường hợp xuất hiện trong ALL MSTs vì nó trung với cases
        //bên trên.
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Idea:
        //- Constraint:
        //2 <= n <= 100
        //1 <= edges.length <= min(200, n * (n - 1) / 2)
        //edges[i].length == 3
        //0 <= ai < bi < n
        //1 <= weighti <= 1000
        //All pairs (ai, bi) are distinct.
        //
        //- Brainstorm
        //- Ở đây ta có thể dựng rất nhiều MSTs:
        //- Dấu hiệu nhận biết 1 edge là critical edge:
        //+ Nó xuất hiện trong mọi MSTs
        //+ Khi chọn min đến 1 node nào đó ==> Nó sẽ là (duy nhất)
        //Ex:
        //- Để đến được 1 ==> min=2 chỉ có 1 edge duy nhất
        //+ Vấn đề là nó vẫn có thể có case:
        //+ weight(a,b) = weight(a,c): Ta đều có thể đến a nhưng 2 edge có connect đến 2 nodes khác nhau (b/c)
        //Ex:
        //      a --2--  b
        // 2 /    \   / 10
        // c --1 -- d
        //- Ta thấy rằng weight(a,b)=weight(a,c)=2
        //- Nhưng để chọn để dến điểm c ==> Ta sẽ không chọn edge(a,c) vì (c,d) có weight=1 < 2 (weight(a,c))
        //    a --- b
        //      \
        // c --- d
        //** ==> Để quyết định chọn 1 edge thì cần phải phụ thuộc vào cả 2 nodes (Ở 2 points)
        // (a,b)=(a,c)=2 : Đều có thể đến được a với weight=2 nhưng ta sẽ không chọn (a,c) mà sẽ chọn (c,d)
        //  + Đến a min nhưng chưa chắc đến b và c đã MIN ==> Ta hướng đến việc (chọn all min)
        //Ex:
        //Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
        //Output: [[0,1],[2,3,4,5]]
        //
        //+ weight(index_edge=0) = 1,2,3
        //+ weight(index_edge=1) = 1,1,6
        //+ weight(index_edge=2) = 1,2
        //+ weight(index_edge=3) = 2,2,3
        //+ weight(index_edge=4) = 3,4,6
        //+ Vấn đề là khi xét 1 edge(a,b) thì ví dụ node 'b' chưa được xét đến thì sẽ như thế nào
        //  + Ta sẽ so sánh edge đó xem nếu dùng thì có ảnh hưởng đến kết quả của edge kia không
        //  Ex: Nếu thêm edge (a,b) mà weight(a,b) > min_weight(b)
        //  + Vẫn có thể có case mà node(a) chỉ có (1 edge duy nhất/ min rồi) + b đã được đến bằng các edge khác < hơn
        //  ==> Không lo b vì đã có trong MST rồi.
        //+ Nếu a connect đến b, weight(a,b) là min với a nhưng b thì ta có 2 giá trị tương tự như thế
        //VD: weight(a,b) = weight(b,c)
        //  + min với a,b nhưng chưa chắc với c ==> Ta không đánh giá được gì trong trường hợp này
        //
        //- Giả sử ta làm theo UNION FIND
        //Sorted edges=[[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
        //- Quy luật là ta sẽ add dần mỗi edge có weight (min - max) vào trong graph:
        //+ Nếu edge đó thêm được (new node) + (hơi đặc biệt chút) là có (multiple edges) có thể connect
        // đến new node mà cũng (min weight đó)
        //Ex:
        //(0,1,3),(1,2,1),(0,2,1)
        //
        //  0 ---3--- 1
        // (1) \   / (1)
        //       2
        //==> Coi như là (pseudo-critical)
        //
        //- Nếu mà new edge được thêm mà connect đến new edge mà chỉ có 1 weight duy nhất
        //==>
        //Ex
        //(0,1,3),(1,2,1),(0,3,3)
        //
        //- Nếu add new edge mà ta được 2 new nodes + và sau đó ta có thêm edge với weight tương tự nhưng đến được
        // 1 node trước đó
        //          0       3
        //        /       /
        //      1  ---- 2
        //Ex:
        //(0,1,3), (3,2,1), (1,2,1)
        //==> Ở đây khi add (1,2) ==> Ta thấy là (1,2) không cùng parent ==> Ta vẫn coi như là 1 critical edge cho đến khi
        // ta tìm được 1 edge tương tự kết nối đển được (1/2) mà cùng chung weight.
        //+ Cùng chung weight(a,b) thì nên chọn cái nào không phải là critical a hay b ==> WRONG IDEA
        //  + Cái ta cần phân biệt là edge chứ không phải points ==> Cần xác định 1 edge có phải (critical edge)
        //Ex
        //  0 ---3--- 1
        // (1) \   / (1)
        //       2
        //- Ta thấy rằng ví dụ bên trên ta có thể chọn (1,2)/(0,2) thì đếu có thể connect đến được 2 cùng weight [Đều được]
        //+ Khi add (1,2) thì nó (chung parent) ==> Về cơ bản việc "chọn trước" sẽ có thể
        //  + Tốt hơn: Tốn weight ít hơn
        //  + Bằng : Tốn weight tương đương
        //+ 2 cạnh lựa chọn ở đây sẽ chung node=2 : edge(0,2), edge(1,2)
        //
        //** Khá là khó để tìm được critical edge mà không phải loại bỏ các edge + build MST traditionally
        //- Idea
        //- Ta sẽ dựng 1 MST với weight min trước
        //- Sau đó sẽ bỏ từng edge đi để so sánh weight trước và sau khi remove nó ==> Sẽ ntn thế nào?
        //  + Nếu min weight giữ nguyên + có đi qua trong quá trình build MST (trong all cases) ==> Đánh dấu là (pseudo-critical edge)
        //  + Nếu min weight thay đổi ==> Critical edge
        //
        //+ Vì weight increase mà ==> Các edge sau luôn tệ hơn ==> new edge là critical.
        //
        //1.1, Implementation
        //- Ignore edge by index
        //- Parent ta sẽ tạo lại mỗi lần build MST
        //- Specical cases:
//        int n = 5;
//        int[][] edges = {{0,1,1},{1,2,1},{2,3,2},{0,3,2},{0,4,3},{3,4,3},{1,4,6}};
//        int n = 4;
//        int[][] edges = {{0,1,1},{1,2,1},{2,3,1},{0,3,1}};
//        int n = 6;
//        int[][] edges = {{0,1,1},{1,2,1},{0,2,1},{2,3,4},{3,4,2},{3,5,2},{4,5,2}};
        //              0
        //           /    \
        //         1  ---- 2
        //                  \
        //                   3
        //                /    \
        //              4  ---- 5
        //+ Tất các node đều đi qua ==> Nhưng chúng không connect với nhau
        //  + Ví dụ trên thì (2,3) đã được đi qua nhưng nếu không có edge(2,3) thì nó sẽ bị chia thành 2 subtrees
        //--> Chung parent
        int n = 4;
        int[][] edges = {{0,1,1},{0,3,1},{0,2,1},{1,2,1},{1,3,1},{2,3,1}};
        //
        //       2 ---  0
        //      | \   /   \
        //      |    1 ---- 3
        //      |---------/
        //- Case là thể hiện việc 1 trường hợp --> Ta có thể chọn nhiều edge có cừng weight
        //+ edge(2,3, 1) ==> Nó sẽ bị ignore nếu union mà 2 points đã thuộc MST rồi
        //
        //- Với trường hợp pseudo-critical edge khá khó khi ta có nhiều trường hợp chọn edge
        //- Ta không thể kết hợp điều kiện đó với việc check union vì:
        //+ Ta thêm 1 edge --> connect mới ==> Check thêm thì việc nhiều lựa chọn tách biệt với UNION
        //
        //- Idea
        //- Là force edge ==> Phải include edge này trong tree ==> Và tính MST cho các edge còn lại
        //
        //1.2, Optimization
        //1.3, Complexity
        //- Space :
        //- Time :
        //
        System.out.println(findCriticalAndPseudoCriticalEdges(n, edges));
        //#Reference:
        //2589. Minimum Time to Complete All Tasks
        //2578. Split With Minimum Sum
        //2307. Check for Contradictions in Equations
    }
}
