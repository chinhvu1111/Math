/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author chinhvu
 */
public class DifferentWaysToAddParentheses_12 {

    public static HashMap<String, List<Integer>> values;

    public static boolean isOperator(char c) {
        return c == '*' || c == '-' || c == '+';
    }

//    public List<Integer> diffWaysToCompute(String expression) {
//        //Bời vì nó có thể có 2 chữ số nên mình cần phải lưu vào list
//        List<String> numbers=new ArrayList<>();
//        List<String> operators=new ArrayList<>();
//        StringBuilder temp=new StringBuilder();
//        
//        for(int i=0;i<expression.length();i++){
//            if(isOperator(expression.charAt(i))){
//                operators.add(String.valueOf(expression.charAt(i)));
//                numbers.add(temp.toString());
//                temp=new StringBuilder();
//            }else{
//                temp.append(expression.charAt(i));
//            }
//        }
//        
//    }
    public static Integer compute(Integer x, Integer y, char operator) {
        switch (operator) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            default:
                return 0;
        }
    }

//    public static List<Integer> diffWaysToCompute(String expression) {
//        if (values.get(expression) != null) {
//            return values.get(expression);
//        }
//        System.out.println(expression);
//
//        int n = expression.length();
//        StringBuilder firstNumber = new StringBuilder();
//        StringBuilder secondNumber = new StringBuilder();
//        char operatorFirst = 0;
//        boolean isFirst = false;
//        char operatorSecond = 0;
//        int indexFirst = 0;
//        int indexSecond = 0;
//
//        for (int i = 0; i < n; i++) {
//            indexSecond++;
//            if (!isFirst) {
//                indexFirst++;
//            }
//            if (isFirst && isOperator(expression.charAt(i))) {
//                operatorSecond = expression.charAt(i);
//                break;
//            }
//            if (!isFirst && isOperator(expression.charAt(i))) {
//                operatorFirst = expression.charAt(i);
//                isFirst = true;
//                continue;
//            }
//            if (!isFirst) {
//                firstNumber.append(expression.charAt(i));
//            } else {
//                secondNumber.append(expression.charAt(i));
//            }
//        }
//
//        List<Integer> tempFirst = (indexFirst < n)
//                ? diffWaysToCompute(expression.substring(indexFirst, n)) : new ArrayList<>();
//        List<Integer> tempSecond = (indexSecond < n)
//                ? diffWaysToCompute(expression.substring(indexSecond, n)) : new ArrayList<>();
//        List<Integer> rs = new ArrayList<>();
//        Integer numberFirst = (!"".equals(firstNumber.toString()))
//                ? Integer.valueOf(firstNumber.toString()) : -1;
//        Integer numberSecond = (!"".equals(secondNumber.toString()) && !"".equals(firstNumber.toString()))
//                ? compute(
//                        Integer.valueOf(firstNumber.toString()),
//                        Integer.valueOf(secondNumber.toString()), operatorFirst) : -1;
//        for (Integer v : tempFirst) {
//            rs.add(compute(numberFirst, v, operatorFirst));
//        }
//        for (Integer v : tempSecond) {
//                rs.add(compute(numberSecond, v, operatorSecond));
//            }
//        if (tempFirst.size() == 0 && numberFirst != -1) {
//            rs.add(numberFirst);
//        }
//        if (tempSecond.size() == 0
//                && numberSecond != -1
//                && indexSecond != n) {
//            rs.add(numberSecond);
//        }
//        values.put(expression, rs);
//        return rs;
//    }
    public static List<Integer> solution(String expression) {
        if(values.get(expression)!=null){
            return values.get(expression);
        }
            
        int n = expression.length();
        List<Integer> rs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            //Nếu là toán tử phép tính
            if (isOperator(expression.charAt(i))) {
                char operator=expression.charAt(i);
                String preEx = expression.substring(0, i);
                String suffEx = expression.substring(i+1, n);

                List<Integer> preRs = solution(preEx);
                List<Integer> suffRs = solution(suffEx);

                for (Integer v : preRs) {
                    for (Integer v1 : suffRs) {
                        rs.add(compute(v, v1, operator));
                    }
                }
            }
        }
        if(rs.size()==0){
            rs.add(Integer.valueOf(expression));
        }
        values.put(expression, rs);
        return rs;
    }

    public static void main(String[] args) {
        String s = "2*3-4*5";
        values = new HashMap<>();
        List<Integer> rs = solution(s);
        rs.forEach((t) -> {
            System.out.println(t);
        });
    }
}
