package interviews;

import java.util.*;

public class E35_JumpGameVI {

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
        //Case 1: Sai do index b??? reset v??? 1 do ch??a check (i+j<n)
//        int arr[]=new int[]{100,-1,-100,-1,100};
        //Case 2: Sai do n???u ch???n -1 --> Ch???n -2/ -3 ==> S??? b??? sai khi ch???n -2
        //--> K???t qu??? s??? kh??ng t???i ??u
//        int arr[]=new int[]{0,-1,-2,-3,1};
        int arr[]=new int[]{0,-2,-1,-3,1};
//        System.out.println(maxResult(arr, 2));
//        System.out.println(maxResult1(arr, 3));
//        System.out.println(maxResultWrongIdea(arr, 2));
        //=================================  LU???NG T?? DUY NH?? SAU:
        //Connection :
        //1 --> 4
        //-5 --> -1
        //-20 --> 3
        //4 --> -6
        //-1 --> -3
        //
        //C???n gi???m th???i gian t??m ph???n t??? max nh???t c??c ???? k ph???n t???:
        //
        //1,-5,-20,4,-1
        //V???i tr?????ng h???p t?? duy ng?????c th?? kh??ng ki???m so??t ???????c c??c gi?? tr??? dp[j]
        //
        //==> Ta s??? t?? duy theo chi???u xu??i
        //
        //1,-5,-20,4,-1,3,-6,-3
        //
        //- 1 s??? ch???n k???t h???p v???i (-5,-20,4) : (-4,-19,5)
        //- -5 s??? ch???n k???t h???p v???i (-20,4, -1) --> remove -5 : (-25,-1,-6)
        //- -20 s??? ch???n k???t h???p v???i (4, -1, 3) --> remove -20 : (-16,-21,-17)
        //# B??i n??y c?? rule ?????c bi???t:
        //Trong 1 slide window ta :
        //VD: (1,2,3,-5,6) k=5
        //--> Ta lu??n ch???n ph????ng ??n ????? ??i h???t t???t c??? c??c s??? d????ng ??? trong 1 slide window
        //VD: (1,2,3,-5,6) sum = 1+2+3+6 =12.
        //
        //1.1, Sum all positive number + d???ng ??? s??? d????ng cu???i c??ng:
        //T???i sao l???i cu???i c??ng : ==> Ta mu???n d???ng s??? c??ng s??t c??ng t???t th?? (S??? L?????NG C??C S??? TI???P THEO TA C?? TH??? TRAVERSE "Nhi???u h??n")
        //
        //1,
        //-5,-20,4,-1,3,-6,-3
        //VD: 1 + 4 + 3 + -3 = 5
        //
        //2, C???n ph???i t???i ??u list add c??c elements sao cho c??c s??? ph???n t??? l???y ra ph???i
        //
        //
        //[0,-1,-2,-3,1]
        //2
        //
        //**==> Ch???n max nh???t s??? b??? sai do case n??y:
        //Output : -2
        //Expected : -1
        //
        //3, C???n ph???i chuy???n t?? duy:
        //3.1, S???p x???p theo (value) ch??a ????? --> C???n ph???i d??ng c??? (index) n???a
        //-5,-20,4,-1,3,-6,-3
        //3.2, Ta t?? duy t??nh theo t???ng v??? tr?? dp[i]
        //==> C???n l??u c??c gi?? tr??? tr?????c sao cho x??c ?????nh value ????? t??nh nhanh nh???t c?? th???.
        //
        //VD:
        //Step 1: num[2]=4;
        //dp[2] based on -5,-20.
        //Step 2: num[3]=-1
        //???????c t??nh theo -20,4
        //3.3, ??? ????y key idea l?? ta mu???n lo???i c??c gi?? tr??? nh??? h??n kh??ng c???n thi???t trong queue
        //C?? 2 ki???u ta c?? th??? lo???i ??i:
        //- Qu?? slide window
        //+ Check index khi push v??o queue.
        //- Value qu?? nh???
        //+ Lo???i ngay ??? tr?????ng h???p num[2]=4 --> N???u dp[2] l???n h??n bao nhi??u s??? --> remove h???t ch??? t??nh theo dp[2]
        //
        //==> K???t h???p c??? 2:
        //d??ng dp[i] l??u k???t qu??? + push(i) v??o l?? 1 l???a ch???n h???p l??.
        //
        //
        //Example:
        //-5,-20,4,-1,3,-6,-3
        //
        //3.4, 1 ??i???u c???n nh??? r???ng n???u mu???n theo quy t???c pop()
        //--> Th?? ta c???n ph???i s???p x???p c??c elements theo (t??ng d???n/ gi???m d???n) theo values ==> Ch??? kh??ng ph???i index ???? ???????c s???p x???p t??ng d???n ????? (th??? hi???n th??? t??? r???i)
        //==> pop index ra th?? kh??ng th??? hi???n v??? m???t value c???a c??c gi?? tr??? sau.
        //3.4.1, S???P X???P PH???I "THEO RULE VALUE"
        //==> Pop d???n c??c gi?? tr??? tr??n peek ra.
        //4, 1 t?? duy quan tr???ng n???a:
        //Ta s???p x???p ??? ????y ????? l??m g???
        //4.1, ????? l???y gi?? l???n nh???t nh??ng ph???i th???a m??n ??i???u ki???n index
        //- Kh??ng c???n ph???i check max v???i Priority queue
        //--> M?? th?????ng (t???n d???ng vi???c s???p x???p) + k???t h???p v???i check 1 c??i g?? ???? ????? (poll c??c value t??? top)
        //Khi n??o th?? ta d???ng l???i ==> Khi ta l???y ???????c gi?? tr??? t???i ??u nh???t (Max nh???t g???n top nh???t) + th???a m??n ??i???u ki???n.
        //VD: ??? ????y ta lu??n h?????ng l???y max ??? top + n???u index m?? kh??ng th???a m??n --> Ta m???i poll ????? l???y value th???a m??n (N???u kh??ng ta s??? poll value)
        //* N???u kh??ng trong slide --> poll(), ta c?? poll ?????n khi l???y ???????c gi?? tr??? max nh???t m?? th???a m??n in slide window.
        //Code : while(index + k > i) pop()
        //5, get the result on last element ==> Kh??ng ph???i max all of elements.

        //**CONCLUSION:
        //1, 1 ??i???u c???n nh??? r???ng n???u mu???n theo quy t???c pop()
        //--> Th?? ta c???n ph???i s???p x???p c??c elements theo (t??ng d???n/ gi???m d???n) theo values ==> Ch??? kh??ng ph???i index ???? ???????c s???p x???p t??ng d???n ????? (th??? hi???n th??? t??? r???i)
        //==> pop index ra th?? kh??ng th??? hi???n v??? m???t value c???a c??c gi?? tr??? sau.
        //1.1, S???P X???P PH???I "THEO RULE VALUE"

        //2.1, ????? l???y gi?? l???n nh???t nh??ng ph???i th???a m??n ??i???u ki???n index
        //- Kh??ng c???n ph???i check max v???i Priority queue
        //--> M?? th?????ng (t???n d???ng vi???c s???p x???p) + k???t h???p v???i check 1 c??i g?? ???? ????? (poll c??c value t??? top)
        //Khi n??o th?? ta d???ng l???i ==> Khi ta l???y ???????c gi?? tr??? t???i ??u nh???t (Max nh???t g???n top nh???t) + th???a m??n ??i???u ki???n.
        //VD: ??? ????y ta lu??n h?????ng (l???y max ??? top) + n???u (index m?? kh??ng th???a m??n) --> Ta m???i poll ????? l???y value th???a m??n (N???u kh??ng ta s??? poll value)

        //C??ch 2 : D??ng Dequeue
        //int arr[]=new int[]{0,-1,-2,-3,1};
        //int arr[]=new int[]{0,-2,-1,-3,1};
        //- V???i case n??y i=3 (nums[3]=-3) ta c?? 2 l???a ch???n (-1, -2)
        //--> L??c n??y ta s??? ch???n max c???a c??? 2.
        //Ta ph???i s???p x???p dequeue theo chi???u gi???m d???n t??? first.
        //- V???i m???i b?????c ta c???n poll value t??? first --> ????? kh??ng v????t ra ngo??i slide window.
        //VD: Case -3,-5,-2,-4, 5 : k=4
        //- Ta s??? ch??? s??? case li??n quan ?????n c??c gi?? tr??? ??? (middle) c?? th??? > -3
        //--> Ta kh??ng ch??? t??nh 1 case trung gian
        //==> Ta c???n t??nh t??? th???i ??i???m init queue
        // (-3,-5,-2,-4, 5)
        // step 1:
        //(-3)
        //(-3) (-2)
        //(-3) (-2) (-4) --> poll (-4)
        //(-3) (-2) 5 --> poll (-3)
        //***CONCLUSION:
        //????Y L?? T?? DUY CH???Y TRONG QUEUE ????? th???a m??n:
        //+ firstPeek : Lu??n l?? ??i???m l???n nh???t
        //+ first s??? ???????c poll d???a tr??n slide window.
        //--> Ch?? ?? ch??? quan t??m queue t??? l??c init --> final.
        //Wrong:
        //d[0]=nums[0] --> Ch?? ?? c??i n??y.
        //--> C?? th??? sai case.
        System.out.println(maxResultPriorityOptimized(arr, 2));
        System.out.println(maxResultDeque(arr, 2));
        System.out.println(maxResultPriorityOptimized1(arr, 2));
    }
}
