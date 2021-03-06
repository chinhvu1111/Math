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
        //B??i n??y t?? duy nh?? sau:
        //1, V???n l?? t?? duy slice windows
        //1.1, Ch?? ??:
        // rs=Math.min(rs, i-left+1) ==> Ph???n n??y s??? ????? trong While
        //--> V?? ??i???u ki???n c???a while:
        //+ while (sum>=target){ ==> L?? ??i???u ki???n ????? l???y k???t qu???.
        //2, Complexity: nlog(n)
        //2.1,
        //??? ????y ta s??? d??ng prefix sum
        //VD:
        //nums: 2,3,1,2,4,3
        //sum : 2,5,6,8,12,15
        //+ B???i v?? nums[i]>=0 --> Prefix sum lu??n t??ng
        //2.2, V???i m???i v??? tr?? (i) ta s??? search trong kho???ng (i ,n-1)
        //+ V?? ta h?????ng (sum continous = target)
        //--> Search d???a tr??n (sum[i]+target) --> ??u ti??n low h??n high (T???t nhi??n ph???i th???a m??n (sum[low]>=key||sum[high]>=key))
        //+ return rs=Math.min(rs, index-i).
    }
}
