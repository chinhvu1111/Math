package E1_Graph;

import javafx.util.Pair;

import java.util.*;

public class E48_MinimumCostToBuyApples {

    public static long[] minCost(int n, int[][] roads, int[] appleCost, int k) {
        List<List<Pair<Integer, Integer>>> adjNodes=new ArrayList<>();

        for(int i=0;i<=n;i++){
            adjNodes.add(new ArrayList<>());
        }
        for(int[] r: roads){
            adjNodes.get(r[0]).add(new Pair<>(r[1], r[2]));
            adjNodes.get(r[1]).add(new Pair<>(r[0], r[2]));
        }

        long[] rs=new long[n];
        for(int i=1;i<=n;i++){
            rs[i-1]=shortestPath(i, n, appleCost, adjNodes, k);
        }

//        for(int i=1;i<=n;i++){
//            long curRs=appleCost[i-1];
//
//            for (int j = 1; j <=n; j++) {
//                if(dist[i][j]!=-1){
//                    curRs=Math.min(dist[i][j]*(k+1)+appleCost[j-1], curRs);
//                }
//            }
//            rs[i-1]=curRs;
//        }
        return rs;
    }

    public static long shortestPath(int node, int n, int[] appleCost, List<List<Pair<Integer, Integer>>> adjNodes, int k){
        PriorityQueue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        queue.add(new int[]{node, 0});
        long minCost=Long.MAX_VALUE;
        int[] travelCosts = new int[n+1];
        Arrays.fill(travelCosts, Integer.MAX_VALUE);
        travelCosts[node]=0;

        while (!queue.isEmpty()){
            int[] curNode=queue.poll();
            minCost=Math.min(minCost, appleCost[curNode[0]-1]+ (long) curNode[1] * (k+1));

            for(Pair<Integer, Integer> nextNode: adjNodes.get(curNode[0])){
                int nextCost=curNode[1]+nextNode.getValue();
                if(nextCost<travelCosts[nextNode.getKey()]){
                    travelCosts[nextNode.getKey()]=nextCost;
                    queue.add(new int[]{nextNode.getKey(), nextCost});
                }
            }
        }
        return minCost;
    }

    public static long[] minCostFloyd(int n, int[][] roads, int[] appleCost, int k) {
        long[][] dist=new long[n+1][n+1];

        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <=n; j++) {
                if(i!=j){
                    dist[i][j]=-1;
                }
            }
        }
//        HashSet<Integer> setNode=new HashSet<>();
        for(int[] r: roads){
            dist[r[0]][r[1]]=r[2];
            dist[r[1]][r[0]]=r[2];
//            setNode.add(r[0]);
//            setNode.add(r[1]);
        }
        for(int v=1;v<=n;v++){
            for (int i = 1; i <=n; i++) {
                for (int j = 1; j <=n; j++) {
                    if(dist[i][v]!=-1&&dist[v][j]!=-1&&(dist[i][j]==-1||dist[i][v]+dist[v][j]<dist[i][j])){
                        dist[i][j]=dist[i][v]+dist[v][j];
                    }
                }
            }
        }
//        for(int v: setNode){
//            for (int i: setNode) {
//                for (int j:setNode) {
//                    if(dist[i][v]!=-1&&dist[v][j]!=-1&&(dist[i][j]==-1||dist[i][v]+dist[v][j]<dist[i][j])){
//                        dist[i][j]=dist[i][v]+dist[v][j];
//                    }
//                }
//            }
//        }
        long[] rs=new long[n];

        for(int i=1;i<=n;i++){
            long curRs=appleCost[i-1];

            for (int j = 1; j <=n; j++) {
                if(dist[i][j]!=-1){
                    curRs=Math.min(dist[i][j]*(k+1)+appleCost[j-1], curRs);
                }
            }
            rs[i-1]=curRs;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a positive integer n representing n cities numbered from (1 to n).
        //- You are also given a 2D array roads, where roads[i] = [ai, bi, costi] indicates
        // that there is (a bidirectional road) between cities (ai and bi) with a cost of traveling equal to (costi).
        //- You can (buy apples) in any city you want, but (some cities) have (different costs) to (buy apples).
        //- You are given the (("1-based") array appleCost) where appleCost[i] is the cost of buying (one apple) from (city i).
        //- You "start" at (some city), traverse through various roads, and eventually buy (exactly one) apple from (any city).
        //- After you buy that apple, you have to (return back) to the city you (started at),
        // but now (the cost of all the roads) will be (multiplied by) a given factor (k).
        //- Given the integer k,
        //* return a 1-based array answer of size n
        // where answer[i] is the (minimum) (total cost) to buy an apple if you start at (city i).
        //*
        //- Chỉ được phép mua 1 quả táo --> Phải quay lại ngay
        //  + Lúc quay lại đi đường nào cũng được.
        //- Tìm result cho start = i (all of i : 1 --> n)
        //
        //Input: n = 4, roads = [[1,2,4],[2,3,2],[2,4,5],[3,4,1],[1,3,4]], appleCost = [56,42,102,301], k = 2
        //Output: [54,42,48,51]
        //Explanation: The minimum cost for each starting city is the following:
        //- Starting at city 1: You take the path 1 -> 2, buy an apple at city 2, and finally take the path 2 -> 1.
        // The total cost is 4 + 42 + 4 * 2 = 54.
        //- Starting at city 2: You directly buy an apple at city 2. The total cost is 42.
        //- Starting at city 3: You take the path 3 -> 2, buy an apple at city 2, and finally take the path 2 -> 3.
        // The total cost is 2 + 42 + 2 * 2 = 48.
        //- Starting at city 4: You take the path 4 -> 3 -> 2 then you buy at city 2, and finally take the path 2 -> 3 -> 4.
        // The total cost is 1 + 2 + 42 + 1 * 2 + 2 * 2 = 51.
        //
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //2 <= n <= 1000
        //1 <= roads.length <= 2000
        //1 <= ai, bi <= n
        //ai != bi
        //1 <= costi <= 10^5
        //appleCost.length == n
        //1 <= appleCost[i] <= 10^5
        //1 <= k <= 100
        //There are no repeated edges.
        //+ N không lớn lắm.
        //
        //- Brainstorm
        //* Dijikstra không support tìm distance từ i --> n điểm còn lại.
        //
        //- Không lấy apply tại chính city (i) luôn vì:
        //  + value of apple đó có thể khá lớn ==> Mua ở thằng có value nhỏ hơn + bù vào đi nhiều path hơn.
        //
        //- Việc quay lại có ảnh hưởng đến kết quả chung không?
        //  + Có ảnh hưởng đến việc chọn mua apple ở thành phố nào
        //      + Nếu mua thành phố càng ở xa:
        //          + Higher cost traverse
        //          + Lower cost apply (if exist)
        //- Ta có thể tìm min dist giữa 2 cities bất kỳ bằng floyd warshall
        //
        //- Sau đó ta sẽ for mỗi city ==> check dist đến từng city
        //  ==> Việc đi đến city đó + go back ==> Nên cùng 1 đường
        //  + Vì đường đó sẽ là min dist giữa 2 city
        //- Lý thuyết là giữa 2 city luôn phải tìm min distance
        //  + Việc chọn city chỉ khác ở chỗ là city có cost of apple khác nhau.
        //
        //- The Floyd-Warshall algorithm, named after its creators Robert Floyd and Stephen Warshall,
        // is a fundamental algorithm in computer science and graph theory.
        // It is used to find the shortest paths between all pairs of nodes in a weighted graph.
        // This algorithm is highly efficient and can handle graphs with both positive and negative edge weights,
        // making it a versatile tool for solving a wide range of network and connectivity problems.
        //
        //- Point của algorithm là sử dụng dynamic programming:
        //  + A -> B -> C
        //- Yếu tố tính trước và tính sau:
        //- Với mỗi node V:
        //  + Ta sẽ xét tất cả các cặp:
        //      + A -> V -> B
        //      ==> Lúc đó ta sẽ tính được cặp (A, B)
        //      ==> Tức là mỗi V vertex ==> Ta sẽ tính lại (all of pairs)
        //- Ta sẽ tư duy quy về tìm (min weight) của 1 node (i) -> node(j):
        //  + Tìm min weight dùng dynamic programming
        //- i có thể đến (node thứ k : 0 --> n-1)
        //  + Từ k nodes ==> Ta cần phải tính tiếp các node xung quanh nó
        //
        //- Tư duy của nó chủ yếu là tư duy intermediate node:
        //  + Mỗi lần ta tính intermediate nodes ==> Map ra nếu các cạnh mà đi qua node này thì dist sẽ được cập nhập hay không
        //
        //- Tức là nếu ta coi all nodes là intermediate node:
        //  + Nếu ta xét được all intermediate nodes ==> Tức là ta đã build xong graph với các edges liên quan
        //  ==> Lúc đó cập nhập được dist của all pair of vertex rồi.
        //- Detail:
        //https://viblo.asia/p/thuat-toan-floyd-warshall-GrLZDBng5k0
        //
        //- Một số ứng dụng của thuật toán
        //  + Giải bài toán tìm đường đi ngắn nhất từ nguồn đơn với đồ thị (kích thước nhỏ) có trọng số
        //  + (Tìm chu trình nhỏ nhất) hoặc (chu trình âm)
        //  + Tìm đường đi ngắn nhất (giữa các cặp đỉnh) của đồ thị có giá trị lớn nhất
        //
        //#Reference:
        //1338. Reduce Array Size to The Half
        //2349. Design a Number Container System
        //465. Optimal Account Balancing
        int n = 4;
        int[][] roads = {{1,2,4},{2,3,2},{2,4,5},{3,4,1},{1,3,4}};
        int[] appleCost = {56,42,102,301};
        int k = 2;
//        int n = 8;
//        int[][] roads = {{8,3,193},{4,1,890},{8,2,714},{7,2,654},{6,1,147}};
//        int[] appleCost = {87310,86029,37141,89780,2747,39709,38302,21966};
//        int k = 63;
//        long[] rs = minCostFloyd(n, roads, appleCost, k);
        long[] rs = minCost(n, roads, appleCost, k);

        for (int i = 0; i < rs.length; i++) {
            System.out.println(rs[i]);
        }
    }
}
