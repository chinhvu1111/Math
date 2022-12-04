package interviews;

import java.util.*;

public class E212_AllAncestorsOfANodeInADirectedAcyclicGraph_topo {

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

    public static List<List<Integer>> getAncestorsRefactor(int number, int[][] edges) {
        List<List<Integer>> adjList=new ArrayList<>();
        //2, Cái này tương tự như trên
        List<Set<Integer>> parentNodeList=new ArrayList<>();
        int[] degree=new int[number+1];
        int n=edges.length;


        for(int i=0;i<number;i++){
            adjList.add(new ArrayList<>());
            parentNodeList.add(new HashSet<>());
        }

        for(int i=0;i<n;i++){
            int[]edge=edges[i];

            int u=edge[0];
            int v=edge[1];
            degree[v]++;
            adjList.get(u).add(v);
        }

        Deque<Integer> deque=new LinkedList<>();

        for(int i=0;i<number;i++){
            if(degree[i]==0){
                deque.add(i);
            }
        }
        while (!deque.isEmpty()){
            int currentNode=deque.pop();
            List<Integer> nextNodes=adjList.get(currentNode);
//            System.out.printf("Parent node: %s", currentNode);

            for(Integer adjNode: nextNodes){
//                System.out.printf(", adjacent node %s ", adjNode);
                degree[adjNode]--;
                if(degree[adjNode]==0){
                    //Adding to traverse continously
                    deque.add(adjNode);
                }
                for(Integer prerequiParent: parentNodeList.get(currentNode)){
                    parentNodeList.get(adjNode).add(prerequiParent);
                }
                //Ngoài các prerequisite --> Cần phải add cả th current parent node nữa
                parentNodeList.get(adjNode).add(currentNode);
            }
//            System.out.println();
        }
        List<List<Integer>> result=new ArrayList<>();

        for(int i=0;i<number;i++){
            List<Integer> lst=new ArrayList<>(parentNodeList.get(i));
            Collections.sort(lst);
            result.add(lst);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] arr=new int[][]{{0,3},{0,4},{1,3},{2,4},{2,7},{3,5},{3,6},{3,7},{4,6}};
        System.out.println(getAncestors(8, arr));
        System.out.println(getAncestorsRefactor(8, arr));
    }
}
