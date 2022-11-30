package interviews;

import java.util.*;

public class E212_AllAncestorsOfANodeInADirectedAcyclicGraph {

    public static List<List<Integer>> getAncestors(int n, int[][] edges) {
        int length=edges.length;
        //Node : List of children
        HashMap<Integer, List<Integer>> hashNodes=new HashMap<>();
        //Node : List of parent nodes
        HashMap<Integer, TreeSet<Integer>> hashParents=new HashMap<>();
        int[] degrees=new int[n];

        for(int i=0;i<length;i++){
            int x=edges[i][0];
            int y=edges[i][1];

            if(!hashNodes.containsKey(x)){
                hashNodes.put(x, new ArrayList<>());
            }
            hashNodes.get(x).add(y);
            degrees[y]++;
        }
        Deque<Integer> deque=new LinkedList<>();

        for(int i=0;i<n;i++){
            if(degrees[i]==0){
                deque.add(i);
            }
        }

        while (!deque.isEmpty()){
            Integer currentNode= deque.pop();
            List<Integer> nextNodes=hashNodes.get(currentNode);

            if(nextNodes==null){
                continue;
            }

            for(Integer node: nextNodes){
                TreeSet<Integer> parentNodes;

                if(hashParents.containsKey(node)){
                    parentNodes=hashParents.get(node);
                }else{
                    parentNodes=new TreeSet<>();
                }
                //Add previous node to current hashset
                parentNodes.add(currentNode);
                //Add all parents of parent to list of parent of current node.
                if(hashParents.containsKey(currentNode)){
                    parentNodes.addAll(hashParents.get(currentNode));
                }
                hashParents.put(node, parentNodes);
                degrees[node]--;
                if(degrees[node]==0){
                    deque.add(node);
                }
            }
        }

        List<List<Integer>> results=new ArrayList<>();

        for(int i=0;i<n;i++){
            TreeSet<Integer> currentSet=new TreeSet<>();
            if(hashParents.containsKey(i)){
                currentSet=hashParents.get(i);
            }
            List<Integer> currentList=new ArrayList<>(currentSet);
            results.add(currentList);
        }
        return results;
    }

    public static void main(String[] args) {
        int[][] arr=new int[][]{{0,3},{0,4},{1,3},{2,4},{2,7},{3,5},{3,6},{3,7},{4,6}};
        System.out.println(getAncestors(8, arr));
    }
}
