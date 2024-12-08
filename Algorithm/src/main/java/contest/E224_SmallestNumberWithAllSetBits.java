package contest;

public class E224_SmallestNumberWithAllSetBits {

    public static int smallestNumber(int n) {
        int temp=n;
        int rs=0;
        int mul=1;
        while (temp!=0){
            rs+=mul;
            temp=temp/2;
            mul=mul*2;
        }
        return rs;
    }

    public static void main(String[] args) {
        //You are given a positive number n.
        //* Return the smallest number x greater than or equal to n,
        // such that (the binary representation of x) contains (only set bits).
        //- A set bit refers to a bit in the binary representation of a number that has a value of 1.
//        int n = 5;
        int n = 10;
        System.out.println(smallestNumber(n));
    }
}
