package interviews;

import java.util.HashSet;

public class E13_HappyNumber {

    public static boolean isHappy(int n) {
        HashSet<Integer> sums=new HashSet<>();

        while(n!=1){
            n=nextSum(n);

            if(sums.contains(n)){
                return false;
            }
            sums.add(n);
        }
        return true;
    }

    public static int nextSum(int n){
        int sum=0;

        while(n!=0){
            sum+=(n%10)*(n%10);
            n=n/10;
        }
        return sum;
    }

    public static boolean isHappyOptimize(int n){
        int slow=n;
        int fast=n;
        while(slow!=1){
            slow=nextSum(slow);
            fast=nextSum(fast);
            fast=nextSum(fast);

            if(slow==fast&&slow!=1){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n=19;
        System.out.println(isHappy(19));
        System.out.println(isHappyOptimize(19));
    }

}
