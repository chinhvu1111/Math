package contest;

public class E208_CheckBalancedString {

    public static boolean isBalanced(String num) {
        int n = num.length();
        int sum=0;
        int sumEven=0;

        for(int i=0;i<n;i++){
            if(i%2==0){
                sumEven+=num.charAt(i)-'0';
            }
            sum+=num.charAt(i)-'0';
        }
        return sum-sumEven==sumEven;
    }

    public static void main(String[] args) {
        //* Requirement
        //-
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= num.length <= 100
        //num consists of digits only
        //
        //** Brainstorm
        //
        //
        String num = "1234";
        System.out.println(isBalanced(num));
    }
}
