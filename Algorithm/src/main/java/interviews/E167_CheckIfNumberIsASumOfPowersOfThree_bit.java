package interviews;

public class E167_CheckIfNumberIsASumOfPowersOfThree_bit {

    public static boolean checkPowersOfThree(int n) {
        int number=n;

        while (number%3!=1){
            number/=3;
        }
        return true;
    }

    public static void main(String[] args) {
        int n=91;
    }
}
