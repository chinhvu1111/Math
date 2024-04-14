package E1_slide_window;

public class E14_CountSubarraysWithFixedBounds {

    public static long countSubarrays(int[] nums, int minK, int maxK) {
        int n=nums.length;
        int leftIndex=-1;
        int rightIndex=-1;
        int badIndex=-1;
        long rs=0;

        for(int i=0;i<n;i++){
            if(nums[i]<minK||nums[i]>maxK){
                badIndex=i;
            }
            if(nums[i]==minK){
                leftIndex=i;
            }
            if(nums[i]==maxK){
                rightIndex=i;
            }
            rs+= Math.max(0, Math.min(leftIndex, rightIndex) - badIndex);
        }
        //4,3,(6),8,([1],3,[5])
        //rs = 1
        //4,3,3,2,([1],3,[5])
        //Ex
        //2,3,4,[1],3,[5],2
        //  + Nếu đứng ở 2 ta tính ntn?
        //      + 2 sẽ kết hợp với all subarray cho đến ==> [1]
        //      + count += index[val=1] - badIndex (index[val=1] ==> Tính bằng min(left, right) do 1 và 5 có thể swap cho nhau)
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer (array nums) and two integers (minK) and maxK().
        //A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
        //  + The (minimum value) in the subarray is equal to (minK).
        //  + The (maximum value) in the subarray is equal to (maxK).
        //* Return the number of (fixed-bound subarrays).
        //- A subarray is a contiguous part of an array.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= nums.length <= 10^5
        //1 <= nums[i], minK, maxK <= 10^6
        //
        //- Brainstorm
        //Ex:
        //nums = [1,3,5,2,7,5], minK = 1, maxK = 5
        //+ start=0,end=0:
        //[1,3,5,2,7,5]
        //- Dùng queue:
        //1,3,5: count+=1
        //1,2,3,5 : count+=2
        //1,2,3,5 + 7 ==> clear
        //  + Nếu nhỏ hơn max thì add thêm được
        //  + Nếu lớn hơn max --> Clear all queue
        //==> Idea này có vẻ chưa đúng vì:
        //  -> 3,2,(1),2,4,(5) ==> Đằng trước >=1 ==> Vẫn tính
        //Ex:
        //[1,3,5,2,7,5]
        //+ queue = 1,3,5,2 : count+=1
        //+ queue = 1,3,5,2,7 : ==> clear queue
        //+ queue = 5 : ==> count+=1
        //Ex:
        //[4,7,2,3,4,(1),3,(5),2,(1),4,5,7,5]
        //+ queue = 4
        //  + min=4, max=4
        //+ queue = 4,7: 7>5 ==> clear
        //  + min=4, max=7
        //+ queue = 2,3,4
        //  + min=2, max=4
        //+ queue = 2,3,4,1,3
        //  + min=1, max=3
        //+ queue = 2,3,4,(1),3,(5)
        //-> Đoạn này tính số lượng ntn?
        //  + count=4
        //  + min=1, max=5
        //+ queue = 2,3,4,(1),3,(5),2
        //  + count+=1
        //  + min=1, max=5
        //+ queue = 2,3,4,(1),3,(5),2,(1)
        //==> Với tư duy này sẽ không xử lý được do cộng dồn
        //- Thử dùng slide window
        //Ex:
        //[4,7,2,3,4,(1),3,(5),2,(1),4,5,7,5]
        //queue = 4
        //queue = 4,7 ==> clear
        //queue = 2,3,4,(1),3,(5)
        //  + min=1, max=5
        //queue = 2,3,4,(1),3,(5),2,1,4,5
        //  + min=1, max=5
        //-> Tính số lượng sub-array thoả mãn ntn
        // 2,3,4,(1),3,(5),2,1,4,5
        //  + Tính ra thì không thể xét từng sub được ==> Chúng liên quan đến nhau
        //  + min=1, max=5
        //- Thử quy về từng cặp (1,5) xem tính ntn?
        //  + Tính ra thì sum all xung quanh (min, max)
        //- Ta có thể tham khảo + làm theo cách trên
        //==> Chia ra từng cặp (min, max) ==> Tìm cách tính từng cặp sao cho không giao nhau
        //* Solution:
        //4,3,(6),8,([1],3,[5])
        //rs = 1
        //4,3,3,2,([1],3,[5])
        //Ex
        //2,3,4,[1],3,[5],2
        //  + Nếu đứng ở 2 ta tính ntn?
        //      + 2 sẽ kết hợp với all subarray cho đến ==> [1]
        //      + count += index[val=1] - badIndex (index[val=1] ==> Tính bằng min(left, right) do 1 và 5 có thể swap cho nhau)
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(1)
        //- Time : O(n)
        //
        int[] nums= {2,3,4,1,3,5,2,1,4,5};
        System.out.println(countSubarrays(nums, 1, 5));
    }
}
