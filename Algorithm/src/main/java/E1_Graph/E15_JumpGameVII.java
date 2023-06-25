package E1_Graph;

import java.util.LinkedList;
import java.util.Queue;

public class E15_JumpGameVII {

    public static class Pair{
        int x;
        int y;
        public Pair(int x, int y){
            this.x=x;
            this.y=y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return x+","+y;
        }
    }

    public static boolean canReach(String s, int minJump, int maxJump) {
        int n=s.length();
        if(s.charAt(n-1)!='0'){
            return false;
        }
        Queue<Pair> queueRanges=new LinkedList<>();
        int nextEnd=Math.min(maxJump, n-1);
        queueRanges.add(new Pair(minJump, nextEnd));
        if(nextEnd>=minJump&&(n-1>=minJump&&nextEnd>=n-1)){
            return true;
        }

        for(int i=1;i<n;i++){
            if(s.charAt(i)!='0'){
                continue;
            }
            Pair currentRange=null;
            if(!queueRanges.isEmpty()){
                currentRange=queueRanges.peek();
            }
            if(currentRange==null){
                continue;
            }
            System.out.printf("Out : %s (%s)\n", i, currentRange);
            if((i<=currentRange.y&&i>=currentRange.x)){
                int nextStartIndex=i+minJump;
                int nextEndIndex=Math.min(i+maxJump, n-1);
                System.out.printf("%s %s %s (%s)\n", i, nextStartIndex, nextEndIndex, currentRange);
                if(nextEndIndex<nextStartIndex){
                    continue;
                }
                if(nextStartIndex==n-1||nextEndIndex==n-1){
                    return true;
                }
                queueRanges.add(new Pair(nextStartIndex, nextEndIndex));
            }else if(i>currentRange.y){
                //i < start --> ignore
                // i >= end
                queueRanges.poll();
//                System.out.println(queueRanges);
                while (!queueRanges.isEmpty()&&(i > queueRanges.peek().y)){
                    queueRanges.poll();
                }
//                System.out.println(queueRanges);
                if(!queueRanges.isEmpty()){
                    currentRange=queueRanges.peek();
                }else{
                    currentRange=null;
                }
                if(currentRange!=null&&i>=currentRange.x&&i<=currentRange.y){
                    int nextStartIndex=i+minJump;
                    int nextEndIndex=Math.min(i+maxJump, n-1);
                    queueRanges.add(new Pair(nextStartIndex, nextEndIndex));
                    System.out.printf("%s %s %s (%s)\n", i, nextStartIndex, nextEndIndex, currentRange);
                    if(nextEndIndex<nextStartIndex){
                        continue;
                    }
                    if(nextStartIndex==n-1||nextEndIndex==n-1){
                        return true;
                    }
                }
            }
            System.out.println(queueRanges);
        }
        return false;
    }

    public static boolean canReachRefactor(String s, int minJump, int maxJump) {
        int n=s.length();
        if(s.charAt(n-1)!='0'){
            return false;
        }
        Queue<Pair> queueRanges=new LinkedList<>();
        int nextEnd=Math.min(maxJump, n-1);
        queueRanges.add(new Pair(minJump, nextEnd));
        if(nextEnd>=minJump&&(n-1>=minJump&&nextEnd>=n-1)){
            return true;
        }

        for(int i=1;i<n;i++){
            if(s.charAt(i)!='0'){
                continue;
            }
            Pair currentRange=null;
            if(!queueRanges.isEmpty()){
                currentRange=queueRanges.peek();
            }
            if(currentRange==null){
                continue;
            }
            int nextStartIndex=i+minJump;
            int nextEndIndex=Math.min(i+maxJump, n-1);
//            System.out.printf("Out : %s (%s)\n", i, currentRange);
            if((i<=currentRange.y&&i>=currentRange.x)){
                boolean result=addPair(nextStartIndex, nextEndIndex, n, queueRanges);
                if(result){
                    return true;
                }
            }else if(i>currentRange.y){
                //i < start --> ignore
                // i >= end
                queueRanges.poll();
//                System.out.println(queueRanges);
                while (!queueRanges.isEmpty()&&(i > queueRanges.peek().y)){
                    queueRanges.poll();
                }
//                System.out.println(queueRanges);
                if(!queueRanges.isEmpty()){
                    currentRange=queueRanges.peek();
                }else{
                    currentRange=null;
                }
                if(currentRange!=null&&i>=currentRange.x&&i<=currentRange.y){
                    boolean result=addPair(nextStartIndex, nextEndIndex, n, queueRanges);
                    if(result){
                        return true;
                    }
                }
            }
//            System.out.println(queueRanges);
        }
        return false;
    }

    public static boolean addPair(int nextStartIndex, int nextEndIndex, int n, Queue<Pair> queueRanges){
        if(nextEndIndex<nextStartIndex){
            return false;
        }
        if(nextStartIndex==n-1||nextEndIndex==n-1){
            return true;
        }
        queueRanges.add(new Pair(nextStartIndex, nextEndIndex));
        return false;
    }

    public static boolean canReachDynamicProgramming(String s, int minJ, int maxJ) {
        int n = s.length();
        int count=0;
        boolean[] dp=new boolean[n];
        dp[0]=true;

        for(int i=1;i<n;i++){
            if(i>=minJ&&dp[i-minJ]){
                count++;
            }
            if(i>maxJ&&dp[i-maxJ-1]){
                count--;
            }
            dp[i]=count>0&&s.charAt(i)=='0';
        }
        return dp[n-1];
    }

    public static boolean canReachDynamicProgrammingRefactor(String s, int minJ, int maxJ) {
        if(s == null || s.length() == 0) return false;
        int n = s.length();
        if(s.charAt(0) == '1' || s.charAt(n - 1) == '1') return false;
        boolean[] dp=new boolean[n];
        dp[0]=true;
        int start;
        int end=0;

        for(int i=0;i<n;i++){
            if(!dp[i]){
                continue;
            }
            start=Math.max(i+minJ, end+1);
            end=Math.min(n-1, i+maxJ);
            for(int j=start;j<=end;j++){
                if(s.charAt(j)=='0'){
                    dp[j]=true;
                }
            }
            if(dp[n-1]){
                return true;
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        //** Requirement
        //- Kiểm tra xem liệu có thể move (i=0) --> (n-1) : return (true/ false)
        //+ (i) có thể (i + minJump) <= j <= min(i + maxJump, n)
        //+ s[i]==0
        //** Idea
        //1. Dùng queue range (a,b)
        //1.0,
        //- Constraint:
        //+ 2 <= s.length <= 10^5
        //--> Với length này --> Chỉ có thể xử lý với O(n)
        //
        //+ s[i] is either '0' or '1'.
        //+ s[0] == '0'
        //+ 1 <= minJump <= maxJump < s.length
        //
        //VD:
        //s="011010", minJump = 2, maxJump = 3
        //index=0 : có thể đến (2 --> 3)
        //+ (min, max) : chia thành range được đánh là true
        //- Khi chia range thì ta sẽ có những cases như sau:
        //VD:
        //(start, end) và (start1, end1)
        //+ Case 1: start < start1 < end1 < end ==> (start, end)
        //+ Case 2: start1 < start < end1 < end ==> (start1, end)
        //+ Case 3: start < start1 < end1 < end ==> (start, end)
        //+ Case 4: start < start1 < end1 < end ==> (start, end)
        //+ Case 5: start < start1 < end1 < end ==> (start, end)
        //+ Case 6: start < start1 < end1 < end ==> (start, end)
        //+ Case 7: start < end (GAP) start1 < end1 ==> (start, end)
        //--> Có thể xảy ra khi 0111111100000 ==> 000 đằng sau không thể đến được.
        //--> i tăng --> (start, end) tăng
        //+ Test case 1:
        //arr =[0,1,1,1,1,1,0,1,0]
        //Steps:
        //start=MAX, end=MIN
        //index=0: newStart=2, newEnd=3 ==> (2,3)
        //  + (2,3) chỉ có 0 mới đi đến được
        //index=6 (Không thuộc 2,3 trước đó - break): newStart=8, newStart=9 ==> (8,9)
        //  + (3,4) chỉ có 1 mới đi đến được
        //+ Test case 2:
        //0,1,1,0,1,0,0,0
        //minJum=2
        //maxJum=3
        //+ index=0: (2,3)
        //+ index=3: (2,3) + (5,6) --> Merge ntn
        //Question là có case nào:
        //--> index tiếp vẫn thuộc (2,3) hay không
        //+ Test case 2.1:
        //0,1,0,1,0,0,0,1
        //minJum=2
        //maxJum=3
        //+ index=0: (2,3)
        //+ index=2: (2,3) + (4,5) --> Merge ntn
        //+ index=3: ==> xét khoảng nào
        //- Giả sử (i) thuộc (start, end) : (j),...,start, i , ..., end ==> (i+minJump, i + maxJump)
        //i+ minJump - end = i + minJump - j - minJump = (i - j)
        //
        //** --> Không merge nữa (Do không merge được) --> add all range vào queue --> pop dần ra khi index vượt quá giới hạn.
        //
        //+ Test case 3:
        //0,1,1,0,1,0,0,0,1
        //minJum=2
        //maxJum=5
        //+ index=0: (2,5)
        //+ index=3: (2,5) + (4,8) --> Merge ntn
        //
        //- Dùng BFS:
        //+ Node --> List node( s[i]==0 và trong khoảng i)
        //
        //1.1, Test cases
//        String s = "011010000";
//        int minJump = 2, maxJump = 3;
//        String s = "01101110";
//        int minJump = 2, maxJump = 3;
        //- Special test case 1:
//        String s = "00111010";
        //(3,5), (8-->7,10-->7)(bỏ)
//        int minJump = 3, maxJump = 5;
        //- Special test case 2:
//        String s = "00";
//        int minJump = 1, maxJump = 1;
        //- Special test case 3:
//        String s = "0000000000";
//        int minJump = 8, maxJump = 8;
        //- Special test case 4:
        //+ Case này pop cả khi index < currentNode.x
        //--> Đoạn này pop xong --> Cần phải check tiếp
        //if(currentRange!=null&&i>=currentRange.x&&i<=currentRange.y)
        String s = "011100110101011011011110";
        //0111(00)110101011011011110 : (4,5)
        //0111(00)11(01)01011011011110 : (8,9)
        //0111(00)11(01)01011011011110 : (8,9)
        //18 ([14,15, 16,17, 19,20]) --> pop (19,20)
        int minJump = 4, maxJump = 5;
//        System.out.println(canReach(s, minJump, maxJump));

//        String s = "0100100010";
        //010(01)00010 : (3,4)
        //010(01)0(00)10 : (6,7)
        //010(01)0(00)10 :
        // 5 [(3,4), (6,7)] ==> Do 5<6 pop (6,7) ra thì hơi sai
//        int minJump = 3, maxJump = 4;
        //1.2, Complexity
        //- Time complexity : O(n) : beat 86%
        //- Space complexity : O(n)
        //
        //* Method 2: Vẫn dùng optimized BFS
        //2.0, Idea
        //- Cách này dùng BFS nhưng kèm việc optimization
        //+ Nếu dùng BFS bình thường thì sẽ bị TLE
        //+ Ta sẽ không scan hết n-1 phần tử --> Ta sẽ chỉ cần check đến maxReach mà thôi.
        //+ maxReach= Math.min(idx + maxJump + 1, s.length() - 1);
        //+ Danh sách nodes ta cần connect đến là
        //  + j>=Math.max(idx + minJump, maxReach) --> (<=maxReach) trước đó đã được add(nodes) trước rồi.
        //  + j<=min(idx + maxJump, n-1)
        //
        //* Method 3: Dynamic programming + slice window
        //3.0, Idea
        //- dp[i] : là kết quả có thể đến tại vị trí (i) của array
        //- dp[i] tính như thế nào?
        //+ Có thể tính được từ các điểm trước (i)
        // ==> Có thể có nhiều (j) trước (i) có thể đến được (i)
        //- j --> (j+minJum, j+maxJum (nếu <n-1 <> n-1))
        //+ Nếu j muốn đến được (j>=minJump)
        //- Giả sử j --> (j + minJump <= i <= j+maxJump)
        //- Liệu có kiếm tra dp[i] mà chỉ check dựa trên (minJump hoặc maxJump không)
        //+ i>=minJump && dp[i-minJump]==true ==> dp[i]=true --> Vì chỉ cần trong khoảng của (j+minJump) nào đó là được
        //+ Xét với maxJump
        //VD: 11(0)1100
        //+ minJump=4
        //+ maxJump=6
        //+ index = 2
        //+ last = 6
        //+ Nếu (dp[i - maxJump] ==true) --> dp[i] vẫn bằng true được --> Vì chỉ cần trong khoảng của (j+maxJump) nào đó là được
        //
        //3.1, Special test cases
        //- 11111(0)1100
        //+ minJump=4
        //+ maxJump=6
        //+ index = 8 thì tính trên index=5 như thế nào?
        //+ dp[index-minJump]= dp[4] = false
        //+ dp[index-maxJump]= dp[2] = false
        //==> Nhìn qua test case trên thì không được
        //
        //- Tư duy dạng (i) --> Sẽ keep count số index có thể reach đến (i)
        //==> Có thể đến i thì có thể từ:
        //+ j+minJump (max) [i-minJump]
        //+ j+maxJump (min) [i-maxJump]
        //==> i-maxJump <= index <= i-minJump
        //VD:
        //- 01111(0)1100
        //+ minJump=4
        //+ maxJump=6
        //+ index = 5 :
        //  dp[5]=true --> tính theo dp[0]=true
        //  pre=1
        //- Tư duy như sau:
        //- Mỗi index ++ --> đều có 1 window số lượng (j) có thể reach riêng (Kể cả nó ==1)
        //+ Mỗi lần tăng i ---> window slide sẽ dịch sang phải 1 đơn vị ===> DẤU HIỆU NHẬN BIẾT để LÀM SLIDE WINDOW <Các khoảng dịch 1 đơn vị 1>
        //+ Ở rìa sẽ là bỏ đi dp(i-maxJum-1) và thêm dp(i-minJump+1)
        //===> Cái này cần suy luận thêm --> Về cơ bản là bảo toàn số lượng thôi.
        //- Tức là mỗi index ta sẽ thu được thêm giá trị nào --> Khi tăng index lên thì ==> slice window cũng di chuyển
        // thêm 1 đơn vị ==> Các giá trị biên nếu đã add vào thì sẽ phải có lúc remove nó ra ngoài (dp[i-minJump]==true)
        // Và đòng thời thêm dp(i-minJump+1)
        //* Kinh nghiệm tư duy:
        //- Tư duy dạng bảo toàn của slice window.
        //
        //3.2, Complexity:
        //- Time complexity : O(n)
        //- Space complexity : O(n)
        //
        //3.3, Refactor
        //- https://leetcode.com/problems/jump-game-vii/solutions/1622748/explained-java-solution-beat-100-dp-sliding-window-easy-approach/
        System.out.println(canReach(s, minJump, maxJump));
        System.out.println(canReachRefactor(s, minJump, maxJump));
        System.out.println(canReachDynamicProgramming(s, minJump, maxJump));
        System.out.println(canReachDynamicProgrammingRefactor(s, minJump, maxJump));
        //#Reference:
        //1. Two Sum
        //1345. Jump Game IV
        //1340. Jump Game V
        //2297. Jump Game VIII
    }

}
