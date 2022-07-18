package interviews;

import java.util.Arrays;

public class E79_MinimumOperationsToReduceXToZero {
    public static int minOperations(int[] nums, int x) {
        int left=0;
        int sum= 0;

        for(Integer i: nums) sum+=i;
        int target=sum-x;

        if(target<0){
            return -1;
        }
        int n=nums.length;
        int currentSum=0;
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            currentSum+=nums[i];

            while (currentSum>target){
                currentSum-=nums[left++];
            }
            if(currentSum==target){
                rs=Math.min(rs, n-(i-left+1));
            }
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    public static int minOperationsOptimize(int[] nums, int x) {
        int left=0;
        int sum= 0;

        for(Integer i: nums) sum+=i;
        int target=sum-x;

        if(target==sum){
            return nums.length;
        }
        int n=nums.length;
        int currentSum=0;
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            currentSum+=nums[i];

            while (currentSum>target){
                currentSum-=nums[left++];
            }
            if(currentSum==target){
                rs=Math.min(rs, n-(i-left+1));
            }
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{1,1,4,2,3};
//        int arr[]=new int[]{3,2,20,1,1,3};
        int x=5;
//        int x=10;
        System.out.println(minOperations(arr, x));
        System.out.println();
        //Bài này ta tư duy như sau:
        //1, Với các dạng bài kiểu prefix --> Ta cần tư duy đảo ngược
        //1.1, Remove left/right ít nhất (Số lượng) --> Sao cho tổng số sum(remove) = x
        //--> Số lượng element <=> MIN
        //--> Quy về bài toán :
        //** Syb-array có độ dài lớn nhất --> Có sum=target
        //--> Quy về slice window
        //1,2, Bài toán tối ưu:
        //---> Thêm điều kiện (left<=right) --> Tăng được thành 60% win
        //https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/discuss/2139431/JAVA-TC%3A-100-beats-MC%3A-95-beat-sliding-window
        //
    }
}
