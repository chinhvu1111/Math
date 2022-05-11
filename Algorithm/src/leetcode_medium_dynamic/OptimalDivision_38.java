package leetcode_medium_dynamic;

public class OptimalDivision_38 {

    public static String optimalDivision(int[] nums) {
        int n=nums.length;

        if(n==1){
            return String.valueOf(nums[0]);
        }
        StringBuilder rs=new StringBuilder();
        rs.append(nums[0]).append("/");
        boolean isLengthGreaterThanTwo=false;

        if(n>2){
            isLengthGreaterThanTwo=true;
            rs.append("(");
        }

        for(int i=1;i<n;i++){
            rs.append(nums[i]);
            if(i!=n-1){
                rs.append("/");
            }
        }
        if(isLengthGreaterThanTwo) rs.append(")");
        return rs.toString();
    }

    public static void main(String[] args) {
        int arr[]=new int[]{2,3};
        System.out.println(optimalDivision(arr));
    }
}
