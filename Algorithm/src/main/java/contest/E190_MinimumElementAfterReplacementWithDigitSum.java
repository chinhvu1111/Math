package contest;

public class E190_MinimumElementAfterReplacementWithDigitSum {

    public static int minElement(int[] nums) {
        int n=nums.length;
        int rs=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int sum=0;
            int temp = nums[i];
            while(temp!=0){
                sum+=temp%10;
                temp=temp/10;
            }
            nums[i]=sum;
            rs=Math.min(rs, nums[i]);
        }
        return rs;
    }

    public static void main(String[] args) {

    }
}
