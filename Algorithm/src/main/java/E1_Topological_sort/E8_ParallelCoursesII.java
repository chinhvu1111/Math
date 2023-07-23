package E1_Topological_sort;

import java.util.*;

public class E8_ParallelCoursesII {

    public static int minNumberOfSemesters(int n, int[][] relations, int k) {
        if(n==1){
            return 1;
        }
        int[] inDegree=new int[n+1];
        List<Integer>[] adjNodes=new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            adjNodes[i]=new ArrayList<>();
        }

        for(int[] rel:relations){
            int x=rel[0];
            int y=rel[1];
            adjNodes[x].add(y);
            inDegree[y]++;
        }
        for(int i=1;i<=n;i++){
            System.out.printf("%s %s\n", i, adjNodes[i]);
        }
        Queue<Integer> nodes=new LinkedList<>();
        int numHoldNodes;
        int rs=0;

        for(int i=1;i<=n;i++){
            if(inDegree[i]==0){
                nodes.add(i);
            }
        }
        while (!nodes.isEmpty()){
            numHoldNodes=nodes.size();
            System.out.printf("%s %s\n", nodes, Math.ceil((double) numHoldNodes/k));
            rs+=Math.ceil((double) numHoldNodes/k);
            Queue<Integer> nextNewNodes=new LinkedList<>();

            while (!nodes.isEmpty()){
                int currentNode=nodes.poll();
                for(Integer neighborNode:adjNodes[currentNode]){
                    inDegree[neighborNode]--;
                    if(inDegree[neighborNode]==0){
                        nextNewNodes.add(neighborNode);
                    }
                }
            }
            nodes=nextNewNodes;
        }

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Course : 1 --> n
        //- Giống bài parallel course + thêm điều kiện k
        //+ K là số courses nhiều nhất ta có thể take ở each semester.
        // + Thừa số lượng course lấy sẽ không được chuyển sang semester khác.
        //
        //** Idea
        //* Method-1:
        //1.
        //1.0,
        //- Constraint
        //1 <= n <= 15
        //1 <= k <= n
        //0 <= relations.length <= n * (n-1) / 2
        //relations[i].length == 2
        //1 <= prevCoursei, nextCoursei <= n
        //prevCoursei != nextCoursei
        //All the pairs [prevCoursei, nextCoursei] are unique.
        //
        //- Brainstorm
        //- Ta thấy rằng ta có thể take course theo layer
        //
        //- Special cases:
        //                 2          1
        //                     /    \     \
        //                   4       7     8
        //
        int n = 4;
        int[][] relations = {{2,1},{3,1},{1,4}};
        int k = 1;
        System.out.println(minNumberOfSemesters(n, relations, k));
    }
}
