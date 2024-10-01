package E1_daily;

public class E113_BitwiseOROfAllSubsequenceSums {

    public static long subsequenceSumOr(int[] nums) {
//        int n=nums.length;
        long sum=0;

        //Time: O(n)
        for(int e: nums){
            sum+=e;
        }
        int numBit = 0;
        long temp=sum;
        while(temp!=0){
            temp=temp>>1;
            numBit++;
        }
        //111
        //3 bits
//        System.out.println(numBit);
        int[] bits=new int[numBit];
        //01010(1)
        //index = 0
        //Time: O(m)
        for(int i=0;i<numBit;i++){
            int sumBitOne=bits[i];
            //Time: O(n)
            for(int e:nums){
                int curBit = (e>>i)&1;
                sumBitOne+=curBit;
                //2 = (10>>0)&1 = 0
                if(curBit>0&&sumBitOne+i-1<numBit){
//                    System.out.printf("e: %s, i:%s, ith bit = 1: %s\n", e, i, sumBitOne+i-1);
                    bits[sumBitOne+i-1]=1;
                }
            }
            //sum bit tăng dần:
            //1
            //2:10
            //3:11
            //4:100
            //==> sum=4 ==> Tất cả các bit trước đó ==1 hết được
        }
        long multipleTwo=1;
        long rs=0;
        for(int i=0;i<numBit;i++){
            if(bits[i]!=0){
                rs+=multipleTwo;
            }
            multipleTwo*=2;
        }
        return rs;
    }

    public static long subsequenceSumOrRefer(int[] nums) {
        long ans = 0, prefix = 0;
        for (int x : nums) {
            prefix += x;
            ans |= x | prefix;
        }
        return ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an integer array nums,
        //* return (the value of the bitwise OR) of (the sum of all possible subsequences) in the array.
        //- A subsequence is a sequence that can be derived from another sequence by (removing zero or more elements)
        // without changing (the order of the remaining elements).
        //- Tức là return lại OR của all of (sum of subsequences) in the array
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 10^5
        //0 <= nums[i] <= 10^9
        //  + Length khá lớn ==> O(n)
        //  + Số khá lớn: Long
        //
        //- Brainstorm
        //- sum của all of sequences:
        //- Phép OR và SUM có liên quan gì không?
        //3+4 = 7
        //=11+10
        // 11
        //+
        // 10
        //=
        //101
        //==> Nhớ số 1
        //- OR :
        //  + Chỉ cần 1 số có bit tại (index=i) == 1
        //  ==> Bit tại (index=i) = 1
        //
        //Example 1:
        //Input: nums = [2,1,0,3]
        //[0,1,2,3]
        //- Giả sử result của OR operations = x
        //x có k bit:
        //+ Xét lần lượt bit số (ith => m):
        //  + m là số lượng bit tối đa mà x có thể có:
        //      + ith m = 1 khi:
        //          + 10...0 -> 11...1:
        //              + 1 trong những số ở đây có thể được tạo từ sum các subsequences trong array
        ///     + ith m=0:
        //          + Không tạo được thôi
        //- Bài toàn trở thành:
        //+ Given x:
        //  + Check xem nó có tạo được thành sum của 1 số elements trong nums hay không?
        // ==> Sau khi biến đổi problem vẫn khá khó ==> Time: O(n^2) mới giải được
        //
        //Example 1:
        //Input: nums = [2,1,0,3]
        //sorted nums = [0,1,2,3]
        //- Để biết bit tại ith có thể = 1 hay không:
        //  + Ta chỉ cần loop toàn bộ nums là được:
        //      + Chỉ cần 1 số có bit tại ith == 1
        //      ==> Bit tại (index=i) sau khi OR == 1
        //
        //- Có trường hợp nào số to hơn value trong nums:
        //  + Thành ra bit có index bigger == 1 không?
        //  ==> Có
        //Ex:
        //  11 = 3
        //+
        //  11 = 3
        //= 110 = 6
        //Ex:
        //   11 = 3
        //+
        //   11 = 3
        //+
        //   11 = 3
        //=1001 = 9 = 2^3+1
        //  + 1+1+1+1
        //  ==> 1<<(số lượng số 1)/2
        //Ex:
        //1 bit 0
        //1+1 bit 1
        //1+1+1 bit 0,1
        //1+1+1+1 bit 2
        //- Nhưng vẫn có thể nó kèm theo các bit đằng sau ==> Gây ra việc cộng không như ý thì sao?
        //Ex:
        //11
        //11
        //+ cộng vs nhau 11+11= 110
        //  + Dù cộng với nhau:
        //      + Bit(1) chắc chắn ==1
        //      + Bit(2) chắc chắn ==1 ==> Vì cần tăng bit lên
        //          ==> Tăng tối đa thì cũng (không lan được) sang bit(4) == 1 được
        //  ==> Nhưng nếu ta cộng nhiều hơn 2 số:
        //  11
        //+
        //  11
        //+
        //  11
        //= 1001 ==> Hoàn toàn có thể lan ra được.
        //  10
        //+
        //  10
        //+
        //  10
        //=110 = 6
        //  ==> Các giá trị hàng đơn vị đang thật sự có ý nghĩa.
        //* Main point:
        //  + Cộng dồn bit 1 từ small index ==> Ra được bigger index
        //      + Try to sum up lower bits and make higher bits.
        //- Ta thấy rằng:
        //  1+1+1 = 11 = 1 bit số 1
        //  10+10+10 + 1 bit số 1 bên trên = 1010 ==> ta đã có bit số 3
        //==> Cộng dồn bit được.
        //
        //Ex:
        //nums = [2,1,0,3]
        //nums = [10,1,0,11]
        //  + Để suy được 100 ==> Cần phải cộng 2 số 1
        //7 = 111
//        int[] nums = {2,1,0,3};
//        int[] nums = {35,20,18,4};
        int[] nums = {42,14,48};
        //rs:63
        //expected:127
        //7 bits
        //
        //1.1, Optimization
        //https://leetcode.com/problems/bitwise-or-of-all-subsequence-sums/solutions/2912919/c-java-python3-prefix-sum/
        //
        //1.2, Complexity
        //- m is max the number of bit of the result
        //- Space: O(m)
        //- Time: O(n*m)
        //
        //#Reference:
        //1979. Find Greatest Common Divisor of Array
        //2708. Maximum Strength of a Group
        //2098. Subsequence of Size K With the Largest Even Sum
        System.out.println(subsequenceSumOr(nums));
        System.out.println(subsequenceSumOrRefer(nums));
    }
}
