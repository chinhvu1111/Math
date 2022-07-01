package interviews;

import java.util.HashMap;

public class E58_ContinuousSubarraySum {

    public static boolean checkSubarraySum(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer, Integer> hashMap=new HashMap<>();
        int sum=0;

        for(int i=0;i<n;i++){
            nums[i]=nums[i]%k;
            sum+=nums[i];
            int numberMultiple=sum/k;
            int currentSum=sum;

//            if((sum%k==0&&!hashMap.isEmpty())||hashMap.containsKey(sum)){
//                return true;
//            }
            if(hashMap.containsKey(sum)&&hashMap.get(sum)!=i-1
                    || (!hashMap.containsKey(sum)&&sum%k==0)&&hashMap.size()>1){
                return true;
            }

            for(int j=1;j<=numberMultiple;j++){
//                int value=currentSum-j*k;
                currentSum-=k;

                if(hashMap.containsKey(currentSum)){
                    return true;
                }
            }
            if(!hashMap.containsKey(sum)){
                hashMap.put(sum, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int nums[]=new int[]{23,2,6,4,7};
        //1, Những bài liên quan đến multiple of k
        //--> Ta có thể sử dụng % (Lấy dư)
        //
//        int nums[]=new int[]{23,2,4,6,6};
        // % : 2, 2, 4, 6, 6.
//        int nums[]=new int[]{5,0,0,0};
//        int nums[]=new int[]{0};
//        int nums[]=new int[]{1,0};
        int nums[]=new int[]{1,0};
        System.out.println(checkSubarraySum(nums, 7));
    }
}
