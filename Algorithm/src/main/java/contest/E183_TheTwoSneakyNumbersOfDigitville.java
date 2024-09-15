package contest;

import java.util.HashMap;

public class E183_TheTwoSneakyNumbersOfDigitville {

    public static int[] getSneakyNumbers(int[] nums) {
        int n=nums.length;
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int[] rs=new int[2];
        int index=0;

        for(int i=0;i<n;i++){
            mapCount.put(nums[i], mapCount.getOrDefault(nums[i], 0)+1);
            if(mapCount.get(nums[i])==2){
                rs[index++]=nums[i];
            }
        }
        return rs;
    }

    public static void main(String[] args) {

    }
}
