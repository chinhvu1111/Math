package E1_weekly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class E20_IntersectionOfMultipleArrays {

    public static List<Integer> intersection(int[][] nums) {
        HashSet<Integer> set=new HashSet<>();

        for(int e: nums[0]){
            set.add(e);
        }
        int n=nums.length;
        for(int j=1;j<n;j++){
            HashSet<Integer> curSet=new HashSet<>();
            for(int e: nums[j]){
                if(set.contains(e)){
                    curSet.add(e);
                }
            }
            set=curSet;
        }
        List<Integer> rs = new ArrayList<>(set);
        Collections.sort(rs);
        return rs;
    }

    public static List<Integer> intersectionCountSort(int[][] nums) {
        List<Integer> ans = new ArrayList<>();
        int[] count  = new int[1001];

        for(int[] arr : nums){
            for(int i : arr){
                count[i]++;
            }
        }
        for(int i=0;i<count.length;i++){
            if(count[i]==nums.length){
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a 2D integer array nums where nums[i] is a (non-empty array) of (distinct positive integers),
        //* Return (the list of integers) that are present in (each array of nums) (sorted in ascending order).
        //- Find the common elements of all of arrays
        //
        //** Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 1000
        //1 <= sum(nums[i].length) <= 1000
        //1 <= nums[i][j] <= 1000
        //All the values of nums[i] are unique.
        //
        //- Brainstorm
        //- Dùng hashSet
        //  + Với mỗi array trong nums
        //  ==> Tạo hashset mới common
        //- Cách 2:
        //- Vì number trong each array là unique
        //- Count số lượng xuất hiện của mỗi số trong all of arrays:
        //  + count[i] == n:
        //      + Xuất hiện đúng n lần trong all of arrays
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(max(value)
        //- Time: O(n*m)
        //
        int[][] nums = {{3,1,2,4,5},{1,2,3,4},{3,4,5,6}};
        System.out.println(intersection(nums));
        System.out.println(intersectionCountSort(nums));
        //
        //#Reference:
        //370. Range Addition
        //985. Sum of Even Numbers After Queries
        //905. Sort Array By Parity
    }
}
