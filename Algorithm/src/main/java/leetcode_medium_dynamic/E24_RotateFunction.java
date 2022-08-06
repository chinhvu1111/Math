/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium_dynamic;

/**
 *
 * @author chinhvu
 */
public class E24_RotateFunction {
    
    public static int maxRotateFunction(int[] nums) {
        int n=nums.length;
        int sum= 0;
//        int dp[]=new int[n];
        int rs=0;

        //Bỏ được 1 loop
        for(int i=0;i<n;i++){
//            dp[i]=sum-nums[i];
            sum+=nums[i];
            rs+=i*nums[i];
        }
        int currentIndex=0;
        int temp=rs;
        for(int i=0;i<n;i++){
            int lastIndex=getRotateIndex(currentIndex-1, n);
//            dp[lastIndex]=sum-nums[lastIndex];
//            temp=temp+(sum-nums[lastIndex])- (n-1)*nums[lastIndex];
            temp=temp+sum - n*nums[lastIndex];
            rs=Math.max(rs, temp);

            currentIndex=lastIndex;
        }
        return rs;
    }
    private static int getRotateIndex(int currentIndex, int n){
        if(currentIndex-1<0){
            currentIndex=n-1;
        }
        return currentIndex;
    }
    
    public static void main(String[] args) {
        int arr[]=new int[]{4,3,2,6};
        //Bài này ta sẽ tập trung vào tối ưu
        //Vì đề bài là ta sẽ xoay để tính tổng max nhất
        //Cách tư duy như sau:
        //1, Mỗi lần xuay ta sẽ không tính lại tất cả các bước ban đầu
        //--> Ta chỉ tính giá trị ban đầu --> Tính khoảng cách giữa các giá trị
        //1.1, Cái thứ 2 cần biết:
        //VD: a(0),b(1),c(2),d(3)
        //Dù ta xoay thế nào thì cái ta cần là giá trị của các phần tử
        //--> ta chỉ quan tâm đến các index của nó sau khi xoay
        //0,1,2,3
        //--> 3,1,2,0
        //--> 1,2,0,3
        //Khoảng giữa các tổng
        //VD: 
        //0*a+1*b+2*c+3*d
        //0*d+1*a+2*b+3*c
        //--> different= (a+b+c)-3*d
        //RULE: diffrent= (SUM-x) -3x = (SUM-x) -(n-1)*x
        //different=SUM-n*x
        //2,--> Việc còn laị là xác định index bằng method reset:
        //--> Tức là (index--) --> (Reset ->(n-1) khi n<0 )
        System.out.println(maxRotateFunction(arr));
    }
}
