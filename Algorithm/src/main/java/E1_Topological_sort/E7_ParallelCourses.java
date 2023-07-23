package E1_Topological_sort;

import java.util.*;

public class E7_ParallelCourses {

    public static int minimumSemesters(int n, int[][] relations) {
        if(n==1){
            return 1;
        }
        int[] inDegree=new int[n+1];
        List<Integer>[] adjNodes=new LinkedList[n+1];

        for(int[] rel: relations){
            int x=rel[0];
            int y=rel[1];
            inDegree[y]++;
            if(adjNodes[x]==null){
                adjNodes[x]=new LinkedList<>();
            }
            adjNodes[x].add(y);
        }
        Queue<Integer> nodes=new LinkedList<>();
        boolean[] visited=new boolean[n+1];

        for(int i=1;i<=n;i++){
            if(inDegree[i]==0){
                nodes.add(i);
            }
        }
        if(nodes.size()==0){
            return -1;
        }
//        System.out.println(nodes);
        int numSemesters=0;

        while (!nodes.isEmpty()){
            System.out.println(nodes);
            Queue<Integer> nextNewNodes=new LinkedList<>();
            numSemesters++;
            //- Nếu viết kiểu ntn dạng add dần dần mà không theo layer thì ta sẽ bị case là cùng 1 layer nhưng có xuất hiện cycle.
//            while (!nodes.isEmpty()){
//                int currentNode=nodes.poll();
//                visited[currentNode]=true;
//                if(adjNodes[currentNode]==null){
//                    continue;
//                }
//                for(Integer neighborNode: adjNodes[currentNode]){
//                    if(visited[neighborNode]){
//                        return -1;
//                    }
//                    inDegree[neighborNode]--;
//                    if(inDegree[neighborNode]==0){
//                        nextNewNodes.add(neighborNode);
//                    }
//                }
//            }
            List<Integer> currentLeavesNode=new ArrayList<>(nodes);
            for(Integer leaf: currentLeavesNode){
                if(!visited[leaf]){
                    visited[leaf]=true;
                }else{
                    return -1;
                }
            }
            while (!nodes.isEmpty()){
                int currentNode=nodes.poll();
                if(adjNodes[currentNode]==null){
                    continue;
                }
                for(Integer neighborNode: adjNodes[currentNode]){
                    if(visited[neighborNode]){
                        return -1;
                    }
                    inDegree[neighborNode]--;
                    if(inDegree[neighborNode]==0){
                        nextNewNodes.add(neighborNode);
                    }
                }
            }
            nodes=nextNewNodes;
        }
        for(int i=1;i<=n;i++){
            if(!visited[i]){
                return -1;
            }
        }
        return numSemesters;
    }

    public static int findMaxDepth(int node,boolean[] visited, int[] maxDepth, List<Integer>[] adjNodes){
        if(visited[node]){
            return -1;
        }
        if(maxDepth[node]!=0){
            return maxDepth[node];
        }
        if(adjNodes[node]==null){
            return 1;
        }
        visited[node]=true;
        int currentMaxDepth=0;

        for(Integer neighborNode:adjNodes[node]){
            if(visited[neighborNode]){
                return -1;
            }
            int tempMax=findMaxDepth(neighborNode, visited, maxDepth, adjNodes);
            if(tempMax==-1){
                return -1;
            }
            currentMaxDepth=Math.max(currentMaxDepth, tempMax+1);
        }
        visited[node]=false;
        return maxDepth[node]=currentMaxDepth;
    }

    public static int minimumSemestersMethod2WithOptimization(int n, int[][] relations) {
        if(n==1){
            return 1;
        }
        int[] inDegree=new int[n+1];
        List<Integer>[] adjNodes=new LinkedList[n+1];

        for(int[] rel: relations){
            int x=rel[0];
            int y=rel[1];
            inDegree[y]++;
            if(adjNodes[x]==null){
                adjNodes[x]=new LinkedList<>();
            }
            adjNodes[x].add(y);
        }
        int[] maxDepth=new int[n+1];
        boolean[] visited=new boolean[n+1];
        int rs=-1;

        for(int i=1;i<=n;i++){
            if(inDegree[i]==0){
                int currentLength=findMaxDepth(i, visited, maxDepth, adjNodes);

                if(currentLength==-1){
                    return -1;
                }
                rs=Math.max(rs, currentLength);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Giống bài course schedule
        //- Mỗi layer là 1 semester
        //* Return số lượng min semester cần to take all courses.
        //- If we don't have any solution : return -1
        //
        //** Idea
        //* Method-1: Tological sort
        //1.
        //1.0,
        //- Constraint
        //1 <= n <= 5000
        //1 <= relations.length <= 5000
        //relations[i].length == 2
        //1 <= prevCoursei, nextCoursei <= n
        //prevCoursei != nextCoursei
        //All the pairs [prevCoursei, nextCoursei] are unique.
        //
        //- Brainstorm:
        //
        //VD:
        //          1        2
        //           \      /
        //            3    5    7
        //             \  /    /
        //              6
        //- Return 3/ 3 layers
//        int n = 3;
//        int[][] relations = {{1,3},{2,3}};
        //
        //1.1, Optimization:
        //- We remove some redundant codes.
        //
        //1.2,
        //
        //* Method-2:
        //2.
        //2.0, Idea
        //- Check cycle : If the cycle exists, return -1
        //- Finding the longest path:
        //+ The number of course we will take along this path which will be the number of semesters.
        //
        //- Checking whether this graph has a cycle or not:
        //  + Checking all nodes
        //- Finding max depth of the graph:
        //  + Finding the max depth of each node in the graph
        //  + Vì ta đang sử dụng directed graph --> Depth của mỗi node có thể cache lại để reused.
        //
        //2.1, Optimization:
        //- Ta có thể tối ưu bằng cách kết hợp việc tìm cycle + việc tìm max depth luôn.
        //- Ta vẫn sẽ xét mọi leaf nodes.
        //  + Nếu ta tìm được length=-1 ==> Tức là có cycle
        //
        //* Có 2 cách để tìm result:
        //- Dùng global variable
        //- Dùng return result+1 + [ if(result==-1) return -1 ]
        int n = 3;
        int[][] relations = {{1,2},{2,3},{3,1}};
        System.out.println(minimumSemesters(n, relations));
        System.out.println(minimumSemestersMethod2WithOptimization(n, relations));
        //#Reference:
        //1494. Parallel Courses II
        //2050. Parallel Courses III
    }
}
