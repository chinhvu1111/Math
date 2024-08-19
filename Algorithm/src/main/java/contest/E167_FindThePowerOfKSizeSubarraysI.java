package contest;

import java.util.*;

public class E167_FindThePowerOfKSizeSubarraysI {

    public static int[] resultsArray(int[] nums, int k) {
        int n=nums.length;
        if(n==0){
            return new int[]{};
        }
        int[] rs=new int[n-k+1];
        Arrays.fill(rs, -1);
        int i;
        int index=0;
        Queue<Integer> listIndexes=new LinkedList<>();
        //Index, value
        TreeSet<int[]> sortNodes=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]!=o2[1]){
                    return o2[1]-o1[1];
                }
                return o2[0]-o1[0];
            }
        });
        int lastIndexDecrease=-1;
        listIndexes.add(0);
        sortNodes.add(new int[]{0, nums[0]});

        for(i=1;i<k;i++){
            //1,2,3,4,3,5
            if(nums[i-1]!=nums[i]-1){
                lastIndexDecrease=i-1;
            }
            int[] curNode={i, nums[i]};
            sortNodes.add(curNode);
            listIndexes.add(i);
        }
        if(listIndexes.peek()>lastIndexDecrease){
            rs[index]=sortNodes.first()[1];
        }
        index++;
        i=k;
        for(;i<n;i++){
            if(nums[i-1]!=nums[i]-1){
                lastIndexDecrease=i-1;
            }
            int prevIndex=i-k;
            int[] removedNode={prevIndex, nums[prevIndex]};
            sortNodes.remove(removedNode);
            int[] curNode={i, nums[i]};
            sortNodes.add(curNode);
            if(prevIndex>=lastIndexDecrease){
                rs[index]=sortNodes.first()[1];
            }
            index++;
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an array of (integers nums of length n) and (a positive integer k).
        //- The power of an array is defined as:
        //  + (Its maximum element) if all of its elements are consecutive and sorted in ascending order.
        //  + -1 otherwise.
        //You need to find (the power of all subarrays of nums) of (size k).
        //* Return an integer array results of (size n - k + 1), where results[i] is the power of nums[i..(i + k - 1)].
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n == nums.length <= 500
        //1 <= nums[i] <= 10^5
        //1 <= k <= n
        //
        //** Brainstorm
        //Example 1:
        //
        //Input: nums = [1,2,3,4,3,2,5], k = 3
        //Output: [3,4,-1,-1,-1]
        //Explanation:
        //There are 5 subarrays of nums of size 3:
        //[1, 2, 3] with the maximum element 3.
        //[2, 3, 4] with the maximum element 4.
        //[3, 4, 3] whose elements are not consecutive.
        //[4, 3, 2] whose elements are not sorted.
        //[3, 2, 5] whose elements are not consecutive.
        //
        //- Slide window
//        int[] nums = {1,2,3,4,3,2,5};
//        int k = 3;
//        int[] nums = {3,2,3,2,3,2};
//        int k = 2;
//        int[] nums = {3,2};
//        int k = 2;
//        int[] nums = {2, 3};
//        int k = 2;
//        int[] nums = {3};
//        int k = 1;
        int[] nums = {1,3,4};
        int k = 2;
        int[] rs= resultsArray(nums, k);
        //[3,4]
        //-1,4
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
    }
}
