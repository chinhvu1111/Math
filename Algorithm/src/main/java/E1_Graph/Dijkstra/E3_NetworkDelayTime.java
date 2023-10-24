package E1_Graph.Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class E3_NetworkDelayTime {

    public static int networkDelayTime(int[][] times, int n, int k) {
        int[] dis=new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);

        List<int[]>[] adj=new ArrayList[n];

        for(int i=0;i<n;i++){
            adj[i]=new ArrayList<>();
        }
        for(int[] e:times){
            int curVertex=e[0]-1;
            adj[curVertex].add(new int[]{e[1]-1, e[2]});
        }
        PriorityQueue<int[]> queue=new PriorityQueue<>((a,b) -> (a[1]-b[1]));
        k--;
        dis[k]=0;
        queue.add(new int[]{k, 0});
        int rs=0;

        while(!queue.isEmpty()){
            int[] curNode=queue.poll();
            //Next node
            for(int[] node: adj[curNode[0]]){
                int nextDist=curNode[1]+node[1];
                if(nextDist<dis[node[0]]){
                    dis[node[0]]=nextDist;
                    queue.add(new int[]{node[0], nextDist});
                }
            }
        }
        for(int i=0;i<n;i++){
            if(dis[i]==Integer.MAX_VALUE){
                return -1;
            }
            rs=Math.max(rs, dis[i]);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given n nodes from 1 to n
        //- Times as directed graph : times[i] = (u, v, w)
        //  + w time to take to travel from u to v
        //- Given k, trả lại node xa nhất từ k nếu có node nào mà không đến được
        //==> return -1
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= k <= n <= 100
        //1 <= times.length <= 6000
        //times[i].length == 3
        //1 <= ui, vi <= n
        //ui != vi
        //0 <= wi <= 100
        //All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
        //
        //- Brainstorm
        //- Ở đây dùng dijkstra
        //- Vì là directed graph --> Thế nên nếu update lại distance tại node nào thì:
        //+ Ta phải add vào max heap lúc đó.
        //- Mỗi node trong max heap là (current node, distance from k to current node)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(V+E)
        //- Time : O(V*E*log(V))
        //
        //#Reference:
        //2039. The Time When the Network Becomes Idle
        //2045. Second Minimum Time to Reach Destination
    }
}
