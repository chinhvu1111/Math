package E1_daily;

import java.util.ArrayList;
import java.util.List;

public class E23_SingleNumberIII {

    public static int[] singleNumber(int[] nums) {
        int n=nums.length;
        int xorVal=0;

        for(int i=0;i<n;i++){
            xorVal^=nums[i];
        }
        int lastBit = xorVal & (-xorVal);
        int[] rs=new int[2];

        for(int i=0;i<n;i++){
            if((lastBit&nums[i])==0){
                rs[0]^=nums[i];
            }else{
                rs[1]^=nums[i];
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an integer array nums, in which exactly (two elements) appear (only once)
        // and (all the other elements) appear (exactly twice).
        //* Find (the two elements) that appear (only once).
        // You can return the answer in any order.
        //- You must write an algorithm that runs in linear runtime complexity
        // and uses only constant extra space.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //2 <= nums.length <= 3 * 10^4
        //-2^31 <= nums[i] <= 2^31 - 1
        //Each integer in nums will appear twice, only two integers will appear once.
        //==> Điều kiện khá strict : Chỉ có thể xử lý trong O(n)
        //
        //- Brainstorm
        //Ex:
        //nums = [1,2,1,3,2,5]
        //- Xuất hiện 2 lần:
        //  + (x^x)==0
        //- Xuất hiện 1 lần?
        //- Nếu xor all:
        //Ex:
        // 1 xor 2 xor 1 xor 3 xor 2 xor 5
        // = 3 xor 5
        //+ a xor b = (a & !b) | (b & !a)
        //+ (a xor b)&a = ((a & !b) | (b & !a))&a = (a & !b) | 0 = (a & !b)
        //+ (a xor b)&b = ((a & !b) | (b & !a))&b = (b & !a) | 0 = (b & !a)
        //+ (a & !b) & a = (a&!b)
        //+ (a & !b) | b = (a|b) & 1 = 1
        //  ==> Có vẻ không đúng lắm
        //- (a xor b)
        //- CT: -x = ~x + 1
        //+ (a xor b) & (~(a xor b) + 1)
        //+ ~(a & b) = ( ~a | ~b )
        //+ ~(a | b) = ( ~a & ~b )
        // -(a xor b) = ~(a xor b) + 1 = ~((a & !b) | (b & !a)) +1 = ~(a & !b) & ~(b & !a) + 1
        // = (!a | b) & (!b | a) + 1
        // = ((!a&!b) | (b&a)) + 1
        //- CT:
        // x & (-x)
        // = x & (~x+1)
        //
        //- CT:
        // (a | b) & (c | d)
        //= ((a|b)&c) | ((a|b)&d)
        //= ((a&c) | (b&c)) | ((a&d) | (b&d))
        //
        //==> Với logic bên trên thì không xử lý được.
        //
        //* Solution:
        //3: 011
        //xor
        //5: 101
        //=  110
        //  + Bit thứ 2 từ last !=0:
        //      + Nó sẽ là phép xor của 2 số có bit thứ 2 từ last :
        //      1 xor 0 = 1
        //- Ta sẽ chia được 2 group:
        //  + group có bit thứ 2 từ last ==1
        //  + group có bit thứ 2 từ last ==0
        //
        //- 2 số cần tìm chắc chắn nằm riêng rẽ ở hai group vì:
        //  ==> Chúng chính là số tạo ra việc (last second bit==1)
        //==> Ta sẽ xor 2 group ==> sẽ tìm được 2 số
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time : O(n)
        //
        //#Reference:
        //2433. Find The Original Array of Prefix Xor
        //
        int[] nums = {1,2,1,3,2,5};
        System.out.println((3^5)&3);
        System.out.println(3&(~5));
        System.out.println(-(5^3));
        System.out.println(~(5^3) + 1);
        //~((a & !b) | (b & !a)) + 1 = -6
        System.out.println(~((5 & ~3) | (3 & ~5)) + 1);
        //-6
        System.out.println(~(5 & ~3) & ~(3 & ~5) + 1);
        //-6
        System.out.println((~5 | 3) & (5 | ~3) + 1);
        //-6
        System.out.println(((~5&~3) | (5&3)) + 1);
        //5  :101
        //-5 :011
        //101 & 011 = 001
        System.out.println(5&(-5));
        System.out.println(5^3);
        //2 : 0010
        System.out.println(2&(-2));
        //2 : 0010
        //-2: 1110
        //0010 & 1110 = 0010
        //5: 0101
        //3: 0011
    }
}
