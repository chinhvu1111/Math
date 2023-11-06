package E1_hashmap;

import java.util.HashMap;

public class E11_LargestUniqueNumber {

    public static int largestUniqueNumber(int[] nums) {
        HashMap<Integer, Integer> mapCount=new HashMap<>();

        for (int num : nums) {
            mapCount.put(num, mapCount.getOrDefault(num, 0) + 1);
        }
        int rs=Integer.MIN_VALUE;
        for(int key: mapCount.keySet()){
            if(mapCount.get(key)==1){
                rs=Math.max(rs, key);
            }
        }
        return rs==Integer.MIN_VALUE?-1:rs;
    }

    public static void main(String[] args) {
        int[] nums = {5,7,3,9,4,9,8,3,1};
        //#Reference:
        //1491. Average Salary Excluding the Minimum and Maximum Salary
        //395. Longest Substring with At Least K Repeating Characters
        //1418. Display Table of Food Orders in a Restaurant
    }
}
