package interviews;

import java.util.Arrays;

public class E73_GuessNumberHigherOrLowerII {

    public static int arr[][];

    public static int getMoneyAmount(int n) {
        arr=new int[n+1][n+1];

        for(int i=0;i<=n;i++){
            Arrays.fill(arr[i], -1);
        }
        int rs=maxCost(1, n);

        return rs;
    }

    public static int maxCost(int low, int high){
        if(low>=high-1){
            if(low==high){
                return 0;
            }
            return low;
        }

        //Chỗ này gán bằng register sẽ cho tốc độ chậm hơn
        // so với việc dùng array 1 cách trưc tiếp
        if(arr[low][high]!=-1){
            return arr[low][high];
        }

        int left=0;
        int right=0;
        int min=Integer.MAX_VALUE;

        for(int i=low+1;i<high;i++){
//            System.out.printf("left : %s %s", low, i-1);
//            System.out.println();
            left=maxCost(low, i-1) + i;
//            System.out.printf("right : %s %s", i+1, high);
//            System.out.println();
            right=maxCost(i+1, high) + i;
            min=Math.min(min, Math.max(right, left));
        }

        return (arr[low][high]=min);
    }

    public static int getMoneyAmountDynamic(int n){
        int dp[][]=new int[n+1][n+1];

        for(int i=1;i+1<=n;i++){
            dp[i][i+1]=i;
        }

        for(int i=3;i<=n;i++){
            for(int j=1;j+i-1<=n;j++){
                int k=i+j-1;
                int min=Integer.MAX_VALUE;

                for(int h=j+1;h<k;h++){
//                    int currentMax=Math.max(dp[j][h-1], dp[h+1][k]);
                    min=Math.min(min, Math.max(dp[j][h-1], dp[h+1][k])+h);
                }
                dp[j][k]=min;
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int n=10;
        System.out.println(getMoneyAmount(n));
        //Bài này ta sẽ tư duy như sau:
        //Đề bài :
        //Cách 1:
        //1, Given a particular n, return the minimum amount of money you need to guarantee a win regardless of what number I pick.
        //==> Tức là người ra yêu cầu chọn thoái mái 1 số giữa 1 --> n
        // + Ta tìm min cost của all index được guess giữa (1 --> n)
        // + Nhưng phải là MAX trong việc chọn giữa 2 nửa (1...index-1), (index+1, n-1)
        //1.1, Người kia chọn ngẫu nhiên 1 số giữa 1 và n
        //+ Tính chất của binary là ta sẽ chia Array thành 2 parts:
        //+ Chọn index nào để chia thì number cần tìm cũng thuộc 1 trong 2 parts
        //2, Bài này ta có thể miss case range chỉ có 2 elements (low, high) ==> Lúc đó ta sẽ ưu tiên chọn low vì low <=high
        //+ 2 elements : return low
        //+ 1 element : return 0.
        //3, CT:
        //+ right=range(i+1, high) + i;
        //+ left=range(low, i-1) + i;
        //+ min=Math.min(min, Math.max(right, left));
        //4, Tối ưu:
        //Code:
        //if(low>=high-1){
        //            if(low==high){
        //                return 0;
        //            }
        //            return low;
        //        }
        //--> Nếu để if thế này ==> Tăng tốc độ thay vì (nhiều if riêng rẽ).
        //4.1, Không phải lúc nào chuyển array --> int value (register) thì sẽ nhanh được
        //int value=arr[low][high];
        //
        //        if(value!=-1){
        //            return value;
        //        }
        //==> Slow hơn rất nhiều so với
        //if(arr[low][high]!=-1){
        //            return arr[low][high];
        //        }
        //--> Vào trong được if để { return; }
        // --> Ít ==> Tốc độ code bên trên sẽ bị slow do có THÊM (1 bước tạo variable) ==> (số lần vào được if khá ít).

        //4.2, Với dạng này thì ta cần tối ưu sao cho:
        //+ SỐ LẦN DÙNG LẠI nhiều nhất có thể
        //--> Tránh việc tính lại arr[low][high]
        //5, Cách xử lý debug khi cần với low, high, và i
        //Cách 2:
        //1, Quy dynamic sẽ giúp ta tối ưu dễ hơn so với recursion
        //--> Trong trường hợp này dynamic sẽ run nhanh hớn do dùng lại được nhiều kết quả hơn.

        System.out.println(getMoneyAmountDynamic(n));
    }
}
