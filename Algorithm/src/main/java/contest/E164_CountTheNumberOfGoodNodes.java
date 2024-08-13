package contest;

import java.util.*;

public class E164_CountTheNumberOfGoodNodes {

    public static int solution(int node, int prevNode, HashMap<Integer, List<Integer>> tree, boolean[] goodNodes){
        List<Integer> adj=tree.get(node);
        int numNode=0;
        HashSet<Integer> setSize=new HashSet<>();

        if(adj==null||adj.isEmpty()){
            goodNodes[node]=true;
        }else {
            for(int nextNode: adj){
                if(prevNode==nextNode){
                    continue;
                }
                int curNumNode = solution(nextNode, node, tree, goodNodes);
                setSize.add(curNumNode);
                numNode+=curNumNode;
            }
        }
        if(setSize.size()==1||setSize.isEmpty()){
            goodNodes[node]=true;
        }
        return numNode+1;
    }

    public static int countGoodNodes(int[][] edges) {
        HashMap<Integer, List<Integer>> tree=new HashMap<>();
        HashMap<Integer, Integer> inDegree=new HashMap<>();
        HashSet<Integer> setNode=new HashSet<>();
        int size=0;

        for(int[] e: edges){
            inDegree.put(e[1], inDegree.getOrDefault(e[0], 0)+1);
            List<Integer> adj=tree.getOrDefault(e[0], new ArrayList<>());
            adj.add(e[1]);
            tree.put(e[0], adj);
            adj=tree.getOrDefault(e[1], new ArrayList<>());
            adj.add(e[0]);
            tree.put(e[1], adj);
            size=Math.max(size, Math.max(e[0], e[1]));
            setNode.add(e[0]);
            setNode.add(e[1]);
        }
        boolean[] goodNodes=new boolean[size+1];
        int root=0;
        solution(root, -1, tree, goodNodes);
        int rs=0;

        for(int i=0;i<goodNodes.length;i++){
            if(goodNodes[i]){
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- There is an undirected tree with n nodes labeled from 0 to n - 1, and rooted at node 0. You are given a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
        //- A node is good if all the subtrees rooted at its children have the same size.
        //Return the number of good nodes in the given tree.
        //A subtree of treeName is a tree consisting of a node in treeName and all of its descendants.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //** Brainstorm
        //
        //
//        int[][] edges = {{0,1},{1,2},{2,3},{3,4},{0,5},{1,6},{2,7},{3,8}};
//        int[][] edges = {{0,1},{1,2},{1,3},{1,4},{0,5},{5,6},{6,7},{7,8},{0,9},{9,10},{9,12},{10,11}};
//        int[][] edges = {{0,1}};
        int[][] edges = {{0,19}};
//        int[][] edges = {{6,0},{1,0},{5,1},{2,5},{3,1},{4,3}};
        //4
        //6
        //    4     2
        //  /     /
        //3     5
        //  \ /
        //  1     6
        //   \  /
        //    0
        System.out.println(countGoodNodes(edges));
    }
}
