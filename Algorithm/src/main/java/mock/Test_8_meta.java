package mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test_8_meta {

    //** Đề bài:
    //- Tìm intersection của 2 array với count phần tử là min của 2 array.
    //- Min=0 --> không xuất hiện
    //
    //** Bài này tư duy như sau:
    //1.
    //1.1, Idea
    //- HashMap cho 1 array trước ==> sau đó traverse array còn lại để giảm dần count
    //VD:
    //nums1={1,2,2,1} ==> [1:2,2:2]
    //nums2={2,2}
    //{2 có tồn tại --> thằng còn lại -1 ==> Add phần tử đó vào result array.

    public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> mapCountNums1=new HashMap<>();
        List<Integer> rsList=new ArrayList<>();

        for(int i=0;i<nums1.length;i++){
            mapCountNums1.put(nums1[i], mapCountNums1.getOrDefault(nums1[i], 0)+1);
        }
        for(int i=0;i<nums2.length;i++){
            if(!mapCountNums1.containsKey(nums2[i])){
                continue;
            }
            rsList.add(nums2[i]);
            int currentCount=mapCountNums1.get(nums2[i]);
            if(currentCount==1){
                mapCountNums1.remove(nums2[i]);
            }else{
                mapCountNums1.put(nums2[i], currentCount-1);
            }
        }
        int[] rs=new int[rsList.size()];

        for(int i=0;i<rsList.size();i++){
            rs[i]=rsList.get(i);
        }
        return rs;
    }

    public static void main(String[] args) {
        //https://leetcode.com/problems/intersection-of-two-arrays-ii/editorial/
        //#Reference:
        //351. Android Unlock Patterns
        //349. Intersection of Two Arrays
        //1002. Find Common Characters
        //2215. Find the Difference of Two Arrays
    }
}
