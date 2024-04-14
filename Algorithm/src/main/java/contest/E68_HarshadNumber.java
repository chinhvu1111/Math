package contest;

public class E68_HarshadNumber {

    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int sumDigit=0;
        int value=x;
        while (value!=0){
            sumDigit+= value%10;
            value = value/10;
        }
        return x%sumDigit==0?sumDigit:-1;
    }

    public static void main(String[] args) {
        //* Requirement
        //-
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //- Brainstorm
    }
}
