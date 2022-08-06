package leetcode_medium_dynamic;

import java.util.Arrays;

public class E62_StoneGame {

    public static boolean stoneGame(int[] piles) {
        int n=piles.length;
        //Không chọn boolean vì nó không quyết định được kết quả hiện tại
        int dp[][]=new int[n][n];
        int sum= Arrays.stream(piles).sum();
        for(int i=0;i<n;i++){
            dp[i][i]=piles[i];
        }

        //dp[i][j] là
        for(int i=2;i<=n;i+=2){
            for(int j=0;i+j-1<n;j++){
                int k=i+j-1;
                int case1=Integer.MAX_VALUE;
                int case2=Integer.MAX_VALUE;
                if(j+1<n&&k-1>=0){
                    case1=Math.min(dp[j+1][k-1], case1);
                }
                if(j+2<n){
                    case1=Math.min(dp[j+2][k], case1);
                }

                if(j+1<n&&k-1>=0){
                    case2=Math.min(dp[j+1][k-1], case2);
                }
                if(k-2>=0){
                    case2=Math.min(dp[j][k-2], case2);
                }
                //1, Việc gán này sẽ chậm hớn với việc
                //Max(a + b, b + c)
//                case1+=piles[j];
//                case2+=piles[k];

                dp[j][k]=Math.max(case1+piles[j], case2+piles[k]);
            }
        }
        return dp[0][n-1]>sum-dp[0][n-1];
    }

    public static boolean stoneGame1(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];

        for (int i=0; i<n; i++) {
            dp[i][i] = piles[i];
        }

        for (int len = 1; len < n; len ++) {
            for (int i=0; i+len < n; i++) {
                dp[i][i+len] = Math.max(piles[i] - dp[i+1][i+len],
                        piles[i+len] - dp[i][i+len-1]);
            }
        }
        return dp[0][n-1] > 0;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{5,3,4,5};
//        int arr[]=new int[]{3,7,2,3};

        //Tư duy như sau:
        //0, Các lỗi tư duy cần làm rõ để bỏ qua
        //0.1, dp[i][j] không thể để kiểu boolean vì:
        //true/ false không thể hiện được "sự phụ thuộc" (after) và "before"
        //- Kết quả: true/false dp[i][j] không thể tính theo dp[i-1[j] và d[i][j+1]
        //---> Ta chọn từ ngoài vào trong thì tổng bên ngoài <--- phụ thuộc ---> cả tổng bên trong.
        //----> dp[i][j] sẽ lưu kết quả tổng tối ưu.
        //1,
        //- dp[i][j] lưu tổng của 1 người (Alice or Sam),
        //- dp[i][j] lưu tổng của cả 2 người
        //2, Max của Min
        //2.1, Nếu Alice chọn max thì nó phải chọn trường hợp sao cho
        // Sau khi Alice chọn thì Sam ăn MAX (Sam ăn max --> Alice nested case sẽ ăn MIN)
        //Thứ tự xét như sau:
        //- MIN( Nest của Alice ăn) + pile[i] (Bên ngoài chọn)
        //- MAX (của bên ngoài Alice chọn) --> Chính là kết quả bài toán
        //VD: 5,3,4,5
        //case 1 = Min( (3,4), (4,5) + 5 (index=0) )
        //case 2 = Min( (5,3), (3,4) + 5 (index=n-1) )
        //rs=Math.max(case1, case2)

        //Cách 2:
        //0.1, dp[i][j] lưu hiệu tổng của Alice -Sam ???? ----------> Sai
        //VD: (j --> k)
        //- Alice được phép chọn (j/k)
        //- Sau đó Sam phải chọn dp[h][k] và dp[j][h] sao cho hiêu lúc đó Sam phải có lợi nhất
        // --> Min của hiệu
        //--> sau đó ta sẽ xét Max theo Alice với hiệu đó + piles của Alice chọn
        //### Kinh nghiệm
        //Min của Max --> Ta có thể chuyển thành (Hiệu của piles [j] -dp[j+1][k] )
        //1, dp[i][j] ở đây lưu giá trị của (hiệu giữa người chọn và người còn lại)
        //--> Alice chọn a ( Hiệu giữa Sam và Alice)
        //--> a - (b - a1 (Alice)) = a+a1 -b (Đúng)
        //==> Ra hiệu giữa (Alice và Sam)
        System.out.println(stoneGame(arr));
        System.out.println(stoneGame1(arr));
    }
}
