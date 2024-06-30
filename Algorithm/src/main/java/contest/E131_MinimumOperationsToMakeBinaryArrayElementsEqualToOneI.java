package contest;

public class E131_MinimumOperationsToMakeBinaryArrayElementsEqualToOneI {

    public static int minOperations(int[] nums) {
        int n=nums.length;
        int rs=0;

        for(int i=0;i<n;i++){
            if(nums[i]==0){
                if(i+1<n){
                    nums[i+1]=1-nums[i+1];
                }else{
                    continue;
                }
                if(i+2<n){
                    nums[i+2]=1-nums[i+2];
                }else{
                    continue;
                }
                nums[i]=1;
                rs++;
            }
        }

        for(int i=0;i<n;i++){
            if(nums[i]==0){
                return -1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a binary array nums.
        //- You can do the following operation on the array (any number of times) (possibly zero):
        //  + Choose (any 3 consecutive elements) from the array and (flip all of them).
        //  + Flipping (an element) means changing its value from 0 to 1, and from 1 to 0.
        //* Return (the minimum number of operations) required to make (all elements) in nums equal to "1". If it is impossible, return -1.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //3 <= nums.length <= 10^5
        //0 <= nums[i] <= 1
        //==> Time: O(n)
        //
        //- Brainstorm
        //Ex:
        //Example 1:
        //Input: nums = [0,1,1,1,0,0]
        //Output: 3
        //Explanation:
        //We can do the following operations:
        //Choose the elements at indices 0, 1 and 2. The resulting array is nums = [1,0,0,1,0,0].
        //Choose the elements at indices 1, 2 and 3. The resulting array is nums = [1,1,1,0,0,0].
        //Choose the elements at indices 3, 4 and 5. The resulting array is nums = [1,1,1,1,1,1].
        //
        //- Mỗi lần phải chọn 3 indexes liên tiếp mới được
        //Ex:
        //nums = [0,1,1,1,0,0]
        //nums = [1,1,1,1,1,1]
        //0,1,1,1,0,0
        //->
        //0,1,1,0,1,1
        //->
        //1,0,0,0,1,1
        //->
        //1,1,1,1,1,1
        //
        //+ Add 1 vào cuối:
        //0,1,1,1,0,0,1
        //--> Nên swap ntn
        //
        //
        //1,0,0
        //=> Không thể được
        //0,0,1,0,0
        //=>
        //0,0,0,1,1
        //=>
        //1,1,1,1,1
        //
        //Ex:
        //nums = [1,1,0,0,1,1,1,0,0]
        //
        //
        //nums = [0,1,1,1,0,0]
        //[1,0,0,1,1,0,0]
        //[1,1,1,0,1,0,0]
        //[1,1,1,1,0,0,0]
        //[1,1,1,1,1,1,1]
    }
}
