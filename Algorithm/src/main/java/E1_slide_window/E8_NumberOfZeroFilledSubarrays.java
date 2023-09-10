package E1_slide_window;

public class E8_NumberOfZeroFilledSubarrays {

    public static long zeroFilledSubarray(int[] nums) {
        long count=0;
        long rs=0;

        for (int num : nums) {
            if (num == 0) {
                count++;
            } else {
                // System.out.printf("Index: %s, count: %s\n", i, count);
                rs += count * (count + 1) / 2;
                count = 0;
            }
        }
        // System.out.printf("Count: %s\n", count);
        rs+=count*(count+1)/2;
        return rs;
    }

    public static long zeroFilledSubarrayOptimization(int[] nums) {
        long count=0;
        long rs=0;

        for (int num : nums) {
            if (num == 0) {
                count++;
                rs+=count;
            } else {
                count = 0;
            }
        }
        // System.out.printf("Count: %s\n", count);
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //+ Given an integer array nums,
        //- A subarray is a contiguous non-empty sequence of elements within an array.
        //* return the number of subarrays filled with 0.
        //Ex:
        //nums = [1,3,0,0,2,0,0,4]
        //There are 4 occurrences of [0] as a subarray.
        //There are 2 occurrences of [0,0] as a subarray.
        //There is no occurrence of a subarray with a size more than 2 filled with 0. Therefore, we return 6.
        //
        //- Tức là đếm số subarray được filled toàn bằng 0
        //+ length=1 --> length=n
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= nums.length <= 10^5
        //-10^9 <= nums[i] <= 10^9
        //
        //0,0,0,0 : Số lượng subarray
        //1 : 4
        //2 : 3
        //3 : 2
        //4 : 1
        //number of subarray = n*(n+1)/2
        //Ex:
        //nums = [0,0,0,2,0,0]
        //0,0,0
        //1: 3
        //2: 2
        //3 : 1
        //
        //0,0
        //1: 2
        //2: 1
        //num = 3*4/2 + 2*3/2= 6+3 = 9
        //
        //- Idea
        //Ex:
        //nums = [1,3,0,0,2,0,0,4]
        //+ nums[i]==0 : count++
        //+ nums[i]==0 : count++
        //+ nums[i]!=0 : rs+= count*(count+1)/2, count=0
        //
        //1.1, Optimization
        //- Refactor 1 chút để việc thực hiện phép n*(n+1) là không cần thiết nữa
        //Ex:
        //0,0,0
        //count=1, count=2, count=3
        //+ count++ mỗi lần tìm thấy nums[i]==0
        //+ count=0 khi không tìm thấy nums[i]==0
        //Sum= (count1=1)+(count1=2)+(count1=3) = length*(length+1)/2= 3*(3+1)/2 ==> Ta chỉ cần cộng count của mỗi lần tìm thấy nums[i]==0 là được
        //
        //1.2, Complexity
        //+ N is the length of array
        //- Space : O(1)
        //- Time : O(n)
        //
        //#Reference:
        //2526. Find Consecutive Integers from a Data Stream
        int[] nums={1,3,0,0,2,0,0,4};
        System.out.println(zeroFilledSubarray(nums));
        System.out.println(zeroFilledSubarrayOptimization(nums));
    }
}