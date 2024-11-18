package E1_daily;

public class E151_ChalkboardXORGame {

    public static boolean xorGame(int[] nums) {
        int xor=0;
        for(int e: nums){
            xor^=e;
        }
        if(xor==0){
            return true;
        }
        return nums.length % 2 == 0;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given (an array of integers nums) represents (the numbers written) on a chalkboard.
        //- Alice and Bob take turns erasing exactly (one number) from the chalkboard, with Alice starting (first).
        //  + If erasing a number causes (the bitwise XOR of all the elements) of the chalkboard to (become 0),
        //      + then (that player) loses.
        //  + The (bitwise XOR) of (one element) is that element itself, and (the bitwise XOR) of (no elements) is 0.
        //- Also, if any player starts their turn with (the bitwise XOR of all the elements) of (the chalkboard equal to 0),
        //  + then that player (wins).
        //* Return (true) if and only (if Alice wins the game), assuming both players play optimally.
        //Example 1:
        //
        //Input: nums = [1,1,2]
        //Output: false
        //Explanation:
        //Alice has two choices: erase 1 or erase 2.
        //- If she erases 1, the nums array becomes [1, 2]. The bitwise XOR of all the elements of the chalkboard is 1 XOR 2 = 3.
        //  + Now Bob can remove any element he wants, because Alice will be the one to erase the last element and she will lose.
        //- If Alice erases 2 first, now nums become [1, 1]. The bitwise XOR of all the elements of the chalkboard is 1 XOR 1 = 0.
        //  + Alice will lose.
        //
        //- If the next turn of A (after removing x),
        //  + A got (xor all of elements) == 0:
        //  => A lose
        //      + 0 element --> 0 (lost)
        //      + >=2 elements --> Xor all == 0
        //
        //- A win only if
        //  + (A remove x) and (remaining only 1 element) for Bob
        //  + (A remove x) and B remove any elements ==> lost
        //      + (a,b,c,d)
        //      b ^ c ^ d = a ^ c ^ d = a ^ b ^ d = a ^ b ^ c
        //      => a = b = c = d
        //      ==> all of remaining elements are equal
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 1000
        //0 <= nums[i] < 2^16
        //  + n<=1000: Time: O(n^2) or backtrack + memo
        //  ==> It is impossible to use the bit mask:
        //      + 2^1000
        //
        //- Brainstorm
        //- Backtrack:
        //- Util remove n number we got the final result
        // 1,2,3,..,n
        // n,n-1,n-2,...,1
        // = n*(n-1)/2
        // = n^2
        //
        //- Both of people play optimally
        //- Alice try to choose the solution leading to result
        //  + Xor of all of the elements is equal to 0
        //a xor b = c
        //  + a xor b = 0
        //  ==> a == b
        //
        //- Formulas:
        //  + (x xor x) = 0
        //  + (x xor x xor x) = x
        //
        //- Alice win only if:
        //  + The number of element is (odd)
        //  + The remaining elements are (equal)
        //      + Exist: (a^b^c),(d,e)(even) == 0
        //Ex:
        //1,1,1,1,4,5,6,7,8
        //1 xor 0 = 1
        //0 xor 0 = 0
        //1 xor 1 = 0
        //
        //x xor y <= max(x,y)
        //  + (The result of xor operation) will be (decreased)
        //Ex:
        //10111
        //10101
        //=
        //00010 = 2
        //
        //Ex:
        //- len is even:
        //a^b^c^d
        //  + (a^b) == 0
        //  + Alice remove(d):
        //      + a^b^c
        //  + Bob won't remove(c)
        //      + Bob remove(a)
        //      + b^c
        //          + b^c == 0
        //              + b==c
        //          + b^c !=0
        //              + there is any bit 1 in (b) doesn't exist in (c)
        //              ==> b!=c
        //              + Alice remove(b): c
        //              + Bob remove(c) : b ==> Bob lose
        //- We assume (b!=c)
        //  + Alice must remove(c):
        //      + We want to avoid that bob remove(c) because we assume (b!=c)
        //  + If bob want to win:
        //      + b!=d (We have the same idea above)
        //       ==> Bob lose
        //- We assume (b!=c and b!=d)
        //  + Alice must remove(a):
        //      + a==b (b!=c and b!=d and c!=d)
        //      ==> b^c^d
        //      + We remove one by one
        //      + We don't have any pair of the number is equal
        //      a (Alice) -> b (Bob) -> c (Alice) -> d (Bob)
        //      => Bob lose
        //      Ex:
        //      b: 010: 2
        //      c: 111: 7
        //      d: 101: 5
        //      ==> b^c^d = 0
        //      ==> Alice lost
        //      + Alice won't choose this way because (Alice have opportunity to reject) (1 layer)
        //
        //- xor == 0
        //  + Return True
        //- xor!=0
        //
        //- Brute force first:
        //- Backtracking
        //  + Each time we choose the bit with 1 zero
        //
        //- Max number = 2^16
        //1...1
        //(16 digits)
        //
        //#Reference:
        //2136. Earliest Possible Day of Full Bloom
        //1685. Sum of Absolute Differences in a Sorted Array
        //2718. Sum of Matrix After Queries
        //
        System.out.println(2^2^2^2);
        System.out.println(2^2^2);
        System.out.println(2^2);
        System.out.println(2);
    }
}
