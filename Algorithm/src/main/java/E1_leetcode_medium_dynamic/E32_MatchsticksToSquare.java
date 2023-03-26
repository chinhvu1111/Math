/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E1_leetcode_medium_dynamic;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author chinhvu
 */
public class E32_MatchsticksToSquare {

//    public static boolean makesquare(int[] matchsticks) {
//        //1,1,2,2,2
//        //3,3,3,3,4
//        int sum=0;
//        int edge;
//        int n=matchsticks.length;
//        int rs=0;
//
//        for(int i=0;i<n;i++){
//            sum+=matchsticks[i];
//        }
//        edge=sum/4;
//        if(edge*4!=sum){
//            return false;
//        }
//
//        int dp[]=new int[edge+1];
//        dp[0]=1;
//
////        for(int i=0;i<n;i++){
////            dp[matchsticks[i]]=1;
////        }
//        for(int i=1;i<=edge;i++){
//            for(int j=0;j<n;j++){
//                if(i-matchsticks[j]<0||dp[i-matchsticks[j]]==0){
//                    continue;
//                }
//                dp[i]=dp[i-matchsticks[j]];
//
//                if(dp[i]==1
//                        &&i==edge){
//                    rs++;
//                }
//            }
//        }
//        for(int i=0;i<matchsticks.length;i++){
//            if(edge-matchsticks[i]<0||dp[edge-matchsticks[i]]==0){
//                return false;
//            }
//        }
//        return rs>=4;
//    }
//
//    public static int solution(
//            int i, 
//            int c1, 
//            int c2, 
//            int c3, 
//            int c4, 
//            int matchsticks[], int mask, int s){
//        int rs=0;
//        if(c1>sum/4||c2>sum/4||c3>sum/4||c4>sum/4){
//            return -1;
//        }
//        if(dp[mask][s]!=0){
//           return dp[mask][s]; 
//        }
//        if(i>=matchsticks.length&&c1==c2&&c2==c3&&c3==c4){
//            return 1;
//        }
//        if(i>=matchsticks.length){
//            return -1;
//        }
//        
//        rs=solution(i+1, c1+matchsticks[i], c2, c3, c4, matchsticks, 1<<3, s-matchsticks[i]);
//        dp[1<<3][s-matchsticks[i]]=rs;
//        if(dp[1<<3][s-matchsticks[i]]==1){
//            return dp[1<<3][s-matchsticks[i]];
//        }
//        rs=solution(i+1, c1, c2+matchsticks[i], c3, c4, matchsticks, 1<<2, s-matchsticks[i]);
//        if(dp[1<<2][s-matchsticks[i]]==1){
//            return dp[1<<2][s-matchsticks[i]];
//        }
//        dp[1<<2][s-matchsticks[i]]=-1;
//        rs=solution(i+1, c1, c2, c3+matchsticks[i], c4, matchsticks, 1<<1, s-matchsticks[i]);
//        if(dp[1<<1][s-matchsticks[i]]==1){
//            return dp[1<<1][s-matchsticks[i]];
//        }
//        dp[1<<1][s-matchsticks[i]]=-1;
//        rs=solution(i+1, c1, c2, c3, c4+matchsticks[i], matchsticks, 1, s-matchsticks[i]);
//        if(dp[1][s-matchsticks[i]]==1){
//            return dp[1][s-matchsticks[i]];
//        }
//        return dp[mask][s]=-1;
//    }
    //Ở đây chậm vì số bước vẫn lặp lại --> Mình chỉ nhanh hơn khi check if() sẽ không đi vào sâu bên trong
    //Mình sẽ bỏ luôn thay vì check bằng cách sắp xếp theo thứ tự tăng dần trước --> Sau đó chỉ chọn theo thứ tự
    //a<=b<=c<=d
    //--> Làm những bài liên quan tới tổng thì: (Sắp xếp + Cắt hoàn toàn) ==> Nhanh nhất
    public static boolean solution(int i, int c1, int c2, int c3, int c4, Integer matchsticks[], int sum) {
        boolean rs = false;
//        if (c1 > sum / 4 || c2 > sum / 4 || c3 > sum / 4) {
//            return false;
//        }
        if (i >= matchsticks.length && c1 == c2 && c2 == c3 && c1 == c4) {
            return true;
        }
        if (i >= matchsticks.length) {
            return false;
        }
        System.out.println(c1 + " " + c2 + " " + c3 + " " + c4);
//        if(remaining<sum/4){
//            return false;
//        }

        //1. Sẽ có trường hợp chạy cả 2 cases mới biết là false
        //Ở đây nếu mình để 1 case (Kết quả là sai) thì cây dưới nõ sẽ return false --> sai
        //2. Viết if --> giảm số bước đệ quy thì sẽ nhanh hơn
        //IF sẽ nhanh hơn việc chạy vào METHOD + IF( return )
        //3, Để vào trong if nhanh hơn 48 ms:
//        if (rs) {
//                return true;
//            }
        //4, Với điều kiện && ta sẽ dự đoán điều kiện dễ FAIL hơn đặt đằng trước --> Tăng tốc độ chương trình
        //Vd ở đây rule (c2>=c1+matchsticks[i]) là dễ FAIL hơn vì (<SUM/4 ) ---> Phải đến gần cuối mới sai
        //5, Ở đây nếu ta sắp xếp --> Sẽ tăng khả năng ta tìm được kết quả sớm hơn đối với trường hợp (true)
        //6, Tại sao lại c2!=c1 --> Vì ta công thêm các giá trị vào 2 số có giá trị bằng nhau --> Kiểu gì cũng xảy ra trường hợp
        //Đệ quy các trường hợp trùng nhau --> Nên ta không xét các trường hợp như thế (Chỉ xét duy nhất trường hợp các số bằng nhau)
        //Sẽ không có trường hợp nào kiểu gán 2 chiều (a!=b), case còn lại thì (b!=a):
        //--> Chỉ so sánh 1 bên vì nếu 2 bên thì các trường hợp (a==b)cd --> sẽ luôn bị bỏ qua (Mình chỉ được phép cộng a/b)
        //===> Vì 2 số == nhau == Việc xét 1 case (a=b)--> (case: a, b+x) (Lúc đó ta có thể cộng thêm vào a dược rồi)
        //6.1, Có phải so sánh theo kiểu xuay vòng không? --> Xuay vòng hay không thì phải tuân theo 1 logic cụ thể nào đó!
        //Rule: Khi 1 số a đang được check (a+x&&a!=b){ mới xét } (Bỏ qua trương hợp ==) <=> Trương hợp (b==a) phải xảy ra với
        //a==b (case a+x)
        //---> c4 đã được check c4!=c3 rồi --> c4 sẽ không được check (c4!=c1): Sẽ không có chuyện so sánh xoay vòng
        //VD: c1!=c4||c1==0
        //--> Sai kết quả
        //Giả dụ: c3==c4 --> c2!=c4 mà có thể trường hợp c2==c1 xảy ra --> Khi so sánh vòng tròn (Các điều kiện sẽ conflict theo vòng tròn)
//        0 0 0 0
//        5 0 0 0
//        5 5 0 0
//        5 5 5 0
//        5 5 5 5
//
//        0 0 0 0
//        5 0 0 0
//        5 5 0 0
//        5 5 5 0
//        5 5 5 5
//        6 5 5 5
//        6 6 5 5
//        6 6 6 5
        //7, Không làm theo kiểu chuỗi tăng dần --> Vì đôi khi chuỗi đó có thể lúc đầu luôn luôn (KHÔNG TĂNG DẦN)
        //--> Sẽ có 1 số trường hợp xảy ra khi chuỗi không đúng quy luật (Tức là việc cộng số lên không đủ) ==> Bị bỏ đi luôn
        //--> Sẽ gây thiếu trường hợp
        //8, Nếu mình so sánh không đúng gây ra việc phải check if(c2!=0) --> Có thể gây chậm cho chương trình
        if (c1 + matchsticks[i] <= sum / 4) {
            rs = solution(i + 1, c1 + matchsticks[i], c2, c3, c4, matchsticks, sum);
            if (rs) {
                return true;
            }
        }
//        if ((c3 <= c2 + matchsticks[i] && c2!=c3 || c3 == 0) && c2 + matchsticks[i] <= sum / 4) {
//            rs = solution(i + 1, c1, c2 + matchsticks[i], c3, c4, matchsticks, sum);
//            if (rs) {
//                return true;
//            }
//        }
        if (c2!=c1&&c2 + matchsticks[i] <= sum / 4) {
            rs = solution(i + 1, c1, c2 + matchsticks[i], c3, c4, matchsticks, sum);
            if (rs) {
                return true;
            }
        }
        if (c3!=c2 && c3 + matchsticks[i] <= sum / 4) {
            rs = solution(i + 1, c1, c2, c3 + matchsticks[i], c4, matchsticks, sum);
            if (rs) {
                return true;
            }
        }
        if (c4!=c3&&c4 + matchsticks[i] <= sum / 4) {
            rs = solution(i + 1, c1, c2, c3, c4 + matchsticks[i], matchsticks, sum);
            if (rs) {
                return true;
            }
        }
        return false;
    }

    public static boolean makesquare(int[] matchsticks) {
        int sum = 0;
        int n = matchsticks.length;

        for (int i = 0; i < n; i++) {
            sum += matchsticks[i];
        }
//        dp=new int[1<<3+1][sum+1];
        if ((sum / 4) * 4 != sum) {
            return false;
        }
//        for (int i = 0; i < n; i++) {
//            if (matchsticks[i] > sum / 4) {
//                return false;
//            }
//        }
        Integer[] arr = Arrays.stream(matchsticks).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer t, Integer t1) {
                return t1 - t;
            }
        });
//        Arrays.sort(arr);
        if(arr[0]>sum/4){
            return false;
        }
        return solution(0, 0, 0, 0, 0, arr, sum);
    }

    public static boolean makesquare2(int[] matchsticks) {
        int sum = 0;
        if (matchsticks.length < 4) {
            return false;
        }
        for (Integer i : matchsticks) {
            sum += i;
        }
        if (sum % 4 != 0) {
            return false;
        }
        Integer[] arr = Arrays.stream(matchsticks).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer t, Integer t1) {
                return t1 - t;
            }
        });

        int side = sum / 4;
        if (matchsticks[0] > side) {
            return false;
        }
        int cnt = 0;
        while (cnt < 4) {
            if (back(arr, 0, 0, side)) {
                cnt++;
            } else {
                return false;
            }
        }
        return true;
    }

//    public static void reverse(int[] input) {
//        int last = input.length - 1;
//        int middle = input.length / 2;
//        for (int i = 0; i <= middle; i++) {
//            int temp = input[i];
//            input[i] = input[last - i];
//            input[last - i] = temp;
//        }
//    }
    public static boolean back(Integer matchsticks[], int pos, int sum, int side) {
        if (side == sum) {
            return true;
        } else if (sum > side) {
            return false;
        }

        for (int i = pos; i < matchsticks.length; i++) {
            if (matchsticks[i] < 0) {
                continue;
            }
            int tsum = sum + matchsticks[i];
            matchsticks[i] = -matchsticks[i];
            if (back(matchsticks, i + 1, tsum, side)) {
                return true;
            }
            matchsticks[i] = -matchsticks[i];
        }
        return false;
    }

    public static boolean makesquare1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 4 != 0) {
            return false;
        }
//        Arrays.sort(nums);
        return dfs(nums, new int[4], nums.length - 1, sum / 4);
    }

    static boolean dfs(int[] nums, int[] sums, int index, int target) {
        if (index == -1) {
            return true;
        }
        Arrays.stream(sums).forEach((i) -> {
            System.out.print(i + " ");
        });
        System.out.println("");
        for (int i = 0; i < 4; i++) {
            if ((sums[i] + nums[index] > target) || (i > 0 && sums[i] == sums[i - 1])) {
                continue;
            }
            sums[i] += nums[index];
            if (dfs(nums, sums, index - 1, target)) {
                return true;
            }
            sums[i] -= nums[index];
        }
        return false;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,1,2,2,2};
//        int arr[]=new int[]{3,3,3,3,4};
//        int arr[]=new int[]{6122053,1031956,460065,3996684,3891947,1839190,6127621,8855952,8835594,3425930,4189081,7596722,876208,7952011,9676846};
//        int arr[] = new int[]{5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3};
//        int arr[] = new int[]{1, 3, 3, 5, 3, 1};
//        int arr[]=new int[]{1,2,3,4,5,6,7,8,9,10,5,4,3,2,1};
//        int arr[]=new int[]{13,11,1,8,6,7,8,8,6,7,8,9,8};
//        int arr[] = new int[]{1, 1, 2, 3, 3, 2, 4};
//        int arr[] = new int[]{10,6,5,5,5,3,3,3,2,2,2,2};
//      Case 172
//        int arr[] = new int[]{12,13,1,15,11,17,16,3,15,11,13,4,2,16,15};
        int arr[] = new int[]{5,5,5,5,1,1,1,1};

        //
        //** Đề bài
        //- Check xem dùng các sticks khác nhau có thể tạo thành hình vuông không
        //VD
        //Input: matchsticks = [1,1,2,2,2]
        //Output: true
        //

        //Tại sao khi thêm vào c2 phải thỏa mãn điều kiện: c2!=c1
        System.out.println(makesquare(arr));
//        System.out.println(makesquare1(arr));
//        System.out.println(makesquare2(arr));
    }
}
