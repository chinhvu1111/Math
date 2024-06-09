package E1_weekly;

import java.util.*;

public class E1_LongestCommonSubsequenceBetweenSortedArrays {

    public static List<Integer> longestCommonSubsequence(int[][] arrays) {
        Map<Integer, Integer> mapCount = new HashMap<>();
        int n = arrays.length;
        List<Integer> rs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int[] a = arrays[i];

            for (int e : a) {
                if (i == 0 || (mapCount.containsKey(e) && mapCount.get(e) == i - 1)) {
                    mapCount.put(e, i);
                }
                if(i==n-1){
                    if(mapCount.containsKey(e)&&mapCount.get(e)==n-1){
                        rs.add(e);
                    }
                }
            }
        }
//        System.out.println(mapCount);

//        for (Map.Entry<Integer, Integer> e : mapCount.entrySet()) {
//            if (e.getValue() == n - 1) {
//                rs.add(e.getKey());
//            }
//        }
//        Collections.sort(rs);
//        System.out.println(rs);
        return rs;
    }

    public static List<Integer> longestCommonSubsequenceTwoPointers(int[][] arrays) {
        int n = arrays.length;
        List<Integer> rs=new ArrayList<>();

        for(int e: arrays[0]){
            rs.add(e);
        }
        for(int i=1;i<n;i++){
            rs=longestCommon(rs, arrays[i]);
        }
        return rs;
    }

    public static List<Integer> longestCommon(List<Integer> prevElements, int[] arr){
        List<Integer> rs=new ArrayList<>();
        int i=0,j=0;
        int n=prevElements.size();
        int m=arr.length;
        //Ex:
        //prev = [1,3,4]
        //arr = [1,4,7,9]
        //
        //- prev[i]<arr[j]: i++ --> tăng lên vì val trong khoảng prev[i] => arr[j] chắc chắn không thuộc list
        //- prev[i]>arr[j]: j++
        while(i<n&&j<m){
            if(prevElements.get(i)==arr[j]){
                rs.add(arr[j]);
                i++;
                j++;
            }else if(prevElements.get(i)<arr[j]){
                i++;
            }else {
                j++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an array of integer arrays where each arrays[i] is sorted in (strictly increasing order),
        //* Return an integer array representing (the longest common subsequence) between (all the arrays).
        //- A subsequence is a sequence that can be derived from (another sequence) by deleting some elements (possibly none)
        // without changing the (order of the remaining elements).
        //* Tìm array chung sao cho phần tử của nó sẽ xuất hiện trong tất cả các arrays đã cho.
        //Ex:
        //Example 1:
        //Input: arrays = [[1,3,4],
        //                 [1,4,7,9]]
        //Output: [1,4]
        //Explanation: The longest common subsequence in the two arrays is [1,4].
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //2 <= arrays.length <= 100
        //1 <= arrays[i].length <= 100
        //1 <= arrays[i][j] <= 100
        //arrays[i] is sorted in strictly increasing order.
        //==> Size khá nhỏ --> Có thể xử lý O(n^2)...
        //
        //- Brainstorm
        //- Bài này giống bài string
        //- Ta chỉ cần count lần xuất hiện của mỗi số là được.
        //  + Mỗi số gắn với 1 index nào đó --> Nếu current index = prev index+1
        //      + Thì ta sẽ update <> thôi.
        //
        //1.1, Optimization
        //- Có thể bỏ đoạn sort đi
        //  ==> Vì đoạn đấy sẽ mất O(n*log(n)) time.
        //
        //1.2, Complexity
        //- N is size of all array
        //- Space : O(n + log(n)(sort)) ==> O(n) after optimizing
        //- Time: O(n)
        //
        //2. Two pointers
        //2.0,
        //- Đoạn này ta có thể xét common elements của từng pair of array
        //- Check thì ta có thể dùng two pointers.
        //2.1, Optimizatiom
        //2.2, Complexity
        //- Space: O(n)
        //- Time : O(n)
        //
        //3. Binary search
        //3.0,
        //- Ta có thể dùng binary search --> Không nhanh bằng two pointers.
        //
        //
        int[][] arrays =
                {
                        {2, 3, 6, 8},
                        {1, 2, 3, 5, 6, 7, 10},
                        {2, 3, 4, 6, 9}
                };
        System.out.println(longestCommonSubsequence(arrays));
        System.out.println(longestCommonSubsequenceTwoPointers(arrays));
        //2225. Find Players With Zero or One Losses
        //2364. Count Number of Bad Pairs
        //676. Implement Magic Dictionary
        //1330. Reverse Subarray To Maximize Array Value
        //874. Walking Robot Simulation
        //2838. Maximum Coins Heroes Can Collect
    }
}
