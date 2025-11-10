package E1_daily;

import java.util.*;

public class E384_PowerGridMaintenance_undone {

    public static HashMap<Integer, TreeSet<Integer>> mapComponent;
    public static int[] findParent(int u, int[] parent, int[] depth){
        int node=u;
        int currentDepth=1;
        while(parent[node]!=node){
            node=parent[node];
            currentDepth++;
        }
        parent[u]=node;
        if(parent[node]!=-1){
            currentDepth=depth[node];
        }
        depth[node] = currentDepth;
        return new int[]{node, currentDepth};
    }

    public static void addEdge(int u, int v, int[] parent, int[] depth){
        int[] parentU=findParent(u, parent, depth);
        int[] parentV=findParent(v, parent, depth);
        if(parentU[0]==parentV[0]){
            return;
        }
        //      A
        //    /
        //   B
        //  /
        // C
        // E
        //  \
        //   F
        //==> Connect short graph to longer graph
        if(parentU[1]<parentV[1]){
            parent[parentV[0]]=parentU[0];
            parent[v]=parentU[0];
            TreeSet<Integer> currentComponentU=mapComponent.getOrDefault(parentU[0], new TreeSet<>());
            TreeSet<Integer> currentComponentV=mapComponent.getOrDefault(parentV[0], new TreeSet<>());
            currentComponentU.add(parentU[0]);
            currentComponentU.addAll(currentComponentV);
            currentComponentU.add(v);
            currentComponentU.add(parentV[0]);
            mapComponent.put(parentU[0], currentComponentU);
        }else{
            parent[parentU[0]]=parentV[0];
            parent[u]=parentV[0];
            TreeSet<Integer> currentComponentV=mapComponent.getOrDefault(parentV[0], new TreeSet<>());
            TreeSet<Integer> currentComponentU=mapComponent.getOrDefault(parentU[0], new TreeSet<>());
            currentComponentV.add(parentV[0]);
            currentComponentV.add(u);
            currentComponentV.add(parentU[0]);
            currentComponentV.addAll(currentComponentU);
            mapComponent.put(parentV[0], currentComponentV);
        }
    }

    public static int[] processQueries(int c, int[][] connections, int[][] queries) {
        int n=connections.length;
        int[] parent=new int[c+1];
        int[] depth=new int[c+1];

        for(int i=1;i<=c;i++){
            parent[i]=i;
        }
        mapComponent=new HashMap<>();
        for(int i=0;i<n;i++){
            addEdge(connections[i][0], connections[i][1], parent, depth);
        }
        int[] state=new int[c+1];
        Arrays.fill(state, 1);
        List<Integer> rsList=new ArrayList<>();

        for(int[] q: queries){
            int[] parentNode = findParent(q[1], parent, depth);
            if(q[0]==1){
                if(state[q[1]]==0){
                    TreeSet<Integer> currentComponent = mapComponent.getOrDefault(parentNode[0], new TreeSet<>());
                    if(currentComponent.isEmpty()){
                        rsList.add(-1);
                    }else{
                        rsList.add(currentComponent.first());
                    }
                    mapComponent.put(parentNode[0], currentComponent);
                }else{
                    rsList.add(q[1]);
                }
            }else{
                //- Set the node to (the offline) mode
                //  + Avoid the cases that marking multiple nodes to offline mode
                //  ==> If we don't calculate the result immediately leading (the wrong result)
                //
                TreeSet<Integer> currentComponent = mapComponent.getOrDefault(parentNode[0], new TreeSet<>());
                currentComponent.remove(q[1]);
//                if(!currentComponent.isEmpty()){
//                    Integer minValue = currentComponent.first();
//                    mapComponent.put(minValue, currentComponent);
//                }
                mapComponent.put(parentNode[0], currentComponent);
                state[q[1]]=0;
            }
        }
        int[] rs=new int[rsList.size()];
        for (int i = 0; i < rsList.size(); i++) {
            rs[i]=rsList.get(i);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer c representing c power stations, each with (a unique identifier id)
        // from 1 to c (1‑based indexing).
        //- These stations are interconnected via (n bidirectional cables), represented by a 2D array connections,
        // where each element connections[i] = [ui, vi] indicates a connection between (station ui and station vi).
        //- Stations that are (directly or indirectly) connected form (a power grid).
        //- Initially, all stations are online (operational).
        //- You are also given a 2D array queries, where each query is one of the following two types:
        //  + [1, x]: A maintenance check is requested for station x.
        //  If station x is online, it resolves the check by itself.
        //  If station x is offline, the check is resolved by the operational station with (the smallest id) in the (same power grid) as x.
        //  If (no operational station) exists in that grid, return -1.
        //  + [2, x]: Station x goes offline (i.e., it becomes non-operational).
        //* Return an array of integers representing the results of each query of type [1, x] in the order they appear.
        //* Note:
        //- (The power grid) preserves its structure; an offline (non‑operational) node
        // remains part of its grid and taking it offline does not alter connectivity.
        //
        //Input: c = 5, connections = [[1,2],[2,3],[3,4],[4,5]], queries = [[1,3],[2,1],[1,1],[2,2],[1,2]]
        //
        //Output: [3,2,3]
        //
        //Explanation:
        //
        //Initially, all stations {1, 2, 3, 4, 5} are online and form a single power grid.
        //Query [1,3]: Station 3 is online, so the maintenance check is resolved by station 3.
        //Query [2,1]: Station 1 goes offline. The remaining online stations are {2, 3, 4, 5}.
        //Query [1,1]: Station 1 is offline, so the check is resolved by the operational station
        // with the smallest id among {2, 3, 4, 5}, which is station 2.
        //Query [2,2]: Station 2 goes offline. The remaining online stations are {3, 4, 5}.
        //Query [1,2]: Station 2 is offline, so the check is resolved by the operational station
        // with the smallest id among {3, 4, 5}, which is station 3.
        //
        //Explanation:
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= c <= 10^5
        //0 <= n == connections.length <= min(10^5, c * (c - 1) / 2)
        //connections[i].length == 2
        //1 <= ui, vi <= c
        //ui != vi
        //1 <= queries.length <= 2 * 10^5
        //queries[i].length == 2
        //queries[i][0] is either 1 or 2.
        //1 <= queries[i][1] <= c
        //  + 1 <= queries.length <= 2 * 10^5 ==> Time: O(n)/O(n*k)
        //  + 0 <= n == connections.length <= min(10^5, c * (c - 1) / 2) ==> O(n)
        //
        //* Brainstorm:
        //- Union find + Priority queue
        //
        //1.1, Case
//        int c = 5;
//        int[][] connections = {{1,2},{2,3},{3,4},{4,5}}, queries = {{1,3},{2,1},{1,1},{2,2},{1,2}};
        int c = 2;
        int[][] connections = {{1,2}}, queries = {{1,2},{2,1},{1,2},{1,1}};
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time:
        //- Space: O(1)
        //
        int[] rs = processQueries(c, connections, queries);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
    }
}
