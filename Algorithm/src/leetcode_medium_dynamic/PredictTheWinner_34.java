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
public class PredictTheWinner_34 {

    //Tại sao không dùng boolean
    //Vì boolean chỉ có 2 giá trị false/true
    //Và mặc định của nó đã là false 
    //--> Ta không thể thể hiện rằng việc chọn hiện tại sẽ cho kết quả THUA
    public static int dp[][];
    public static int sum;

//    public static boolean solution(int posititon, int nums[], int left, boolean isLeft){
//        boolean currentResult=false;
//        if(posititon==1<<(nums.length-1)){
//            return left>=sum-left;
//        }
//        if(dp[posititon]==1){
//            return true;
//        }
//        if(dp[posititon]==-1){
//            return false;
//        }
//        
//        for(int i=0;i<nums.length;i++){
//            if((posititon&1<<i)!=0){
//                continue;
//            }
//            int nextLeft=left;
//            
//            if(isLeft){
//                nextLeft=left+nums[i];
//            }
//            boolean nextsult=
//                    solution(posititon|1<<i, nums, nextLeft, !isLeft);
//            
//            dp[posititon|1<<i]=(nextsult==true)?1:-1;
//            if(!nextsult){
//                currentResult=true;
//                break;
//            }
//        }
//        dp[posititon]=(currentResult==true)?1:-1;
//        return currentResult;
//    }
    public static boolean solution(int low, int high, int nums[], int left, int right, boolean isLeft) {
        //Kỹ năng chạy thử để tìm kết quả bài toán --> Viết lên đầu recursion để debug
        //Và in kết quả mong muốn để xem kết quả Brute force
//        if(low>high&&left>right){
//            System.out.println("");
//        }
//        if(low>high){
//            System.out.println("");
//        }
        //Case cuối này nó chạy ra (ngoài kích thước mảng)
        //--> Sẽ tính dựa trên isLeft --> Nó có thể chẵn lẻ
//        if (low > high) {
//            //Phần này là phần hay/dễ sai nhất
//            //Nếu isLeft=fasle --> Chứng trỏ trước đó đang chọn left
//            //Mục tiêu: --> Return cho kết quả trước đó nhận là true ( Rs= !(Kết quả hiện tại của next Recursion)
//            //--> Ta phải return (false) --> Chứ không phải return (true)
//
//            //Vì ta đang xét điều kiện trên left>=sum-left
//            //Left tức là 1 trường hợp cụ thể --> nếu isLeft: return true
//            //Nếu mà right thì sao? --> Ở đấy kết quả return của chung (right và left) --> Đang hoàn toàn phụ thụôc vào kết quả 
//            //Của left --> (Điều này là sai)
//            //VD: Nếu trước đó là left mà --> Ta sẽ so sánh tổng left>=sum-left
//            //Trước đó là right --> Ta phải so sánh right --> Mới cho kết quả đúng của right
//            //***** CHÚ Ý: (KHÓ)--> Tập trung vào kết quả của recursion trước đó
//            //MAP 1:1: Trường hợp nào sẽ mong muốn kết quả như thế nào?
//            if (!isLeft && left >= sum - left) {
//                return false;
//            }
//            if (isLeft && left < sum - left) {
//                return false;
//            }
////            if(isLeft&&left>=sum-left){
////                return true;
////            }
//            return true;
//        }
        //- Ở đấy nếu ta so sánh ngược lại --> Gây sai vì left thực tế chưa được cộng hết phần tử
        //- Nó chí đúng khi left chưa cộng hết --> Mà đã lớn hơn
        //Trường hợp chưa cộng hết mà < sum-left --> Vô nghĩa vì:
        //- Left có thể tăng lên
        //- sum -left --> Có thể giảm đi sau sự tăng lên của left
        //--> Vậy thì ta sẽ thêm (1 chiều đối xứng cộng của left là right)

        //Câu này diễn giải ra:
        //Nếu ta ở bên phải nhưng trái (left> phần còn lại)
        //-->  Hiện tại ta không thể chiến thắng
        //...................SAI
//        if (!isLeft && left >= sum - left) {
//            System.out.println("Greater than "+left+" "+right);
//            return false;
//        }
        if (left >= sum - left) {
//            System.out.println("Greater than "+left+" "+right);
            return false;
        }
        //Phải có isleft để biết mình đang check cái gì
        //Nếu ta ở bên trái mà (right> Phần còn lại)
        //--> Hiện tại ta không thể chiến thắng
        //===> Viết kiểu này so với việc viết ngược lại isLeft && left >= sum - left --> return true
        //---> Là như nhau
        //..............SAI
//        if (isLeft && right > sum - right) {
//            System.out.println("Less than "+left+" "+right);
//            return false;
//        }
        if (right > sum - right) {
//            System.out.println("Less than "+left+" "+right);
            return false;
        }
        //Sau khi dùng kỹ năng kiểm tra vét cạn
        //--> Có vẻ 2 điều kiện if trên có vẻ sai trong case nào đó
        //Hoặc là với điều kiện này (low>high&&left>right) sẽ luôn cùng lúc sai
        //--> Tại sao
        //Câu trả lời rằng:
        //--> Kết quả ở đâu return tại đó nó sẽ sai nếu gặp case như sau:
        //1.1, Sau khi công left --> Left-1 (Đi vào method recursion) 
        //(Mặc dù left-1 > sum - left-1) --> Do ta đang xét điều kiện (!isLeft && left >= sum - left)
        //--> Tức là đằng trước phải là right thì mới return false
        //==> Ta vẫn không return ngay lúc đó
        //1.2, Ta sẽ đến right chọn --> Right-1 --> Đi sâu vào Recursion --> Thì (!isLeft && left >= sum - left) return false
        //Right --> Có thể tìm thấy (Dẫn đến kết quả return false của LEFT --> Chuyển thành của RIGHT) --> SAI
        //KINH NGHIỆM:
        //Với những bài optimize: --> Kết quả đâu return đó không lòng vòng

//        if(low>high){
//            return true;
//        }
//        if(low>high&&isLeft&&left<right){
//            return false;
//        }
//        if(low>high&&!isLeft&&right<left){
//            return false;
//        }
        //Đối với việc truy vết thì luôn luôn == nhau
        //---> Việc bằng nhau như thế này: ---> Là luôn đúng trừ khi ta sai logic quy hoạch động như ở đây
        //Ta ở đây đáng nhẽ phải xác định: 
        //- dp[low][high] có ý nghĩa là gì?
        //- Khi return thì có nghĩa là gì?
        //--> Ở đây return như thế này là hoàn toàn sai
        //==> Như ở đây ta cần phải thêm 1 prdedicate nữa thì mới đúng được
        //---> (VẪN SAI) vì left/right đóng vai trò như nhau --> Ta đang xét sole
        //---> Cả 2 phải return cùng 1 giá trị (false/true)
        //==> Việc chia dp[i][j]=1/dp[i][j]=-1 --> Để xét ta phải phân rõ các trường hợp ra
        //**Phân bằng cách dùng isLeft
        if (left>=right&&dp[low][high] == 1) {
            return true;
        }
        if (right>left&&dp[low][high] == -1) {
            return true;
        }

        int nextLeft = left;
        int nextRight = right;

        //Case 1
        if (isLeft) {
            nextLeft = left + nums[low];
        } else {
            nextRight = right + nums[low];
        }
        boolean nextRs = solution(low + 1, high, nums, nextLeft, nextRight, !isLeft);
        //Thêm bộ nhớ để không phải check (low + 1<n)
//        if (low < nums.length) {
//            dp[low + 1][high] = (nextRs == true) ? 1 : -1;
//        }
        if (!nextRs&&isLeft) {
            dp[low][high] = 1;
            return true;
        }
        if (!nextRs&&!isLeft) {
            dp[low][high] = -1;
            return true;
        }

        //Case 2
        if (isLeft) {
            nextLeft = left + nums[high];
        } else {
            nextRight = right + nums[high];
        }
//        else{
//            //Với những bài dạng như thế này nhớ chú ý
//            //Dùng chung biến giữa 2 Recursion --> Phải gán lại nó
//            //Không nó sẽ dùng lại kết quả bên trên --> Sai
//            nextLeft=left;
//        }

        nextRs = solution(low, high - 1, nums, nextLeft, nextRight, !isLeft);
//        if (high >= 1) {
//            dp[low][high - 1] = (nextRs == true) ? 1 : -1;
//        }
        if (!nextRs&&isLeft) {
            dp[low][high] = 1;
            return true;
        }
        if (!nextRs&&!isLeft) {
            dp[low][high] = -1;
            return true;
        }
        return false;
    }

    public static boolean PredictTheWinner1(int []nums){
        int n=nums.length;
        int dp[][]=new int[n][n];
        int sum=0;
        
        for(int i=0;i<n;i++){
            dp[i][i]=nums[i];
            sum+=nums[i];
        }
        
        for(int i=2;i<=n;i++){
            for(int j=0;j+i-1<n;j++){
                int k=i+j-1;
                int max=0;
                int case1=Integer.MAX_VALUE;
                int case2=Integer.MAX_VALUE;
                if(k>=2){
                    case1=dp[j][k-2]+nums[k];
                }
                if(j+1<n&&k>=1){
                    case1=Math.min(dp[j+1][k-1]+nums[k], case1);
                }
                if(j+2<n){
                    case2=nums[j]+dp[j+2][k];
                }
                if(j+1<n&&k>=1){
                    case2=Math.min(dp[j+1][k-1]+nums[j], case2);
                }
                
//                if(j+1<n){
//                    max=Math.min(dp[j][k-2], dp[i+1][k-1])+nums[k];
//                }
//                if(k-1>=0){
//                    max=Math.max(max, dp[j][k-1]);
//                }
                dp[j][k]=Math.max(case1, case2);
            }
        }
        System.out.println(dp[0][n-1]>=sum-dp[0][n-1]);
        return dp[0][n-1]>=sum-dp[0][n-1];
    }
    
    public boolean PredictTheWinnerOptimized2(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
       
        for (int i=0; i<n; i++) {
            dp[i][i] = nums[i];
        }
        
        for (int len = 1; len < n; len ++) {
            for (int i=0; i+len < n; i++) {
                dp[i][i+len] = Math.max(nums[i] - dp[i+1][i+len], 
                                        nums[i+len] - dp[i][i+len-1]);
            }
        }
        return dp[0][n-1] >= 0;
    }
    
    public static boolean PredictTheWinner(int[] nums) {
        sum = Arrays.stream(nums).sum();
        int n = nums.length;
        if (n == 1) {
            return true;
        }
        dp = new int[n + 1][n];
        boolean rs = solution(0, n - 1, nums, 0, 0, true);
        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,5,233,7};
//        int arr[] = new int[]{1, 5, 2};
//        int arr[] = new int[]{0};
//        int arr[] = new int[]{1,567,1,1,99,100};
//        int arr[] = new int[]{1, 1, 1};
//        int arr[] = new int[]{2, 4, 55, 6, 8};
//        int arr[] = new int[]{10,10,2,2,454,2,2};
//        int arr[] = new int[]{10, 17, 11, 16, 17, 9, 14, 17, 18, 13, 11, 4, 17, 18, 15, 3, 13, 10};
//        int arr[] = new int[]{1,1};
//        int arr[] = new int[]{1,5,2,4,6};
        int arr[] = new int[]{3606449,6,5,9,452429,7,9580316,9857582,8514433,9,6,6614512,753594,5474165,4,2697293,8,7,1};
//        int arr[] = new int[]{2,4,55,6,8};
        System.out.println(PredictTheWinner(arr));
        System.out.println(PredictTheWinner1(arr));
    }
}
