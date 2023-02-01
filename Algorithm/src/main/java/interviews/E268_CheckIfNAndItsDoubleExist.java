package interviews;

import java.util.HashMap;
import java.util.HashSet;

public class E268_CheckIfNAndItsDoubleExist {

    public static boolean checkIfExist(int[] arr) {
        HashMap<Integer, Integer> values=new HashMap<>();

        for(int val:arr){
            values.put(val, values.getOrDefault(val,0)+1);
        }
        for(int val:arr){
            if(val!=0
                    &&((val%2==0&&values.containsKey(val/2))||values.containsKey(val*2))){
                return true;
            }
            if(val==0&&values.get(0)>1){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{10,2,5,3};
        //#Reference
        //1347. Minimum Number of Steps to Make Two Strings Anagram
        //540. Single Element in a Sorted Array
        //2092. Find All People With Secret
        //2395. Find Subarrays With Equal Sum
    }
}
