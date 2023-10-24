package E1_minimum_spaning_tree;

import javafx.util.Pair;

import java.util.*;

public class E3_OptimizeWaterDistributionInAVillage {

    public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        int[] minCost=new int[n+1];

        for(int i=1;i<=n;i++){
            minCost[i]=Integer.MAX_VALUE;
        }
        boolean[] inMst=new boolean[n+1];
        int m= pipes.length;
        int initHouse;
        int usedPipe=0;
        initHouse=pipes[0][0];
        minCost[initHouse]=pipes[0][2];
        List<Integer> curListHouses=new ArrayList<>();
        int rs=0;

        while(usedPipe<=m){
            int curMinDis=Integer.MAX_VALUE;
            int curHouse=-1;

            for(int i=1;i<=n;i++){
                if(!inMst[i]&&curMinDis>minCost[i]){
                    curMinDis=minCost[i];
                    curHouse=i;
                }
            }
            if(curHouse==-1){
                for(int i=0;i<m;i++){
                    if(!inMst[pipes[i][0]]){
                        curHouse=pipes[i][0];
                        break;
                    }else if(!inMst[pipes[i][1]]){
                        curHouse=pipes[i][1];
                        break;
                    }
                }
            }
            System.out.printf("Curhouse: %s, usedPipe: %s\n",curHouse, usedPipe);
            if(curHouse==-1){
                int minWell=Integer.MAX_VALUE;
                int houseHavingMinWell=0;
                int chosenWell=-1;

                for(int house: curListHouses){
                    if(minWell>wells[house-1]){
                        minWell=wells[house-1];
                        houseHavingMinWell=house;
                    }
                    minWell=Math.min(minWell, wells[house-1]);
                    if(minCost[house]>wells[house-1]){
                        minCost[house]=wells[house-1];
                    }else {
                        chosenWell=house;
                    }
                    rs+=minCost[house];
                }
                if(chosenWell==-1){
                    rs-=minCost[houseHavingMinWell];
                    rs+=minWell;
                }
                curListHouses.clear();
                break;
            }
            curListHouses.add(curHouse);
            inMst[curHouse]=true;
            usedPipe++;

            for(int i=0;i<m;i++){
                int x=pipes[i][0];
                int y=pipes[i][1];
                int cost=pipes[i][2];

//                System.out.printf("Ouside, Cur house: %s, x:%s, y:%s, cost: %s\n", curHouse, x, y, cost);
                if(curHouse==x&&!inMst[y]&&cost<minCost[y]){
                    minCost[y]=cost;
//                    System.out.printf("Cur house: %s, Update house: %s, cost: %s\n", curHouse, y, cost);
                }else if(curHouse==y&&!inMst[x]&&cost<minCost[x]){
                    minCost[x]=cost;
//                    System.out.printf("Cur house : %s, Update house: %s, cost: %s\n", curHouse, x, cost);
                }
            }
        }

        int minWell=Integer.MAX_VALUE;
        int houseHavingMinWell=0;
        int chosenWell=-1;
        System.out.println(curListHouses);

        for(int house: curListHouses){
            if(minWell>wells[house-1]){
                minWell=wells[house-1];
                houseHavingMinWell=house;
            }
            if(minCost[house]>wells[house-1]){
                minCost[house]=wells[house-1];
                chosenWell=house;
            }
            rs+=minCost[house];
        }
        if(chosenWell==-1){
            rs-=minCost[houseHavingMinWell];
            rs+=minWell;
        }
        for(int i=1;i<=n;i++){
            if(minCost[i]==Integer.MAX_VALUE){
                rs+=wells[i-1];
            }
        }

        for(int i=1;i<=n;i++){
            System.out.printf("House: %s, cost: %s\n", i, minCost[i]);
//            int min=Math.min(minCost[i], wells[i-1]);
//            rs+=minCost[i];
//            System.out.printf("House: %s, min cost: %s\n", i, min);
        }

        return rs;
    }

    public static int minCostToSupplyWaterReference(int n, int[] wells, int[][] pipes) {
        //Space: O(m+n)
        //- (a,b): node, cost
        //==> 1 edge : 2 node costs
        //==> 2*m
        //- well : n nodes
        //==> n
        //Time : O((m+n)*log(m+n)
        PriorityQueue<int[]> queue=new PriorityQueue<>((a, b) -> (a[1]-b[1]));
        //Space: O(n+m)
        //+ each node : k edge
        //the number of edges)= m ==> O(m)
        //+ n node ==> O(n)
        //
        List<List<int[]>> graph=new ArrayList<>();

        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }

        //Time : O(n*log(n+m))
        for(int i=0;i<n;i++){
            int[] startBuildWell=new int[]{i+1, wells[i]};
            graph.get(0).add(startBuildWell);
            queue.add(startBuildWell);
        }
        //Time: O(m)
        for(int[] pipe: pipes){
            int house1=pipe[0];
            int house2=pipe[1];
            int cost=pipe[2];
            graph.get(house1).add(new int[]{house2, cost});
            graph.get(house2).add(new int[]{house1, cost});
        }
        //Space : O(m)
        HashSet<Integer> inMst=new HashSet<>();
        inMst.add(0);
        int totalCost=0;

        //- Bỏ qua đoạn này vì trong tính sum rồi
        while(inMst.size()<n+1){
            int[] curPipe=queue.poll();
            int cost=curPipe[1];
            int curHouse=curPipe[0];

            if(inMst.contains(curHouse)){
                continue;
            }
            inMst.add(curHouse);
            totalCost+=cost;

            //Time : O(k) ==> Sum=O(m+n)
            for(int[] nextNode: graph.get(curHouse)){
                if(!inMst.contains(nextNode[0])){
                    //Time : O(log(n+m))
                    queue.add(nextNode);
                }
            }
        }
        return totalCost;
    }

    public static void main(String[] args) {
        //** Requirement:
        //+ Well: Giếng
        //- Given (well) and (pipes) array
        //- Wells[i] present cost to build a well inside it (the house at ith position)
        //- Pipe[i] present cost to connect pipe between (house1-j and house2-j)
        //  + pipes[j] = [house1-j, house2-j, cost-j]
        //  + The connection is bi-directional
        //- There could be (multiple valid connections) between the (same two houses) with (different costs)
        //- Ta có 2 lựa chọn:
        //+ Build well bên trong house
        //+ Build pipe để dẫn nước (từ well khác đến nó) : pipe in water from another well
        //
        //* Return minimum total cost to supply water to all houses
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Idea:
        //- Constraint:
        //2 <= n <= 104
        //wells.length == n
        //0 <= wells[i] <= 105
        //1 <= pipes.length <= 104
        //pipes[j].length == 3
        //1 <= house1j, house2j <= n
        //0 <= costj <= 105
        //house1j != house2j
        //
        //- Brainstorm
        //- Well[]: Sẽ luôn có giá trị mặc định để supply water for each house
        //==> Ta có thêm lựa chọn không build pipe cho 1 số houses:
        //  + Không có trong pipes (Tức là không có pipe) ==> Bắt buộc phải build well
        //  + Hoặc là chi phí đến house đó > chi phí đi trực tiếp ==> Build well sẽ tốt hơn
        //
        //- Giả sử:
        //- Chi phí build pipe đến (ith house) mất x
        //- Chi phí build well inside (ith house) mất y
        //==> Ta sẽ chọn min(x,y)
        //Vì:
        //+ Đều thoả mãn đi qua được ith house
        //+ Và qua được (ith) house thằng nào cho chi phí ít hơn ==> Thì ta sẽ chọn thằng đó
        //+ Với những cases mà connect đến (ith house) không khác gì nếu chọn min
        //
        //- Vì cost tính các houses độc lập nhau:
        //- Idea
        //+ Tính all chi phí minCost cho từng house nếu sử dụng pipe all
        //+ Sau đó update lại các giá trị nếu chúng vượt quá việc build well trực tiếp
        //- Idea này có đúng không?
        //+ Ta cần các source well: chọn house để build well như thế nào
        //  + Ta sẽ chọn 1 node đầu với weight=0 bừa ==> Sau đó tính tiếp
        //+ Sau khi tính minCost rồi ==> Ta sẽ so sánh nó với well (Build trực tiếp) + update lại
        //+ Với những node không đến được lấy well luôn.
        //+ Tính sum all --> Result
        //
        //* Cost độc lập nên sẽ không sao ==> Làm ntn được.
        //
        //- Vì house trong pipe không đủ
        //==> Ta cần list ra all house trong đó ==> Để sử dụng để traverse.
        //
        //- Special cases:
        //- Case 1:
        //+ 1 node pipe đến nhiều lần
        //Ex: (1,2), (3,1) ==> 1 đến nhiều lần ==> Phải chọn min cost trong 2 cho init house
        //
        //- Case 2:
        // 2 ---- 1
        // \75312
        //  3 --- 5,  (4)
        //- Ở đây chi phí để đến 2 có thể:
        //= min(3 -> 2, 1 -> 2)
        //- Ở đây bắt buộc phải 1 house có water để nối đến các house còn lại
        // + Nếu chọn 3 có water thì + well của 3
        // + Nếu chọn 5 có water thì + well của 5
        //- Mỗi một tập hợp liên thông phải có ít nhất 1 well
        //1 : 6719
        //2 : 6719
        //3 : 64965 (well)
        //5 : 33304 (well)
        //+ Sum = 6719 + 6719 + 64965 + 33304 + 751 (4) (SAI) ???
        //===> SAI ở chỗ (3 -> 2: 75312) nếu ta tính min ở 2 theo (2 -> 1) chưa đủ
        //  + vì không có (3 -> 2) thì nước không đi từ (3->2) được.
        //
        //- Chuyển từ house --> Sang edge thôi
        //
        //1.1, Exp
        //- Cần xác định clear rõ công thức tính kết quả:
        //+ Thay vì sai như trên rõ ràng là tính cost theo edge/pipe ==> Mà lại quy về tổng node/house thì là SAI rồi.
        //
//        int n = 3;
//        int[] wells = {1,2,2};
//        int[][] pipes = {{1,2,1},{2,3,1}};
//
//        int n = 2;
//        int[] wells = {1,1};
//        int[][] pipes = {{1,2,1},{1,2,2}};
        //
        //** PRIM ALGORITHM
        //To implement Prim's algorithm, essentially we will need the following three data structures:
        //
        //- adjacency list: we need this to represent the graph, i.e. vertices and edges.
        // The adjacency list can be a list of lists or a dictionary of lists.
        //- set: we need a set to maintain all the vertices that we have added to the final minimum spanning tree,
        // during the construction of the tree.
        //With the help of set, we can determine whether a vertex has been added or not.
        //
        //- heap: due to the nature of the greedy strategy, at each step, we can determine the best edge to be added based on
        // the cost it will add to the tree.
        //Heap (also known as a priority queue) is a data structure that allows us to retrieve the minimum element
        // in constant time and to remove the minimum element in logarithmic time.
        // This fits our need to repeatedly find the lowest cost edge perfectly.
        //
        //- Với phương pháp Prim như thế này thì bài classic ta sẽ dùng
        //  + list of edge(a, b): Tức là ta sẽ sort theo cost các edge với nhau
        //  + Mỗi lần ta sẽ pop each edge ==> Xét các node còn lại xem có thể đến được node nào ==> Add thêm edge.
        //
        //- Bài này thì liên quan đến việc mỗi tập hợp (collection) phải có ít nhất 1 well
        //  + Với bài Prim thông thường thì vì each node phải qua --> Nên với node đó ta sẽ chọn ngay được 1 trường hợp best.
        //--> Ở đây cũng thế mỗi node có thể:
        //  + Build well directly
        //  + Going from other nodes
        //--> Choose the best way, we will (minimize the cost) to arrive the specific node.
        //- Vấn đề thứ 2, mỗi tập hợp có ít nhất 1 well
        //  + Thế nên nếu luôn chọn giải pháp minimize cost ==> Nó sẽ bị case các node go each other mostly,
        //  they won't be built any well
        //
        //Ex:
        //1 -- 2 -- 3, 4
        //wells=[4,4,4,5]
        //pipes=[{1,2,1},{2,3,1}]
        //+ Ở đây thì cost của pipe luôn < cost to build a new well
        //+ 4 is the independent node
        //
        //- Rule:
        //+ Ta sẽ luôn có ít nhất 1 well ==> Init ta sẽ lấy well có cost nhỏ nhất để build.
        //  + Có trường hợp build well-i > well-j thì tốt hơn không?
        //  Ex: (well-i=4) > (well-j=2)
        //  well-i, node-i đi kèm với cost pipe-i = 100
        //  well-j, node-j đi kèm với cost pipe-j = 3
        //  Ex:
        //  i=1, j=2
        //  well={1,4,2,6}
        //  pipes=[{1,2,4}, {1,4,100},{6,1,3},{2,6,3]
        //  6
        //  |   \
        //  1 --- 2
        //  |
        //  4
        //-> Trả lời: Không
        //- Lý do:
        //- 2 wells có 2 trường hợp:
        //+ Connect với nhau
        //  + Thì nên chọn cái min ==> Vì chúng connect với nhau nên ==> Cùng tập hợp các edge sẽ được chọn từ min --> max như cơ
        //  chế bình thường
        //+ Không connect với nhau
        //  + Chọn cả 2 ==> Để có thể prove the water to both collections.
        //
        //- Với nhưng tập hợp mà riêng rẽ, JUMP giữa các tập hợp như thế nào?
        //  + PRIM khác với BFS và DFS ở chỗ là khi spanning tree ==> Thì nó sẽ add thêm lựa chọn
        //  ==> Về sau ta có thể chọn cách tối ưu nhất : Để có thể có đủ nodes trong MST.
        //  ==> Nó là cái việc "Check all NODES liên tục" ==> Add edge để node exists in MST
        //  ==> ĐẾN KHI all of nodes do exists in MST thì stop
        //--> Việc jump <=> Check exists in MST.
        //** Tại sao sang new collection ta vẫn có thể tìm được well mới?
        //- Vì khi kết thúc old collection, last node can not get any edge
        //  ==> At the beginning of the loop, ta chỉ có một cách duy nhất là pop từ heap ra ==> Pop từ heap ra thì nếu chưa add
        //  + Thì sẽ chỉ là node well mà ta đã add từ lúc đầu ==> Luôn tìm được min well đầu tiên.
        //
        //- Vậy chọn well mới trong node như thế nào? ==> "ĐÃ TRẢ LỜI THÀNH CÔNG"
        //+ 1 well chỉ được chọn khi:
        //  + Không còn edge đi đến nó được xét nữa
        //     + Xảy ra khi we have only one remaining node
        //      Ex:
        //      wells=[3,3]
        //      pipes[{1,2,1}]
        //      + Ở đây ta chỉ có 1 edge ==> Nhưng khi xét cho 1 ==> Remove 1 edge {1,2} rồi
        //      ==> Khi xét MST ta vẫn còn 1 node chưa exists là 2 ==> 2 thì chỉ có well của 2 vẫn còn trong queue (Well đi kèm node
        //      để validate có trong MST hay không)
        //      ** Khi không còn edge đến 2 + trong queue chỉ còn well của 2 ==> Ta sẽ xét đến 2
        //      + Nếu mà ta có (2 cạnh/3 nodes) thì khi đó có 1 node có well rồi thì khi đến last turn có cần xét well hay không?
        //      wells=[3,3,2]
        //      pipes[{1,2,1},{3,1,1}]
        //          + Xét well,i=3, MST= empty
        //          + curNode=3, totalCost=2, MST=3 xét edge {3,1,1}
        //          + curNode=1, totalCost=2+1, MST=1, xét edge {1,2,1} do well của i=1 > pipe-1, totalCost=3+1=4,
        //          + curNode=2
        //      ** Công thức tính cost:
        //      1 -- 2 -- 3
        //      total cost= well-1 + 2[cost(1->2)] + 3[cost(2-->3)]
        //  + Không có edge nào
        //      + Sub-graph has only one node.
        //
        //1.1, Exp
        //** Công thức tính cost:
        //- Chọn (ending point) as the (root node):
        //- 1 -- 2 -- 3
        //+ total cost= well-1 + 2[cost(1->2)] + 3[cost(2-->3)]
        //
        //+ 1 well chỉ được chọn khi:
        //  + Hết edge đến từ 1 node ==> ta phải pop từ heap ra (các phần tử add vào ban đầu)
        //  + Không có edge nào
        //
        //** Tại sao sang new collection ta vẫn có thể tìm được well mới?
        //- Vì khi kết thúc old collection, last node can not get any edge
        //  ==> At the beginning of the loop, ta chỉ có một cách duy nhất là pop từ heap ra ==> Pop từ heap ra thì nếu chưa add
        //  + Thì sẽ chỉ là node well mà ta đã add từ lúc đầu ==> Luôn tìm được min well đầu tiên.
        //
        //1.2, Optimization
        //1.3, Complexity
        //- Space complexity: O(n+m)
        //- Time complexity: O(log(n+m) *(n+m))
        //
//        int n = 5;
//        int[] wells = {46012,72474,64965,751,33304};
//        int[][] pipes = {{2,1,6719},{3,2,75312},{5,3,44918}};

        int n = 3;
        int[] wells = {3,3,2};
        int[][] pipes = {{1,2,1},{3,1,1}};
        System.out.println(minCostToSupplyWater(n, wells, pipes));
        System.out.println(minCostToSupplyWaterReference(n, wells, pipes));
    }
}
