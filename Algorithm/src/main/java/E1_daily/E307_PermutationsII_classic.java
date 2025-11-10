package E1_daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class E307_PermutationsII_classic {

    public static void backTrack(
            List<List<Integer>> list, List<Integer> curList,
            int[] nums, int index, HashMap<Integer, Integer> mapCount){
        int n=nums.length;
        if(index==n){
            list.add(new ArrayList<>(curList));
            return;
        }
        for(int val: mapCount.keySet()){
            if(mapCount.get(val)==0){
                continue;
            }
            curList.add(val);
            int curCount = mapCount.get(val);
            mapCount.put(val, curCount-1);
            backTrack(list, curList, nums, index+1, mapCount);
            mapCount.put(val, mapCount.getOrDefault(val, 0)+1);
            curList.remove(curList.size()-1);
        }
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        int n=nums.length;
        //
        List<List<Integer>> rs=new ArrayList<>();
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        for (int i = 0; i < n; i++) {
            mapCount.put(nums[i], mapCount.getOrDefault(nums[i], 0)+1);
        }
        backTrack(rs, new ArrayList<>(), nums, 0, mapCount);
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a collection of numbers, nums, that might contain duplicates,
        //* Return all possible unique permutations in any order.
        //
        //Example 1:
        //
        //Input: nums = [1,1,2]
        //Output:
        //[[1,1,2],
        // [1,2,1],
        // [2,1,1]]
        //==> This problem is related to the values
        //Ex:
        //1,1,2
        //1,1,2 ==> If we just choose 1 or 1
        //
        //1 + countMap(1:1,2:1)
        //1 + countMap(1:1,2:1)
        //==> Same
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 8
        //-10 <= nums[i] <= 10
        //  + Time: O(n!)
        //
        //* Brainstorm:
        //- Backtracking
        //Ex:
        //1,1,2
        //1,1,2 ==> If we just choose 1 or 1
        //
        //1 + countMap(1:1,2:1)
        //1 + countMap(1:1,2:1)
        //==> Same
        //
        //- Rather than choosing 1,1
        //  + We just choose 1
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n!)
        //
        int[] nums = {1,1,2};
        System.out.println(permuteUnique(nums));
    }
}
