package E1_Topological_sort;

import java.util.*;

public class E10_LargestColorValueInADirectedGraph {

    public static class Node{
        int node;
        HashMap<Integer, Integer> nodeToVal;
        public Node(int node, HashMap<Integer, Integer> nodeToVal){
            this.node=node;
            this.nodeToVal=nodeToVal;
        }
    }

    public static boolean isCycleWrong(boolean[] trace,int n, Queue<Integer> queueCycle,HashSet<String> visited, List<Integer>[] adjNodes){
        while(!queueCycle.isEmpty()){
            System.out.printf("Visited: %s\n",visited);
//            System.out.printf("Queue: %s\n",queueCycle);
            Integer curNode=queueCycle.poll();
            for(Integer nextNode: adjNodes[curNode]){
                if(visited.contains(curNode+""+nextNode)){
                    System.out.println(curNode+""+nextNode);
                    return true;
                }
//                if(!trace[nextNode]){
//                    queueCycle.add(nextNode);
//                }else{
//                    continue;
//                }
//                trace[nextNode]=true;
                queueCycle.add(nextNode);
                visited.add(curNode+""+nextNode);
            }
        }
        return false;
    }

    public static boolean isCycle(boolean[] trace, int node, List<Integer>[] adjNodes){
        for(Integer nextNode: adjNodes[node]){
            if(trace[nextNode]){
                return true;
            }
            trace[nextNode]=true;
            boolean isCycle=isCycle(trace, nextNode, adjNodes);
            trace[nextNode]=false;
            if(isCycle){
                return true;
            }
        }
        return false;
    }

    public static int largestPathValueSlow(String colors, int[][] edges) {
        int n=colors.length();
        int[] inDegree=new int[n];
        List<Integer>[] adjNodes=new ArrayList[n];

        if(edges.length==0&&n!=0){
            return 1;
        }
        for(int i=0;i<n;i++){
            adjNodes[i]=new ArrayList<Integer>();
        }
        for(int[] edge: edges){
            int x=edge[0];
            Integer y=edge[1];
            inDegree[y]++;
            adjNodes[x].add(y);
        }
        Queue<Node> nodes=new LinkedList<>();
        Queue<Integer> queueCycle=new LinkedList<>();
        int rs=-1;
        boolean[] trace=new boolean[n];

        for(int i=0;i<n;i++){
            if(inDegree[i]==0){
                HashMap<Integer, Integer> rootMap=new HashMap<>();
                int curColor=colors.charAt(i)-'a';
                rootMap.put(curColor, 1);
                nodes.add(new Node(i, rootMap));
                queueCycle.add(i);
                trace[i]=true;
                boolean isCycle=isCycle(trace, i, adjNodes);
                if(isCycle){
                    return -1;
                }
                rs=1;
            }
        }
        int count=0;
        HashMap<Integer, HashMap<Integer, Integer>> prevNodeToVals=null;
        HashMap<Integer, HashMap<Integer, Integer>> nodeToVals=new HashMap<>();

        while(!nodes.isEmpty()){
            int size=nodes.size();

            //- Because We won't have the same node in multiple paths
            //--> We will use the value of node to merge
            //Ex:
            //          1(a)   2(b)
            //            \    /
            //             2(b)
            //
            //Node, <color, count>
            HashMap<Integer, HashMap<Integer, Integer>> tmp=new HashMap<>();

            for(int i=0;i<size;i++){
                Node curNode=nodes.poll();
//                System.out.printf("Cur node: %s, Next nodes: %s\n",curNode.node, adjNodes[curNode.node]);

                for(Integer nextNode: adjNodes[curNode.node]){
                    //Get color
                    int color=colors.charAt(nextNode)-'a';

                    if(!nodeToVals.containsKey(nextNode)){
                        HashMap<Integer, Integer> previousMap=curNode.nodeToVal;
                        HashMap<Integer, Integer> nextNodeMap= new HashMap<>(previousMap);
                        Integer oldVal=nextNodeMap.get(color);
                        oldVal=oldVal==null?0:oldVal;
                        int newVal=oldVal+1;
                        nextNodeMap.put(color, newVal);
                        nodeToVals.put(nextNode, nextNodeMap);
                        tmp.put(nextNode, nextNodeMap);
                    }else{
                        //Map of the previous caculation for this node
                        HashMap<Integer, Integer> oldMap=nodeToVals.get(nextNode);
                        //Map of the previous node
                        HashMap<Integer, Integer> previousMap=curNode.nodeToVal;
//                        System.out.printf("Node: %s, color: %s, Update map: %s, old map: %s\n", nextNode, color, previousMap, oldMap);
                        for(int curColor: previousMap.keySet()){
                            int countColor=previousMap.get(curColor);
                            if(curColor==color){
                                countColor++;
                            }
                            if(!oldMap.containsKey(curColor)||oldMap.get(curColor)<countColor){
                                oldMap.put(curColor, countColor);
                                tmp.put(nextNode, oldMap);
                            }
                        }
//                        System.out.printf("After updating: %s\n", oldMap);
                    }
                }
            }
//            System.out.println(nodeToVals);
//            if(count==5){
//                return 1;
//            }
            count++;
            for(int key: tmp.keySet()){
                HashMap<Integer, Integer> curMap=tmp.get(key);
                nodes.add(new Node(key, curMap));
            }
            if(nodes.isEmpty()&&!prevNodeToVals.isEmpty()){
                for(int key: prevNodeToVals.keySet()){
                    HashMap<Integer, Integer> mapColor=prevNodeToVals.get(key);
                    for(int color: mapColor.keySet()){
                        rs=Math.max(rs, mapColor.get(color));
                    }
                }
            }
            prevNodeToVals=nodeToVals;
        }
        return rs;
    }

    public static HashMap<Integer, HashMap<Integer, Integer>> nodeToVals;
    public static HashMap<Integer, Integer> solution(int node,boolean[] visited, List<Integer>[] adjNodes, String colors){
        if(nodeToVals.containsKey(node)){
            return nodeToVals.get(node);
        }
        HashMap<Integer, Integer> curRs=new HashMap<>();
        //Time : O(m)
        for(Integer nextNode: adjNodes[node]){
            if(visited[nextNode]){
                return null;
            }
            visited[nextNode]=true;
            //Time : O(m)
            HashMap<Integer, Integer> curNodes=solution(nextNode, visited, adjNodes, colors);
            visited[nextNode]=false;
            if(curNodes==null){
                return null;
            }
            //Time : O(26)
            for(int color: curNodes.keySet()){
                int newCount=curNodes.get(color);
                if(!curRs.containsKey(color)){
                    curRs.put(color, curNodes.get(color));
                }else if(newCount>curRs.get(color)){
                    curRs.put(color, newCount);
                }
            }
        }
        int curColor=colors.charAt(node)-'a';
        curRs.put(curColor, curRs.getOrDefault(curColor, 0)+1);
//        System.out.printf("Current node: %s, HashMap: %s\n", node, curRs);
        nodeToVals.put(node, curRs);
        return curRs;
    }

    public static int largestPathValue(String colors, int[][] edges) {
        //Space : O(n)
        int n=colors.length();
        int[] inDegree=new int[n];
        List<Integer>[] adjNodes=new ArrayList[n];

        if(edges.length==0&&n!=0){
            return 1;
        }
        //Space: O(n+m)
        //Ex:
        //[1, [2,3,4]] ==> The number of edges
        //[2,[5,6,7]]
        //+ Space = Space (1+2...)
        //+ Space at i step = Space (edges)
        for(int i=0;i<n;i++){
            adjNodes[i]=new ArrayList<Integer>();
        }
        //Space : O(n)
        boolean[] listNodes=new boolean[n];
        int initNode=-1;

        //Time : O(n)
        for(int[] edge: edges){
            int x=edge[0];
            initNode=x;
            Integer y=edge[1];
            inDegree[y]++;
            adjNodes[x].add(y);
            listNodes[x]=true;
        }
        Queue<Node> nodes=new LinkedList<>();
        //Space : O(n)
        boolean[] visited=new boolean[n];
        int rs=-1;

        for(int i=0;i<n;i++){
            if(inDegree[i]==0){
                HashMap<Integer, Integer> rootMap=new HashMap<>();
                int curColor=colors.charAt(i)-'a';
                rootMap.put(curColor, 1);
                if(listNodes[i]){
                    nodes.add(new Node(i, rootMap));
                }
//                boolean isCycle=isCycle(trace, i, adjNodes);
//                if(isCycle){
//                    return -1;
//                }
                rs=1;
            }
        }
        nodeToVals=new HashMap<>();

        if(nodes.isEmpty()&&initNode!=-1){
            visited[initNode]=true;
            //Time : O(m)
            boolean isCycle=isCycle(visited, initNode, adjNodes);
            if(isCycle){
                return -1;
            }
        }
        while(!nodes.isEmpty()){
            int size=nodes.size();

            //- Because We won't have the same node in multiple paths
            //--> We will use the value of node to merge
            //Ex:
            //          1(a)   2(b)
            //            \    /
            //             2(b)
            //
            //Node, <color, count>
            for(int i=0;i<size;i++){
                int curNode=nodes.poll().node;
                if(!listNodes[curNode]){
                    continue;
                }
//                System.out.println(curNode);
                visited[curNode]=true;
                //Time : O(m)
                //Space : O(
                HashMap<Integer, Integer> curRs=solution(curNode, visited, adjNodes, colors);
                if(curRs==null){
                    return -1;
                }
                visited[curNode]=false;
                for(int color: curRs.keySet()){
                    rs=Math.max(rs, curRs.get(color));
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given directed graph edges when edges[j] = [aj, bj] indicates that there is (a directed edge) from node (aj) to node (bj)
        //- String colors when colors[i] is a lowercase English letters representing the color of the (ith) node in the graph
        //  + colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed).
        //- A valid path is a sequence of nodes : x1 -> x2 -> ... -> x(k) such that there is a directed edge from x(i) to x(i+1) for every (1 <= i <k)
        //- The color value of the path : Là số lượng lớn nhất lần xuất hiện của 1 color trên path đó
        //* Return the largest color value của bất kỳ valid path nào trong graph
        //<> return -1 nếu có cycle.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //n == colors.length
        //m == edges.length
        //1 <= n <= 10^5
        //0 <= m <= 10^5
        //colors consists of lowercase English letters.
        //0 <= aj, bj < n
        //--> N khá lớn
        //+ colors là lower case ==> chars[26]
        //
        //- Brainstorm
        //- Ở đây mục đích của bài toán là return max frequency của color nào đó xuất hiện trong valid path.
        //Ex:
        // 0 -> 2 -> 3 -> 4
        // |
        // 1
        //- Tính số lượng max color trên 1 path như thế nào?
        //+ Ta sẽ xuất phát từ đâu để xét valid node.
        //+ Lưu thông tin colors ở intermediate node + tính tiếp như thế nào?
        //
        //- Ta sẽ scan all paths --> Chỉ tính các path dài nhất có đúng không?
        //+ Path dài nhất chưa chắc đã return max frequency
        //+ Ta nên tìm root trước
        //==> Sau đó xét các path theo đó?
        //+ Là bài này không tìm root được vì:
        //* Directed graph không chỉ có 1 root
        //==> Traverse by using topological sort : Tức là traverse theo (số edge connect với node đó)
        //
        //- Ta sẽ đi từ những node có indegree=0 ==> Tức là không có in edges
        //==> Sau đó dùng bfs để đi sâu hơn + kết hợp việc tính size thì ta có thể tối ưu được time.
        //
        //- Lưu các giá trị count trung gian như thế nào?
        //+ colors là lower case ==> chars[26]
        //
        //Ex:
        //colors = abaca
        // 0(a) -> 2 (a) -> 3 (c) -> 4 (a)
        // |
        // 1 (b)
        //+ Giả sử lưu intermediate string :
        //+ 2 : aa
        //+ 3 : aac
        //+ 3 : aaca
        //+ Tính số lần max nhất của count character trong string
        //
        //- Ta cần memorize lại các giá trị đã tính
        //              5 (a)       4(b)
        //Ex:         /     \    /
        //         0 (a)     3 (b)
        //       /    \        \
        //     1 (b)   2 (a)    4 (b)
        //+ Khi xét 5 ta đã đi qua được 3 rồi --> 4 sẽ không cần tính lại
        //+ Vì storage intermediate result
        //  + Ta sử dụng bottom up strategy
        //+ Liệu có tính chọn theo max của các sub-graph được không
        //
        //- Mỗi node sẽ được tính theo 26 colors khác nhau:
        //+ 26 colors này sẽ được sắp xếp giảm dần theo số lượng color xuất hiện mỗi loại nhiều nhất mà ta có thể đi xuống
        //top down để lấy
        //Ex:
        // 5(a) thì ta sẽ lưu : ({2,a}, {2,b})
        //- Ta sẽ lưu giá trị trung gian như thế nào để:
        //+ Giả sử giá trị trước đó đi trên nhiều paths ==> Cũng có count của 26 ký tự riêng
        //==> Thì ta có thể so sánh với điểm sắp tới đến (Nếu điểm đó được tính rồi) --> Để loop tìm color tốt nhất
        //  + --> Lúc đó ta sẽ lưu lại điểm ntn
        //                     3 (b)
        //                    /
        //                  1 (b)
        //                /
        //              5 (a)       4(b)
        //            /     \    /
        //         0 (a)     3 (b)
        //       /    \        \
        //     1 (b)   2 (a)    4 (b)
        //+ 0(a) -> 5(a) tính như thế nào
        //  + 0(a) : ({a:2}, {b:1}}
        //  + 3(b) : ({b:2})
        //  + 5(a) : ==> Lấy gộp max của cả 2
        //      + ({a:2}, {b:2}) + a
        //      + ({a:3}, {b:2})
        //- Ta phải lấy cả b vì giá sử như case bên trên thì 3(b) --> 1(b) ta có ({b:2}) rồi
        //==> Nên kết quả sẽ cần kết hợp với (b)
        //- Giá trị trung gian --> Phù hợp với việc update ==> Ta sẽ dùng hashmap
        //
        //- Nếu ta traverst từ top --> down
        //--> Ta vẫn có thể merge được.
        //
        //- Ngoải ra nếu có cycle --> return -1
        //+ Cycle trong directed graph --> Tính theo edge chứ không phải node
        //  + Trong bài này thì số lượng node khá lớn ==> Không nên dùng visited[][]
        //  + Ta sẽ tính space theo số edges : visited[i+""+j]
        //  + ==> Check graph liệu có cycle hay không trong directed graph
        //- Có 2 cases như sau phải phân biệt được:
        //          1
        //        /   \
        //      2      3
        //       \    /
        //        v  v
        //         4
        //* Nếu viết kiểu BFS này thì nó sẽ chạy qua (2,3) ==> Thế nên không thể dùng BFS
        //** NOTE:
        //- Nếu muốn check có cycle hay không thì phải dùng DFS
        //Và
        //          1
        //        /   \
        //      2      3
        //     ^       v
        //    /         \
        //   /           v
        //   5 <--------  4
        //- Với nhưng case update giá trị --> Ta sẽ không thêm vào (queue/ tmp map) nữa vì đã tính rồi
        //  + Ta chỉ merge thêm value vào (nodeToVals) chứ không cần add thêm vào queue
        //
        //- Test step:
        //
//        String colors = "abaca";
//        int[][] edges = {{0,1},{0,2},{2,3},{3,4}};
//        String colors = "a";
//        int[][] edges = {{0,0}};
        //Ex:
        //                     0 (b)
        //                    /
        //                  1 (b)
        //                /
        //              2 (a)       3(b)
        //            /     \    /
        //         5 (a)     4 (b)
        //       /    \        \
        //     6 (b)   7 (a)    8 (b)
        //
//        String colors = "bbabbabab";
//        int[][] edges = {{0,1},{1,2},{2,5},{2,4},{5,6},{5,7},{4,8},{3,4}};
        //- Case này thì 4 sẽ được tính từ 3 trước ==> node-0 --> Đến 4 thì còn rất lâu
        //- Expected result : {b:4}
        //
        //Ex:
        //                     0 (b)
        //                    /
        //                  1 (b)
        //                /
        //              2 (a)       3(b)
        //            /     \    /
        //         5 (b)     4 (b)
        //         /
        //      1 (b)
//        String colors = "bbabbb";
        //- Case này sẽ bị cycle
//        int[][] edges = {{0,1},{1,2},{2,5},{2,4},{5,1},{3,4}};
//        String colors = "b";
        //- Case này sẽ bị cycle
//        int[][] edges = {{0,0}};
//        String colors = "b";
        //- Case này sẽ bị cycle
//        int[][] edges = {};
        //
        //- Liệu việc return -1 có đúng không?
        //Ex:
        //               6(c)    0 (b)
        //                \    /
        //                  1 (b)
        //                /
        //              2 (a)       3(b)
        //            /     \    /
        //         5 (b)     4 (b)
        //         /
        //      1 (b)
        //- Không cần thêm node vào queue vì ta không cần thêm node đó để tính lại nữa
        //+ Vậy thì khi merge vào thì các giá trị tiếp theo có cần tính lại không?
        //--> Đoạn này đang gặp vấn đề trong việc tính lại và lưu visited để check cycle
        //
        //- Làm kiểu top down như thế này --> Không đảm bảo tốc độ
        //==> Do mỗi lần update value --> Ta cần add lại node mới ==> Recalculate dẫn đến slow
        //
        //- Nên ta sẽ quay lại tư duy ban đầu --> Bottom up
        //+ Lưu lại các giá trị intermediate
        //
        //Ex:
        //               6(c)    0 (b)
        //                \    /
        //                  1 (b)
        //                /
        //                2 (a)       3(b) {b:2}
        //            /          \    /
        //         5 (b) {b:2}   4 (b) {b:1}
        //         /
        //      1 (b) {b:1}
//        String colors="hhqhuqhqff";
//        int[][] edges={{0,1},{0,2},{2,3},{3,4},{3,5},{5,6},{2,7},{6,7},{7,8},{3,8},{5,8},{8,9},{3,9},{6,9}};
//        String colors="zaazazwlqqwaazlalwqaaqllzzzwzqlazqaazqlaqlllzqzzalqzwalwlzwzqwzqzaqzwllzlqwlqwwqawqzlqzallwlqaqq";
//        int[][] edges={{0,1},{0,2},{1,2},{2,3},{3,4},{4,5},{5,6},{6,7},{7,8},{6,8},{7,9},{8,9},{4,9},{8,10},{9,10},{5,10},{6,11},{10,11},{11,12},{12,13},{9,13},{10,13},{13,14},{10,14},{13,15},{9,15},{14,15},{15,16},{11,16},{12,17},{16,17},{17,18},{8,18},{15,18},{14,18},{10,19},{17,19},{18,19},{18,20},{17,21},{15,21},{18,21},{21,22},{20,22},{17,23},{21,23},{20,23},{22,23},{19,24},{22,24},{24,25},{23,25},{20,25},{22,26},{16,27},{18,27},{22,28},{27,29},{18,29},{20,29},{10,29},{29,30},{28,30},{28,31},{29,32},{17,32},{30,32},{27,33},{31,34},{34,35},{35,36},{27,36},{31,37},{36,37},{35,38},{38,39},{32,39},{39,40},{40,41},{33,41},{39,41},{36,41},{26,42},{39,42},{34,42},{29,42},{36,42},{41,43},{42,43},{37,43},{42,44},{40,44},{43,44},{23,44},{29,45},{35,46},{28,46},{44,46},{41,47},{44,47},{45,47},{42,48},{44,49},{39,49},{37,49},{45,49},{32,49},{46,50},{47,50},{36,50},{48,50},{49,50},{46,51},{50,51},{50,52},{37,52},{31,53},{37,54},{49,54},{52,54},{40,55},{52,55},{41,55},{54,55},{45,56},{53,56},{55,57},{54,57},{50,57},{47,57},{31,58},{56,58},{56,59},{59,60},{52,60},{50,60},{58,60},{52,61},{50,61},{58,61},{56,61},{61,62},{54,63},{34,63},{28,64},{11,64},{61,64},{52,64},{41,64},{36,65},{33,65},{62,65},{52,65},{65,66},{61,67},{65,67},{59,67},{60,67},{67,68},{66,68},{56,68},{64,68},{65,69},{64,69},{65,70},{70,71},{42,71},{52,72},{53,72},{55,72},{70,73},{65,74},{68,74},{73,74},{72,74},{15,74},{60,75},{69,75},{73,75},{70,75},{72,76},{54,76},{53,76},{74,76},{68,77},{75,77},{71,77},{76,77},{69,77},{72,78},{55,78},{76,78},{60,78},{77,78},{69,79},{74,79},{75,79},{77,79},{78,80},{52,81},{74,81},{78,81},{47,82},{79,82},{81,82},{66,82},{78,83},{81,83},{75,84},{80,84},{38,84},{77,84},{75,85},{80,85},{59,85},{78,86},{84,86},{68,86},{86,87},{85,87},{57,88},{86,88},{77,88},{62,88},{36,89},{89,90},{88,90},{85,91},{88,91},{89,91},{91,92},{82,92},{76,92},{86,92},{71,92},{91,93},{69,93},{82,93},{86,94},{39,94},{83,94},{87,94},{76,95},{94,95},{67,95}};
//        System.out.println(largestPathValueSlow(colors, edges));
        //
        //1.1, Optimization
        //- Đổi tư duy từ top down --> bottom up
        //==> Ta sẽ tối ưu được time bằng cách lưu lại các giá trị trung gian
        //
        //1.2, Complexity
        //* TIME COMPLEXITY OF DFS/ BFS:
        //  + O(V+E)
        //
        //** While iterating with this technique, we move over each node and edge exactly once,
        // and once we are over a node that has already been visited then we backtrack,
        // which means we are pruning possibilities that have already been marked.
        // So hence the overall complexity is reduced from exponential to linear.
        //- Tức là mỗi lần ta sẽ đi qua mỗi node và mỗi edge 1 lần (1 cạnh thì ta sẽ đi qua tầm 2 node)
        //  + Mặc dù các node không có cạnh tiếp theo --> Về cơ bản ta vẫn cần phải mark node đó
        //
        //- Space : O(m+n)
        //- Time : O(m*26+n*26)
        //Since there are m edges at most and while iterating over each edge
        // we try to update the frequencies of all the 26 colors,
        // it would take O(26*m) time.
        //
        //1.4, Kinh nghiệm:
        //- Tìm cycle của directed graph:
        //+ Chỉ có thể dùng DFF vì BFS sẽ bị trường hợp các node sẽ đuổi kịp -> Và chạy cùng đường nhau
        //+ Ta cần phải loop các node có indegree[node]=0 (Tức là không có edge đí vào)
        //+ Không thể dùng BFS để tìm cycle ngay cả dùng visited[ node+""+node1] vì vẫn có cases 2 nodes cùng point to 1 node
        //
        //- Các cases đặc biệt trong topological traverse:
        //+ Không có node vào queue == empty : Ta cần xét 1 node mặc định
        //  + Nếu queue.isEmpty() : Tức là không có node nào đi vào + (có node nào đó !=-1) ==> có cycle
        //  + Cũng có thể có 1 node : return 1
        //
        int[][] edges={{1,2},{2,1}};
        String colors="aaa";
        //          1
        //        /
        //      v
        //     2 ---> 1
        System.out.println(largestPathValue(colors, edges));
        //#Reference:
        //1977. Number of Ways to Separate Numbers
        //763. Partition Labels
        //1591. Strange Printer II
        //2007. Find Original Array From Doubled Array
        //2547. Minimum Cost to Split an Array
        //2473. Minimum Cost to Buy Apples
    }
}
