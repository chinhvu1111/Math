package E1_Graph.Dijkstra;

import javafx.util.Pair;

import java.util.*;

public class E1_PathWithMaximumProbability {

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        return 1;
    }

    public double maxProbabilityBell(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        int maxEdges=Math.min(edges.length, n-1);
        double dp[][]=new double[n+1][maxEdges+1];
        HashMap<String, Double> prob=new HashMap();
        for(int i=0;i<n;i++){
            for(int j=1;j<=maxEdges;j++){
                dp[i][j]=0;
            }
        }
        dp[start_node][0]=1;
        List<Integer>[] adjNodes=new ArrayList[n];
        for(int i=0;i<edges.length;i++){
            int x=edges[i][0];
            int y=edges[i][1];
            if(adjNodes[x]==null){
                adjNodes[x]=new ArrayList();
            }
            if(adjNodes[y]==null){
                adjNodes[y]=new ArrayList();
            }
            adjNodes[y].add(x);
            adjNodes[x].add(y);
            prob.put(x+""+y, succProb[i]);
            prob.put(y+""+x, succProb[i]);
        }

        //Time : O(Max* n * n)
        for(int i=1;i<=maxEdges;i++){
            for(int j=0;j<n;j++){
                List<Integer> currentAdjNodes=adjNodes[j];
                if(currentAdjNodes==null){
                    continue;
                }
                // System.out.printf("%s %s\n", j, currentAdjNodes);
                for(int node: currentAdjNodes){
                    if(prob.containsKey(node+""+j)){
                        dp[j][i]=Math.max(dp[node][i-1]*prob.get(node+""+j), dp[j][i]);
                    }
                }
            }
        }
        double rs=0;
        for(int i=1;i<=maxEdges;i++){
            if(dp[end_node][i]!=1){
                rs=Math.max(rs, dp[end_node][i]);
            }
        }
        return rs;
    }

    public double maxProbabilityBellOptimization(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        double[] dp =new double[n+1];
        int m=edges.length;
        dp[start_node]=1;

        for(int i=0;i<n;i++){
            //Cần để update bên trong
            boolean isUpdated=false;
            for(int j=0;j<m;j++){
                int u=edges[j][0];
                int v=edges[j][1];
                if(dp[u]<dp[v]*succProb[j]){
                    dp[u]=dp[v]*succProb[j];
                    isUpdated=true;
                }
                if(dp[v]<dp[u]*succProb[j]){
                    dp[v]=dp[u]*succProb[j];
                    isUpdated=true;
                }
            }
            if(!isUpdated){
                break;
            }
        }
        return dp[end_node];
    }

    public double maxProbability1(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<Pair<Integer, Double>>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            double pathProb = succProb[i];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Pair<>(v, pathProb));
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new Pair<>(u, pathProb));
        }

        double[] maxProb = new double[n];
        maxProb[start] = 1d;

        PriorityQueue<Pair<Double, Integer>> pq = new PriorityQueue<>((a, b) -> -Double.compare(a.getKey(), b.getKey()));
        pq.add(new Pair<>(1.0, start));
        while (!pq.isEmpty()) {
            Pair<Double, Integer> cur = pq.poll();
            double curProb = cur.getKey();
            int curNode = cur.getValue();
            if (curNode == end) {
                return curProb;
            }
            for (Pair<Integer, Double> nxt : graph.getOrDefault(curNode, new ArrayList<>())) {
                int nxtNode = nxt.getKey();
                double pathProb = nxt.getValue();
                if (curProb * pathProb > maxProb[nxtNode]) {
                    maxProb[nxtNode] = curProb * pathProb;
                    pq.add(new Pair<>(maxProb[nxtNode], nxtNode));
                }
            }
        }

        return 0d;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Cho 1 đồ thị vô hướng
        //- [a,b] = x , x là probability of success of traveling this edge
        //- Given start and end
        //* return max probability of success to go from start to end
        //<> if doesn't exist any path.
        //- Accept : correct answer at most 10^-5
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //2 <= n <= 10^4
        //0 <= start, end < n
        //start != end
        //0 <= a, b < n
        //a != b
        //0 <= succProb.length == edges.length <= 2*10^4
        //0 <= succProb[i] <= 1
        //
        //- Brainstorm
        //Ex: start=0, end=3
        //                     0
        //            0.5 /  0.11  \ 0.1
        //               1 -------- 2
        //                \       /
        //                    3
        //- Nếu ta đi lùi : i tính theo (i-1)
        //+ Với case bên trên nếu chưa traverse qua 2 ==> Ta đứng ở 1 sẽ có kết quả sai.
        //- Nếu ta đi tiến
        //- i --> Tính cho (i+1) ==> Cái này ok vì ta sẽ update cho all các next nodes
        //- Có 1 vấn đề là khi (i) --> Tính (i+1) thì ta cũng phải cần tính lại cho các nodes kề với (i+1) nữa
        //
        //- Idea là ta nên tìm đường đi ngắn nhất từ (i) --> Start trước
        //+ Tức là ta sẽ không traverse từ start ==> Sẽ là đi từ (i) --> Start
        //
        //
        //- Idea :
        //+ (i) sẽ tính (i+1)
        //+ visited[][] : Sẽ là lưu trace của edges
        //--> Có thể dùng BFS để traverse theo chiều rộng liệu có được không?
        //Ex: start=0, end=3
        //                     0
        //            0.5 /  0.11  \ 0.1
        //               1 -------- 2
        //             0.1 \       / 0.2
        //                    3
        //- Nếu đi theo BFS --> 2 sẽ update 0.5*0.11 = 0.055
        //--> 2 đã được update là 0.055 : Nếu 2 --> 1 thì có thể sẽ bị sai
        //+ Đi đến 1 --> Ta nên đi 0 -> 2 -> 1 = 0.1*0.11=0.011 ==> Nếu update vào 2 thì ta không tính được 1 đúng
        //
        //Target vertex | Shortest distance | prev
        //  (0)                 0             +
        //   1                  0.5            0
        //   2                  0.1            0
        //   3                  0              +
        //
        //Target vertex | Shortest distance | prev
        //   0                  0              +
        //  (1)                 0.5           0
        //   2                  0.055          1
        //   3                  0.05           1
        //
        //Target vertex | Shortest distance | prev
        //   0                  0              +
        //   1                  0.5            0
        //  (2)                 0.055         1
        //   3                  0.05           1
        //
        //Target vertex | Shortest distance | prev
        //   0                  0              +
        //   1                  0.5            0
        //   2                  0.055          1
        //  (3)                 0.055*0.2      1
        //                    = 0.011
        //0.1*0.11*0.1 = 0.0011
        //==> Điểm khác ở đây là đường đi từ
        //+ 0 -> 2 -> 1 -> 3
        //+ 0 -> 1 -> 2 -> 3
        //==> 1 và 2 nối với nhau thì nên chọn điểm nào ==> Chọn điểm gần 0 hơn là 1 traverse thì sẽ đúng hơn.
        //
        //Basic theorem:
        //+ The graph has N vertices
        //- Trong 1 graph không có chu kỳ trọng số âm (no negative-weight cycles)
        //- The shortest path between any two vertices has at most N-1 edges.
        //
        //- Dùng dynamic programming để tìm shortest path:
        //Quy luật như sau:
        //Ex:
        //      0 ---------------> 3
        //(down)|  \(down) /(down) | (upper)
        //      1 ---------------> 2
        //[0,3]=200
        //[0,2]=500
        //[0,1]=100
        //[3,1]=-150
        //[2,3]=100
        //[1,2]=100
        //
        // - At most N-1 edges
        //     0 | 1 | 2 | 3
        // 0 : 0 | + | + | + : 0 edge
        // 1 : 0 | 1 | 2 | 3 : Chi phí để đến mỗi node với số canh=i (i==1)
        // 2 : 0 | 1 | 2 | 3 :
        // 3 : 0 | 1 | 2 | 3
        //* Idea :
        //- Ở đây ta sẽ dynamic theo số lượng edges apply cho each nodes
        //- Node1[edges] = min all( nodei[edges-1])
        //
        //- Nếu bài này code traditional bellman force:
        //+ Timeout
        //
        //- Optimization
        //+ Ta sẽ duyệt nhiều nhất n-1 lần
        //+ Nếu turn hiện tại kết quả như turn trước --> Stop không cần update thêm
        //- Mỗi turn ta sẽ:
        //+ 0 -> 1 -> 3
        //       1 -> 2 -> 3
        //+ 0 -> 2 -> 1
        //       2 -> 3
        //==> Làm kiểu này thì có vẻ sẽ bị cycle trong undirected graph.
        //
        //Ex:
        //Ex: start=0, end=3
        //                     0
        //            0.5 /  0.11  \ 0.1
        //               1 -------- 2
        //             0.1 \       / 0.2
        //                    3
        // 0 | 1 |   2 | 3
        // 0 |0.5|0.055| 0.011
        //--> Làm kiểu này thì nó sẽ bị cycle --> 2
        //
        //- Ta có thể tối ưu bằng cách traverse all edges mỗi lần được không?
        //Ex:
        //(0,1), (0,2), (1,2), (1,3), (2,3)
        // 0 | 1     |   2   | 3
        // 0 | 0.05  |0.055  | 0.011
        //
        //* NOTE: Với undirected graph --> 2 chiều như trên
        //+ Xét all edges ta cần phải --> xét 2 chiều (u, v) và (v, u)
        //
        //Ex:
        //(0,1), (0,2), (1,2), (1,3), (2,3)
        //(0,1) : 1=0.5
        //(0,2) : 2=0.1
        //(1,2) : 2=0.055, 1=0.011
        //(1,3) : 3=0.0011
        //(2,3) : 3=0.011 ==> still 3=0.0011
        //
        //(0,1), (1,2), (0,2), (1,3), (2,3)
        //(0,1) : 1=0.5
        //(1,2) : 2=0.055, 1=0.011
        //(1,3) : 3=0.05, 3=0.0011 ==> 0.011
        //(2,3) : 3=0.011 ==> still 3=0.0011
        //--> Tức là ta có thể update 2 chiều nên vẫn ổn.
        //- Cần để isUpdated bên trong loop --> Timeout ngay
        //
        //2. Method-2: Dùng Dijkstra
        //2.0,
        //PriorityQueue<int[]> queue=new PriorityQueue<>((o1, o2) -> o2[0]-o1[0]);
        //==> Cần <> không là sai syntax
        //
        //- Comparator với double cần:
        //+ -Double.compare(o1[1], o2[1])
        //
        //- Idea của Dijkstra
        //- Idea của nó là các weight >=0
        //- Ta dùng max heap để sắp xếp các khoảng cách hiện tại
        //+ Mỗi lần ta sẽ xét 1 node với distance từ start --> current node min nhất <=> Việc poll từ max heap ra
        //+ Sau đó tính khoảng cách từ nó đến các neighbor nodes ==> nếu giá trị tính nhỏ hơn ==> update <> thì thôi
        //+ Sau khi xét xong current node thì tương đương với việc ta đã xét được Min distance từ (start --> current node) rồi
        //  + Việc keep trace visited <=> poll node đó ra khỏi max heap / priority queue
        //
        //- Câu hỏi?
        //+ Liệu có case nào mà điểm mới --> Tính ra mà có thể update lại các điểm đã poll rồi hay không?
        //Ex:
        //1 -> 2 -> 3 -> 4
        //\    \
        // \    7
        //  \    \
        //   5 -> 6
        //+ Ở đây ta thấy rằng nếu:
        //(1 -> 2 -> 6) < (1 -> 5 -> 6)
        //+ Giả sử
        //  + (1 -> 5) + (5 -> 6) < (1 -> 2) + (2 -> 7)
        //  ==> 6 sẽ là cái min tiếp theo
        //- Lúc đó giá sử (7 -> 6) chỉ là 1 chiều:
        //+ Ta sẽ tính được 6 là min là X
        //+ Nếu 7 tính tiếp được kết quả ==> Ta sẽ lại phải add vào queue
        //- Lúc đó giá sử (7 -> 6) chỉ là 2 chiều:
        //+ Giá trị 7 sẽ có lợi hơn nếu đi từ 1 -> 5 -> 6 -> 7 thay vì đi từ (1 -> 2 -> 7) đã > (1 -> 5 -> 6) rồi.
        //** Tư duy greedy y hệt là dạng nước lan.
        //
        //- Time complexity : O(V+E*Log(V))
        //  + V: Do cần traverse hết all vertex
        //  + E*Log(V) : Vì mỗi node đi qua hết edge liên quan của nó ==> Push all edge vào heap = E*Log(E)
        //  + E max nhất = V^V (Cùng lắm mỗi node connect đến all nodes còn lại) = O(E*Log(E)) = O(E*Log(V^2)) = O(E*2*Log(V)) = O(E*Log(V))
        //- Space complexity : O(V+E)
        //  + Distance array : O(V)
        //  + Priority queue : O(E)
        //
        //** NOTE:
        //- Chú ý với:
        //+ undirected graph : Không cần tính lại node cũ
        //+ directed graph : cần tính lại các nodes kể cả các node poll ra rồi
        //- Dijkstra được dùng cho:
        //+ Weight graph
        //- BFS được dùng cho
        //+ Unweight graph
        //
        //-
        //
        //#Reference:
        //505. The Maze II
        //1976. Number of Ways to Arrive at Destination
    }
}
