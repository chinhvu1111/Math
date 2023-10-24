package E1_UnionFind;

import java.util.*;

public class E4_CheckingExistenceOfEdgeLengthLimitedPaths {

    public static int[] find(int[] parent, int node){
        int depth=1;
        int prevNode=node;

        while(parent[node]!=node){
            node=parent[node];
            depth++;
        }
        parent[prevNode]=node;
        return new int[]{node, depth};
    }

    public static boolean union(int[] parent, int u, int v){
        int[] parentU=find(parent, u);
        int[] parentV=find(parent, v);

        if(parentU[0]!=parentV[0]){
            if(parentU[1]>parentV[1]){
                parent[parentV[0]]=parentU[0];
            }else{
                parent[parentU[0]]=parentV[0];
            }
            return false;
        }
        return true;
    }

    public static boolean isConnected(int[] parent, int u, int v){
        int[] parentU=find(parent, u);
        int[] parentV=find(parent, v);

        if(parentU[0]!=parentV[0]){
            return false;
        }
        return true;
    }

    public static boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int m=queries.length;
        List<int[]> sortedQueries=new ArrayList<>();
        int i=0;

        for(int[] query: queries){
            sortedQueries.add(new int[]{query[0], query[1], query[2], i++});
        }
        sortedQueries.sort((a, b) -> (a[2] - b[2]));
        Arrays.sort(edgeList, (a, b) -> (a[2]-b[2]));
        int edgeIndex=0;
        int[] parent=new int[n];

        for(i=0;i<n;i++){
            parent[i]=i;
        }

        boolean[] answer=new boolean[m];

        for(i=0;i<m;i++){
            for(;edgeIndex<edgeList.length&&edgeList[edgeIndex][2]<sortedQueries.get(i)[2];edgeIndex++){
                int[] e=edgeList[edgeIndex];
                union(parent, e[0], e[1]);
            }
            if(isConnected(parent, sortedQueries.get(i)[0], sortedQueries.get(i)[1])){
                answer[sortedQueries.get(i)[3]]=true;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given undirected graph:
        //+ edgeList[i] = [u-i, v-i, dis-i] denotes an edge between nodes u-i and v-i with distance dist-i.
        //+ There may be multiple edges between two nodes.
        //- Given an array queries, where queries[j] = [pj, qj, limitj],
        // your task is to determine for each queries[j]
        // whether there is a path between p-j and q-j such that each edge on the path has a distance strictly < limit-j .
        //* Return a boolean array answer, where (answer.length == queries.length) and
        // the jth value of answer is true if there is a path for queries[j] is true, and false otherwise.
        //--> Nói chung là return lại answer có length=querie.length
        //
        //** Idea
        //1.
        //1.1, Idea
        //- Constraint
        //2 <= n <= 10^5
        //1 <= edgeList.length, queries.length <= 10^5
        //edgeList[i].length == 3
        //queries[j].length == 3
        //0 <= ui, vi, pj, qj <= n - 1
        //ui != vi
        //pj != qj
        //1 <= dis-i, limit-j <= 10^9
        //--> Số lượng vertex khá lớn ==> Ta không thể loop đôi 1 sau đó xử lý được.
        //
        //- Brainstorm
        //- Bài này cơ bản là tìm distance nhỏ nhất giữa 2 node trong graph
        //+ 2 node có thể có nhiều path đi qua --> Mà là undirected graph nên ta chỉ cần lấy distance min là được
        //- Ta thấy djikstra chỉ có thể dụng để tìm (min distance) từ 1 node --> node khác
        // ==> Chứ không phải all nodes trong graph
        //
        //- Ta có n nodes:
        //+ Mỗi node-i sẽ tính distance đến (n-1) còn lại ==> Space : O(n^2) ==> Impossible
        //        (4)
        //Ex : 1 ----- 2
        //     \       |(8)
        //      \ 2,16 |
        //       ----- 0
        //- Nếu làm đơn giản thì mỗi query ta sẽ tìm distance 1 lần
        //+ Time complexity : O(m*(n+n))
        //- Ở đây nó cho thres hold liên quan đến distance
        //==> Ta có thể build graph từ từ xuất phát từ (min distance => max distance) được không
        //- Ta sẽ build the graph bằng cách add từ từ các edge vào:
        //+ Add dần các edge có distance từ min --> max
        //+ Ta sẽ sort cả edge list và queries để tính dần.
        //
        //- Build new graph như thế nào?
        //+ Không cần build graph nếu dùng tư duy đặc biệt như bên trên
        //
        int n = 5;
        int[][]edgeList = {{0,1,10},{1,2,5},{2,3,9},{3,4,13}}, queries = {{0,4,14},{1,4,13}};
        boolean[] answer=distanceLimitedPathsExist(n, edgeList, queries);

        for(int i=0;i<queries.length;i++){
            System.out.printf("%s, ", answer[i]);
        }
        //#Reference:
        //1724. Checking Existence of Edge Length Limited Paths II
        //2421. Number of Good Paths
        //2492. Minimum Score of a Path Between Two Cities
    }
}
