package leetcode_medium_dynamic;

import java.util.Arrays;

public class NumberOfLongestIncreasingSubsequence_44 {

    public static int findNumberOfLIS(int[] nums) {
        int n=nums.length;
        int[] dp=new int[n];
        int rs=0;
        int max=1;
        Arrays.fill(dp, 1);

        for(int i=0;i<n;i++){
            int maxLength=0;

            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    maxLength=Math.max(maxLength, dp[j]);
                }
            }
            dp[i]=maxLength+1;
            max=Math.max(max, dp[i]);
        }
        if(max==1) return n;

        for(int i=0;i<n;i++){
            if(dp[i]==max-1){
                for(int j=i+1;j<n;j++){
                    if(nums[j]>nums[i]){
                        rs+=dp[i];
                    }
                }
            }
        }
        return rs;
    }

    public static int findNumberOfLIS1(int[] nums) {
        int n=nums.length;
        int[] dp=new int[n];
        int count[]=new int[n];
        int rs=0;
        int max=1;
        Arrays.fill(dp, 1);
        count[0]=1;

        for(int i=0;i<n;i++){
            int maxLength=0;
            int currentCount=0;

            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    if(maxLength<dp[j]){
                        maxLength=dp[j];
                    }
                }
            }
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    if(maxLength==dp[j]){
                        currentCount+=count[j];
                    }
                }
            }
            dp[i]=maxLength+1;
            if(currentCount!=0) count[i]+=currentCount;
            else count[i]=1;
            max=Math.max(max, dp[i]);
        }
        if(max==1) return n;

        for(int i=0;i<n;i++){
            if(dp[i]==max-1){
                for(int j=i+1;j<n;j++){
                    if(nums[j]>nums[i]){
                        rs+=count[i];
                    }
                }
            }
        }
        return rs;
    }

    public static int findNumberOfLIS2(int[] nums) {
        int n=nums.length;
        int[] dp=new int[n];
        int[] c=new int[n];

        Arrays.fill(dp,1);
        Arrays.fill(c,1);

        int max=1;

        for(int i=1;i<n;i++)
        {
            int maxCount=0;

            for(int j=0;j<i;j++)
            {
                if(nums[i]>nums[j])
                {
                    if(dp[j]+1 > dp[i])//same subsequence
                    {
                        dp[i]=dp[j]+1;
                        maxCount=c[j];
                    }
                    else if(dp[j]+1==dp[i])//different subsequence
                    {
//                        c[i]+=c[j];
                        maxCount+=c[j];
                    }

                }
            }
            max=Math.max(dp[i],max);
            if(maxCount!=0){
                c[i]=maxCount;
            }else{
                c[i]=1;
            }
        }

        int count=0;
        for(int i=0;i<n;i++)
            if(dp[i]==max)
                count+=c[i];

        return count;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,3,5,4,7};
//        int arr[]=new int[]{2,2,2,2,2};
//        int arr[]=new int[]{1,2,4,3,5,4,7,2};
        int arr[]=new int[]{3,1,2};
        //C??ch 1:
        //T?? duy b??i n??y nh?? sau:
        //1, V???n l?? ????? ph???c t???p O(n^2) ??? ????y ta c?? 2 ??i???u c???n l??u:
        //+ Chi???u d??i c???a chu???i t??ng d???n li??n ti???p:
        //VD: 1,3,4,(2),6 : length=4
        //--> Ta s??? l??u t???i dp[i]
        //+ S??? l?????ng chu???i t??ng d???n n???u k???t th??c t???i v??? tr?? (i)
        //--> Ta s??? l??u s??? l?????ng chu???i t??ng d???n t???i v??? tr?? (i)
        //--> Ta s??? l??u t???i v??? tr?? count[i]
        //2, N???u ?????m s??? chu???i c?? ????? d??i Max
        //?????i v???i m???i (i) --> Ta ph???i so s??nh (nums[i] > nums[j]) --> max=Max(dp[j])
        //--> dp[i]=dp[j]+1
        //2.1, Sau khi t??m ???????c max --> Ta c???n t??nh s??? l?????ng chu???i k???t th??c t???i v??? tr?? (i)
        //if(dp[i]==max) rs+=count[i]
        //count[i]=rs
        //3, ??o???n cu???i t??? v??? tr?? (= max_length-1) ==> (i) --> (i --> n-1)
        // ++ h???t t???t c??? c??c (rs)

        //C??ch 2:
        //1, Ta v???n d??ng 2 m???ng ????? l??u:
        //dp[i]: ????? l??u ????? d??i t??ng d???n max c???a chu???i k???t th??c t???i v??? tr?? (i)
        //count[i]: L?? s??? l?????ng chu???i d??i nh???t max nh???t k???t th??c t???i v??? tr?? (i)
        //nums[i]>nums[j]
        //--> dp[i]=dp[j]+1
        //1.1, N???u t??m ???????c gi?? tr??? ph?? h???p h??n: dp[i] < dp[j]+1
        //--> Update count[i]=count[j]
        //1.2, N???u t??m ???????c dp[i]=dp[j]+1
        //--> count[i]+=count[j] (T???ng c??c chu???i m?? = length_max-1)
        System.out.println(findNumberOfLIS(arr));
        System.out.println(findNumberOfLIS1(arr));
        System.out.println(findNumberOfLIS2(arr));
    }
}
