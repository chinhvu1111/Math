/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E1_leetcode_medium_dynamic;

/**
 *
 * @author chinhvu
 */
public class E23_CombinationSum {
    
//    public static int combinationSum4(int[] nums, int target) {
//        Arrays.sort(nums);
//        int n=nums.length;
//        int max=Arrays.stream(nums).max().getAsInt();
//        int dp[]=new int[max+1];
//        int rs=0;
//        for(int i=0;i<n;i++){
//            dp[nums[i]]=1;
//        }
//        
//        for(int i=1;i<n;i++){
//            int temp=0;
//            
//            for(int j=i-1;j>=0;j--){
//                temp+=dp[nums[i]-nums[j]];
//            }
//            dp[nums[i]]+=temp;
//        }
//        for(int i=0;i<n;i++){
//            if(target>=nums[i]&&target-nums[i]<=max){
//                rs+=dp[target-nums[i]];
//            }
//        }
//        return rs;
//    }
    
    public static int combinationSum4(int[] nums, int target) {
        // int max=Arrays.stream(nums).max().getAsInt();
        int n=nums.length;
        int dp[]=new int[target+1];
        dp[0]=1;
        
        //Giảm số loop mà chỉ đẩy câu lệnh đó thực hiện vào (loop) khác --> Không tăng tốc độ
        //Vì tính trung bình ra ta thực hiện số câu lệnh như nhau --> Chẳng qua ta đẩy vào loop khác thực hiện mà thôi
        for(int i=0;i<n;i++){
            if(nums[i]<=target) dp[nums[i]]=1;
        }

        for(int i=2;i<=target;i++){
            int temp=0;
            for(int j=0;j<n;j++){

                if(i>nums[j]){
                    temp+=dp[i-nums[j]];
                }
            }
            dp[i]+=temp;
        }
        return dp[target];
    }
    
    public static void main(String[] args) {
//        int arr[]=new int[]{1,2,3};
        int arr[]=new int[]{9};
        int target=4;
        System.out.println(combinationSum4(arr, target));
    }
}
