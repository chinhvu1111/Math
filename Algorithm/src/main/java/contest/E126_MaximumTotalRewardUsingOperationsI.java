package contest;

import java.util.*;

public class E126_MaximumTotalRewardUsingOperationsI {

//    public static int maxTotalReward(int[] rewardValues) {
//        PriorityQueue<Integer> incre=new PriorityQueue<>();
//        int n = rewardValues.length;
//
//        for (int rewardValue : rewardValues) {
//            incre.add(rewardValue);
//        }
//        int total=0;
//
//        while(!incre.isEmpty()){
//            while (!incre.isEmpty()&&incre.peek()<=total){
//                incre.poll();
//            }
//            if(!incre.isEmpty()){
//                total+=incre.poll();
//            }
//        }
//        return total;
//    }

    public static int maxTotalReward(int[] rewardValues) {
        int n = rewardValues.length;
        int num = (1<<n)-1;
        int max=0;

        for(int i=1;i<=num;i++){
            int pos=0;
//            int countBit=Integer.bitCount(i);
            int curRs=0;
            int initVal=i;
            List<Integer> list=new ArrayList<>();
//            System.out.printf("%s %s\n", i, countBit);

            while (initVal>0){
                //1100001 : lẻ
                //1100000 : chẵn
                if((initVal&1)==1){
//                    System.out.println(pos);
                    list.add(rewardValues[n-1-pos]);
                }
                initVal = initVal>>1;
                pos++;
//                pos++;
            }
            Collections.sort(list);
            int curSum=0;
            boolean isValid=true;

            for(int e: list){
                if(e<=curSum){
                    isValid=false;
                    break;
                }
                curSum+=e;
            }
            if(isValid){
//                System.out.println(list);
                max=Math.max(curSum, max);
            }
        }
        return max;
    }

//    public static int maxTotalRewardDynamic(int[] rewardValues) {
//        int n = rewardValues.length;
//        int max=0;
//        Arrays.sort(rewardValues);
//        int[] dp=new int[n];
//
//        for (int i = 0; i < n; i++) {
//            int maxPrev=0;
//            for(int j=i-1;j>=0;j--){
//                if(rewardValues[i]>dp[j]){
//                    maxPrev=Math.max()
//                }
//            }
//        }
//        return max;
//    }
    public static int rs=0;

    public static int search(int[] rewardValues, int i, int j, int val){
        int rs=-1;
        while(i<=j){
            int mid=i+(j-i)/2;
            if(val>=rewardValues[mid]){
                i=mid+1;
            }else{
                rs=mid;
                j=mid-1;
            }
        }
        return rs;
    }
    public static void solution(int index, int[] rewardValues, int curSum){
        if(index==rewardValues.length){
            rs=Math.max(rs, curSum);
            return;
        }
        if(rewardValues[rewardValues.length-1]<=curSum){
            return;
        }
//        for(int i=index+1;i<rewardValues.length;i++){
//            if(rewardValues[i]>curSum){
//                solution(index+1, rewardValues, curSum+rewardValues[i]);
//            }
//        }
        int j = search(rewardValues, index, rewardValues.length-1, curSum);
        index=j;
        if(j!=-1){
            solution(index+1, rewardValues, curSum+rewardValues[index]);
            solution(index+1, rewardValues, curSum);
        }
    }

    public static int maxTotalRewardBacktrack(int[] rewardValues) {
        Arrays.sort(rewardValues);
        rs=0;
        solution(0, rewardValues, 0);
        return rs;
    }

    public static int maxTotalRewardDynamic(int[] rewardValues) {
        //Space: O(log(n))
        Arrays.sort(rewardValues);
        int n=rewardValues.length;
        //Tổng point tại mỗi rewardValues <= rewardValues[i] <=2000
        //- dp[i][j]:
        //  + Chỉ ra là nếu đến vị trí (index=i) lấy được (j points) thì có thoả mãn hay không
        //Space: O(sum)
        //Time: O(sum)
        boolean[] dp=new boolean[4002];

        //Time: O(N^2*k) ==> timeout
//        for(int i=0;i<n;i++){
//            for(int p=rewardValues[i]-1;p>=0;p--){
//                for(int j=i-1;j>=0;j--){
//                    if(p>=rewardValues[j]){
//                        dp[i][p+rewardValues[i]]=dp[j][p];
//                        if(dp[i][p+rewardValues[i]]){
//                            max=Math.max(max, p+rewardValues[i]);
//                        }
//                    }
//                }
//            }
//        }
        int maxPrevCollectionSum=0;
        dp[0]=true;

        //Time: O(n*sum)
        for(int i=0;i<n;i++){
            for(int j=maxPrevCollectionSum;j>=0;j--){
                if(dp[j]&&j<rewardValues[i]){
                    dp[j+rewardValues[i]]=true;
                    maxPrevCollectionSum=Math.max(maxPrevCollectionSum, j+rewardValues[i]);
                }
            }
        }
        return maxPrevCollectionSum;
    }

    public static int solutionTopDown(int[][] dp, int index, int curSum, int[] rewardValues){
        if(index>=rewardValues.length){
            return 0;
        }
        if(dp[index][curSum]!=-1){
            return dp[index][curSum];
        }
        int curMax=0;
        if(rewardValues[index]>curSum){
            curMax=Math.max(rewardValues[index]+ solutionTopDown(dp, index+1, curSum+rewardValues[index], rewardValues), solutionTopDown(dp, index+1, curSum, rewardValues));
        }else{
            curMax=solutionTopDown(dp, index+1, curSum, rewardValues);
        }
        return dp[index][curSum]=curMax;
    }

    public static int maxTotalRewardTopDown(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int n=rewardValues.length;
        int max=0;
        int[][] dp=new int[n][4002];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        for(int i=0;i<n;i++){
            max=Math.max(max, solutionTopDown(dp, i, 0, rewardValues));
        }
        return max;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an (integer array rewardValues of length n), representing (the values of rewards).
        //- Initially, your total reward x is 0, and (all indices are unmarked).
        //- You are allowed to perform the following operation any number of times:
        //  + Choose an unmarked index i from the range [0, n - 1].
        //  + If rewardValues[i] is greater than your current total reward x, then add rewardValues[i] to x (i.e., x = x + rewardValues[i]), and mark the index i.
        //* Return an integer denoting the maximum total reward you can collect by performing the operations (optimally).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= rewardValues.length <= 2000
        //1 <= rewardValues[i] <= 2000
        //==> Value cũng không quá lớn ==> Có thể O(n^2) được.
        //
        //- Brainstorm
        //- Chỉ được phép chọn reward có value > current total of reward
        //==> Chọn tăng dần là tốt nhất
        //==> sort
        //
//        int[] rewardValues = {1,1,3,3};
        //+ 1,2,3,4,6
        //1+2+4 ==> false
        //+ 1,6,4,3,2
        //
        //- Sort xong dynamic thì sao
        //1,6,4,3,2
        //==> 1,2,3,4,6
        //+ 1,2,3,4,6
        //1+2+4 ==> false
        //+ 1,2,3,4,6
        //==> 1+4+6
        //
        //1,2,4,7
        //==> 2,4,7 : Sẽ bỏ qua 1
        //
        //- Nếu dùng dp
        //- Sort trước
        //Ex:
        //+ 1,2,3,4,6
        //- Mỗi element liệu ta sẽ check tính khả thi để reach được all rewards?
        //  - Ta thấy ta có thể bỏ qua một số elements
        //  Ex:
        //  + 1,2,3,4,6
        //  rs = 1,4,6
        //  4 được tính với 1
        //  ==> Liệu ta có cần gẵn index vào không?
        //  - Vì để có được 4 với prefixSum=5 thì 4 chỉ cần tính được theo 1 index bất kỳ trước đó mà reach được đến 1 là được (1-4)
        //  ==> Ta sẽ không cần phải gắn với index
        //
        //- Với mỗi element --> Ta sẽ tính new values bằng cách:
        //  + Xét all các sum mà đã được buid trước đó là được.
        //
        //- Có thể có cases mà:
        //  + Gán update trước đó ==> cùng 1 lần loop lại dùng lại dẫn đến sai không?
        //==> Không
        //* Kinh nghiệm:
        //- Để loại bỏ case:
        //  + Gán update trước đó ==> cùng 1 lần loop lại dùng lại dẫn đến sai không?
        //  ==> Ta có thể tính cho các cases ==> Loop SAU ĐÓ KHÔNG THỂ SỬ DỤNG LẠI
        //  ==> Ví dụ bên trên là các giá trị tính trước (j+ rewardValues[i]) (luôn >) các giá trị check if(dp[j])
        //- Làm dynamic programming:
        //  - Có những trường hợp có thể bỏ bậc của array như bên trên:
        //      - Nếu có dấu hiệu bậc (current value) tại (index=i) có thể tính được bằng bất cứ element nào có bậc (index=j) trước đó
        //      ==> Ta có thể BỎ BẬC INDEX (Chỉ giữ lại bậc SUM)
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(sum)
        //- Time: O(n*sum)
        //
        //2. Top Down approach
        //2.0,
        //- Tại sao mình lại timeout?
        //- Có phải vì mình đợi đến cuối mới tính?
        //- Có 2 cách tính được kết quả:
        //  + i==n: tính
        //  + tính mỗi lần call
        //- dp[i][j] là gì?
        //  + Là reward val lớn nhất nếu đi từ (index=i) + (sum reward=j)
        //- Init run:
        //  - Ta cần phải chạy với curSum==0 cho mọi vị trí vì:
        //      Ex:
        //      1,2,4,6
        //      ==> Nếu đứng ở (index=0, curSum==0) ==> (index=1) ==> curSum luôn tăng lên
        //      ** Ta cần thử với cả case bỏ qua 1 nữa.
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(n*sum)
        //- Time : O(n*sum)
        //
        //** Kinh nghiệm:
        //- Với những bài dạng ntn
        //==> Đến cuối tính gần như chắc chắn timeout
        //  + Ta cần phải memo lại mới pass được.
        //
//        int[] rewardValues = {1,6,4,3,2};
//        int[] rewardValues = {10};
        int[] rewardValues = {23,99,87,13,67,100,91,40,51,39,49,74,55,88,4,42,93,97,26,43,64,37,84};
//        199
//        System.out.println(maxTotalReward(rewardValues));
        System.out.println(maxTotalRewardBacktrack(rewardValues));
        System.out.println(maxTotalRewardDynamic(rewardValues));
        System.out.println(maxTotalRewardTopDown(rewardValues));
//        System.out.println(1<<2001);
    }
}
