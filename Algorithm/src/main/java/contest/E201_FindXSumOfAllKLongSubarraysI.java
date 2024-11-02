package contest;

import E1_Tree.E13_ConstructBinaryTreeFromInorderAndPostorderTraversal;

import java.util.*;

public class E201_FindXSumOfAllKLongSubarraysI {

    public static int[] findXSum(int[] nums, int k, int x) {
        int n=nums.length;
        int[] rs=new int[n-k+1];

        for(int i=0;i+k-1<n;i++){
            Map<Integer, Integer> mapCount=new HashMap<>();
            for(int j=i;j<=i+k-1;j++){
                mapCount.put(nums[j], mapCount.getOrDefault(nums[j], 0)+1);
            }
            PriorityQueue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[1]!=o2[1]){
                        return o2[1]-o1[1];
                    }
                    return o2[0]-o1[0];
                }
            });
            for(Map.Entry<Integer, Integer> e: mapCount.entrySet()) {
                queue.add(new int[]{e.getKey(), e.getValue()});
            }
            HashSet<Integer> maxElements=new HashSet<>();
            for(int j=0;j<x&&!queue.isEmpty();j++){
                maxElements.add(queue.poll()[0]);
            }
            int sum=0;
            for(int j=i;j<=i+k-1;j++){
                if(maxElements.contains(nums[j])){
                    sum+=nums[j];
                }
            }
            rs[i]=sum;
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an array nums of n integers and two integers k and x.
        //- The x-sum of an array is calculated by the following procedure:
        //  + Count (the occurrences of all elements) in the array.
        //  + Keep only the occurrences of the top (x most frequent elements).
        //  If two elements have the same number of occurrences, the element with (the bigger value) is considered (more frequent).
        //  + Calculate (the sum of the resulting array).
        //* Note that if an array has less than x distinct elements, its x-sum is the sum of the array.
        //* Return an integer array answer of length n - k + 1 where answer[i] is the x-sum of the subarray nums[i..i + k - 1].
        //- A subarray is a contiguous non-empty sequence of elements within an array.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //** Brainstorm
        //
//        int[] nums = {1,1,2,2,3,4,2,3};
//        int k = 6, x = 2;
        int[] nums = {9,22};
        int k = 3, x = 3;
        int[] rs = findXSum(nums, k, x);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s,", rs[i]);
        }
        System.out.println();
    }
}
