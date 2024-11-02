package E1_daily;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class E127_MaximumWidthRamp {

    public static int maxWidthRampSort(int[] nums) {
        int n=nums.length;
        int[][] valIndex=new int[n][2];
        for (int i = 0; i < n; i++) {
            valIndex[i][0]=nums[i];
            valIndex[i][1]=i;
        }
        Arrays.sort(valIndex, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        int minIndex=valIndex[0][1];
        int rs=0;

        for(int i=1;i<n;i++){
            rs=Math.max(valIndex[i][1]-minIndex, rs);
            minIndex=Math.min(minIndex, valIndex[i][1]);
        }
        return rs;
    }

    public static int maxWidthRamp(int[] nums) {
        int n=nums.length;
        int rs=0;
        //[val, index]
        Stack<int[]> stack=new Stack<>();
        stack.add(new int[]{nums[0], 0});

        for(int i=1;i<n;i++){
            while (!stack.isEmpty()&&stack.peek()[0]>nums[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                rs=Math.max(rs, i-stack.peek()[1]);
            }else{
                stack.add(new int[]{nums[i], i});
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- A ramp in an integer array nums is a pair (i, j)
        //  + for which i < j and nums[i] <= nums[j].
        //- The width of such a ramp is (j - i).
        //- Given an integer array nums,
        //* return (the maximum width of a ramp) in nums.
        //  + If there is no ramp in nums, return 0.
        //+ Khoảng rộng nhất có width
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //2 <= nums.length <= 5 * 10^4
        //0 <= nums[i] <= 5 * 10^4
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: nums = [6,0,8,2,1,5]
        //- Đứng ở 5 tìm value có:
        //  + small index
        //  + nums[index] <= 5
        //
        //nums = [6,0,8,2,1,5]
        //Sort trước:
        //0,1,2,5,6,8
        //- Loop (left -> right):
        //  + Để tìm xem min index là bao nhiêu
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n+log(n))
        //- Time: O(n*log(n))
        //
        //2, Stack
        //Example 1:
        //Input: nums = [6,0,8,2,1,5]
        //- Ta thấy rằng nếu ta tính kết quả theo (kết quả trung gian) cũng được:
        //6,0,8,2,1,5
        //- val x -> so sánh với y sao cho:
        //  + x>=y
        //  - Ta chỉ cần tìm y gần nhất mà x>=y là được vì:
        //      + y,y1...,x
        //      ==> y1>=x
        //      ==> Số lớn hơn thì ta không quan tâm nữa
        //          + Số đằng sau x vd: x1 chỉ quan tâm đến số <=x1
        //              + y1>=y mà lại gần hơn y
        //          ==> Ta không cần quan tâm.
        //- Có cần lưu lại số hiện tại không:
        //  + Không vì chỉ quan tâm đến số nhỏ hơn thôi (smaller value)
        //Ex:
        //8,7,5,3,2,10
        //- Ta cần lưu lại bigger value = 8
        //  ==> 10 sẽ cần dùng 8
        //==> Nếu cứ tư duy theo hướng kia thì không xử lý được
        //
        //- Dùng index để xác định
        //Ex:
        //8,7,5,3,2,10
        //0,1,2,3,4,5
        //
        //Example 1:
        //Input: nums = [6,8,2,1,5]
        //
        int[]  nums = {6,0,8,2,1,5};
        System.out.println(maxWidthRampSort(nums));
        System.out.println(maxWidthRamp(nums));
        //#Reference:
        //2921. Maximum Profitable Triplets With Increasing Prices II
        //2728. Count Houses in a Circular Street
        //2580. Count Ways to Group Overlapping Ranges
    }
}
