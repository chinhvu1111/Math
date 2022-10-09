package interviews;

import java.util.HashMap;

public class E96_MinimumNumberOfDaysToMakeMBouquets_binary {

    public static int minDaysOptimize(int[] bloomDay, int m, int k) {
        int n=bloomDay.length;
        int low=0;
        int high=0;

        if(n<m*k){
            return -1;
        }

        for(int i=0;i<n;i++){
            low=Math.min(low, bloomDay[i]);
            high=Math.max(high, bloomDay[i]);
        }
        while (low<high){
            int mid=low+(high-low)/2;

            if(isValid(mid, m, k, bloomDay)){
                high=mid;
            }else{
                low=mid+1;
            }
        }

        return low;
    }

    public static boolean isValid(int mid, int m, int k, int[] bloomDay){
        int n=bloomDay.length;
        int count=0;
        int adjCount=0;

        for(int i=0;i<n;i++){
            if(bloomDay[i]<=mid){
                count++;
                if(count==k){
                    adjCount++;
                    count=0;
                }
            }else{
                count=0;
            }
        }
        return adjCount >=m;
    }

    public static int minDaysDynamic(int[] bloomDay, int m, int k) {
        int n=bloomDay.length;
        int dp[][]=new int[n+1][m+1];

        int[] max = new int[n];

        for(int i=k-1;i<n;i++){
            int currentMax=Integer.MIN_VALUE;

            for(int j=i;j>=i-k+1;j--){
                currentMax=Math.max(currentMax, bloomDay[j]);
            }
            max[i]=currentMax;
        }
        int rs=recursive(n-1, m, k, dp, max);

        return rs;
    }

    public static int recursive(int end, int m, int k, int dp[][], int max[]){
//        if(m==0){
//            return dp[end][m]=max[end];
//        }
        if(m*k>end+1){
            return -1;
        }
        if(m<=0){
            return -1;
        }
        if(dp[end][m]!=0){
            return dp[end][m];
        }
        int rs=Integer.MAX_VALUE;

        for(int i=end;i>=m*k-1;i--){
            int rightMax=max[i];
            int left=recursive(i-k, m-1, k, dp, max);
            rs=Math.min(rs, Math.max(rightMax, left));
//            System.out.printf("end %s, right: %s, left: %s\n",i, rightMax, left);
        }
        return dp[end][m]=rs;
    }

    static int[][] memo;
    public static int minDaysReference(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        memo = new int[n + 1][m + 1];
        int[] max = new int[n + 1];
        for (int i = 0; i <= n - k; i++) {
            for (int j = i; j < i + k; j++) {
                max[i] = Math.max(max[i], bloomDay[j]);
            }
        }
        //max
//        [1, 10, 3, 10, 2, 0]
        return dp(bloomDay, m, k, 0, max);
    }

    private static int dp(int[] A, int m, int k, int start, int[] max) {
        if (m == 0) return 0;
        if (memo[start][m] != 0) return memo[start][m];
        int n = A.length;
        if (n - start < m * k) {
            memo[start][m] = -1;
            return -1;
        }

        int res = Integer.MAX_VALUE;
        for (int i = start; i <= n - m * k; i++) {
            int c = max[i];
            int right=dp(A, m - 1, k, i + k, max);
            res = Math.min(res, Math.max(c, right));
//            System.out.printf("start %s, c: %s, right: %s\n",start, c, right);
        }
        memo[start][m] = res;
        return res;
    }

    static int m,k;
    public static int minDaysOptimize100(int[] bloomDay, int m1, int k1) {
        if(m1*k1 > bloomDay.length) return -1;
        int max = 0;
        m = m1;
        k=k1;
        for(int v:bloomDay) max = Math.max(max,v);

        return binarySearch(bloomDay,1,max);
    }

    public static int binarySearch(int[] a, int low,int high){
        while(low<high){
            int mid = (low+high)/2;
            if(isPossible(a,mid)) high = mid;
            else low = mid+1;
        }
        return low;
    }

    public static boolean isPossible(int[] a,int val){
        int low = 0;
        int ans = 0;

        for(int i=0;i<a.length;i++){
            if(a[i] > val){
                ans+= ((i-low)/k);
                if(ans == m) return true;
                low=i+1;
            }
        }

        ans+= ((a.length-low)/k);
        return (ans>=m);
    }

    public static void main(String[] args) {
//        int bloomDay[]=new int[]{7,7,7,7,12,7,7};
//        int m=2,k=3;
//        int bloomDay[]=new int[]{6,5,2,3,4,1,2};
//        int m=2,k=3;
        int bloomDay[]=new int[]{1,10,3,10,2};
        int m=3,k=1;
//        int bloomDay[]=new int[]{1,10,3,10,2};
//        int m=3,k=2;
//        System.out.println(minDays(bloomDay, m, k));
        //Tham khảo: Cách này đang write dựa trên tư duy ngược
//        System.out.println(minDaysReference(bloomDay, m, k));
        //Rewrite code: Theo tư duy xuôi
//        System.out.println(minDaysDynamic(bloomDay, m, k));
        //Binary search
        System.out.println(minDaysOptimize(bloomDay, m, k));
        //
        //** Đề bài:
        //- m là số "bó hoa" cần chờ nỏ
        //- k là số bông hoa (số bổng hoa để tạo thành "1 bó")
        //==> Trả lại số ngày ít nhất để có thể có được m bó hoa <> không có return -1.
        //
        //Bài này tư duy như sau:
        //Bài này có 2 cách giải
        //+ Dynamic programming O(n^2)
        //+ Binary search O(nlog(k))
        //Cách 1:
        //1, Vỡi cách này tư duy sẽ phức tạp hơn ở công thức truy hồi
        //1.1, Với các bài dynamic programming công thức truy hồi có thể có 2 trường hợp:
        //+ Suy trực tiếp tính được vói độ phức tạp O(1)
        //+ Tính theo kiểu vét cạn (brute force) O(n)
        //--> Bài này thuộc dạng vét cạn (brute force)
        //1.3, dp[i][j]: (i: 0 --> n-1, j : 1 --> m) với:
        //+ i là vị trí ta xét đến
        //+ j là số lượng Bouquets cân ( tức là j (k số liên tiếp nhau))
        //1.4, Tư duy suy diễn ra công thức truy hồi như sau:
        //Ở đây ta sẽ suy ngược.
        //Kết quả: dp[n-1][m]
        //- Ta thấy ở đây rằng để suy ra được dp[i][m]
        //dp[i][m] = f(dp[j][m-1])
        //- Khi giảm m --> m-1 --> Ta có thể (bỏ đi k số đứng cuối cùng)
        //==> Tuy nhiên ta có thẻ không cần bỏ đến (K) phần tử vì CÓ THỂ THỪA đằng trước đó
        //dp[i][m-1] (có thể thừa/ có thể không thừa)
        //*** ==> Ta sẽ vét cạn các khả năng (Thừa/ Không thừa) ===> DẤU HIỆN NHẬN BIẾT QUY HOẠCH ĐỘNG (Brute force dynamic)
        //1.5, Khi xác định được đây là (brute force dynamic)
        //dp[n-1][m] --> Tính theo [i-k][m-1]
        //Question: Lúc này (i) chạy như thế nào
        //* (i): Chạy sao cho thảo mãn đủ (m đoạn k) tức là (i) : (m*k-1 --> n-1)
        //==> Chỗ này có thể sẽ gây nhầm lẫn :
        //- Để trả lại kết quả chính (m đoạn k) thì ta đã có (m*k-1 --> n-1) cách chọn đoạn để có thể đủ (m đoạn k)
        //===> Như thế này sẽ cover được các cases (thừa/ thiếu k khi trừ đi)
        //1.6, Nếu vẫn cảm thấy lạ lùng ở chỗ này (Nhất ở phần - đủ/thiếu k)
        //VD: ta đang ở (i) --> Trừ thiếu k, ta sẽ có (k-1) trường hợp
        //- trừ 1 số --> k số
        //+ Trừ 1 số
        // dp[i][m]=dp[i-1][m-1] ==> TA KHÔNG HỀ BIẾT TRONG dp[i-1][m-1] có "THỪA HAY KHÔNG"
        //---> Cách k thiếu này SAI.
        //1.6,1,
        //KẾT LUẬN : (i) Phải ít nhất đủ (m đoạn k trước) ==> Sau đó mới - k (Xét với (m-1) k đoạn.
        //+ i=m*k-1 : Khi (-k) sẽ là trường hợp đủ nhất (Không thừa phần tử nào trong dp[i][m-1])
        //... ==> Dần dần sẽ thừa dần
        //NOTE: Cho dù thừa thì việc (-k) để giảm (m-1) là bắt buộc, như phân tích bên trên (việc trừ thiếu K) --> Không giảm được m
        //==> Tư duy (- thiếu K) không giảm được m ==> Tư duy sai.
        //1.7, Phần bù (k) đơn vị sẽ tính max[i] --> Lưu max của (k tính từ i trở về trước)
        //VD:
        //    {1,10,3}, m=3, k=1
        //max:1,10,3
        //--> Vì slice =1
        //CODE:
        //int c = max[i];
        //int right=dp(A, m - 1, k, i + k, max);
        //res = Math.min(res, Math.max(c, right));
        //KINH NGHIỆM:
        //+ Công thức truy hồi cần phải giảm được index ==> index-1 --> Mới tính xét được
        //VD: Bài này phải giảm i cho k để có thể giảm m ==> m-1.
        //1.7, Chú ý các cases đặc biệt:
        //VD: 1,10,3
        //+ dp[3][1]=1
        //+ Với end=3 --> lúc nó m==0 ==> Thì ta mới return dp[end][m]=max[m]
        //--> m==1 chưa return.
        //
        //Cách 2:
        //1, Ta sẽ lấu min, max ==> Là 2 giá trị nhỏ nhất/ lớn nhất của array
        //1.1, Sau đó ta sẽ tìm số sao cho isValid()
        //isValid: Tức là đủ các m đoạn k
        //+ Nếu thảo mãn high=mid --> Vẫn còn có thể tốt hơn nữa (Có thể có số <high)
        //+ low=mid+1 ==> Cần 1 số lớn hơn.
        //2, Kinh nghiệm:
        //+ Dấu hiệu các bài binary search --> Có 1 đk để tìm 1 kết quả lớn hơn
        //+ Số tìm được sẽ phải thuộc range (min, max).
        //2, Tối ưu:
        System.out.println(minDaysOptimize100(bloomDay, m, k));
    }
}
