package interviews.bytedance;

import java.util.ArrayList;
import java.util.List;

public class E19_Birthday_interview {

    public static List<Integer> junieBirthday(List<Integer> nums, List<List<Integer>> questions) {
        // Write your code here
        int n=nums.size();
        //1,4,4,5,4,5,1,5,3,5,4,2,4,2,2,3,4,3
        //
        int prefixSum[][][]=new int[n][n][2];
        int sum=0;

        for(int i=0;i<n;i++){
            int currentSum=nums.get(i);
            if(nums.get(i)%12==0){
                prefixSum[i][i][0]=1;
            }
            if(nums.get(i)%23==0){
                prefixSum[i][i][1]=1;
            }
            for(int j=i-1;j>=0;j--){
                currentSum+=nums.get(j);
                if(currentSum%12==0){
                    prefixSum[j][i][0]=prefixSum[j+1][i][0]+1;
                }else{
                    prefixSum[j][i][0]=prefixSum[j+1][i][0];
                }

                if(currentSum%23==0){
                    prefixSum[j][i][1]=prefixSum[j+1][i][1]+1;
                }else{
                    prefixSum[j][i][1]=prefixSum[j+1][i][1];
                }
                if(nums.get(j)%12==0){
                    prefixSum[j][i][0]++;
                }
                if(nums.get(j)%23==0){
                    prefixSum[j][i][1]++;
                }
            }
        }
//        int[][][] dp=new int[n][n][2];
//        for(int i=0;i<n;i++){
//            if(nums.get(i)==12){
//                dp[i][i][0]=1;
//            }
//            if(nums.get(i)==23){
//                dp[i][i][1]=1;
//            }
//        }
//
//        for(int i=2;i<=n;i++){
//            for(int j=0;i+j-1<n;j++){
//                int k=i+j-1;
//                int rightValue=0;
//
//                if(j-1>=0){
//                    rightValue=prefixSum[j-1];
//                }
//                int currentSum=prefixSum[k]-rightValue;
//
//                if(currentSum%12==0){
//                    dp[j][k][0]=dp[j][k-1][0]+1;
//                }else{
//                    dp[j][k][0]=dp[j][k-1][0];
//                }
//                if(currentSum%23==0){
//                    dp[j][k][1]=dp[j][k-1][1]+1;
//                }else{
//                    dp[j][k][1]=dp[j][k-1][1];
//                }
//            }
//        }
        List<Integer> rs=new ArrayList<>();
        for(int i=0;i<questions.size();i++){
            List<Integer> currentList=questions.get(i);
            if(currentList.get(2)==12){
                rs.add(prefixSum[currentList.get(0)][currentList.get(1)][0]);
            }else{
                rs.add(prefixSum[currentList.get(0)][currentList.get(1)][1]);
            }
        }

        return rs;
    }

    public static void countbit(){
        int n=7;
        int rs=0;
        while (n!=0){
            rs++;
            n&=n-1;
        }
        System.out.println(rs);
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{1,4,4,5,4,5,1,5,3,5,4,2,4,2,2,3,4,3};
        int[] arr=new int[]{1,23,12,11};
        List<Integer> list=new ArrayList<>();
        List<List<Integer>> question=new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            list.add(arr[i]);
        }
        List<Integer> integers=new ArrayList<>();
//        integers.add(5);
//        integers.add(13);
//        integers.add(12);
//        integers.add(0);
//        integers.add(3);
//        integers.add(23);
        question.add(integers);
        System.out.println(junieBirthday(list, question));
        countbit();
    }
}
