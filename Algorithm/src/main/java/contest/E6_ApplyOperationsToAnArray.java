package contest;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class E6_ApplyOperationsToAnArray {
    public static int[] applyOperations(int[] nums) {
        int n=nums.length;

        if(n==0){
            return nums;
        }
        ArrayList<Integer> integers=new ArrayList<>();
        int countZero=0;

        for(int i=0;i<n-1;i++){
            if(nums[i]==nums[i+1]){
                if(nums[i]*2!=0){
                    integers.add(nums[i]*2);
                }else{
                    countZero++;
                }
                nums[i+1]=0;
            }else{
                if(nums[i]!=0){
                    integers.add(nums[i]);
                }else{
                    countZero++;
                }
            }
        }
        integers.add(nums[n-1]);

        for(int i=0;i<countZero;i++){
            integers.add(0);
        }
        for(int i=0;i<integers.size();i++){
            nums[i]=integers.get(i);
        }
        return nums;
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{1,2,2,1,1,0};
//        int[] arr=new int[]{0};
        int[] arr=new int[]{1,1};
        //- Các giá trị không có sự thay đôi tức thì
        //Day xuong cuooi --> Ve sau
        //
        int rs[]=applyOperations(arr);
        for(int a:rs){
            System.out.printf("%s, ", a);
        }
    }
}
