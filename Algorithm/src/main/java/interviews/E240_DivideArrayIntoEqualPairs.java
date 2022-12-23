package interviews;

import java.util.Arrays;
import java.util.HashMap;

public class E240_DivideArrayIntoEqualPairs {

    public static boolean divideArray(int[] nums) {
        int max= Arrays.stream(nums).max().getAsInt();
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int[] count=new int[max+1];
        int n=nums.length;

        for(int i=0;i<n;i++){
//            Integer currentCount=mapCount.get(nums[i]);
//            if(currentCount==null){
//                currentCount=1;
//            }else{
//                currentCount++;
//            }
//            mapCount.put(nums[i], currentCount);
            count[nums[i]]++;
        }
        for(int i=0;i<n;i++){
            if(count[nums[i]]%2==1){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{3,2,3,2,2,2};
    }
}
