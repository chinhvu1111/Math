package contest;

import java.util.Arrays;
import java.util.List;

public class E199_ConstructTheMinimumBitwiseArrayII {

    public static int[] minBitwiseArray(List<Integer> nums) {
        int n=nums.size();
        int[] rs=new int[n];

        for(int i=0;i<n;i++){
            int temp=nums.get(i);
            int curBit=0;
            int count=0;
            while (temp!=0){
                curBit = temp&1;
                if(curBit==0){
                    break;
                }
                temp=temp>>1;
                count++;
            }
            //101
            if(count!=0){
                rs[i]= nums.get(i) ^ (1<<(count-1));
            }else{
                rs[i]=-1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an array nums consisting of (n prime integers).
        //You need to construct an array ans of length n, such that,
        //  + for each index i, the bitwise OR of ans[i] and ans[i] + 1 is equal to nums[i],
        //  Ex: i.e. ans[i] OR (ans[i] + 1) == nums[i].
        //  + Additionally, you must (minimize) (each value of ans[i]) in the resulting array.
        //- If it is not possible to find such a value for ans[i] that satisfies the condition, then set ans[i] = -1.
        //- A prime number is a natural number greater than 1 with only two factors, 1 and itself.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 100
        //2 <= nums[i] <= 10^9
        //  + Bắt đầu từ 2
        //nums[i] is a prime number.
        //  + len nhỏ
        //  + n == 10^9: Time: O(log(n))
        //
        //** Brainstorm
        //- Prime number:
        //  + Bit cuối của nó có gì đặc biệt không
        //  Ex:
        //  2 = 10
        //  5 = 101 ==> Prime
        //VD:
        //Even num:
        //5 = 101
        //6 = 110
        //7 = 111
        //8 = 1000
        //Even: bit cuối == 0
        //Odd: bit cuối == 1
        //
        //Even:
        //x = a or (a+1)
        //x = = a or a or 1
        //x = a or 1
        //  ==> a = x - 1
        //- Prime number:
        //  + Odd + không chia hết cho số nào
        //
        //Odd:
        //x = a or (a+1)
        //Ex:
        //101001111 + 1
        //=
        //101010000
        //or
        //101001111
        //=
        //101011111
        //+ Chuyển đổi số đầu tiên (right-> left) thành 1
        //
        //Ex:
        //100111 => 101111
        //  + 101111 - 100111 = 2^3
        //  ==> Cùng cách nhau:
        //Ex:
        //100111
        //100011 + 1 = 100100
        //100111 or 100100 = 100111
        //
        //100110 + 1 = 100111
        //
        //3 = 11
        //(10+1) or 10 = 11
        //val = 2 but smallest value == 1
        //- Smallest:
        //  ==> Cần giảm bit lớn nhất
        //Ex:
        // 7 = 111
        //011+1 or 011
        //
//        String s = "111011100110101100101000000000";
//        System.out.println(Math.pow(2, 30));
//        System.out.println(s.length());
//        Integer[] nums = {11,13,31};
        Integer[] nums = {2,3,5,7};
        int[] rs= minBitwiseArray(Arrays.asList(nums));
        for (int i = 0; i < rs.length; i++) {
            System.out.println(rs[i]);
        }
    }
}
