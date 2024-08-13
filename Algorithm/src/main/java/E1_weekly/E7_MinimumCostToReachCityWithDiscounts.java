package E1_weekly;

import java.util.*;

public class E7_MinimumCostToReachCityWithDiscounts {

    public static int minimumCostRefer(int n, int[][] highways, int discounts) {
        // Construct the graph from the given highways array
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            graph.add(new ArrayList<>());
        }
        for (int[] highway : highways) {
            int u = highway[0], v = highway[1], toll = highway[2];
            graph.get(u).add(new int[] { v, toll });
            graph.get(v).add(new int[] { u, toll });
        }

        // Min-heap priority queue to store tuples of (cost, city, discounts used)
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                Comparator.comparingInt(a -> a[0])
        );
        pq.offer(new int[] { 0, 0, 0 }); // Start from city 0 with cost 0 and 0 discounts used

        // 2D array to track minimum distance to each city with a given number of discounts used
        int[][] dist = new int[n][discounts + 1];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentCost = current[0], city = current[1], discountsUsed =
                    current[2];

            // If this cost is already higher than the known minimum, skip it
            if (currentCost > dist[city][discountsUsed]) {
                continue;
            }

            // Explore all neighbors of the current city
            for (int[] neighbor : graph.get(city)) {
                int nextCity = neighbor[0], toll = neighbor[1];

                // Case 1: Move to the neighbor without using a discount
                if (currentCost + toll < dist[nextCity][discountsUsed]) {
                    dist[nextCity][discountsUsed] = currentCost + toll;
                    pq.offer(
                            new int[] {
                                    dist[nextCity][discountsUsed],
                                    nextCity,
                                    discountsUsed,
                            }
                    );
                }

                // Case 2: Move to the neighbor using a discount if available
                if (discountsUsed < discounts) {
                    int newCostWithDiscount = currentCost + toll / 2;
                    if (
                            newCostWithDiscount < dist[nextCity][discountsUsed + 1]
                    ) {
                        dist[nextCity][discountsUsed + 1] = newCostWithDiscount;
                        pq.offer(
                                new int[] {
                                        newCostWithDiscount,
                                        nextCity,
                                        discountsUsed + 1,
                                }
                        );
                    }
                }
            }
        }

        // Find the minimum cost to reach city n-1 with any number of discounts used
        int minCost = Integer.MAX_VALUE;
        for (int d = 0; d <= discounts; ++d) {
            minCost = Math.min(minCost, dist[n - 1][d]);
        }
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    public static int minimumCostWrong(int n, int[][] highways, int discounts) {
        PriorityQueue<int[]> minEdges=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        minEdges.add(new int[]{0, 0});
        List<List<int[]>> adjNodes=new ArrayList<>();
        int[][] w=new int[n][n];

        for(int i=0;i<n;i++){
            adjNodes.add(new ArrayList<>());
        }
        for(int[] e: highways){
            adjNodes.get(e[0]).add(new int[]{e[1], e[2]});
            adjNodes.get(e[1]).add(new int[]{e[0], e[2]});
            w[e[0]][e[1]]=e[2];
            w[e[1]][e[0]]=e[2];
        }
        HashMap<Integer, Integer> parentNode=new HashMap<>();
        int[] minWeight=new int[n];
        Arrays.fill(minWeight, Integer.MAX_VALUE);
        minWeight[0]=0;

        while (!minEdges.isEmpty()){
            int[] currentNode=minEdges.poll();
            List<int[]> adj=adjNodes.get(currentNode[0]);

            if(currentNode[0]==n-1){
                break;
            }
            for(int[] nextE: adj){
                if(nextE[1]+currentNode[1]<minWeight[nextE[0]]){
                    minWeight[nextE[0]]=nextE[1]+currentNode[1];
                    minEdges.add(new int[]{nextE[0], minWeight[nextE[0]]});
                    parentNode.put(nextE[0], currentNode[0]);
//                    visited[nextE[0]]=true;
                }
            }
        }
        int destNode=n-1;

        if(!parentNode.containsKey(destNode)){
            return -1;
        }
        PriorityQueue<Integer> maxWeights=new PriorityQueue<>(Collections.reverseOrder());
        while (parentNode.containsKey(destNode)){
            int nextNode = parentNode.get(destNode);
            maxWeights.add(w[destNode][nextNode]);
            destNode=nextNode;
        }
        int rs=0;

        while (discounts!=0&&!maxWeights.isEmpty()){
            rs+=maxWeights.poll()/2;
            discounts--;
        }
        while (!maxWeights.isEmpty()){
            rs+=maxWeights.poll();
        }
        return rs;
    }

    public static int minimumCost(int n, int[][] highways, int discounts) {
        //Time: O(V*log(V))
        //Space: O(V)
        PriorityQueue<int[]> minEdges=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        //(node, weight, usedDiscount)
        minEdges.add(new int[]{0, 0, 0});
        //Space: O(V+E)
        List<List<int[]>> adjNodes=new ArrayList<>();

        //Time: O(V)
        for (int i = 0; i < n; i++) {
            adjNodes.add(new ArrayList<>());
        }
        //Time: O(E)
        for(int[] e: highways){
            adjNodes.get(e[0]).add(new int[]{e[1], e[2]});
            adjNodes.get(e[1]).add(new int[]{e[0], e[2]});
        }
//        HashMap<Integer, Integer> parentNode=new HashMap<>();
        //Time: O(V*discount)
        int[][] minWeight=new int[n][discounts+1];

        //Time: O(E)
        for(int i=0;i<n;i++){
            Arrays.fill(minWeight[i], Integer.MAX_VALUE);
        }
        minWeight[0][0]=0;

        //Time: O(V+E)
        while (!minEdges.isEmpty()){
            int[] currentNode=minEdges.poll();
            List<int[]> adj=adjNodes.get(currentNode[0]);

            if(currentNode[0]==n-1){
                break;
            }
            for(int[] nextE: adj){
                if(nextE[1]+currentNode[1]<minWeight[nextE[0]][currentNode[2]]){
                    minWeight[nextE[0]][currentNode[2]]=nextE[1]+currentNode[1];
                    minEdges.add(new int[]{nextE[0], minWeight[nextE[0]][currentNode[2]], currentNode[2]});
//                    parentNode.put(nextE[0], currentNode[0]);
//                    visited[nextE[0]]=true;
                }
                if(currentNode[2]<discounts){
                    int curW = nextE[1]/2+currentNode[1];
                    if(curW<minWeight[nextE[0]][currentNode[2]+1]){
                        minWeight[nextE[0]][currentNode[2]+1]=curW;
                        minEdges.add(new int[]{nextE[0], curW, currentNode[2]+1});
                    }
                }
            }
        }
        int minValue=Integer.MAX_VALUE;

        //Time: O(discount)
        for(int i=0;i<=discounts;i++){
            minValue=Math.min(minValue, minWeight[n-1][i]);
        }
        return minValue==Integer.MAX_VALUE?-1: minValue;
    }

    public static void main(String[] args) {
        //** Requirement
        //- A series of highways connect n cities numbered from 0 to n - 1.
        //- You are given a 2D integer array highways where highways[i] = [city1i, city2i, tolli] indicates
        // that there is (a highway) that connects (city1i and city2i), allowing a car to go (from city1i to city2i)
        // and vice versa for (a cost of tolli).
        //- You are also given (an integer discounts) which represents (the number of discounts) you have.
        //- You can use (a discount) to travel across (the ith highway) for (a cost of tolli / 2) (integer division).
        //- (Each discount) may only be (used once), and you can only use (at most one) (discount per highway).
        //* Return (the minimum total cost) to go from city (0 to city n - 1),
        // or (-1) if (it is not possible) to go from city (0 to city n - 1).
        //
        //**Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //2 <= n <= 1000
        //1 <= highways.length <= 1000
        //highways[i].length == 3
        //0 <= city1i, city2i <= n - 1
        //city1i != city2i
        //0 <= tolli <= 10^5
        //0 <= discounts <= 500
        //There are no duplicate highways.
        //
        //- Brainstorm
        //- Bài này dùng dijikstra
        //  + Tìm shortest path
        //  + Lưu các path cần vào priority queue ==> Giảm dần các value dựa trên discount
        //
        //- Ôn lại dijikstra:
        //- Tìm path ntn?
        //  ==> Dùng hashmap --> Update parent node liên tục
        //- Nếu discount = 1
        //  + 1 cái weight to --> /2 thì sẽ nhỏ hơn nhiều so với nhiều path nhỏ nối nhau cộng dồn vào
        //Ex:
        //Input: n = 5, highways = [[0,1,4],[2,1,3],[1,4,11],[3,2,3],[3,4,2]], discounts = 1
        //+ (4 + 11/2) = 9 < (4/2 + 3 + 3 + 2) = 10
        //
        //* KINH NGHIỆM:
        //- Dạng bài update:
        //  + Dựa trên 1 dimension nữa là discount
        //  ==> Support đi nhiều direction/ dynamic programming (Ta có thể chọn thêm option để update)
        //- Nếu đi 1 direction + non visited:
        //  ==> Ta có thể update map liên tục để tìm parent.
        //- Với dạng bài kiểu biến hoá:
        //  ==> Thường sẽ không có visited ==> Mà sẽ update dựa trên giá trị
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(V+E+V*discount)
        //- Time: O(V*log(V)+E+V+discount)
        //
//        int n = 4;
//        int[][] highways = {{1,3,17},{1,2,7},{3,2,5},{0,1,6},{3,0,20}};
//        int discounts = 20;
//        int n = 5;
//        int[][] highways = {{0,1,4},{2,1,3},{1,4,11},{3,2,3},{3,4,2}};
//        int discounts = 1;
        int n = 5;
        int[][] highways = {{2,3,4},{0,3,2},{4,3,5}};
        int discounts = 2;
        System.out.println(minimumCostWrong(n, highways, discounts));
        System.out.println(minimumCostRefer(n, highways, discounts));
        System.out.println(minimumCost(n, highways, discounts));
        //
        //#Reference:
        //2247. Maximum Cost of Trip With K Highways
        //1928. Minimum Cost to Reach Destination in Time
    }
}
