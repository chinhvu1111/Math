package interviews;

public class E83_MinimumSizeSubarraySum {

    public static int minSubArrayLen(int target, int[] nums) {
        int rs=Integer.MAX_VALUE;
        int left=0;
        int n=nums.length;
        long sum=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];

            while (sum>=target){
                rs=Math.min(rs, i-left+1);
                sum-=nums[left];
                left++;
            }
        }

        return rs==Integer.MAX_VALUE?0:rs;
    }

    public static int minSubArrayLenNLogN(int target, int[] nums) {
        int n=nums.length;
        int sum[]=new int[n];
        int rs=Integer.MAX_VALUE;

        if(nums.length>=1){
            sum[0]=nums[0];
        }
        for(int i=1;i<n;i++){
            sum[i]=sum[i-1]+nums[i];
        }
        for(int i=0;i<n;i++){
            int index=findNumberInRange(i, n-1, sum[i]+target, sum);
            if(index==-1){
                continue;
            };
//            System.out.printf("%s %s\n", i, n-1);
//            System.out.printf("%s\n", index);
            rs=Math.min(rs, index-i);
        }
        return rs==Integer.MAX_VALUE?0:rs;
    }

    public static int findNumberInRange(int low, int high, int key, int sum[]){

        if(low>=high-1){
//            System.out.printf("%s %s", low, key);
//            System.out.println();
            if(sum[low]>=key){
                return low;
            }
            if(sum[high]>=key){
                return high;
            }
            return -1;
        }
        int mid=low + (high-low)/2;
        int index;

        if(sum[mid]<key){
            index=findNumberInRange(mid+1, high, key, sum);
        }else{
            index=findNumberInRange(low, mid, key, sum);
        }
        return index;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{2,3,1,2,4,3};
//        int arr[]=new int[]{1,4,4};
        int arr[]=new int[]{1,1,1,1,1,1,1,1};
//        int k=7;
//        int k=4;
        int k=11;
        System.out.println(minSubArrayLen(k, arr));
        System.out.println(minSubArrayLenNLogN(k, arr));
        //Bài này tư duy như sau:
        //1, Vẫn là tư duy slice windows
        //1.1, Chú ý:
        // rs=Math.min(rs, i-left+1) ==> Phần này sẽ để trong While
        //--> Vì điều kiện của while:
        //+ while (sum>=target){ ==> Là điều kiện để lấy kết quả.
        //2, Complexity: nlog(n)
        //2.1,
        //Ở đây ta sẽ dùng prefix sum
        //VD:
        //nums: 2,3,1,2,4,3
        //sum : 2,5,6,8,12,15
        //+ Bởi vì nums[i]>=0 --> Prefix sum luôn tăng
        //2.2, Với mỗi vị trí (i) ta sẽ search trong khoảng (i ,n-1)
        //+ Vì ta hướng (sum continous = target)
        //--> Search dựa trên (sum[i]+target) --> ưu tiên low hơn high (Tất nhiên phải thỏa mãn (sum[low]>=key||sum[high]>=key))
        //+ return rs=Math.min(rs, index-i).
    }
}
