package interviews;

import java.util.Arrays;

public class E8_CountingBits {

    public static int[] countBits(int n) {
        int[] dp =new int[n+1];

        if(n==0){
            return dp;
        }
        dp[1]=1;
        int cycleCount=1;
        int currentLength=2;
//        int numberZero=1;

        for(int i=1;i<=n;i++){
            if(cycleCount==4){
                if(Math.pow(2, currentLength)!=i){
                    dp[i]=binaryZeros(i);
//                    numberZero=2;
                }else{
                    dp[i]=1;
                    currentLength++;
//                    numberZero=1;
                }
                cycleCount=0;
            }else if(cycleCount==2){
                dp[i]=dp[i-1];
            }else{
                dp[i]=dp[i-1]+1;
            }
            //Qua 1 chu kỳ : (00,01,10,11)
//            if(cycleCount==3){
//                numberZero+=1;
//            }
            cycleCount++;
        }
        return dp;
    }

    private static boolean changeLength(int number, int prevNumber, int lengthBinary){
        return (double) number==(double)prevNumber+Math.pow(2, lengthBinary-1);
    }

    public static int binaryZeros(int n) {
        long val = n & 0xFFFFFFFFL;
//        int totalBits = (int)(Math.log(val) / Math.log(2) + 1);
        int setBits = Long.bitCount(val);
        return setBits;
    }

    public static int[] countBitsDynamicPrograming(int n) {
        int[] countBit = new int[n + 1];
        int m=1;
        int currentValue=1;

        for(int i=1;i<=n;i++){
            if(i-currentValue>=0){
                countBit[i]=countBit[i-currentValue]+1;
            }
//            System.out.printf("%s %s %s\n", i, countBit[i],  currentValue);
            if(i+1>=currentValue*2){
                currentValue*=2;
            }
        }
        return countBit;
    }

    public static void main(String[] args) {
//        int n=5;
//        int n=1;
        //Đề bài : Đếm số bit 1 trong biểu diễn của số C.
        //Case 1:
        //Sai cycle khi reset length --> dp[i]=1
        //<> thì apply theo ct nếu cycle=4 --> dp[i]=dp[i-4]+1
//        int n=8;
        //Case 1: reset cycle sai --> reset về 1
        //False: 0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1,2,2,3,2,3,3,4,3
        //True : 0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1,2,2,3,2,3,3,4,2
//        int n=24;
        //Case 1: dp[i]=dp[i]-numberZero+1
        //Viết như thế này vì nghĩ rằng 1000 --> 1100
        //start: 19
        //10100: 20 : number of Zero=2
        //10101: 21
        //10110: 22
        //10111: 23
        //11000: 24 : number of Zero=3 (Tăng)
        //11001: 25
        //11010: 26
        //11011: 27
        //11100: 28 : number of Zero=2
        //11101 : 29
        //11110 : 30
        //11111 : 31
        //100000 : 32 : number of Zero=1
        //case 2:
        //1000 --> 1100
        //Vẫn thiếu trường hợp:
        //10100 --> 11000
        //10100: 20
        //10101: 21
        //10110: 22
        //10111: 23
        //11000: 24
        int n=28;
//        int n=64;
//        int n=0;
        int arr[]=countBits(n);
        //Để bài:
        //Input: n = 5
        //Output: [0,1,1,2,1,2]
        //Explanation:
        //0 --> 0
        //1 --> 1
        //2 --> 10
        //3 --> 11
        //4 --> 100
        //5 --> 101
        //Bài này tư duy như sau:
        //1, Bài này ta dùng dynamic programming
        //1.1, Dynamic ở đây được xác định là chỉ có hiệu lực trong 1 cycle nhất định
        //+ cycleCount=4
        //
        //Cách 2:
        //2.
        //2.1, Idea
        //0:0
        //1: (1) : m=1
        //2: 10 : (1) : m=1
        //3: 11 : 2 : m=1
        //4: 100 (1)
        //5: 101 : 2
        //6: 110 : 2
        //7: 111 : 3
        //8:1000 : (1)
        //9: 1001 : 2
        //10: 1010 : 2
        //11: 1011 : 3
        //12: 1100 : 2
        //13: 1101 : 3
        //14: 1110 : 3
        //15: 1111 : 3
        //15: 10000 : (1)
        //- Ta có:
        //+ (2,3) = 1 + (0,1)
        //<->
        // 10= 1 - 0
        // 11= 1 - 1
        //--> (2,3) = 2^1 + (0,1)
        //+ (4,7) = 1 + (0,3)
        //<->
        //100 = 2^2 + 0
        //1011 = 2^2 + 3
        //==> Đây là việc ta đặt 1 đằng trước ==> Tăng số lượng số 1 lên 1
        //VD:
        //5 : 101 = 2^1 + 1
        //(5) = (1) + 1
        //2.2, Ta tư duy như sau:
        //- Công thức truy hồi như sau:
        //
        //m : 1 --> n ==>
        //- count_bit(x) = count_bit(x-2^m) + 1 vs (2^m >x)
        //2.3,
        //- Chú ý: ta chỉ tăng m (2^m) khi:
        //+ i+1>= 2^m *2 ==> Next loop có thể tính được.
        //2.4, Complexity:
        //- Time complexity: O(n)
        //- Space complexity : O(n)
//        Arrays.stream(arr).forEach(i -> System.out.print(i+","));
        arr=countBitsDynamicPrograming(n);
        Arrays.stream(arr).forEach(i -> System.out.print(i+","));
        //#Reference:
        //339. Nested List Weight Sum
        //1916. Count Ways to Build Rooms in an Ant Colony
        //1521. Find a Value of a Mysterious Function Closest to Target
        //847. Shortest Path Visiting All Nodes
    }
}
