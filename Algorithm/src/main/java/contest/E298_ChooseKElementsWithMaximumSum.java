package contest;

import java.util.*;

public class E298_ChooseKElementsWithMaximumSum {

    public static long[] findMaxSum(int[] nums1, int[] nums2, int k) {
        int n=nums1.length;
        int[][] arr=new int[n][3];

        for(int i=0;i<n;i++){
            arr[i][0]=nums1[i];
            arr[i][1]=nums2[i];
            arr[i][2]=i;
        }
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
//        for (int i = 0; i < arr.length; i++) {
//            System.out.printf("%s, ", arr[i][2]);
//        }
//        System.out.println();
        HashSet<Integer> visited=new HashSet<>();
        HashSet<Integer> duplicated=new HashSet<>();

        for (int i = 0; i < n; i++) {
            if(!visited.contains(arr[i][0])){
                visited.add(arr[i][0]);
            }else{
                duplicated.add(arr[i][0]);
            }
        }
        long sum=0L;
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        long[] rs=new long[n];

        //1,2,3,3,3,4
        //rs[2] = [1]+[2]
        //rs[4] = [3]+[3]
        long tmpSum=0;
        List<Integer> tmpList=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            rs[arr[i][2]]=sum;
            if(!duplicated.contains(arr[i][0])){
                queue.add(arr[i][1]);
                sum+=arr[i][1];
//                while(queue.size()>=k+1&&!queue.isEmpty()){
//                    sum-=queue.poll();
//                }
            }else{
                tmpSum+=arr[i][1];
                tmpList.add(arr[i][1]);
            }
            if(i+1<n&&arr[i+1][0]!=arr[i][0]){
                queue.addAll(tmpList);
                sum+=tmpSum;
                tmpSum=0;
                tmpList.clear();
            }
            while (queue.size()>=k+1&&!queue.isEmpty()){
                sum-=queue.poll();
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two integer arrays, (nums1 and nums2), both of length n, along with (a positive integer k).
        //- For each index (i from 0 to n - 1), perform the following:
        //  + Find (all indices j) where (nums1[j] is less than nums1[i]).
        //  + Choose (at most k) values of nums2[j] at these indices to (maximize) (the total sum).
        //* Return an array answer of size n, where answer[i] represents the result for (the corresponding index i).
        //
        //Example 1:
        //
        //Input: nums1 = [4,2,1,5,3], nums2 = [10,20,30,40,50], k = 2
        //Output: [80,30,0,80,50]
        //
        //Explanation:
        //
        //For i = 0: Select the 2 largest values from nums2 at indices [1, 2, 4] where nums1[j] < nums1[0], resulting in 50 + 30 = 80.
        //For i = 1: Select the 2 largest values from nums2 at index [2] where nums1[j] < nums1[1], resulting in 30.
        //For i = 2: No indices satisfy nums1[j] < nums1[2], resulting in 0.
        //For i = 3: Select the 2 largest values from nums2 at indices [0, 1, 2, 4] where nums1[j] < nums1[3], resulting in 50 + 30 = 80.
        //For i = 4: Select the 2 largest values from nums2 at indices [1, 2] where nums1[j] < nums1[4], resulting in 30 + 20 = 50.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == nums1.length == nums2.length
        //1 <= n <= 10^5
        //1 <= nums1[i], nums2[i] <= 10^6
        //  + >0
        //1 <= k <= n
        //  + n<=10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //
        //Input: nums1 = [4,2,1,5,3], nums2 = [10,20,30,40,50], k = 2
        //Output: [80,30,0,80,50]
        //
        //
        //1.1, Cases
        //1.2, Optimize
        //
        //
        //1.3, Complexity
        //
        //
//        int[] nums1 = {4,2,1,5,3}, nums2 = {10,20,30,40,50};
//        int[] nums1 = {2,2,2,2}, nums2 = {3,1,2,3};
//        int[] nums1 = {2,2,2,2}, nums2 = {3,1,2,3};
//        int[] nums1 = {1,2,3,3,3,4}, nums2 = {10,20,30,100,40,25};
//        int k = 2;
        int[] nums1 = {25,15,1,28,3,13,29,26,1,2,28,5,2,14,19,2,4};
        int[] nums2 = {25,21,3,23,26,6,30,22,27,21,24,27,15,17,15,16,25};
        int k = 9;
        //Output:   [195,180,0,211,82,160,220,205,0,0,211,133,0,166,195,0,108]
        //Expected: [195,180,0,211,82,160,220,205,0,30,211,133,30,166,195,30,108]
        long[] rs= findMaxSum(nums1, nums2, k);
        //1,2,3,3,3,4
        //rs[2] = [1]+[2]
        //rs[4] = [3]+[3]
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
    }
}
