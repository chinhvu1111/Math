package E1_Graph.Dijkstra;

import javafx.util.Pair;

import java.util.*;

public class E8_FindEdgesInShortestPaths_duplicated {

    public static void dfs(int curNode, HashMap<Integer, HashSet<Integer>> nodesInPath, HashSet<Pair<Integer, Integer>> existEdges){
        HashSet<Integer> adjNodes= nodesInPath.get(curNode);

        if(adjNodes==null){
            return;
        }
        for(Integer prevNode: adjNodes){
            Pair<Integer, Integer> curEdge = new Pair<>(curNode, prevNode);
//            Pair<Integer, Integer> curEdge1 = new Pair<>(prevNode, curNode);
            if(!existEdges.contains(curEdge)){
                existEdges.add(curEdge);
//                existEdges.add(curEdge1);
                dfs(prevNode, nodesInPath, existEdges);
            }
        }
    }

    public static boolean[] findAnswer(int n, int[][] edges) {
        //Space:
        // 0 -> (0,1),(0,2)...
        //-> O(V+E)
        HashMap<Integer, List<Pair<Integer, Integer>>> graph=new HashMap<>();

        //Time: O(E)
        for(int[] e: edges){
            graph.computeIfAbsent(e[0], k -> new ArrayList<>()).add(new Pair<>(e[1], e[2]));
            graph.computeIfAbsent(e[1], k -> new ArrayList<>()).add(new Pair<>(e[0], e[2]));
        }
//        System.out.println(graph);
        //Time : O(V) (V vertices)
        int[] minWeight=new int[n];
        Arrays.fill(minWeight, Integer.MAX_VALUE);
        //Space: O(E)
        //Time: O(E*log(E))
        PriorityQueue<Pair<Integer, Integer>> minEdges=new PriorityQueue<>(Comparator.comparingInt(Pair::getValue));
        //src, weight
        minEdges.add(new Pair<>(0, 0));
        minWeight[0]=0;
        //Dùng Set để tránh giữa 2 node có 2 edges cùng weight
        HashMap<Integer, HashSet<Integer>> nodesInPath=new HashMap<>();
//        boolean[] visited=new boolean[n];
//        visited[0]=true;

        while(!minEdges.isEmpty()){
            Pair<Integer, Integer> curEdge= minEdges.poll();
//            visited[curEdge.getKey()]=true;
            //min weight, current node
            List<Pair<Integer, Integer>> nextNodes=graph.get(curEdge.getKey());
            if(nextNodes==null){
                continue;
            }
            for(Pair<Integer, Integer> nextNode: nextNodes){
                int curWeight=curEdge.getValue();

                if(curWeight+nextNode.getValue()<=minWeight[nextNode.getKey()]){
                    if(curWeight+nextNode.getValue()<minWeight[nextNode.getKey()]){
                        minWeight[nextNode.getKey()]=curWeight+nextNode.getValue();
                        minEdges.add(new Pair<>(nextNode.getKey(), curWeight+nextNode.getValue()));
                        //Ta cần remove tránh add cả mấy nodes của edges không min vào
                        nodesInPath.remove(nextNode.getKey());
                    }
//                    System.out.printf("CurNode: %s, NextNode: %s, cur weight: %s\n", curEdge.getKey(), nextNode.getKey(), curWeight+nextNode.getValue());
                    nodesInPath.computeIfAbsent(nextNode.getKey(), k -> new HashSet<>()).add(curEdge.getKey());
                }
            }
        }
        HashSet<Pair<Integer, Integer>> existEdges=new HashSet<>();
        //Time: O(E+V)
        //- Worst case:
        //  + All edges included in the shortest path
        //  + All nodes included in the shortest path
        dfs(n-1, nodesInPath, existEdges);
//        System.out.println(nodesInPath);
//        System.out.println(existEdges);

        boolean[] rs=new boolean[edges.length];
        for(int i=0;i<edges.length;i++){
            if(existEdges.contains(new Pair<>(edges[i][0],edges[i][1]))|| existEdges.contains(new Pair<>(edges[i][1],edges[i][0]))){
                rs[i]=true;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //You are given an undirected weighted graph of n nodes numbered from 0 to n - 1.
        //- The graph consists of m edges represented by a 2D array edges, where edges[i] = [ai, bi, wi]
        // indicates that there is an edge between (nodes ai and bi with weight wi).
        //- Consider all the shortest paths from node 0 to node n - 1 in the graph.
        //- You need to find a boolean array answer where answer[i] is true if the edge edges[i] is part of (at least one shortest path).
        // Otherwise, answer[i] is false.
        //* Return the array answer.
        //* Xác định xem edge[i] có thuộc shortest path từ (0 --> n-1) không.
        //
        //- Note that the graph may not be connected.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= n <= 5 * 10^4
        //m == edges.length
        //1 <= m <= min(5 * 10^4, n * (n - 1) / 2)
        //0 <= ai, bi < n
        //ai != bi
        //1 <= wi <= 10^5
        //There are no repeated edges.
        //==> Có khá nhiều nodes
        //- Weight >=0 + weight được cộng dồn.
        //==> Ta có thể làm traditional dijiktra mà:
        //  + Không cần xét lại node cũ
        //
        //- Brainstorm
        //- Có thể có nhiều shortest path
        //- Questions:
        //  + Có nên tìm all shortest paths hay không?
        //  --> Có
        //- Why?
        //Ex:
        // 0 -- 1 -- 2
        //  \      /
        //   3 -- 4
        //+ Giả sử
        // + 0 - 1 - 2 = 5
        // + 0 - 3 - 4 - 2 = 5
        //==> Nếu tìm 1 path thì sẽ không thể xét được đủ
        //
        //- Tìm shortest path ntn?
        //  + --> Cần tìm cả 2 paths.
        //Ex:
        // 0  ---(1)---  1
        // |             |(10) \(11)
        // 2 --- (2)---  3 -(1)- 5
        //- Khi đến 0 -> 1 thì không phải là edges trong shortest path từ (0 --> n-1)
        //  + Vì nó qua điểm 3,5 với weight rất lớn
        //- Không phải add all edges khi traverse là được.
        //
        //- Vì ta có mốc là src=0
        //  + Mỗi lần traverse extend: Ta cần + dồn lên giá trị.
        //
        //- Shortest path
        //  + dijikstra
        //- Nếu 1 điểm mà có thể có nhiều path đến từ 0 + (return weight bằng nhau) thì sao
        //- Nếu ta dùng parent node[i] = List
        //  + Ta chọn add element ngay khi:
        //   + weight[i] > currentWeigh + nextWeight là sai
        //+ Vì:
        //       0 ---- 1
        // (1)/     \(4)
        //   2 -(2)- 3
        //  ==> 3 được đến trước ==> Add cả edges (0,3) vào --> Sai.
        //
        //1.1, Optimization:
        //- Bài này không cần visited vì:
        //  + Update weight (From MAX to new value) nó đã thể hiện việc đi qua rồi.
        //
        //* Reverse traverse:
        //- Để tìm được shortest path:
        //  + Ta có thể tiếp tục tìm shortest path từ (destination node)
        //Ex:
        // 0 -- 1 -- 5
        //+ Traverse: 5 -> 1
        //dist[nextVal=1]+curWeight[nextVal=1] == dist[5]
        //  + rs[index of (1,5)] = true.
        //1.2, Complexity
        //- Space : O(V+E)
        //- Time : O(V+E+E*log(E))
        //  + E max nhất = V*V
        //  => Time = O(V+E*log(V))
        //
//        int[][] edges = {{0,1,4},{0,2,1},{1,3,2},{1,4,3},{1,5,1},{2,3,1},{3,5,3},{4,5,2}};
//        int n=6;
//        int[][] edges = {{2,0,1},{0,1,1},{0,3,4},{3,2,2}};
//        int n=4;
        //#Reference:
        //2810. Faulty Keyboard
        //1984. Minimum Difference Between Highest and Lowest of K Scores
        //2076. Process Restricted Friend Requests
        //2109. Adding Spaces to a String
        //1891. Cutting Ribbons
        //2848. Points That Intersect With Cars
        int[][] edges = {{2,1,6}};
        int n=3;
        findAnswer(n, edges);
    }
}
