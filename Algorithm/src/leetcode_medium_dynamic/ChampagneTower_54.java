package leetcode_medium_dynamic;

import java.util.Arrays;

public class ChampagneTower_54 {

    public static double champagneTower(int poured, int query_row, int query_glass) {
        int n=(query_row+1)*(query_row+2)/2;
        double dp[]=new double[n];

        //Chú ý: Ban đầu hiểu sai đề bài
//        if(i%2!=0){
//            dp[i]=(dp[i-1]-1)/2;
////                if(i!=1){
////                    dp[i-1]=1;
////                }
//        }else{
//            dp[i]=(dp[i-2]-1)/2;
////                dp[i-2]=1;
//        }

        dp[0]=poured;
        int range=2;
        int start=0;
        int target=(query_row+1)*query_row/2;

        for(int i=0;i+range<n;i++){
            if(dp[i]-1>=0){
                dp[i+range-1]+=(dp[i]-1)/2;
                dp[i+range]+=(dp[i]-1)/2;
            }
            if(start==i){
                start+=range;
                range++;
            }
//            if(i==(target+query_glass)){
//                return dp[i];
//            }
        }
        return dp[target+query_glass]>1?1:dp[target+query_glass];
    }

    public static double champagneTower1(int poured, int query_row, int query_glass) {
        double[] res = new double[query_row + 2];
        res[0] = poured;
        for (int row = 1; row <= query_row; row++)
            for (int i = row; i >= 0; i--)
                res[i + 1] += res[i] = Math.max(0.0, (res[i] - 1) / 2);
        return Math.min(res[query_glass], 1.0);
    }

    public static double champagneTower2(int poured, int query_row, int query_glass) {
        // initialize dp table
        double[][] memo = new double[query_row + 1][query_row + 1];
        for (double[]  row: memo) {
            Arrays.fill(row, -1.0);
        }
        memo[0][0] = poured;

        // get the total liquid for the requested glass
        double totalLiquid = recurse(query_row, query_glass, memo);
        // calculate how much did the glass hold
        return totalLiquid <= 1 ? totalLiquid : 1;
    }

    private static double recurse(int query_row, int query_glass, double[][] memo) {
        // base case
        if (query_glass < 0 || query_glass > query_row) {
            return 0;
        }
        else if (memo[query_row][query_glass] != -1) {
            return memo[query_row][query_glass];
        }

        // get liquid from the parent glasses
        double left = recurse(query_row - 1, query_glass - 1, memo);
        double right = recurse(query_row - 1, query_glass, memo);

        // combine the excess for the current glass
        double cur = (left <= 1 ? 0 : (left - 1) / 2) + (right <= 1 ? 0 : (right  - 1) / 2);

        memo[query_row][query_glass] = cur;
        return cur;
    }

    public static void main(String[] args) {
//        int poured=2;
//        int queryRow=1;
//        int queryGlass=1;
//        int poured=4;
//        int queryRow=2;
//        int queryGlass=2;

        //Tư duy ở đây như sau:
        //1, Khi pour xuống vị trí [i]
        //1.1, Ta chỉ có thể nhận 1 đơn vị
        //--> Dùng sau không cần trừ đi (Vì số đó chỉ dùng 1 lần không dừng nữa để tính)
        //+ Ta chỉ trừ đí --> Bù sang vị trí khác.
        //dp[i+range-1]+=(dp[i]-1)/2;
        //dp[i+range]+=(dp[i]-1)/2;
        //1.2,
        //Ta tính theo: dp[i]: Đánh (index - tăng dần) lần lượt theo tầng
        // Giới hạn n = (i+1+1)*(i+1) /2
        //--> Target: t= (i+1)*i/2 + (index cần lấy ở hàng cuối)
        //2, Ở đây quy luật range: --> sẽ thay đổi theo từng tầng
        //+ Khi nào thay đổi range: Khi ta đến cuối hàng (i==start)
        //--> Update biên tiếp: start+=range;
        //+ Thay đổi thì ta: Tẳng range lên 1 đơn vị

        int poured=100000009;
        int queryRow=33;
        int queryGlass=17;
//        int poured=25;
//        int queryRow=6;
//        int queryGlass=1;
        System.out.println(champagneTower(poured, queryRow, queryGlass));
        System.out.println(champagneTower1(poured, queryRow, queryGlass));
        System.out.println(champagneTower2(poured, queryRow, queryGlass));
    }
}
