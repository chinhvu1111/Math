package contest;

public class E37_LargestElementInAnArrayAfterMergeOperations {
    public long maxArrayValue(int[] nums) {
        int n=nums.length;
        long rs=nums[n-1];

        for(int i=n-1;i>=1;i--){
            if(rs>=nums[i-1]){
                rs+=nums[i-1];
            }else {
                rs=nums[i-1];
            }
        }
        return rs;
    }
    public static void main(String[] args) {
        int[] arr=new int[]{2,3,7,9,3};
        //Thực hiện trên array nhiều lần
        //Ex:
        //
    }
}
