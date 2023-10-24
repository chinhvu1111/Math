package E1_minimum_spaning_tree;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import static algorithm.KingdomDivisionHack1.queue;

public class E2_MinCostToConnectAllPoints {

    public static int[] findParent(int[] parent, int node){
        int prevNode=node;
        int depth=1;

        while(parent[node]!=node){
            node=parent[node];
            parent[prevNode]=node;
            depth++;
        }
//        parent[prevNode]=node;
        return new int[]{node, depth};
    }

    public static boolean unionFind(int[] parent, int u, int v){
        int[] parentU=findParent(parent, u);
        int[] parentV=findParent(parent, v);

        if(parentU[0]!=parentV[0]){
            if(parentU[1]>parentV[1]){
                parent[parentV[0]]=parentU[0];
            }else{
                parent[parentU[0]]=parentV[0];
            }
            return true;
        }
        return false;
    }
    public static int minCostConnectPoints(int[][] points) {
        List<int[]> pointSortedDis=new ArrayList<>();
        int n=points.length;

        //Time : O(n^2)
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int curDistance=Math.abs(points[i][0]-points[j][0]) + Math.abs(points[i][1]-points[j][1]);
                pointSortedDis.add(new int[]{i, j, curDistance});
            }
        }
        int[] parent=new int[n+1];

        for(int i=1;i<=n;i++){
            parent[i]=i;
        }
        //Time : O((n^2)*log(n^2)) = O(n^2 * log2(n))
        //Space : O(n^2)
        pointSortedDis.sort((a, b) -> (a[2]-b[2]));
        int totalDis=0;
        int totalNode=0;

        //Time : O(n^2)
        for(int[] dis: pointSortedDis){
            //Time: O(log2(n^2))
            if(unionFind(parent, dis[0], dis[1])){
                totalDis+=dis[2];
                totalNode++;
                if(totalNode==n-1){
                    return totalDis;
                }
            }
        }

        return -1;
    }

    public static int minCostConnectPointsPrimAlgorithm(int[][] points) {
        int n = points.length;
        //Space : O(n^2)
        //Time : O(n*log(n))
        PriorityQueue<int[]> queue=new PriorityQueue<>((a, b) -> (a[1]-b[1]));
        //Space : O(n)
        //Time : O(n)
        boolean[] inMST=new boolean[n];
        queue.add(new int[]{0, 0});
        int mstCost=0;
        int edgeUsed=0;

        //==> total of time = O(n^2 * log2(n^2)) = O(n^2 * log2(n))
        while(edgeUsed<n){
            int[] curNode=queue.poll();
            if (inMST[curNode[0]]) {
                continue;
            }
            mstCost+=curNode[1];
            edgeUsed++;
            inMST[curNode[0]]=true;

            //Time : O(n)
            for(int i=0;i<n;i++){
                if(!inMST[i]){
                    int nextWeight=Math.abs(points[curNode[0]][0]-points[i][0])+Math.abs(points[curNode[0]][1]-points[i][1]);
                    //Time : O(log(n^2))
                    queue.add(new int[]{i, nextWeight});
                }
            }
        }

        return mstCost;
    }

    public static int minCostConnectPointsPrimAlgorithmOptimization(int[][] points) {
        int n = points.length;
        //Time : O(n)
        boolean[] inMST=new boolean[n];
        int[] minDist=new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0]=0;
        int mstCost=0;
        int edgeUsed=0;

        //==> total of time = O(n^2 * log2(n^2)) = O(n^2 * log2(n))
        while(edgeUsed<n){
            int minCurWeight=Integer.MAX_VALUE;
            int curNode=0;

            for(int i=0;i<n;i++){
                if(!inMST[i]&&minCurWeight>minDist[i]){
                    minCurWeight=minDist[i];
                    curNode=i;
                }
            }
            mstCost+=minDist[curNode];
            edgeUsed++;
            inMST[curNode]=true;

            //Time : O(n)
            for(int i=0;i<n;i++){
                if(!inMST[i]){
                    int nextWeight=Math.abs(points[curNode][0]-points[i][0])+Math.abs(points[curNode][1]-points[i][1]);
                    //Time : O(log(n^2))
                    if(nextWeight<minDist[i]){
                        minDist[i]=nextWeight;
                    }
                }
            }
        }

        return mstCost;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given points representing integer coordinates of some points on 2D-plane
        //+ points[i] = [xi, yi]
        //- The cost of connecting two points [xi, yi] and [xj, yj] is (the manhattan distance)
        // between them: |xi - xj| + |yi - yj|
        //* Return the minimum cost to make all points connected (If there is exactly one simple path between any two points)
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Idea:
        //- Constraint:
        //1 <= points.length <= 1000
        //-10^6 <= xi, yi <= 10^6
        //All pairs (xi, yi) are distinct.
        //--> Do All pairs (xi, yi) are distinct.
        //==> Points sẽ biểu diễn all points ==> Ta có thể dùng index biểu diễn được.
        //
        //- Brainstorm
        //- Làm tương tự chỉ là sẽ cần biểu thị khoảng cách giữa 2 points
        //+ Ta sẽ dùng (index) của array sub-points của points để biểu thị khoảng cách giữa 2 points
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is the number of points
        //- Space: O(n^2)
        //- Time : O( n^2 * log2(n) + n^2)
        //
        //2, Prim algorithm
        //2.1, Idea
        //- Nếu 1 edge add vào thì ta có thể có 2 cases:
        //+ Nó combine với MST (Minimum spaning tree) hiện tại + thêm (1 node mới/ không thêm node nào mới [ Do 2 nodes đó đã thuộc
        //MTS rồi)
        //+ Không combine được với current MST ==> thêm 1 tree con nữa.
        //
        //==> Ta có 1 điều nữa:
        //- Nếu thêm 1 edge vào thì 2 nodes của node có thể:
        //+ Thuộc 2 trees riêng rẽ có sẵn của collections trước đó ==> Add vào sẽ giúp connect trees
        //+ Thuộc cùng 1 tree ==> Add vào cũng như không
        //+ Không thuộc tree nào ==> Add vào tạo new subtree
        //
        //- Ta 1 concept là những node đã có trong MST (tức là đã có thể connected lẫn nhau) sau lần thứ ith add new edge:
        //- Ta sẽ bỏ qua 1 edge nếu:
        //+ 2 node của edge đó cùng thuộc (1 sub-MSTs) nào đó.
        //
        //- Mấu chốt ở đây là (luôn luôn có 1 edge giữa 2 nodes) trong graph
        //==> Bài này (có thể làm theo Prim algorithm được)
        //+ Tức là ta sẽ không bị giới hạn edge
        // ==> Tức là khi add 1 node vào sẽ luôn tìm được 1 node nối với nó next turn mà (min distance nhất)
        //** Bên trên hơi hiểu nhầm:
        //+ Yêu cầu tìm MST tức là luôn có 1 path giữa các node ==> Nên kiểu gì từ 1 node cũng span ra được all remaining nodes
        //+ Ở đây đổi view từ edge sang node ==> Nên sẽ không quan tâm đến việc add 1 edge vào thì sẽ có các trường hợp như thế nào?
        //  + Không cần union find vì theo view node ==> span ra thì ta luôn có 1 MST thay vì (1 tổ hợp collection phân mảnh như làm
        //  theo kiểu UNION FIND.
        //
        //- Rule:
        //+ Ta sẽ start từ bất kỳ điểm nào trong graph
        //+ Do node là points có (length=n) ==> point sẽ bao gồm thứ tự từ (0 to n-1)
        //--> Ta sẽ start với node(0,0) :
        // + index=0 và weight lúc đầu =0 (Tức là chọn điểm bắt đầu thì weight luôn = 0)
        //** Bài toán yêu cầu
        //- Tìm MST cost ==> Theo giải thích bên trên thì ta có thể làm kiểu kiểm tra các node mới có trong MST được
        //+ Nếu có : ignore
        //+ Nếu chưa có thì add (new node) vào
        //
        //- Liệu làm kiểu này có ra kết quả MIN hay không?
        //Ex:
        // 1 - 2 - 3
        //- Nếu quy ra điểm thì mỗi node phải bắt buộc phải có trong MST
        //+ Nên ta luôn hướng đến việc (add node đó) vào mà (tốn min cost)
        //==> Ta có thể đi từ bất cứ node nào : Ở đây ta đi từ node có index=0
        //+ Union find thì vẫn là tư duy add từ min --> max cost của mọi edge sao cho min cost
        //==> Vẫn qyay lại tư duy này
        //- Liệu có case (min cost) với node này ==> Nhưng node khác thì không được (min cost) hay không
        //+ Vẫn là cơ chế thêm node vào thôi ==> (Chọn min cost để connect đến node đó)
        //===> 2 trường hợp này độc lập nhau nên không xảy ra
        //** Vin vào việc muốn connect đến new node "Để suy luận thôi"
        //
        //** Conclusion:
        // Việc add từng min edge vào ==> Sau đó tìm xem connect chưa
        //<=> Việc từng node tìm min cost ==> Đến điểm nằm trong MST
        //- Tìm min cost new node ==> All node nằm trong MST như thế nào
        //+ Việc tìm min cost đến điểm nằm trong MST
        // ==> Ta chỉ cần (add all new weight với việc connect từng new node) vào pair là được
        //  + Pair ở đây include cả (node đó, và weight để đến node đó)
        //      + Node đó để new node có thể connect đến tiếp
        //      + weight để tính cost khi pop node ra.
        // ==> sorted
        //+ pop connect operation nào ==> Lấy cái đó
        //
        //- Special case:
        //- Bởi vì add vào queue thì :
        //+ 1 số node lúc add vòo thì chưa có trong MST
        //  + Nhưng sau khi xử lý các node trong queue trước node đó ==> Thì node đó đã in MST rồi
        //==> Trong while() ta cần phải check:
        //=======
        //if (inMST[curNode[0]]) {
        //    continue;
        //}
        //==> Continue nếu có node hiện tại muốn xét rồi.
        //=======
        //
        //1.1, Optimization
        //- Ở đây ta không dùng PriorityQueue nữa --> Ta chuyển sang dùng array min distance để lưu min all node.
        //+ min distance[i] : là khoảng cách min nhất để đến được (node thứ ith)
        //- Add:
        //+ 1 array (minDist)
        //+ Update đoạnc check min dist curnode ngay đầu
        //+ Trong for update new min distance nếu không trong MST
        //
        //1.2, Complexity
        //- Space : O(n^2)
        //- Time : O(n^2 * log2(n)) ==> O(n^2) nếu use optimized solution.
        //
        //#Reference
        //2152. Minimum Number of Lines to Cover Points
        int[][] points = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        System.out.println(minCostConnectPointsPrimAlgorithm(points));
        System.out.println(minCostConnectPointsPrimAlgorithmOptimization(points));
    }
}
