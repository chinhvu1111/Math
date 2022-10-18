package interviews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class E146_MostFrequentEvenElement {

    public static int mostFrequentEven(int[] nums) {
        HashMap<Integer, Integer> countMap=new HashMap<>();
        int rsCount=0;
        int rsVal=-1;
        int n=nums.length;

        for(int i=0;i<n;i++){
            int currentValue=nums[i];

            if(rsCount>=n-1){
                return rsVal;
            }
            if(currentValue%2==0){
                int valueCount;

                valueCount=countMap.getOrDefault(currentValue, 0)+1;
                countMap.put(currentValue, valueCount);

                if(valueCount>rsCount){
                    rsCount=valueCount;
                    rsVal=currentValue;
                }else if(valueCount==rsCount&&currentValue<rsVal){
                    rsVal=currentValue;
                }

            }
        }

        return rsVal;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{0,1,2,2,4,4,1};
//        int[] arr=new int[]{0};
//        int[] arr=new int[]{1};
        System.out.println(mostFrequentEven(arr));
    }
}
