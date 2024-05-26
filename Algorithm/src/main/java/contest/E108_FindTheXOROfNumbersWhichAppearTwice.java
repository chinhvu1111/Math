package contest;

import java.util.HashMap;
import java.util.Map;

public class E108_FindTheXOROfNumbersWhichAppearTwice {

    public static int duplicateNumbersXOR(int[] nums) {
        int n=nums.length;
        HashMap<Integer, Integer> mapCount=new HashMap<>();

        for(int i=0;i<n;i++){
            mapCount.put(nums[i], mapCount.getOrDefault(nums[i], 0)+1);
        }
        int rs=0;

        for(Map.Entry<Integer, Integer> e: mapCount.entrySet()){
            if(e.getValue()==2){
                rs=rs^e.getKey();
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //-
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //- Brainstorm
//        int[] nums = {1,2,1,3};
        int[] nums = {1,2,3};
        System.out.println(duplicateNumbersXOR(nums));
    }
}
