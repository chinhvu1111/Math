package contest;

import java.util.Arrays;

public class E206_MinimumDivisionOperationsToMakeArrayNonDecreasing {

    public static int minOperations(int[] nums) {
        int operations = 0;

        // Start from the second last element and move backwards
        for (int i = nums.length - 1; i > 0; i--) {
            // If current element is smaller than previous, adjust the previous
            if (nums[i] < nums[i - 1]) {
                int required = (nums[i - 1] + nums[i] - 1) / nums[i];
                if (required == 1) return -1; // Cannot reduce further
                nums[i - 1] /= required;
                operations += required - 1;
            }
        }

        return operations;
    }

    public static int minOperations1(int[] nums) {
        int[] flynorpexel = Arrays.copyOf(nums, nums.length);
        int operations = 0;

        //Time: O(n)
        for (int i = nums.length - 1; i > 0; i--) {
            while (flynorpexel[i] < flynorpexel[i - 1]) {
                //Time: O(1000)
                int greatestProperDivisor = findGreatestProperDivisor(flynorpexel[i - 1]);

                if (greatestProperDivisor == 1) {
                    return -1; // Cannot reduce the number further
                }

                //Chia dần dần để
                //+ lynorpexel[i] < flynorpexel[i - 1]
                //- Nhưng greatestProperDivisor sẽ được tính đi tính lại dựa trên flynorpexel[i - 1]
                flynorpexel[i - 1] /= greatestProperDivisor;
                operations++;
            }
        }

        return operations;
    }
    private static int findGreatestProperDivisor(int x) {
        if ((x & 1) == 0)
            return x / 2;
        int i = 3;
        while (i * i <= x) {
            if (x % i == 0) {
                return x / i;
            }
            i = i + 2;
        }
        return 1;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer array nums.
        //- (Any positive divisor) of a natural number x that is strictly less than x is called a proper divisor of x.
        // For example, 2 is a proper divisor of 4, while (6 is not a proper divisor of 6).
        //- You are allowed to perform an operation (any number of times) on nums,
        // where in each operation you select any one element from nums and divide it by its greatest proper divisor.
        //
        //* Return (the ("minimum") number of operations) required to make the array (non-decreasing).
        //- If it is not possible to make the array non-decreasing using any number of operations, return -1.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^6
        //  + n khá lớn, time: O(n)
        //
        //** Brainstorm
        //
        //- Làm array increase
        //Ex:
        //nums = [25,7]
        //
        //(a,b)
        //(a/x)/y <= b
        //25/5<=7
        //
        //- Even
        //16 -> 2 : Luôn luôn ra số nhỏ nhất
        //- Odd:
        //13*21 = 273
        //25*21 = 525
        //  + Chia cho số max divisor ==> số còn lại chắc chắn min
        //  ==> Chỉ cần chia 1 lần rồi check là được.
        //
        int[] nums={25,7};
        System.out.println(minOperations1(nums));
        System.out.println(minOperations(nums));
    }
}
