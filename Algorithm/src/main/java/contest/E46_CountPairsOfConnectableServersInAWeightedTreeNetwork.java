package contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class E46_CountPairsOfConnectableServersInAWeightedTreeNetwork {

    public static void solution(int root, int node, HashMap<Integer, List<Integer>> adjNodes,
                                boolean[] visited, HashSet<Integer> validNodes,
                                int dist, int signalSpeed, int[][] w){
        List<Integer> adj=adjNodes.get(node);
        if (adj!=null) {
            for(int nextE: adj){
                if(!visited[nextE]){
                    if(nextE!=root&&(dist+w[node][nextE])%signalSpeed==0){
                        validNodes.add(nextE);
                    }
                    visited[nextE]=true;
                    solution(root, nextE, adjNodes, visited, validNodes, dist+w[node][nextE], signalSpeed, w);
                    visited[nextE]=false;
                }
            }
        }
    }

    public static int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        //Adj nodes
        //<node, list<node>>
        HashMap<Integer, List<Integer>> adjNodes=new HashMap<>();
        int n=edges.length;
        int[][] weight=new int[1001][1001];
        int point=0;

        for(int[] edge: edges){
            int x=edge[0];
            int y=edge[1];
            int w=edge[2];
            point=Math.max(Math.max(x, y), point);
            weight[x][y]=w;
            weight[y][x]=w;
            List<Integer> adj=adjNodes.get(x);
            if(adj==null){
                adj=new ArrayList<>();
            }
            adj.add(y);
            adjNodes.put(x, adj);

            adj=adjNodes.get(y);
            if(adj==null){
                adj=new ArrayList<>();
            }
            adj.add(x);
            adjNodes.put(y, adj);
        }
        int[] rs=new int[point+1];
        for(int node:adjNodes.keySet()){
            boolean[] visited=new boolean[point+1];
            List<Integer> adj=adjNodes.get(node);
            visited[node]=true;
            int curRs=0;
            int count=0;
            List<Integer> countRs=new ArrayList<>();

            for(int nextE: adj){
                HashSet<Integer> curValidNodes=new HashSet<>();
                if(!visited[nextE]){
                    if(nextE!=node&&(weight[node][nextE])%signalSpeed==0){
                        curValidNodes.add(nextE);
                    }
                    visited[nextE]=true;
                    solution(node, nextE, adjNodes, visited, curValidNodes, weight[node][nextE], signalSpeed, weight);
                    visited[nextE]=false;
                }
                if(curValidNodes.size()!=0){
                    countRs.add(curValidNodes.size());
                }
                count++;
//                System.out.printf("curNode: %s, list:\n%s\n", node, curValidNodes);
            }
            if(count>=2){
                for(int i=0;i<countRs.size();i++){
                    for(int j=i+1;j<countRs.size();j++){
                        curRs+=countRs.get(i)*countRs.get(j);
                    }
                }
                rs[node]=curRs;
            }
            visited[node]=false;
        }
        for(int i: rs){
            System.out.printf("%s,", i);
        }
        System.out.println();
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //You are given an unrooted weighted tree with n vertices representing servers numbered from 0 to n - 1,
        // an array edges where edges[i] = [ai, bi, weighti] represents a bidirectional edge between vertices ai and bi
        // of weight weighti. You are also given an integer signalSpeed.
        //Two servers a and b are connectable through a server c if:
        //a < b, a != c and b != c.
        //- The distance from c to a is divisible by signalSpeed.
        //- The distance from c to b is divisible by signalSpeed.
        //- The path from c to b and the path from c to a do not share any edges.
        //* Return an integer array (count of length n) where count[i] is the (number of server pairs) that are connectable
        // through the server (i).
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //2 <= n <= 1000
        //edges.length == n - 1
        //edges[i].length == 3
        //0 <= ai, bi < n
        //edges[i] = [ai, bi, weighti]
        //1 <= weighti <= 10^6
        //1 <= signalSpeed <= 10^6
        //The input is generated such that edges represents a valid tree.
        //
        //- Brainstorm
        //- Sum left ==> Modulo cho signalSpeed được
        //- Ta có thể cache lại sum left/ right
        //- Bài toán trở thành:
        //- Với mỗi node-c, ta tìm:`
        //  + Left/ right số lượng (node!= node-c)
        //  + sum -> node đó disible by signalSpeed
        //  + Left và right không share edges ==> Visited[][]
        //- Sửa lại 1 chút:
        //  + All nodes connected với node-c chữ không phải mỗi left và right.
        //- a-c và c-b không share edges có nghĩa là gì?
        //  + Tức là khi traverse từ a -> ... -> c --> ... ==> Không qua b
        //Ex:
        // 1 -> 2 -> 3
        //
        //- Ghép các pair ntn?
        //+ Ta sẽ get list các nodes theo từng nhánh
        //  ==> Sau đó ghép với nhau
        //+ Visited luôn giữ nguyên + 2 directions
        //
        //- Nếu có cycle thì sao?
        //+ Không sao hết
        //
        //- a<b
        //  + Nếu xét 2 chiều thì <=> a!=b
        //  ==> Nhưng mà chỉ xét 1 lần từ a/b
        //
//        int[][] edges = {{0,1,1},{1,2,5},{2,3,13},{3,4,9},{4,5,2}};
//        int signalSpeed = 1;
//        int[][] edges = {{0,6,3},{6,5,3},{0,3,1},{3,2,7},{3,1,6},{3,4,2}};
//        int signalSpeed = 3;
//        int[][] edges = {{0,6,3}};
//        int signalSpeed = 3;
//        int[][] edges = {{1,0,2},{2,1,4},{3,2,4},{4,0,3},{5,1,4},{6,2,2},{7,6,4},{8,1,2},{9,8,3}};
//        int signalSpeed = 1;
        //   8 - 9
        //    \
        // 5 - 1 - 0 - 4
        //     \
        //  6 - 2 - 3
        //   \
        //    7`
        int[][] edges = {{0,6,3},{6,5,3},{0,3,1},{3,2,7},{3,1,6},{3,4,2}};
        int signalSpeed = 3;
        //  0 - 6 - 5
        //   \
        //4 - 3 - 2
        //    \
        //  `   1
        //
        //
        countPairsOfConnectableServers(edges, signalSpeed);
    }
}
