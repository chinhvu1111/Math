/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium;

import java.util.Arrays;

/**
 *
 * @author chinhvu
 */
public class ArithmeticSlices_26 {

//    public static int numberOfArithmeticSlices(int[] nums) {
//        int n=nums.length;
//        if(n==1){
//            return 0;
//        }
//        int countConsecution=0;
//        int beforeDiff=nums[1]-nums[0];
//        int rs=0;
//        
//        for(int i=0;i+1<n;i++){
//            int diff=nums[i+1]-nums[i];
//            boolean isAdapt=false;
//            if(countConsecution==0){
//                beforeDiff=diff;
//            }
//            if(diff==beforeDiff||countConsecution==0){
//                countConsecution++;
//                isAdapt=true;
//            }else{
//                beforeDiff=diff;
//                countConsecution=0;
//            }
//            if(countConsecution>=2&&isAdapt){
//                rs++;
//            }
//        }
//        return rs;
//    }
//    public static int numberOfArithmeticSlices(int[] nums) {
//        int n=nums.length;
//        int dp[][]=new int[n][n];
//        int rs=0;
//        for(int i=0;i<n;i++){
//            Arrays.fill(dp[i], Integer.MIN_VALUE);
//        }
//        
//        for(int i=1;i<=n;i++){
//            int beforeRs=rs;
//            for(int j=0;i+j<n;j++){
//                boolean isAdap=false;
//                
//                if(i==1){
//                    dp[j][j+i]=nums[i+j]-nums[j];
//                    continue;
//                }
//                //Nếu trong trường hợp tất cả các vòng for đều có 1 predicate VD: ||i==1
//                //--> Tách ra trường hợp riêng xử lý --> Dễ theo dõi hơn
//                //Và trường hợp riêng này --> continue được thì càng tốt
//                if(nums[j+1]-nums[j]==dp[j+1][j+i]&&dp[j+1][j+i]!=Integer.MIN_VALUE){
//                    dp[j][j+i]=dp[j+1][j+i];
//                    isAdap=true;
//                }
////                if(nums[j+i]-nums[j+i-1]==dp[j][j+i-1]){
////                    dp[j][j+i]=dp[j][j+i-1];
////                    isAdap=true;
////                }
//                if(isAdap&&i>=2){
//                    rs++;
//                }
//            }
//            if(beforeRs==rs&&i!=1){
//                break;
//            }
//        }
//        return rs;
//    }
    public static int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int countConsecution = 0;
        int beforeDiff = nums[1] - nums[0];
        int rs = 0;

        for (int i = 0; i + 1 < n; i++) {
            int diff = nums[i + 1] - nums[i];
//            boolean isAdapt=false;

            if (countConsecution == 0) {
                beforeDiff = diff;
            }
            if (diff == beforeDiff || countConsecution == 0) {
                countConsecution++;
//                isAdapt=true;
            } else {
                beforeDiff = diff;
                countConsecution = 1;
            }
            if (countConsecution >= 2) {
                rs += countConsecution - 1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        int arr[] = new int[]{1, 3, 5, 7, 9};
        int arr[] = new int[]{1, 2, 3, 4};
//        int arr[]=new int[]{1,3};
//        int arr[]=new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
//        int arr[] = new int[]{1, 3, 5, 7, 9, 1, 2, 3, 4, 3, 2, 5, 6, 7};

        System.out.println("");
        //For ta cần phải xác định trình tự: index giảm/ index tăng
        //VD: nums[i + 1] - nums[i];
        //Bài này ta có 2 cách xử lý:
        //Method 1: Làm để xử lý vấn đề, tư duy như sau:
        //1,Vì độ dái chuỗi đếm chí có thể là (1,2,3 --> n)
        //Ta xét từng trường hợp --> Trường (sau) --> Tính theo trường hợp trước
        //VD: (3) tính theo (2)
        //1.1,Để tăng tốc độ: Nếu (2) Không có trường hợp nào 
        //--> Break, không tính (3) nữa
        //VD: Check rs xem có thay đổi không bằng 1 biến tạm (beforeRs)
        //2, Vì chuỗi là chuỗi cách đều nhau --> Ta không cần xét 2 đầu (i+1/j-1)
        //--> Xét 1 đầu là được
        //VD: (i+1)
        //2.1, Chú ý: các trường hợp đặc biệt dp[j][j+i]=0 có thể là
        //VD1: (1,1,1,1) --> Các số bằng nhau
        //VD2: (3,4,6,7) --> Các số chả liên quan gì đến nhau
        //--> Tính dp[j][j+i]=dp[j][j+i-1];
        //isAdap=true;
        //Xét (i>=2&&isAdap) --> Vẫn rs++ :==> Mặc dù sai
        // --> Nên ta cần khởi tạo lại giá trị ban đầu =MAX_INTEGER
        
        //Bài này cần phải chú ý:
        //Các trường hợp giữa lúc tăng/ giảm --> Trở nên không theo quy luật nữa --> reset như thế nào?
        //Quy lụât của bài như sau:
        //Nếu độ dài chuỗi >=3: Ta thêm 1 trường hợp (chuỗi con =3) --> rs++
        //Nếu độ dài chuỗi >=4: Ta thêm 2 trường hợp (chuỗi con =3) (=4) --> rs+=2
        //Nếu độ dài chuỗi >=5: Ta thêm 2 trường hợp (chuỗi con =3) (=4) (=5) --> rs+=3
        //VD: (1,2,3,4) --> Khi tăng 1 đơn vị của countConsecution++
        //--> Sẽ xuất hiện thêm 1 chuỗi con có độ dài của "Mỗi" loại chuỗi từ (3 --> countConsecution+1)
        //Chốt lại sẽ là: rs+=countConsecution-1
        //VD: (3) --> Gắn với index=2 (Do ta đang xét cặp)
        //(3) + thêm 1 --> (index-1)
        //(4) --> index=3 --> index-1= 3-1= (2): (1 của 3) + (1 của 4)
        
        System.out.println(numberOfArithmeticSlices(arr));
    }
}
