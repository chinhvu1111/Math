package interviews;

public class E112_CheckIfAParenthesesStringCanBeValid {

    public static boolean canBeValid(String s, String locked) {
        return false;
    }

    public static void main(String[] args) {
        String s="))()))";
        String locked="010100";
        System.out.println(canBeValid(s, locked));
    }
}
