/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author chinhvu
 */
public class Permutations_30 {
    public static boolean visited[];
    public static LinkedList<Integer> temp;
    public static List<List<Integer>> rs;
    
    public static void solution(int nums[],int l){
        if(l>=visited.length){
            rs.add(new LinkedList<>(temp));
            return;
        }
        for(int i=0;i<visited.length;i++){
            if(!visited[i]){
                visited[i]=true;
                temp.add(nums[i]);
                solution(nums, l+1);
                visited[i]=false;
                temp.pollLast();
            }
        }
    }
    
    public static void solution1(int l, int r, int nums[]){
        if(l==r){
            List<Integer> t=new ArrayList<>();
            for(int i=0;i<nums.length;i++){
                t.add(nums[i]);
            }
            rs.add(t);
            return;
        }
        for(int i=l;i<r;i++){
            swap(nums ,l, i);
            solution1(l+1,r,nums);
            swap(nums ,l, i);
        }
    }
    
    public static void swap(int nums[], int i, int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    
    public static List<List<Integer>> permute(int[] nums) {
        int n=nums.length;
        visited=new boolean[n];
        rs=new LinkedList<>();
        temp=new LinkedList<>();
//        solution(nums, 0);
        solution1(0, nums.length, nums);
        return rs;
    }
    
    public static void main(String[] args) {
        int arr[]=new int[]{1,2,3};
        permute(arr);
    }
}
