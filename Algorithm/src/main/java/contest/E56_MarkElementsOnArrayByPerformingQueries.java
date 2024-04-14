package contest;

import javafx.util.Pair;

import java.util.Objects;
import java.util.TreeSet;

public class E56_MarkElementsOnArrayByPerformingQueries {

    public static long[] unmarkedSumArray(int[] nums, int[][] queries) {
        TreeSet<Pair<Integer, Integer>> numSet=new TreeSet<>((a, b) ->{
            if(!Objects.equals(a.getKey(), b.getKey())){
                return a.getKey()-b.getKey();
            }
            return a.getValue()-b.getValue();
        });
        int n=nums.length;
        long sum=0;

        for(int i=0;i<n;i++){
            numSet.add(new Pair<>(nums[i], i));
            sum+= nums[i];
        }
//        System.out.println(numSet);
        boolean[] visited=new boolean[n];
        long[] ans=new long[queries.length];
        int index=0;

        for(int[] query: queries){
            if(!visited[query[0]]){
                numSet.remove(new Pair<>(nums[query[0]], query[0]));
                sum-=nums[query[0]];
            }
//            System.out.printf("First: %s, sum: %s, %s\n", index, sum, numSet);
            visited[query[0]]=true;
            int count=query[1];

            while (count!=0&&!numSet.isEmpty()){
                Pair<Integer, Integer> curElement = numSet.pollFirst();
                if(query[0]!=curElement.getValue()){
                    sum-=curElement.getKey();
                }
                visited[curElement.getValue()]=true;
                count--;
            }
//            System.out.printf("Second: %s, sum: %s, %s\n", index, sum, numSet);
            ans[index]=sum;
            index++;
        }
//        for(long e: ans){
//            System.out.printf("%s, ", e);
//        }
//        System.out.println();
        return ans;
    }

    public static void main(String[] args) {
        //* Requirement
        //You are given a 0-indexed array nums of size n consisting of positive integers.
        //- You are also given a 2D array queries of size m where queries[i] = [index-i, k-i].
        //- Initially all elements of the array are unmarked.
        //- You need to apply m queries on the array in order, where on the ith query you do the following:
        //  - Mark the element at index (index-i) if it is not already marked.
        //  - Then mark (k-i unmarked elements) in the array (with the smallest values).
        //  - If (multiple such elements exist), mark the ones with the (smallest indices).
        // And if less than ki unmarked elements exist, then mark all of them.
        //* Return an array answer of size m where (answer[i]) is the sum of unmarked elements
        // in the array (after the ith query).
        //nums = [1,2,2,1,2,3,1], queries = [[1,2],[3,3],[4,2]]
        //[8,3,0]
        //(1),[2],2,(1),2,3,1
        //-1,-1,(2),[-1],(2),3,(1)
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //n == nums.length
        //m == queries.length
        //1 <= m <= n <= 105
        //1 <= n <= 105
        //queries[i].length == 2
        //0 <= index-i, ki <= n - 1
        //--> Sort
        //
        //- Brainstorm
        //- TreeSet : sort val, index incremental
        //- mark --> Xoá ra khỏi TreeSet
        //
        int[] nums = {1,2,2,1,2,3,1};
        int[][] queries = {{1,2},{3,3},{4,2}};
        unmarkedSumArray(nums, queries);
    }
}
