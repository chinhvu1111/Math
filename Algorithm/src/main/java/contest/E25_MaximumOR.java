package contest;

public class E25_MaximumOR {

    public static long maximumOr(int[] nums, int k) {
        int n=nums.length;
        long[][] dp=new long[n][k+1];

        dp[0][0]=nums[0];
        for(int i=1;i<=k;i++){
            dp[0][i]= (long) nums[0] <<i;
//            System.out.printf("%s, ", dp[0][i]);
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<=k;j++){
                for(int h=j;h>=0;h--){
                    dp[i][j]=Math.max(dp[i][j],dp[i-1][h]| ((long) nums[i] <<(j-h)));
//                    System.out.println((nums[i]<<(j-h)));
                }
            }
        }
        long rs=Long.MIN_VALUE;
        long currentValue=0;

        for(int i=n-1;i>=0;i--){
            rs=Math.max(rs, dp[i][k] | currentValue);
            currentValue=currentValue| nums[i];
//            System.out.println(rs);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Cho 1 array nums =[a0,a1,a2,a3]
        //- Số k
        //- Mỗi operation sẽ lấy 1 số bất kỳ *2
        //+ Tìm số lớn nhất có thể a0 | a1 ... | an
        //+ Sau k lần chọn ngẫu nhiên (Tự do)
        //
        //** Idea
        //1.
        //1.1,
        //- Dữ kiện:
        //- 0 < n <= 10^5
        //- 1 <= k <=15
        //
        //- 1 số biểu diễn *2 <=> dịch sang bên trái 1 bit a*2 = (a << 1)
        //1100
        //or
        //1001
        //+ Bài toán trở thành dịch ntn trong a và b để tối ưu
        //- Dùng 1 quy hoạch động để tính theo số trước
        //
        //12 | 9
        //1100
        //or
        //1001
        //----
        // 1 1
        //
        //24 | 9
        //
        //11000
        //01001
        //1   1
        //
        //
        //24 | 9
        //12 | 18
        //
        //
        //A, b, c, d
        //- Ta chọn b thì tích bằng
        //K =1
        //A | (b*2) | c | d
        //= a | c | d | (b*2)
        //
        //K = 2
        //A | (b*2) | c | d
        //+ Vì ta update thành (b*2) rồi thì ta cần phải update tất cả giá trị của a, c, d dựa trên kết quả mới
        //+ Mà n <= 10^5 khá lớn
        //--> Ta chỉ có thể tính trực tiếp từ or all các elements
        //--> Tính đảo ngược
        //VD:
        //12 | 9
        //1100
        //or
        //1001
        //=
        //1101 = 8 + 4 = 1
        //13
        //
        //1101
        //1100
        //--> Sao tính được 9 ==> Không tính được.
        //
        //1001 --> (Xor)
        //
        //A | b = c
        //A | b | (b*2) --> Không liên quan.
        //
        //11001
        //110010
        //1100100
        //===> Tư duy sai vì 15 operations không phải dạng [n][k]
        //
        //- Suy luận 1 chút
        //8, 1, 2
        //- 15 lần --> chia cho n vị trí
        //
        //0 --> I : dp[i][k]
        //Xét phần tử  j=i + 1:
        //+ Nếu thêm 1 lần 2 vào (i+1) ==> Kết quả khi đến j: dp[i][k+1] = d[i][k] | (arr[j]<<1)
        //+ Nếu thêm 1 lần 3 vào (i+1) ==> Kết quả khi đến j: dp[i][k+2] = d[i][k] | (arr[j]<<2)
        //
        //- dp=new int[n+1][k+1];
        //+ dp[i][j]
        //+ Đại diện cho kết quả cho đến vị trí thứ i thì ta thực hiện j operations
        int a=12;
        int b=9;
//        System.out.println((b|a));
//        System.out.println((b|a) << 1);
//        System.out.println(24|9);
//        System.out.println(13 ^ 9);
//        System.out.println(13 ^ 12);
//        System.out.println(12 | 9);
//        System.out.println(12 | 18);
//        System.out.println(12 | 9 | 18);
//        System.out.println(12 | 3);
//        System.out.println(12 | 6);
//        System.out.println(12 | 6 | 12);

        System.out.println(2<<0);
//        int[] nums = {8,1,2};
//        int k = 2;
//        int[] nums = {12,9};
//        int k = 1;
        int[] nums = {41,79,82,27,71,62,57,67,34,8,71,2,12,93,52,91,86,81,1,79,64,43,32,94,42,91,9,25};
        int k = 10;
        System.out.println(maximumOr(nums, k));
    }
}
