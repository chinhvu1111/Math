package E1_Graph;

public class E30_MaximalNetworkRank {

    public static int maximalNetworkRank(int n, int[][] roads) {
        int[] inEdges=new int[n];
        boolean[][] connected=new boolean[n][n];

        for(int[] currentRoad:roads){
            int x=currentRoad[0];
            int y=currentRoad[1];
            inEdges[x]++;
            inEdges[y]++;
            connected[x][y]=true;
            connected[y][x]=true;
        }
        int rs=0;
        for(int i=0;i<n;i++){

            for(int j=i+1;j<n;j++){
                int commonEdge=0;

                if(connected[i][j]){
                    commonEdge=1;
                }
                System.out.printf("%s %s %s %s\n", i, j, inEdges[i], inEdges[j]);
                rs=Math.max(rs, inEdges[i]+inEdges[j]-commonEdge);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- The network rank of two different cities is defined as the total number of directly connected roads to either city.
        //+ If a road is directly connected to both cities, it is only (counted once).
        //+ Network rank of specific pair of cities : Tức là tổng số road connect to 1 trong 2 cities đó.
        //+ Nếu chung 1 số road --> tính là 1 thôi
        //- Mỗi pair of cities chỉ có duy nhất 1 edge nối giữa chúng --> Nếu nối giữa 2 cái thì sẽ được tính từ đầu.
        //- Xét các pair of cities không cần thiết phải kết nối được với nhau.
        //
        //* Tính tổng maximum network rank của tất cả các pair of different cities.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //+ 2 <= n <= 100
        //+ 0 <= roads.length <= n * (n - 1) / 2
        //
        //- Brainstorm
        //+ Đơn giản là tình số bậc của từng vertice --> Nếu pair thì cần tính tổng bậc của 2 vertices - 1 bậc của connected edge
        //
        //1.1, Complexity:
        //- Time complexity : O(N^2)
        //- Space complexity : O(N^2)
        //+ N is the number of cities
        //
        //1.2, Optimization
        //- Space --> O(E) : is the number of edge by using HashMap.
        //
        int n = 8;
        // (0) -- (1) -- (2) -- (3)   (5) -- (6)
        //                \             \
        //                 (4)          (7)
        int[][] roads = {{0,1},{1,2},{2,3},{2,4},{5,6},{5,7}};
        System.out.println(maximalNetworkRank(n, roads));
        //#Reference:
        //85. Is Graph Bipartite?
        //505. The Maze II
        //765. Couples Holding Hands
        //1042. Flower Planting With No Adjacent
        //444. Sequence Reconstruction
        //1928. Minimum Cost to Reach Destination in Time
    }
}
