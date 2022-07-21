package interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class E90_FindAllDuplicatesInAnArray {

    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> rs=new LinkedList<>();
//        HashSet<Integer> s=new HashSet<>();
        boolean[] s=new boolean[10001];

        for(int i=0;i<nums.length;i++){
            if(s[nums[i]]){
                rs.add(nums[i]);
            }else{
                s[nums[i]]=true;
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{4,3,2,7,8,2,3,1};
        System.out.println(findDuplicates(arr));
    }
}
