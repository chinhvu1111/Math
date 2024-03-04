package contest;

import java.util.ArrayList;
import java.util.List;

public class E47_DistributeElementsIntoTwoArraysI {

    public static int[] resultArray(int[] nums) {
        int n=nums.length;
        List<Integer> nums1=new ArrayList<>();
        List<Integer> nums2=new ArrayList<>();
        int p=0, p1=0;
        int i=0;
        if(n>=1){
            nums1.add(nums[i++]);
            p++;
        }
        if(n>=2){
            nums2.add(nums[i++]);
            p1++;
        }
        for(;i<n;i++){
            if(nums1.get(p-1)>nums2.get(p1-1)){
                nums1.add(nums[i]);
                p++;
            }else{
                nums2.add(nums[i]);
                p1++;
            }
        }
        List<Integer> concatRs=new ArrayList<>();
        concatRs.addAll(nums1);
        concatRs.addAll(nums2);
        int[] rs=new int[concatRs.size()];

        for(i=0;i<concatRs.size();i++){
            rs[i]=concatRs.get(i);
            System.out.printf("%s, ", rs[i]);
        }
        return rs;
    }

    public static void main(String[] args) {
//        int[] arr={2,1,3};
//        int[] arr={1};
        int[] arr={};
        resultArray(arr);
    }
}
