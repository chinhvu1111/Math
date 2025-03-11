package contest;

import java.util.Map;
import java.util.TreeMap;

public class E285_MergeTwo2DArraysBySummingValues {

    public static int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int n=nums1.length;
        int m=nums2.length;
        TreeMap<Integer, Integer> sortMap=new TreeMap<>();

        for(int i=0;i<n;i++){
            Integer curVal = sortMap.getOrDefault(nums1[i][0], 0);
            curVal+=nums1[i][1];
            sortMap.put(nums1[i][0], curVal);
        }
        for(int i=0;i<m;i++){
            Integer curVal = sortMap.getOrDefault(nums2[i][0], 0);
            curVal+=nums2[i][1];
            sortMap.put(nums2[i][0], curVal);
        }
        int[][] rs=new int[sortMap.size()][2];
        int i=0;
        for(Map.Entry<Integer, Integer> e: sortMap.entrySet()){
            rs[i][0]=e.getKey();
            rs[i][1]=e.getValue();
            i++;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two 2D integer arrays nums1 and nums2.
        //  + nums1[i] = [id-i, vali] indicate that the number with (the id id-i) has a value equal to (val-i).
        //  + nums2[i] = [id-i, vali] indicate that the number with (the id id-i) has a value equal to (val-i).
        //- Each array contains unique ids and is sorted in ascending order by id.
        //- Merge the two arrays into one array that is sorted in ascending order by id, respecting the following conditions:
        //  + Only ids that appear in (at least one) of the two arrays should be included in the resulting array.
        //  + Each id should be included (only once) and its value should be (the sum of the values) of this id in the two arrays.
        //      + If (the id does not exist) in one of the two arrays, then assume its value in that array to be 0.
        //* Return the resulting array.
        //  - The returned array must be sorted in (ascending order by id).
        //
        //Example 1:
        //
        //Input: nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
        //Output: [[1,6],[2,3],[3,2],[4,6]]
        //Explanation: The resulting array contains the following:
        //- id = 1, the value of this id is 2 + 4 = 6.
        //- id = 2, the value of this id is 3.
        //- id = 3, the value of this id is 2.
        //- id = 4, the value of this id is 5 + 1 = 6.
        //
        // Idea
        //1. Binary search
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums1.length, nums2.length <= 200
        //nums1[i].length == nums2[j].length == 2
        //1 <= idi, vali <= 1000
        //Both arrays contain unique ids.
        //Both arrays are in strictly ascending order by id.
        //
        //- Brainstorm
        //
        int[][] nums1 = {{1,2},{2,3},{4,5}}, nums2 = {{1,4},{3,2},{4,1}};
        int[][] rs = mergeArrays(nums1, nums2);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s %s, ", rs[i][0], rs[i][1]);
        }
        //
    }
}
