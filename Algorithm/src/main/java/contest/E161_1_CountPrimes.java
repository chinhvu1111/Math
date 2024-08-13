package contest;

public class E161_1_CountPrimes {

    public static int countPrimes(int n) {
        if(n<=2){
            return 0;
        }
        //Time: O(n)
        boolean[] isNotPrimes=new boolean[n];

        //Time: O(sqrt(n))
        for(int i=2;i<=Math.sqrt(n);i++){
            //Time: O((n-i*i)/i) = O(n/i-i) ==> O(log log(n))
            for(int j=i*i;j<n;j+=i){
                isNotPrimes[j]=true;
            }
        }
        int rs=0;

        for(int i=2;i<n;i++){
            rs+=!isNotPrimes[i]?1:0;
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //-
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //- 0 <= n <= 5 * 10^6
        //
        //** Brainstorm
        //- Use Sieve of Eratosthenes:
        //
        //- Now the question is: What are the bounds on these two loops? The outer loop will start at 2 and go up to (n)
        //This is because by that point we will have considered all of the possible multiples of all the prime numbers below n.
        // Let's look at the example where n is 30. Now the square-root of n is greater than 5.
        //Ex:
        //It is not necessary to consider any number greater than the square root of n.
        //
        //6 * 1 = 6 = 1 * 6
        //6 * 2 = 12 = 2 * 6
        //6 * 3 = 18 = 3 * 6
        //6 * 4 = 24 = 2 * 12
        //6 * 5 = 30 = 5 * 6
        //6 * 6 = 36 > 30
        //
        //- Notice that every multiple of 6 was already addressed by some multiple of a prime number less than 6.
        //==> Chỉ cần check (i=2 ==> Sqrt(n)) là được
        //
        //- Cơ chế ở đây là xét i:
        //  + Tất cả các bội số của (i) chắc chắn không phải là prime
        //      + Chỗ này ta chỉ cần loop tất cả các bội số của (i) là được
        //  + Khi xét next(i):
        //      + Nếu cái nào là bội số ==> next không xét
        //          ==> vì từ previous ta có thể tính được next(current_i) mà không cần xét thêm bội số của current_i
        //
        //- Tại sao xét bội số lại chỉ xét từ:
        //  + i*i:
        //      + j=i++
        //      + i*j
        //      + Vì (i-1)*i đã được xét bằng những previous(i) rồi nên ta không cần xét nó nữa.
        //Ex:
        //3*2 ==> Đã được xét từ 2*3 rồi
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n+sqrt(n)*loglog(n))
        //
        int n = 10;
        System.out.println(countPrimes(n));
    }
}
