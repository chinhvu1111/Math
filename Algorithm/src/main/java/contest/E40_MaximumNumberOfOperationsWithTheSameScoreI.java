package contest;

public class E40_MaximumNumberOfOperationsWithTheSameScoreI {

    public static int maxOperations(int[] nums) {
        int n=nums.length;
        if(n<2){
            return 0;
        }
        int rs=1;
        int fixedSum;
        int curSum=0;
        fixedSum=nums[0]+nums[1];

        for(int i=2;i<n;i++){
            curSum+=nums[i];
            if((i+1)%2==0){
                if(curSum==fixedSum){
                    rs++;
                }else{
                    break;
                }
                curSum=0;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given an array of integers called nums, you can perform the following operation while nums contains at least 2 elements:
        //+ Choose the first two elements of nums and delete them.
        //+ The score of the operation is the sum of the deleted elements.
        //Your task is to find the maximum number of operations that can be performed, such that all operations have the same score.
        //* Return the (maximum number of operations) possible that satisfy the condition mentioned above.
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //2 <= nums.length <= 100
        //1 <= nums[i] <= 1000
        //
        //- Brainstorm
        //
//        int[] nums = {3,2,1,4,5};
        int[] nums = {3,2,6,1,4};
        System.out.println(maxOperations(nums));
    }
}
