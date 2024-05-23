package contest;

import java.util.HashMap;

public class E105_SpecialArrayII {

    public static boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n=nums.length;
        HashMap<Integer, Integer> rangeMap=new HashMap<>();

        for(int i=0;i<n;i++){
            rangeMap.put(i, i);
        }
        int start=0, end;

        for(int i=0;i<n-1;i++){
            if(Math.abs((nums[i]%2)- (nums[i+1]%2))==1){
                end=i+1;
                //nums[2]<>nums[3]
                rangeMap.put(end, start);
            }else{
                //2,3,1 ==>
                //+ start=0,end=1
                //+ start=0, end=2 ==> Not adaptive ==> start=2
                //
                //nums[1]<>nums[2]
                start=i+1;
            }
        }
//        System.out.println(rangeMap);
        boolean[] rs=new boolean[queries.length];
        for(int i=0;i<rs.length;i++){
            int[] q=queries[i];

            if(rangeMap.get(q[1])<=q[0]){
                rs[i]=true;
            }else{
                rs[i]=false;
            }
//            System.out.printf("%s %s\n", i, rs[i]);
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- An array is considered special if every pair of its adjacent elements contains two numbers with (different parity).
        //You are given an array of integer nums and a 2D integer matrix queries, where for queries[i] = [fromi, toi]
        // your task is to check that subarray nums[fromi..toi] (is special or not).
        //* Return an array of booleans answer such that answer[i] is true if nums[fromi..toi] is special.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^5
        //1 <= queries.length <= 10^5
        //queries[i].length == 2
        //0 <= queries[i][0] <= queries[i][1] <= nums.length - 1
        //--> N lớn ==> Time : O(N)
        //
        //- Brainstorm
        //Example 2:
        //Input: nums = [4,3,1,6], queries = [[0,2],[2,3]]
        //Output: [false,true]
        //nums = [4,3,1,6]
        //0,1,1,0
        //
        //1,1,0,1
        //1,1,0,0 ==> Việc sum %2 chưa chắc đúng.
        //- Lưu lại độ dài nhất cùa từng special array
        //Ex:
        //nums = [4,3,1,6]
        //+ start=0, end=1
        //+ start=2, end=3
        //
        int[] nums=new int[]{4,3,1,6};
        int[][] queries=new int[][]{{0,2}, {2,3}};
        isArraySpecial(nums, queries);
    }
}
