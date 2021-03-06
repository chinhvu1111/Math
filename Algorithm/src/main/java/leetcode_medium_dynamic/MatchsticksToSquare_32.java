/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium_dynamic;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author chinhvu
 */
public class MatchsticksToSquare_32 {

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
    //??? ????y ch???m v?? s??? b?????c v???n l???p l???i --> M??nh ch??? nhanh h??n khi check if() s??? kh??ng ??i v??o s??u b??n trong
    //M??nh s??? b??? lu??n thay v?? check b???ng c??ch s???p x???p theo th??? t??? t??ng d???n tr?????c --> Sau ???? ch??? ch???n theo th??? t???
    //a<=b<=c<=d
    //--> L??m nh???ng b??i li??n quan t???i t???ng th??: (S???p x???p + C???t ho??n to??n) ==> Nhanh nh???t
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

        //1. S??? c?? tr?????ng h???p ch???y c??? 2 cases m???i bi???t l?? false
        //??? ????y n???u m??nh ????? 1 case (K???t qu??? l?? sai) th?? c??y d?????i n?? s??? return false --> sai
        //2. Vi???t if --> gi???m s??? b?????c ????? quy th?? s??? nhanh h??n
        //IF s??? nhanh h??n vi???c ch???y v??o METHOD + IF( return )
        //3, ????? v??o trong if nhanh h??n 48 ms:
//        if (rs) {
//                return true;
//            }
        //4, V???i ??i???u ki???n && ta s??? d??? ??o??n ??i???u ki???n d??? FAIL h??n ?????t ?????ng tr?????c --> T??ng t???c ????? ch????ng tr??nh
        //Vd ??? ????y rule (c2>=c1+matchsticks[i]) l?? d??? FAIL h??n v?? (<SUM/4 ) ---> Ph???i ?????n g???n cu???i m???i sai
        //5, ??? ????y n???u ta s???p x???p --> S??? t??ng kh??? n??ng ta t??m ???????c k???t qu??? s???m h??n ?????i v???i tr?????ng h???p (true)
        //6, T???i sao l???i c2!=c1 --> V?? ta c??ng th??m c??c gi?? tr??? v??o 2 s??? c?? gi?? tr??? b???ng nhau --> Ki???u g?? c??ng x???y ra tr?????ng h???p
        //????? quy c??c tr?????ng h???p tr??ng nhau --> N??n ta kh??ng x??t c??c tr?????ng h???p nh?? th??? (Ch??? x??t duy nh???t tr?????ng h???p c??c s??? b???ng nhau)
        //S??? kh??ng c?? tr?????ng h???p n??o ki???u g??n 2 chi???u (a!=b), case c??n l???i th?? (b!=a):
        //--> Ch??? so s??nh 1 b??n v?? n???u 2 b??n th?? c??c tr?????ng h???p (a==b)cd --> s??? lu??n b??? b??? qua (M??nh ch??? ???????c ph??p c???ng a/b)
        //===> V?? 2 s??? == nhau == Vi???c x??t 1 case (a=b)--> (case: a, b+x) (L??c ???? ta c?? th??? c???ng th??m v??o a d?????c r???i)
        //6.1, C?? ph???i so s??nh theo ki???u xuay v??ng kh??ng? --> Xuay v??ng hay kh??ng th?? ph???i tu??n theo 1 logic c??? th??? n??o ????!
        //Rule: Khi 1 s??? a ??ang ???????c check (a+x&&a!=b){ m???i x??t } (B??? qua tr????ng h???p ==) <=> Tr????ng h???p (b==a) ph???i x???y ra v???i
        //a==b (case a+x)
        //---> c4 ???? ???????c check c4!=c3 r???i --> c4 s??? kh??ng ???????c check (c4!=c1): S??? kh??ng c?? chuy???n so s??nh xoay v??ng
        //VD: c1!=c4||c1==0
        //--> Sai k???t qu???
        //Gi??? d???: c3==c4 --> c2!=c4 m?? c?? th??? tr?????ng h???p c2==c1 x???y ra --> Khi so s??nh v??ng tr??n (C??c ??i???u ki???n s??? conflict theo v??ng tr??n)
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
        //7, Kh??ng l??m theo ki???u chu???i t??ng d???n --> V?? ????i khi chu???i ???? c?? th??? l??c ?????u lu??n lu??n (KH??NG T??NG D???N)
        //--> S??? c?? 1 s??? tr?????ng h???p x???y ra khi chu???i kh??ng ????ng quy lu???t (T???c l?? vi???c c???ng s??? l??n kh??ng ?????) ==> B??? b??? ??i lu??n
        //--> S??? g??y thi???u tr?????ng h???p
        //8, N???u m??nh so s??nh kh??ng ????ng g??y ra vi???c ph???i check if(c2!=0) --> C?? th??? g??y ch???m cho ch????ng tr??nh
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

        //T???i sao khi th??m v??o c2 ph???i th???a m??n ??i???u ki???n: c2!=c1
        System.out.println(makesquare(arr));
//        System.out.println(makesquare1(arr));
//        System.out.println(makesquare2(arr));
    }
}
