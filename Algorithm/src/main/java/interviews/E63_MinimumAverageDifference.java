package interviews;

public class E63_MinimumAverageDifference {
    public static int minimumAverageDifference(int[] nums) {
        int n=nums.length;
        long prefix[]=new long[n];
        long sum=0;
        long rs=Integer.MAX_VALUE;
        int index=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefix[i]=sum;
        }
        for(int i=0;i<n;i++){
            int divisionRight=n-i-1==0?1:n-i-1;
            int currentValue= (int) (prefix[i]/(i+1)-(sum-prefix[i])/divisionRight);
            currentValue=Math.abs(currentValue);

            if(rs>currentValue){
                rs=currentValue;
                index=i;
            }
        }
        return index;
    }

    public static int minimumAverageDifferenceOptimized(int[] nums) {
        int n=nums.length;
        long sum=0;
        long rs=Integer.MAX_VALUE;
        int index=0;
        long lsum=0,rsum=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
        }
        for(int i=0;i<n;i++){
            lsum+=nums[i];
            rsum-=nums[i];
            int divL=i+1;
            int divR=n-i-1==0?1:n-i-1;
            int currentValue= (int) (lsum/divL-(sum+rsum)/divR);

            currentValue=Math.abs(currentValue);
            if(rs>currentValue){
                rs=currentValue;
                index=i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{2,5,3,9,5,3};
        System.out.println(minimumAverageDifference(arr));
        //Bài này liên quan đến tối ưu không tại prefix_sum[]
        //1, Sẽ chỉ sum( all elements)
        //2,
        // lSum+=nums[i]
        // rSum-=nums[i]
        //CT : (lsum/divL-(sum+rsum)/divR);
        System.out.println(minimumAverageDifferenceOptimized(arr));
    }
}
