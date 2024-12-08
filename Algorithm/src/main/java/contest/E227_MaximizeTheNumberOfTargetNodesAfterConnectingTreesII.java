//package contest;
//
//import java.util.HashMap;
//import java.util.HashSet;
//
//public class E227_MaximizeTheNumberOfTargetNodesAfterConnectingTreesII {
//
//    public static int[] getNumTarget(
//            int node, int prevNode, HashMap<Integer,
//            HashSet<Integer>> graph, int k, int depth, int[][] memo1){
//        //          x
//        //            \
//        //             y
//        if(depth==k){
//            return new int[]{0, 1};
//        }
//        HashSet<Integer> adj=graph.get(node);
//        if(adj==null){
//            return new int[]{0, 0};
//        }
//        //         x
//        //      /    \
//        //    y       y1
//        //              \
//        //               y2
//        int[] curRs=new int[]{0 ,0};
//        for (Integer e: adj) {
//            if(e!=prevNode){
//                int[] nextRs = getNumTarget(e, node, graph, k, depth+1, memo1);
//                curRs[0]+=nextRs[0];
//                curRs[1]+=nextRs[1];
//            }
//        }
//        curRs[0]+=1;
//        curRs[1]+=1;
//        return curRs;
//    }
//
//    public static int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
//        int n=edges1.length;
//        int m = edges2.length;
//        HashMap<Integer, HashSet<Integer>> graph1=new HashMap<>();
//        HashMap<Integer, HashSet<Integer>> graph2=new HashMap<>();
//
//        for (int i = 0; i < n; i++) {
//            int x = edges1[i][0];
//            int y = edges1[i][1];
//            graph1.computeIfAbsent(x, z -> new HashSet<>());
//            graph1.computeIfAbsent(y, z -> new HashSet<>());
//            graph1.get(x).add(y);
//            graph1.get(y).add(x);
//        }
//        for (int i = 0; i < m; i++) {
//            int x = edges2[i][0];
//            int y = edges2[i][1];
//            graph2.computeIfAbsent(x, z -> new HashSet<>());
//            graph2.computeIfAbsent(y, z -> new HashSet<>());
//            graph2.get(x).add(y);
//            graph2.get(y).add(x);
//        }
//        int[] numTarget1=new int[n+1];
//        int[][] memo1=new int[n+1][2];
//        for(int i=0;i<=n;i++){
//            numTarget1[i]=getNumTarget(i, -1, graph1, k, 0, memo1);
//        }
//        int[] numTarget2=new int[m+1];
//        int maxVal=Integer.MIN_VALUE;
//        for(int i=0;i<=m;i++){
//            if(k-1>=0){
//                numTarget2[i]=getNumTarget(i, -1, graph2, k-1, 0, memo1);
//            }
//            maxVal=Math.max(numTarget2[i], maxVal);
//        }
//        if(maxVal==Integer.MAX_VALUE){
//            maxVal=0;
//        }
//        int[] rs=new int[n+1];
//        for (int i = 0; i <=n; i++) {
//            rs[i]=numTarget1[i]+maxVal;
//        }
//        return rs;
//    }
//
//    public static void main(String[] args) {
//
//    }
//}
