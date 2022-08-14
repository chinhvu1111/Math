package interviews;

import java.util.Arrays;

public class E100_SplitArrayLargestSum_Binary {
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
                }
                right=mid-1;
            }
        }
        if(rs==Integer.MAX_VALUE){
            rs= Arrays.stream(nums).max().getAsInt();
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

    public static int splitArrayBinarySearchOptimize(int[] nums, int m) {
        if(nums==null||nums.length==0||m==0){
            return 0;
        }
        int max=0;
        int sum=0;

        for(int value: nums){
            sum+=value;
            max=Math.max(max, value);
        }
        if(m==nums.length){
            return max;
        }else if(m==1){
            return sum;
        }else{
            int low=max;
            int high=sum;
            int rs=0;

            while (low<=high){
                int mid=low +(high-low)/2;

                //1, Ở đây điều kiện ta đang xét <=
                //+ Thế nên ta có thể gán RS luôn Vì (Số lớn hơn kết quả) ==> (Số đó đang giảm dần)
                //--> (Không cần) rs=min(rs, mid)
                //+ Ta có thể loại phần tử xem nó còn <= không
                //2, Lớn hơn thì kết quả sẽ không nằm bên đó --> Không cần xét.
                //3, Chú ý ở đây:
                //+ low = Max(nums[i]) : Một số trường hợp không gán thế này thì kết quả sẽ sai.
                //VD:
                //low=0, high=950
                //+ 950 là kết quả của bài toán do (nums[i]=950)
                //Sau khi dịch left nhiều lần (high=mid-1)
                //+ mid= 425 --> vẫn possible vì if(count<=m) return true
                //==> Count lúc này sẽ == 0
                //==> Thế nên nó sẽ return 0 (Thay vì return 950) ===> Sai
                //** Ta cần giới hạn (low) sao cho
                //+ Ít nhất với rs=low --> Có thể chia được 1 phần tử
                //+ Ở giá trị (low) thì phải thỏa mãn điều kiện (Trong mọi trường hợp)
                //VD: low=0 --> Không phải lúc nào cũng thỏa mãn
                //Nếu all(nums[i]) > 0 : Dịch theo kiểu điều kiện trên --> SAI:
                //+ return 0 (Tốt nhất)
                //*** KINH NGHIỆM : low --> Tệ nhất/ min(nums[i]/ ...
                //4, Câu hỏi đặt ra:
                //- isPossible(nums, mid, m) <==> (count<=m) :
                //+ Liệu có return rs (mà count<m) --> Conflict với đề bài (count == m) hay không?
                //--> KHÔNG vì:
                //+ Điều kiện đó SỐ LỚN --> Giảm dấn đến khi gặp số NHỎ --> thì (count>m)
                //--> DO LUÔNG TÌM ĐƯỢC KẾT QUẢ --> Rs lúc đó sẽ nhận số trước đó (<=m)
                //+ Với (count > m) : low lúc đó cũng sẽ tăng dần để tìm (rs==m)
                //---> CHẮC CHẮN SẼ TÌM DC rs trong RANGE lúc trước.
                //
                if(isPossible(nums, mid, m)){
                    rs=mid;
                    high=mid-1;
                }else{
                    low=mid+1;
                }
            }
            return rs;
        }
    }

    public static boolean isPossible(int nums[], int mid, int m){
        //- Ở đây number of stages init =1
        //+ Ta đang so sánh (sum> mid ) numberOfStages++;
        //+ Nên nếu khi (i==n-1) : sum > mid, lúc đó sum=nums[i]
        //---> Ta có thể thêm 1 subarray nữa.
        //** Câu hỏi là liệu có trường hợp nào (sum cuối trống không bao gồm phần tử nào không)
        //+ Không có vì ta đang so sánh (sum>mid) sum=nums[i]
        //--> Kiểu gì số cuối cùng còn sum (elements)
        //===> Ta gán ngay (numOfStages=1)
        //
        int numberOfStages=1;
        int sum=0;

        for(int i=0;i<nums.length;i++){
            sum+=nums[i];

            if(sum>mid){
                sum=nums[i];
                numberOfStages++;
            }
        }
        if(numberOfStages<=m){
            return true;
        }
        return false;
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
        System.out.println(splitArrayBinarySearchOptimize(arr, m));
        //Bài này tư duy như sau:
        //https://leetcode.com/problems/split-array-largest-sum/discuss/1904499/JAVA-or-0ms-100-or-Clean-Simplest-Solution-with-comments
        //Bài này có 2 cách:
        //1, Dynamic programming --> Slow
        //2, Binary search
        //
        //Cách 2:
        //1, Binary search
        //Cách này mình tư duy hơi cồng kềnh
        //1.1, Giới hạn không gian tìm kiếm từ (0 --> 1_000_000*50)
        //1.2, Điều kiện dịch như sau:
        //+ Số đoạn cần tìm là m, nếu số lượng đoạn ta tìm thấy là k -->
        //- K> m : mid nhỏ hơn yêu cầu : (low, high-1)
        //- K< m : mid lớn hơn yêu câu : (mid+1, high)
        //- K==m : mid đạt yêu cầu, sẽ có thêm các trường hợp sau đây:
        //+ Vì để bài là tìm MIN(SUM của các elements liên tiếp nhau)
        //--> Ta cần phải tìm trường hợp mà (K==m) tối ưu nhất
        //===> chọn tiếp (low, high-1)
        //+ Vì cần tìm MIN(All of SUMS) : Nên ta cần phải xét MIN()
        //
        //2, Các lỗi khi làm bài dạng kiểu này
        //2.1, Method tìm số stages:
        //- sum=0 --> sum+=nums[i] sau mỗi lần run.
        //- if(sum> mid)
        //+ valueCount++ (Cộng dần lên số lượng stages)
        //+ Vì thêm 1 stages nếu ta cần giảm sym đi = nums[i] (Phần thừa ra)
        //**, Trường hợp đặc biệt khi (nums[i]==mid) --> Lúc đó ta còn phải
        //+ sum=0
        //+ valueCount++ (Thêm vị trí đó)
        //
        //**, Trường hợp thiếu khi (i==n-1) : sum vẫn thừa ==> Ta phải check:
        //+ Sum!=0 : valueCount++;
        //
        //2.2, Cases đặc biệt khi :
        //+ m==1 : return sum (all of elements)
        //
        //3, Tối ưu code:
        //CODE: =================
        //if(isPossible(nums, mid, m)){
        //                    rs=mid;
        //                    high=mid-1;
        //                }else{
        //                    low=mid+1;
        //                }
        //=======================
        //1, Ở đây điều kiện ta đang xét <=
        //+ Thế nên ta có thể gán RS luôn Vì (Số lớn hơn kết quả) ==> (Số đó đang giảm dần)
        //--> (Không cần) rs=min(rs, mid)
        //+ Ta có thể loại phần tử xem nó còn <= không
        //2, Lớn hơn thì kết quả sẽ không nằm bên đó --> Không cần xét.
        //3, Chú ý ở đây:
        //+ low = Max(nums[i]) : Một số trường hợp không gán thế này thì kết quả sẽ sai.
        //VD:
        //low=0, high=950
        //+ 950 là kết quả của bài toán do (nums[i]=950)
        //Sau khi dịch left nhiều lần (high=mid-1)
        //+ mid= 425 --> vẫn possible vì if(count<=m) return true
        //==> Count lúc này sẽ == 0
        //==> Thế nên nó sẽ return 0 (Thay vì return 950) ===> Sai
        //** Ta cần giới hạn (low) sao cho
        //+ Ít nhất với rs=low --> Có thể chia được 1 phần tử
        //+ Ở giá trị (low) thì phải thỏa mãn điều kiện (Trong mọi trường hợp)
        //VD: low=0 --> Không phải lúc nào cũng thỏa mãn
        //Nếu all(nums[i]) > 0 : Dịch theo kiểu điều kiện trên --> SAI:
        //+ return 0 (Tốt nhất)
        //*** KINH NGHIỆM : low --> Tệ nhất/ min(nums[i]/ ...
        //4, Câu hỏi đặt ra:
        //- isPossible(nums, mid, m) <==> (count<=m) :
        //+ Liệu có return rs (mà count<m) --> Conflict với đề bài (count == m) hay không?
        //--> KHÔNG vì:
        //+ Điều kiện đó SỐ LỚN --> Giảm dấn đến khi gặp số NHỎ --> thì (count>m)
        //--> DO LUÔNG TÌM ĐƯỢC KẾT QUẢ --> Rs lúc đó sẽ nhận số trước đó (<=m)
        //+ Với (count > m) : low lúc đó cũng sẽ tăng dần để tìm (rs==m)
        //---> CHẮC CHẮN SẼ TÌM DC rs trong RANGE lúc trước.
        //
        //CODE
        //====================
        //int numberOfStages=1;
        //        int sum=0;
        //
        //        for(int i=0;i<nums.length;i++){
        //            sum+=nums[i];
        //
        //            if(sum>mid){
        //                sum=nums[i];
        //                numberOfStages++;
        //            }
        //        }
        //        if(numberOfStages<=m){
        //            return true;
        //        }
        //====================
        //- Ở đây number of stages init =1
        //+ Ta đang so sánh (sum> mid ) numberOfStages++;
        //+ Nên nếu khi (i==n-1) : sum > mid, lúc đó sum=nums[i]
        //---> Ta có thể thêm 1 subarray nữa.
        //** Câu hỏi là liệu có trường hợp nào (sum cuối trống không bao gồm phần tử nào không)
        //+ Không có vì ta đang so sánh (sum>mid) sum=nums[i]
        //--> Kiểu gì số cuối cùng còn sum (elements)
        //===> Ta gán ngay (numOfStages=1)
        //
    }
}
