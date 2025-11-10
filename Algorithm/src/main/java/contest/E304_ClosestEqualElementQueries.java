package contest;

import java.util.*;

public class E304_ClosestEqualElementQueries {

    public static List<Integer> solveQueries(int[] nums, int[] queries) {
        int n=nums.length;
        int[] minDistance=new int[n];
        Arrays.fill(minDistance, Integer.MAX_VALUE);
        HashMap<Integer, Integer> firstExists = new HashMap<>();
        HashMap<Integer, Integer> lastExists = new HashMap<>();
        HashMap<Integer, Integer> leftTraverse=new HashMap<>();
        HashMap<Integer, Integer> rightTraverse=new HashMap<>();

        for (int i = 0; i < n; i++) {
            if(leftTraverse.containsKey(nums[i])){
               int prevIndex = leftTraverse.get(nums[i]);
               minDistance[i]=i-prevIndex;
            }
            leftTraverse.put(nums[i], i);
        }
        for (int i = n-1; i >=0; i--) {
            if(rightTraverse.containsKey(nums[i])){
                int prevIndex = rightTraverse.get(nums[i]);
                minDistance[i]=Math.min(prevIndex-i, minDistance[i]);
            }
            rightTraverse.put(nums[i], i);
        }

        for(int i=0;i<n;i++){
            if(!firstExists.containsKey(nums[i])){
                firstExists.put(nums[i], i);
            }
        }
//        for (int i = 0; i < n; i++) {
//            System.out.printf("%s, ", minDistance[i]);
//        }
//        System.out.print("\n");
        for (int i = n-1; i >=0; i--) {
            if(!lastExists.containsKey(nums[i])){
                lastExists.put(nums[i], i);
                if(firstExists.containsKey(nums[i])&&firstExists.get(nums[i])!=i){
//                    System.out.printf("%s %s",i, firstExists.get(nums[i]));
//                    System.out.println();
                    minDistance[i]=Math.min(n-i+firstExists.get(nums[i]), minDistance[i]);
                }
            }
        }
//        System.out.println(firstExists);
//        System.out.println(lastExists);
        //0,1,2...,3,4
        //= n-1-last+first+1
        for(int e: firstExists.keySet()){
            int index = firstExists.get(e);
            if(lastExists.containsKey(e)&& !Objects.equals(lastExists.get(e), index)){
//                System.out.printf("%s %s",index, lastExists.get(e));
//                System.out.println();
                minDistance[index]=Math.min(n-lastExists.get(e)+index, minDistance[index]);
            }
        }
        List<Integer> rs=new ArrayList<>(queries.length);
        for(int i: queries){
            if(minDistance[i]!=Integer.MAX_VALUE){
                rs.add(minDistance[i]);
            }else{
                rs.add(-1);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a circular array nums) and (an array queries).
        //- For (each query i), you have to find the following:
        //  + The (minimum distance) between the element at (index queries[i]) and any (other index j) in (the circular array),
        //  where nums[j] == nums[queries[i]].
        //      + If no such index exists, the answer for that query should be -1.
        //* Return an array answer of the same size as queries, where answer[i] represents the result for query i.
        //
        //Example 1:
        //
        //Input: nums = [1,3,1,4,1,3,2], queries = [0,3,5]
        //
        //Output: [2,-1,3]
        //
        //Explanation:
        //
        //Query 0: The element at queries[0] = 0 is nums[0] = 1. The nearest index with the same value is 2, and the distance between them is 2.
        //Query 1: The element at queries[1] = 3 is nums[3] = 4. No other index contains 4, so the result is -1.
        //Query 2: The element at queries[2] = 5 is nums[5] = 3. The nearest index with the same value is 1, and the distance between them is 3 (following the circular path: 5 -> 6 -> 0 -> 1).
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= queries.length <= nums.length <= 10^5
        //1 <= nums[i] <= 10^6
        //0 <= queries[i] < nums.length
        //  + Length<=10^5 ==> Time: O(n*k)
        //
        //- Brainstorm
        //- Prefix sum?
        //
        //Example 1:
        //
        //Input: nums = [1,3,1,4,1,3,2], queries = [0,3,5]
        //Output: [2,-1,3]
        // 3 -> 2 -> 1 -> 3
        //- First apprearance ==> last apprerance
        //- Middle element is compared to:
        //  + Left
        //      + For-1
        //  + Right
        //      + For-2
        //
//        int[] nums = {1,3,1,4,1,3,2}, queries = {0,3,5};
        int[] nums = {1,1,1,1,1,1}, queries = {0,3,5};
        System.out.println(solveQueries(nums, queries));
    }
}
