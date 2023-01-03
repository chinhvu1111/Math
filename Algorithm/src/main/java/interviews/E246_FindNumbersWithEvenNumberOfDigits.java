package interviews;

public class E246_FindNumbersWithEvenNumberOfDigits {

    public static int getNumberDigits(int n){
        int numberDigits=0;
        while (n!=0){
            n=n/10;
            numberDigits++;
        }
        return numberDigits;
    }

    public static int findNumbers(int[] nums) {
        int n=nums.length;
        int rsCount=0;

        for (int num : nums) {
            int numberGigits = getNumberDigits(num);

            if (numberGigits % 2 == 0) {
                rsCount++;
            }
        }
        return rsCount;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{12,345,2,6,7896};
        System.out.println(findNumbers(arr));
        //
        //#Reference
        //1296. Divide Array in Sets of K Consecutive Numbers
        //902. Numbers At Most N Given Digit Set
        //2134. Minimum Swaps to Group All 1's Together II
        //2408. Design SQL
    }
}
