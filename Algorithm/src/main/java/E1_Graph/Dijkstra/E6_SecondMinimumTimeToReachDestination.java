package E1_Graph.Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class E6_SecondMinimumTimeToReachDestination {

    public static int secondMinimumWrong(int n, int[][] edges, int time, int change) {
        List<List<Integer>> adjNodes=new ArrayList<>();

        for(int i=0;i<n;i++){
            adjNodes.add(new ArrayList<>());
        }
        for(int[] edge:edges){
            edge[0]--;
            edge[1]--;
            adjNodes.get(edge[0]).add(edge[1]);
            adjNodes.get(edge[1]).add(edge[0]);
        }
        int[][] dist=new int[n][2];
        for(int i=0;i<n;i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0]=0;
        PriorityQueue<int[]> queue=new PriorityQueue<>((a, b) -> a[1]-b[1]);
        queue.add(new int[]{0, 0});

        while(!queue.isEmpty()){
            int[] curNode=queue.poll();

            for(int nextNode: adjNodes.get(curNode[0])){
                int nextTime=getNextTime(curNode[1], change)+time;
                if(nextTime<dist[nextNode][0]){
                    dist[nextNode][1]=dist[nextNode][0];
                    dist[nextNode][0]=nextTime;
                    queue.add(new int[]{nextNode, nextTime});
                }else if(nextTime<dist[nextNode][1]){
                    dist[nextNode][1]=nextTime;
                    queue.add(new int[]{nextNode, nextTime});
                }
            }
        }
        return dist[n-1][0];
    }

    public static int secondMinimum(int n, int[][] edges, int time, int change) {
        List<List<Integer>> adjNodes=new ArrayList<>();

        for(int i=0;i<n;i++){
            adjNodes.add(new ArrayList<>());
        }
        for(int[] edge:edges){
            edge[0]--;
            edge[1]--;
            adjNodes.get(edge[0]).add(edge[1]);
            adjNodes.get(edge[1]).add(edge[0]);
        }
        int[][] dist=new int[n][2];
        for(int i=0;i<n;i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0]=0;
        PriorityQueue<int[]> queue=new PriorityQueue<>((a, b) -> a[1]-b[1]);
        queue.add(new int[]{0, 0});

        while(!queue.isEmpty()){
            int[] curNode=queue.poll();

            for(int nextNode: adjNodes.get(curNode[0])){
                int nextTime=getNextTime(curNode[1], change)+time;
                if(nextTime<dist[nextNode][0]){
                    dist[nextNode][1]=dist[nextNode][0];
                    dist[nextNode][0]=nextTime;
                    queue.add(new int[]{nextNode, nextTime});
                }else if(nextTime>dist[nextNode][0]&&nextTime<dist[nextNode][1]){
                    dist[nextNode][1]=nextTime;
                    queue.add(new int[]{nextNode, nextTime});
                }
            }
        }
        return dist[n-1][0];
    }

    public static int getNextTime(int t, int change){
        int times=t/change;
        return times%2==0?t:(times+1)*change;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a graph, time to traverse any edge : (t) time
        //- Each vertex has a traffic signal which changes its colers from (green) to (red) and vice versa every (change) minutes
        //- All signals change at the same time
        //- We can access a vertex any time. We leave a vertex only when signal is green and We (cannot wait) at a vertex if the signal is green.
        //  + Green thì phải leave vertex còn access thì tuỳ ý, leave thì green mới được leave.
        //
        //- Second minimum value is defined as the smallest value strictly larger than the minimum value.
        //For example the second minimum value of [2, 3, 4] is 3, and the second minimum value of [2, 2, 4] is 4.
        //* return the second minimum time it will take to go from (vertex 1 to vertex n).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //2 <= n <= 104
        //n - 1 <= edges.length <= min(2 * 104, n * (n - 1) / 2)
        //edges[i].length == 2
        //1 <= ui, vi <= n
        //ui != vi
        //There are no duplicate edges.
        //Each vertex can be reached directly or indirectly from every other vertex.
        //1 <= time, change <= 103
        //
        //- Brainstorm
        //- Cost từ 1 edge to other node
        //Ex:
        //change=5
        //- Tức là green (time change=5) -> red (time change=5)
        //  + Nó sẽ chuyển từ green --> red mỗi color sẽ keep time change=5
        //- Cost từ 1 node -> neighbor node
        //+ A (T0) A --> B
        //+ Green light: 0,1,2,3,4
        //  + Vì cannot waith when we got green light
        //  + Cost= T0 + time
        //+ Red light : 5,6,7,8,9
        //  + Ta phải chờ cho đến green light
        //  + Cost=10 + time
        //
        //- Giả sử
        //+ green =0
        //+ red = 1
        //==> 0,1,2,3,4 <=> 0
        //==> 5,6,7,8,9 <=> 1
        //time changes = floor(To/change) ==> Chính là chỉ color (green/ red)
        //
        //- Cost:
        //- Color Green / Red : (time changes+1)*change
        //
        //- Chữa lý do tại sao predicate với second array lại cần add vào queue.
        int[][] edges = {{1,2},{1,3},{1,4},{3,4},{4,5}};
        int n = 5;
        int time = 3, change = 5;
        System.out.println(secondMinimumWrong(n, edges, time, change));
        edges = new int[][]{{1,2},{1,3},{1,4},{3,4},{4,5}};
        System.out.println(secondMinimum(n, edges, time, change));
    }
}
