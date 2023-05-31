package E1_Graph;

import java.util.*;

public class E3_FindEventualSafeStates {

    public static int findParent(int[] parent, int node){
        int prevVal=node;

        while (node!=-1&&node!=parent[node]){
            prevVal=node;
            node=parent[node];
        }
        if(prevVal==node){
            return -1;
        }
        return prevVal;
    }

    public static void addEdge(int[] parent, int u, int v){
        int parentU=findParent(parent, u);
        int parentV=findParent(parent, v);
        parent[v]=parentV;
        parent[u]=parentU;

        if(parentV==-1&&parentU==-1){
            parent[v]=u;
            return;
        }
        if(parentV==parentU){
            return;
        }
        if(parentU==-1){
            parent[u]=v;
            return;
        }
        if(parentV==-1){
            parent[parentU]=v;
            return;
        }
        parent[parentU]=parentV;
    }

    public static List<Integer> eventualSafeNodesWrongUnionFind(int[][] graph) {
        int n=graph.length;
        int[] parent=new int[n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                addEdge(parent, i, j);
            }
        }
        for(int i=0;i<n;i++){
            System.out.println(parent[i]);
        }
        List<Integer> rs=new ArrayList<>();
        for(int i=0;i<n;i++){
            int currentParent=findParent(parent, i);

            if(currentParent!=i){
                rs.add(i);
            }
        }
        return rs;
    }

//    public static void bfsHasCycle(int node, int[][] graph, boolean[] visited, List<Integer> result){
//        Queue<Integer> nodes=new LinkedList<>();
//        nodes.add(node);
//        List<Integer> currentResult=new ArrayList<>();
//
//        while (!nodes.isEmpty()){
//            Integer currentNode=nodes.poll();
//            visited[currentNode]=true;
//            currentResult.add(currentNode);
//
//            for(int i=0;i<graph[currentNode].length;i++){
//                int nextNode=graph[currentNode][i];
//                if(visited[nextNode]){
//                }
//                nodes.add(nextNode);
//                visited[nextNode]=true;
//            }
//        }
//        result.addAll(currentResult);
//    }

    public static void dfsHasCycle(int node){
//        System.out.println(node);
        int[] adjNodes=graphNodes[node];

        for(int i=0;i<adjNodes.length;i++){
            int nextNode=adjNodes[i];
            if(visited[nextNode]||cycleNodes[nextNode]){
                cycleNodes[nextNode]=true;
                int tmpCurrentNode=node;

                while (tmpCurrentNode!=-1){
                    cycleNodes[tmpCurrentNode]=true;
                    tmpCurrentNode=parent[tmpCurrentNode];
                }
//                parent[nextNode]=-1;
                continue;
            }
            parent[nextNode]=node;
            visited[nextNode]=true;
            dfsHasCycle(nextNode);
            parent[nextNode]=-1;
            visited[nextNode]=false;
        }
    }

    public static boolean dfsHasCycleRefactor(int node){
//        System.out.println(node);
        int[] adjNodes=graphNodes[node];
        //Phải thêm 1 phần cache lại những nodes đã run into trước đó --> coi như là return được (có cycle or không có cycle luôn)
        path[node]=true;
        boolean isCycle=false;
        if(cycleNodes[node]){
            return true;
        }

        for(int i=0;i<adjNodes.length;i++){
            int nextNode=adjNodes[i];
            if(visited[nextNode]||cycleNodes[nextNode]){
                cycleNodes[nextNode]=true;
                isCycle=true;
                continue;
            }
            //Phải đặt sau đây mới pass --> Vì nếu có cycle thì return bên trên còn không có --> Chạy xuống dưới
            //==> Thực ra vẫn có thể optimize thêm được.
            if(prevPath[nextNode]){
                continue;
            }
            visited[nextNode]=true;
            isCycle=isCycle || dfsHasCycleRefactor(nextNode);
            visited[nextNode]=false;
        }
        if(isCycle){
//            System.out.println(node);
            cycleNodes[node]=true;
            return true;
        }
        return false;
    }

    public static int[][] graphNodes;
    public static boolean[] visited;
    public static boolean[] path;
    public static boolean[] prevPath;
    public static boolean[] cycleNodes;
    public static int[] parent;

    public static List<Integer> eventualSafeNodes(int[][] graph) {
        int n=graph.length;
        graphNodes=graph;
//        System.out.println(n);
        visited=new boolean[n];
        cycleNodes=new boolean[n];
        path=new boolean[n];
        prevPath=new boolean[n];
        List<Integer> result=new ArrayList<>();

        for(int i=0;i<n;i++){
            if(!cycleNodes[i]&&!prevPath[i]){
                visited[i]=true;
                dfsHasCycleRefactor(i);
                prevPath=Arrays.copyOf(path, path.length);
                visited[i]=false;
            }
        }
        for(int i=0;i<n;i++){
            if(!cycleNodes[i]){
                result.add(i);
            }
        }
//        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Count the number of safe nodes in graph
        //- Terminal node:
        //+ A node is a terminal node if there are no outgoing edges ==> Not loop path.
        //
        //- Safe node:
        //+ A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Using union find --> detect.
        //- Constraint:
        //+ 1 <= n <= 10^4
        //+ graph[i] is sorted in a strictly increasing order.
        //
        //- Applying : while(parent[u]!=u).
        //- In 1 circle, we have only one parent.
        //- for --> remove all node having (parent[x]==x)
        //- Use cases:
        //+ Adding new edge: u, v
        //+ Adding edge which creates a circle:
        //  + Ex: 1 --> 2 (2 --> 3 --> 1)
        //  parent[2] = 1 --> new assigning: parent[1]=1
        //WRONG --> Bài này dạng directed graph --> Không thể dùng union find vì các điểm không thể gom lại được
        //VD: 1-->2, 1-->3 --> (1,2,3) không cùng 1 group --> Không dùng được union find
        //VD: 1-->2, 3-->2 --> parent của 2 là điểm nào (1/3) --> Không dùng được union find
        //
        //- Số lượng node là 10^4 cũng khá nhiều --> Ta cần tận dụng tính chất sắp xếp đã cho.
        //
        //- Tư duy khác là có thể dùng bfs ra 1 danh sách nodes --> nếu bfs lần đó :
        //+ Có cycle --> remove all nodes ra khỏi danh sách + add nodes (Không cần xét nữa)
        //+ Không có cycle --> Add nodes (Không cần xét nữa)
        //==> Tư duy này có cases như sau:
        //VD: 1-->2, 1-->3, 3-->4, 4-->1
        //==> path : 1-->3-->4-->1 ==> chỉ có 1,3,4 được remove còn (2) thì vẫn có thể xét tiếp.
        //+ 1 lần xét có thể có nhiều circle path --> không được return luôn.
        //VD: 1-->2-->3-->1, 1-->2-->4-->5-->1
        //
        //- Quay lại bài toán tìm circle path có tính chất sau:
        //+ Node-1 đi ra có cycle --> Tất cả các node connect to node-1 đều có cycle
        //==> Với cách suy nghĩ dạng tìm path cycle ==> Dùng DFS là hợp nhất vì:
        //+ Có thể lưu parent[u]=v được
        //VD: Nếu dùng BFS cùng loop traverse ta không thể lưu được dạng:
        //1-->2-->3-->1, 1-->2-->4-->1 ==> Ta không thể lưu parent của [1] ==> Không biết nó là 3/4.
        //+ Kết thúc đến điểm cuối phát hiện ra cycle thì ta có thể lấy được path dễ dàng + thậm chí không cần dùng dạng parent[u]=v
        //--> Cache list.
        //
//        int[][] graph = {{1,2},{2,3},{5},{0},{5},{},{}};
        int[][] graph = {{1,2,3,4},{1,2},{3,4},{0,4},{}};
//        int[][] graph = {{0},{1,2,3,4},{1,3,4},{2,4},{2}};
//        int[][] graph = {{1,2,3,4},{1,2,3,4},{3,4},{4},{}};
        System.out.println(eventualSafeNodes(graph));
    }
}
