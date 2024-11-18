package contest;

public class E216_MakeArrayElementsEqualtoZero {

    public static boolean validLeft(int[] nums, int index, int init){
        int n=nums.length;
        int count=init;
        index+=count;
        while(index>=0&&index<n){
            if(nums[index]>0){
                nums[index]--;
                count=-1*count;
            }
            index+=count;
        }
        for(int i=0;i<n;i++){
            if(nums[i]!=0){
                return false;
            }
        }
        return true;
    }

    public static int countValidSelections(int[] nums) {
        int n=nums.length;
        int rs=0;

        for(int i=0;i<n;i++){
            if(nums[i]==0){
                int[] tmp=new int[n];
                System.arraycopy(nums, 0, tmp, 0, n);
                boolean leftIsValid = validLeft(tmp, i, -1);
                if(leftIsValid){
                    rs++;
                }
                System.arraycopy(nums, 0, tmp, 0, n);
                boolean rightIsValid = validLeft(tmp, i, 1);
                if(rightIsValid){
                    rs++;
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        int[] nums = {1,0,2,0,3};
        int[] nums = {0};
        System.out.println(countValidSelections(nums));
        //Example 1:
        //
        //Input: nums = [1,0,2,0,3]
        //Output: 2
        //Explanation:
        //The only possible valid selections are the following:
        //
        //Choose curr = 3, and a movement direction to the left.
        //[1,0,2,0,3] -> [1,0,2,0,3] -> [1,0,1,0,3] -> [1,0,1,0,3] -> [1,0,1,0,2] -> [1,0,1,0,2] -> [1,0,0,0,2] -> [1,0,0,0,2] -> [1,0,0,0,1] -> [1,0,0,0,1] -> [1,0,0,0,1] -> [1,0,0,0,1] -> [0,0,0,0,1] -> [0,0,0,0,1] -> [0,0,0,0,1] -> [0,0,0,0,1] -> [0,0,0,0,0].
        //Choose curr = 3, and a movement direction to the right.
        //[1,0,2,0,3] -> [1,0,2,0,3] -> [1,0,2,0,2] -> [1,0,2,0,2] -> [1,0,1,0,2] -> [1,0,1,0,2] -> [1,0,1,0,1] -> [1,0,1,0,1] -> [1,0,0,0,1] -> [1,0,0,0,1] -> [1,0,0,0,0] -> [1,0,0,0,0] -> [1,0,0,0,0] -> [1,0,0,0,0] -> [0,0,0,0,0].
        //
        //
        //
    }
}
