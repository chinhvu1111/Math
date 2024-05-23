package E1_bitmanipulation;

public class E9_MinimumArrayEnd {

    public static long solutionWrong(int n, int x){
        long low=0;
        long high=1_000_000_000;
        long rs=-1;
        while (low<=high){
            long mid=low+(high-low)/2;
            //10110111
            //- Cần đếm bỏ bit đầu tiên thì có bao nhiêu bits:
            //10110111
            //&
            //01111111
            long midShift=mid & ((1L <<Long.bitCount(mid))-1);
            long numNum = Long.bitCount(midShift);
            if(numNum>=n-1){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static int findZeroBitFromPos(int num, int firstVal){
        int pos=0;
        while ((num|(1<<pos))==num){
            pos++;
        }
        //Lật các vị trí đằng sau 1 => 0 nếu đằng trước có 1 r
        int p=0;
        while(p<pos){
            if((firstVal>>p&1)==0&&(num>>p&1)==1){
                //11(1)111
                //Flip bit at pos
                num = num ^ (1<<p);
            }
            p++;
        }
        num=num|(1<<pos);
//        System.out.printf("pos: %s, num: %s\n", pos, num);
        return num;
    }

    public static long minEnd(int n, int x) {
        int rs=x;
        for(int i=1;i<n;i++){
            rs=findZeroBitFromPos(rs, x);
        }
        return rs;
    }

    public static long minEnd1(int n, int x) {
        long rs=x;
        for(int i=1;i<n;i++){
            rs=(rs+1)|x;
        }
        return rs;
    }

    public static long[] fillZeroBitFromPos(long pos, long num){
        while ((num|(1<<pos))==num){
            pos++;
        }
        num=num|(1<<pos);
//        System.out.printf("pos: %s, num: %s\n", pos, num);
        return new long[]{pos, num};
    }

//    public static long minEndOptimization(int n, int x) {
//        long rs=x;
//        long pos=0;
//        //011
//        //100
//        //101
//        //110
//        while (n>0){
//            long[] info = fillZeroBitFromPos(pos, rs);
//            pos=info[0];
//            rs=info[1];
//            n=n>>1;
//        }
//        return rs;
//    }

    public static long minEndOptimization(int n, int x) {
        long rs=x;
        n--;
        //1011001
        //n=3
        //==> Cần điền:
        //1011001 -> 1011(11)1
        //End
        long curMask=1;
        while(n>0){
            //
            if((rs&curMask)==0){
                //- Cần check xem n có bit đó không
                //- bit ở n --> Dịch sao cho fit với (vị trí == 0)
                //  + Chỗ này có thể * với (00001000..) <=> curMask là được.
                rs=rs|((n&1)*curMask);
                n=n>>1;
            }
            curMask = curMask<<1;
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given two integers n and x. You have to construct an array of positive integers nums of size n
        // where for every 0 <= i < n - 1, nums[i + 1] is greater than nums[i], and the result of the bitwise AND operation
        // between all elements of nums is x.
        //* Return the (minimum possible) value of nums[n - 1].
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n, x <= 10^8
        // N khá lớn ==> Chỉ có thể dynamic
        //  Hoặc là log --> Binary search
        //==> X khá lớn
        //
        //- Brainstorm
        //Ex:
        //Input: n = 3, x = 4
        //Output: 6
        //Explanation:
        //nums can be [4,5,6] and its last element is 6.
        //
        //(a -> x) + (điều kiện dãy tăng dần)
        //+ Từ a --> Bỏ đi (1 số bit) đi thành x
        // ==> Đếm số lượng bit là được.
        //- 0 <= i < n-1 (<=n-2)
        //  + n=3: 0 -> 1 => 2 bit
        //
        //11 => 1,2,3 ==> 3 số
        //110 ==> 1 + 2^(bit-1)
        //==> Đếm bit thôi thì chưa đủ vì:
        //  + Ex:
        //  1(01101) ==> Phần trong ngoặc có thể có rất nhiều cases OR với nhau được
        //  Ex: 0 & 1 = 0/ 1 & 0 = 0
        //
        //- x : 10^8 ==> Có thể chuyển thành bit
        //- Tìm num[n-1] --> Size của array = n
        //
        //- Phép AND/ & = x
        //  => Các số trong đó sẽ có số > x
        //  + Mà dãy tăng dần --> nums[n-1] lớn nhất
        //- nums[n-1] >= x
        //Ex:
        // x= 183 <=> 10110111
        //* Ta thấy quy luật:
        //+ Với những vị trí = 0
        //  + Ta có thể điền 1/0 + phải có 1 vị trí = 0
        //+ Với những vị trí = 1
        //  + Ta phải điền 1 hết
        //
        //15&1 = 1 (Vì bit 1 toàn bộ trước đó ==0 + còn lại ==1)
        //10110111, có thể tách thành:
        //10110111
        //&
        //1x11x111
        //  + Mà x = 10110111 cần lớn nhất nhưng những chỗ pos=0 ta chỉ có thể điền vào đó
        //  ==> Nó sẽ sinh ra số > x --> Sai.
        //--> Nums[0]>=x
        //Ex:
        //x=10110111
        //n=3
        //==> Ta chỉ add bit lên dần là được.
        //x=10110111 ==> Để giữ nguyên tính chất thì ta sẽ add bit vào những vị trí 0
        //  ==> Nếu add 0 vào 1 thì kết quả AND chung sẽ thay đổi.
        //Ex:
        //x=10110111
        //n=3
        //1: 10110111
        //2: 10111111
        //3: 11111111
        //--> Ở đây ta sẽ dùng phép OR để xử lý
        //
        //Ex:
        //x=4 : 100
        //n=3
        //100
        //101
        //110
        //==> Tức là ta có thể điền 1 ở vị trí 0 mới ==> Có thể điền 0 toàn bộ vị trí còn lại.
        //** Làm như trên timeout:
        //  ==> O(n*k) : k là phần loop bit
        //- Bài này ta chỉ cần cộng dần lên là được
        //  + rs++ | x : Để loại bỏ đi nhưng thằng ==0 của x (Để and all thì nó sẽ ==x)
        //* Cần nhớ 1 tính chất:
        //0000
        //0001
        //0010
        //0011 ==> Là cơ chế tăng dần 1 bit một ==> Nên chỉ cần rs++ là được.
        //  + Sau đo kết hợp với check rs=(rs+1)|x là được
        //
        //1.1, Optimization
        //- Thay vì O(n) ta có thể giảm time xuống được vì:
        //  + rs không nhất thiết phải rs++ dần
        //  ==> Nếu rs++ sẽ slow do phải take O(n) time
        //
        //- Với mỗi bit==0 ta có thể điền 1 vào:
        //Ex:
        //1100101
        //-> fill 0 -> 1
        //1100111
        //  + Giả sử ở đâu ta có 3 số 0, ta có thể generate được các case:
        //  000
        //  ->
        //  111 : 0 -> 7 (8 số)
        //  + Vấn đề là chỉ cần n=3 <=> 11 thôi
        //  ** Phải điền 0 -> 11 như của n thôi
        //  - Vì 000 + fill theo n sẽ cho ta đích là nhỏ nhất.
        //
        //- Vì cần tìm nums[n-1] --> n--;
        //====== CODE EXPLANATION ======
        //if((rs&curMask)==0){
        //    //- Cần check xem n có bit đó không
        //    //- bit ở n --> Dịch sao cho fit với (vị trí == 0)
        //    //  + Chỗ này có thể * với (00001000..) <=> curMask là được.
        //    rs=rs|((n&1)*curMask);
        //    n=n>>1;
        //}
        //====== CODE EXPLANATION ======
        //
        //1.2, Complexity
        //- Before optimization
        //  + Space: O(1)
        //  + Time : O(n)
        //- Optimization:
        //  + Space : O(1)
        //  + Time : O(log(max(n, log(x)))
        //
        long mid = 15;
        long midShift=mid & ((1L <<Long.bitCount(mid))-1);
        System.out.println(Long.bitCount(midShift));
        System.out.println(15&2);
        System.out.println(1<<0);
        System.out.println(1<<1);
        System.out.println("Solution");
        System.out.println(minEnd(2, 7));
        System.out.println(minEndOptimization(3, 4));
        System.out.println(minEndOptimization(2, 7));
    }
}
