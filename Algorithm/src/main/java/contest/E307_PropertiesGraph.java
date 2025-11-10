package contest;

import java.util.HashSet;

public class E307_PropertiesGraph {

    public static int[] findParent(int[] parent, int node){
//        int prevNode=node;
        int depth=1;
        int initNode=node;

        while(parent[node]!=node){
//            prevNode=node;
            node=parent[node];
            depth++;
        }
        parent[initNode]=node;
        return new int[]{node, depth};
    }

    public static int addEdge(int u, int v, int[] parent){
        int[] parentU=findParent(parent, u);
        int[] parentV=findParent(parent, v);
//        System.out.printf("u: %s, parentu: %s\n", u, parentU[0]);
//        System.out.printf("v: %s, parentV: %s\n", v, parentV[0]);

        //1 -> 2
        //2 -> 3
        //==> parent(1) != parent(3) ==> Nhưng findParent(1)==findParent(3)
        //==>
        if(parentU[0]!=parentV[0]){
            if(parentU[1]>parentV[1]){
                parent[parentV[0]]=parentU[0];
            }else{
                parent[parentU[0]]=parentV[0];
            }
            return 0;
        }
        return 1;
    }

    public static int numberOfComponents(int[][] properties, int k) {
        int n=properties.length;
        int[] parent=new int[n];

        for (int i = 0; i < n; i++) {
            parent[i]=i;
        }

        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                HashSet<Integer> distinctNum=new HashSet<>();
                HashSet<Integer> distinctNum1=new HashSet<>();
                int count=0;
                for (int l = 0; l < properties[i].length; l++) {
                    distinctNum.add(properties[i][l]);
                }
                for (int l = 0; l < properties[j].length; l++) {
                    distinctNum1.add(properties[j][l]);
                }
                for(int e: distinctNum){
                    if(distinctNum1.contains(e)){
                        count++;
                    }
                }
                if(count>=k){
//                    System.out.printf("i:%s, j:%s, num: %s\n", i, j, count);
                    addEdge(i, j, parent);
                }
            }
        }
        HashSet<Integer> groups=new HashSet<>();
        for(int i=0;i<n;i++){
            int currentParent=findParent(parent, i)[0];
            groups.add(currentParent);
//            System.out.printf("%s\n", currentParent);
        }
        return groups.size();
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 2D integer array properties having dimensions n x m and an integer k.
        //- Define a function intersect(a, b) that
        //* returns (the number of ("distinct integers common")) to (both arrays a and b).
        //- Construct (an undirected graph) where (each index i) corresponds to properties[i].
        // There is an edge between (node i and node j) if and only if intersect(properties[i], properties[j]) >= k,
        // where i and j are in the range [0, n - 1] and (i != j).
        //* Return (the number of connected components) in the resulting graph.
        //
        //Example 1:
        //
        //Input: properties = [[1,2],[1,1],[3,4],[4,5],[5,6],[7,7]], k = 1
        //Output: 3
        //
        //Explanation:
        //
        //The graph formed has 3 connected components:
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= n == properties.length <= 100
        //1 <= m == properties[i].length <= 100
        //1 <= properties[i][j] <= 100
        //1 <= k <= m
        //  + Time: O(n^3)
        //
        //- Brainstorm
        //
//        int[][] properties = {{1,2},{1,1},{3,4},{4,5},{5,6},{7,7}};
//        int k = 1;
        int[][]  properties = {{1,2,3},{2,3,4},{4,3,5}};
        int k = 2;
        System.out.println(numberOfComponents(properties, k));
    }
}
