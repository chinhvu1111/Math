package E1_Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class E28_MinimumNumberOfVerticesToReachAllNodes {

    public static List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
//        int[] outEdges=new int[n];
        int[] inEdges=new int[n];
        for(List<Integer> edge: edges){
//            outEdges[edge.get(0)]++;
            inEdges[edge.get(1)]++;
        }
        List<Integer> result=new ArrayList<>();
        for(int i=0;i<n;i++){
            if(inEdges[i]==0){
                result.add(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Cho 1 đồ thị có hướng không có chu kỳ:
        //- Chỉ có duy nhất 1 solution
        //* Trả lại (tập hợp các node nhỏ nhất) trong graph mà có thể tới được các nodes khác.
        //
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //+ 2 <= n <= 10^5
        //+ 1 <= edges.length <= min(10^5, n * (n - 1) / 2)
        //+ edges[i].length == 2
        //
        //- Brainstorm
        //+ Chỉ add những điểm có bậc vào =0 : Tức là không có cạnh nào chỉ vào nó
        //VD
        // inedge[], outedge[]
        //(0 --> 1) : outedge[0]=1, inedge[1]=1
        //(2 --> 1)
        //(3 --> 1)
        //(1 --> 4)
        //(2 --> 4)
        //1.1, Complexity:
        //- Time complexity : O(N+M)
        //+ N is the number of vertices
        //+ M is the number of edges
        //- Space complexity : O(N)
        int n = 6;
        int[][] edges = {{0,1},{0,2},{2,5},{3,4},{4,2}};
        List<List<Integer>> edgesList=new ArrayList<>();
        for(int[] edge: edges){
            List<Integer> currentList=new ArrayList<>();
            currentList.add(edge[0]);
            currentList.add(edge[1]);
            edgesList.add(currentList);
        }
        System.out.println(findSmallestSetOfVertices(n, edgesList));
        //#Reference:
        //1615. Maximal Network Rank
        //1042. Flower Planting With No Adjacent
        //1192. Critical Connections in a Network
        //2371. Minimize Maximum Value in a Grid
    }
}
