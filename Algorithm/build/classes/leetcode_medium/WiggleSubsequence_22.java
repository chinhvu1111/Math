/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium;

import java.util.Stack;

/**
 *
 * @author chinhvu
 */
public class WiggleSubsequence_22 {

    public static int wiggleMaxLength(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int n = nums.length;
        int dp[][] = new int[n][2];

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
        }
        int rs = 1;

        for (int i = 0; i < n; i++) {
            int incre = Integer.MIN_VALUE;
            int decre = Integer.MIN_VALUE;

            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    incre = Math.max(incre, dp[j][1] + 1);
                }
                if (nums[i] < nums[j]) {
                    decre = Math.max(decre, dp[j][0] + 1);
                }
            }
            dp[i][0] = Math.max(dp[i][0], incre);
            dp[i][1] = Math.max(dp[i][1], decre);
            rs = Math.max(rs, Math.max(incre, decre));
        }
        return rs;
    }

    //Chú ý: ở đây chúng ta không cần pop ra
    //--> Cái ta quan tâm là độ dài --> Chỉ cần 1 biến length --> Tăng khi cần
    //VD: if() --> length++
    //Cái mà ta quan tâm chỉ là trạng thái đằng trước đó bao gồm: status=1/2 (0 chỉ xét ban đầu) + lastElement (Để so sánh)
    //Có các trường hợp như sau:
    //1, Dãy đang ở trường hợp tăng
    //VD: (1,2,4) --> ta tìm thấy 9 --> Ta có thể thay (4=9) vì cái ta cần là dãy giảm (9>4) nên "việc tìm các số" <9 
    //đa dạng hơn/nhiều cases hơn so với (<4) + độ dài giữ nguyên
    //Tương tự với đang giảm --> ta sẽ lấy số nhỏ nhất (lastElement>nums[i]) --> Lấy nums[i]
    //1.1, Ngược lại ở các trường hợp trên --> add thẳng vào + (cập nhập status)
    //2, Case đặc biệt có không:
    //VD: a,b,c,d (a<b, b>c)
    //    a,b,c,d (a<,c c>d)
    //--> Lấy trường hợp nào ==> Tương tự với: (a,b,c,d): (a<b,b>d)
    
    //3, Cách code bỏ qua trường hợp đầu mới
    //--> Không cần thực hiện lệnh check(first) nhiều lần
    //If(non-first) continue;
    //code: first
    //--> Nếu xảy ra trường hợp next --> Luôn bỏ qua first
    //
    //4, Đôi khi ta phải hi sinh độ phức tạp code/VD: lồng của if
    //+ thêm số câu lệnh thực hiện
    //VD: flat if <=> duplicate if (Tránh lồng if với nhau)
    //VD: như đoạn check lastElement phần (stack.size()>1)
    //--> ta có thể giảm số line bằng (lồng if + viết lệnh add() 1 lần thôi)
    public static int wiggleMaxLength1(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int status = 0;
        int rs = 1;
        stack.add(nums[0]);

        for (int i = 1; i < n; i++) {
            if(stack.size()>1){
                int lastElement=stack.lastElement();
                
                if(status==1&&nums[i]>lastElement){
                    stack.add(nums[i]);
                }else if(status==1&&nums[i]<lastElement){
                    status=2;
                    stack.add(nums[i]);
                    rs++;
                }
                if(status==2&&nums[i]<lastElement){
                    stack.add(nums[i]);
                }else if(status==2&&nums[i]>lastElement){
                    status=1;
                    stack.add(nums[i]);
                    rs++;
                }
                continue;
            }
            
            if (nums[i] > nums[0]) {
                status = 1;
                stack.add(nums[i]);
                rs++;
            } else if (nums[i] < nums[0]) {
                status = 2;
                stack.add(nums[i]);
                rs++;
            } else {
                status = 0;
            }
        }

        return rs;
    }

    public static void main(String[] args) {
//        int arr[] = new int[]{0, 0};
        int arr[] = new int[]{1,17,5,10,13,15,10,5,16,8};
        System.out.println(wiggleMaxLength(arr));
        System.out.println(wiggleMaxLength1(arr));
    }
}
