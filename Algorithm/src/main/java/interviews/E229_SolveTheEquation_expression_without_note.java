package interviews;

import java.util.Deque;
import java.util.LinkedList;

public class E229_SolveTheEquation_expression_without_note {

    public static String solveEquation(String equation) {
        int index=equation.indexOf('=');
        int n=equation.length();
        int[] left=getShortenExpressionRefactor(equation, 0, index);
        int[] right=getShortenExpressionRefactor(equation, index+1, n);

        int mulX=right[0]-left[0];
        int value=left[1]-right[1];

        if(mulX==0){
            if(value==0){
                return "Infinite solutions";
            }
            return "No solution";
        }
        int result=value/mulX;

        return "x="+result;
    }

    public static int[] getShortenExpression(String equation, int start, int end){
        Deque<String> deque=new LinkedList<>();
        int currentValue=0;
        int mulNumberLeft=0;

        for (int i = start; i < end; i++) {
            StringBuilder currentNumber=new StringBuilder();
            boolean isX=false;

            while (i<end&&!isOperator(equation.charAt(i)+"")){
                currentNumber.append(equation.charAt(i));
                if(equation.charAt(i)=='x'){
                    isX=true;
                }
                i++;
            }
            if(isX){
                int mulX=1;

                if(currentNumber.length()>1){
                    mulX=Integer.parseInt(currentNumber.substring(0, currentNumber.toString().length()-1));
                }

                if(deque.isEmpty()){
                    deque.add("x");
                    mulNumberLeft=mulX;
                }else{
                    if(!"-".equals(deque.peekLast())){
                        deque.removeLast();
                        mulNumberLeft+=mulX;
                    }else{
                        deque.removeLast();
                        mulNumberLeft-=mulX;
                    }
                }
            }else if(!"".equals(currentNumber.toString())){
                int number=Integer.parseInt(currentNumber.toString());

                if(deque.isEmpty()){
                    deque.addLast(number+"");
                    currentValue=number;
                }else{
                    if(!"-".equals(deque.peekLast())){
                        deque.removeLast();
                        currentValue+=number;
                    }else{
                        deque.removeLast();
                        currentValue-=number;
                    }
                }
            }
            if(i<end){
                deque.addLast(equation.charAt(i)+"");
            }
        }
        return new int[]{mulNumberLeft, currentValue};
    }

    public static int[] getShortenExpressionRefactor(String equation, int start, int end){
        int currentValue=0;
        int mulNumberLeft=0;
        String prevSign="";

        for (int i = start; i < end; i++) {
            StringBuilder currentNumber=new StringBuilder();
            boolean isX=false;

            while (i<end&&!isOperator(equation.charAt(i)+"")){
                currentNumber.append(equation.charAt(i));
                if(equation.charAt(i)=='x'){
                    isX=true;
                }
                i++;
            }
            if(isX){
                int mulX=1;

                if(currentNumber.length()>1){
                    mulX=Integer.parseInt(currentNumber.substring(0, currentNumber.toString().length()-1));
                }

                if(mulNumberLeft==0){
                    if("-".equals(prevSign)){
                        mulNumberLeft=mulX*-1;
                    }else{
                        mulNumberLeft=mulX;
                    }
                }else{
                    if(!"-".equals(prevSign)){
                        mulNumberLeft+=mulX;
                    }else{
                        mulNumberLeft-=mulX;
                    }
                }
            }else if(!"".equals(currentNumber.toString())){
                int number=Integer.parseInt(currentNumber.toString());

                if(currentValue==0){
                    if("-".equals(prevSign)){
                        currentValue=number*-1;
                    }else{
                        currentValue=number;
                    }
                }else{
                    if(!"-".equals(prevSign)){
                        currentValue+=number;
                    }else{
                        currentValue-=number;
                    }
                }
            }
            if(i<end){
                prevSign=equation.charAt(i)+"";
            }
        }
        return new int[]{mulNumberLeft, currentValue};
    }

    public static boolean isOperator(String s){
        return "+".equals(s)|| "-".equals(s);
    }

    public static void main(String[] args) {
//        String equation = "x+5-3+x=6+x-2";
//        String equation = "x=x";
//        String equation = "2x=x";
        String equation = "-x=1";

        System.out.println(solveEquation(equation));
    }
}
