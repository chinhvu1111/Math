/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium_dynamic;

import java.util.Arrays;

/**
 *
 * @author chinhvu
 */
public class JumpGame_3 {
    
    public static class Node{
        int index;
        int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public Node() {
        }
        
    }
    //Chuyển sang việc dùng đồ thị
//    public static boolean canJump(int[] nums) {
//        LinkedList<Node> queue=new LinkedList<>();
//        Node firstNode=new Node();
//        firstNode.index=0;
//        firstNode.value=nums[0];
//        int n=nums.length;
//        
//        queue.push(firstNode);
//        int max=0;
//        
//        while(queue.size()!=0){
//            Node node=queue.pop();
//            int currentIndex=node.index;
//            int value=node.value;
//            
//            if(currentIndex+value>=n-1){
//                return true;
//            }
//            for(int i=1;i<=value;i++){
//                if(nums[currentIndex+i]+currentIndex<=max){
//                    continue;
//                }
//                Node n1=new Node(currentIndex+i, nums[currentIndex+i]);
//                queue.push(n1);
//            }
//            max=Math.max(currentIndex+nums[currentIndex], max);
//        }
//        return false;
//    }
    
    
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        int dp[] = new int[n];

        //Cần tối ưu code
        Arrays.fill(dp, 100000);
        int max=0;
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            if(dp[i]==100000||i+nums[i]<max){
                continue;
            }
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < n&&max<i+j+nums[i+j]) {
                    dp[i + j] = Math.min(dp[i] + 1, dp[i + j]);
                    max=Math.max(max, i+j+nums[i+j]);
                }
            }
            max=Math.max(max, i+nums[i]);
            if(max>=n-1){
                return true;
            }
        }
        return dp[n - 1]!=100000;
    }
    
    //Khi làm 1 lượt --> Nhận ra cách đơn giản nhất là dùng (greedy)
    public static boolean canJumGreedy(int nums[]){
        int max=0;
        int n=nums.length;
        
        for(int i=0;i<nums.length;i++){
            if(i>max){
                return false;
            }
            if(max<nums[i]+i){
                max=nums[i]+i;
            }
            if(max>=n-1){
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
//        int arr[]=new int[]{2,3,1,1,4};
        int arr[]=new int[]{1,3,2};
//        int arr[]=new int[]{1,1,2,2,0,1,1};
        System.out.println(canJump(arr));
    }
}
