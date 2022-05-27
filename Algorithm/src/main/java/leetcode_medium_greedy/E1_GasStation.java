package leetcode_medium_greedy;

public class E1_GasStation {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n=gas.length;
//        int sub[]=new int[n];
//        int dp[]=new int[n];
        int sum=0;

        for(int i=0;i<n;i++){
//            sub[i]=gas[i]-cost[i];
            sum+=gas[i]-cost[i];
//            dp[i]=sum;
        }
        if(sum<0){
            return -1;
        }
        int indexRs=-1;
        int acc=0;

//        if(n>=1){
//            acc=sub[0];
//        }
//        if(n>=1&&sub[0]>=0){
//            indexRs=0;
//        }
        for(int i=0;i<n;i++){
            acc+=gas[i]-cost[i];
//            if(acc>=0&&indexRs==-1){
//                indexRs=i;
//            }
            if(acc<0){
                acc=0;
                indexRs=(i+1)%n;
            }else if(acc>=0&&indexRs==-1){
                indexRs=i;
            }
        }
        return indexRs;
    }

    public static int canCompleteCircuitOptimize(int[] gas, int[] cost) {
        int gasSum  = 0, costSum = 0, total = 0, pos = 0;

        for(int i : gas) gasSum += i;
        for(int i : cost) costSum += i;

        if(gasSum < costSum) return -1;

        for(int i=0;i<gas.length;i++) {
            total += gas[i] - cost[i];
            if(total < 0) {
                total = 0;
                pos = i+1;
            }
        }
        return pos;
    }

    public static void main(String[] args) {
//        int gas[]=new int[]{1,2,3,4,5};
//        int cost[] = new int[]{3,4,5,1,2};
        //Case 1:
        //Expect 3
        //+ Case này ta phải tư duy cộng dồn giá trị >0
        //Nếu <0 thì chắc chắn không phải index
//        int gas[]=new int[]{5,8,2,8};
//        int cost[] = new int[]{6,5,6,6};
        //Case 2:
        //[3,1,1]
        //[1,2,2]
//        int gas[]=new int[]{3,1,1};
//        int cost[] = new int[]{1,1,2};

        //Case 3: Optimize thì bị sai
        //[1,2]
        //[2,1]
        //Output : 0
        //Expect : 1
        //--> Nhầm case đầu check sub[0] thay vì gas[0]
//        int gas[]=new int[]{1,2};
//        int cost[] = new int[]{2,1};
        //Case 4:
        //[2,0,3]
        //[1,2,2]
//        int gas[]=new int[]{2,0,3};
//        int cost[] = new int[]{1,2,2};
        //Output: 0
        //Expect: 2
        //Case 5:
        //[3,1,1]
        //[1,2,2]
//        int gas[]=new int[]{3,1,1};
//        int cost[] = new int[]{1,2,2};
        //Case 6:
        //[2,3,1]
        //[3,1,2]
        int gas[]=new int[]{2,3,1};
        int cost[] = new int[]{3,1,2};
        //Bài này ta tư duy như sau:
        //Vì bài này là dạng đi vòng tròn
        //VD:
        // gas: 1,4,2
        // cost:2,3,100
        //start= 2 (gas[2]=2)
        //--> Đi hết vòng thì bắt buộc phải mất = (2-100) + (1-2) +(4-3)[Để quay trở về i=2]
        //1, Bài toán chuyển thành việc tìm tổng dương min (Gần 0 nhất)
        //Giá sử ta tìm được nó ở vị trí (i) --> (i --> n-1) tổng của hiệu (gas[i-->n-1]-cost[i-->n-1] >=0)
        //Vị trí (i) phải thỏa mãn nếu đi từ (i --> n-1) sẽ không xuất hiện sum <0 lần nào
        //Giả sử nó có case:
        //(-5, -3, 7, 14, -5)
        //Với trường hợp này ta có thể chọn (7,/14) đều được.
        //---> Ta ưu tiên chọn 7 hơn vì khi indexRs=2 (=7) --> Tức là nó sẽ >0 --> Cộng dần lên nên kết quả từ (7) sẽ tối ưu hơn so với (14)
        //---> Trên all cases
        //--> Còn các giá trị đằng trước (i)
        //Ta có 2 cách xử lý:
        //1, Vì kiểu gì cũng sum all values --> Ta dãy đó có thỏa mãn hay không dựa trên sum (all) <0 --> return -1
        //--> Nếu tìm được indexRs cũng sai
        //2, Ta có thể tạo 1 array lưu sum đến từng vị trí (i) --> Sau khi chốt (i) Ta có thể + startSum[i] >=0 --> return indexRs : -1
        //2, Tạo sub[n] là thừa --> Ta chỉ cần lấy hiệu gas[i]-cost[i] là được.
        System.out.println(canCompleteCircuit(gas, cost));
    }
}
