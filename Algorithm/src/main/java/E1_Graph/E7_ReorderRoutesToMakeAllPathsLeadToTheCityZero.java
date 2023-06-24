package E1_Graph;

import java.util.*;

public class E7_ReorderRoutesToMakeAllPathsLeadToTheCityZero {

    public static int solution(int n, List<Integer>[] adjNodes, HashSet<String> connectionMap){
        Deque<Integer> listNodes=new LinkedList<>();
        listNodes.add(0);
        int numberReversePath=0;
        boolean[] visited =new boolean[n];
//        System.out.println(connectionMap);

        while (!listNodes.isEmpty()){
            Integer currentNode=listNodes.poll();
            List<Integer> currentAdjNode=adjNodes[currentNode];
            visited[currentNode]=true;

            if(currentAdjNode!=null){
                for(Integer node: currentAdjNode){
                    if(visited[node]){
                       continue;
                    }
//                    System.out.printf("%s %s %s\n", currentNode, node, connectionMap.contains(currentNode+"_"+node));
                    visited[node]=true;
                    if(!connectionMap.contains(currentNode+"_"+node)){
                        numberReversePath++;
                    }
                    listNodes.add(node);
                }
            }
        }
        return numberReversePath;
    }

    public static int minReorder1(int n, int[][] connections) {
        List<Integer>[] adjNodes=new ArrayList[n];
        HashSet<String> connectionMap=new HashSet<>();

        for(int i=0;i<connections.length;i++){
            int[] currentEdge=connections[i];
            int v=currentEdge[0];
            int u=currentEdge[1];
            if(adjNodes[u]==null){
                adjNodes[u]=new ArrayList<>();
            }
            if(adjNodes[v]==null){
                adjNodes[v]=new ArrayList<>();
            }
            adjNodes[u].add(v);
            adjNodes[v].add(u);
            connectionMap.add(u+"_"+v);
//            System.out.printf("%s %s\n", u, v);
        }
        return solution(n, adjNodes, connectionMap);
    }

    public static int minReorder(int n, int[][] connections) {
        LinkedList<int[]>[] graph = new LinkedList[n];
        for(int i=0;i<n;i++){
            graph[i]=new LinkedList<>();
        }
        for(int[] c: connections){
            graph[c[0]].add(new int[]{c[1], 1});
            graph[c[1]].add(new int[]{c[0], 0});
        }
        boolean[] visited=new boolean[n];
        int reorderRoads = 0;
        Queue<Integer> nodes=new LinkedList<>();
        nodes.add(0);

        while (!nodes.isEmpty()){
            int currentNode=nodes.poll();
            if(visited[currentNode]){
                continue;
            }
            visited[currentNode]=true;
            for(int[] adj:graph[currentNode]){
                if(!visited[adj[0]]){
                    nodes.add(adj[0]);
                    if(adj[1]==1){
                        reorderRoads++;
                    }
                }
            }
        }
        return reorderRoads;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Reorient lại các edge làm sao để mỗi city có thể đến city (0) ( Do city 0 đang tổ chức sự kiện lớn)
        //
        //** Idea
        //1.
        //1.0,
        //- constraint:
        //+ 2 <= n <= 5 * 10^4
        //+ Chỉ có 1 path (1 chiều) giữa 2 cities bất kỳ.
        //
        //- Vì các cities trỏ về cùng 1 root --> có nghĩa rằng là ta đi từ root --> có thể traverse hết các nodes trong tree đó.
        //==> Với đường đi là mũi tên ngược lại --> nếu trên đường đi có mũi tên nào ngược thì ta đổi lại
        //+ Vì mỗi edge ta chỉ có 1 path --> nên đổi như thế này sẽ cho kết quả đúng.
        //---> Ý tưởng là chuyển về đồ thị vô hướng (undirected graph) --> Sau đó mark dần dần.
        //#Reference:
        //797. All Paths From Source to Target
        //297. Serialize and Deserialize Binary Tree
        //733. Flood Fill
        //2445. Number of Nodes With Value One
        int n=6;
        int[][] connections = {{0,1},{1,3},{2,3},{4,0},{4,5}};
//        int n = 5;
//        int[][] connections = {{1,0},{1,2},{3,2},{3,4}};
//        int n = 5;
//        int[][] connections = {{4,3},{2,3},{1,2},{1,0}};
        // 4 --> 3
        // 1 --> 2 --> 3
        // 1 --> 0
        System.out.println(minReorder(n, connections));
        System.out.println(minReorder1(n, connections));
    }
}
