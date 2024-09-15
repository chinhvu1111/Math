package E1_bitmanipulation;

public class E14_CountNumberOfMaximumBitwiseORSubsets {

    public static int countMaxOrSubsets(int[] nums) {
        int max=0;
        int n = nums.length;
        int size = (1<<n)-1;
        int rs=0;

        for(int e: nums){
            max=max | e;
        }
//        System.out.printf("Max: %s\n", max);

        for(int i=1;i<=size;i++){
            int temp = i;
            int index=0;
            int curOrVal=0;
//            System.out.printf("Value: %s\n", i);
            while (temp!=0){
                int curBit=temp%2;
//                System.out.printf("%s",curBit);
                if(curBit==1){
                    curOrVal = curOrVal | nums[index];
                }
                temp=temp/2;
                index++;
            }
//            System.out.printf("\nRs: %s\n", curOrVal);
            if(curOrVal==max){
                rs++;
            }
        }
        return rs;
    }

    public static int count=0;

    public static void solution(int index, int[] nums, int curVal, int n, int max){
        if(index==n){
            if(curVal==max){
                count++;
            }
            return;
        }
        solution(index+1, nums, curVal|nums[index], n, max);
        solution(index+1, nums, curVal, n, max);
    }

    public static int countMaxOrSubsetsOptimization(int[] nums) {
        int max=0;
        count=0;

        for(int e: nums){
            max=max | e;
        }
//        System.out.printf("Max: %s\n", max);
        solution(0, nums, 0, nums.length, max);
        return count;
    }

    public static int countMaxOrSubsetsRefer(int[] nums) {
        subsets(nums, 0, 0);
        return count;
    }

    static int count1 = 0;
    static int maxOR = 0;

    private static void subsets(int[] arr, int vidx, int OR){

        if(vidx == arr.length){

            if(OR == maxOR){
                count1 ++;
            }else if(OR > maxOR){
                count1 = 1;
                maxOR = OR;
            }

            return;
        }
        // include
        subsets(arr, vidx+1, OR | arr[vidx]);

        // exclude
        subsets(arr, vidx+1, OR);
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given (an integer array nums), find (the maximum possible bitwise OR of a subset of nums) and
        //* return (the number of different non-empty subsets) with (the maximum bitwise OR).
        //- An array a is (a subset of an array b) if a can be obtained from b by deleting some (possibly zero) (elements of b).
        //- Two subsets are considered different if (the indices of the elements "chosen") are different.
        //+ The bitwise OR of an array a is equal to a[0] OR a[1] OR ... OR a[a.length - 1] (0-indexed).
        //
        //- Subset là tập hợp trong nums không cần continues
        //- Count số lượng subset có MAX OR.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 16
        //1 <= nums[i] <= 10^5
        //  + Length của nums <=16 khá nhỏ:
        //      + Time: O(n^3)
        //      ==> Ta có thể loop bằng binary toàn bộ các subsets cũng được
        //      + BRUTE FORCE cũng được.
        //
        //- Brainstorm
        //- Càng OR nhiều thì value càng bigger
        //  + Tìm max nhất bằng cách OR all of elements
        //- Brute force bằng bit cũng được.
        //
        //1.1, Optimization
        //
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(2^n*n)(Iteration)
        //  + Recursion: O(2^n)
        //
//        System.out.println(1<<16);
        System.out.println(1<<1);
        int[] nums = {3,2,1,5};
        System.out.println(countMaxOrSubsets(nums));
        System.out.println(countMaxOrSubsetsOptimization(nums));
        System.out.println(countMaxOrSubsetsRefer(nums));
        //#Reference:
        //2275. Largest Combination With Bitwise AND Greater Than Zero
        //
    }
}
