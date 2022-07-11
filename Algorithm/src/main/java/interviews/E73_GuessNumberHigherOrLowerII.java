package interviews;

import java.util.Arrays;

public class E73_GuessNumberHigherOrLowerII {

    public static int arr[][];

    public static int getMoneyAmount(int n) {
        arr=new int[n+1][n+1];

        for(int i=0;i<=n;i++){
            Arrays.fill(arr[i], -1);
        }
        int rs=maxCost(1, n);

        return rs;
    }

    public static int maxCost(int low, int high){
        if(low>=high-1){
            return 0;
        }
        if(arr[low][high]!=-1){
            return arr[low][high];
        }

        int left=0;
        int right=0;
        int min=Integer.MAX_VALUE;

        for(int i=low+1;i<high;i++){
            right=maxCost(i+1, high) + i;
            left=maxCost(low, i-1) + i;
            min=Math.min(min, Math.max(right, left));
        }

        return (arr[low][high]=min);
    }

    public static void main(String[] args) {
        int n=10;
        System.out.println(getMoneyAmount(n));
    }
}
