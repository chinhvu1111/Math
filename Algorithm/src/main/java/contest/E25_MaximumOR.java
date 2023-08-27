package contest;

public class E25_MaximumOR {

    public static long maximumOrWrong(int[] nums, int k) {
        int n=nums.length;
        long[][] dp=new long[n][k+1];

        dp[0][0]=nums[0];
        for(int i=1;i<=k;i++){
            dp[0][i]= (long) nums[0] <<i;
//            System.out.printf("%s, ", dp[0][i]);
        }

//        for(int i=1;i<n;i++){
//            for(int j=0;j<=k;j++){
//                for(int h=j;h>=0;h--){
//                    //dp[i][j]:
//                    //- i là vị trí đang xét đến (ta sẽ có i-1 vị trí cần xét * 2)
//                    //- j là số operation đã thực hiện * 2
//                    //dp[i][k] = dp[h: 0 --> i-1][k-1]
//                    dp[i][j]=Math.max(dp[i][j],dp[i-1][h]| ((long) nums[i] <<(j-h)));
////                    System.out.println((nums[i]<<(j-h)));
//                }
//            }
//        }
        for(int i=1;i<=k;i++){
            for(int j=0;j<n;j++){
                for(int h=j-1;h>=0;h--){
                    //VD:
                    //(8),1,2
                    //(16),1,2
                    //(32),1,2
                    //32|1|2 = 35.
                    //
                    //8|1|2=
                    dp[j][i]=Math.max(dp[j][i], dp[j][i-1] |((long) nums[h]<<1));
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

    public static long maximumOr(int[] nums, int k) {
        int n=nums.length;
        if(n==0){
            return 0;
        }
        long[] leftValue=new long[n];
        long[] rightValue=new long[n];

        for(int i=1;i<n;i++){
            leftValue[i]=leftValue[i-1]|nums[i-1];
        }
        for(int i=n-2;i>=0;i--){
            rightValue[i]=rightValue[i+1]|nums[i+1];
        }
        long maxVal=0;

        for(int i=0;i<n;i++){
            long curVal= (long) nums[i] *(1L <<k);
            maxVal=Math.max(maxVal, leftValue[i] | curVal | rightValue[i]);
        }
        return maxVal;
    }

    public static long maximumOrTest(int[] nums, int k) {
        int n=nums.length;
        if(n==0){
            return 0;
        }
        long maxVal=Integer.MIN_VALUE;
        int maxIndex=0;

        for(int i=0;i<n;i++){
            if(maxVal<nums[i]){
                maxVal=nums[i];
                maxIndex=i;
            }
        }
        long leftVal=0;
        long rightVal=0;

        for(int i=1;i<n;i++){
            if(i<=maxIndex){
                leftVal=leftVal|nums[i-1];
            }
        }
        for(int i=n-2;i>=0;i--){
            if(i>=maxIndex){
                rightVal=rightVal|nums[i+1];
            }
        }
        return leftVal|maxVal<<k|rightVal;
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
        //+ Đại diện cho kết quả cho đến vị trí thứ (i) thì ta thực hiện (j) operations
        //
        //16|1|2|32 != 32|1|2
        //--> Cần bỏ 16 or 32 thì mới ra kết quả đúng được
        //--> bỏ đi thì có operator nào hỗ trợ không? (xor thử thì không được)
        //==> Phép or 1 | 0 <=> 1 | 1 ==> Không thể nào revert được.
        //* Ta sẽ không thể bỏ giá trị cũ đi được.
        //
        //Ex:
        //arr=[ 8,1,2 ]
        //+ k = 2
        //+ operations =[ 2,0,0 ]
        //a | b | c
        // a | b
        //Ex:
        //+ Test prefix sum
        //
        //* Tính chất nếu a|b|c|d ==> Chỉ nên nhân 2 k lần với cùng 1 số cho (n trường hợp)
        //- Sẽ không có case nhân cọc cạch để ra kết quả tốt hơn
        //
        //- Chứng minh việc thì add vào 1 element[i] thì mới hiệu quả:
        //[12, 9]
        //Ex : [12<<4, 9]
        //11000000 // 192, multiplying 12 by 2 4 times
        //00001001
        //-----
        //11001001 // 201, after ORing
        //
        //Ex : [12, 9<<4]
        //00001100
        //10010000 // 144, multiplying 9 by 2 4 times
        //-----
        //10011100 // 156, after ORing
        //
        //* Or nên số 1 ngoài cùng là quan trọng nhất ==> nếu càng lớn thì số 1 đó ngày càng rộng ra
        //- Vì 100000(len) > 11111(len=5)
        //- Vì vẫn có case các length của các số = nhau --> Ta cần nhân k lần với all số trong nums[i] (i : 0 --> n-1)
        //
        //+ or(12<< 4) < result < or(9<<4)
        //- Nếu binary cùng length thì sẽ có case như sau:
        //Ex:
        //[12, 9], k=1
        //
        //12: 1100
        //9 : 1001
        //
        //12*2 : 11000
        //
        //11000 : 24
        //or
        //01001 : 9
        //= 11001
        //
        //01100 : 12
        //or
        //10010
        //= 11110
        // --> 11110 > 11001
        //
        // [30, 4, 7, 8]
        //[1] = 30 | 4
        //[2] = 30 | 4 | 7
        //[3] = 30 | 4 | 7 | 8
        //
        // [30, 4, 7, 8]
        // 8
        //[n-2] = 7 | 8
        //[n-3] = 4 | 7 |8
        //[n-4] = 30 | 4 | 7 | 8
        //
        //
        //1.1, Optimization
        //- Có thể tối ưu space O(n) --> O(1)
        //
        //1.2, Complexity
        //- Space : O(n)
        //- Time complexity : O(n)
        //
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
        System.out.println("===============Test");
        System.out.println(8|1|2);
        System.out.println(16|1|2);
        //16|1|2|32 != 32|1|2
        //--> Cần bỏ 16 or 32 thì mới ra kết quả đúng được
        //--> bỏ đi thì có operator nào hỗ trợ không? (xor thử thì không được)
        //==> Phép or 1 | 0 <=> 1 | 1 ==> Không thể nào revert được.
        //* Ta sẽ không thể bỏ giá trị cũ đi được.
        //
        //
        //#Reference:
        //1. Two Sum
        //1090. Largest Values From Labels
        //1675. Minimize Deviation in Array
        //1756. Design Most Recently Used Queue
        System.out.println(16|1|2|32);
        System.out.println(32|1|2);
        System.out.println( ((16|1|2) ^ 16) | 32 );
        System.out.println( ((15|1|2) ^ 15) | 30 );
        System.out.println( 30|1|2 );
        System.out.println("===============Test prefix sum");
        //30 | 4
        //30 | 4 | 7
        //30 | 4 | 7 | 8
        System.out.println( 12|4 );
        System.out.println( 12|4|7 );
        System.out.println( 12|4|7|21 );
        // 7| 21
        System.out.println( 31^12 );
        System.out.println( 7|21 );
        //
    }
}
