package contest;

public class E65_ShortestSubarrayWithORAtLeastKI {

    public static boolean isValid(int[] nums, int k, int x, int y){
        int rs=0;
        for(int i=x;i<=y;i++){
            rs = rs | nums[i];
//            System.out.println(rs);
            if(rs>=k){
                return true;
            }
        }
        return false;
    }

    public static int minimumSubarrayLength(int[] nums, int k) {
        int n= nums.length;
        for(int i=1;i<=n;i++){
            for(int j=0;i+j-1<n;j++){
                int l = i+j-1;
//                System.out.printf("%s %s\n", i, l);
                if(isValid(nums, k, j, l)){
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an array nums of (non-negative integers) and an integer k.
        //An array is called special if the (bitwise OR of all of its elements) is at least k.
        //* Return the length of (the shortest special non-empty subarray of nums), or return -1 if no special subarray exists.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 50
        //0 <= nums[i] <= 50
        //0 <= k < 64
        //
        //- Brainstorm
        //1 or 2 or 4 or 12
        //
//        int[] nums = {1,2,3};
//        int k = 2;
        int[] nums = {2,1,8};
        int k = 10;
        System.out.println(minimumSubarrayLength(nums, k));
    }
}
