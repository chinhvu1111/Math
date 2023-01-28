package interviews;

import java.util.HashMap;

public class E264_DegreeOfAnArray_without_des {

    public static int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> startIndex=new HashMap<>();
        HashMap<Integer, Integer> countHash=new HashMap<>();
        int n=nums.length;
        int rs=Integer.MAX_VALUE;
        int countMax=0;

        for(int i=0;i<n;i++){
            int currentValue=countHash.getOrDefault(nums[i], 0)+1;
            countHash.put(nums[i], countHash.getOrDefault(nums[i], 0)+1);
            if(!startIndex.containsKey(nums[i])){
                startIndex.put(nums[i], i);
            }
            countMax=Math.max(countMax, currentValue);
        }
        for(int i=n-1;i>=0;i--){
            if(countHash.containsKey(nums[i])&&countHash.get(nums[i])==countMax){
                int currentLength=i-startIndex.get(nums[i])+1;

                if(currentLength<=rs){
                    rs=currentLength;
                }
                countHash.remove(nums[i]);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{1,2,2,3,1,4,2};
        int[] arr=new int[]{1,2,2,3,1};
        System.out.println(findShortestSubArray(arr));
    }
}
