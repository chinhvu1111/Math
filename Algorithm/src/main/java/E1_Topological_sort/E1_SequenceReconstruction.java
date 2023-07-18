package E1_Topological_sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class E1_SequenceReconstruction {

    public static List<Integer>[] nodes;

    public static boolean findNonDependentVertices(int n, int[] inDegree, int[] nums){
        boolean[] visited=new boolean[n+1];
        Queue<Integer> nodesQueue=new LinkedList<>();

        for(int i=1;i<=n;i++){
            if(inDegree[i]==0){
                nodesQueue.add(i);
                visited[i]=true;
            }
        }
//        System.out.println(nodesQueue);
        while (!nodesQueue.isEmpty()){
            if(nodesQueue.size()>1){
                return false;
            }
            Integer currentNode=nodesQueue.poll();
            for(int u: nodes[currentNode]){
                if(visited[u]){
                    continue;
                }
                inDegree[u]--;
                if(inDegree[u]==0){
                    visited[u]=true;
                    nodesQueue.add(u);
                }
            }
        }
        for(int num: nums){
            if(!visited[num]){
                return false;
            }
        }
        return true;
    }

    public static boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        //Building adjent list
        int n=nums.length;
        nodes=new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            nodes[i]=new ArrayList<>();
        }
        //Đếm số edge đi vào (i)
        int[] inDegree=new int[n+1];

        for(List<Integer> seq:sequences){
            for(int i=0;i<seq.size()-1;i++){
                int x=seq.get(i);
                int y=seq.get(i+1);
                inDegree[y]++;
                nodes[x].add(y);
//                nodes[y].add(x);
            }
        }
        return findNonDependentVertices(n, inDegree, nums);
    }

    public static void main(String[] args) {
        //** Nhận diện bài topological sort:
        //+ Các node depend lẫn nhau
        //+ print kết quả theo thứ tự
        //
        //** Requirement:
        //-
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint:
        //- Brainstorm:
        //
        int[] nums = {1,2,3};
        int[][] sequences = {{1,2},{1,3}};
//        int[][] sequences = {{1,2},{1,3},{2,3}};
        List<List<Integer>> seqList=new ArrayList<>();
        for(int[] conn:sequences){
            List<Integer> currentList=new ArrayList<>();
            for(Integer node:conn){
                currentList.add(node);
            }
            seqList.add(currentList);
        }
        System.out.println(sequenceReconstruction(nums, seqList));
        //#Reference:
        //1. Two Sum
        //2656. Maximum Sum With Exactly K Elements
        //1743. Restore the Array From Adjacent Pairs
        //1562. Find Latest Group of Size M
    }
}
