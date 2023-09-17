package E1_hashmap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class E1_FindTheDifferenceOfTwoArrays {

    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        HashSet<Integer> hashSet1=new HashSet<>();
        HashSet<Integer> hashSet2=new HashSet<>();
        List<Integer> list=new ArrayList<>();
        List<Integer> list1=new ArrayList<>();

        for(Integer e:nums1){
            hashSet1.add(e);
        }
        for(Integer e:nums2){
            hashSet2.add(e);
        }
        for(Integer e:hashSet1){
            if(!hashSet2.contains(e)){
                list.add(e);
            }
        }
        for(Integer e:hashSet2){
            if(!hashSet1.contains(e)){
                list1.add(e);
            }
        }
        List<List<Integer>> rs=new ArrayList<>();
        rs.add(list);
        rs.add(list1);
        return rs;
    }

    public static void main(String[] args) {

    }
}
