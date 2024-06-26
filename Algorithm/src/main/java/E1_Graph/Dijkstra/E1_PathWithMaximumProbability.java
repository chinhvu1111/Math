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
        HashMap<String, Double> prob=new HashMap<>();
//        for(int i=0;i<n;i++){
//            for(int j=1;j<=maxEdges;j++){
//                dp[i][j]=0;
//            }
//        }
        dp[start_node][0]=1;
        List<Integer>[] adjNodes=new ArrayList[n];
        for(int i=0;i<edges.length;i++){
            int x=edges[i][0];
            int y=edges[i][1];
            if(adjNodes[x]==null){
                adjNodes[x]=new ArrayList<>();
            }
            if(adjNodes[y]==null){
                adjNodes[y]=new ArrayList<>();
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

    public double maxProbabilityOptimization2(int n, int[][] edges, double[] succProb, int start, int end) {
        int m=edges.length;
        double[] dp =new double[n];
        PriorityQueue<double[]> queue=
                new PriorityQueue<>((o1, o2) -> -Double.compare(o1[1], o2[1]));
        boolean[] visited=new boolean[n];
        queue.add(new double[]{start, 1});
        HashMap<String, Double> prob=new HashMap<>();
        dp[start]=1D;
        visited[start]=true;

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

        while(!queue.isEmpty()){
            double[] currentNode=queue.poll();
            int node=(int)currentNode[0];
            double value=currentNode[1];
            if(node==end){
                return value;
            }
            List<Integer> currentAdjNodes=adjNodes[node];
            if(currentAdjNodes==null){
                continue;
            }
            for(Integer nextNode: currentAdjNodes){
                if(visited[nextNode]){
                    continue;
                }
                // System.out.printf("Out of loop, Prev node: %s, prev value %s, node: %s newNode %s\n",node, value, nextNode, dp[nextNode]);
                if(dp[nextNode]<value*prob.get(node+""+nextNode)){
                    double newValue=value*prob.get(node+""+nextNode);
                    dp[nextNode]=newValue;
                    queue.add(new double[]{nextNode, newValue});
                    // System.out.printf("Prev node: %s, prev value %s, node: %s newNode %s\n",node, value, nextNode, newValue);
                }
                // if(prob.containsKey(node+""+nextNode)){
                //     if(dp[node]<dp[nextNode]*prob.get(node+""+nextNode)){
                //         double newValue=dp[nextNode]*prob.get(node+""+nextNode);
                //         dp[node]=newValue;
                //         queue.add(new double[]{node, newValue});
                //         System.out.printf("%s %s\n", nextNode, dp[node]);
                //     }
                // }
            }
            visited[node]=true;
        }
        return dp[end];
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
        //- Chọn điểm start=0
        // + Nếu là dijkstra -> Tính khoảng cách từ 0 ==> all nodes
        //Target vertex | Shortest distance | prev
        //  (0)                 0             +
        //   1                  0.5            0
        //   2                  0.1            0
        //   3                  0              +
        //
        //Target vertex | Shortest distance | prev
        //   0                  0              +
        //  (1)                 0.5            0
        //   2                  0.055          1
        //   3                  0.05           1
        //
        //Target vertex | Shortest distance | prev
        //   0                  0              +
        //   1                  0.5            0
        //  (2)                 0.055          1
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
        //=========== 1 lưu ý nữa về bản chất dủa Dijikstra
        //Ex:
        // 1 -- 2 -- 5 (target) --> Sai tất cả là target hết
        //  \       |
        //   3 -- 4
        //- Giả dụ trong trường hợp nhân với nhau theo số thực
        //+ (1,2)= 0.1
        //+ (1,3)=0.2
        //+ (2,5)= 0.4
        //+ (3,4)=0.6
        //+ (4,5)=0.001
        //==> Ở đây ta thấy nếu đi (1 - 3 - 4) sẽ tệ hơn (1 - 2 - 5) (target=5)
        //Nhưng khi đi (1 - 3 - 4 - 5) sẽ tốt hơn (1 - 2 - 5) (target=5)
        //+ Step 0: start=1
        //+ Step 1: 1 -> 2(0.1), 1 -> 3(0.1)
        //1 2   3
        //0 0.1 0.2
        //+ Step 2: Chọn 2 (Chọn node (chưa đi) mà min distance)
        // 1 - 2 - 5 = 0.1*0.4
        //1 | 2   | 3   | 5
        //0 |0.1  | 0.2 | 0.04
        //+ Step 3: Chọn 5 min=0.04 + visited[5]=false
        //1 | 2   | 3   | 5     | 4
        //0 |0.1  | 0.2 | 0.04  | 0.00004
        //** Ta thấy ở đây rằng distance(5)= 0.04 không phải min_distance từ (1 -> 5)
        //===> Thuật toán dijikstra chỉ để tìm (node có khoảng cách min/max) đến (start node)?
        //* Dijikstra không phải thuật toán dạng tìm kiếm min distance của 1 node --> all nodes còn lại? ==> "WRONG IDEA"
        //** Chẳng qua bài trên là dạng đặc biệt
        //- Chỉ có thể tìm min distance của all remaining nodes nếu nó là (directed graph)
        //  + Khi chiều (4 -> 5) là 1 chiều ==> 5 không thể xuống 4 được, tính được bình thường cho (5 theo 1 - 3 - 4 -5)
        //  + Khi chiều (5 -> 4) là 1 chiều ==> 5 không thể xuống 4 được, tính được bình thường cho (4 theo 1 - 2 - 5 - 4) và (5)
        //- Nếu không phải nhân số thực --> Gía trị sẽ cộng dồn ==> Sẽ không có case (1 - 3 -4 - 5) < (1 - 2 - 5) (Do 4 - 5 small)
        //- Nếu hướng (4 - 5) và (5 -> 4) cùng tồn tại ==> Nhân số thực sẽ ra small dần --> 0 (Nên sẽ không có case ntn)
        //
        //** Đại loại nếu đi từ (1 -> 2 -> 5), (1 -> 3 -> 4) thì
        //* Trong undirected graph:
        //  + 2 paths trên đều có thể đi qua edge(4,5)
        //  ==> Cái nào trước đó nhỏ hơn thì lấy vẫn đúng --> Không cần xét lại
        //      ==> Với undirected graph "không" cần xét lại node đã visited
        //* Trong directed graph:
        //  + 2 paths trên chỉ có 1 path được đi qua edge(4,5)
        //  ==> Cái nào trước đó nhỏ hơn thì sẽ không còn đúng nữa:
        //      + 1 -> 2 -> 5 đang nhỏ hơn nhưng không đi qua được edge(4,5) thì sẽ không bằng việc (1 -> 3 -> 4) đi qua edge(4,5)
        //      ==> Với directed graph cần xét lại node đã visited
        //
        //** Liệu trong undirected graph thì visited rồi thì có cần xét lại không?
        // ** "CÓ" ==> Chỉ xảy ra với dạng bài tỉ lệ
        //  - Như là các cạnh sau có thể ảnh hưởng đến kết quả tổng thế
        //      + Weight có thể <0
        //      + Weight là tỉ lệ 1/10000 ==> Ảnh hưởng đến kết quả chung.
        //* (Với weight>0 chưa qua node thì ok còn nếu qua rồi --> sẽ không cần đi lại vì cộng dồn sum (+) thì chắc chắn tệ hơn)
        //  + Với directed or undirected là như nhau.
        //- Ở đây ta thấy là sẽ là cộng dồn ==> undirected graph
        //  + Nếu node nào đi rồi thì sao?
        //Ex:
        //1 ---- 2
        //\   /
        // 3 --- 4
        //weight(1,2) = 0.1
        //weight(2,3) = 0.2
        //weight(1,3) = 2
        //weight(3, 4) = 4
        //+ Step 1:
        // 1
        // 0
        //+ Step 2: min=0 (Vị trí node 1)
        //  + 1 ->2
        //  + 1 -> 3
        // 1 | 2   |  3
        // 0 | 0.1 |  2
        //+ Step 3: min=(1,2) ==> 2 -> 3
        // 1 | 2   |  3 (updated)
        // 0 | 0.1 |  0.1+0.2=0.3
        //- Ta thấy dù 3 đã visited ==> Nhưng có updated tức là ta vẫn có thể xét 3 tiếp (Mặc dù đây là undirected graph)
        //** Ta vẫn phải (add vào queue) với nhưng (update nodes)
        //==> Chứ (không phải) cứ (run qua rồi) ta sẽ (không xét nữa)
        //
        //- Tìm shortest path ntn?
        //Ex:
        // 0  ---(1)---  1
        // |             |(10) \(11)
        // 2 --- (2)---  3 -(1)- 5
        //- Khi đến 0 -> 1 thì không phải là edges trong shortest path từ (0 --> n-1)
        //  + Vì nó qua điểm 3,5 với weight rất lớn
        //- Không phải add all edges khi traverse là được.
        //* Với mỗi step thì:
        //- Ta xác định được shortest path từ (source -> current node)
        //  + Cái này sẽ được add tiếp vào (min/max) queue để có thể tìm (shortest path) của các (next nodes)
        //- Để tìm shortest path thì không phải add all edges khi traverse là được.
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
        //  + Weight graph
        //- BFS được dùng cho
        //  + Unweight graph
        //
        //-
        //
        //#Reference:
        //505. The Maze II
        //1976. Number of Ways to Arrive at Destination
        //* Solution:
        //  - maxProbabilityBellOptimization
        //  - maxProbabilityBell
        //  - maxProbabilityOptimization2
        //* Invalid solution:
        //  - maxProbability1
    }
}
