package contest;

public class E293_ClosestPrimeNumbersInRange {

    public static boolean isPrime(int number){
        if(number==1){
            return false;
        }
        for(int i=2;i*i<=number;i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }

    public static int[] closestPrimes(int left, int right) {
        int x=-1,y=-1;
        int prevX=-1;

        for(int i=left;i<=right;i++){
            if(isPrime(i)){
//                System.out.println(i);
                if(prevX!=-1&&((i-prevX)<(y-x)|(x==-1&&y==-1))){
                    y=i;
                    x=prevX;
                }
                prevX=i;
            }
        }
        return new int[]{x, y};
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given two positive integers (left and right), find the two integers (num1 and num2) such that:
        //  + left <= num1 < num2 <= right .
        //  + Both (num1 and num2) are prime numbers.
        //  + (num2 - num1) is the ("minimum") amongst all other pairs satisfying (the above conditions).
        //* Return the positive integer array ans = [num1, num2].
        //  + If there are multiple pairs satisfying these conditions,
        // return the one with (the smallest "num1" value).
        //  + If no such numbers exist, return [-1, -1].
        //
        //Example 1:
        //
        //Input: left = 10, right = 19
        //Output: [11,13]
        //Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19.
        //The closest gap between any pair is 2, which can be achieved by [11,13] or [17,19].
        //Since 11 is smaller than 17, we return the first
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= left <= right <= 10^6
        //
        //- Brainstorm
        //Ex:
        //Input: left = 10, right = 19
        //Output: [11,13]
        //
        //- (a-b) > (b-c)
        //  + Update x=b,y=c
        //
        //- We don't need to loop from (left to right)
        //  + We loop from 1 -> x*x<=right
        //  Ex:
        //
        //1.1, Cases
//        int left = 10, right = 19;
        int left = 1, right = 1000000;
        int[] rs = closestPrimes(left, right);
//        System.out.println(isPrime(11));
        System.out.printf("%s %s\n", rs[0], rs[1]);
        //
        //1.2, Optimization
        //- Sieve of Eratosthenes
        //
        //
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(n*sqrt(n))
        //
        //#Reference:
        //1735. Count Ways to Make Array With Product
    }
}
