package interviews;

public class E81_NumberOfSmoothDescentPeriodsOfAStock {

    public static long getDescentPeriods(int[] prices) {
        int n=prices.length;
        int left=0;
        int prev=0;
        long rs=0;

        if(n>=1){
            rs=1;
            prev=prices[0];
        }

        for(int i=1;i<n;i++){
            if(prices[i]-prev!=-1){
                left=i;
                rs++;
            }else{
                rs+=i-left+1;
            }
            prev=prices[i];
        }

        return rs;
    }

    public static long getDescentPeriodsOptimize(int[] prices) {
        long ans = 0;
        int cnt = 0, prev = -1;
        for (int cur : prices) {
            if (prev - cur == 1) {
                ++cnt;
            }else {
                cnt = 1;
            }
            ans += cnt;
            prev = cur;
        }
        return ans;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{3,2,1,4};
//        int arr[]=new int[]{8,6,7,7};
//        int arr[]=new int[]{8};
        int arr[]=new int[]{};
        System.out.println(getDescentPeriods(arr));
        System.out.println(getDescentPeriodsOptimize(arr));
        //Bài này tư duy như sau:
        //1, Bài này là 1 dạng bài slice window
        //--> Ta vẫn áp dụng tư duy left --> Chỉ là khi gặp (i) không thỏa mãn rule
        //==> left=i.
        //2, Tối ưu:
        //2.1, Với cách giải tối ưu --> Nó không reset left <=> reset count
        //--> count : Số lần đếm mỗi lần ghép cặp.
    }
}
