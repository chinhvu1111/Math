package interviews;

import java.util.Arrays;

public class E72_GuessNumberHigherOrLower {

    public static int arr[];

    public static int guess(int num){
        return arr[num];
    }

    public static int guessNumber(int n) {
        return searchElement(1, n);
    }

    public static int searchElement(int low, int high){
        int mid= low+ (high-low)/2;
        int index=-1;

        if(guess(mid)==1){
            index=searchElement(mid+1, high);
        }else if(guess(mid)==-1){
            index=searchElement(low, mid-1);
        }else{
            return mid;
        }
        return index;
    }

    public static void main(String[] args) {
        int n=10;
//        int n=2126753390;
        int k=6;
        arr=new int[n+1];
        Arrays.fill(arr, -1);
        arr[k]=0;
        int mid=1+ (2126753390-1)/2;
        System.out.println(guessNumber(n));
    }
}
