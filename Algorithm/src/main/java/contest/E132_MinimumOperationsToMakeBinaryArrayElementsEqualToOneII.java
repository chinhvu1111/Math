package contest;

public class E132_MinimumOperationsToMakeBinaryArrayElementsEqualToOneII {

    public static int minOperations(int[] nums) {
        int n=nums.length;
        int flip=0;
        int rs=0;

        for(int i=0;i<n;i++){
            if(flip==1){
                if(nums[i]==1){
                    flip=0;
                    rs++;
                }else{
                    nums[i]=1;
                }
                continue;
            }
            if(nums[i]==0){
                flip++;
                nums[i]=1;
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a binary array nums.
        //- You can do the following operation on the array any number of times (possibly zero):
        //  + Choose (any index i) from the array and flip (all the elements from index i) to (the end of the array).
        //  + Flipping an element means changing its value from 0 to 1, and from 1 to 0.
        //* Return the minimum number of operations required to make all elements in nums equal to 1.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 10^5
        //0 <= nums[i] <= 1
        //
        //- Brainstorm
        //- Mỗi số flip 2 lần ==> Cũ
        //Ex
        //Input: nums = [0,1,1,0,1]
        //(0),1,1,0,1 : rs=1
        //  ->1,0,0,1,0
        //1,(0),0,1,0  : rs=2
        //  -> 1,1,1,0,1
        //1,1,1,0,1 : rs=3
        //-> 1,1,1,1,0
        //1,1,1,1,(0) : rs=4
        //
        //(0),1,1,0,1
        //  + numOne = 3
        //  + numZero = 1
        //- Đi từ cuối có được không?
        //(0),1,1,0,1 ==> Không được
        //- Cache lại số lượng flip thì sao
        //(0),1,1,0,1
        // + 1,1,1,0,1
        // + flip = 1
        //1,0,1,0,1
        // +
        //
        int[] nums= {0,1,1,0,1};
        System.out.println(minOperations(nums));
    }
}
