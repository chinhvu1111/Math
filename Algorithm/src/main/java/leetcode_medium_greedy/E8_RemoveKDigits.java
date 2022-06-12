package leetcode_medium_greedy;
import java.util.Stack;

public class E8_RemoveKDigits {

    public static String removeKdigitsOptimize(String num, int k) {
        char[] arr = num.toCharArray();
        int top = -1;
        for (int i = 0; i <= arr.length; i++) {
            while (k > 0 && top > -1 && arr[top] > (i == arr.length ? Integer.MIN_VALUE : arr[i])) {
                --top;
                if (--k == 0) break;
            }
            if (i < arr.length && (arr[i] != '0' || top >= 0)) {
                arr[++top] = arr[i];
            }
        }
        return top == -1 ? "0" : new String(arr, 0, top + 1);
    }

    public static String removeKdigits(String num, int k) {
        Stack<Integer> characters=new Stack<>();
        characters.push(0);
        boolean visited[]=new boolean[num.length()];

        if(k>=num.length()){
            return "0";
        }

        for(int i=1;i<num.length();i++){
            Integer indexTop=characters.peek();
            Character valueTop=num.charAt(indexTop);
            Character currentCharacter=num.charAt(i);

            if(currentCharacter>valueTop){
                characters.push(i);
            }else{
                while (!characters.isEmpty()&&k>0
                        &&num.charAt(characters.peek())>currentCharacter){
                    visited[characters.pop()]=true;
                    k--;
                }
                characters.push(i);
            }
//            if(k==0){
//                break;
//            }
        }
        if(k!=0){
            while (!characters.isEmpty()&&k>0){
                visited[characters.pop()]=true;
                k--;
            }
        }
        StringBuilder rs=new StringBuilder();
        boolean isNotZero=false;

        for(int i=0;i<=characters.size()-1;i++){
            if(!isNotZero&&num.charAt(characters.get(i))!='0'){
                isNotZero=true;
            }
            if(isNotZero){
                rs.append(num.charAt(characters.get(i)));
            }
        }

//        for(int i=0;i<num.length();i++){
//            if((!visited[i]&&num.charAt(i)!='0')
//                    || (!visited[i]&&num.charAt(i)=='0'
//                    &&(!"".equals(rs.toString())||i==num.length()-1))
//            ){
//                rs.append(num.charAt(i));
//            }
//        }
        if("".equals(rs.toString())){
            return "0";
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        String s="1432219";
        int k=3;
//        String s="10200";
//        int k=1;
        //Case 1: chuỗi empty
//        String s="10";
//        int k=1;
        //Case 2: tăng dần
//        String s="12345";
//        int k=2;
        // Case 3: các số bằng nhau
//        String s="1111111";
//        int k=3;
//        String s="10001";
//        int k=4;
        System.out.println(removeKdigits(s, k));
    }
}
