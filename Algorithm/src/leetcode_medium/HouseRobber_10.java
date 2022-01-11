/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium;

/**
 *
 * @author chinhvu
 */
public class HouseRobber_10 {
    
//    public static int rob(int[] nums) {
//        int n=nums.length;
//        int dp[]=new int[n];
//        int max=0;
//        
//        int rs=dp[0]=nums[0];
//        
//        //Nếu tìm max cả đoạn
//        //Ta có thể lưu đoạn đó vào vị trí đếm trước
//        //VD: Ta chỉ quan tâm max trước đó (Không tính điểm kề) --> Ta chỉ cần lưu max (của chuối)
//        //Không kề đã chạy qua là xong
//        for(int i=1;i<n;i++){
//            int temp=0;
//            
//            if(i>=2){
//                max=Math.max(max, dp[i-2]);
//            }
////            for(int j=i-2;j>=0;j--){
////                temp=Math.max(dp[j], temp);
////            }
//            dp[i]=max+nums[i];
//            rs=Math.max(dp[i], rs);
//        }
//        return rs;
//    }
    
    public static int rob(int[] nums) {
        int n=nums.length;
        int dp[]=new int[3];
        int max=0;
        
        int rs=dp[0]=nums[0];
        
        //Nếu tìm max cả đoạn
        //Ta có thể lưu đoạn đó vào vị trí đếm trước
        //VD: Ta chỉ quan tâm max trước đó (Không tính điểm kề) --> Ta chỉ cần lưu max (của chuối)
        //Không kề đã chạy qua là xong
        for(int i=1;i<n;i++){
            int temp=0;
            
            if(i>=2){
                max=Math.max(max, dp[0]);
            }
            dp[0]=Math.max(dp[0], dp[1]);
//            for(int j=i-2;j>=0;j--){
//                temp=Math.max(dp[j], temp);
//            }
            dp[1]=Math.max(max+nums[i], dp[1]);
            rs=Math.max(dp[1], rs);
        }
        return rs;
    }
    
    public static int rob1(int[] nums, int start, int end) {
        int n=nums.length;
        int dp[]=new int[n];
        int max=0;
        
        if(n==1){
            return nums[0];
        }
        int rs=dp[start]=nums[start];
        
        //Nếu tìm max cả đoạn
        //Ta có thể lưu đoạn đó vào vị trí đếm trước
        //VD: Ta chỉ quan tâm max trước đó (Không tính điểm kề) --> Ta chỉ cần lưu max (của chuối)
        //Không kề đã chạy qua là xong
        for(int i=start+1;i<end;i++){
            int temp=0;
            
            if(i>=2){
                max=Math.max(max, dp[i-2]);
            }
//            for(int j=i-2;j>=0;j--){
//                temp=Math.max(dp[j], temp);
//            }
            dp[i]=max+nums[i];
            rs=Math.max(dp[i], rs);
        }
        return rs;
    }
    
    public static int rob2(
            int[] nums, 
            int start, 
            int end, 
            int index) {
        
        int n=nums.length;
        int dp[]=new int[3];
        int max=0;
        
        if(n==1){
            return nums[0];
        }
        int rs=dp[0]=nums[start];
        
        //Nếu tìm max cả đoạn
        //Ta có thể lưu đoạn đó vào vị trí đếm trước
        //VD: Ta chỉ quan tâm max trước đó (Không tính điểm kề) --> Ta chỉ cần lưu max (của chuối)
        //Không kề đã chạy qua là xong
        for(int i=start+1;i<end;i++){
            int temp=0;
            
            if(i>=index){
                max=Math.max(max, dp[0]);
            }
            dp[0]=Math.max(dp[0], dp[1]);
//            for(int j=i-2;j>=0;j--){
//                temp=Math.max(dp[j], temp);
//            }
            dp[1]=Math.max(max+nums[i], dp[1]);
            rs=Math.max(dp[1], rs);
        }
        return rs;
    }
    
    public static void main(String[] args) {
//        int arr[]=new int[]{1,2,3,1};
//        int arr[]=new int[]{1,2};
        int arr[]=new int[]{2,3,2};
        int n=arr.length;
        System.out.println(
                rob2(arr, 1, n, 3));
        System.out.println(
                rob2(arr, 0, n-1, 2));
    }
}
