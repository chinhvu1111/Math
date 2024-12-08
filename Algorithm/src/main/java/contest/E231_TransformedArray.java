package contest;

public class E231_TransformedArray {

    public static int[] constructTransformedArray(int[] nums) {
        int n=nums.length;
        int[] rs=new int[n];

        for(int i=0;i<n;i++){
            if(nums[i]>0){
                int nextIndex=(i+nums[i])%n;
                rs[i]=nums[nextIndex];
            }else if(nums[i]<0){
                //nums = [0,(1),3,5]
                //+ i=1, move = 3
                //  + nextIndex=2
                //  = i-3+(n) = 1-3 + 4 = -2+4 = 2
                //
                int nextIndex=i-Math.abs(nums[i]);
                nextIndex=nextIndex%n;
                if(nextIndex<0){
                    //0,1,2,3
                    nextIndex+=n;
                }
                rs[i]=nums[nextIndex];
            }else{
                rs[i]=nums[i];
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //You are given an integer array nums that represents a circular array. Your task is to create a new array result of the same size,
        // following these rules:
        //
        //For each index i (where 0 <= i < nums.length), perform the following independent actions:
        //  + If nums[i] > 0: Start at index i and move nums[i] steps to the right in the circular array.
        // Set result[i] to the value of the index where you land.
        //  + If nums[i] < 0: Start at index i and move abs(nums[i]) steps to the left in the circular array.
        // Set result[i] to the value of the index where you land.
        //  + If nums[i] == 0: Set result[i] to nums[i].
        //Return the new array result.
        //
        //Note: Since nums is circular, moving past the last element wraps around to the beginning,
        // and moving before the first element wraps back to the end.
        //
//        int[] nums = {3,-2,1,1};
        int[] nums = {3,-20,1,1};
//        int[] nums = {-10};
        //move left = -20
        //==> remaining = -19
        //19 ==> -19%4 = 3
        int[] rs = constructTransformedArray(nums);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
        System.out.println(-19%4);
    }
}
