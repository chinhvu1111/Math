package contest;

import java.util.*;

public class E158_MinimumOperationsToMakeArrayEqualToTarget {

    public static long minimumOperationsWrong(int[] nums, int[] target) {
        int n=nums.length;
        int[] diff=new int[n];
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        HashSet<Integer> increase=new HashSet<>();
        List<Integer> increaseList=new ArrayList<>();
        HashSet<Integer> decrease=new HashSet<>();
        List<Integer> decreaseList=new ArrayList<>();

        for(int i=0;i<n;i++){
            diff[i]=target[i]-nums[i];
            mapCount.put(diff[i], mapCount.getOrDefault(diff[i], 0)+1);
            if(diff[i]>0){
                increase.add(diff[i]);
            }else{
                decrease.add(diff[i]);
            }
        }
        long rs=0;
        increaseList=new ArrayList<>(increase);
        decreaseList=new ArrayList<>(decrease);
        Collections.sort(increaseList);
        Collections.sort(decreaseList, Collections.reverseOrder());
        System.out.println(increaseList);
        System.out.println(decreaseList);
        //-1,-4,-5
        Deque<Integer> incrQueue=new LinkedList<>(increaseList);
        Deque<Integer> decrQueue=new LinkedList<>(decreaseList);
        int increaseNum=0;
        int decreaseNum=0;

        while (!incrQueue.isEmpty()){
            int first=incrQueue.removeFirst();
            rs+=first-increaseNum;
            increaseNum+=first-increaseNum;
        }
        while (!decrQueue.isEmpty()){
            int first=decrQueue.removeFirst();
            first=Math.abs(first);
            rs+=first-decreaseNum;
            decreaseNum+=first-decreaseNum;
        }
        return rs;
    }

    public static long minimumOperations(int[] nums, int[] target) {
        long res = 0, preDiff = 0;
        int n =target.length;

        for(int i=0;i<n;i++){
            res+=Math.max(target[i]-nums[i]-preDiff, 0);
            preDiff=target[i]-nums[i];
        }
        //nums =    [2,3,4,6,2,3]
        //target =  [5,6,3,5,3,4]
        //diff   =  [3,3,-1,-1,1,1]
        //- Đến số cuối:
        //  + Nếu preDiff>=0 ==> Đã cộng rồi (Bằng cách liên quan bù của current diff tại)
        //  + Nếu preDiff<0 ==> Chưa cộng
        //      + Ta sẽ đổi dấu và cộng thêm là được.
        return res+Math.max(-preDiff, 0);
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given (two "positive" integer arrays) (nums and target),
        //  + of the (same length).
        //- In a single operation,
        // you can select (any subarray of nums) and (increment) or (decrement) each element within that subarray (by 1).
        //* Return (the minimum number of operations) required to make (nums) equal to (the array target).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length == target.length <= 10^5
        //1 <= nums[i], target[i] <= 10^8
        //+ Time: O(n*k)
        //
        //** Brainstorm
        //Example 1:
        //Input: nums = [3,5,1,2], target = [4,6,2,4]
        //Output: 2
        //Explanation:
        //
        //We will perform the following operations to make nums equal to target:
        //- Increment nums[0..3] by 1, nums = [4,6,2,3].
        //- Increment nums[3..3] by 1, nums = [4,6,2,4].
        //
        //- Increase sub array:
        //  + by 1
        //==> Nếu các phần tử cách nhau k ==> 1*k
        //- Ta có thể tăng đều M phần tử cùng lúc thêm 1 (in the same step):
        //Ex:
        //nums =    [3,5,1,2]
        //target =  [4,6,2,4]
        //
        //- Ta phân các phần tử thành 2 loại:
        //  + (lớn hơn or =) từ nums[i]>=target[i]
        //  + nums[i]<target[i]
        //- Chia nums thành các sub array ngăn nhau bởi các phần tử:
        //1,2,5
        //4,5,6
        //diff:
        //3,3,1
        //=>
        //2,3,6
        //4,5,6
        //
        //Ex:
        //nums =    [3,5,1,2]
        //target =  [4,6,2,4]
        //diff   =  [1,1,1,2]
        //rs= 1+(2-1)
        //
        //Ex:
        //nums =    [3,5,1,2,3]
        //target =  [4,6,2,5,6]
        //diff   =  [1,1,1,3,3]
        //rs= 1+(3-1)+(3-1) = 1+4 = 5
        //==> Min
        //
        //Ex:
        //nums =    [2,3,3,5]
        //target =  [5,6,4,6]
        //diff   =  [3,3,1,1]
        //min = (3-1)+(3-1) + 1 = 5
        //==> Lấy min -> max được không
        //
        //Ex:
        //nums =    [2,3,4,6]
        //target =  [5,6,3,5]
        //diff   =  [3,3,-1,-1]
        //min = (3-1)+(3-1) + 1 = 5
        //==> abs là được
        //
        //1,1,1,3,3,3,2,2,2
        //==>
        //Remove (1,1,1), x=1
        //  + rs+=1 + count(3) + count(2)
        //Remove (2,2,2) -> (2-x...)
        //  + rs += 1 + count(3)
        //Remove (3,3,3) -> (3-1-(2-x)) = x
        //  + rs += x
        //
        //1,1,1,4,4,4,2,2,2
        //==>
        //Remove (1,1,1), x=1
        //  + rs+=1 + count(4) + count(2)
        //Remove (2,2,2) -> (2-x...)
        //  + rs += 1 + count(3)
        //Remove (3,3,3) -> (4-1-(2-x)) = 1+x
        //  + rs += x
        //- Hash count of diff
        //- sort diff
        //- Loop diff
        //
        //nums =    [2,3,4,6,2,3]
        //target =  [5,6,3,5,3,4]
        //diff   =  [3,3,-1,-1,1,1]
        //diff[i]<0: ==> Decrease
        //- Phân thành 2 phần (decrease and increase)
        //
        //-1,-1,-1
        //
//        int[] nums={3,5,1,2,3};
//        int[] target={4,6,2,5,6};
//        int[] nums={3,5,1,2};
//        int[] target={4,6,2,4};
//        int[] nums={2,3,4,6,2,3};
//        int[] target={5,6,3,5,3,4};
        //- Nhiều diff[i]<0 hơn
//        int[] nums={2,3,4,6,2,3,7,7};
//        int[] target={5,6,3,5,3,4,5,5};
        //- Diff:
        //  + 5 <-> 3
        //  5 ->
        int[] nums={1,3,2};
        int[] target={2,1,4};
        //- Vì là sub array --> Nếu gặp thằng decrease --> increase sẽ bị break
        //==> Nch là cách này khá khó
        //
        //2. Dynamic programming
        //2.0.
        //- Brainstorm:
        //- Bài này tương tự bài:
        //- https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/solutions/754682/wall-of-bricks/
        //- Cho target:
        //Example: [3,1,5,4,2,3,4,2].
        //  + Đếm số step để biến đổi:
        //  [0,0,0,0,0,0,0,0] -> [3,1,5,4,2,3,4,2]
        //  + Left edges are marked by red color. Total number of left edges is 3 + 4 + 1 + 1.
        //- Tính chất:
        //  + Nếu (previous diff >= current diff):
        //      + Ta có thể include current diff cùng (increase/ decrease) của previous step
        //  + Nếu previous diff < current diff:
        //      + Tức là ta cần phải cộng lên phần (current diff - previous diff)
        //          ==> Ta sẽ (bù thêm thôi) + còn (1 phần) sẽ include vào (previous diff)
        //      + Tức là nếu (đằng trước nhỏ hơn) --> (Include vào hiện tại)
        //      + Nếu previous lớn hơn:
        //          + Trừ đi vẫn bị <0 ==> so sánh với 0 là được
        //
        //- Với case diff <0:
        //  + The current diff --> Max(current diff - prev, 0):
        //      + Tức là nếu (đằng trước nhỏ hơn) --> (Include vào hiện tại)
        //      + Nếu previous lớn hơn:
        //          + Trừ đi vẫn bị <0 ==> so sánh với 0 là được
        //              ==> Ta sẽ lấy max.
        //
        //nums =    [2,3,4,6,2,3]
        //target =  [5,6,3,5,3,4]
        //diff   =  [3,3,-1,-1,1,1]
        //- Đến số cuối:
        //  + Nếu preDiff>=0 ==> Đã cộng rồi (Bằng cách liên quan bù của current diff tại)
        //  + Nếu preDiff<0 ==> Chưa cộng
        //      + Ta sẽ đổi dấu và cộng thêm là được.
        //====== CODE
        //return res+Math.max(-preDiff, 0);
        //======
        //
        //1.1, Optimization
        //- Space: O(1)
        //- Time: O(n)
        //
        System.out.println(minimumOperationsWrong(nums, target));
        System.out.println(minimumOperations(nums, target));
    }
}
