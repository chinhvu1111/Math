package interviews;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class E91_FindAllNumbersDisappearedInAnArray {

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> rs=new LinkedList<>();

        for(int i=0;i<nums.length;i++){
            int value=Math.abs(nums[i]);

            if(nums[value-1]>=0){
                nums[value-1]*=-1;
            }
        }
        for(int i=1;i<=nums.length;i++){
            if(nums[i-1]>=0){
                rs.add(i);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        int nums[]=new int[]{4,3,2,7,8,2,3,1};
        int nums[]=new int[]{1, 1};

        System.out.println(findDisappearedNumbers(nums));
        //Bài này ta tư duy như sau:
        //1, Ta tư duy giống bài trước
        //1.1, Chỉ có thể tối ưu 1 chút ở chỗ:
        //abs ==> Chuyển thành if else.
    }
}
