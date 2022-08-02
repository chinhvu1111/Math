package interviews;

import java.util.Arrays;

public class E100_SplitArrayLargestSum {
    public static int splitArrayDynamic(int[] nums, int m) {
        int n=nums.length;
        int dp[][]=new int[n][m+1];
        int prefixSum[]=new int[n];
        int sum=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefixSum[i]=sum;
            dp[i][1]=sum;
        }

        for(int i=2;i<=m;i++){

            for(int j=i-1;j<n;j++){
                int min=Integer.MAX_VALUE;

                for(int h=j-1;h>=i-2;h--){
//                    int left=0;
//
//                    if(h>=1){
//                        left=prefixSum[h-1];
//                    }
                    min=Math.min(min, Math.max(prefixSum[j]-prefixSum[h], dp[h][i-1]));
                }
                dp[j][i]=min;
            }
        }

        return dp[n-1][m];
    }

    public static int splitArrayBinarySearch(int[] nums, int m) {
        int right=1_000_000*50;
        int n=nums.length;
        int left=0;
        int rs=Integer.MAX_VALUE;

        if(m==1){
            return Arrays.stream(nums).sum();
        }

        while(left<=right){
            int mid=(left+right)/2;
            int valueRs=0;
//            System.out.println(left+" "+right);
//            System.out.println(mid);
            int sum=0;
            int currentRs=0;
//            boolean isValid=true;

            for(int i=0;i<n;i++){
                sum+=nums[i];

//                if(nums[i]>mid){
//                    isValid=false;
//                    break;
//                }
                if(sum>mid){
                    valueRs++;
                    currentRs=Math.max(currentRs, sum-nums[i]);
                    sum=nums[i];
                    if(nums[i]==mid){
                        valueRs++;
                        sum=0;
                    }
                }
            }
            currentRs=Math.max(currentRs, sum);
            if(sum!=0){
                valueRs++;
            }

            if(valueRs>m){
                left=mid+1;
            }else if(valueRs<m){
                right=mid-1;
            }else{
                if(rs>currentRs){
                    rs= currentRs;
                    right=mid-1;
                }else{
                    right=mid;
                }
            }
            if(mid==left&&mid==right){
                break;
            }
        }
        if(rs==Integer.MAX_VALUE){
            int maxValue=Arrays.stream(nums).max().getAsInt();
            rs=maxValue;
        }

        return rs;
    }

    public static int maxValue(int mid, int nums[]){
        int sum=0;
        int currentRs=0;
        int valueRs=0;

        for(int i=0;i<nums.length;i++){
            sum+=nums[i];

            if(sum>mid){
                valueRs++;
                currentRs=Math.max(currentRs, sum-nums[i]);
                sum=nums[i];
            }
        }
        currentRs=Math.max(currentRs, sum);
        if(sum!=0){
            valueRs++;
        }
        System.out.println(valueRs);
        return currentRs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{7,2,5,10,8};
//        int m=2;
//        int arr[]=new int[]{1,2,3,4,5};
//        int m=2;
//        int arr[]=new int[]{1,4,4};
//        int m=3;
//        int arr[]=new int[]{2,3,1,2,4,3};
//        int m=5;
        //Case 1: n=1
//        int arr[]=new int[]{0};
//        int m=1;
//        int arr[]=new int[]{1,2,3,4,5};
//        int m=1;
        int arr[]=new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,50,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,150,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,200,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,250,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,300,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,350,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,400,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,450,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,500,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,550,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,600,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,650,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,700,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,750,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,800,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,850,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,900,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,950,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int m=50;
        System.out.println(maxValue(0, arr));
        System.out.println(splitArrayDynamic(arr, m));
        System.out.println(splitArrayBinarySearch(arr, m));
        //Bài này tư duy như sau:
        //https://leetcode.com/problems/split-array-largest-sum/discuss/1904499/JAVA-or-0ms-100-or-Clean-Simplest-Solution-with-comments
        //
    }
}
