/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chinhvu
 */
public class MaximumProductSubarray_11 {

    public static int maxProductSolution(List<Integer> nums) {
        if (nums.size() == 0) {
            return 0;
        }
        if (nums.size() == 1) {
            return nums.get(0);
        }
        List<Integer> list = new ArrayList<>();
        int n = nums.size();
        Long dp[] = new Long[n];
        Long rs = 1l;

        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) < 0) {
                list.add(i);
            }
            rs *= nums.get(i);
            dp[i] = rs;
        }
        int lengthIndex = list.size();

        if (list.size() % 2 == 0) {
            return rs.intValue();
        } else {
            Long first = dp[list.get(0)];
            Long last = dp[list.get(list.size() - 1)];
            int temp = (int) Math.max(first, last);

            if (list.size() >= 1) {
                temp = (int) Math.max(rs / first, rs / last);
            }
            if (list.get(0) - 1 >= 0) {
                temp = (int) Math.max(temp, dp[list.get(0) - 1]);
            }
            if (list.get(list.size() - 1) - 1 >= 0) {
                temp = (int) Math.max(temp, dp[list.get(list.size() - 1) - 1]);
            }
            return temp;
        }
    }

    public static int maxProduct1(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int n = nums.length;
        int rs = Integer.MIN_VALUE;
        boolean isExistsZero = false;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                isExistsZero = true;
                rs = Math.max(rs, maxProductSolution(list));
                list.clear();
            } else {
                list.add(nums[i]);
            }
        }
        rs = Math.max(rs, maxProductSolution(list));

        if (isExistsZero) {
            rs = Math.max(rs, 0);
        }
        return rs;
    }

    public static int maxProduct(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        int temp = max;
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            if (nums[i] < 0) {
                int m=min;
                min = Math.min(nums[i], temp * nums[i]);
                temp = Math.max(nums[i], m * nums[i]);
//                temp = Math.max(nums[i], min * nums[i]);
                //Không nên viết predicate thế này --> Nên căn cứ vào max
//                if (temp * nums[i] < temp) {
//                    //Max cũ có thể là số 
//                    max = temp;
////                    temp = 1;
//                    continue;
//                }
                if(temp>max){
                    max=temp;
                }
//                temp = Math.max(nums[i], m * nums[i]);
            } else {
                min = Math.min(nums[i], min * nums[i]);
                //Tại sao lại là 2 htrường hợp
                //Vì có thể max (cũ) sẽ có thể là số âm
                temp = Math.max(nums[i], temp * nums[i]);
                if (nums[i] == 0) {
                    //Không điền 1
                    //--> Nếu điền 1 thì test case (-2, 0, -1) --> 1
                    //Do mình đã so sánh (1*-1 và -1) (min)
                    //Max (nếu nums[i] <0 ) --> temp=1 (reset để tính tiếp) 
                    //--> max =Math.max (temp, max) =1 (kết quả sai)
                    temp = 0;
                    min = 0;
                    continue;
                }
                //Không nên viết predicate như thế nàynày
//                if(temp*nums[i]<temp){
//                    max=temp;
////                    temp=1;
//                    continue;
//                }
                if(temp>max){
                    max=temp;
                }
            }
        }
        max=Math.max(max, temp);
        return max;
    }

    public static void main(String[] args) {
//        int nums[] = new int[]{2,3,-2,4};
//        int nums[] = new int[]{2,3,-2,4};
//        int nums[] = new int[]{-2};
//        int nums[] = new int[]{0};
//        int nums[] = new int[]{3,-1,4};
//        int nums[] = new int[]{-2,0,6};

        //
//        int nums[] = new int[]{2,3,-2,4};
        //Vị trí 0 (Phải reset)
//        int nums[] = new int[]{-2, 0, -1};
//        int nums[] = new int[]{-2,-3,7};
        //Bị sai vị trí temp
        int nums[] = new int[]{-4,-3,-2};
//        int nums[] = new int[]{2, 3, -2, 4};
//        int nums[] = new int[]{-2,3,-4};
        System.out.println(maxProduct1(nums));
        System.out.println(maxProduct(nums));
    }
}
