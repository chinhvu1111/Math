package E1_slide_window;

import java.util.HashMap;
import java.util.Map;

public class E16_SubarraysWithKDifferentIntegers {

    public static int subarraysWithKDistinctRefer(int[] nums, int k) {
        return slidingWindowAtMost(nums, k) - slidingWindowAtMost(nums, k - 1);
    }

    private static int slidingWindowAtMost(int[] nums, int distinctK) {
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int n=nums.length;
        int rs=0;
        int left=0;

        for(int i=0;i<n;i++){
            mapCount.put(nums[i], mapCount.getOrDefault(nums[i], 0)+1);
            while (mapCount.size()>distinctK){
                int curCount=mapCount.getOrDefault(nums[left], 0);
                if(curCount==1){
                    mapCount.remove(nums[left]);
                }else{
                    mapCount.put(nums[left], curCount-1);
                }
                left++;
            }
            rs+=i-left+1;
        }
        return rs;
    }

    public static int subarraysWithKDistinctWrong(int[] nums, int k) {
        int n=nums.length;
        int left=0;
        Map<Integer, Integer> mapCount=new HashMap<>();
        int rs=0;
        int rightMost=0;
        HashMap<Integer, Integer> leftMostIndex=new HashMap<>();

        for(int i=0;i<n;i++){
            int curCount=mapCount.getOrDefault(nums[i], 0);
            if(curCount==0){
                rightMost=i;
            }else if(nums[rightMost]==nums[i]){
                //Updated
                rightMost=-1;
            }
            mapCount.put(nums[i], curCount+1);
            leftMostIndex.put(nums[i], i);
            while(mapCount.size()>k){
                int count=mapCount.get(nums[left]);
                if(count==1){
                    mapCount.remove(nums[left]);
                    rs+=i-rightMost+1;
                    break;
                }else if(count==2){
                    rightMost=Math.max(rightMost, leftMostIndex.get(nums[left]));
                    rs+=i-rightMost+1;
                }else {
                    mapCount.put(nums[left], count-1);
                    rs+=i-rightMost+1;
                }
                left++;
            }
        }
        return rs;
    }

    public static int subarraysWithKDistinctRefer2(int[] nums, int k) {
        // Array to store the count of distinct values encountered
        int[] distinctCount = new int[nums.length + 1];

        int totalCount = 0;
        int left = 0;
        int right = 0;
        int currCount = 0;

        while (right < nums.length) {
            // Increment the count of the current element in the window
            if (distinctCount[nums[right++]]++ == 0) {
                // If encountering a new distinct element, decrement K
                k--;
            }

            // If K becomes negative, adjust the window from the left
            if (k < 0) {
                // Move the left pointer until the count of distinct elements becomes valid again
                --distinctCount[nums[left++]];
                k++;
                currCount = 0;
            }

            // If K becomes zero, calculate subarrays
            if (k == 0) {
                // While the count of left remains greater than 1, keep shrinking the window from the left
                while (distinctCount[nums[left]] > 1) {
                    --distinctCount[nums[left++]];
                    currCount++;
                }
                // Add the count of subarrays with K distinct elements to the total count
                totalCount += (currCount + 1);
            }
        }
        return totalCount;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given an integer array nums and (an integer k),
        //* return (the number of good subarrays) of nums.
        //- A good array is an array where (the number of different integers) in that array is (exactly k).
        //- For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
        //- A subarray is a contiguous part of an array.
        //- Đếm số subarray sao cho (the number of different integers == k)
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 2 * 10^4
        //1 <= nums[i], k <= nums.length
        //  + n khá lớn ==> Time: O(n)
        //
        //- Brainstorm
        //- Mình dùng prefix unique là được
        //
        //Example 1:
        //Input: nums = [1,2,1,2,3], k = 2
        //Output: 7
        //Explanation: Sub-arrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
        //prefix = [1,2,2,2,3]
        //+ count[i] số lương prefix có unique = i
        //  + Ta sẽ dùng count[i] trừ đi
        //+ count[index=4]-count[index=0] = 2
        //  + 3 - 1 = 2
        //  ==> Không thể hiện số unique values từ (index=2 -> 3)
        //  + vals = [2,1,2,3] (Số unique values = 3)
        //- Dùng prefix khó:
        //  + Dùng slide window được không?
        //
        //Example 1:
        //Input: nums = [1,2,1,2,3], k = 2
        //[1,2],[1,2,1]
        //- We just need to increase left to 1
        //  + If the number of unique number is greater than k
        //      + left++
        //  <> right++
        //  + Each loop increase the rs to 1
        //
        //
        int[] nums = {1,2,1,2,3};
        int k = 2;
        System.out.println(subarraysWithKDistinctWrong(nums, k));
        System.out.println(subarraysWithKDistinctRefer(nums, k));
        //- Nếu mà loop tăng left
        //[1,2]
        //[2,1] ==> Không đúng lắm
        //
        //[1,2,1,2,3]
        //[1,2,1,2]
        //  + rs+=3
        //  + Do nếu add(3) [1,2,1,2,3], unique size = 3
        //      + Xét riêng [1,2,1,2]
        //[1,2,1,2], unique size = 2
        //  + left++: [2,1,2], unique size = 2
        //      + rs+=1
        //  ==> Nếu tính ntn vẫn thiếu case [2,1]
        //- Tức là:
        //(1,2,1,2,1),[3]
        //  + Nếu bỏ số 1 đi thì (2 kết hợp đến đâu thì đủ (k==2) unique elements)
        //  + Thiếu mất case ở giữa: khi tăng right rồi:
        //      + Thiếu mất đoạn ( (bigger left) ==> Match với (smaller right) )
        //- Nếu xét bình thường thì cần xét hết:
        //[1,2,1,2],1
        //- Nếu xét đến (index==3):
        //  + 1,2,1,2
        //  + 2,1,2
        //  + 1,2
        //  ==> O(n^2) mất
        //[1,2,1,2],1
        //  + [2,1,2],1
        //      + Cần right-- ==>Để xem right nào thì phù hợp với left này
        //      ==> làm ntn thì vẫn slow do mỗi lần left++ ==> right -- lại
        //- Tìm phần tử right most mà có (count == 1)
        //  + Khi giảm count đi ==> từ (2 -> 1)
        //      + Lúc đó thì các element có count==2 cũng sẽ cần được add vào.
        //- Bản chất map có window:
        //  + Number has (count==1 + highest index)
        //      + Ta hoàn toàn có thể lưu được.
        //- ([a,b,c,d],[e,f,g])
        //  + Xét đến f ==> count[nums[f]] từ (a -> f) chỉ là 1
        //      + a chỉ cỏ thế kết hợp với (g->f)
        //  + Nếu remove(a):
        //      + count(a) !=1
        //          + Nó sẽ kết hợp với element có x mà x có (count==1)
        //          ==> Mà ta add hàng ngày
        //          + Mỗi lần (i) ==> Ta cần check lại xem cái rightMost có (count==1) hay không?
        //              + !=1 ==> update rightMost = -1
        //
        //- Ta thấy rằng (index=1):
        //  + map giảm đi 1 ==> nó phải giảm đi thằng có (count==1) + (max right)
        //  ==> Update dần thằng có (count==1) + (update right) là được
        //Ex:
        //[1,1,1,2,3]
        //
        //** Reference solution:
        //- số lượng subarray có số unique elements <= k
        //  + x
        //- số lượng subarray có số unique elements <= k-1
        //  + y
        //==> Số lượng subarrays có số unique element == k
        //  + rs = x-y
        //
        //* Kinh nghiệm:
        //- Exact(x) = at most (x) - at most(x-1)
        //
        //Ex:
        //[1,1,1,2,3]
        //+ số lượng subarray có nhiều nhất (k unique elements)
        //  + [1],[1,1],[1,1,1],[1,1,1,2],[1,1,1,2,3]
        //      + [1]: rs+=1
        //      + [1,1]: rs+=2
        //      + [1,1,1]:
        //          + [1] cuối sẽ kết hợp với:
        //              + []: đứng 1 mình
        //              + [1]: 1 đằng trước
        //              + [1,1]: 2 số 1 đằng trước
        //          ==> rs+=3 = right-left+1
        //          [1],[1,1],[1,1,1]
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
    }
}
