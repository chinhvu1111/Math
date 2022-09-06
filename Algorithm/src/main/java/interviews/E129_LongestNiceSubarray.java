package interviews;

public class E129_LongestNiceSubarray {

    public static int longestNiceSubarray(int[] nums) {
        int start=-1;
        int n=nums.length;
        int rs=0;
        int i;

        for(i=1;i<n;i++){
            if((nums[i]&nums[i-1])==1){
                rs=Math.max(rs, i-start-1);
                start=i-1;
                System.out.printf("%s,", i);
            }
        }
        rs=Math.max(rs, i-start-1);
        System.out.println();

        return rs;
    }

    public static void main(String[] args) {
//        int nums[]=new int[]{1,3,8,48,10};
        int nums[]=new int[]{3,1,5,11,13};
//        System.out.println(1&3);
        System.out.println(longestNiceSubarray(nums));
    }
}
