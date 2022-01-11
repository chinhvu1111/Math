package leetcode_medium;

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
        //Cách 1:
        //Tư duy bài này như sau:
        //1, Vẫn là độ phức tạp O(n^2) ở đây ta có 2 điều cần lưu:
        //+ Chiều dài của chuỗi tăng dần liên tiếp:
        //VD: 1,3,4,(2),6 : length=4
        //--> Ta sẽ lưu tại dp[i]
        //+ Số lượng chuỗi tăng dần nếu kết thúc tại vị trí (i)
        //--> Ta sẽ lưu số lượng chuỗi tăng dần tại vị trí (i)
        //--> Ta sẽ lưu tại vị trí count[i]
        //2, Nếu đếm số chuỗi có độ dài Max
        //Đối với mỗi (i) --> Ta phải so sánh (nums[i] > nums[j]) --> max=Max(dp[j])
        //--> dp[i]=dp[j]+1
        //2.1, Sau khi tìm được max --> Ta cần tính số lượng chuỗi kết thúc tại vị trí (i)
        //if(dp[i]==max) rs+=count[i]
        //count[i]=rs
        //3, Đoạn cuối từ vị trí (= max_length-1) ==> (i) --> (i --> n-1)
        // ++ hết tất cả các (rs)

        //Cách 2:
        //1, Ta vẫn dùng 2 mảng để lưu:
        //dp[i]: Để lưu độ dài tăng dần max của chuỗi kết thúc tại vị trí (i)
        //count[i]: Là số lượng chuỗi dài nhất max nhất kết thúc tại vị trí (i)
        //nums[i]>nums[j]
        //--> dp[i]=dp[j]+1
        //1.1, Nếu tìm được giá trị phù hợp hơn: dp[i] < dp[j]+1
        //--> Update count[i]=count[j]
        //1.2, Nếu tìm được dp[i]=dp[j]+1
        //--> count[i]+=count[j] (Tổng các chuỗi mà = length_max-1)
        System.out.println(findNumberOfLIS(arr));
        System.out.println(findNumberOfLIS1(arr));
        System.out.println(findNumberOfLIS2(arr));
    }
}
