package contest;

import java.util.HashMap;
import java.util.HashSet;

public class E242_MinimumNumberOfOperationsToMakeElementsInArrayDistinct {

    public static int minimumOperations(int[] nums) {
        int n=nums.length;
        int rs=0;

        for(int i=0;;i+=3){
            HashSet<Integer> val=new HashSet<>();
            for(int j=i;j<n;j++){
                val.add(nums[j]);
            }
            if(val.size()==n-Math.min(i,n)){
                break;
            }
            rs++;
            if(val.size()==0){
                break;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        int[] nums = {1,2,3,4,2,3,3,5,7};
//        int[] nums = {1,1,1,1,1};
//        int[] nums = {1,1,1,1,1,1};
        int[] nums = {1,1,1,1};
        System.out.println(minimumOperations(nums));
    }
}
