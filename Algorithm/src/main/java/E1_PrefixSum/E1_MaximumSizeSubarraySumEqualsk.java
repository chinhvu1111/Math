package E1_PrefixSum;

import java.util.HashMap;

public class E1_MaximumSizeSubarraySumEqualsk {

    public static int maxSubArrayLen(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer, Integer> leftMostIndexOfSum=new HashMap<>();
        int leftSum=0;
        int rs=0;
        leftMostIndexOfSum.put(0, -1);

        for(int i=0;i<n;i++){
            leftSum+=nums[i];
            int neededSum=leftSum-k;

            if(leftMostIndexOfSum.containsKey(neededSum)){
                int indexNeededSum=leftMostIndexOfSum.get(neededSum);
//                System.out.printf("%s %s %s\n", leftSum, neededSum, indexNeededSum);
                rs=Math.max(rs, i-indexNeededSum);
            }
            if(!leftMostIndexOfSum.containsKey(leftSum)){
                leftMostIndexOfSum.put(leftSum, i);
            }
        }

        return rs;
    }

    public static void main(String[] args) {
//        int[] nums = {1,-1,5,-2,3};
//        int k = 3;
//        int[] nums = {-2,-1,2,1};
//        int k = 1;
//        int[] nums = {-2};
//        int k = -2;
        int[] nums = {2};
        int k = 0;
        System.out.println(maxSubArrayLen(nums, k));
        //** Đề bài:
        //{1,-1,5,-2,3}
        //- Tìm chiều dài mã của subarray có sum=k
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Ý tưởng:
        //- Có cả số <0
        //- Dùng hashmap để lưu prefixSum[j]
        //- Giả sử đến vị trí prefixSum[i] ==> ta cần sum=k
        //==> Ta sẽ tìm xem đằng trước có tồn tại prefixSum[i-k] là được.
        //+ HashMap<Sum, Index> ==> Lấy index trước đó ra.
        //+ Index sẽ được là left most ==> Để length luôn max nhất.
        //
        //1.2, Complexity
        //- Time complexity : O(n)
        //- Space complexity : O(n)
    }
}
