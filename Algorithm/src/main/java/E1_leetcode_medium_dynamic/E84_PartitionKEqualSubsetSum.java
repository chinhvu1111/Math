package E1_leetcode_medium_dynamic;

import java.util.Arrays;
import java.util.HashSet;

public class E84_PartitionKEqualSubsetSum {

    public static boolean canPartitionOld(int[] nums) {
        int sum=0;
        int n=nums.length;

        for (int j : nums) {
            sum += j;
        }
        if(sum%2!=0){
            return false;
        }
        int valHalf=sum/2;
        boolean[] dp=new boolean[valHalf+1];
        dp[0]=true;

//        for(int i=1;i<=valHalf;i++){
//            for (int num : nums) {
//                if (i >= num && dp[i - num]) {
//                    dp[i] = true;
//                    break;
//                }
//            }
//        }
        //1: 1-1
        //2: 2-1
        //3: 3-1
        //4: 4-1
        for (int num : nums) {
            for(int i=valHalf;i>=num;i--){
                if(dp[i-num]){
                    dp[i]=dp[i-num];
                }
            }
        }

        return dp[valHalf];
    }

    public static boolean canPartitionKSubsetsWrong(int[] nums, int k) {
        int n=nums.length;
        int[] prefixSum=new int[n];
        int[][] dp=new int[n][k+1];
        int sum=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefixSum[i]=sum;
            dp[i][1]=sum;
//            System.out.println(sum);
        }
        for(int i=2;i<=k;i++){
            for(int j=0;j<n;j++){
                for(int h=0;h<j-1;h++){
                    int sumNewArray=prefixSum[i]-prefixSum[h];
//                    System.out.printf("%s %s %s\n", i, sumNewArray, dp[h][i-1]);

                    if(sumNewArray==dp[h][i-1]&&dp[h][i-1]!=0){
                        dp[j][i]=dp[h][i-1];
                        System.out.println(sumNewArray);
                        break;
                    }
                }
            }
        }
        return dp[n-1][k]!=0;
    }

    public static void getAllCases(HashSet<Integer> values, int index,
                                   int currentPos,int currentSum, int[] nums, int sum){
        if(currentSum>sum||nums[nums.length-1-index]>sum){
            return;
        }
        if(index==-1){
            if(currentSum==sum){
                values.add(currentPos);
//                System.out.printf("%s %s\n",Integer.toBinaryString(currentPos), currentSum);
            }
            return;
        }
//        System.out.printf("%s %s\n",Integer.toBinaryString(currentPos), currentSum);
//        getAllCases(values,index+1, currentPos|(1<<(nums.length-1-index)), currentSum+nums[nums.length-1-index], nums, sum);
        getAllCases(values,index-1, currentPos|(1<<(index)), currentSum+nums[nums.length-1-index], nums, sum);
        getAllCases(values,index-1, currentPos, currentSum, nums, sum);
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int n=nums.length;
        int size=1<<(n);
        boolean[][] dp =new boolean[k+1][size];
        int sum=0;

        for (int num : nums) {
            sum += num;
        }
        if((sum%k)!=0){
            return false;
        }
        int valueEVal=sum/k;
        HashSet<Integer> valuesPos=new HashSet<>();
        getAllCases(valuesPos, n-1, 0, 0, nums, valueEVal);
//        System.out.printf("%s %s\n", size,valuesPos);

        for (int j = 1; j <=size-1; j++) {
            if(valuesPos.contains(j)){
                dp[1][j] = true;
            }
        }
        for(int i=2;i<=k;i++){
            for(int j=1;j<=size-1;j++){
                //Thừa những khoảng ==0 ở đây có thể tạo ra thành các số = 5
                for (Integer sumValid:valuesPos){
                    //000101000
                    //101000000
                    if ((sumValid & j) == 0 && dp[i-1][j]) {
                        dp[i][j|sumValid] = true;
//                        System.out.printf("==%s %s %s\n", j, sumValid, j|sumValid);
//                        System.out.printf("%s %s %s\n", Integer.toBinaryString(j), Integer.toBinaryString(sumValid), Integer.toBinaryString(j|sumValid));
//                        break;
                    }
                }
            }
        }
        return dp[k][size-1];
    }

    public static boolean canPartitionKSubsetsOptimize(int[] nums, int k) {
        int n=nums.length;
        int size=1<<(n);
        boolean[] dp =new boolean[size];
        int sum=0;

        for (int num : nums) {
            sum += num;
        }
        if((sum%k)!=0){
            return false;
        }
        int valueEVal=sum/k;
        Arrays.sort(nums);
        HashSet<Integer> valuesPos=new HashSet<>();
        getAllCases(valuesPos, n-1, 0, 0, nums, valueEVal);
//        System.out.printf("%s %s\n", size,valuesPos);

        for (int j = 1; j <=size-1; j++) {
            if(valuesPos.contains(j)){
                dp[j] = true;
            }
        }
        for(int i=2;i<=k;i++){
            for(int j=1;j<=size-1;j++){
                //Thừa những khoảng ==0 ở đây có thể tạo ra thành các số = 5
                if(dp[j]){
                    for (Integer sumValid:valuesPos){
                        //000101000
                        //101000000
                        if ((sumValid & j) == 0 && dp[j]) {
                            dp[j|sumValid] = true;
//                        System.out.printf("==%s %s %s\n", j, sumValid, j|sumValid);
//                        System.out.printf("%s %s %s\n", Integer.toBinaryString(j), Integer.toBinaryString(sumValid), Integer.toBinaryString(j|sumValid));
//                        break;
                        }
                    }
                }
            }
        }
        return dp[size-1];
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{1,5,11,5};
//        int[] arr=new int[]{1,2,3,5};
        //Bị cases liên quan đến lặp số --> 4 = 2+2 --> nên nếu (số 2) được tạo từ (phần tử 2) rồi thì sẽ không được dùng lại nữa.
        int[] arr=new int[]{1,2,5};

        System.out.println(canPartitionOld(arr));
        //**
        //** Bài này tư duy như sau:
        //1.
        //1.1,
        //1.2, Chú ý dạng bài này có thể xảy ra cases:
        //Bị cases liên quan đến lặp số --> 4 = 2+2 --> nên nếu (số 2) được tạo từ (phần tử 2) rồi thì sẽ không được dùng lại nữa.
//        int[] arr=new int[]{1,2,5};
        //- Cách xử lý là:
        //+ For chọn số trước --> Mỗi số ==> Tổng
        //+ Phải xét từ tổng từ max --> min vì nếu min trước ==> max có thể sẽ đọc lại giá trị từ (min đã tính ==true) ==> kết quả sai
        //VD:
        //chọn phần tử 2 để tạo tổng từ : 1-->4
        //+ 2 ==> Được tạo ra từ phần tử 2
        //--> Check xem 2 có được tạo ra 4 không ==> dp[4-2] đã bằng true rồi ==> = true ( nên sai )
        //+ Cần check tử nhỏ đến lớn
        //+ i >= nums[i] ==> xét từ (max --> nums[i], j--)
        //
        //1.3, Complexity:
        //- Time complexity : O(N^2)
        //- Space complexity : O(h)
        //#Reference
        //417. Pacific Atlantic Water Flow
        //698. Partition to K Equal Sum Subsets
        //1981. Minimize the Difference Between Target and Chosen Elements
        //2025. Maximum Number of Ways to Partition an Array
        //
        //
//        int[] nums=new int[]{4,3,2,3,5,2,1};
//        int k=4;
        //4,3,2,3,5,2,1
        //0,0,0,0,0,0,0
        //0,0,0,0,1,0,0 = true
        //0,0,0,1,1,1,0 = true
//        int[] nums=new int[]{1334,4518,1235,1146,3565,64,913,203,1522};
//        int k=2;
//        int[] nums=new int[]{3,9,4,5,8,8,7,9,3,6,2,10,10,4,10,2};
//        int k=10;
        int[] nums=new int[]{2,9,4,7,3,2,10,5,3,6,6,2,7,5,2,4};
        int k=7;
        System.out.println(Integer.toBinaryString(64));
//        System.out.println(canPartitionKSubsets(nums, k));
//        System.out.println(canPartitionKSubsetsWrong(nums, k));
        System.out.println(canPartitionKSubsets(nums, k));
        System.out.println(canPartitionKSubsetsOptimize(nums, k));

        //
        //** Đề bài:
        //- return result : cho việc chia array thành k phần có sum = nhau
        //+ Lưu ý : Các phần tử có thể tự do xáo trộn
        //
        //** Bài này tư duy như sau:
        //1.
        //1.0, Tư duy sai ==> Ở đây không phải là subarray ==> Mà là chia 1 cách tự do
        //- dp có ý nghĩa như thế nào?
        //dp[i][k]=x
        //+ Là vị trí đang xét đến
        //+ k số lượng subarray ta có thể chia
        //+ x là giá trị sum của mỗi subarray đó.
        //
        //- Tính dp như thế nào?
        //+ init: dp[0][1]=sum (được khởi tạo từ đầu)
        //+ dp[i][2]= dp[j : 0 ->i-1][1] với  sum(subarray(j+1 --> i)) == dp[j][1]
        //
        //1.1,
        //- dp[i][k] : Là thể hiện cách chia tự do cho đến vị trí (i)
        //VD:
        //4,3,2,3 (I==3)
        //sum=12
        //==> có thể chia thành (4,2),(3,3)
        //- Để check [I=3][2] có chia được không
        //--> Dựa vào [j][1]=x
        //+ x là sum đã chia được
        //+ j là vị trí
        //VD: current_sum=12 ==> ta cần k --> mỗi sum=6
        //
        //- Ta không thể xây dựng công thức truy hồi từ k=1 ==> k=2 với cố định (i) (0..i) ==> vì sum nó rất lung tung.
        //VD:
        //3,5,2 ==> Chia được 2 thành phần k=2 với sum=5
        //Nhưng k=1 không dựng riêng được.
        //
        //
        //- Tư duy kiểu khác đi:
        //Cần sum = 5
        //Giả sử ta có:
        //4,3,2,3,5,2,1
        //0,0,0,0,0,0,0
        //+ [0,1,1,0,1,0,0][2]=5
        //==> Có thể tính theo [][1]
        //- Bằng cách tính sum=5 với tất cả các giá trị (bit==0) còn lại.
        //- traverse all [I][k=1] ==> Sao cho ([][k=1]==true) thì ta mới xét.
        //2,3,5,3
        //0,0,0,0
        //0,1,0,0
        //[0,1,0,0]
        //1.2, Bài toán con
        //- Tìm all array mà sum của chúng = x
        //VD:
        //1,2,5,6,9
        //x=7
        //+ Với dạng bài toán này thì --> brute force
        //
        //- Với các bài dạng brute force --> Muốn check index cuối (==-1/==0)
        //+ Xét riêng (length=0/length=1) là xong
        //
        //1.3, Các lưu ý khi << dịch bit
        //- value|(1<<index) ==> 000001
        //+ Tức là 00001 <=> index=0 (Hơi ngược so với tưởng tượng)
        //
        //#Reference
        //699. Falling Squares
        //2305. Fair Distribution of Cookies
        //2025. Maximum Number of Ways to Partition an Array
        //2397. Maximum Rows Covered by Columns
    }
}
