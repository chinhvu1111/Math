package contest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

public class E36_CountCompleteSubarraysInAnArray {

    public static int countCompleteSubarraysTemp(int[] nums) {
        HashSet<Integer> distinct=new HashSet<>();

        for(Integer num: nums){
            distinct.add(num);
        }
        System.out.println(distinct.size());
        int low=0;
        int high=0;
        int n=nums.length;
        HashMap<Integer, Integer> currentDistinct=new HashMap<>();
        int rs=0;

        while (low<=high){
            currentDistinct.put(nums[high], high);
            high++;
            System.out.printf("%s %s\n", rs, currentDistinct);
            if(currentDistinct.size()==distinct.size()){
                while (currentDistinct.size()==distinct.size()){
                    rs++;
                    currentDistinct.put(nums[high], high);
                    high++;
                }
                while (currentDistinct.size()==distinct.size()){
                    rs++;
                    if(currentDistinct.get(nums[low])>low){
                        low++;
                    }else{
                        currentDistinct.remove((Integer)(nums[low]));
                        low++;
                    }
                }
            }else if(currentDistinct.size()<distinct.size()){
                continue;
            }else{
                while (currentDistinct.size()>distinct.size()){
                    if(currentDistinct.get(nums[low])>low){
                        low++;
                    }else{
                        currentDistinct.remove((Integer)(nums[low]));
                        low++;
                    }
                }
            }
        }
        return rs;
    }

    public static int countCompleteSubarrays(int[] nums) {
        HashSet<Integer> distinct=new HashSet<>();
        int n=nums.length;

        for(Integer num: nums){
            distinct.add(num);
        }
        int rs=0;
        for(int i=0;i<n;i++){
            HashSet<Integer> curSet=new HashSet<>();
            for(int j=i;j<nums.length;j++){
                if(curSet.size()>distinct.size()){
                    break;
                }
                curSet.add(nums[j]);
                if(curSet.size()==distinct.size()){
                    rs++;
                }
            }
        }
        //https://leetcode.com/problems/count-complete-subarrays-in-an-array/submissions/
        return rs;
    }
    public static void main(String[] args) {
        int[] arr={1,3,1,2,2};
        System.out.println(countCompleteSubarrays(arr));
    }
}
