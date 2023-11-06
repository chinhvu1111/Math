package E1_hashmap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class E6_FindAnagramMappings {

    public static int[] anagramMappings(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> mapIndexNums1=new HashMap<>();
        int[] rs=new int[nums1.length];

        for(int i=0;i<nums1.length;i++){
            mapIndexNums1.put(nums1[i], i);
        }
        for(int i=0;i<nums2.length;i++){
            rs[mapIndexNums1.get(nums2[i])]=i;
        }
        return rs;
    }

    public static int[] anagramMappingsQueue(int[] nums1, int[] nums2) {
        HashMap<Integer, Queue<Integer>> mapIndexNums1=new HashMap<>();
        int[] rs=new int[nums1.length];

        for(int i=0;i<nums1.length;i++){
            Queue<Integer> oldQueue=mapIndexNums1.get(nums1[i]);
            if(oldQueue==null){
                oldQueue=new LinkedList<>();
            }
            oldQueue.add(i);
            mapIndexNums1.put(nums1[i], oldQueue);
        }
        for(int i=0;i<nums2.length;i++){
            rs[mapIndexNums1.get(nums2[i]).poll()]=i;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- An array a is (an anagram of an array b) means b is made by randomizing the order of the elements in a.
        //- You are given two integer arrays nums1 and nums2 where nums2 is an anagram of nums1.
        //- (Both arrays) may contain duplicates.
        //* Return (an index mapping array) mapping from (nums1 to nums2) where (mapping[i] = j)
        // means the ith element in nums1 appears in nums2 (at index j)
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= nums1.length <= 100
        //nums2.length == nums1.length
        //0 <= nums1[i], nums2[i] <= 10^5
        //nums2 is an anagram of nums1.
        //
        //- Brainstorm
        //- Dùng hashmap
        //+ Dùng 1 hashmap lưu lại index của cái 1
        //+ Int[n] để lưu lại kết quả
        //- Value có thể bị duplicate:
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n)
        //
        //#Reference:
        //252. Meeting Rooms
        //1675. Minimize Deviation in Array
        //160. Intersection of Two Linked Lists
        //
    }
}
