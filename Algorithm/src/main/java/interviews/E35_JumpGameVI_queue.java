package interviews;

import java.util.*;

public class E35_JumpGameVI_queue {

    public static int maxResult(int[] nums, int k) {
        int n=nums.length;
        int dp[]=new int[n+1];
//        int rs=0;
        if(dp.length>=1){
            dp[0]=nums[0];
//            rs=dp[0];
        }else{
            return 0;
        }
        for(int i=1;i<n;i++){
            int max=Integer.MIN_VALUE;

            for(int j=1;j<=k&&i-j>=0;j++){
                max=Math.max(max, dp[i-j]+nums[i]);
            }
            dp[i]=max;
//            rs=Math.max(rs, max);
        }
//        for(int i=n-1;i>n-1-k&&i>=0;i--){
//            rs=Math.max(rs, dp[i]);
//        }
        return dp[n-1];
    }

    public static int maxResultWrongIdea(int[] nums, int k) {
        int n=nums.length;
//        int rs=0;
        if(n==0){
            return 0;
        }
        int rs=nums[0];

        for(int i=0;i<n;i++){
            int index=0;
            int negativeNumber=Integer.MIN_VALUE;
            int indexNegative=-1;
            int initRs=rs;

            for(int j=1;j<=k;j++){
                if(i+j==n-1){
                    index=n-1;
                    rs+=nums[n-1];
                    break;
                }
                if(i+j<n&&negativeNumber<nums[i+j]){
                    negativeNumber=nums[i+j];
                    indexNegative=i+j;
                }
                if(i+j<n&&nums[i+j]>0){
                    rs+=nums[i+j];
                    index=i+j;
                }
            }
            if(index==n-1){
                break;
            }

            if(initRs==rs){
                rs+=negativeNumber;
                index=indexNegative;
            }

            i=index-1;
//            rs=Math.max(rs, max);
        }
//        for(int i=n-1;i>n-1-k&&i>=0;i--){
//            rs=Math.max(rs, dp[i]);
//        }
        return rs;
    }

    public static int maxResultDeque(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        Deque<Integer> q = new LinkedList<>();
        q.offer(0);
        for(int i = 1; i < n; i++){
            dp[i] = nums[i] + dp[q.peekFirst()];
            while(!q.isEmpty() && dp[q.peekLast()] <= dp[i]) q.pollLast();
            q.offerLast(i);
            if(i - q.peekFirst() >= k) q.pollFirst();
        }
        return dp[n - 1];
    }

    public int maxResultPriorityQueue(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)-> (b[0] - a[0]));
        queue.add(new int[]{nums[0], 0});
        int max = nums[0];
        for(int i=1;i<n;i++){
            while(queue.peek()[1]+k<i){
                queue.poll();
            }
            max = nums[i]+queue.peek()[0];
            queue.add(new int[]{max, i});

        }
        return max;
    }

    public static int maxResultPriorityOptimized(int[] nums, int k) {
        int n = nums.length;
        // Compare method for place element in
        // Reverse order
        PriorityQueue<int[]> integers = new PriorityQueue<>((a, b)-> (b[0] - a[0]));
        int rs=Integer.MIN_VALUE;

        if(nums.length>=1){
            integers.add(new int[]{nums[0], 0});
        }
        for(int i=1;i<n;i++){
            while(!integers.isEmpty()&&integers.peek()[1]+k<i){
                integers.poll();
            }
            int currentValue=integers.peek()[0] + nums[i];
//            rs=Math.max(rs, currentValue);
            rs=currentValue;
            integers.add(new int[]{currentValue, i});
        }

        return rs;
    }

    public static int maxResultPriorityOptimized1(int[] nums, int k) {
        int n = nums.length;
        // Compare method for place element in
        // Reverse order
        Deque<Integer> integers = new LinkedList<>();
        int dp[]=new int[n];

        if(nums.length>=1){
            integers.add(0);
            dp[0]=nums[0];
        }
        for(int i=1;i<n;i++){
            dp[i]=dp[integers.peekFirst()] + nums[i];

            while(!integers.isEmpty()&&dp[integers.peekLast()]<=dp[i]){
                integers.pollLast();
            }
            integers.offerLast(i);
            if(integers.peekFirst()+k<=i){
                integers.pollFirst();
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{10,-5,-2,4,0,3};
//        int arr[]=new int[]{10};
//        int arr[]=new int[]{1,-5,-20,4,-1,3,-6,-3};
        //Case 1: Sai do index bị reset về 1 do chưa check (i+j<n)
//        int arr[]=new int[]{100,-1,-100,-1,100};
        //Case 2: Sai do nếu chọn -1 --> Chọn -2/ -3 ==> Sẽ bị sai khi chọn -2
        //--> Kết quả sẽ không tối ưu
//        int arr[]=new int[]{0,-1,-2,-3,1};
        int arr[]=new int[]{0,-2,-1,-3,1};
//        System.out.println(maxResult(arr, 2));
//        System.out.println(maxResult1(arr, 3));
//        System.out.println(maxResultWrongIdea(arr, 2));
        //=================================  LUỒNG TƯ DUY NHƯ SAU:
        //Connection :
        //1 --> 4
        //-5 --> -1
        //-20 --> 3
        //4 --> -6
        //-1 --> -3
        //
        //Cần giảm thời gian tìm phần tử max nhất các đó k phần tử:
        //
        //1,-5,-20,4,-1
        //Với trường hợp tư duy ngược thì không kiểm soát được các giá trị dp[j]
        //
        //==> Ta sẽ tư duy theo chiều xuôi
        //
        //1,-5,-20,4,-1,3,-6,-3
        //
        //- 1 sẽ chọn kết hợp với (-5,-20,4) : (-4,-19,5)
        //- -5 sẽ chọn kết hợp với (-20,4, -1) --> remove -5 : (-25,-1,-6)
        //- -20 sẽ chọn kết hợp với (4, -1, 3) --> remove -20 : (-16,-21,-17)
        //# Bài này có rule đặc biệt:
        //Trong 1 slide window ta :
        //VD: (1,2,3,-5,6) k=5
        //--> Ta luôn chọn phương án để đi hết tất cả các số dương ở trong 1 slide window
        //VD: (1,2,3,-5,6) sum = 1+2+3+6 =12.
        //
        //1.1, Sum all positive number + dừng ở số dương cuối cùng:
        //Tại sao lại cuối cùng : ==> Ta muốn dừng số càng sát càng tốt thì (SỐ LƯỢNG CÁC SỐ TIẾP THEO TA CÓ THỂ TRAVERSE "Nhiều hơn")
        //
        //1,
        //-5,-20,4,-1,3,-6,-3
        //VD: 1 + 4 + 3 + -3 = 5
        //
        //2, Cần phải tối ưu list add các elements sao cho các số phần tử lấy ra phải
        //
        //
        //[0,-1,-2,-3,1]
        //2
        //
        //**==> Chọn max nhất sẽ bị sai do case này:
        //Output : -2
        //Expected : -1
        //
        //3, Cần phải chuyển tư duy:
        //3.1, Sắp xếp theo (value) chưa đủ --> Cần phải dùng cả (index) nữa
        //-5,-20,4,-1,3,-6,-3
        //3.2, Ta tư duy tính theo từng vị trí dp[i]
        //==> Cần lưu các giá trị trước sao cho xác định value để tính nhanh nhất có thể.
        //
        //VD:
        //Step 1: num[2]=4;
        //dp[2] based on -5,-20.
        //Step 2: num[3]=-1
        //Được tính theo -20,4
        //3.3, Ở đây key idea là ta muốn loại các giá trị nhỏ hơn không cần thiết trong queue
        //Có 2 kiểu ta có thể loại đi:
        //- Quá slide window
        //+ Check index khi push vào queue.
        //- Value quá nhỏ
        //+ Loại ngay ở trường hợp num[2]=4 --> Nếu dp[2] lớn hơn bao nhiêu số --> remove hết chỉ tính theo dp[2]
        //
        //==> Kết hợp cả 2:
        //dùng dp[i] lưu kết quả + push(i) vào là 1 lựa chọn hợp lý.
        //
        //
        //Example:
        //-5,-20,4,-1,3,-6,-3
        //
        //3.4, 1 điều cần nhớ rằng nếu muốn theo quy tắc pop()
        //--> Thì ta cần phải sắp xếp các elements theo (tăng dần/ giảm dần) theo values ==> Chứ không phải index đã được sắp xếp tăng dần để (thể hiện thứ tự rồi)
        //==> pop index ra thì không thể hiện về mặt value của các giá trị sau.
        //3.4.1, SẮP XẾP PHẢI "THEO RULE VALUE"
        //==> Pop dần các giá trị trên peek ra.
        //4, 1 tư duy quan trọng nữa:
        //Ta sắp xếp ở đây để làm gì?
        //4.1, Để lấy giá lớn nhất nhưng phải thỏa mãn điều kiện index
        //- Không cần phải check max với Priority queue
        //--> Mà thường (tận dụng việc sắp xếp) + kết hợp với check 1 cái gì đó để (poll các value từ top)
        //Khi nào thì ta dừng lại ==> Khi ta lấy được giá trị tối ưu nhất (Max nhất gần top nhất) + thỏa mãn điều kiện.
        //VD: Ở đây ta luôn hướng lấy max ở top + nếu index mà không thỏa mãn --> Ta mới poll để lấy value thỏa mãn (Nếu không ta sẽ poll value)
        //* Nếu không trong slide --> poll(), ta có poll đến khi lấy được giá trị max nhất mà thỏa mãn in slide window.
        //Code : while(index + k > i) pop()
        //5, get the result on last element ==> Không phải max all of elements.

        //**CONCLUSION:
        //1, 1 điều cần nhớ rằng nếu muốn theo quy tắc pop()
        //--> Thì ta cần phải sắp xếp các elements theo (tăng dần/ giảm dần) theo values ==> Chứ không phải index đã được sắp xếp tăng dần để (thể hiện thứ tự rồi)
        //==> pop index ra thì không thể hiện về mặt value của các giá trị sau.
        //1.1, SẮP XẾP PHẢI "THEO RULE VALUE"

        //2.1, Để lấy giá lớn nhất nhưng phải thỏa mãn điều kiện index
        //- Không cần phải check max với Priority queue
        //--> Mà thường (tận dụng việc sắp xếp) + kết hợp với check 1 cái gì đó để (poll các value từ top)
        //Khi nào thì ta dừng lại ==> Khi ta lấy được giá trị tối ưu nhất (Max nhất gần top nhất) + thỏa mãn điều kiện.
        //VD: Ở đây ta luôn hướng (lấy max ở top) + nếu (index mà không thỏa mãn) --> Ta mới poll để lấy value thỏa mãn (Nếu không ta sẽ poll value)

        //Cách 2 : Dùng Dequeue
        //int arr[]=new int[]{0,-1,-2,-3,1};
        //int arr[]=new int[]{0,-2,-1,-3,1};
        //- Với case này i=3 (nums[3]=-3) ta có 2 lựa chọn (-1, -2)
        //--> Lúc này ta sẽ chọn max của cả 2.
        //Ta phải sắp xếp dequeue theo chiều giảm dần từ first.
        //- Với mỗi bước ta cần poll value từ first --> Để không vươt ra ngoài slide window.
        //VD: Case -3,-5,-2,-4, 5 : k=4
        //- Ta sẽ chỉ sợ case liên quan đến các giá trị ở (middle) có thể > -3
        //--> Ta không chỉ tính 1 case trung gian
        //==> Ta cần tính từ thời điểm init queue
        // (-3,-5,-2,-4, 5)
        // step 1:
        //(-3)
        //(-3) (-2)
        //(-3) (-2) (-4) --> poll (-4)
        //(-3) (-2) 5 --> poll (-3)
        //***CONCLUSION:
        //ĐÂY LÀ TƯ DUY CHẠY TRONG QUEUE ĐỂ thỏa mãn:
        //+ firstPeek : Luôn là điểm lớn nhất
        //+ first sẽ được poll dựa trên slide window.
        //--> Chú ý chỉ quan tâm queue từ lúc init --> final.
        //Wrong:
        //d[0]=nums[0] --> Chú ý cái này.
        //--> Có thể sai case.
        System.out.println(maxResultPriorityOptimized(arr, 2));
        System.out.println(maxResultDeque(arr, 2));
        System.out.println(maxResultPriorityOptimized1(arr, 2));
    }
}
