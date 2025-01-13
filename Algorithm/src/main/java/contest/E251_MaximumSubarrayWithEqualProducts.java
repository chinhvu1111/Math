package contest;

public class E251_MaximumSubarrayWithEqualProducts {

    public static int USCLN(int a, int b) {
        if (b == 0) return a;
        return USCLN(b, a % b);
    }

    public static int BCNN(int a, int b) {
        return (a*b)/USCLN(a, b);
    }

    public static int maxLength(int[] nums) {
        int n=nums.length;
        int rs=0;

        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                int prod=1;
                for(int h=i;h<=j;h++){
                    prod=prod*nums[h];
                }
                int gcd=nums[i];
                for(int h=i+1;h<=j;h++){
                    gcd=USCLN(gcd, nums[h]);
                }
                int lcm=nums[i];
                for(int h=i+1;h<=j;h++){
                    lcm=BCNN(lcm, nums[h]);
                }
                if(prod==gcd*lcm){
                    rs=Math.max(rs, j-i+1);
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //You are given an array of positive integers nums.
        //
        //An array arr is called product equivalent if prod(arr) == lcm(arr) * gcd(arr), where:
        //
        //prod(arr) is the product of all elements of arr.
        //gcd(arr) is the GCD of all elements of arr.
        //lcm(arr) is the LCM of all elements of arr.
        //Return the length of the longest product equivalent subarray of nums.
        //
        //A subarray is a contiguous non-empty sequence of elements within an array.
        //
        //The term gcd(a, b) denotes the greatest common divisor of a and b.
        //
        //The term lcm(a, b) denotes the least common multiple of a and b.
        int[] nums = {1,2,1,2,1,1,1};
        System.out.println(USCLN(1,2));
        System.out.println(USCLN(1,1));
        System.out.println(BCNN(2,2));
        System.out.println(BCNN(2,1));
        System.out.println(maxLength(nums));
    }
}
