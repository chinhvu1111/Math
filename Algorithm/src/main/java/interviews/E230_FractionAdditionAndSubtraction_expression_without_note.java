package interviews;

public class E230_FractionAdditionAndSubtraction_expression_without_note {

    public static String fractionAddition(String expression) {
        int numerator=0;
        int denominator=0;
        int n=expression.length();
        char prevSign=0;

        for (int i = 0; i < n; i++) {
            int currentNumerator=0;
            boolean isNumber=false;

            while (i<n&&!isOperator(expression.charAt(i))){
                isNumber=true;
                currentNumerator=currentNumerator*10+expression.charAt(i)-'0';
                i++;
            }
            if(isNumber){
                i++;
            }
            int currentDenominator=0;

            while (i<n&&!isOperator(expression.charAt(i))){
                currentDenominator=currentDenominator*10+expression.charAt(i)-'0';
                i++;
            }
            if(isNumber){
                if(numerator==0&&denominator==0){
                    if(prevSign=='-'){
                        numerator=-1*currentNumerator;
                    }else{
                        numerator=currentNumerator;
                    }
                    denominator=currentDenominator;
                }else{
                    if(prevSign=='-'){
                        numerator= numerator* currentDenominator - currentNumerator * denominator;
                    }else{
                        numerator= numerator* currentDenominator + currentNumerator * denominator;
                    }
                    denominator=denominator*currentDenominator;
                    int commonDivisor=greatestCommonDivisor(numerator, denominator);
                    numerator=numerator/commonDivisor;
                    denominator=denominator/commonDivisor;

                    if(denominator<0){
                        numerator=numerator*-1;
                        denominator=denominator*-1;
                    }
//                    System.out.printf("%s %s\n", numerator, denominator);
                }
            }
            if(i<n){
                prevSign=expression.charAt(i);
            }
        }
        return numerator+"/"+denominator;
    }

    public static boolean isOperator(char c){
        return c=='+'||c=='/'||c=='-';
    }

    public static int greatestCommonDivisor(int a, int b){
        if(a>b){
            int temp=a;
            a=b;
            b=temp;
        }
        while (b!=0){
            int c=a%b;

            if(c==0){
                return b;
            }
            a=b;
            b=c;
        }
        return 1;
    }

    public static void main(String[] args) {
        String s="-1/2+1/2";
//        String s="-1/2+1/2+1/3";
//        String s="1/3-1/2";
//        String s="1/3";
        System.out.println(fractionAddition(s));
    }
}
