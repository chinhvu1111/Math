package interviews;

import java.util.Arrays;

public class E8_CountingBits {

    public static int[] countBits(int n) {
        int dp[]=new int[n+1];
        dp[1]=1;
        int cycleCount=1;
        int currentLength=2;
        int numberZero=1;

        for(int i=1;i<=n;i++){
            if(cycleCount==4){
                if(Math.pow(2, currentLength)!=i){
                    dp[i]=dp[i-1]-numberZero+1;
                    numberZero=2;
                }else{
                    dp[i]=1;
                    currentLength++;
                    numberZero=1;
                }
                cycleCount=0;
            }else if(cycleCount==2){
                dp[i]=dp[i-1];
            }else{
                dp[i]=dp[i-1]+1;
            }
            //Qua 1 chu kỳ : (00,01,10,11)
            if(cycleCount==3){
                numberZero+=1;
            }
            cycleCount++;
        }
        return dp;
    }

    private static boolean changeLength(int number, int prevNumber, int lengthBinary){
        return (double) number==(double)prevNumber+Math.pow(2, lengthBinary-1);
    }

    public static void main(String[] args) {
//        int n=5;
//        int n=1;
        //Case 1:
        //Sai cycle khi reset length --> dp[i]=1
        //<> thì apply theo ct nếu cycle=4 --> dp[i]=dp[i-4]+1
//        int n=8;
        //Case 1: reset cycle sai --> reset về 1
        //False: 0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1,2,2,3,2,3,3,4,3
        //True : 0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1,2,2,3,2,3,3,4,2
//        int n=24;
        //10100: 20
        //10101: 21
        //10110: 22
        //10111: 23
        //11000: 24 : numberZero=3
        //11001: 25
        //11010: 26
        //11011: 27
        //11100: 28
        int n=28;
        int arr[]=countBits(n);
        Arrays.stream(arr).forEach(i -> System.out.print(i+","));
    }
}
