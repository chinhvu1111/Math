package leetcode_medium_dynamic;

import java.util.Arrays;
import java.util.HashMap;

public class E61_LengthOfLongestFibonacciSubsequence {

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

    public static int lenLongestFibSubseqSlow(int[] arr) {
        int n=arr.length;
        //Kinh nghiệm: sẽ lưu index
        HashMap<Integer, Integer> mapLength=new HashMap<>();
        //Array sẽ lưu length --> để lúc tính tăng tốc độ
        //Index sẽ bị giới hạn
        int dp[][]=new int[n][n];
        int rs=0;

        for(int i=0;i<n;i++){
            mapLength.put(arr[i],i);
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i!=j){
                    dp[i][j]=2;
                }else{
                    dp[i][j]=1;
                }
            }
        }
        for(int i=1;i<n;i++){
            for(int j=i-1;j>=0;j--){
                int sub=arr[i]-arr[j];
                Integer indexBefore=mapLength.get(sub);

                if(indexBefore==null||indexBefore>=j){
                    continue;
                }
                dp[i][j]=dp[j][indexBefore]+1;
                rs=Math.max(rs, dp[i][j]);
            }
        }
        return rs>=3?rs:0;
    }

    public static int lenLongestFibSubseqTwoPointer(int[] arr){
        int n=arr.length;
        int dp[][]=new int[n][n];
        int rs=0;

        for(int i=2;i<n;i++){
            int currentValue=arr[i];
            int low=0;
            int high=i-1;

            while (low<high){
                int lowValue=arr[low];
                int highValue=arr[high];
                if(currentValue-highValue>lowValue){
                    low++;
                }else if(currentValue-highValue<lowValue){
                    high--;
                }else{
                    dp[i][high]=dp[high][low]+1;
                    rs=Math.max(dp[i][high], rs);
                    high--;
                    low++;
                }
            }
        }
        return rs>=1?rs+2:0;
    }

    public static int lenLongestFibSubseq(int[] arr) {
        int len = arr.length;
        int max = 0;
        int[][] dp = new int[len][len];
        for (int i = 2; i < len; i++) {  // at least start from 2
            int lo = 0, hi = i-1;
            while (lo < hi) {
                int sum = arr[lo] + arr[hi];
                if (sum < arr[i]) {
                    lo++;
                } else if (sum > arr[i]) {
                    hi--;
                } else { //sum == arr[i]
                    dp[hi][i] = dp[lo][hi] + 1;
                    max = Math.max(max, dp[hi][i]);
                    lo++;
                    hi--;
                }
            }
        }
        return max > 0 ? max+2 : 0; // count the started 2 num in
    }

    public static int lenLongestFibSubseqOptimize(int[] arr) {
        int n=arr.length;
        //Kinh nghiệm: sẽ lưu index
        HashMap<Integer, Integer> mapLength=new HashMap<>();
        //Array sẽ lưu length --> để lúc tính tăng tốc độ
        //Index sẽ bị giới hạn
        int dp[][]=new int[n][n];
        int rs=0;

        for(int i=0;i<n;i++){
            mapLength.put(arr[i],i);
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i!=j){
                    dp[i][j]=2;
                }else{
                    dp[i][j]=1;
                }
            }
        }
        for(int i=1;i<n;i++){
            for(int j=i-1;j>=0;j--){
                int sub=arr[i]-arr[j];
                Integer indexBefore=mapLength.get(sub);

                if(indexBefore==null||indexBefore>=j){
                    continue;
                }
                dp[i][j]=dp[j][indexBefore]+1;
                rs=Math.max(rs, dp[i][j]);
            }
        }
        return rs>=3?rs:0;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{1,3,7,11,12,14,18};
//        int arr[]=new int[]{1,2,3,4,5,6,7,8};
//        int arr[]=new int[]{2,4,5,6,7,8,11,13,14,15,21,22,34};
//        int arr[]=new int[]{1,3,7,11,12,14,18};
//        int arr[]=new int[]{1,2,3,4,5,6,7,8};
//        int arr[]=new int[]{2,5,6,7,8,10,12,17,24,41,65};
        //{7,17,24,41,65}
//        int arr[]=new int[]{1,3,4,7,10,11,12,18,23,35};

//        int arr[]=new int[]{3,4,9,10,12,14,15,24,27,38,42};
        //Case 1: 3(0),12(4),15(6),27(8),42(10)
        //Xét thiếu trường hợp ddp[i][j]=dp[j][k]+1
        //k lấy từ map ra phải thỏa mãn điều kiện: (k != null&& k <= j)
//        System.out.println(lenLongestFibSubseqWrong(arr));
//        int arr[]=new int[]{};
//        int arr[]=new int[]{1,2,3};
//        int arr[]=new int[]{1};
        System.out.println(lenLongestFibSubseqOptimize(arr));
        //Bài này tư duy như sau:
        //1, - Với những bài có chuỗi tăng dần, thường sẽ tối ưu được bằng cách:
        //+ Binary search
        //+ Tận dung sự tăng dần để tìm ra tính chất mới:
        //VD: 1,3,7,11,12,14,18
        //Vẫn là tư duy dp[i][j]
        //Nếu hiện tại ta ở vị trí (i):
        //Ta có phương pháp two, pointer để giảm số lần duyệt --> Không cần thiết phải if else tất cả các cases đằng trước:
        //VD: arr[i]-arr[j] : với mỗi vị trí (i) ta sẽ tính lại nhóm toàn bộ với các cases đằng trước (j)
        //Ví dụ: mỗi j * k(Tìm hash cả n) > so với việc mỗi (j) * log(i) (Ta sử dụng 2 pointer)
        //Rule :
        //+ vì tăng dần nên nếu (arr[i]-arr[high] > arr[low]) --> low++ (arr[low] cần tăng lên) <>
        //+ Nếu tìm được số = nhau rồi, high--, low++ --> Tìm ví trí next.
        //2, rs>=1?rs+2:0;
        //Vẫn là return đúng kết quả --> Thay vì gán giá trị init cho arr[][] (range=2) (Tốn time)
        //--> Thì ta coi như range=2 --> Giá trị =0
        //Chuyển thành : return rs+2 là xong.
        System.out.println(lenLongestFibSubseqTwoPointer(arr));
    }
}
