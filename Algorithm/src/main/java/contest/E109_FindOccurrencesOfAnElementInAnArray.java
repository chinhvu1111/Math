package contest;

import java.util.ArrayList;
import java.util.List;

public class E109_FindOccurrencesOfAnElementInAnArray {

    public static int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        int n=nums.length;
        List<Integer> occ=new ArrayList<>();

        for(int i=0;i<n;i++){
            if(nums[i]==x){
                occ.add(i);
            }
        }
        int[] rs=new int[queries.length];

        for(int i=0;i<queries.length;i++){
            int curRs=-1;
            if(queries[i]<=occ.size()){
                curRs=occ.get(queries[i]-1);
            }
            rs[i]=curRs;
//            System.out.printf("i=%s, rs:%s\n", i, rs[i]);
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer array nums, (an integer array queries), and an integer x.
        //For each queries[i], you need to find the (index) of (the queries[i] th occurrence of x) in the (nums array).
        // If there are fewer than (queries[i] occurrences of x), the answer should be -1 for that query.
        //* Return an integer array answer containing the answers to all queries.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length, queries.length <= 10^5
        //1 <= queries[i] <= 10^5
        //1 <= nums[i], x <= 10^4
        //
        //- Brainstorm
        //
        //
        int[] nums = {1,3,1,7};
        int[] queries = {1,3,2,4};
        int x = 1;
        occurrencesOfElement(nums, queries, x);
    }
}
