package interviews;

public class E62_NumberOfWaysToSplitArray {

    public static int waysToSplitArray(int[] nums) {
        long sum=0;
        int n=nums.length;
        long prefix[]=new long[n];
        int rs=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefix[i]=sum;
        }
        for(int i=0;i<n-1;i++){
            if(sum<=2*prefix[i]){
                rs++;
            }
        }
        return rs;
    }

    public static int waysToSplitArrayOptimized(int[] nums) {
        long sum=0;
        int n=nums.length;
        long prefix[]=new long[n];
        int rs=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefix[i]=sum;
        }
        for(int i=0;i<n-1;i++){
            if(sum-prefix[i]<prefix[i]){
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{10,4,-8,7};
        int arr[]=new int[]{2,3,1,0};
        System.out.println(waysToSplitArray(arr));
    }
}
