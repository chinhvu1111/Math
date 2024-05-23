package E1_daily;

import E1_Graph.E11_WordLadder;

public class E5_MinimumNumberOfOperationsToMakeArrayXOREqualToK {

    public static int minOperations(int[] nums, int k) {
        int curXor=0;
        for(int e: nums){
            curXor = curXor ^ e;
        }
        int rs=0;
        int num=k;
        while(curXor!=0||num!=0){
            if((curXor&1)!=(num&1)){
                rs++;
            }
            curXor=curXor>>1;
            num=num>>1;
//            System.out.printf("Cur: %s, num: %s\n", curXor, num);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed (integer array nums) and (a positive integer k).
        //- You can apply the following operation on the array any number of times:
        //  + Choose any element of the array and (flip a bit) in its binary representation.
        //  + Flipping a bit means changing a 0 to 1 or vice versa.
        //* Return (the (minimum) number of operations) required to make (the bitwise XOR) of (all elements)
        // of the final array equal to k.
        //- Note that you can flip (leading zero bits) in the binary representation of elements.
        //  + Được flip bit đầu tiên
        //  For example, for the number (101)2 you can flip the fourth bit and obtain (1101)2.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= nums.length <= 10^5
        //0 <= nums[i] <= 10^6
        //0 <= k <= 10^6
        //--> Size khá lớn.
        //
        //- Brainstorm
        //Ex:
        //Input: nums = [2,1,3,4], k = 1
        //Output: 2
        //Explanation: We can do the following operations:
        //- Choose element 2 which is 3 == (011)2, we flip the first bit and we obtain (010)2 == 2. nums becomes [2,1,2,4].
        //- Choose element 0 which is 2 == (010)2, we flip the third bit and we obtain (110)2 = 6. nums becomes [6,1,2,4].
        //The XOR of elements of the final array is (6 XOR 1 XOR 2 XOR 4) == 1 == k.
        //It can be shown that we cannot make the XOR equal to k in less than 2 operations.
        //2: 10
        //1: 01
        //3: 11
        //4 100
        //Xor all=
        //  101
        //- Phép xor nếu có số lượng bit 1 trong all số tại bit (i)
        //  => bit(i) = 1
        //  Ex:
        //  + 1 xor 0 = 1
        //  + 1 xor 0 xor 1 = 0
        //  + 1 xor 0 xor 1 xor 1 = 1
        //  + 1 xor 1 xor 1 = 1
        //+ <> = 0 hết
        //Nếu:
        //- Flip 0 -> 1:
        //  - Cần chọn thêm 1 thằng = 0 -> 1
        //- Flip 1 -> 0:
        //  - Flip thêm 1 thằng 0 -> 1 là được.
        //      + lẻ 1 -> chẵn 1 : tăng số lượng 1
        //  - Flip thêm 1 thằng 1 -> 0 là được.
        //      + lẻ 1 -> chẵn 1 : giảm số lượng 1
        //- Số lượng flip ==> Số lượng bit khác nhau thôi
        //1.1, Optimization
        //1.2, Complexity
        //- Time : O(n+numBit)
        //- Space : O(1)
        //0
        System.out.println(1^1);
        //0
        System.out.println(0^0);
        //1
        System.out.println(0^1);
        //1
        System.out.println(1^0);
        //1
        System.out.println(1^1^1);
        //0
        System.out.println(0^0^0);
        int[] nums = {2,1,3,4};
        System.out.println("2^1^3^4");
        //100 => 001
        System.out.println(2^1^3^4);
        int k = 1;
        System.out.println(minOperations(nums, k));
        //#Reference:
        //2220. Minimum Bit Flips to Convert Number
    }
}
