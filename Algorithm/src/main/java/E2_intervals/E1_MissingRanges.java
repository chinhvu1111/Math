package E2_intervals;

import java.util.ArrayList;
import java.util.List;

public class E1_MissingRanges {

    public static List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> rs=new ArrayList<>();

        for(int i=0;i<nums.length;i++){
            List<Integer> list=new ArrayList<>();
            if(nums[i]-1>=lower){
                list.add(lower);
                list.add(nums[i]-1);
                rs.add(list);
            }
            lower=nums[i]+1;
        }
        if(lower<=upper){
            List<Integer> list=new ArrayList<>();
            list.add(lower);
            list.add(upper);
            rs.add(list);
        }
        return rs;
    }

    public static void main(String[] args) {
        //#Reference:
        //228. Summary Ranges
        //2655. Find Maximal Uncovered Ranges
    }
}
