package leetcode_medium;

import java.util.Arrays;
import java.util.HashMap;

public class LengthOfLongestFibonacciSubsequence_61 {

    public static int lenLongestFibSubseqWrong(int[] arr) {
        int n=arr.length;
        //Kinh nghiệm: sẽ lưu index
        HashMap<Integer, Integer> mapLength=new HashMap<>();
        //Array sẽ lưu length --> để lúc tính tăng tốc độ
        //Index sẽ bị giới hạn
        int dp[]=new int[n];
        int rs=0;
        Arrays.fill(dp, 1);

        for(int i=0;i<n;i++){
            mapLength.put(arr[i],i);
        }
        for(int i=1;i<n;i++){
            int max=0;
            for(int j=i-1;j>=0;j--){
                int sub=arr[i]-arr[j];
                Integer indexBefore=mapLength.get(sub);

                //1. Chỉ nhận là chưa có phần tử nào trước nó --> Mới tính là (2)
                //2. Số này có khả năng nhân bản
                // VD: 2,4
                //1,2 --> Coi như là =2 luôn vì chắc chắn nó không thuộc dãy của số trước đó
                if((indexBefore==null&&dp[j]==1)||(indexBefore!=null&&indexBefore==j)){
                    max=Math.max(2, max);
                    continue;
                }
                if(indexBefore==null||indexBefore>=j||arr[indexBefore]>arr[j]){
                    continue;
                }
                int presub=arr[j]-sub;
                Integer indexBeforePre=mapLength.get(presub);

                if(indexBeforePre==null&&dp[indexBefore]>1){
                    continue;
                }
                //Điều kiện dp[indexBefore]!=1
                //Tức là với index tìm được của arr[j]-sub (IndexBefore) so sánh với IndexBeforePre
                if(indexBefore!=0&&indexBeforePre!=null
                        &&dp[indexBeforePre]+1!=dp[indexBefore]
//                        &&(dp[indexBefore]!=1&&(indexBeforePre>=indexBefore||arr[indexBeforePre]>=arr[indexBefore]))
                        ){
                    continue;
                }
                max=Math.max(max, dp[indexBefore]+2);
            }
            dp[i]=Math.max(dp[i], max);
            rs=Math.max(rs, max);
        }
        return rs>=3?rs:0;
    }

//    public static int lenLongestFibSubseq(int[] arr) {
//        int n=arr.length;
//        //Kinh nghiệm: sẽ lưu index
//        int dp[][]=new int[n][n];
//
//        for(int i=0;i<n;i++){
//            for(int j=i-1;j>=0;j--){
//
//            }
//        }
//        return rs>=3?rs:0;
//    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,3,7,11,12,14,18};
//        int arr[]=new int[]{1,2,3,4,5,6,7,8};
//        int arr[]=new int[]{2,4,5,6,7,8,11,13,14,15,21,22,34};
//        int arr[]=new int[]{1,3,7,11,12,14,18};
//        int arr[]=new int[]{1,2,3,4,5,6,7,8};
//        int arr[]=new int[]{2,5,6,7,8,10,12,17,24,41,65};
        //{7,17,24,41,65}
//        int arr[]=new int[]{1,3,4,7,10,11,12,18,23,35};
        int arr[]=new int[]{3,4,9,10,12,14,15,24,27,38,42};

        System.out.println(lenLongestFibSubseqWrong(arr));
    }
}
