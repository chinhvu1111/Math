/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author chinhvu
 */
public class LargestDivisibleSubset_20 {

    //1. Tính chất bắc cầu: a%b=0, b%c=0
    //--> a%c=0
    //2, a%b=0 --> a>=b
    //Ta cần phải sắp xếp để độ dài tập con các số nhỏ trước --> Sau đó đến các số lớn
    //2.1, Không được thay đổi L[j]: vd: Không được gán L[i]=L[j] vì L[j] có thể sẽ dùng lại trong tương lai 
    public static List<Integer> largestDivisibleSubset(Integer[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<Integer>[] list = new ArrayList[n];
        List<Integer> rs = new ArrayList();
        int currentMaxLength = 1;

        //Bắt buộc phải theo order
        //Vì (1) phải được tính trước thay vì (2)
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
            list[i].add(nums[i]);

            int low = 0;
            int high = i - 1;
            while (low <= high) {
                if (nums[i] % nums[low] == 0
                        && (list[i] == null || list[i].size() < list[low].size() + 1)) {
//                    list[i]=new ArrayList<>(list[j]);
                    list[i] = new ArrayList<>(list[low]);
                    list[i].add(nums[i]);

                    currentMaxLength = Math.max(currentMaxLength, list[i].size());

                    if (currentMaxLength == list[i].size()) {
                        rs = list[i];
                    }
                }
                if (nums[i] % nums[high] == 0
                        && (list[i] == null || list[i].size() < list[high].size() + 1)) {
//                    list[i]=new ArrayList<>(list[j]);
                    list[i] = new ArrayList<>(list[high]);
                    list[i].add(nums[i]);

                    currentMaxLength = Math.max(currentMaxLength, list[i].size());

                    if (currentMaxLength == list[i].size()) {
                        rs = list[i];
                    }
                }
                low++;
                high--;
            }

        }
        if (rs.size() == 0) {
            rs.add(nums[0]);
            return rs;
        }
        return rs;
    }

    //1. Tính chất bắc cầu: a%b=0, b%c=0
    //--> a%c=0
    //2, a%b=0 --> a>=b
    //Ta cần phải sắp xếp để độ dài tập con các số nhỏ trước --> Sau đó đến các số lớn
    //2.1, Không được thay đổi L[j]: vd: Không được gán L[i]=L[j] vì L[j] có thể sẽ dùng lại trong tương lai 
    public static List<Integer> largestDivisibleSubset_1(Integer[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<Integer>[] list = new ArrayList[n];
        List<Integer> rs = new ArrayList();
        int currentMaxLength = 1;

        //Bắt buộc phải theo order
        //Vì (1) phải được tính trước thay vì (2)
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
            list[i].add(nums[i]);

            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0
                        && (list[i] == null || list[i].size() < list[j].size() + 1)) {
//                    list[i]=new ArrayList<>(list[j]);
                    list[i] = new ArrayList<>(list[j]);
                    list[i].add(nums[i]);

                    currentMaxLength = Math.max(currentMaxLength, list[i].size());

                    if (currentMaxLength == list[i].size()) {
                        rs = list[i];
                    }
                }
            }
        }
        if (rs.size() == 0) {
            rs.add(nums[0]);
            return rs;
        }
        return rs;
    }

    public static void main(String[] args) {
//        Integer arr[]=new Integer[]{4,8,10,240};
        Integer arr[] = new Integer[]{1, 2, 4, 8};

        List<Integer> list = largestDivisibleSubset(arr);

        list.forEach((t) -> {
            System.out.println(t);
        });
    }
}
