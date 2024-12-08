package contest;

import java.util.HashMap;
import java.util.HashSet;

public class E230_DigitOperationsToMakeTwoIntegersEqual {

    static HashMap<Integer, Integer> memo;
    public static int rs;
    public static int[] cal = {1,10,100,1000,10000,100000};
    public static int solution(int index, int n, int m, int length, HashSet<Integer> visited){
        if(n==m){
//            System.out.println(visited);
            return m;
        }
//        if(index==length){
//            return Integer.MAX_VALUE;
//        }
//        System.out.println(n);
        if(memo.containsKey(n)){
            return memo.get(n);
        }
        int curRs=Integer.MAX_VALUE;
        //Increase
        for(int i=0;i<length;i++){
            //92
            //+ 2
            //+ 9
            if((n/cal[i])%10<9){
                int nextVal = n+cal[i];
                if(isPrimeOptimization(nextVal)||visited.contains(nextVal)){
                    continue;
                }
                visited.add(nextVal);
                int nextRs= solution(index+1, nextVal, m, length, visited);
                visited.remove(nextVal);
                if(nextRs==Integer.MAX_VALUE){
                    continue;
                }
                curRs=Math.min(curRs, nextRs+n);
            }
        }
        //Decrease
        for(int i=0;i<length;i++){
            if((n/cal[i])%10>0){
                int nextVal = n-cal[i];
                if(isPrimeOptimization(nextVal)||visited.contains(nextVal)){
                    continue;
                }
                visited.add(nextVal);
                int nextRs= solution(index+1, nextVal, m, length, visited);
                visited.remove(nextVal);
                if(nextRs==Integer.MAX_VALUE){
                    continue;
                }
                curRs=Math.min(curRs, nextRs+n);
            }
        }
        int nextRs=Integer.MAX_VALUE;
        if(!visited.contains(n)){
            visited.add(n);
            nextRs= solution(index+1, n, m, length, visited);
            visited.remove(n);
        }
        if(nextRs!=Integer.MAX_VALUE){
            curRs=Math.min(curRs, nextRs);
        }
        memo.put(n, curRs);
        return curRs;
    }

    public static int minOperations(int n, int m) {
        memo=new HashMap<>();
        int length=0;
        int temp=n;
        while (temp!=0){
            temp=temp/10;
            length++;
        }
        int rs=Integer.MAX_VALUE;
        if(!isPrimeOptimization(n)){
            rs=solution(0, n, m, length, new HashSet<>());
        }
//        System.out.println(memo);
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    public static boolean isPrimeOptimization(int number){
        if(number<=1){
            return false;
        }
        if(number<=3){
            return true;
        }
        if(number%2==0||number%3==0){
            return false;
        }
        for(int i=5;i*i<=number;i+=6){
            if(number%i==0||number%(i+2)==0){
                return false;
            }
        }
        return true;
    }

    public static boolean isPrime(int number){
        if(number<=1){
            return false;
        }
        for(int i=2;i<=Math.sqrt(number);i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two integers (n and m) that consist of the (same number of digits).
        //- You can perform the following operations any number of times:
        //  + Choose any digit from n that is not 9 and increase it by 1.
        //  + Choose any digit from n that is not 0 and decrease it by 1.
        //- The integer n (must not be a prime) number at any point, including its original value and after each operation.
        //- The cost of a transformation is the sum of all values that n takes throughout the operations performed.
        //* Return the minimum cost to transform (n into m).
        //+ If it is impossible, return -1.
        //- A prime number is a natural number greater than 1 with (only two factors), (1 and itself).
        //
        //Example 1:
        //
        //Input: n = 10, m = 12
        //Output: 85
        //Explanation:
        //- We perform the following operations:
        //Increase the first digit, now n = 20.
        //Increase the second digit, now n = 21.
        //Increase the second digit, now n = 22.
        //Decrease the first digit, now n = 12.
        //
        //- We can not perform:
        //  + 10 -> 11 -> 12
        //  => 11 is the prime number
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= n, m < 10^4
        //  + number of digits = 4 (10000)
        //n and m consist of the same number of digits.
        //
        //- Brainstorm
        //- Min cost
        //  + Minimize (sum of intermediates value)
        //- Make higher priority for the smaller unit digits
        //Ex:
        //ab
        //->
        //99
        //11
        //9*9*9*9*9
        //- Recursion + memo
        //
//        int n = 10, m = 12;
//        int n = 17, m = 72;
        int n = 76, m = 21;
        //output = 475
        //rs: 457
        System.out.println(isPrimeOptimization(91));
        System.out.println(isPrime(91));
        System.out.println(minOperations(n, m));
    }
}
