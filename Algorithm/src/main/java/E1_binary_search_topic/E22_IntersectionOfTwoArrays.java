package E1_binary_search_topic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class E22_IntersectionOfTwoArrays {

    public static int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1=new HashSet<>();
        HashSet<Integer> rs=new HashSet<>();

        for(int e: nums1){
            set1.add(e);
        }
        for(int e: nums2){
            if(set1.contains(e)){
                rs.add(e);
            }
        }
        int[] rsArr=new int[rs.size()];
        Iterator<Integer> iter = rs.iterator();

        for(int i=0;i<rs.size();i++){
            rsArr[i]=iter.next();
        }
        return rsArr;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Given two integer arrays nums1 and nums2,
        //* Return an array of their intersection.
        // Each element in the result must be (unique) and you may return the result in any order.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= nums1.length, nums2.length <= 1000
        //0 <= nums1[i], nums2[i] <= 1000
        //
        //- Brainstorm
        //-
        //#Reference:
        //1213. Intersection of Three Sorted Arrays
        //2085. Count Common Words With One Occurrence
        //2143. Choose Numbers From Two Arrays in Range
    }
}
