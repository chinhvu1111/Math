package E1_daily;

import java.util.HashMap;

public class E121_MakeSumDivisibleByP {

    public static int minSubarray(int[] nums, int p) {
        long sum=0;
        int n=nums.length;

        for(int i=0;i<n;i++){
            sum+=nums[i];
        }
        int x= (int) (sum % p);
        if(x==0){
            return 0;
        }
        HashMap<Integer, Integer> nearIndex=new HashMap<>();
        nearIndex.put(0, -1);
        int curSum=0;
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            curSum=(curSum+nums[i])%p;
            int temp = (curSum-x+p)%p;
            if(nearIndex.containsKey(temp)){
                rs=Math.min(rs, i-nearIndex.get(temp));
            }
            nearIndex.put(curSum, i);
//            curSum=(curSum+nums[i]%p);
//            int temp = (curSum%p-x+p)%p;
//            if(nearIndex.containsKey(temp)){
//                rs=Math.min(rs, i-nearIndex.get(temp));
//            }
//            nearIndex.put(curSum%p, i);
        }
        return (rs==Integer.MAX_VALUE||rs==n)?-1:rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an array of positive integers nums, remove the smallest subarray (possibly empty)
        // such that (the sum of the remaining elements) (is divisible by p).
        //- It is (not allowed) to remove the whole array.
        //* Return (the length of the smallest subarray) that you need to (remove), or -1 if it's impossible.
        //- A subarray is defined as (a contiguous block of elements) in the array.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^9
        //1 <= p <= 10^9
        //  + nums[i] khá lớn 10^9: ==> Long
        //  + Length khá lớn ==> Time: O(n*k)
        //
        //- Brainstorm
        //Example 1:
        //Input: nums = [3,1,4,2], p = 6
        //Output: 1
        //Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.
        //- (sum - x) % p == 0
        //Ex:
        //p = 6
        //(16-4)%6 ==0
        //  + 16%6 == 4
        //  + 4%6 == 4
        //==> Tức là 2 số %6 đều cho kết quả giống nhau
        //Example 2:
        //Input: nums = [6,3,5,2], p = 9
        //Output: 2
        //sum = 16%9 = 7
        //  + Ta sẽ tìm dãy có (sum%9) == 7
        //
        //- Lấy % các số: ===> WRONG
        //  + Sum continuous array == x
        //Input: nums = [6,3,5,2], p = 9
        //nums%p = [6,3,5,2]
        //[i:j] = [0:0]
        //  + [6]
        //[i:j] = [0:1] ==>
        //  + [6,3] = 9 ==> [i:j] = [1:1]
        //  ==> 9%9 == 0
        //[i:j] = [1:1]
        //  + [3] = 3
        //[i:j] = [1:2]
        //  + [3,5] = 8 > 7
        //  ==> [2:2] (Remove [1]
        //- Có case nào mà cộng vào > 9 ==> Nhưng vẫn lấy không
        //Ex:
        //nums  = [6,8,2,2]
        //nums%p= [6,8,2,2]
        //+ 6+8 = 14%9 = 5
        //+ 6+8+2 = 16%9 = 7 ==> Good
        //  + sum>=9 vẫn lấy
        //
        //Ex:
        //nums  = [6,8,2,5]
        //nums%p= [6,8,2,5]
        //+ 6+8 = 14%9 = 5
        //+ 6+8+2 = 16%9 = 7 ==> rs = 3
        //+ 6+8+2+5 = 21%9 = 3 < 7
        //  + Ở đây thì left++ ==> Thu được kết quả thì không nói
        //- Khi làm slide window:
        //  + Không phải lúc nào cũng nên right++
        //  ==> Vì có thể sẽ thiếu các case với (left1>left) + (right1<right) thoả mãn điều kiện
        //
        //Ex:
        //nums  = [6,8,8,5]
        //nums%p= [6,8,8,5]
        //+ 6+8+8 = 22%9 = 4
        //  ==> Cộng tiếp
        //+ 8+8 = 16%9 = 7 (Thoả mãn)
        //
        //** NGU
        //==> Máy móc quá
        //Nếu tìm (sum constinious sub array %x==0)
        //  + Prefix sum ==> prefix[i]%7 == prefix[j]%7
        //Ex:
        //nums = [6,3,5,2], p = 9
        //nums%k = [6,3,5,2]
        //prefix = [6,2,0,2]
        //===> WRONG
        //- Tìm sum continious array sao cho:
        //  + x%p = sum%p
        //  ==> Tìm (số dư+x)/(số dư-x)
        //- Giả sử:
        //  + sum%p = x
        //- prefixSum%p == k
        //  + Cần tìm:
        //      + k-k1 = x
        //      ==> Cần tìm (k-x)
        //          + Đoạn này cũng không đúng lắm vì:
        //              + k có thể <x
        //  ==> Cần + p để đảm bảo >=x
        //Ex:
        // nums = [3,1,4,2]
        // p = 6
        // [3,4,8,10]
        // [3,3,2,4]
        //+ Tìm sub (sum % p) == 4
        //  => sum = (p*x+4)
        //
//        int[] nums = {3,1,4,2};
//        int p = 6;
//        int[] nums = {1,2,3};
//        int p = 3;
//        int[] nums = {1,2,3};
//        int p = 7;
//        int[] nums = {4,4,2};
//        int p = 7;
        //x=10%7=3
        //- Chú ý:
        //  + (4+4+2)%3 = 10%3 = 1
        //  + 4%3+4%3+2%3 = 4 ==> Cần % tiếp
        //
        //+ 4+4+2=10%7==3
        //+ (4%7)+(4%7)+(2%7) = 10%7 = 3
        //  + ((4%7)+(4%7))%7 = 8%7 == 1 ==>Sai
//        int[] nums = {26,19,11,14,18,4,7,1,30,23,19,8,10,6,26,3};
//        int p = 26;
        int[] nums = {6,3,5,2};
        int p = 9;
        //- Tính sum%p dần dần:
        //nums = {6,3,5,2};
        //nums%p = {6%9,3%9,5%9,2%9} = {6,3,5,2}
        //prefix = {6,0,(6+3+5)%9,(6+3+5+2)%9};
        //prefix = {6,0,5,7};
        //sum = 16, 16%9 == 7
        //==>
        //- Cần tìm:
        //  + x-7 = (7-7) == 0
        //      + Nhưng sẽ có case:
        //          x<7 ==> Cần cộng thêm số cho chắc
        //      + Vì x%p ==> Bóc 1 p ra để cộng thêm được không:
        //          ==> Sau đó %p nữa là được.
        //  ==> (7+9-7)%9 = 0 == 7-7 (TỔNG QUÁT)
        //- Overflow:
        //prefix = {6,0,(6+3+5)%9,(6+3+5+2)%9};
        //==> Nếu làm ntn thì sẽ bị overflow
        //  + Do cộng dồn quá nhiều
        //** Kinh nghiệm:
        //- % dư:
        //  + Để ra chuỗi dư x ==> hiệu 2 prefix dư k và k1 sao cho:
        //      + (k-k1+p)%p = x
        //prefix = {6,0,(6+3+5)%9,(6+3+5+2)%9};
        //* TÍNH CHẤT
        //(a+b+c)%p = ((a+b)%p+c)%p
        //Ex:
        //(6+3+5+2)%9 = 7
        //(((6+3)%9+5)%9+2)%9 = (5+2)%9 == 7
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        System.out.println(minSubarray(nums, p));
        //#Reference:
        //https://leetcode.com/problems/maximum-coins-heroes-can-collect/description/
    }
}
